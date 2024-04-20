package org.razordevs.ascended_quark.datagen.loot;

import org.razordevs.ascended_quark.blocks.AQBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.content.building.block.VerticalSlabBlock;

import java.util.*;

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

