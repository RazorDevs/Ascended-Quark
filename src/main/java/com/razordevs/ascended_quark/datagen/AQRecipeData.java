package com.razordevs.ascended_quark.datagen;

import com.aetherteam.aether.block.AetherBlocks;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class AQRecipeData extends RecipeProvider {
    public AQRecipeData(DataGenerator output) {
        super(output);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        slab(AQBlocks.AETHER_DIRT_BRICK_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        stairs(AQBlocks.AETHER_DIRT_BRICK_STAIRS.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);
        wall(AQBlocks.AETHER_DIRT_BRICK_WALL.get(), AQBlocks.AETHER_DIRT_BRICKS.get(), consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.SKYROOT_STOOL.get()).define('A', AetherBlocks.SKYROOT_SLAB.get()
                ).define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("AAA").unlockedBy(getHasName(AQBlocks.SKYROOT_STOOL.get()), has(AQBlocks.SKYROOT_STOOL.get())).save(consumer);

        ShapedRecipeBuilder.shaped(AQBlocks.SKYROOT_CHEST.get()).define('A', AetherBlocks.SKYROOT_PLANKS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA").unlockedBy(getHasName(AetherBlocks.SKYROOT_PLANKS.get()), has(AetherBlocks.SKYROOT_PLANKS.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(Blocks.CHEST).requires(AQBlocks.SKYROOT_CHEST.get()).
            unlockedBy(getHasName(AetherBlocks.SKYROOT_PLANKS.get()), has(AetherBlocks.SKYROOT_PLANKS.get())).save(consumer);

        veticalSlab(AQBlocks.ANGELIC_VERTICAL_SLAB.get(), AetherBlocks.ANGELIC_STONE.get(), consumer);
        veticalSlab(AQBlocks.HELLFIRE_VERTICAL_SLAB.get(), AetherBlocks.HELLFIRE_STONE.get(), consumer);
        veticalSlab(AQBlocks.HOLYSTONE_VERTICAL_SLAB.get(), AetherBlocks.HOLYSTONE.get(), consumer);
        veticalSlab(AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get(), AetherBlocks.MOSSY_HOLYSTONE.get(), consumer);
        veticalSlab(AQBlocks.ICESTONE_VERTICAL_SLAB.get(), AetherBlocks.ICESTONE.get(), consumer);
        veticalSlab(AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get(), AetherBlocks.HOLYSTONE_BRICKS.get(), consumer);
        veticalSlab(AQBlocks.AEROGEL_VERTICAL_SLAB.get(), AetherBlocks.AEROGEL.get(), consumer);
    }


    void slab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        slabBuilder(slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void veticalSlab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        verticalSlabBuilder(slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    protected static RecipeBuilder verticalSlabBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(itemLike, 3).define('#', ingredient)
                .pattern("#")
                .pattern("#")
                .pattern("#");
    }

    void veticalSlabRevert(Block slab, Block reverted, Consumer<FinishedRecipe> consumer) {
        verticalSlabBuilder(slab, Ingredient.of(reverted)).unlockedBy(getHasName(reverted), has(reverted)).save(consumer);
    }

    protected static RecipeBuilder verticalSlabRevertBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapelessRecipeBuilder.shapeless(itemLike).requires(ingredient);
    }

    void stairs(Block stairs, Block texture, Consumer<FinishedRecipe> consumer) {
        stairBuilder(stairs, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void wall(Block wall, Block texture, Consumer<FinishedRecipe> consumer) {
        wallBuilder(wall, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    protected ResourceLocation name(String name) {
        return new ResourceLocation(AscendedQuarkMod.MODID, name);
    }
}
