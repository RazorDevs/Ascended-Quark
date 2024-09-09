package org.razordevs.ascended_quark.datagen.loot;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AQBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(AetherBlocks.TREASURE_CHEST.get()).map(ItemLike::asItem).collect(Collectors.toSet());
    final HashMap<String, Block> blockMap;
    List<Block> registeredBlocks = new ArrayList<>();

    protected AQBlockLoot(HashMap<String, Block> blockMap) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
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

