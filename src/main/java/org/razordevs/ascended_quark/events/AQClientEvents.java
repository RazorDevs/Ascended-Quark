package org.razordevs.ascended_quark.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQBlocks;
import org.razordevs.ascended_quark.particle.AQParticles;
import org.razordevs.ascended_quark.particle.custom.AmbrosiumShardParticle;

@Mod.EventBusSubscriber(modid = AscendedQuark.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AQClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.SKYROOT_HEDGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.CRYSTAL_SKYROOT_HEDGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.HOLIDAY_SKYROOT_HEDGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.DECORATED_HOLIDAY_SKYROOT_HEDGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.CRYSTAL_FRUIT_SKYROOT_HEDGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.GOLDEN_SKYROOT_HEDGE.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(AQBlocks.SKYROOT_LEAF_CARPET.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.CRYSTAL_LEAF_CARPET.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.HOLIDAY_LEAF_CARPET.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.DECORATED_HOLIDAY_LEAF_CARPET.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.CRYSTAL_FRUIT_LEAF_CARPET.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(AQBlocks.GOLDEN_OAK_LEAF_CARPET.get(), RenderType.cutoutMipped());
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(AQParticles.AMBROSIUM_SHARD_PARTICLE.get(), AmbrosiumShardParticle.Provider::new);
    }
}
