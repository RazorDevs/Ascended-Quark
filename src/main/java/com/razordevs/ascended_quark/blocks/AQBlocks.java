package com.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.construction.QuicksoilGlassBlock;
import com.aetherteam.aether.block.construction.QuicksoilGlassPaneBlock;
import com.aetherteam.aether.client.particle.AetherParticleTypes;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.nitrogen.item.block.EntityBlockItem;
import com.razordevs.ascended_quark.AscendedQuark;
import com.razordevs.ascended_quark.entity.block.AQBlockEntityTypes;
import com.razordevs.ascended_quark.entity.block.SkyrootChestBlockEntity;
import com.razordevs.ascended_quark.entity.block.SkyrootTrappedChestBlockEntity;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.content.building.block.VerticalSlabBlock;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class AQBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AscendedQuark.MODID);
    public static final RegistryObject<Block> AETHER_DIRT_BRICKS = registerBlock("aether_dirt_bricks", () -> new Block(Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_SLAB = registerBlock("aether_dirt_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_STAIRS = registerBlock("aether_dirt_brick_stairs", () -> new StairBlock(AETHER_DIRT_BRICKS.get().defaultBlockState(), Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_WALL = registerBlock("aether_dirt_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AETHER_DIRT_BRICK_VERTICAL_SLAB = registerBlock("aether_dirt_brick_vertical_slab", () -> new VerticalSlabBlock(AQBlocks.AETHER_DIRT_BRICKS, BlockBehaviour.Properties.copy(AQBlocks.AETHER_DIRT_BRICKS.get())));

    public static final RegistryObject<Block> SKYROOT_STOOL = registerBlock("skyroot_stool", AQStoolBlock::new);
    public static final RegistryObject<Block> SKYROOT_CHEST = registerBlock("skyroot_chest", () -> new AQChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST), AQBlockEntityTypes.SKYROOT_CHEST::get));
    public static final RegistryObject<Block> SKYROOT_TRAPPED_CHEST = registerBlock("skyroot_trapped_chest", () -> new AQTrappedChestBlock(BlockBehaviour.Properties.copy(Blocks.TRAPPED_CHEST)));
    public static final RegistryObject<Block> SKYROOT_HEDGE = registerBlock("skyroot_hedge", () -> new AQHedgeBlock(AetherBlocks.SKYROOT_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> GOLDEN_SKYROOT_HEDGE = registerBlock("golden_skyroot_hedge", () -> new AQHedgeBlock(AetherBlocks.SKYROOT_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> CRYSTAL_SKYROOT_HEDGE = registerBlock("crystal_skyroot_hedge", () -> new AQHedgeBlock(AetherBlocks.CRYSTAL_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> CRYSTAL_FRUIT_SKYROOT_HEDGE = registerBlock("crystal_fruit_skyroot_hedge", () -> new AQHedgeBlock(AetherBlocks.CRYSTAL_FRUIT_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> HOLIDAY_SKYROOT_HEDGE = registerBlock("holiday_skyroot_hedge", () -> new AQHedgeBlock(AetherBlocks.HOLIDAY_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));
    public static final RegistryObject<Block> DECORATED_HOLIDAY_SKYROOT_HEDGE = registerBlock("decorated_holiday_skyroot_hedge", () -> new AQHedgeBlock(AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), AetherBlocks.SKYROOT_FENCE.get()));

    public static final RegistryObject<Block> SKYROOT_LEAF_CARPET = registerBlock("skyroot_leaf_carpet", () -> new AQLeafCarpetBlock(AetherBlocks.SKYROOT_LEAVES.get(), BlockBehaviour.Properties.of(Material.CLOTH_DECORATION,
                    MaterialColor.GRASS)
            .strength(0F)
            .sound(SoundType.GRASS).noOcclusion()));

    public static final RegistryObject<Block> GOLDEN_OAK_LEAF_CARPET = registerBlock("golden_oak_leaf_carpet", () -> new LeafCarpetWithParticlesBlock(AetherParticleTypes.GOLDEN_OAK_LEAVES, AetherBlocks.GOLDEN_OAK_LEAVES.get(), BlockBehaviour.Properties.copy(AQBlocks.SKYROOT_LEAF_CARPET.get())));
    public static final RegistryObject<Block> CRYSTAL_LEAF_CARPET = registerBlock("crystal_leaf_carpet", () -> new LeafCarpetWithParticlesBlock(AetherParticleTypes.CRYSTAL_LEAVES, AetherBlocks.CRYSTAL_LEAVES.get(), BlockBehaviour.Properties.copy(AQBlocks.SKYROOT_LEAF_CARPET.get())));
    public static final RegistryObject<Block> CRYSTAL_FRUIT_LEAF_CARPET = registerBlock("crystal_fruit_leaf_carpet", () -> new LeafCarpetWithParticlesBlock(AetherParticleTypes.CRYSTAL_LEAVES, AetherBlocks.CRYSTAL_FRUIT_LEAVES.get(), BlockBehaviour.Properties.copy(AQBlocks.SKYROOT_LEAF_CARPET.get())));
    public static final RegistryObject<Block> HOLIDAY_LEAF_CARPET = registerBlock("holiday_leaf_carpet", () -> new LeafCarpetWithParticlesBlock(AetherParticleTypes.HOLIDAY_LEAVES, AetherBlocks.HOLIDAY_LEAVES.get(), BlockBehaviour.Properties.copy(AQBlocks.SKYROOT_LEAF_CARPET.get())));
    public static final RegistryObject<Block> DECORATED_HOLIDAY_LEAF_CARPET = registerBlock("decorated_holiday_leaf_carpet", () -> new LeafCarpetWithParticlesBlock(AetherParticleTypes.HOLIDAY_LEAVES, AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), BlockBehaviour.Properties.copy(AQBlocks.SKYROOT_LEAF_CARPET.get())));

    public static final RegistryObject<Block> HOLLOW_SKYROOT_LOG = registerBlock("hollow_skyroot_log", () -> new AQHollowLogBlock(BlockBehaviour.Properties.copy(AetherBlocks.SKYROOT_LOG.get())));
    public static final RegistryObject<Block> SKYROOT_LADDER = registerBlock("skyroot_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));
    public static final RegistryObject<Block> SKYROOT_POST = registerBlock("skyroot_post", () -> new AQWoodenPostBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_SKYROOT_POST = registerBlock("stripped_skyroot_post", () -> new AQWoodenPostBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> SKYROOT_VERTICAL_SLAB = registerBlock("skyroot_vertical_slab", ()-> new VerticalSlabBlock(AetherBlocks.SKYROOT_PLANKS, BlockBehaviour.Properties.copy(AetherBlocks.SKYROOT_PLANKS.get())));
    public static final RegistryObject<RotatedPillarBlock> SKYROOT_STICK_BLOCK = registerBlock("skyroot_stick_block", ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(AetherBlocks.SKYROOT_PLANKS.get())));

    public static final RegistryObject<Block> BLUE_BERRY_CRATE = registerBlock("blue_berry_crate", () -> new FlammableBlock(150, Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(1.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> QUICKSOIL_BRICKS = registerBlock("quicksoil_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> QUICKSOIL_BRICK_SLAB = registerBlock("quicksoil_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> QUICKSOIL_BRICK_STAIRS = registerBlock("quicksoil_brick_stairs", () -> new StairBlock(QUICKSOIL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> QUICKSOIL_BRICK_WALL = registerBlock("quicksoil_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> QUICKSOIL_BRICK_VERTICAL_SLAB = registerBlock("quicksoil_brick_vertical_slab", () -> new VerticalSlabBlock(AQBlocks.QUICKSOIL_BRICKS, BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));

    public static final RegistryObject<Block> POLISHED_ICESTONE = registerBlock("polished_icestone", () -> new Block(BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> POLISHED_ICESTONE_SLAB = registerBlock("polished_icestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> POLISHED_ICESTONE_STAIRS = registerBlock("polished_icestone_stairs", () -> new StairBlock(POLISHED_ICESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> POLISHED_ICESTONE_WALL = registerBlock("polished_icestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> POLISHED_ICESTONE_VERTICAL_SLAB = registerBlock("polished_icestone_vertical_slab", () -> new VerticalSlabBlock(AQBlocks.POLISHED_ICESTONE, BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));

    public static final RegistryObject<Block> ICESTONE_BRICKS = registerBlock("icestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> ICESTONE_BRICK_SLAB = registerBlock("icestone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> ICESTONE_BRICK_STAIRS = registerBlock("icestone_brick_stairs", () -> new StairBlock(ICESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> ICESTONE_BRICK_WALL = registerBlock("icestone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> ICESTONE_BRICK_VERTICAL_SLAB = registerBlock("icestone_brick_vertical_slab", () -> new VerticalSlabBlock(AQBlocks.ICESTONE_BRICKS, BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));


    public static final RegistryObject<Block> HOLYSTONE_FURNACE = registerBlock("holystone_furnace", () -> new AQFurnaceBlock(BlockBehaviour.Properties.copy(AetherBlocks.HOLYSTONE.get()).lightLevel(litBlockEmission(13))));
    public static final RegistryObject<Block> AMBROSIUM_LAMP = registerBlock("ambrosium_lamp", () -> new AmbrosiumLampBlock(BlockBehaviour.Properties.copy(AetherBlocks.HOLYSTONE.get()).lightLevel((blockState) -> AmbrosiumLampBlock.getScaledChargeLevel(blockState, 16))));
    public static final RegistryObject<Block> QUICKSOIL_FRAMED_GLASS = registerBlock("quicksoil_framed_glass", () -> new QuicksoilGlassBlock(BlockBehaviour.Properties.copy(AetherBlocks.QUICKSOIL_GLASS.get())));
    public static final RegistryObject<Block> QUICKSOIL_FRAMED_GLASS_PANE = registerBlock("quicksoil_framed_glass_pane", () -> new QuicksoilGlassPaneBlock(BlockBehaviour.Properties.copy(AetherBlocks.QUICKSOIL_GLASS_PANE.get())));


    // VANILLA AETHER VERTICAL SLABS
    public static final RegistryObject<Block> ANGELIC_VERTICAL_SLAB = registerBlock("angelic_vertical_slab", () -> new VerticalSlabBlock(AetherBlocks.ANGELIC_STONE, BlockBehaviour.Properties.copy(AetherBlocks.ANGELIC_STONE.get())));
    public static final RegistryObject<Block> HELLFIRE_VERTICAL_SLAB = registerBlock("hellfire_vertical_slab", () -> new VerticalSlabBlock(AetherBlocks.HELLFIRE_STONE, BlockBehaviour.Properties.copy(AetherBlocks.HELLFIRE_STONE.get())));
    public static final RegistryObject<Block> HOLYSTONE_VERTICAL_SLAB = registerBlock("holystone_vertical_slab", () -> new VerticalSlabBlock(AetherBlocks.HOLYSTONE, BlockBehaviour.Properties.copy(AetherBlocks.HOLYSTONE.get())));
    public static final RegistryObject<Block> MOSSY_HOLYSTONE_VERTICAL_SLAB = registerBlock("mossy_holystone_vertical_slab", () -> new VerticalSlabBlock(AetherBlocks.MOSSY_HOLYSTONE, BlockBehaviour.Properties.copy(AetherBlocks.MOSSY_HOLYSTONE.get())));
    public static final RegistryObject<Block> ICESTONE_VERTICAL_SLAB = registerBlock("icestone_vertical_slab", () -> new VerticalSlabBlock(AetherBlocks.ICESTONE, BlockBehaviour.Properties.copy(AetherBlocks.ICESTONE.get())));
    public static final RegistryObject<Block> HOLYSTONE_BRICK_VERTICAL_SLAB = registerBlock("holystone_brick_vertical_slab", () -> new VerticalSlabBlock(AetherBlocks.HOLYSTONE_BRICKS, BlockBehaviour.Properties.copy(AetherBlocks.HOLYSTONE_BRICKS.get())));
    public static final RegistryObject<Block> AEROGEL_VERTICAL_SLAB = registerBlock("aerogel_vertical_slab", () -> new VerticalSlabBlock(AetherBlocks.AEROGEL, BlockBehaviour.Properties.copy(AetherBlocks.AEROGEL.get())));


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
            else if (block == SKYROOT_TRAPPED_CHEST.get()) {
                return new EntityBlockItem(block, SkyrootTrappedChestBlockEntity::new, new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS));
            }
            else return new BlockItem(block, new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS));
        };
    }

    private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }
}

