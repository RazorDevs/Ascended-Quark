package org.razordevs.ascended_quark.module.compat.deep_aether;

import org.razordevs.ascended_quark.RegistryUtil;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import teamrazor.deepaether.init.DABlocks;

//TODO : IMPLEMENT DEEP AETHER COMBAT
//@ZetaLoadModule(category = "deep_aether", antiOverlap = {"everycomp"})
public class WoodModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        RegistryUtil.registerWoodsetExtension("roseroot", this, DABlocks.ROSEROOT_SLAB, DABlocks.ROSEROOT_PLANKS, DABlocks.ROSEROOT_FENCE, DABlocks.ROSEROOT_LOG, DABlocks.ROSEROOT_LEAVES);
        RegistryUtil.createLeafCarpet("flowering_roseroot_leaf_carpet", this, DABlocks.FLOWERING_ROSEROOT_LEAVES);
        RegistryUtil.createLeafCarpet("blue_roseroot_leaf_carpet", this, DABlocks.BLUE_ROSEROOT_LEAVES);
        RegistryUtil.createLeafCarpet("flowering_blue_roseroot_leaf_carpet", this, DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES);

        RegistryUtil.createHedge("flowering_roseroot_hedge", this, DABlocks.FLOWERING_ROSEROOT_LEAVES, DABlocks.ROSEROOT_FENCE);
        RegistryUtil.createHedge("blue_roseroot_hedge", this, DABlocks.BLUE_ROSEROOT_LEAVES, DABlocks.ROSEROOT_FENCE);
        RegistryUtil.createHedge("flowering_blue_hedge", this, DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES, DABlocks.ROSEROOT_FENCE);
    }
}
