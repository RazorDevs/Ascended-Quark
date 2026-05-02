package org.razordevs.ascended_quark.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.razordevs.ascended_quark.blocks.AQStoolBlock;
import org.violetmoon.quark.mixin.mixins.accessor.AccessorPistonMovingBlockEntity;

import javax.annotation.Nonnull;
import java.util.List;

public class AQStool extends Entity {

    public AQStool(EntityType<? extends AQStool> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> passengers = getPassengers();
        boolean dead = passengers.isEmpty();

        BlockPos pos = blockPosition();
        BlockState state = level().getBlockState(pos);

        if(!dead) {
            if(!(state.getBlock() instanceof AQStoolBlock)) {
                PistonMovingBlockEntity piston = null;
                boolean didOffset = false;

                BlockEntity tile = level().getBlockEntity(pos);
                if(tile instanceof PistonMovingBlockEntity pistonBE && pistonBE.getMovedState().getBlock() instanceof AQStoolBlock)
                    piston = pistonBE;
                else
                    for(Direction d : Direction.values()) {
                        BlockPos offPos = pos.relative(d);
                        tile = level().getBlockEntity(offPos);

                        if(tile instanceof PistonMovingBlockEntity pistonBE && pistonBE.getMovedState().getBlock() instanceof AQStoolBlock) {
                            piston = pistonBE;
                            break;
                        }
                    }

                if(piston != null) {
                    boolean lmfao = noPhysics;
                    noPhysics = false;
                    Direction dir = piston.getMovementDirection();
                    AccessorPistonMovingBlockEntity.getMoveEntityByPiston(dir, this, piston.getProgress(0.98f), dir);
                    noPhysics = lmfao;

                    didOffset = true;
                }

                dead = !didOffset;
            }
        }

        if(dead && !level().isClientSide) {
            removeAfterChangingDimensions();

            if(state.getBlock() instanceof AQStoolBlock)
                level().setBlockAndUpdate(pos, state.setValue(AQStoolBlock.SAT_IN, false));
        }
    }

    @Override
    public Vec3 getPassengerRidingPosition(Entity entity) {
        return super.getPassengerRidingPosition(entity).subtract(0, 0.5, 0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    protected void readAdditionalSaveData(@Nonnull CompoundTag compound) {
        // NO-OP
    }

    @Override
    protected void addAdditionalSaveData(@Nonnull CompoundTag compound) {
        // NO-OP
    }
}