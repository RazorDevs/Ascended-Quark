package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.RegistryUtil;
import org.razordevs.ascended_quark.entity.AmbrosiumTorchArrow;
import org.razordevs.ascended_quark.entity.render.AmbrosiumTorchArrowRenderer;
import org.razordevs.ascended_quark.particle.AmbrosiumShardParticle;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.item.ZetaArrowItem;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.Hint;

@Mod.EventBusSubscriber(modid = AscendedQuark.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@ZetaLoadModule(category = "aether")
public class AmbrosiumTorchArrowModule extends ZetaModule {

    public static EntityType<AmbrosiumTorchArrow> ambrosiumTorchArrowType;
    public static SimpleParticleType ambrosiumShardParticle;

    @Hint
    public static Item ambrosium_torch_arrow;

    @LoadEvent
    public void register(ZRegister register) {
        ambrosium_torch_arrow = new ZetaArrowItem.Impl("ambrosium_torch_arrow", this, (level, stack, living) -> new AmbrosiumTorchArrow(level, living));
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES.getKey(), ambrosium_torch_arrow, AetherItems.ENCHANTED_DART);

        ambrosiumTorchArrowType = EntityType.Builder.<AmbrosiumTorchArrow>of(AmbrosiumTorchArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("ambrosium_torch_arrow");
        register.getRegistry().register(ambrosiumTorchArrowType, "ambrosium_torch_arrow", Registries.ENTITY_TYPE);
        DispenserBlock.registerBehavior(ambrosium_torch_arrow, new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level level, Position position, ItemStack itemStack) {
                AmbrosiumTorchArrow torch_arrow = new AmbrosiumTorchArrow(level, position.x(), position.y(), position.z());
                torch_arrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return torch_arrow;
            }
        });

        ambrosiumShardParticle = new SimpleParticleType(true);
        register.getRegistry().register(ambrosiumShardParticle, "ambrosium_shard_particle", Registries.PARTICLE_TYPE);

    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ambrosiumShardParticle, AmbrosiumShardParticle.Provider::new);
    }

    @ZetaLoadModule(clientReplacement = true)
    public static class Client extends AmbrosiumTorchArrowModule {

        @LoadEvent
        public final void clientSetup(ZClientSetup event) {
            EntityRenderers.register(ambrosiumTorchArrowType, AmbrosiumTorchArrowRenderer::new);
        }
    }


}
