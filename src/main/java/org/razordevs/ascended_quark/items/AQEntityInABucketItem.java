package org.razordevs.ascended_quark.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.base.components.QuarkDataComponents;
import org.violetmoon.zeta.item.ZetaItem;
import org.violetmoon.zeta.module.ZetaModule;

import javax.annotation.Nonnull;
import java.util.UUID;

public abstract class AQEntityInABucketItem extends ZetaItem {
	private final DeferredHolder<EntityType<?>, ? extends EntityType<? extends Slime>> bucketEntityHolder;
	private final EntityType<? extends Slime> bucketEntity;

	public AQEntityInABucketItem(String regname, @Nullable ZetaModule module, Properties properties,
			DeferredHolder<EntityType<?>, ? extends EntityType<? extends Slime>> bucketEntity) {
		super(regname, module, properties.stacksTo(1));
		this.bucketEntityHolder = bucketEntity;
		this.bucketEntity = null;
	}

	public AQEntityInABucketItem(String regname, @Nullable ZetaModule module, Properties properties,
			EntityType<? extends Slime> bucketEntity) {
		super(regname, module, properties.stacksTo(1));
		this.bucketEntity = bucketEntity;
		this.bucketEntityHolder = null;
	}

	@Override
	public void inventoryTick(@Nonnull ItemStack stack, @Nonnull Level world, @Nonnull Entity entity, int itemSlot,
			boolean isSelected) {
		if (world instanceof ServerLevel serverLevel) {
			Vec3 pos = entity.position();
			int x = Mth.floor(pos.x);
			int z = Mth.floor(pos.z);
			boolean slime = getsExcited(serverLevel, x, z);
			boolean excited = Boolean.TRUE.equals(stack.get(QuarkDataComponents.EXCITED));
			if (excited != slime)
				stack.set(QuarkDataComponents.EXCITED, slime);
		}
	}

	@NotNull @Override
	public Component getName(@NotNull ItemStack stack) {
		if (stack.has(QuarkDataComponents.SLIME_NBT)) {
			CompoundTag cmp = stack.get(QuarkDataComponents.SLIME_NBT).copyTag();
			if (cmp != null && cmp.contains("CustomName")) {
				Component custom = Component.Serializer.fromJson(cmp.getString("CustomName"), RegistryAccess.EMPTY);
				return Component.translatable("item.quark.slime_in_a_bucket.named", custom);
			}
		}
		return super.getName(stack);
	}

	public abstract boolean getsExcited(ServerLevel world, int x, int z);

	public EntityType<? extends Slime> getBucketEntityType() {
		return bucketEntityHolder == null ? bucketEntity : bucketEntityHolder.get();
	}

	public abstract Slime getNewSlimeInstance(EntityType<? extends Slime> type, Level level);

	@Nonnull
	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos pos = context.getClickedPos();
		Direction facing = context.getClickedFace();
		Level level = context.getLevel();
		Player player = context.getPlayer();
		if (player == null)
			return InteractionResult.FAIL;
		InteractionHand hand = context.getHand();

		double x = pos.getX() + 0.5 + facing.getStepX();
		double y = pos.getY() + 0.5 + facing.getStepY();
		double z = pos.getZ() + 0.5 + facing.getStepZ();

		if (!level.isClientSide()) {
			Slime slime = getNewSlimeInstance(getBucketEntityType(), level);

			if ((player.getItemInHand(hand).has(QuarkDataComponents.SLIME_NBT))) {
				CompoundTag data = player.getItemInHand(hand).get(QuarkDataComponents.SLIME_NBT).copyTag();
				slime.load(data);
			} else {
				slime.getAttribute(Attributes.MAX_HEALTH).setBaseValue(1.0);
				slime.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3);
				slime.setHealth(slime.getMaxHealth());
			}

			slime.setPos(x, y, z);
			if (player.getAbilities().instabuild)
				slime.setUUID(UUID.randomUUID());

			level.gameEvent(player, GameEvent.ENTITY_PLACE, slime.position());
			level.addFreshEntity(slime);
			player.swing(hand);
		}

		level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.NEUTRAL, 1.0F, 1.0F);

		if (!player.getAbilities().instabuild)
			player.setItemInHand(hand, new ItemStack(Items.BUCKET));

		return InteractionResult.SUCCESS;
	}
}
