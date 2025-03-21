package org.razordevs.ascended_quark.datagen.compat.deep_aether;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.datagen.AQItemModelData;
import org.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import org.razordevs.ascended_quark.items.AQSwetInABucketItem;
import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.item.IZetaItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DACompItemModelData extends AQItemModelData {

    public DACompItemModelData(PackOutput output, ExistingFileHelper helper, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(output, helper, itemMap, blockMap);
    }
    
    @Override
    protected void registerModels() {
        List<Item> toGenerateBlockItem = new ArrayList<>();
        toGenerateBlockItem.addAll(itemMap.values());

        List<Block> toGenerateBlock = new ArrayList<>();
        toGenerateBlock.addAll(blockMap.values());

        for(Item item : toGenerateBlockItem) {
            if(!Objects.equals(((IZetaItem) (item)).getModule().category().requiredMod, AscendedQuark.DEEP_AETHER))
                continue;

            if (item instanceof AQSlimeInABucketItem || item instanceof AQSwetInABucketItem) {
                continue;
            }

            if (item instanceof TieredItem) {
                this.handheldItem(item);
            }
            else {
                this.item(item);
            }
        }

        for(Block block : toGenerateBlock) {
            if(!Objects.equals(((IZetaBlock) (block)).getModule().category().requiredMod, AscendedQuark.DEEP_AETHER))
                continue;

             if(block instanceof AQHedgeBlock)
                 this.itemBlock(block, new ResourceLocation(AscendedQuark.MODID,  "block/" + this.blockName(block) + "_post"));
             else if(block instanceof WallBlock)
                this.itemBlock(block, new ResourceLocation(AscendedQuark.MODID,  "block/" + this.blockName(block) + "_inventory"));

             else if(block instanceof ChestBlock) {
                 this.itemChest(block);
             }
             else if(block instanceof LadderBlock) {
                 this.itemBlockFlat(block);
             }
             else {
                 this.itemBlock(block);
             }
        }
    }
}