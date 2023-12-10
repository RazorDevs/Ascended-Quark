package com.razordevs.ascended_quark.events;


import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.item.AetherItems;
import com.mojang.datafixers.util.Pair;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQStoolBlock;
import com.razordevs.ascended_quark.items.AQEntityInABucketItem;
import com.razordevs.ascended_quark.items.AQItems;
import com.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = AscendedQuarkMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AQEvents {
    public static List<Pair<EntityType, AQEntityInABucketItem>> SLIME_WITH_BUCKET_ITEM = new ArrayList<>();
    public static List<Pair<EntityType, AQEntityInABucketItem>> SLIME_WITH_BUCKET_ITEM_SKYROOT = new ArrayList<>();
    @SubscribeEvent
    public static void entityInteract(PlayerInteractEvent.EntityInteract event) {
        System.out.println("Hello");
        if(event.getTarget() != null) {
            if(event.getTarget().isAlive()) {
                Player player = event.getEntity();
                ItemStack stack = player.getMainHandItem();

                Pair<AQEntityInABucketItem, InteractionHand> result = CheckAllBucket(player, event.getTarget().getType());
                if (result != null) {
                    InteractionHand hand = result.getSecond();
                    if (!event.getLevel().isClientSide) {
                        ItemStack outStack = new ItemStack(result.getFirst());
                        CompoundTag cmp = event.getTarget().serializeNBT();
                        ItemNBTHelper.setCompound(outStack, AQEntityInABucketItem.TAG_ENTITY_DATA, cmp);

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
    }

    public static Pair<AQEntityInABucketItem, InteractionHand> CheckAllBucket(Player player, EntityType slime) {
        ItemStack stack = player.getMainHandItem();
        ItemStack stack2 = player.getOffhandItem();
        InteractionHand hand = InteractionHand.MAIN_HAND;

        if (stack.getItem() == Items.BUCKET || stack2.getItem() == Items.BUCKET) {
            if(stack.getItem() != Items.BUCKET)
                hand = InteractionHand.OFF_HAND;

            for (Pair<EntityType, AQEntityInABucketItem> entry : SLIME_WITH_BUCKET_ITEM) {
                if (entry.getFirst() == slime) {
                    return new Pair<>(entry.getSecond(), hand);
                }
            }
        }
        else if (stack.getItem() == AetherItems.SKYROOT_BUCKET.get() || stack2.getItem() == AetherItems.SKYROOT_BUCKET.get()) {
            if(stack.getItem() != AetherItems.SKYROOT_BUCKET.get())
                hand = InteractionHand.OFF_HAND;

            for (Pair<EntityType, AQEntityInABucketItem> entry : SLIME_WITH_BUCKET_ITEM_SKYROOT) {
                if (entry.getFirst() == slime) {
                    return new Pair<>(entry.getSecond(), hand);
                }
            }
        }

        return null;
    }
}
