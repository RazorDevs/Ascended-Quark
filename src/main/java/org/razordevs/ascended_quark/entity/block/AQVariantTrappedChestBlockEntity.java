package org.razordevs.ascended_quark.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.razordevs.ascended_quark.module.SkyrootQuarkBlocksModule;

public class AQVariantTrappedChestBlockEntity extends AQVariantChestBlockEntity {
    public AQVariantTrappedChestBlockEntity(BlockPos pos, BlockState state) {
        super(SkyrootQuarkBlocksModule.aqTrappedChestTEType, pos, state);
    }

    protected void signalOpenCount(Level world, BlockPos pos, BlockState state, int prevOpenCount, int openCount) {
        super.signalOpenCount(world, pos, state, prevOpenCount, openCount);
        if (prevOpenCount != openCount) {
            Block block = state.getBlock();
            world.updateNeighborsAt(pos, block);
            world.updateNeighborsAt(pos.below(), block);
        }
    }
}
