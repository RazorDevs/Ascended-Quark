package com.razordevs.ascended_quark.events;


import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.item.AetherItems;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQStoolBlock;
import com.razordevs.ascended_quark.items.AQItems;
import com.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.arl.util.ItemNBTHelper;
import vazkii.quark.content.tools.item.SlimeInABucketItem;

@Mod.EventBusSubscriber(modid = AscendedQuarkMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AQEvents {

    @SubscribeEvent
    public void itemUsed(PlayerInteractEvent.RightClickBlock event) {
        if(event.getEntity().isShiftKeyDown() && event.getItemStack().getItem() instanceof BlockItem && event.getFace() == Direction.UP) {
            BlockState state = event.getLevel().getBlockState(event.getPos());
            if(state.getBlock() instanceof AQStoolBlock stool)
                stool.blockClicked(event.getLevel(), event.getPos());
        }
    }


    @SubscribeEvent
    public void entityInteract(PlayerInteractEvent.EntityInteract event) {
        if(event.getTarget() != null) {

            Item bucket = Items.BUCKET;
            EntityType slime;

            if(event.getTarget().getType() == EntityType.SLIME && ((Slime) event.getTarget()).getSize() == 1) {
                slime = EntityType.SLIME;
            }else
            if(event.getTarget().getType() == AetherEntityTypes.BLUE_SWET.get() && ((Swet) event.getTarget()).getSize() == 1) {
                slime = AetherEntityTypes.BLUE_SWET.get();
            }else
            if(event.getTarget().getType() == AetherEntityTypes.GOLDEN_SWET.get() && ((Swet) event.getTarget()).getSize() == 1) {
                slime = AetherEntityTypes.GOLDEN_SWET.get();
            }
            else {
                return;
            }

            if(event.getTarget().isAlive()) {
                Player player = event.getEntity();
                InteractionHand hand = InteractionHand.MAIN_HAND;
                ItemStack stack = player.getMainHandItem();

                if (stack.getItem() == Items.BUCKET) {
                    if (slime == EntityType.SLIME)
                        return;
                    else return;
                } else if (stack.getItem() == AetherItems.SKYROOT_BUCKET.get()) {
                    if (slime == EntityType.SLIME) {
                        bucket = AQItems.SLIME_IN_A_SKYROOT_BUCKET_ITEM.get();
                    }
                } else if (player.getOffhandItem().getItem() == Items.BUCKET) {
                    hand = InteractionHand.OFF_HAND;
                    if (slime == EntityType.SLIME)
                        return;
                    else return;
                } else if (player.getOffhandItem().getItem() == AetherItems.SKYROOT_BUCKET.get()) {
                    hand = InteractionHand.OFF_HAND;
                    if (slime == EntityType.SLIME)
                        bucket = AQItems.SLIME_IN_A_SKYROOT_BUCKET_ITEM.get();
                } else return;

                if (stack.getItem() == Items.BUCKET) {
                    if (slime == AetherEntityTypes.BLUE_SWET.get())
                        bucket = AQItems.SWET_IN_A_BUCKET_ITEM.get();

                } else if (stack.getItem() == AetherItems.SKYROOT_BUCKET.get()) {
                    if (slime == AetherEntityTypes.BLUE_SWET.get())
                        bucket = AQItems.SWET_IN_A_SKYROOT_BUCKET_ITEM.get();

                } else if (player.getOffhandItem().getItem() == Items.BUCKET) {
                    hand = InteractionHand.OFF_HAND;
                    if (slime == AetherEntityTypes.BLUE_SWET.get())
                        bucket = AQItems.SWET_IN_A_BUCKET_ITEM.get();
                } else if (player.getOffhandItem().getItem() == AetherItems.SKYROOT_BUCKET.get()) {
                    hand = InteractionHand.OFF_HAND;
                    if (slime == AetherEntityTypes.BLUE_SWET.get())
                        bucket = AQItems.SWET_IN_A_SKYROOT_BUCKET_ITEM.get();
                } else return;

                if (stack.getItem() == Items.BUCKET) {
                    if (slime == AetherEntityTypes.GOLDEN_SWET.get())
                        bucket = AQItems.GOLDEN_SWET_IN_A_BUCKET_ITEM.get();

                } else if (stack.getItem() == AetherItems.SKYROOT_BUCKET.get()) {
                    if (slime == AetherEntityTypes.GOLDEN_SWET.get())
                        bucket = AQItems.GOLDEN_SWET_IN_A_SKYROOT_BUCKET_ITEM.get();

                } else if (player.getOffhandItem().getItem() == Items.BUCKET) {
                    hand = InteractionHand.OFF_HAND;
                    if (slime == AetherEntityTypes.GOLDEN_SWET.get())
                        bucket = AQItems.GOLDEN_SWET_IN_A_BUCKET_ITEM.get();
                } else if (player.getOffhandItem().getItem() == AetherItems.SKYROOT_BUCKET.get()) {
                    hand = InteractionHand.OFF_HAND;
                    if (slime == AetherEntityTypes.GOLDEN_SWET.get())
                        bucket = AQItems.GOLDEN_SWET_IN_A_SKYROOT_BUCKET_ITEM.get();
                } else return;

                if (!event.getLevel().isClientSide) {
                    ItemStack outStack = new ItemStack(bucket);
                    CompoundTag cmp = event.getTarget().serializeNBT();
                    ItemNBTHelper.setCompound(outStack, AQSlimeInABucketItem.TAG_ENTITY_DATA, cmp);

                    if (stack.getCount() == 1)
                        player.setItemInHand(hand, outStack);
                    else {
                        stack.shrink(1);
                        if (stack.getCount() == 0)
                            player.setItemInHand(hand, outStack);
                        else if (!player.getInventory().add(outStack))
                            player.drop(outStack, false);
                    }

                    event.getLevel().gameEvent(player, GameEvent.ENTITY_INTERACT, event.getTarget().position());
                    event.getTarget().discard();
                } else player.swing(hand);

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);

            }
        }
    }

    public void CheckBucket() {

    }

    public void checkEntity(Player player, InteractionHand hand, ItemStack stack, EntityType slime, Item bucket, EntityType test){
        if (stack.getItem() == Items.BUCKET) {
            if (slime == test)
                return;
            else return;
        } else if (stack.getItem() == AetherItems.SKYROOT_BUCKET.get()) {
            if (slime == EntityType.SLIME) {
                bucket = AQItems.SLIME_IN_A_SKYROOT_BUCKET_ITEM.get();
            }
        } else if (player.getOffhandItem().getItem() == Items.BUCKET) {
            hand = InteractionHand.OFF_HAND;
            if (slime == test)
                return;
            else return;
        } else if (player.getOffhandItem().getItem() == AetherItems.SKYROOT_BUCKET.get()) {
            hand = InteractionHand.OFF_HAND;
            if (slime == test)
                bucket = AQItems.SLIME_IN_A_SKYROOT_BUCKET_ITEM.get();
        } else return;
    }
}
