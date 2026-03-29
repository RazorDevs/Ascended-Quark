package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.entity.AmbrosiumTorchArrow;
import org.razordevs.ascended_quark.entity.render.AmbrosiumTorchArrowRenderer;
import org.razordevs.ascended_quark.particle.AmbrosiumShardParticle;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.item.ZetaArrowItem;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.Hint;

@ZetaLoadModule(category = "aether")
public class AmbrosiumTorchArrowModule extends ZetaModule {

    public static EntityType<AmbrosiumTorchArrow> ambrosiumTorchArrowType;
    public static SimpleParticleType ambrosiumShardParticle;

    @Hint
    public static ArrowItem ambrosium_torch_arrow;

    @LoadEvent
    public final void register(ZRegister register) {
        ambrosium_torch_arrow = new ZetaArrowItem.Impl("ambrosium_torch_arrow", this, AmbrosiumTorchArrow::new, AmbrosiumTorchArrow::new);

        ambrosiumTorchArrowType = EntityType.Builder.<AmbrosiumTorchArrow>of(AmbrosiumTorchArrow::new, MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .clientTrackingRange(4)
                .updateInterval(20)
                .build("ambrosium_torch_arrow");

        register.getRegistry().register(ambrosiumTorchArrowType, "ambrosium_torch_arrow", Registries.ENTITY_TYPE);
        DispenserBlock.registerBehavior(ambrosium_torch_arrow, new ProjectileDispenseBehavior(ambrosium_torch_arrow));

        ambrosiumShardParticle = new SimpleParticleType(true);
        register.getRegistry().register(ambrosiumShardParticle, "ambrosium_shard_particle", Registries.PARTICLE_TYPE);
    }

    @ZetaLoadModule(clientReplacement = true)
    public static class Client extends AmbrosiumTorchArrowModule {

        @LoadEvent
        public final void clientSetup(ZClientSetup event) {
            EntityRenderers.register(ambrosiumTorchArrowType, AmbrosiumTorchArrowRenderer::new);
        }
    }


}
