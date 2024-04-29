package org.razordevs.ascended_quark.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.razordevs.ascended_quark.entity.block.AQBlockEntityTypes;
import org.razordevs.ascended_quark.entity.block.HolystoneFurnaceBlockEntity;
import org.razordevs.ascended_quark.particle.AQParticles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AQFurnaceBlock extends AbstractFurnaceBlock {
    public AQFurnaceBlock(Properties properties) {
        super(properties);
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new HolystoneFurnaceBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, AQBlockEntityTypes.HOLYSTONE_FURNACE.get());
    }

    /**
     * Interface for handling interaction with blocks that implement AbstractFurnaceBlock. Called in onBlockActivated
     * inside AbstractFurnaceBlock.
     */
    @Override
    protected void openContainer(Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (blockEntity instanceof HolystoneFurnaceBlockEntity) {
            player.openMenu((MenuProvider)blockEntity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }

    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(LIT)) {
            double d0 = (double)blockPos.getX() + 0.5D;
            double d1 = (double)blockPos.getY();
            double d2 = (double)blockPos.getZ() + 0.5D;
            if (randomSource.nextDouble() < 0.1D) {
                level.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = blockState.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = randomSource.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = randomSource.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            level.addParticle(AQParticles.AMBROSIUM_SHARD_PARTICLE.get(), d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}
