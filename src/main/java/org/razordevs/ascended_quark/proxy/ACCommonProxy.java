package org.razordevs.ascended_quark.proxy;

import net.minecraft.world.level.block.Blocks;
import org.razordevs.ascended_quark.AQGeneralConfig;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.zeta.module.ZetaCategory;
import org.violetmoon.zetaimplforge.module.ModFileScanDataModuleFinder;

import java.util.List;

public class ACCommonProxy {

    public void start() {
        // MODULES
        AscendedQuark.ZETA.loadModules(
                List.of(
                        new ZetaCategory("aether", Blocks.GLOWSTONE.asItem(), AscendedQuark.MODID)
                ),
                new ModFileScanDataModuleFinder(AscendedQuark.MODID), //forge only
                AQGeneralConfig.INSTANCE
        );

        AscendedQuark.ZETA.loadBus
                .subscribe(this);
    }
}
