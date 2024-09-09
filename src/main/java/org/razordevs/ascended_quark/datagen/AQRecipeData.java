package org.razordevs.ascended_quark.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class AQRecipeData extends RecipeProvider {

    //TODO: Fix Recipes with Every Compat and Woodworks

    public AQRecipeData(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
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

/*
    void slab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        slabBuilder(slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
        //slabRevert(slab, texture, consumer);
    }

    void veticalSlab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        verticalSlabBuilder(slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
        veticalSlabRevert(slab, texture, consumer);
    }

    protected static RecipeBuilder verticalSlabBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(itemLike, 3).define('#', ingredient)
                .pattern("#")
                .pattern("#")
                .pattern("#");
    }

    void carpet(ItemLike carpet, ItemLike leaf, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(carpet, 3).define('A', leaf)
                .pattern("AA").unlockedBy(getHasName(leaf), has(leaf)).save(consumer);
    }

    void veticalSlabRevert(Block slab, Block reverted, Consumer<FinishedRecipe> consumer) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    void hedge(ItemLike hedge, ItemLike leaves, TagKey<Item> stem, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(hedge,2).define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A").unlockedBy(getHasName(leaves), has(leaves)).save(consumer);
    }

    void hedge(ItemLike hedge, ItemLike leaves, ItemLike stem, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(hedge,2).define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A").unlockedBy(getHasName(leaves), has(leaves)).save(consumer);
    }

    void skyrootHedge(ItemLike hedge, ItemLike leaves, Consumer<FinishedRecipe> consumer) {
        hedge(hedge, leaves, AetherTags.Items.SKYROOT_LOGS, consumer);
    }
    protected static RecipeBuilder verticalSlabRevertBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapelessRecipeBuilder.shapeless(itemLike).requires(ingredient);
    }

    void slabRevert(Block slab, Block reverted, Consumer<FinishedRecipe> consumer) {
        slabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    protected static RecipeBuilder slabRevertBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(itemLike).define('A', ingredient).pattern("AA");
    }

    void stairs(Block stairs, Block texture, Consumer<FinishedRecipe> consumer) {
        stairBuilder(stairs, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void wall(Block wall, Block texture, Consumer<FinishedRecipe> consumer) {
        wallBuilder(wall, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    protected ResourceLocation  name(String name) {
        return new ResourceLocation(AscendedQuark.MODID, name);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, Consumer<FinishedRecipe> consumer) {
        stonecuttingRecipe(item, ingredient, 1, consumer);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, int count, Consumer<FinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), item, count).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, name(getConversionRecipeName(item, ingredient) + "_stonecutting"));
    }

 */
}
