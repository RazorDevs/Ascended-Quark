package com.razordevs.ascended_quark.datagen.loot;

import com.google.common.collect.Sets;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.content.building.block.VerticalSlabBlock;

import java.util.*;
import java.util.function.BiConsumer;

import static java.lang.System.in;

public class AQBlockLoot extends BlockLoot {

    Collection<RegistryObject<Block>> blocks = AQBlocks.BLOCKS.getEntries();

    List<Block> registeredBlocks = new ArrayList<>();
    @Override
    protected void addTables() {
        //this.dropWhenSilkTouch(AQBlocks.AETHER_DIRT_BRICKS.get());

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

