package org.razordevs.ascended_quark.datagen.normal.tags;

import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.datagen.provider.tags.AQBlockTagProvider;
import org.violetmoon.quark.base.Quark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AQBlockTagData extends AQBlockTagProvider {
    public AQBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper, HashMap<String, Block> blockMap) {
        super(output, registries, helper, blockMap);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        List<Block> blocks = new ArrayList<>(blockMap.values());

        IntrinsicTagAppender<Block> tag = this.tag(AetherTags.Blocks.TREATED_AS_AETHER_BLOCK);
        blocks.forEach(tag::add);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                blockMap.get("holystone_furnace"),
                blockMap.get("ambrosium_lamp"),
                blockMap.get("angelic_vertical_slab"),
                blockMap.get("hellfire_vertical_slab"),
                blockMap.get("holystone_brick_vertical_slab"),
                blockMap.get("mossy_holystone_vertical_slab"),
                blockMap.get("holystone_brick_vertical_slab"),
                blockMap.get("aerogel_vertical_slab")
        );

        tag(BlockTags.MINEABLE_WITH_AXE).add(
                blockMap.get("blue_berry_crate")
        );

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "stools"))).add(
                blockMap.get("skyroot_stool")
        );

        super.addTags(provider);
    }
}
