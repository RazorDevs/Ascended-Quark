package org.razordevs.ascended_quark.datagen.compat.deep_aether;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.datagen.normal.AQItemModelData;
import org.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import org.razordevs.ascended_quark.items.AQSwetInABucketItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DACompItemModelData extends AQItemModelData {

	public DACompItemModelData(PackOutput output, ExistingFileHelper helper, HashMap<String, Item> itemMap,
			HashMap<String, Block> blockMap) {
		super(output, helper, itemMap, blockMap);
	}

	@Override
	protected void registerModels() {
		List<Item> toGenerateBlockItem = new ArrayList<>(itemMap.values());
		List<Block> toGenerateBlock = new ArrayList<>(blockMap.values());

		for (Item item : toGenerateBlockItem) {
			if (item instanceof AQSlimeInABucketItem || item instanceof AQSwetInABucketItem) {
				continue;
			}

			if (item instanceof TieredItem) {
				this.handheldItem(item);
			} else {
				this.item(item);
			}
		}

		for (Block block : toGenerateBlock) {
			if (block instanceof AQHedgeBlock)
				this.itemBlock(block, AscendedQuark.asResource("block/" + this.blockName(block) + "_post"));
			else if (block instanceof WallBlock)
				this.itemBlock(block, AscendedQuark.asResource("block/" + this.blockName(block) + "_inventory"));

			else if (block instanceof ChestBlock) {
				this.itemChest(block);
			} else if (block instanceof LadderBlock) {
				this.itemBlockFlat(block);
			} else {
				this.itemBlock(block);
			}
		}
	}
}