package org.razordevs.ascended_quark.proxy;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.handler.AQButtonHandler;
import org.razordevs.ascended_quark.handler.AQClientUtil;
import org.razordevs.ascended_quark.handler.AQConfigHomeScreen;

import java.util.function.Supplier;

public class ACClientProxy extends ACCommonProxy {
    public void start() {
        AscendedQuark.ZETA.playBus
                .subscribe(AQClientUtil.class);


        AscendedQuark.ZETA.playBus
                .subscribe(AQButtonHandler.class)
                .subscribe(AQClientUtil.class);

        super.start();

        Supplier<IConfigScreenFactory> configScreen = () ->
                (mc, previousScreen) -> new AQConfigHomeScreen(previousScreen);
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, configScreen);
    }
}
