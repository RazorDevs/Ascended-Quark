package org.razordevs.ascended_quark.datagen.provider.tags;

import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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

public class AQItemTagProvider extends ItemTagsProvider {
    protected final HashMap<String, Item> itemMap;
    protected final HashMap<String, Block> blockMap;
    public AQItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(p_275343_, p_275729_, p_275322_, AscendedQuark.MODID, existingFileHelper);
        this.itemMap = itemMap;
        this.blockMap = blockMap;
    }

    public static final String[] Wood  = {
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

        IntrinsicTagAppender<Item> tag = this.tag(AetherTags.Items.TREATED_AS_AETHER_ITEM);
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
                tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "posts"))).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof AQHedgeBlock)
                tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "hedges"))).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof AQHollowLogBlock)
                tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "hollow_logs"))).add(block.asItem());
        });

        blocks.forEach(block -> {
            if (block instanceof ChestBlock) {
                tag(Tags.Items.CHESTS_WOODEN).add(block.asItem());
                tag(Tags.Items.CHESTS).add(block.asItem());

                if((block instanceof AQTrappedVariantChestBlock)) {
                    tag(Tags.Items.CHESTS_TRAPPED).add(block.asItem());
                }
                else {
                    tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "revertable_chests"))).add(block.asItem());
                    tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "boatable_chests"))).add(block.asItem());
                }
            }
        });

        blocks.forEach(block -> {
            if (block instanceof LadderBlock)
                tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "ladders"))).add(block.asItem());
        });

        blockMap.keySet().forEach(s -> {
            if (blockMap.get(s) instanceof VerticalSlabBlock block) {
                tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "vertical_slabs"))).add(block.asItem());
                for(String string : Wood) {
                    if(s.contains(string)) {
                        tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "wooden_vertical_slabs"))).add(block.asItem());
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
