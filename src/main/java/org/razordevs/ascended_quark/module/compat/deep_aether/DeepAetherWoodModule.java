package org.razordevs.ascended_quark.module.compat.deep_aether;

import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.blocks.AQLeafCarpetBlock;
import org.razordevs.ascended_quark.util.WoodSetContext;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import teamrazor.deepaether.init.DABlocks;

@ZetaLoadModule(category = "deep_aether", antiOverlap = {"everycomp"})
public class DeepAetherWoodModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        if(ModList.get().isLoaded(AscendedQuark.DEEP_AETHER)) {
            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey(), new AQLeafCarpetBlock("flowering_roseroot_leaf_carpet", this),  DABlocks.FLOWERING_ROSEROOT_LEAVES, this);
            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey(), new AQLeafCarpetBlock("blue_roseroot_leaf_carpet", this),  DABlocks.BLUE_ROSEROOT_LEAVES, this);
            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey(), new AQLeafCarpetBlock("flowering_blue_roseroot_leaf_carpet", this),  DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES, this);

            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQHedgeBlock("flowering_roseroot_hedge", this),  DABlocks.ROSEROOT_FENCE, this);
            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQHedgeBlock("blue_roseroot_hedge", this),  DABlocks.ROSEROOT_FENCE, this);
            RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQHedgeBlock("flowering_blue_roseroot_hedge", this),  DABlocks.ROSEROOT_FENCE, this);

            RegistryUtil.registerWoodsetExtension("roseroot", this, new WoodSetContext(DABlocks.ROSEROOT_SLAB, DABlocks.ROSEROOT_PLANKS, DABlocks.ROSEROOT_FENCE, DABlocks.ROSEROOT_LOG, DABlocks.ROSEROOT_LEAVES));
            RegistryUtil.registerWoodsetExtension("yagroot", this, new WoodSetContext(DABlocks.YAGROOT_SLAB, DABlocks.YAGROOT_PLANKS, DABlocks.YAGROOT_FENCE, DABlocks.YAGROOT_LOG, DABlocks.YAGROOT_LEAVES));
            RegistryUtil.registerWoodsetExtension("cruderoot", this, new WoodSetContext(DABlocks.CRUDEROOT_SLAB, DABlocks.CRUDEROOT_PLANKS, DABlocks.CRUDEROOT_FENCE, DABlocks.CRUDEROOT_LOG, DABlocks.CRUDEROOT_LEAVES));
            RegistryUtil.registerWoodsetExtension("conberry", this, new WoodSetContext(DABlocks.CONBERRY_SLAB, DABlocks.CONBERRY_PLANKS, DABlocks.CONBERRY_FENCE, DABlocks.CONBERRY_LOG, DABlocks.CONBERRY_LEAVES));
            RegistryUtil.registerWoodsetExtension("sunroot", this, new WoodSetContext(DABlocks.SUNROOT_SLAB, DABlocks.SUNROOT_PLANKS, DABlocks.SUNROOT_FENCE, DABlocks.SUNROOT_LOG, DABlocks.SUNROOT_LEAVES));
        }
    }
}
