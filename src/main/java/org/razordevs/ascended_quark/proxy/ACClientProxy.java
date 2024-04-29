package org.razordevs.ascended_quark.proxy;

import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.base.client.handler.ModelHandler;

public class ACClientProxy extends ACCommonProxy {
    //TODO: CONFIG SCREEN

    public void start() {
        AQClient.start();
        AscendedQuark.ZETA.loadBus.subscribe(ModelHandler.class);
        super.start();
    }
}
