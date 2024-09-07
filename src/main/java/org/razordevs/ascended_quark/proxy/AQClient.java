package org.razordevs.ascended_quark.proxy;

import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.zeta.client.ClientTicker;
import org.violetmoon.zeta.client.ZetaClient;
import org.violetmoon.zeta.util.ZetaSide;
import org.violetmoon.zetaimplforge.client.ForgeZetaClient;

public class AQClient {

    static {
        if (AscendedQuark.ZETA.side == ZetaSide.SERVER)
            throw new IllegalAccessError("SOMEONE LOADED AQClient ON THE SERVER!!!! DON'T DO THAT!!!!!!");
    }

    public static final ZetaClient ZETA_CLIENT = new ForgeZetaClient(AscendedQuark.ZETA);
    public static final ClientTicker ticker = ClientTicker.INSTANCE;

}
