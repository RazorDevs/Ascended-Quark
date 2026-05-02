package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.item.AetherItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import org.razordevs.ascended_quark.items.AQSwetInABucketItem;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.base.components.QuarkDataComponents;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.bus.PlayEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.event.play.entity.player.ZPlayerInteract;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ZetaLoadModule(category = "aether")
public class ExtraSlimeAndSwetInABucketModule extends ZetaModule {

	@Config(flag = "are_swets_exited_in_the_aether", name = "Are Swets Exited In The Aether", description = "Disables Swets from dancing in The Aether. Note that this config only affects the client.")
	public static boolean swets_exited = true;
	@Config(flag = "are_swet_buckets_enabled", name = "Are Swet Buckets Enabled", description = "When disabled, disables all Ascended Quark bucket items except for the Slime in a Skyroot Bucket item. Disable if you find swet buckets unbalanced.")
	public boolean swet_bucket_enabled = true;

	public static List<Pair<DeferredHolder<EntityType<?>, ? extends EntityType<Swet>>, AQSwetInABucketItem>> SWET_WITH_BUCKET_ITEM = new ArrayList<>();
	public static List<Pair<DeferredHolder<EntityType<?>, ? extends EntityType<Swet>>, AQSwetInABucketItem>> SWET_WITH_BUCKET_ITEM_SKYROOT = new ArrayList<>();
	Item slimeSkyroot;
	Item blueSwet;
	Item blueSwetSkyroot;
	Item goldenSwet;
	Item goldenSwetSkyroot;

	@LoadEvent
	public void register(ZRegister register) {
		blueSwet = new AQSwetInABucketItem("blue_swet_in_a_bucket", this, AetherEntityTypes.BLUE_SWET, false);
		blueSwetSkyroot = new AQSwetInABucketItem("blue_swet_in_a_skyroot_bucket", this, AetherEntityTypes.BLUE_SWET,
				true);
		goldenSwet = new AQSwetInABucketItem("golden_swet_in_a_bucket", this, AetherEntityTypes.GOLDEN_SWET, false);
		goldenSwetSkyroot = new AQSwetInABucketItem("golden_swet_in_a_skyroot_bucket", this,
				AetherEntityTypes.GOLDEN_SWET, true);
		slimeSkyroot = new AQSlimeInABucketItem("slime_in_a_skyroot_bucket", this);
	}

	@PlayEvent
	public void entityInteract(ZPlayerInteract.EntityInteract event) {
		if (event.getTarget() != null) {
			if (event.getTarget().isAlive()) {
				Player player = event.getEntity();
				ItemStack stack = player.getMainHandItem();
				EntityType<?> entity = event.getTarget().getType();

				Pair<AQSwetInABucketItem, InteractionHand> result = checkAllBucket(player, entity);

				if (result == null && entity == EntityType.SLIME) {
					InteractionHand hand;
					if (player.getMainHandItem().getItem() == AetherItems.SKYROOT_BUCKET.get())
						hand = InteractionHand.MAIN_HAND;
					else if (player.getOffhandItem().getItem() == AetherItems.SKYROOT_BUCKET.get())
						hand = InteractionHand.OFF_HAND;
					else
						return;

					if (!event.getLevel().isClientSide) {
						ItemStack outStack = new ItemStack(slimeSkyroot);
						CompoundTag cmp = new CompoundTag();
						event.getTarget().save(cmp);
						outStack.set(QuarkDataComponents.SLIME_NBT, CustomData.of(cmp));

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
					}

					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide));
				} else if (result != null) {
					InteractionHand hand = result.getSecond();
					if (!event.getLevel().isClientSide) {

						ItemStack outStack = new ItemStack(result.getFirst());
						CompoundTag cmp = new CompoundTag();
						event.getTarget().save(cmp);
						outStack.set(QuarkDataComponents.SLIME_NBT, CustomData.of(cmp));

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
					}

					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide()));
				}
			}
		}
	}

	@Nullable public Pair<AQSwetInABucketItem, InteractionHand> checkAllBucket(Player player, EntityType<?> swet) {
		if (!swet_bucket_enabled)
			return null;

		var result = handBucketSelection(player, Items.BUCKET, swet, SWET_WITH_BUCKET_ITEM);
		return result == null
				? handBucketSelection(player, AetherItems.SKYROOT_BUCKET.get(), swet, SWET_WITH_BUCKET_ITEM_SKYROOT)
				: result;
	}

	private Pair<AQSwetInABucketItem, InteractionHand> handBucketSelection(Player player, Item check,
			EntityType<?> swet,
			List<Pair<DeferredHolder<EntityType<?>, ? extends EntityType<Swet>>, AQSwetInABucketItem>> list) {
		ItemStack stack = player.getMainHandItem();
		ItemStack stack2 = player.getOffhandItem();
		InteractionHand hand = InteractionHand.MAIN_HAND;

		if (stack.is(check) || stack2.is(check)) {
			if (stack.getItem() != check)
				hand = InteractionHand.OFF_HAND;

			for (Pair<DeferredHolder<EntityType<?>, ? extends EntityType<Swet>>, AQSwetInABucketItem> entry : list) {
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
				for (Pair<DeferredHolder<EntityType<?>, ? extends EntityType<Swet>>, AQSwetInABucketItem> pair : SWET_WITH_BUCKET_ITEM_SKYROOT) {
					if (!(pair.getSecond() instanceof AQSwetInABucketItem && !swets_exited))
						ItemProperties.register(pair.getSecond(), Quark.asResource("excited"), (stack, world, e,
								id) -> Boolean.TRUE.equals(stack.get(QuarkDataComponents.EXCITED)) ? 1 : 0);
				}

				if (swets_exited) {
					for (Pair<DeferredHolder<EntityType<?>, ? extends EntityType<Swet>>, AQSwetInABucketItem> pair : SWET_WITH_BUCKET_ITEM) {
						ItemProperties.register(pair.getSecond(), Quark.asResource("excited"), (stack, world, e,
								id) -> Boolean.TRUE.equals(stack.get(QuarkDataComponents.EXCITED)) ? 1 : 0);
					}
				}

				ItemProperties.register(slimeSkyroot, Quark.asResource("excited"),
						(stack, world, e, id) -> Boolean.TRUE.equals(stack.get(QuarkDataComponents.EXCITED)) ? 1 : 0);
			});
		}
	}
}
