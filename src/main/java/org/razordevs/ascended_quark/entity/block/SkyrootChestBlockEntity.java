package org.razordevs.ascended_quark.entity.block;

import org.razordevs.ascended_quark.blocks.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SkyrootChestBlockEntity extends ChestBlockEntity {
    protected SkyrootChestBlockEntity(BlockEntityType<?> p_155327_, BlockPos p_155328_, BlockState p_155329_) {
        super(p_155327_, p_155328_, p_155329_);
    }
    public SkyrootChestBlockEntity(BlockPos p_155331_, BlockState p_155332_) {
        this(AQBlockEntityTypes.SKYROOT_CHEST.get(), p_155331_, p_155332_);
    }

    public SkyrootChestBlockEntity() {
        this(AQBlockEntityTypes.SKYROOT_CHEST.get(), BlockPos.ZERO, AQBlocks.SKYROOT_CHEST.get().defaultBlockState());
    }
}
