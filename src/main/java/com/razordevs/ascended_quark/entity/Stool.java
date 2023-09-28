package com.razordevs.ascended_quark.entity;

import com.razordevs.ascended_quark.blocks.AetherStoolBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.List;
public class Stool extends Entity {

    public Stool(EntityType<? extends Stool> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> passengers = getPassengers();
        boolean dead = passengers.isEmpty();

        BlockPos pos = blockPosition();
        BlockState state = level.getBlockState(pos);

        if(!dead) {
            if(!(state.getBlock() instanceof AetherStoolBlock)) {
                PistonMovingBlockEntity piston = null;
                boolean didOffset = false;

                BlockEntity tile = level.getBlockEntity(pos);
                if(tile instanceof PistonMovingBlockEntity pistonBE && pistonBE.getMovedState().getBlock() instanceof AetherStoolBlock)
                    piston = pistonBE;
                else for(Direction d : Direction.values()) {
                    BlockPos offPos = pos.relative(d);
                    tile = level.getBlockEntity(offPos);

                    if(tile instanceof PistonMovingBlockEntity pistonBE && pistonBE.getMovedState().getBlock() instanceof AetherStoolBlock) {
                        piston = pistonBE;
                        break;
                    }
                }

                if(piston != null) {
                    Direction dir = piston.getMovementDirection();
                    move(MoverType.PISTON, new Vec3((float) dir.getStepX() * 0.33, (float) dir.getStepY() * 0.33, (float) dir.getStepZ() * 0.33));

                    didOffset = true;
                }

                dead = !didOffset;
            }
        }

        if(dead && !level.isClientSide) {
            removeAfterChangingDimensions();

            if(state.getBlock() instanceof AetherStoolBlock)
                level.setBlockAndUpdate(pos, state.setValue(AetherStoolBlock.SAT_IN, false));
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return -0.3;
    }

    @Override
    protected void defineSynchedData() {
        // NO-OP
    }

    @Override
    protected void readAdditionalSaveData(@Nonnull CompoundTag compound) {
        // NO-OP
    }

    @Override
    protected void addAdditionalSaveData(@Nonnull CompoundTag compound) {
        // NO-OP
    }

    @Nonnull
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}