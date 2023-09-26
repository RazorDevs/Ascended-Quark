package com.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.item.AetherCreativeTabs;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.content.building.block.QuarkVerticalSlabBlock;
import vazkii.quark.content.building.block.VerticalSlabBlock;
import vazkii.quark.content.building.module.VerticalSlabsModule;

import java.util.function.Supplier;

public class AQBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AscendedQuarkMod.MODID);


    public static final RegistryObject<Block> AETHER_COARSE_DIRT = registerBlock("aether_coarse_dirt", () -> new AetherCoarseDirtBlock(Block.Properties.copy(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> AETHER_DIRT_BRICKS = registerBlock("aether_dirt_bricks", () -> new Block(Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_SlAB = registerBlock("aether_dirt_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_STAIRS = registerBlock("aether_dirt_brick_stairs", () -> new StairBlock(AETHER_DIRT_BRICKS.get().defaultBlockState(), Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_WALL = registerBlock("aether_dirt_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_VERTICAL_SLAB = registerBlock("aether_dirt_brick_vertical_slab", () -> new VerticalSlabBlock(AETHER_DIRT_BRICKS, Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));





    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return AQItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS)));
    }
}
