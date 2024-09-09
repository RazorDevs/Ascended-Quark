package org.razordevs.ascended_quark.datagen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.HashMap;
import java.util.List;

public class AQLootTableData {
    public static LootTableProvider create(PackOutput output, HashMap<String, Block> blockMap) {
        return new LootTableProvider(output, AQLoot.IMMUTABLE_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(()-> new AQBlockLoot(blockMap), LootContextParamSets.BLOCK)
        ));
    }
}
