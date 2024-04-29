package org.razordevs.ascended_quark.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.particle.AQParticles;
import org.razordevs.ascended_quark.particle.custom.AmbrosiumShardParticle;

@Mod.EventBusSubscriber(modid = AscendedQuark.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AQClientEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(AQParticles.AMBROSIUM_SHARD_PARTICLE.get(), AmbrosiumShardParticle.Provider::new);
    }
}
