package org.razordevs.ascended_quark.datagen.normal.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AQLootTableData {
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, HashMap<String, Block> blockMap) {
        //return new LootTableProvider(output, AQLoot.IMMUTABLE_LOOT_TABLES, List.of(
        return new LootTableProvider(output, Collections.emptySet(),
            List.of(
                new LootTableProvider.SubProviderEntry((provider1)-> new AQBlockLoot(blockMap, provider1), LootContextParamSets.BLOCK)
            ),
                provider
        );
    }
}
