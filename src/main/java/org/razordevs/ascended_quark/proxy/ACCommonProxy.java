package org.razordevs.ascended_quark.proxy;

import net.minecraft.world.level.block.Blocks;
import org.razordevs.ascended_quark.AQGeneralConfig;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.zeta.module.ZetaCategory;
import org.violetmoon.zetaimplforge.module.ModFileScanDataModuleFinder;
import teamrazor.deepaether.init.DABlocks;

import java.util.List;

public class ACCommonProxy {

    //TODO: Deep Aether Compat

    public void start() {
        // MODULES
        AscendedQuark.ZETA.loadModules(
                List.of(
                        new ZetaCategory("aether", Blocks.GLOWSTONE.asItem(), AscendedQuark.MODID),
                        new ZetaCategory("deep_aether", DABlocks.ROSEROOT_LOG.get().asItem(), AscendedQuark.MODID)
                ),
                new ModFileScanDataModuleFinder(AscendedQuark.MODID), //forge only
                AQGeneralConfig.INSTANCE
        );

        AscendedQuark.ZETA.loadBus
                .subscribe(this);
    }
}
