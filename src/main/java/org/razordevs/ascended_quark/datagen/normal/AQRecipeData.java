package org.razordevs.ascended_quark.datagen.normal;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.zeta.config.FlagCondition;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AQRecipeData extends RecipeProvider {
    protected final HashMap<String, Item> aqItems;
    protected final HashMap<String, Block> aqBlocks;

    public AQRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, HashMap<String, Item> aqItems, HashMap<String, Block> aqBlocks) {
        super(output, provider);
        this.aqItems = aqItems;
        this.aqBlocks = aqBlocks;
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        this.woodset("skyroot", AetherBlocks.SKYROOT_PLANKS.get(),
                AetherBlocks.SKYROOT_LOG.get(), AetherBlocks.SKYROOT_WOOD.get(),
                AetherBlocks.STRIPPED_SKYROOT_WOOD.get(), AetherBlocks.SKYROOT_LEAVES.get(),
                AetherBlocks.SKYROOT_SLAB.get(), "skyroot_quark_blocks", consumer);

        this.skyrootHedge(aqBlocks.get("holiday_skyroot_hedge"), AetherBlocks.HOLIDAY_LEAVES.get(), consumer);
        this.skyrootHedge(aqBlocks.get("decorated_holiday_skyroot_hedge"), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), consumer);
        this.skyrootHedge(aqBlocks.get("golden_skyroot_hedge"), AetherBlocks.GOLDEN_OAK_LEAVES.get(), consumer);

        this.carpet(aqBlocks.get("holiday_leaf_carpet"), AetherBlocks.HOLIDAY_LEAVES.get(), "skyroot_quark_blocks", consumer);
        this.carpet(aqBlocks.get("decorated_holiday_leaf_carpet"), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), "skyroot_quark_blocks", consumer);
        this.carpet(aqBlocks.get("golden_oak_leaf_carpet"), AetherBlocks.GOLDEN_OAK_LEAVES.get(), "skyroot_quark_blocks", consumer);

        this.verticalSlab(aqBlocks.get("icestone_vertical_slab"), AetherBlocks.ICESTONE_SLAB.get(), consumer);
        this.verticalSlab(aqBlocks.get("icestone_bricks_vertical_slab"), aqBlocks.get("icestone_bricks_slab"), consumer);
        this.verticalSlab(aqBlocks.get("angelic_vertical_slab"), AetherBlocks.ANGELIC_SLAB.get(), consumer);
        this.verticalSlab(aqBlocks.get("hellfire_vertical_slab"), AetherBlocks.HELLFIRE_SLAB.get(), consumer);
    }

    protected void woodset(String type, Block planks, Block log, Block wood, Block strippedWood, Block leaves, Block slab, String flag, RecipeOutput consumer) {
        this.verticalPlanks(aqBlocks.get("vertical_" + type + "_planks"), planks, flag, consumer);
        this.hedge(aqBlocks.get(type+"_hedge"), leaves, log, flag, consumer);
        this.verticalSlab(aqBlocks.get(type + "_vertical_slab"), slab, flag, consumer);
        this.carpet(aqBlocks.get(type + "_leaf_carpet"), leaves, flag, consumer);
        this.post(aqBlocks.get(type + "_post"), wood, flag, consumer);
        this.post(aqBlocks.get("stripped_" + type + "_post"), strippedWood, flag, consumer);
        //this.chest(blockMap.get(type+"_chest"), planks, flag, consumer);
        //this.chest(blockMap.get(type+"_trapped_chest"), planks, flag, consumer);
        this.hollowLog(aqBlocks.get("hollow_" + type + "_log"), log, flag, consumer);
        this.ladder(aqBlocks.get(type + "_ladder"), planks, flag, consumer);
        //this.bookshelf(blockMap.get(type + "_bookshelf"), planks, flag, consumer);
    }

    private void bookshelf(Block bookshelf, Block planks, String flag, RecipeOutput consumer) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf, 1)
                .define('A', planks)
                .define('B', Items.BOOK)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer
                        .withConditions(
                                zetaCond("variant_bookshelves"),
                                zetaCond(flag)
                        )
                );
        
    }

    void slab(Block slab, Block texture, RecipeOutput consumer) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void verticalSlab(Block vertical, Block slab, String flag, RecipeOutput consumer) {
        verticalSlabBuilder(vertical, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab))
            .save(consumer
                    .withConditions(
                            zetaCond("vertical_slabs"),
                            zetaCond(flag)
                    )
            );
        verticalSlabRevert(vertical, slab, consumer, flag);
    }

    void verticalSlab(Block vertical, Block slab, RecipeOutput consumer) {
        verticalSlabBuilder(vertical, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab))
                .save(consumer
                        .withConditions(
                                zetaCond("vertical_slabs")
                        )
                );
        verticalSlabRevert(vertical, slab, consumer);
    }

    protected static RecipeBuilder verticalSlabBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike, 3).define('#', ingredient)
                .pattern("#")
                .pattern("#")
                .pattern("#");
    }

    protected void carpet(ItemLike carpet, ItemLike leaf, String flag, RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, carpet, 3).define('A', leaf)
                .pattern("AA")
                .unlockedBy(getHasName(leaf), has(leaf))
                .save(consumer
                        .withConditions(
                                zetaCond("leaf_carpet"),
                                zetaCond(flag)
                        )
                );
    }

    void post(ItemLike post, ItemLike wood, String flag, RecipeOutput consumer) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, post, 8)
                .define('A', wood)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(wood), has(wood))
                                .save(consumer
                        .withConditions(
                                zetaCond("wooden_posts"),
                                zetaCond(flag)
                        )
                );

         
    }

    void verticalPlanks(ItemLike verticalPlanks, ItemLike planks, String flag, RecipeOutput consumer) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, verticalPlanks, 3)
                .define('A', planks)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer
                        .withConditions(
                                zetaCond("vertical_planks"),
                                zetaCond(flag)
                        )
                );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks)
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer
                    .withConditions(
                            zetaCond("vertical_planks"),
                            zetaCond(flag)
                    ), getItemName(verticalPlanks) + "_from_" + getItemName(planks)
        );
         
    }

    void chest(ItemLike chest, ItemLike planks, String flag, RecipeOutput consumer) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chest)
                .define('A', planks)
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA")
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer
                        .withConditions(
                                zetaCond("variant_chests"),
                                zetaCond(flag)
                        )
                );
    }

    void hollowLog(ItemLike hollowLog, ItemLike log, String flag, RecipeOutput consumer) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hollowLog, 4)
                .define('A', log)
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .unlockedBy(getHasName(log), has(log))
                .save(consumer
                        .withConditions(
                                zetaCond("hollow_logs"),
                                zetaCond(flag)
                        )
                );
    }

    void ladder(ItemLike ladder, ItemLike planks, String flag, RecipeOutput consumer){
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ladder, 4)
                .define('A', planks)
                .define('-', AetherItems.SKYROOT_STICK.get())
                .pattern("- -")
                .pattern("-A-")
                .pattern("- -")
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer
                        .withConditions(
                                zetaCond("variant_ladders"),
                                zetaCond(flag)
                        )
                );

         
    }

    void verticalSlabRevert(Block slab, Block reverted, RecipeOutput consumer, String flag) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab))
                .save(consumer
                        .withConditions(
                                zetaCond("vertical_slabs"),
                                zetaCond(flag)
                        ), getItemName(reverted) + "_from_" + getItemName(slab)
                );
    }

    void verticalSlabRevert(Block slab, Block reverted, RecipeOutput consumer) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab))
                .save(consumer
                        .withConditions(
                                zetaCond("vertical_slabs")
                        ), getItemName(reverted) + "_from_" + getItemName(slab)
                );
    }

    void hedge(ItemLike hedge, ItemLike leaves, TagKey<Item> stem, String flag, RecipeOutput consumer) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2)
                .define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A").unlockedBy(getHasName(leaves), has(leaves))
                .save(consumer
                        .withConditions(
                                zetaCond("hedges"),
                                zetaCond(flag)
                        )
                );

         
    }

    protected void hedge(ItemLike hedge, ItemLike leaves, ItemLike stem, String flag, RecipeOutput consumer) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2).define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A")
                .unlockedBy(getHasName(leaves), has(leaves))
                .save(consumer
                        .withConditions(
                                zetaCond("hedges"),
                                zetaCond(flag)
                        )
                );

         
    }

    void skyrootHedge(ItemLike hedge, ItemLike leaves, RecipeOutput consumer) {
        hedge(hedge, leaves, AetherTags.Items.SKYROOT_LOGS, "skyroot_quark_blocks", consumer);
    }
    protected static RecipeBuilder verticalSlabRevertBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, itemLike)
        .requires(ingredient);
    }

    void slabRevert(Block slab, Block reverted, RecipeOutput consumer) {
        slabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    protected static RecipeBuilder slabRevertBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike).define('A', ingredient).pattern("AA");
    }

    void stairs(Block stairs, Block texture, RecipeOutput consumer) {
        stairBuilder(stairs, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void wall(Block wall, Block texture, RecipeOutput consumer) {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    protected ResourceLocation name(String name) {
        return ResourceLocation.fromNamespaceAndPath(AscendedQuark.MODID, name);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, RecipeOutput consumer, FlagCondition... condition) {
        stonecuttingRecipe(item, ingredient, 1, consumer, condition);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, int count, RecipeOutput consumer, FlagCondition... condition) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, item, count)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(
                        consumer.withConditions(condition), name(getConversionRecipeName(item, ingredient) + "_stonecutting")
                );
    }

    public static FlagCondition zetaCond(String flag) {
        return new FlagCondition(flag, Optional.empty());
    }
}
