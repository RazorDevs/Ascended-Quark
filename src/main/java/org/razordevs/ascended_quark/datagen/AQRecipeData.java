package org.razordevs.ascended_quark.datagen;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.datagen.recipe.ConditionalShapedRecipeBuilder;
import org.razordevs.ascended_quark.datagen.recipe.ConditionalShapelessRecipeBuilder;
import org.razordevs.ascended_quark.datagen.recipe.ConditionalSingleItemBuilder;
import org.violetmoon.quark.base.Quark;
import oshi.util.tuples.Pair;
import teamrazor.deepaether.init.DABlocks;

import java.util.HashMap;
import java.util.function.Consumer;

public class AQRecipeData extends RecipeProvider {

    public static final ResourceLocation DEFAULT_FLAG = new ResourceLocation(AscendedQuark.MODID, "flag");
    public static final ResourceLocation QUARK_FLAG = new ResourceLocation(Quark.MOD_ID, "flag");

    private final HashMap<String, Item> itemMap;
    private final HashMap<String, Block> blockMap;

    private static final String DA_WOOD = "deep_aether_wood";

    public AQRecipeData(PackOutput output, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(output);
        this.itemMap = itemMap;
        this.blockMap = blockMap;
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        skyrootHedge(blockMap.get("decorated_holiday_skyroot_hedge"), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), consumer);

        woodset("roseroot", DABlocks.ROSEROOT_PLANKS.get(), DABlocks.ROSEROOT_LOG.get(), DABlocks.ROSEROOT_WOOD.get(), DABlocks.STRIPPED_ROSEROOT_WOOD.get(), DABlocks.ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_SLAB.get(), DA_WOOD, consumer);
        this.hedge(blockMap.get("flowering_roseroot_hedge"), DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DEFAULT_FLAG, DA_WOOD, consumer);
        this.hedge(blockMap.get("blue_roseroot_hedge"), DABlocks.BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DEFAULT_FLAG, DA_WOOD, consumer);
        this.hedge(blockMap.get("flowering_blue_roseroot_hedge"), DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DEFAULT_FLAG, DA_WOOD, consumer);
        this.carpet(blockMap.get("flowering_roseroot_leaf_carpet"), DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);
        this.carpet(blockMap.get("blue_roseroot_leaf_carpet"), DABlocks.BLUE_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);
        this.carpet(blockMap.get("flowering_blue_roseroot_leaf_carpet"), DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);

        woodset("cruderoot", DABlocks.CRUDEROOT_PLANKS.get(), DABlocks.CRUDEROOT_LOG.get(), DABlocks.CRUDEROOT_WOOD.get(), DABlocks.STRIPPED_CRUDEROOT_WOOD.get(), DABlocks.CRUDEROOT_LEAVES.get(), DABlocks.CRUDEROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("sunroot", DABlocks.SUNROOT_PLANKS.get(), DABlocks.SUNROOT_LOG.get(), DABlocks.SUNROOT_WOOD.get(), DABlocks.STRIPPED_SUNROOT_WOOD.get(), DABlocks.SUNROOT_LEAVES.get(), DABlocks.SUNROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("yagroot", DABlocks.YAGROOT_PLANKS.get(), DABlocks.YAGROOT_LOG.get(), DABlocks.YAGROOT_WOOD.get(), DABlocks.STRIPPED_YAGROOT_WOOD.get(), DABlocks.YAGROOT_LEAVES.get(), DABlocks.YAGROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("conberry", DABlocks.CONBERRY_PLANKS.get(), DABlocks.CONBERRY_LOG.get(), DABlocks.CONBERRY_WOOD.get(), DABlocks.STRIPPED_CONBERRY_WOOD.get(), DABlocks.CONBERRY_LEAVES.get(), DABlocks.CONBERRY_SLAB.get(), DA_WOOD, consumer);

        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockMap.get("aether_mud_pillar"))
                .define('A', DABlocks.AETHER_MUD_BRICKS_SLAB.get())
                .pattern("A")
                .pattern("A")
                .condition(QUARK_FLAG, "more_mud_blocks")
                .condition(DEFAULT_FLAG, "more_aether_mud_blocks")
                .unlockedBy(getHasName(DABlocks.AETHER_MUD_BRICKS_SLAB.get()), has(DABlocks.AETHER_MUD_BRICKS_SLAB.get()))
                .save(consumer);

        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockMap.get("aether_mud_brick_lattice"), 4)
                .define('A', DABlocks.AETHER_MUD_BRICKS.get())
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .condition(QUARK_FLAG, "more_mud_blocks")
                .condition(DEFAULT_FLAG, "more_aether_mud_blocks")
                .unlockedBy(getHasName(DABlocks.AETHER_MUD_BRICKS.get()), has(DABlocks.AETHER_MUD_BRICKS.get()))
                .save(consumer);

        stonecuttingRecipe(blockMap.get("aether_mud_pillar"), DABlocks.AETHER_MUD_BRICKS.get(), consumer,
                new Pair<>(QUARK_FLAG, "more_mud_blocks"),
                new Pair<>(DEFAULT_FLAG, "more_aether_mud_blocks"));

        /*
        ShapedRecipeBuilder.shaped(AQBlocks.AETHER_DIRT_BRICKS.get(), 4).define('A', AetherBlocks.AETHER_DIRT.get())
                .define('B', AetherBlocks.HOLYSTONE.get())
                .pattern("BA")
                .pattern("AA").unlockedBy(getHasName(AetherBlocks.AETHER_DIRT.get()), has(AetherBlocks.AETHER_DIRT.get())).save(consumer);

        slab(AQBlocks.AETHER_DIRT_BRICK_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        stairs(AQBlocks.AETHER_DIRT_BRICK_STAIRS.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        wall(AQBlocks.AETHER_DIRT_BRICK_WALL.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        veticalSlab(AQBlocks.AETHER_DIRT_BRICK_VERTICAL_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);

        stonecuttingRecipe(AQBlocks.AETHER_DIRT_BRICK_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), 2, consumer);
        stonecuttingRecipe(AQBlocks.AETHER_DIRT_BRICK_STAIRS.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        stonecuttingRecipe(AQBlocks.AETHER_DIRT_BRICK_WALL.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        stonecuttingRecipe(AQBlocks.AETHER_DIRT_BRICK_VERTICAL_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get(),2, consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.SKYROOT_STOOL.get()).define('A', AetherBlocks.SKYROOT_SLAB.get()
                ).define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("AAA").unlockedBy(getHasName(AQBlocks.SKYROOT_STOOL.get()), has(AQBlocks.SKYROOT_STOOL.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.SKYROOT_CHEST.get()).define('A', AetherBlocks.SKYROOT_PLANKS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA").unlockedBy(getHasName(AetherBlocks.SKYROOT_PLANKS.get()), has(AetherBlocks.SKYROOT_PLANKS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.SKYROOT_CHEST.get()).define('A', AetherBlocks.SKYROOT_LOG.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA").unlockedBy(getHasName(AetherBlocks.SKYROOT_LOG.get()), has(AetherBlocks.SKYROOT_LOG.get())).save(consumer, "skyroot_chest_from_wood");

        ShapelessRecipeBuilder.shapeless(Blocks.CHEST).requires(AQBlocks.SKYROOT_CHEST.get()).
            unlockedBy(getHasName(AetherBlocks.SKYROOT_PLANKS.get()), has(AetherBlocks.SKYROOT_PLANKS.get())).save(consumer);

        skyrootHedge(AQBlocks.SKYROOT_HEDGE.get(), AetherBlocks.SKYROOT_LEAVES.get(), consumer);
        skyrootHedge(AQBlocks.GOLDEN_SKYROOT_HEDGE.get(), AetherBlocks.GOLDEN_OAK_LEAVES.get(), consumer);
        skyrootHedge(AQBlocks.CRYSTAL_SKYROOT_HEDGE.get(), AetherBlocks.CRYSTAL_LEAVES.get(), consumer);
        skyrootHedge(AQBlocks.CRYSTAL_FRUIT_SKYROOT_HEDGE.get(), AetherBlocks.CRYSTAL_FRUIT_LEAVES.get(), consumer);
        skyrootHedge(AQBlocks.HOLIDAY_SKYROOT_HEDGE.get(), AetherBlocks.HOLIDAY_LEAVES.get(), consumer);
        skyrootHedge(AQBlocks.DECORATED_HOLIDAY_SKYROOT_HEDGE.get(), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), consumer);


        carpet(AQBlocks.SKYROOT_LEAF_CARPET.get(), AetherBlocks.SKYROOT_LEAVES.get(), consumer);
        carpet(AQBlocks.GOLDEN_OAK_LEAF_CARPET.get(), AetherBlocks.GOLDEN_OAK_LEAVES.get(), consumer);
        carpet(AQBlocks.CRYSTAL_LEAF_CARPET.get(), AetherBlocks.CRYSTAL_LEAVES.get(), consumer);
        carpet(AQBlocks.CRYSTAL_FRUIT_LEAF_CARPET.get(), AetherBlocks.CRYSTAL_FRUIT_LEAVES.get(), consumer);
        carpet(AQBlocks.HOLIDAY_LEAF_CARPET.get(), AetherBlocks.HOLIDAY_LEAVES.get(), consumer);
        carpet(AQBlocks.DECORATED_HOLIDAY_LEAF_CARPET.get(), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.HOLLOW_SKYROOT_LOG.get(), 4).define('A', AetherBlocks.SKYROOT_LOG.get())
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .unlockedBy(getHasName(AetherBlocks.SKYROOT_LOG.get()), has(AetherBlocks.SKYROOT_LOG.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.SKYROOT_LADDER.get(), 4)
                .define('A', AetherBlocks.SKYROOT_PLANKS.get())
                .define('B', Items.STICK)
                .pattern("B B")
                .pattern("BAB")
                .pattern("B B")
                .unlockedBy(getHasName(AetherBlocks.SKYROOT_PLANKS.get()), has(AetherBlocks.SKYROOT_PLANKS.get())).save(consumer);


        ShapedRecipeBuilder.shaped(AQBlocks.SKYROOT_POST.get(), 8)
                .define('A', AetherBlocks.SKYROOT_WOOD.get())
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(AetherBlocks.SKYROOT_WOOD.get()), has(AetherBlocks.SKYROOT_WOOD.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.STRIPPED_SKYROOT_POST.get(), 8)
                .define('A', AetherBlocks.STRIPPED_SKYROOT_WOOD.get())
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(AetherBlocks.STRIPPED_SKYROOT_WOOD.get()), has(AetherBlocks.STRIPPED_SKYROOT_WOOD.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.BLUE_BERRY_CRATE.get())
                .define('A', AetherItems.BLUE_BERRY.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(AetherItems.BLUE_BERRY.get()), has(AetherItems.BLUE_BERRY.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.QUICKSOIL_BRICKS.get(),2).define('A', AetherBlocks.QUICKSOIL.get())
                .pattern("AA")
                .pattern("AA").unlockedBy(getHasName(AetherBlocks.QUICKSOIL.get()), has(AetherBlocks.QUICKSOIL.get())).save(consumer);

        slab(AQBlocks.QUICKSOIL_BRICK_SLAB.get(), AQBlocks.QUICKSOIL_BRICKS.get(), consumer);
        stairs(AQBlocks.QUICKSOIL_BRICK_STAIRS.get(), AQBlocks.QUICKSOIL_BRICKS.get(), consumer);
        wall(AQBlocks.QUICKSOIL_BRICK_WALL.get(), AQBlocks.QUICKSOIL_BRICKS.get(), consumer);
        veticalSlab(AQBlocks.QUICKSOIL_BRICK_VERTICAL_SLAB.get(), AQBlocks.QUICKSOIL_BRICKS.get(), consumer);

        stonecuttingRecipe(AQBlocks.QUICKSOIL_BRICK_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), 2, consumer);
        stonecuttingRecipe(AQBlocks.QUICKSOIL_BRICK_STAIRS.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        stonecuttingRecipe(AQBlocks.QUICKSOIL_BRICK_WALL.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        stonecuttingRecipe(AQBlocks.QUICKSOIL_BRICK_VERTICAL_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get(),2, consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.HOLYSTONE_FURNACE.get()).define('A', AetherBlocks.HOLYSTONE.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA").unlockedBy(getHasName(AetherBlocks.HOLYSTONE.get()), has(AetherBlocks.HOLYSTONE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.AMBROSIUM_LAMP.get())
                .define('A', AetherBlocks.HOLYSTONE_BRICKS.get())
                .define('B', Blocks.GLASS)
                .define('C', AetherBlocks.AMBROSIUM_TORCH.get())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("AAA").unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.ZANITE_BARS.get(), 16)
                .define('A', AetherItems.ZANITE_GEMSTONE.get())
                .pattern("AAA")
                .pattern("AAA").unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get())).save(consumer);


        ShapedRecipeBuilder.shaped(AQBlocks.QUICKSOIL_FRAMED_GLASS.get(), 4)
                .define('A', AetherBlocks.QUICKSOIL_GLASS.get())
                .define('B', Items.IRON_INGOT)
                .pattern("BAB")
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy(getHasName(AetherBlocks.QUICKSOIL_GLASS.get()), has(AetherBlocks.QUICKSOIL_GLASS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.QUICKSOIL_FRAMED_GLASS_PANE.get(), 16)
                .define('A', AQBlocks.QUICKSOIL_FRAMED_GLASS.get())
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(AetherBlocks.QUICKSOIL_GLASS.get()), has(AetherBlocks.QUICKSOIL_GLASS.get())).save(consumer);


        ShapelessRecipeBuilder.shapeless(AQItems.AMBROSIUM_TORCH_ARROW.get())
                .requires(Items.ARROW).requires(AetherBlocks.AMBROSIUM_TORCH.get())
                .unlockedBy(getHasName(AetherBlocks.AMBROSIUM_TORCH.get()), has(AetherBlocks.AMBROSIUM_TORCH.get())).save(consumer);
x
        veticalSlab(AQBlocks.ANGELIC_VERTICAL_SLAB.get(), AetherBlocks.ANGELIC_SLAB.get(), consumer);
        veticalSlab(AQBlocks.HELLFIRE_VERTICAL_SLAB.get(), AetherBlocks.HELLFIRE_SLAB.get(), consumer);
        veticalSlab(AQBlocks.HOLYSTONE_VERTICAL_SLAB.get(), AetherBlocks.HOLYSTONE_SLAB.get(), consumer);
        veticalSlab(AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get(), AetherBlocks.MOSSY_HOLYSTONE_SLAB.get(), consumer);
        veticalSlab(AQBlocks.ICESTONE_VERTICAL_SLAB.get(), AetherBlocks.ICESTONE_SLAB.get(), consumer);
        veticalSlab(AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get(), AetherBlocks.HOLYSTONE_BRICK_SLAB.get(), consumer);
        veticalSlab(AQBlocks.AEROGEL_VERTICAL_SLAB.get(), AetherBlocks.AEROGEL_SLAB.get(), consumer);
        veticalSlab(AQBlocks.SKYROOT_VERTICAL_SLAB.get(), AetherBlocks.SKYROOT_SLAB.get(), consumer);

        ShapedRecipeBuilder.shaped(AetherBlocks.QUICKSOIL_GLASS.get())
                .define('A', AQItems.QUICKSOIL_GLASS_SHARD.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(AetherBlocks.QUICKSOIL_GLASS.get()), has(AetherBlocks.QUICKSOIL_GLASS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.ICESTONE_BRICKS.get())
                .define('A', AQBlocks.POLISHED_ICESTONE.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(AetherBlocks.ICESTONE.get()), has(AetherBlocks.ICESTONE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.POLISHED_ICESTONE.get())
                .define('A', AetherBlocks.ICESTONE.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(AetherBlocks.ICESTONE.get()), has(AetherBlocks.ICESTONE.get())).save(consumer);

        slab(AQBlocks.ICESTONE_BRICK_SLAB.get(), AQBlocks.ICESTONE_BRICKS.get(), consumer);
        stairs(AQBlocks.ICESTONE_BRICK_STAIRS.get(), AQBlocks.ICESTONE_BRICKS.get(), consumer);
        wall(AQBlocks.ICESTONE_BRICK_WALL.get(), AQBlocks.ICESTONE_BRICKS.get(), consumer);
        veticalSlab(AQBlocks.ICESTONE_BRICK_VERTICAL_SLAB.get(), AQBlocks.ICESTONE_BRICKS.get(), consumer);

        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_SLAB.get(), AQBlocks.ICESTONE_BRICKS.get(), 2, consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_STAIRS.get(), AQBlocks.ICESTONE_BRICKS.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_WALL.get(), AQBlocks.ICESTONE_BRICKS.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_VERTICAL_SLAB.get(), AQBlocks.ICESTONE_BRICKS.get(),2, consumer);

        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_SLAB.get(), AQBlocks.POLISHED_ICESTONE.get(), 2, consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_STAIRS.get(), AQBlocks.POLISHED_ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_WALL.get(), AQBlocks.POLISHED_ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_VERTICAL_SLAB.get(), AQBlocks.POLISHED_ICESTONE.get(),2, consumer);

        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_SLAB.get(), AetherBlocks.ICESTONE.get(), 2, consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_STAIRS.get(), AetherBlocks.ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_WALL.get(), AetherBlocks.ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICK_VERTICAL_SLAB.get(), AetherBlocks.ICESTONE.get(),2, consumer);

        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE.get(), AetherBlocks.ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICKS.get(), AetherBlocks.ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.ICESTONE_BRICKS.get(), AQBlocks.POLISHED_ICESTONE.get(), consumer);

        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_SLAB.get(), AQBlocks.POLISHED_ICESTONE.get(), 2, consumer);
        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_STAIRS.get(), AQBlocks.POLISHED_ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_WALL.get(), AQBlocks.POLISHED_ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_VERTICAL_SLAB.get(), AQBlocks.POLISHED_ICESTONE.get(),2, consumer);

        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_SLAB.get(), AetherBlocks.ICESTONE.get(), 2, consumer);
        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_STAIRS.get(), AetherBlocks.ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_WALL.get(), AetherBlocks.ICESTONE.get(), consumer);
        stonecuttingRecipe(AQBlocks.POLISHED_ICESTONE_VERTICAL_SLAB.get(), AetherBlocks.ICESTONE.get(),2, consumer);
*/
    }

    private void woodset(String type, Block planks, Block log, Block wood, Block strippedWood, Block leaves, Block slab, String flag, Consumer<FinishedRecipe> consumer) {
        this.verticalPlanks(blockMap.get("vertical_" + type + "_planks"), planks, flag, consumer);
        this.hedge(blockMap.get(type+"_hedge"), leaves, log, DEFAULT_FLAG, flag, consumer);
        this.verticalSlab(blockMap.get(type + "_vertical_slab"), slab, flag, consumer);
        this.carpet(blockMap.get(type + "_leaf_carpet"), leaves, flag, consumer);
        this.post(blockMap.get(type + "_post"), wood, flag, consumer);
        this.post(blockMap.get("stripped_" + type + "_post"), strippedWood, flag, consumer);
        this.chest(blockMap.get(type+"_chest"), planks, flag, consumer);
        this.chest(blockMap.get(type+"_trapped_chest"), planks, flag, consumer);
        this.hollowLog(blockMap.get("hollow_" + type + "_log"), log, flag, consumer);
        this.ladder(blockMap.get(type + "_ladder"), planks, flag, consumer);
    }


    void slab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void verticalSlab(Block vertical, Block slab, String flag, Consumer<FinishedRecipe> consumer) {
        verticalSlabBuilder(vertical, Ingredient.of(slab), flag).unlockedBy(getHasName(slab), has(slab)).save(consumer);
        verticalSlabRevert(vertical, slab, consumer, flag);
    }

    protected static RecipeBuilder verticalSlabBuilder(ItemLike itemLike, Ingredient ingredient, String flag) {
        return ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike, 3).define('#', ingredient)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag);
    }

    void carpet(ItemLike carpet, ItemLike leaf, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, carpet, 3).define('A', leaf)
                .pattern("AA")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "leaf_carpet")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(leaf), has(leaf)).save(consumer);
    }

    void post(ItemLike post, ItemLike wood, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, post, 8)
                .define('A', wood)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "wooden_posts")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(wood), has(wood)).save(consumer);
    }

    void verticalPlanks(ItemLike verticalPlanks, ItemLike planks, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, verticalPlanks, 3)
                .define('A', planks)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_planks")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);

        ConditionalShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks)
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag).requires(verticalPlanks)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer, getItemName(verticalPlanks) + "_from_" + getItemName(planks));
    }

    void chest(ItemLike chest, ItemLike planks, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chest)
                .define('A', planks)
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "variant_chests")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    void hollowLog(ItemLike hollowLog, ItemLike log, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hollowLog, 4)
                .define('A', log)
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "hollow_logs")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(log), has(log)).save(consumer);
    }

    void ladder(ItemLike ladder, ItemLike planks, String flag, Consumer<FinishedRecipe> consumer){
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ladder, 4)
                .define('A', planks)
                .define('-', AetherItems.SKYROOT_STICK.get())
                .pattern("- -")
                .pattern("-A-")
                .pattern("- -")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "variant_ladders")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    void verticalSlabRevert(Block slab, Block reverted, Consumer<FinishedRecipe> consumer, String flag) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab), flag).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    void hedge(ItemLike hedge, ItemLike leaves, TagKey<Item> stem, ResourceLocation conditionLocation, String conditionName, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2)
                .define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A").unlockedBy(getHasName(leaves), has(leaves))
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "hedges")
                .condition(conditionLocation, conditionName)
                .save(consumer);
    }

    void hedge(ItemLike hedge, ItemLike leaves, ItemLike stem, ResourceLocation conditionLocation, String conditionName, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2).define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "hedges")
                .condition(conditionLocation, conditionName)
                .unlockedBy(getHasName(leaves), has(leaves)).save(consumer);
    }

    void skyrootHedge(ItemLike hedge, ItemLike leaves, Consumer<FinishedRecipe> consumer) {
        hedge(hedge, leaves, AetherTags.Items.SKYROOT_LOGS, DEFAULT_FLAG, "skyroot_quark_blocks", consumer);
    }
    protected static RecipeBuilder verticalSlabRevertBuilder(ItemLike itemLike, Ingredient ingredient, String flag) {
        return ConditionalShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, itemLike).condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag).requires(ingredient);
    }

    void slabRevert(Block slab, Block reverted, Consumer<FinishedRecipe> consumer) {
        slabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    protected static RecipeBuilder slabRevertBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike).define('A', ingredient).pattern("AA");
    }

    void stairs(Block stairs, Block texture, Consumer<FinishedRecipe> consumer) {
        stairBuilder(stairs, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void wall(Block wall, Block texture, Consumer<FinishedRecipe> consumer) {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    protected ResourceLocation name(String name) {
        return new ResourceLocation(AscendedQuark.MODID, name);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, Consumer<FinishedRecipe> consumer, Pair<ResourceLocation, String>... condition) {
        stonecuttingRecipe(item, ingredient, 1, consumer, condition);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, int count, Consumer<FinishedRecipe> consumer, Pair<ResourceLocation, String>[] condition) {
        ConditionalSingleItemBuilder.stonecutting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, item, count)
                .condition(condition)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, name(getConversionRecipeName(item, ingredient) + "_stonecutting"));
    }
}
