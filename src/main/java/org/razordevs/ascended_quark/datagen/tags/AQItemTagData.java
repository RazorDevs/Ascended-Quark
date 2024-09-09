package org.razordevs.ascended_quark.datagen.tags;

import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.blocks.AQHollowLogBlock;
import org.razordevs.ascended_quark.blocks.AQTrappedVariantChestBlock;
import org.razordevs.ascended_quark.blocks.AQWoodenPostBlock;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AQItemTagData extends ItemTagsProvider {
    final HashMap<String, Item> itemMap;
    final HashMap<String, Block> blockMap;
    public AQItemTagData(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(p_275343_, p_275729_, p_275322_, AscendedQuark.MODID, existingFileHelper);
        this.itemMap = itemMap;
        this.blockMap = blockMap;
    }

    protected static final String[] Wood  = {
            "skyroot",
            "roseroot",
            "yagroot",
            "cruderoot",
            "sunroot",
            "conberry"
    };

    @Nonnull
    @Override
    public String getName() {
        return "Ascended Quark Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        List<Item> items = new ArrayList<>(itemMap.values());
        List<Block> blocks = new ArrayList<>(blockMap.values());

        blockMap.values().forEach(block -> items.add(block.asItem()));

        // Makes tool debuff work with all Deep Aether blocks. Commented code can be used to remove blocks if necessary
        IntrinsicTagAppender<Item> tag = this.tag(AetherTags.Items.TREATED_AS_AETHER_ITEM);
        // itemList.remove(Blocks.DIRT);
        items.forEach(tag::add);


        blocks.forEach(block -> {
            if (block instanceof SlabBlock)
                tag(ItemTags.SLABS).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof StairBlock)
                tag(ItemTags.STAIRS).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof WallBlock)
                tag(ItemTags.WALLS).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof AQWoodenPostBlock)
                tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "posts"))).add(block.asItem());
        });

        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "stools"))).add(
                blockMap.get("skyroot_stool").asItem());


        blocks.forEach(block -> {
            if (block instanceof AQHedgeBlock)
                tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "hedges"))).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof AQHollowLogBlock)
                tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "hollow_logs"))).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof ChestBlock) {
                tag(Tags.Items.CHESTS_WOODEN).add(block.asItem());
                tag(Tags.Items.CHESTS).add(block.asItem());

                if((block instanceof AQTrappedVariantChestBlock)) {
                    tag(Tags.Items.CHESTS_TRAPPED).add(block.asItem());
                }
                else {
                    tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "revertable_chests"))).add(block.asItem());
                    tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "boatable_chests"))).add(block.asItem());
                }
            }
        });

        blocks.forEach(block -> {
            if (block instanceof LadderBlock)
                tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "ladders"))).add(block.asItem());
        });

        tag(ItemTags.ARROWS).add(
                itemMap.get("ambrosium_torch_arrow"));

        blockMap.keySet().forEach(s -> {
            if (blockMap.get(s) instanceof VerticalSlabBlock block) {
                tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "vertical_slabs"))).add(block.asItem());
                for(String string : Wood) {
                    if(s.contains(string)) {
                        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "wooden_vertical_slabs"))).add(block.asItem());
                        break;
                    }
                }
            }
        });

        blockMap.keySet().forEach(s -> {
            if(s.contains("vertical_") && s.contains("_planks")) {
                tag(AetherTags.Items.PLANKS_CRAFTING).add(blockMap.get(s).asItem());
            }
        });

        blockMap.keySet().forEach(s -> {
            if(s.contains("bookshelf")) {
                tag(Tags.Items.BOOKSHELVES).add(blockMap.get(s).asItem());
            }
        });
    }
}
