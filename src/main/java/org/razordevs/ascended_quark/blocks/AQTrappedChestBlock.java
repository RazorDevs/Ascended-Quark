package org.razordevs.ascended_quark.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.entity.block.AQBlockEntityTypes;
import org.razordevs.ascended_quark.entity.block.SkyrootTrappedChestBlockEntity;


public class AQTrappedChestBlock extends AQChestBlock {
    public AQTrappedChestBlock(BlockBehaviour.Properties p_57573_) {
        super(p_57573_, AQBlockEntityTypes.SKYROOT_TRAPPED_CHEST::get);
    }

    public BlockEntity newBlockEntity(BlockPos p_154834_, BlockState p_154835_) {
        return new SkyrootTrappedChestBlockEntity(p_154834_, p_154835_);
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    public boolean isSignalSource(BlockState p_57587_) {
        return true;
    }

    public int getSignal(BlockState p_57577_, BlockGetter p_57578_, @NotNull BlockPos p_57579_, @NotNull Direction p_57580_) {
        return Mth.clamp(ChestBlockEntity.getOpenCount(p_57578_, p_57579_), 0, 15);
    }

    public int getDirectSignal(@NotNull BlockState p_57582_, @NotNull BlockGetter p_57583_, @NotNull BlockPos p_57584_, @NotNull Direction p_57585_) {
        return p_57585_ == Direction.UP ? p_57582_.getSignal(p_57583_, p_57584_, p_57585_) : 0;
    }
}