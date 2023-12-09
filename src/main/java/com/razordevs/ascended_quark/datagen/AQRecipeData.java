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
    }


    void slab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        slabBuilder(slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void veticalSlab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        verticalSlabBuilder(slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    protected static RecipeBuilder verticalSlabBuilder(ItemLike p_176705_, Ingredient p_176706_) {
        return ShapedRecipeBuilder.shaped(p_176705_, 6).define('#', p_176706_)
                .pattern("#")
                .pattern("#")
                .pattern("#");
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
