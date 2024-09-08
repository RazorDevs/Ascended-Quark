package org.razordevs.ascended_quark.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.function.Supplier;

public class AQHedgeBlock extends HedgeBlock {

    private final Supplier<Block> leaf;
    public AQHedgeBlock(String regname, @Nullable ZetaModule module, Supplier<Block> leaf) {
        super(regname, module, Blocks.OAK_FENCE, Blocks.OAK_LEAVES);
        this.leaf = leaf;
    }

    @Override
    public BlockState getLeaf() {
        return this.leaf.get().defaultBlockState();
    }
}

