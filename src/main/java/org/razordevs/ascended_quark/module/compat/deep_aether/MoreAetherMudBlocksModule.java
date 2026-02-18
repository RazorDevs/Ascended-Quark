package org.razordevs.ascended_quark.module.compat.deep_aether;

import com.aetherteam.aether.item.AetherCreativeTabs;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AetherMudBrickLatticeBlock;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.zeta.block.ZetaPillarBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "deep_aether")
public class MoreAetherMudBlocksModule extends ZetaModule {
    @LoadEvent
    public final void register(ZRegister event) {
        if(ModList.get().isLoaded(AscendedQuark.DEEP_AETHER)) {
            BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS);
            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new ZetaPillarBlock("aether_mud_pillar", this, props), DABlocks.AETHER_MUD_BRICKS_STAIRS, this);
            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AetherMudBrickLatticeBlock(this, props), DABlocks.AETHER_MUD_BRICKS_STAIRS, this);
        }
    }
}
