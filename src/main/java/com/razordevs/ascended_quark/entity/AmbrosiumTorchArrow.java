package com.razordevs.ascended_quark.entity;

import com.aetherteam.aether.block.AetherBlocks;
import com.razordevs.ascended_quark.items.AQItems;
import com.razordevs.ascended_quark.particle.AQParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import vazkii.quark.content.tools.module.TorchArrowModule;
import vazkii.quark.integration.claim.IClaimIntegration;

public class AmbrosiumTorchArrow extends AbstractArrow {

    public AmbrosiumTorchArrow(EntityType<AmbrosiumTorchArrow> type, Level level) {
        super(type, level);
    }

    public AmbrosiumTorchArrow(Level level, double x, double y, double z) {
        super(AQEntityTypes.AMBROSIUM_TORCH_ARROW.get(), x, y, z, level);
    }

    public AmbrosiumTorchArrow(Level level, LivingEntity shooter) {
        super(AQEntityTypes.AMBROSIUM_TORCH_ARROW.get(), shooter, level);
    }

    @Override
    public void tick() {
        super.tick();
        spawnFlyingParticles();
    }


    // Method responsible for Ambrosium Torch placing on block hit.
    @Override
    protected void onHitBlock(BlockHitResult result) {
        if(!level.isClientSide) {
            BlockPos pos = result.getBlockPos();
            Direction direction = result.getDirection();
            BlockPos finalPos = pos.relative(direction);
            BlockState state = level.getBlockState(finalPos);

            if((state.isAir() || state.getMaterial().isReplaceable()) && direction != Direction.DOWN) {

                if(this.getOwner() instanceof Player p && !IClaimIntegration.INSTANCE.canPlace(p, finalPos))
                    return;

                BlockState setState;
                if(direction == Direction.UP)
                    setState = AetherBlocks.AMBROSIUM_TORCH.get().defaultBlockState();
                    else setState = AetherBlocks.AMBROSIUM_WALL_TORCH.get().defaultBlockState().setValue(WallTorchBlock.FACING, direction);

                if(setState.canSurvive(level, finalPos)) {
                    level.setBlock(finalPos, setState, 2);
                    playSound(setState.getSoundType().getPlaceSound());
                    discard();
                    return;
                }
            }
        }
        super.onHitBlock(result);
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        if(result.getEntity() instanceof LivingEntity entity)
            entity.heal(10.0F);
        super.onHitEntity(result);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(TorchArrowModule.extinguishOnMiss ? Items.ARROW : AQItems.AMBROSIUM_TORCH_ARROW.get());
    }

    private void spawnFlyingParticles() {
        if(!inGround && level.isClientSide && tickCount > 2) {
            Vec3 motion = getDeltaMovement();
            double rs = 0.03;
            double ms = 0.08;
            double sprd = 0.1;

            int parts = 6;
            for(int i = 0; i < parts; i++) {
                double px = getX() - motion.x * ((float) i / parts) + (Math.random() - 0.5) * sprd;
                double py = getY() - motion.y * ((float) i / parts) + (Math.random() - 0.5) * sprd;
                double pz = getZ() - motion.z * ((float) i / parts) + (Math.random() - 0.5) * sprd;

                double mx = (Math.random() - 0.5) * rs - motion.x * ms;
                double my = (Math.random() - 0.5) * rs - motion.y * ms;
                double mz = (Math.random() - 0.5) * rs - motion.z * ms;
                level.addParticle(AQParticles.AMBROSIUM_SHARD_PARTICLE.get(), px, py, pz, mx, my, mz);
            }
        }
    }
}
