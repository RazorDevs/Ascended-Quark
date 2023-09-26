package com.razordevs.ascended_quark.entity;

import com.razordevs.ascended_quark.entity.block.AQEntityTypes;
import com.razordevs.ascended_quark.entity.render.StoolEntityRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AQEntityRenderers {


	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AQEntityTypes.STOOL.get(), StoolEntityRender::new);
	}
}
