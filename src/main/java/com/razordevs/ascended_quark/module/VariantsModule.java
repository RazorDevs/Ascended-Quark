package com.razordevs.ascended_quark.module;

import com.razordevs.ascended_quark.LoadModuleButWithoutCategory;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.module.config.Config;
@LoadModuleButWithoutCategory()
public class VariantsModule extends QuarkModule {

    @Config(flag = "test") public static boolean test = false;
    @Override
    public void register() {

    }
}
