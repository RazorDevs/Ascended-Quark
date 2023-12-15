package com.razordevs.ascended_quark.module;

import com.google.common.collect.Lists;
import com.razordevs.ascended_quark.AetherModuleCategory;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.module.config.Config;

import java.util.List;

@LoadModule(category = AetherModuleCategory.THE_AETHER)
public class TestModule extends QuarkModule {

    @Config(flag = "test") public static boolean test = false;

    @Override
    public void register() {

    }
}
