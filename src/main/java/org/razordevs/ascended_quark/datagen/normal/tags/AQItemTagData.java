package org.razordevs.ascended_quark.datagen.normal.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.datagen.provider.tags.AQItemTagProvider;
import org.violetmoon.quark.base.Quark;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class AQItemTagData extends AQItemTagProvider {
    public AQItemTagData(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(p_275343_, p_275729_, p_275322_, existingFileHelper, itemMap, blockMap);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "stools"))).add(
                blockMap.get("skyroot_stool").asItem());

        tag(ItemTags.ARROWS).add(
                itemMap.get("ambrosium_torch_arrow"));

        super.addTags(provider);
    }
}
