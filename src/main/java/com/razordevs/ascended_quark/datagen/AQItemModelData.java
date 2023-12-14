package com.razordevs.ascended_quark.datagen;

import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class AQItemModelData extends ItemModelProvider {
    public AQItemModelData(DataGenerator output, ExistingFileHelper helper) {
        super(output, AscendedQuarkMod.MODID, helper);
    }
    
    
    @Override
    protected void registerModels() {
        this.itemBlock(AQBlocks.AETHER_DIRT_BRICKS.get());
        this.itemBlock(AQBlocks.AETHER_DIRT_BRICK_STAIRS.get());
        this.itemBlock(AQBlocks.AETHER_DIRT_BRICK_SLAB.get());
        this.itemBlock(AQBlocks.AETHER_DIRT_BRICK_VERTICAL_SLAB.get());

        this.itemBlock(AQBlocks.QUICKSOIL_BRICKS.get());
        this.itemBlock(AQBlocks.QUICKSOIL_BRICK_STAIRS.get());
        this.itemWallBlock(AQBlocks.QUICKSOIL_BRICK_WALL.get(), AQBlocks.QUICKSOIL_BRICKS.get());
        this.itemBlock(AQBlocks.QUICKSOIL_BRICK_SLAB.get());
        this.itemBlock(AQBlocks.QUICKSOIL_BRICK_VERTICAL_SLAB.get());

        this.itemBlock(AQBlocks.SKYROOT_LEAF_CARPET.get());
        this.itemBlock(AQBlocks.HOLLOW_SKYROOT_LOG.get());
        this.itemBlock(AQBlocks.SKYROOT_POST.get());
        this.itemBlock(AQBlocks.STRIPPED_SKYROOT_POST.get());
        this.itemBlockFlat(AQBlocks.SKYROOT_LADDER.get());
        this.itemBlock(AQBlocks.HOLYSTONE_FURNACE.get());
        this.itemWallBlock(AQBlocks.AETHER_DIRT_BRICK_WALL.get(), AQBlocks.AETHER_DIRT_BRICKS.get());

        this.itemBlock(AQBlocks.ANGELIC_VERTICAL_SLAB.get());
        this.itemBlock(AQBlocks.HELLFIRE_VERTICAL_SLAB.get());
        this.itemBlock(AQBlocks.HOLYSTONE_VERTICAL_SLAB.get());
        this.itemBlock(AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get());
        this.itemBlock(AQBlocks.ICESTONE_VERTICAL_SLAB.get());
        this.itemBlock(AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get());
        this.itemBlock(AQBlocks.AEROGEL_VERTICAL_SLAB.get());

        this.item(AQItems.AMBROSIUM_TORCH_ARROW.get());

        this.itemBlock(AQBlocks.QUICKSOIL_FRAMED_GLASS.get());
        this.item(AQItems.QUICKSOIL_GLASS_SHARD.get());
    }

    public void handheldItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/handheld"))
                .texture("layer0", this.modLoc("item/"  + this.itemName(item)));
    }

    public void itemBlock(Block block) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block)));
    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }
    public void itemFence(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/fence_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }

    public void itemBlockFlatName(Block block, String location) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated")).texture("layer0", this.texture(location));
    }
    public void itemButton(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/button_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }
    public void eggItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/template_spawn_egg"));
    }
    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
    }

    public void itemBlockFlat(Block block) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated"))
                .texture("layer0", this.texture(this.blockName(block)));
    }

    public String blockName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public String itemName(Item item) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
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