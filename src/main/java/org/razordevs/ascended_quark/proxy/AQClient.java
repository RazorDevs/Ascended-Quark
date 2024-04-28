package org.razordevs.ascended_quark.proxy;

import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.integration.lootr.client.ClientLootrIntegration;
import org.violetmoon.quark.integration.lootr.client.IClientLootrIntegration;
import org.violetmoon.zeta.client.ClientTicker;
import org.violetmoon.zeta.client.ZetaClient;
import org.violetmoon.zeta.util.ZetaSide;
import org.violetmoon.zetaimplforge.client.ForgeZetaClient;

public class AQClient {
    public static AQClient instance;
    public static final ZetaClient ZETA_CLIENT;
    public static final ClientTicker ticker;

    public static void start() {
        instance = new AQClient();
        ZETA_CLIENT.start();
    }

    static {
        ZETA_CLIENT = new ForgeZetaClient(AscendedQuark.ZETA);
        ticker = ZETA_CLIENT.ticker;
    }

}
