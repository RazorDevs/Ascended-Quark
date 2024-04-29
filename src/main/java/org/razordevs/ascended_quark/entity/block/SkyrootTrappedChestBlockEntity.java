package org.razordevs.ascended_quark.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.razordevs.ascended_quark.blocks.AQBlocks;

public class SkyrootTrappedChestBlockEntity extends SkyrootChestBlockEntity {
    protected SkyrootTrappedChestBlockEntity(BlockEntityType<?> p_155327_, BlockPos p_155328_, BlockState p_155329_) {
        super(p_155327_, p_155328_, p_155329_);
    }
    public SkyrootTrappedChestBlockEntity(BlockPos p_155331_, BlockState p_155332_) {
        this(AQBlockEntityTypes.SKYROOT_TRAPPED_CHEST.get(), p_155331_, p_155332_);
    }

    public SkyrootTrappedChestBlockEntity() {
        this(AQBlockEntityTypes.SKYROOT_TRAPPED_CHEST.get(), BlockPos.ZERO, AQBlocks.SKYROOT_TRAPPED_CHEST.get().defaultBlockState());
    }

    @Override
    protected void signalOpenCount(Level p_155865_, BlockPos p_155866_, BlockState p_155867_, int p_155868_, int p_155869_) {
        super.signalOpenCount(p_155865_, p_155866_, p_155867_, p_155868_, p_155869_);
        if (p_155868_ != p_155869_) {
            Block block = p_155867_.getBlock();
            p_155865_.updateNeighborsAt(p_155866_, block);
            p_155865_.updateNeighborsAt(p_155866_.below(), block);
        }

    }
}
