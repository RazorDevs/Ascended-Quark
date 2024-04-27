package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.item.AetherItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.registries.RegistryObject;
import org.razordevs.ascended_quark.items.AQEntityInABucketItem;
import org.razordevs.ascended_quark.items.AQSwetInABucketItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import org.violetmoon.quark.content.tools.module.SlimeInABucketModule;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.bus.PlayEvent;
import org.violetmoon.zeta.event.play.entity.player.ZPlayerInteract;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.ItemNBTHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ZetaLoadModule(category = "aether")
public class ExtraSlimeAndSwetInABucketModule extends ZetaModule {

    @Config(flag = "are_swets_exited_in_the_aether", name = "Are Swets Exited In The Aether", description = "Disables Swets from dancing in The Aether. Note that this config only affects the client.")
    public static boolean swets_exited = true;
    @Config(flag = "are_swet_buckets_enabled", name = "Are Swet Buckets Enabled", description = "When disabled, disables all Ascended Quark bucket items except for the Slime in a Skyroot Bucket item. Disable if you find swet buckets unbalanced.")
    public boolean swet_bucket_enabled = true;
    public static List<Pair<RegistryObject<EntityType<Swet>>, AQSwetInABucketItem>> SLIME_WITH_BUCKET_ITEM = new ArrayList<>();
    public static List<Pair<RegistryObject<EntityType<Swet>>, AQSwetInABucketItem>> SLIME_WITH_BUCKET_ITEM_SKYROOT = new ArrayList<>();

    @PlayEvent
    public void entityInteract(ZPlayerInteract.EntityInteract event) {
        if (event.getTarget() != null) {
            if (event.getTarget().isAlive()) {
                Player player = event.getEntity();
                ItemStack stack = player.getMainHandItem();
                EntityType<?> entity = event.getTarget().getType();
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

    @Nullable
    public Pair<AQEntityInABucketItem, InteractionHand> CheckAllBucket(Player player, EntityType<?> swet) {
        ItemStack stack = player.getMainHandItem();
        ItemStack stack2 = player.getOffhandItem();
        InteractionHand hand = InteractionHand.MAIN_HAND;

        if (!swet_bucket_enabled)
            return null;

        if(stack.getItem() == Items.BUCKET )

        if (stack.getItem() == Items.BUCKET || stack2.getItem() == Items.BUCKET) {
            if (stack.getItem() != Items.BUCKET)
                hand = InteractionHand.OFF_HAND;

            for (Pair<RegistryObject<EntityType<Swet>>, AQSwetInABucketItem> entry : SLIME_WITH_BUCKET_ITEM) {
                if (entry.getFirst().get() == swet) {
                    return new Pair<>(entry.getSecond(), hand);
                }
            }
        } else if (stack.getItem() == AetherItems.SKYROOT_BUCKET.get() || stack2.getItem() == AetherItems.SKYROOT_BUCKET.get()) {
            if (stack.getItem() != AetherItems.SKYROOT_BUCKET.get())
                hand = InteractionHand.OFF_HAND;

            for (Pair<RegistryObject<EntityType<Swet>>, AQEntityInABucketItem> entry : SLIME_WITH_BUCKET_ITEM_SKYROOT) {
                if (entry.getFirst().get() == swet) {
                    return new Pair<>(entry.getSecond(), hand);
                }
            }
        }

        return null;
    }

    @ZetaLoadModule(clientReplacement = true)
    public static class Client extends ExtraSlimeAndSwetInABucketModule {

        @LoadEvent
        public void clientSetup(ZClientSetup event) {
            event.enqueueWork(() -> {
                for (Pair<EntityType, AQEntityInABucketItem> pair : SLIME_WITH_BUCKET_ITEM_SKYROOT) {
                    if (!(pair.getSecond() instanceof AQSwetInABucketItem && !swets_exited))
                        ItemProperties.register(pair.getSecond(), new ResourceLocation("excited"), (stack, world, e, id) -> ItemNBTHelper.getBoolean(stack, AQEntityInABucketItem.TAG_EXCITED, false) ? 1 : 0);
                }

                if (swets_exited) {
                    for (Pair<EntityType, AQEntityInABucketItem> pair : SLIME_WITH_BUCKET_ITEM) {
                        ItemProperties.register(pair.getSecond(), new ResourceLocation("excited"), (stack, world, e, id) -> ItemNBTHelper.getBoolean(stack, AQEntityInABucketItem.TAG_EXCITED, false) ? 1 : 0);
                    }
                }
            });
        }
    }
}
