package org.razordevs.ascended_quark.entity.render;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.razordevs.ascended_quark.blocks.AQBlocks;
import org.razordevs.ascended_quark.entity.AQEntityTypes;
import org.razordevs.ascended_quark.entity.block.AQBlockEntityTypes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AQEntityRenderers {

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AQEntityTypes.STOOL.get(), StoolEntityRender::new);
		event.registerBlockEntityRenderer(AQBlockEntityTypes.SKYROOT_CHEST.get(), AQChestRender::new);
		event.registerBlockEntityRenderer(AQBlockEntityTypes.SKYROOT_TRAPPED_CHEST.get(), AQTrappedChestRender::new);
		event.registerEntityRenderer(AQEntityTypes.AMBROSIUM_TORCH_ARROW.get(), AmbrosiumTorchArrowRenderer::new);
	}

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(AQBlocks.SKYROOT_LADDER.get(), RenderType.cutout());
	}
}
