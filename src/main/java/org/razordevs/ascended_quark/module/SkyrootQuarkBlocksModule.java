package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.particle.AetherParticleTypes;
import org.razordevs.ascended_quark.RegistryUtil;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether", description = "Enables skyroot woodset blocks for quark blocks, such as skyroot post. Disable if another mod already adds compat blocks. ", antiOverlap = "everycomp")
public class SkyrootQuarkBlocksModule extends ZetaModule {


    @LoadEvent
    public void register(ZRegister register) {
        RegistryUtil.registerWoodsetExtension("skyroot", this, AetherBlocks.SKYROOT_SLAB);
        RegistryUtil.createLeafCarpetParticle("golden_oak_leaf_carpet", this, AetherParticleTypes.GOLDEN_OAK_LEAVES);
        RegistryUtil.createLeafCarpetParticle("crystal_leaf_carpet", this, AetherParticleTypes.CRYSTAL_LEAVES);
        RegistryUtil.createLeafCarpetParticle("crystal_fruit_leaf_carpet", this, AetherParticleTypes.CRYSTAL_LEAVES);
        RegistryUtil.createLeafCarpetParticle("holiday_leaf_carpet", this, AetherParticleTypes.HOLIDAY_LEAVES);
        RegistryUtil.createLeafCarpetParticle("decorated_holiday_leaf_carpet", this, AetherParticleTypes.HOLIDAY_LEAVES);
        RegistryUtil.createHedge("golden_skyroot_hedge", this, AetherBlocks.GOLDEN_OAK_LEAVES);
        RegistryUtil.createHedge("crystal_skyroot_hedge", this, AetherBlocks.CRYSTAL_LEAVES);
        RegistryUtil.createHedge("crystal_fruit_skyroot_hedge", this, AetherBlocks.CRYSTAL_FRUIT_LEAVES);
        RegistryUtil.createHedge("holiday_skyroot_hedge", this, AetherBlocks.HOLIDAY_LEAVES);
        RegistryUtil.createHedge("decorated_holiday_skyroot_hedge", this, AetherBlocks.DECORATED_HOLIDAY_LEAVES);

    }


}
