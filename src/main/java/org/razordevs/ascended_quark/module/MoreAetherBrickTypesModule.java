package org.razordevs.ascended_quark.module;

import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class MoreAetherBrickTypesModule extends ZetaModule {
    @Config(flag = "quicksoil_bricks", name = "Enable Quicksoil Bricks") public boolean quicksoil_bricks = true;
    @Config(flag = "aether_dirt_bricks", name = "Enable Aether Dirt Bricks") public boolean aether_dirt_bricks = true;
    @Config(flag = "icestone_bricks", name = "Enable  Polished Icestone and Icestone Bricks") public boolean icestone_bricks = true;
}
