package org.razordevs.ascended_quark.proxy;

public class ACClientProxy extends ACCommonProxy {
    //TODO: CONFIG SCREEN

    public void start() {
        //AscendedQuark.ZETA.loadBus.subscribe(ModelHandler.class).subscribe(WoodSetHandler.Client.class).subscribe(ClientUtil.class);
        super.start();

//        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> {
//            return new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
//                return new QuarkConfigHomeScreen(screen);
//            });
//        });
    }
}
