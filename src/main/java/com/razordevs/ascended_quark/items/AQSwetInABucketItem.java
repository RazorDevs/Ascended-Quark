package com.razordevs.ascended_quark.items;

import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.phys.Vec3;
import vazkii.arl.util.ItemNBTHelper;

import javax.annotation.Nonnull;

public class AQSwetInABucketItem extends Item {
    public static final String TAG_ENTITY_DATA = "swet_nbt";
    EntityType bucketEntity;

    public AQSwetInABucketItem(EntityType bucketEntity) {
        super(new Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).craftRemainder(AetherItems.SKYROOT_BUCKET.get()));
        this.bucketEntity = bucketEntity;
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
            Swet swet = new Swet(bucketEntity, worldIn);

            CompoundTag data = ItemNBTHelper.getCompound(playerIn.getItemInHand(hand), TAG_ENTITY_DATA, true);
            if(data != null)
                swet.load(data);
            else {
                swet.getAttribute(Attributes.MAX_HEALTH).setBaseValue(1.0);
                swet.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3);
                swet.setHealth(swet.getMaxHealth());
            }

            swet.setPos(x, y, z);

            worldIn.gameEvent(playerIn, GameEvent.ENTITY_PLACE, swet.position());
            worldIn.addFreshEntity(swet);
            playerIn.swing(hand);
        }

        worldIn.playSound(playerIn, pos, SoundEvents.BUCKET_EMPTY, SoundSource.NEUTRAL, 1.0F, 1.0F);

        if(!playerIn.getAbilities().instabuild)
            playerIn.setItemInHand(hand, new ItemStack(Items.BUCKET));

        return InteractionResult.SUCCESS;
    }

    @Nonnull
    @Override
    public Component getName(@Nonnull ItemStack stack) {
        if(stack.hasTag()) {
            CompoundTag cmp = ItemNBTHelper.getCompound(stack, TAG_ENTITY_DATA, false);
            if(cmp != null && cmp.contains("CustomName")) {
                Component custom = Component.Serializer.fromJson(cmp.getString("CustomName"));
                return Component.translatable("item.quark.slime_in_a_bucket.named", custom);
            }
        }

        return super.getName(stack);
    }
}