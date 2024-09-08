package org.razordevs.ascended_quark.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.module.SkyrootQuarkBlocksModule;
import org.violetmoon.quark.content.building.block.be.VariantChestBlockEntity;
import org.violetmoon.quark.content.building.module.VariantChestsModule;

public class AQVariantTrappedChestBlockEntity extends AQVariantChestBlockEntity {
    public AQVariantTrappedChestBlockEntity(BlockPos pos, BlockState state) {
        super(SkyrootQuarkBlocksModule.aqTrappedChestTEType, pos, state);
    }

    protected void signalOpenCount(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, int prevOpenCount, int openCount) {
        super.signalOpenCount(world, pos, state, prevOpenCount, openCount);
        if (prevOpenCount != openCount) {
            Block block = state.getBlock();
            world.updateNeighborsAt(pos, block);
            world.updateNeighborsAt(pos.below(), block);
        }
    }
}
