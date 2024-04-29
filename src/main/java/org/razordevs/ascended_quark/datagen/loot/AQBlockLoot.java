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
import org.razordevs.ascended_quark.blocks.AQBlocks;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AQBlockLoot extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(AetherBlocks.TREASURE_CHEST.get()).map(ItemLike::asItem).collect(Collectors.toSet());
    Collection<RegistryObject<Block>> blocks = AQBlocks.BLOCKS.getEntries();

    List<Block> registeredBlocks = new ArrayList<>();

    protected AQBlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
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
       List<Block> returnBlock = new ArrayList<>();
        for (RegistryObject<Block> block : blocks) {
            returnBlock.add(block.get());
        }
        return  returnBlock;
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        registeredBlocks.add(block);
        this.map.put(block.getLootTable(), builder);
    }
}

