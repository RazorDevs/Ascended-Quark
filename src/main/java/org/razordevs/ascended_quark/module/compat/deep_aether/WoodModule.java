package org.razordevs.ascended_quark.module.compat.deep_aether;

import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import teamrazor.deepaether.init.DABlocks;

//TODO : IMPLEMENT DEEP AETHER COMPAT
@ZetaLoadModule(category = "deep_aether", antiOverlap = {"everycomp"})
public class WoodModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        // ROSEROOT
        RegistryUtil.registerWoodsetExtension("roseroot", this, DABlocks.ROSEROOT_SLAB, DABlocks.ROSEROOT_PLANKS, DABlocks.ROSEROOT_FENCE, DABlocks.ROSEROOT_LOG, DABlocks.ROSEROOT_LEAVES);
        RegistryUtil.createLeafCarpet("flowering_roseroot_leaf_carpet", this, DABlocks.FLOWERING_ROSEROOT_LEAVES);
        RegistryUtil.createLeafCarpet("blue_roseroot_leaf_carpet", this, DABlocks.BLUE_ROSEROOT_LEAVES);
        RegistryUtil.createLeafCarpet("flowering_blue_roseroot_leaf_carpet", this, DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES);

        RegistryUtil.createHedge("flowering_roseroot_hedge", this, DABlocks.FLOWERING_ROSEROOT_LEAVES, DABlocks.ROSEROOT_FENCE);
        RegistryUtil.createHedge("blue_roseroot_hedge", this, DABlocks.BLUE_ROSEROOT_LEAVES, DABlocks.ROSEROOT_FENCE);
        RegistryUtil.createHedge("flowering_blue_roseroot_hedge", this, DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES, DABlocks.ROSEROOT_FENCE);

        // YAGROOT
        RegistryUtil.registerWoodsetExtension("yagroot", this, DABlocks.YAGROOT_SLAB, DABlocks.YAGROOT_PLANKS, DABlocks.YAGROOT_FENCE, DABlocks.YAGROOT_LOG, DABlocks.YAGROOT_LEAVES);

        // CRUDEROOT
        RegistryUtil.registerWoodsetExtension("cruderoot", this, DABlocks.CRUDEROOT_SLAB, DABlocks.CRUDEROOT_PLANKS, DABlocks.CRUDEROOT_FENCE, DABlocks.CRUDEROOT_LOG, DABlocks.CRUDEROOT_LEAVES);

        // CONBERRY
        RegistryUtil.registerWoodsetExtension("conberry", this, DABlocks.CONBERRY_SLAB, DABlocks.CONBERRY_PLANKS, DABlocks.CONBERRY_FENCE, DABlocks.CONBERRY_LOG, DABlocks.CONBERRY_LEAVES);

        // SUNROOT
        RegistryUtil.registerWoodsetExtension("sunroot", this, DABlocks.SUNROOT_SLAB, DABlocks.SUNROOT_PLANKS, DABlocks.SUNROOT_FENCE, DABlocks.SUNROOT_LOG, DABlocks.SUNROOT_LEAVES);
    }
}
