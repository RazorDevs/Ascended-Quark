package org.razordevs.ascended_quark.datagen.compat.deep_aether;

import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
//import org.razordevs.ascended_quark.datagen.builders.recipe.ConditionalShapedRecipeBuilder;
import org.razordevs.ascended_quark.datagen.normal.AQRecipeData;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class DACompRecipeData extends AQRecipeData {

    private static final String DA_WOOD = "deep_aether_wood";

    public DACompRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(output, provider, itemMap, blockMap);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        woodset("roseroot", DABlocks.ROSEROOT_PLANKS.get(), DABlocks.ROSEROOT_LOG.get(), DABlocks.ROSEROOT_WOOD.get(), DABlocks.STRIPPED_ROSEROOT_WOOD.get(), DABlocks.ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_SLAB.get(), DA_WOOD, consumer);
        this.hedge(aqBlocks.get("flowering_roseroot_hedge"), DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DA_WOOD, consumer);
        this.hedge(aqBlocks.get("blue_roseroot_hedge"), DABlocks.BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DA_WOOD, consumer);
        this.hedge(aqBlocks.get("flowering_blue_roseroot_hedge"), DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DA_WOOD, consumer);
        this.carpet(aqBlocks.get("flowering_roseroot_leaf_carpet"), DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);
        this.carpet(aqBlocks.get("blue_roseroot_leaf_carpet"), DABlocks.BLUE_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);
        this.carpet(aqBlocks.get("flowering_blue_roseroot_leaf_carpet"), DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);

        woodset("cruderoot", DABlocks.CRUDEROOT_PLANKS.get(), DABlocks.CRUDEROOT_LOG.get(), DABlocks.CRUDEROOT_WOOD.get(), DABlocks.STRIPPED_CRUDEROOT_WOOD.get(), DABlocks.CRUDEROOT_LEAVES.get(), DABlocks.CRUDEROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("sunroot", DABlocks.SUNROOT_PLANKS.get(), DABlocks.SUNROOT_LOG.get(), DABlocks.SUNROOT_WOOD.get(), DABlocks.STRIPPED_SUNROOT_WOOD.get(), DABlocks.SUNROOT_LEAVES.get(), DABlocks.SUNROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("yagroot", DABlocks.YAGROOT_PLANKS.get(), DABlocks.YAGROOT_LOG.get(), DABlocks.YAGROOT_WOOD.get(), DABlocks.STRIPPED_YAGROOT_WOOD.get(), DABlocks.YAGROOT_LEAVES.get(), DABlocks.YAGROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("conberry", DABlocks.CONBERRY_PLANKS.get(), DABlocks.CONBERRY_LOG.get(), DABlocks.CONBERRY_WOOD.get(), DABlocks.STRIPPED_CONBERRY_WOOD.get(), DABlocks.CONBERRY_LEAVES.get(), DABlocks.CONBERRY_SLAB.get(), DA_WOOD, consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, aqBlocks.get("aether_mud_pillar"))
                .define('A', DABlocks.AETHER_MUD_BRICKS_SLAB.get())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.AETHER_MUD_BRICKS_SLAB.get()), has(DABlocks.AETHER_MUD_BRICKS_SLAB.get()))
                .save(consumer
                        .withConditions(
                                zetaFlag("more_mud_blocks"),
                                zetaFlag("more_aether_mud_blocks")
                        )
                );

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, aqBlocks.get("aether_mud_brick_lattice"), 4)
                .define('A', DABlocks.AETHER_MUD_BRICKS.get())
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .unlockedBy(getHasName(DABlocks.AETHER_MUD_BRICKS.get()), has(DABlocks.AETHER_MUD_BRICKS.get()))
                .save(consumer
                        .withConditions(
                                zetaFlag("more_mud_blocks"),
                                zetaFlag("more_aether_mud_blocks")
                        )
                );

        stonecuttingRecipe(aqBlocks.get("aether_mud_pillar"), DABlocks.AETHER_MUD_BRICKS.get(), consumer,
                zetaFlag("more_mud_blocks"),
                zetaFlag("more_aether_mud_blocks")
        );
    }
}
