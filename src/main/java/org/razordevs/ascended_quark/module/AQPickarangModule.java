package org.razordevs.ascended_quark.module;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.content.tools.client.render.entity.PickarangRenderer;
import org.violetmoon.quark.content.tools.config.PickarangType;
import org.violetmoon.quark.content.tools.entity.rang.AbstractPickarang;
import org.violetmoon.quark.content.tools.entity.rang.Flamerang;
import org.violetmoon.quark.content.tools.entity.rang.Pickarang;
import org.violetmoon.quark.content.tools.item.PickarangItem;
import org.violetmoon.quark.content.tools.module.PickarangModule;
import org.violetmoon.zeta.advancement.ManualTrigger;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.*;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;
import org.violetmoon.zeta.util.Hint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@ZetaLoadModule(category = "aether")
public class AQPickarangModule extends ZetaModule {

    @Config(flag = "phoenix_flamerang")
    public static boolean enableFlamerang = true;

    @Hint
    public static Item valk_pickarang;
    @Hint("flamerang")
    public static Item phoenix_flamerang;

    private static final List<PickarangType<?>> knownTypes = new ArrayList<>();
    private static boolean isEnabled;

    @LoadEvent
    public final void register(ZRegister event) {
        valk_pickarang = makePickarang(PickarangModule.pickarangType, "valkyrie_pickarang", Pickarang::new, Pickarang::new, BooleanSuppliers.TRUE).setCreativeTab(CreativeModeTabs.TOOLS_AND_UTILITIES, Items.DIAMOND_HOE, false);
        phoenix_flamerang = makePickarang(PickarangModule.flamerangType, "phoenix_flamerang", Flamerang::new, Flamerang::new, () -> enableFlamerang).setCreativeTab(CreativeModeTabs.TOOLS_AND_UTILITIES, Items.NETHERITE_HOE, false);
    }

    private <T extends AbstractPickarang<T>> PickarangItem makePickarang(PickarangType<T> type, String name,
                                                                         EntityType.EntityFactory<T> entityFactory,
                                                                         PickarangType.PickarangConstructor<T> thrownFactory,
                                                                         BooleanSupplier condition) {

        EntityType<T> entityType = EntityType.Builder.of(entityFactory, MobCategory.MISC)
                .sized(0.4F, 0.4F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .setCustomClientFactory((t, l) -> entityFactory.create(type.getEntityType(), l))
                .build(name);
        Quark.ZETA.registry.register(entityType, name, Registries.ENTITY_TYPE);

        knownTypes.add(type);
        type.setEntityType(entityType, thrownFactory);
        return (PickarangItem) new PickarangItem(name, this, propertiesFor(type.durability, type.isFireResistant()), type).setCondition(condition);
    }

    private Item.Properties propertiesFor(int durability, boolean fireResist) {
        Item.Properties properties = new Item.Properties()
                .stacksTo(1);

        if (durability > 0)
            properties.durability(durability);

        if (fireResist)
            properties.fireResistant();

        return properties;
    }

    @LoadEvent
    public final void configChanged(ZConfigChanged event) {
        // Pass over to a static reference for easier computing the coremod hook
        isEnabled = this.enabled;
    }

    public static boolean getIsFireResistant(boolean vanillaVal, Entity entity) {
        if (!isEnabled || vanillaVal)
            return vanillaVal;

        Entity riding = entity.getVehicle();
        if (riding instanceof AbstractPickarang<?> pick)
            return pick.getPickarangType().isFireResistant();

        return false;
    }

    @ZetaLoadModule(clientReplacement = true)
    public static class Client extends AQPickarangModule {
        @LoadEvent
        public final void clientSetup(ZClientSetup event) {
            knownTypes.forEach(t -> EntityRenderers.register(t.getEntityType(), PickarangRenderer::new));
        }
    }
}
