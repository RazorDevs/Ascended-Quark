package org.razordevs.ascended_quark.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.violetmoon.zeta.util.ItemNBTHelper;

import javax.annotation.Nonnull;

public class AQSlimeInABucketItem extends AQEntityInABucketItem {


    public AQSlimeInABucketItem(EntityType<Slime> bucketEntity, boolean isSkyroot) {
        super(bucketEntity, isSkyroot);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getClickedFace();
        Level worldIn = context.getLevel();
        Player playerIn = context.getPlayer();
        InteractionHand hand = context.getHand();

        double x = pos.getX() + 0.5 + facing.getStepX();
        double y = pos.getY() + 0.5 + facing.getStepY();
        double z = pos.getZ() + 0.5 + facing.getStepZ();

        if(!worldIn.isClientSide) {
            Slime slime = new Slime(this.getBucketEntity(), worldIn);

            CompoundTag data = ItemNBTHelper.getCompound(playerIn.getItemInHand(hand), TAG_ENTITY_DATA, true);
            if(data != null)
                slime.load(data);
            else {
                slime.getAttribute(Attributes.MAX_HEALTH).setBaseValue(1.0);
                slime.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3);
                slime.setHealth(slime.getMaxHealth());
            }

            slime.setPos(x, y, z);

            worldIn.gameEvent(playerIn, GameEvent.ENTITY_PLACE, slime.position());
            worldIn.addFreshEntity(slime);
            playerIn.swing(hand);
        }

        worldIn.playSound(playerIn, pos, SoundEvents.BUCKET_EMPTY, SoundSource.NEUTRAL, 1.0F, 1.0F);

        if(!playerIn.getAbilities().instabuild)
            playerIn.setItemInHand(hand, new ItemStack(Items.BUCKET));

        return InteractionResult.SUCCESS;
    }
}
