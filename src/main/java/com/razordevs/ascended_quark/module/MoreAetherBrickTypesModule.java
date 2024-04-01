package com.razordevs.ascended_quark.module;

import com.razordevs.ascended_quark.config.LoadModuleButWithoutCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.module.config.Config;

@LoadModuleButWithoutCategory()
public class MoreAetherBrickTypesModule extends QuarkModule {
    @Config(flag = "quicksoil_bricks", name = "Enable Quicksoil Bricks") public boolean quicksoil_bricks = true;
    @Config(flag = "aether_dirt_bricks", name = "Enable Aether Dirt Bricks") public boolean aether_dirt_bricks = true;
    @Config(flag = "icestone_bricks.png", name = "Enable  Polished Icestone and Icestone Bricks") public boolean icestone_bricks = true;

}
