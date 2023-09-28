package com.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.nitrogen.item.block.EntityBlockItem;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.entity.block.AQBlockEntityTypes;
import com.razordevs.ascended_quark.entity.block.SkyrootChestBlockEntity;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.content.building.block.*;
import vazkii.quark.mixin.LadderBlockMixin;

import java.util.Objects;
import java.util.function.Function;
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

    public static final RegistryObject<Block> SKYROOT_STOOL = registerBlock("skyroot_stool", AQStoolBlock::new);
    public static final RegistryObject<Block> SKYROOT_CHEST = registerBlock("skyroot_chest", () -> new AQChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST), AQBlockEntityTypes.SKYROOT_CHEST::get));
    public static final RegistryObject<Block> SKYROOT_HEDGE = registerBlock("skyroot_hedge", () -> new AQHedgeBlock(AetherBlocks.SKYROOT_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> SKYROOT_LEAF_CARPET = registerBlock("skyroot_leaf_carpet", () -> new AQLeafCarpetBlock(AetherBlocks.SKYROOT_LEAVES.get(), BlockBehaviour.Properties.of(Material.CLOTH_DECORATION,
                    MaterialColor.GRASS)
            .strength(0F)
            .sound(SoundType.GRASS).noOcclusion()));

    public static final RegistryObject<Block> HOLLOW_SKYROOT_LOG = registerBlock("hollow_skyroot_log", () -> new AQHollowLogBlock(BlockBehaviour.Properties.copy(AetherBlocks.SKYROOT_LOG.get())));
    public static final RegistryObject<Block> SKYROOT_LADDER = registerBlock("skyroot_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        AQItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    @SuppressWarnings("unchecked")
    private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>) register(name, block, AQBlocks::registerBlockItem);
    }

    private static <B extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<B> blockRegistryObject) {
        return () -> {
            B block = Objects.requireNonNull(blockRegistryObject.get());
            if (block == SKYROOT_CHEST.get()) {
           return new EntityBlockItem(block, SkyrootChestBlockEntity::new, new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS));
            }
            else return  new BlockItem(block, new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS));
    };
    }
}

