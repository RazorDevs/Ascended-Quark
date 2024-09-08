package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.razordevs.ascended_quark.blocks.AQStoolBlock;
import org.razordevs.ascended_quark.entity.AQStool;
import org.razordevs.ascended_quark.entity.render.StoolEntityRender;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class SkyrootStoolModule extends ZetaModule {


    public static EntityType<AQStool> stoolEntity;

    @LoadEvent
    public void register(ZRegister register) {
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQStoolBlock("skyroot_stool", this), AetherBlocks.SKYROOT_PLANKS);

        stoolEntity = EntityType.Builder.of(AQStool::new, MobCategory.MISC)
                .sized(0.375F, 0.5F)
                .clientTrackingRange(3)
                .updateInterval(Integer.MAX_VALUE)
                .setShouldReceiveVelocityUpdates(false)
                .setCustomClientFactory((spawnEntity, world) -> new AQStool(stoolEntity, world)).build("stool");
        AscendedQuark.ZETA.registry.register(stoolEntity, "stool", Registries.ENTITY_TYPE);
    }

    @LoadEvent
    public final void clientSetup(ZClientSetup event) {
        EntityRenderers.register(stoolEntity, StoolEntityRender::new);
    }
}
