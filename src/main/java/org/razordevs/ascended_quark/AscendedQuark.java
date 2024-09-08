package org.razordevs.ascended_quark;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.razordevs.ascended_quark.proxy.ACClientProxy;
import org.razordevs.ascended_quark.proxy.ACCommonProxy;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zetaimplforge.ForgeZeta;

@Mod(AscendedQuark.MODID)
public class AscendedQuark {

    //TODO: Handle better Documentation
    //TODO: 1.1 Planning with other Addon compat

    public static AscendedQuark instance;
    public static ACCommonProxy proxy;

    public static final String MODID = "ascended_quark";
    public static final String DEEP_AETHER = "deep_aether";
    public static final Zeta ZETA = new ForgeZeta(MODID, LogManager.getLogger("aq-zeta"));

    public AscendedQuark() {
        instance = this;
        ZETA.start();

        proxy = Env.unsafeRunForDist(() -> ACClientProxy::new, () -> ACCommonProxy::new);
        proxy.start();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
