package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.level.block.Block;

import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.quark.content.automation.block.MetalButtonBlock;
import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.Hint;

@ZetaLoadModule(category = "aether")
public class AQButtonsModule extends ZetaModule {

    @Config(flag = "zanite_button")
    public static boolean enableZanite = true;

    @Hint("zanite_button")
    public static Block zanite_button;

    @LoadEvent
    public final void register(ZRegister event) {
        zanite_button = new MetalButtonBlock("zanite_button", this, 100).setCondition(() -> enableZanite);
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey(),
                zanite_button,
                AetherBlocks.ALTAR, this);
    }

}