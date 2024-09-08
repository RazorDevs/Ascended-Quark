package org.razordevs.ascended_quark.module.compat.deep_aether;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.block.ZetaPillarBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

//TODO : IMPLEMENT DEEP AETHER COMBAT
@ZetaLoadModule(category = "deep_aether", antiOverlap = {"everycomp"})
public class WoodModule extends ZetaModule {
/*
    public static final RegistryObject<Block> HOLLOW_ROSEROOT_LOG = registerBlock("hollow_roseroot_log", () -> new AQHollowLogBlock(BlockBehaviour.Properties.copy(AetherBlocks.SKYROOT_LOG.get())));
    public static final RegistryObject<Block> ROSEROOT_LADDER = registerBlock("roseroot_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> ROSEROOT_POST = registerBlock("roseroot_post", () -> new AQWoodenPostBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_ROSEROOT_POST = registerBlock("stripped_roseroot_post", () -> new AQWoodenPostBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> ROSEROOT_VERTICAL_SLAB = registerBlock("roseroot_vertical_slab", ()-> new VerticalSlabBlock(AetherBlocks.SKYROOT_PLANKS, BlockBehaviour.Properties.copy(AetherBlocks.SKYROOT_PLANKS.get())));
    public static final RegistryObject<Block> VERTICAL_ROSEROOT_PLANKS = registerBlock("vertical_roseroot_planks", () -> new Block(BlockBehaviour.Properties.copy(AetherBlocks.SKYROOT_PLANKS.get())));
    public static final RegistryObject<Block> ROSEROOT_CHEST = registerBlock("roseroot_chest", () -> new AQChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST), AQBlockEntityTypes.SKYROOT_CHEST::get));
    public static final RegistryObject<Block> ROSEROOT_TRAPPED_CHEST = registerBlock("roseroot_trapped_chest", () -> new AQTrappedChestBlock(BlockBehaviour.Properties.copy(Blocks.TRAPPED_CHEST)));
    public static final RegistryObject<Block> ROSEROOT_HEDGE = registerBlock("roseroot_hedge", () -> new AQHedgeBlock(AetherBlocks.SKYROOT_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> DECORATED_HOLIDAY_ROSEROOT_HEDGE = registerBlock("decorated_holiday_roseroot_hedge", () -> new AQHedgeBlock(AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> ROSEROOT_LEAF_CARPET = registerBlock("roseroot_leaf_carpet", () -> new AQLeafCarpetBlock(AetherBlocks.SKYROOT_LEAVES.get()));
*/


    @LoadEvent
    public void register(ZRegister register) {

    }
}
