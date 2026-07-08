package org.razordevs.ascended_quark.datagen.normal;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import org.razordevs.ascended_quark.items.AQSwetInABucketItem;
import org.violetmoon.zeta.block.ZetaButtonBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AQItemModelData extends ItemModelProvider {

	protected final HashMap<String, Item> itemMap;
	protected final HashMap<String, Block> blockMap;

	public AQItemModelData(PackOutput output, ExistingFileHelper helper, HashMap<String, Item> itemMap,
			HashMap<String, Block> blockMap) {
		super(output, AscendedQuark.MODID, helper);
		this.itemMap = itemMap;
		this.blockMap = blockMap;
	}

	@Override
	protected void registerModels() {
		List<Item> toGenerateBlockItem = new ArrayList<>();
		toGenerateBlockItem.addAll(itemMap.values());

		List<Block> toGenerateBlock = new ArrayList<>();
		toGenerateBlock.addAll(blockMap.values());

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

		toGenerateBlock.remove(blockMap.get("ambrosium_lamp"));
		this.itemBlock(blockMap.get("ambrosium_lamp"), AscendedQuark.asResource("block/ambrosium_lamp_0"));
		toGenerateBlock.remove(blockMap.get("quicksoil_framed_glass_pane"));
		this.itemBlockFlatName(blockMap.get("quicksoil_framed_glass_pane"), "quicksoil_framed_glass");
		toGenerateBlock.remove(blockMap.get("zanite_button"));

		for (Block block : toGenerateBlock) {
			if (block instanceof AQHedgeBlock)
				this.itemBlock(block, AscendedQuark.asResource("block/" + this.blockName(block) + "_post"));
			else if (block instanceof WallBlock)
				this.itemBlock(block, AscendedQuark.asResource("block/" + this.blockName(block) + "_inventory"));

			else if (block instanceof ChestBlock) {
				this.itemChest(block, AscendedQuark.AETHER, "block/construction/");
			} else if (block instanceof LadderBlock) {
				this.itemBlockFlat(block);
			} else {
				this.itemBlock(block);
			}
		}
	}

	public ItemModelBuilder handheldItem(Item item) {
		return this.withExistingParent(this.itemName(item), this.mcLoc("item/handheld")).texture("layer0",
				this.modLoc("item/" + this.itemName(item)));
	}

	public void itemBlock(Block block) {
		this.withExistingParent(this.blockName(block), this.texture(this.blockName(block)));
	}

	public void itemBlock(Block block, ResourceLocation location) {
		this.withExistingParent(this.blockName(block), location);
	}

	public void itemChest(Block block) {
		this.withExistingParent(this.blockName(block), this.mcLoc("item/" + this.blockName(Blocks.CHEST)));
	}

	public void itemChest(Block block, String id, String prefix) {
		String plankName = this.blockName(block).replace("trapped_", "").replace("_chest", "_planks");
		ResourceLocation plankRes = ResourceLocation.fromNamespaceAndPath(id, prefix + plankName);
		this.withExistingParent(this.blockName(block), this.mcLoc("item/" + this.blockName(Blocks.CHEST)))
				.texture("particle", plankRes);
	}

	public void item(Item item) {
		this.withExistingParent(this.itemName(item), mcLoc("item/generated")).texture("layer0",
				modLoc("item/" + this.itemName(item)));
	}
	public void itemFence(Block block, Block baseBlock) {
		this.withExistingParent(this.blockName(block), this.mcLoc("block/fence_inventory")).texture("texture",
				this.texture(this.blockName(baseBlock)));
	}

	public void itemBlockFlatName(Block block, String location) {
		this.withExistingParent(this.blockName(block), this.mcLoc("item/generated")).texture("layer0",
				this.texture(location));
	}
	public void itemButton(Block block, Block baseBlock) {
		this.withExistingParent(this.blockName(block), this.mcLoc("block/button_inventory")).texture("texture",
				this.texture(this.blockName(baseBlock)));
	}
	public void eggItem(Item item) {
		this.withExistingParent(this.itemName(item), this.mcLoc("item/template_spawn_egg"));
	}
	public void itemWallBlock(Block block, Block baseBlock) {
		this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
	}

	public void itemBlockFlat(Block block) {
		this.withExistingParent(this.blockName(block), this.mcLoc("item/generated")).texture("layer0",
				this.texture(this.blockName(block)));
	}

	public String blockName(Block block) {
		ResourceLocation location = BuiltInRegistries.BLOCK.getKey(block);
		if (location != null) {
			return location.getPath();
		} else {
			throw new IllegalStateException("Unknown block: " + block.toString());
		}
	}

	public String itemName(Item item) {
		ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);
		if (location != null) {
			return location.getPath();
		} else {
			throw new IllegalStateException("Unknown item: " + item.toString());
		}
	}
	protected ResourceLocation texture(String name) {
		return this.modLoc("block/" + name);
	}
}