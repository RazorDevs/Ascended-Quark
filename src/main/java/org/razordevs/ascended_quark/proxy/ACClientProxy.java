package org.razordevs.ascended_quark.proxy;

import net.minecraft.core.RegistryAccess;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.base.client.handler.ClientUtil;
import org.violetmoon.quark.base.client.handler.ModelHandler;
import org.violetmoon.quark.base.client.handler.QuarkProgrammerArtHandler;
import org.violetmoon.quark.base.handler.ContributorRewardHandler;
import org.violetmoon.quark.base.handler.WoodSetHandler;

import javax.annotation.Nullable;

public class ACClientProxy extends ACCommonProxy {
    //TODO: CONFIG SCREEN

    public void start() {
        AscendedQuark.ZETA.loadBus.subscribe(ModelHandler.class).subscribe(WoodSetHandler.Client.class).subscribe(ClientUtil.class);
        super.start();

//        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> {
//            return new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
//                return new QuarkConfigHomeScreen(screen);
//            });
//        });
    }
}
