package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import org.razordevs.ascended_quark.blocks.AQRodBlock;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.Hint;

@ZetaLoadModule(category = "aether")
public class AQRodModule extends ZetaModule {

    public static TagKey<Block> ironRodImmuneTag;

    @Hint
    public static Block zanite_rod;

    @LoadEvent
    public final void register(ZRegister event) {
        zanite_rod = new AQRodBlock("zanite_rod", this);
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey(),
                zanite_rod,
                AetherBlocks.ALTAR, this);
    }
}