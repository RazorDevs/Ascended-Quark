package org.razordevs.ascended_quark.items;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.razordevs.ascended_quark.module.ExtraSlimeAndSwetInABucketModule;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.zeta.module.ZetaModule;

public class AQSwetInABucketItem extends AQEntityInABucketItem {

	public AQSwetInABucketItem(String name, ZetaModule module,
			DeferredHolder<EntityType<?>, EntityType<Swet>> bucketEntity, boolean isSkyroot) {
		super(name, module, new Item.Properties().stacksTo(1), bucketEntity);
		if (isSkyroot)
			ExtraSlimeAndSwetInABucketModule.SWET_WITH_BUCKET_ITEM_SKYROOT.add(new Pair<>(bucketEntity, this));
		else
			ExtraSlimeAndSwetInABucketModule.SWET_WITH_BUCKET_ITEM.add(new Pair<>(bucketEntity, this));

		RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES.getKey(), this,
				AetherItems.SKYROOT_TADPOLE_BUCKET, module);
	}

	@Override
	public boolean getsExcited(ServerLevel world, int x, int z) {
		return world.dimensionTypeRegistration() == AetherDimensions.AETHER_DIMENSION_TYPE;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Slime getNewSlimeInstance(EntityType<? extends Slime> type, Level level) {
		return new Swet((EntityType<? extends Swet>) type, level);
	}
}