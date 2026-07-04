package org.razordevs.ascended_quark.proxy;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.integration.AQOBEIntegration;
import org.razordevs.ascended_quark.module.AmbrosiumTorchArrowModule;
import org.razordevs.ascended_quark.particle.AmbrosiumShardParticle;
import org.violetmoon.zeta.client.ZetaClient;
import org.violetmoon.zeta.util.ZetaSide;
import org.violetmoon.zetaimplforge.client.ForgeZetaClient;

public class AQClient {

	static {
		if (AscendedQuark.ZETA.side == ZetaSide.SERVER)
			throw new IllegalAccessError("SOMEONE LOADED AQClient ON THE SERVER!!!! DON'T DO THAT!!!!!!");


        if(AscendedQuark.ZETA.isModLoaded("obe")) {
            AQOBEIntegration.init();
        }
	}

	public static final ZetaClient ZETA_CLIENT = new ForgeZetaClient(AscendedQuark.ZETA);

	@SuppressWarnings("deprecation")
	public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
		Minecraft.getInstance().particleEngine.register(AmbrosiumTorchArrowModule.ambrosiumShardParticle,
				AmbrosiumShardParticle.Provider::new);
	}
}
