package org.razordevs.ascended_quark;

import org.violetmoon.zeta.config.Config;

public class AQGeneralConfig {
    public static final AQGeneralConfig INSTANCE = new AQGeneralConfig();

    @Config(name = "Enable 'aq' Button")
    public static boolean enableAQButton = true;

    @Config(name = "'aq' Button on the Right")
    public static boolean aqButtonOnRight = false;

    @Config
    public static boolean disableAQMenuEffects = false;

    @Config(description = "Set to false to disable the popup message telling you that you can config ascended quark in the aq menu")
    public static boolean enableOnboarding = true;
}
