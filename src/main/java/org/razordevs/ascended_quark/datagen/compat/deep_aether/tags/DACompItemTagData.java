package org.razordevs.ascended_quark.datagen.compat.deep_aether.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.datagen.provider.tags.AQItemTagProvider;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class DACompItemTagData extends AQItemTagProvider {

	public DACompItemTagData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider,
			CompletableFuture<TagLookup<Block>> block, @Nullable ExistingFileHelper existingFileHelper,
			HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
		super(packOutput, provider, block, existingFileHelper, itemMap, blockMap);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		super.addTags(provider);
	}
}
