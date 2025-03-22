package org.razordevs.ascended_quark.datagen.compat.deep_aether.tags;

import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.blocks.AQHollowLogBlock;
import org.razordevs.ascended_quark.blocks.AQTrappedVariantChestBlock;
import org.razordevs.ascended_quark.blocks.AQWoodenPostBlock;
import org.razordevs.ascended_quark.datagen.provider.tags.AQItemTagProvider;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DACompTagData extends AQItemTagProvider {

    public DACompTagData(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(p_275343_, p_275729_, p_275322_, existingFileHelper, itemMap, blockMap);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Ascended Quark Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        super.addTags(provider);
    }
}
