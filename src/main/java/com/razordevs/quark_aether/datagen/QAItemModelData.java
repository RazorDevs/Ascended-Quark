package com.razordevs.quark_aether.datagen;

import com.razordevs.quark_aether.QuarkAetherMod;
import com.razordevs.quark_aether.blocks.QABlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class QAItemModelData extends ItemModelProvider {
    public QAItemModelData(DataGenerator output, ExistingFileHelper helper) {
        super(output, QuarkAetherMod.MODID, helper);
    }
    
    
    @Override
    protected void registerModels() {
        this.itemBlock(QABlocks.AETHER_COARSE_DIRT.get());
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