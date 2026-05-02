package org.razordevs.ascended_quark.items;

import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.phys.Vec3;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.quark.base.components.QuarkDataComponents;
import org.violetmoon.zeta.item.ZetaItem;
import org.violetmoon.zeta.module.ZetaModule;

import javax.annotation.Nonnull;

public class AQSlimeInABucketItem extends AQEntityInABucketItem {

	public AQSlimeInABucketItem(String name, ZetaModule module) {
		super(name, module, new Item.Properties().stacksTo(1), EntityType.SLIME);

		RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES.getKey(), this,
				AetherItems.SKYROOT_TADPOLE_BUCKET, module);
	}

	@Override
	public boolean getsExcited(ServerLevel world, int x, int z) {
		ChunkPos chunkpos = new ChunkPos(new BlockPos(x, 0, z));
		return WorldgenRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
	}

	@Override
	public Slime getNewSlimeInstance(EntityType<? extends Slime> type, Level level) {
		return new Slime(type, level);
	}
}
