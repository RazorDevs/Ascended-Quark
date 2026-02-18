package org.razordevs.ascended_quark.datagen.normal.loot;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AQBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(AetherBlocks.TREASURE_CHEST.get()).map(ItemLike::asItem).collect(Collectors.toSet());
    final HashMap<String, Block> blockMap;
    List<Block> registeredBlocks = new ArrayList<>();

    protected AQBlockLoot(HashMap<String, Block> blockMap, HolderLookup.Provider provider) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
        this.blockMap = blockMap;
    }
    @Override
    protected void generate() {
        for (Block block : getKnownBlocks()) {
            if(!registeredBlocks.contains(block)) {
                if(block instanceof SlabBlock || block instanceof VerticalSlabBlock)
                    this.add(block, createSlabItemTable(block));
                else this.dropSelf(block);
            }

        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
       return blockMap.values();
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        registeredBlocks.add(block);
        this.map.put(block.getLootTable(), builder);
    }
}

