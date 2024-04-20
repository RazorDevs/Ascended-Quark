package org.razordevs.ascended_quark.proxy;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.item.AetherItems;
import net.minecraftforge.common.MinecraftForge;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.base.config.QuarkGeneralConfig;
import org.violetmoon.zeta.module.ZetaCategory;
import org.violetmoon.zetaimplforge.module.ModFileScanDataModuleFinder;

import java.util.List;

public class ACCommonProxy {

    public void start() {
        // MODULES
        AscendedQuark.ZETA.loadModules(
                List.of(
                        new ZetaCategory("the_aether", AetherItems.AETHER_PORTAL_FRAME.get(), Aether.MODID)
                ),
                new ModFileScanDataModuleFinder(AscendedQuark.MODID), //forge only
                QuarkGeneralConfig.INSTANCE
        );
    }
}
