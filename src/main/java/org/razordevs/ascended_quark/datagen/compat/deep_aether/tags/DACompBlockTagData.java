package org.razordevs.ascended_quark.datagen.compat.deep_aether.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.datagen.provider.tags.AQBlockTagProvider;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class DACompBlockTagData extends AQBlockTagProvider {
    public DACompBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper, HashMap<String, Block> blockMap) {
        super(output, registries, helper, blockMap);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                blockMap.get("aether_mud_brick_lattice"),
                blockMap.get("aether_mud_pillar"),
                blockMap.get("mossy_holystone_tile_vertical_slab"),
                blockMap.get("holystone_tile_vertical_slab"),
                blockMap.get("big_holystone_bricks_vertical_slab")
        );

        tag(BlockTags.MINEABLE_WITH_AXE).add(
                blockMap.get("goldenleaf_berries_crate")
        );

        super.addTags(provider);
    }
}
