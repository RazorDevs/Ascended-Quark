package org.razordevs.ascended_quark.proxy;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.handler.AQButtonHandler;
import org.razordevs.ascended_quark.handler.AQClientUtil;
import org.razordevs.ascended_quark.handler.AQConfigHomeScreen;
import org.violetmoon.quark.base.client.handler.ClientUtil;
import org.violetmoon.quark.base.client.handler.ModelHandler;
import org.violetmoon.quark.base.handler.WoodSetHandler;

public class ACClientProxy extends ACCommonProxy {
    //TODO: CONFIG SCREEN

    public void start() {
        AscendedQuark.ZETA.loadBus
                .subscribe(ModelHandler.class)
                .subscribe(WoodSetHandler.Client.class)
                .subscribe(ClientUtil.class)
                .subscribe(AQClientUtil.class);

        AscendedQuark.ZETA.playBus
                .subscribe(ClientUtil.class)
                .subscribe(AQButtonHandler.class)
                .subscribe(AQClientUtil.class);

        super.start();

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (minecraft, screen) -> new AQConfigHomeScreen(screen)));
    }
}
