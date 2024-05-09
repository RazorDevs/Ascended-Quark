package org.razordevs.ascended_quark.module;

import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class AmbrosiumLampModule extends ZetaModule {
    @Config(flag = "ambrosium_lamp")
    public static boolean enableAmbrosiumLamp = true;
}
