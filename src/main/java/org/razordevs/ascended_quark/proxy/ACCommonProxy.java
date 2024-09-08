package org.razordevs.ascended_quark.proxy;

import com.aetherteam.aether.item.AetherItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.razordevs.ascended_quark.AQGeneralConfig;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.module.compat.deep_aether.WoodModule;
import org.violetmoon.zeta.module.ZetaCategory;
import org.violetmoon.zetaimplforge.module.ModFileScanDataModuleFinder;
import teamrazor.deepaether.init.DABlocks;

import java.util.List;
import java.util.function.Supplier;

public class ACCommonProxy {

    public void start() {
        // MODULES
        AscendedQuark.ZETA.loadModules(
                List.of(
                        new ZetaCategory("aether", () -> new ItemStack(AetherItems.AETHER_PORTAL_FRAME.get()), AscendedQuark.MODID),
                        new ZetaCategory("deep_aether", () -> new ItemStack(DABlocks.ROSEROOT_LOG.get()), AscendedQuark.DEEP_AETHER)
                ),
                new ModFileScanDataModuleFinder(AscendedQuark.MODID), //forge only
                AQGeneralConfig.INSTANCE
        );

        AscendedQuark.ZETA.loadBus
                .subscribe(this);
    }
}
