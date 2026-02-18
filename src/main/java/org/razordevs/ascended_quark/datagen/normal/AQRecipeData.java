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
//import org.razordevs.ascended_quark.datagen.builders.recipe.ConditionalShapedRecipeBuilder;
//import org.razordevs.ascended_quark.datagen.builders.recipe.ConditionalShapelessRecipeBuilder;
//import org.razordevs.ascended_quark.datagen.builders.recipe.ConditionalSingleItemBuilder;
import org.violetmoon.quark.base.Quark;
import oshi.util.tuples.Pair;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AQRecipeData extends RecipeProvider {

    public static final ResourceLocation DEFAULT_FLAG = ResourceLocation.fromNamespaceAndPath(AscendedQuark.MODID, "flag");
    public static final ResourceLocation QUARK_FLAG = ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag");

    protected final HashMap<String, Item> itemMap;
    protected final HashMap<String, Block> blockMap;

    private static final String DA_WOOD = "deep_aether_wood";

    public AQRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(output, provider);
        this.itemMap = itemMap;
        this.blockMap = blockMap;
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        //haha
        skyrootHedge(blockMap.get("decorated_holiday_skyroot_hedge"), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), consumer);
    }

    protected void woodset(String type, Block planks, Block log, Block wood, Block strippedWood, Block leaves, Block slab, String flag, RecipeOutput consumer) {
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
        this.bookshelf(blockMap.get(type + "_bookshelf"), planks, flag, consumer);
    }

    private void bookshelf(Block bookshelf, Block planks, String flag, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf, 1)
                .define('A', planks)
                .define('B', Items.BOOK)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "variant_bookshelves")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);

         */
    }

    void slab(Block slab, Block texture, RecipeOutput consumer) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void verticalSlab(Block vertical, Block slab, String flag, RecipeOutput consumer) {
        verticalSlabBuilder(vertical, Ingredient.of(slab), flag).unlockedBy(getHasName(slab), has(slab)).save(consumer);
        verticalSlabRevert(vertical, slab, consumer, flag);
    }

    protected static RecipeBuilder verticalSlabBuilder(ItemLike itemLike, Ingredient ingredient, String flag) {
        /*
        return ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike, 3).define('#', ingredient)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag);
         }
         */
        return null;
    }

    protected void carpet(ItemLike carpet, ItemLike leaf, String flag, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, carpet, 3).define('A', leaf)
                .pattern("AA")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "leaf_carpet")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(leaf), has(leaf)).save(consumer);

         */
    }

    void post(ItemLike post, ItemLike wood, String flag, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, post, 8)
                .define('A', wood)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "wooden_posts")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(wood), has(wood)).save(consumer);

         */
    }

    void verticalPlanks(ItemLike verticalPlanks, ItemLike planks, String flag, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, verticalPlanks, 3)
                .define('A', planks)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "vertical_planks")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);

        ConditionalShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks)
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag).requires(verticalPlanks)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer, getItemName(verticalPlanks) + "_from_" + getItemName(planks));

         */
    }

    void chest(ItemLike chest, ItemLike planks, String flag, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chest)
                .define('A', planks)
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "variant_chests")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);

         */
    }

    void hollowLog(ItemLike hollowLog, ItemLike log, String flag, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hollowLog, 4)
                .define('A', log)
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "hollow_logs")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(log), has(log)).save(consumer);

         */
    }

    void ladder(ItemLike ladder, ItemLike planks, String flag, RecipeOutput consumer){
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ladder, 4)
                .define('A', planks)
                .define('-', AetherItems.SKYROOT_STICK.get())
                .pattern("- -")
                .pattern("-A-")
                .pattern("- -")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "variant_ladders")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);

         */
    }

    void verticalSlabRevert(Block slab, Block reverted, RecipeOutput consumer, String flag) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab), flag).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    void hedge(ItemLike hedge, ItemLike leaves, TagKey<Item> stem, ResourceLocation conditionLocation, String conditionName, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2)
                .define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A").unlockedBy(getHasName(leaves), has(leaves))
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "hedges")
                .condition(conditionLocation, conditionName)
                .save(consumer);

         */
    }

    protected void hedge(ItemLike hedge, ItemLike leaves, ItemLike stem, ResourceLocation conditionLocation, String conditionName, RecipeOutput consumer) {
        /*
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2).define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A")
                .condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "hedges")
                .condition(conditionLocation, conditionName)
                .unlockedBy(getHasName(leaves), has(leaves)).save(consumer);

         */
    }

    void skyrootHedge(ItemLike hedge, ItemLike leaves, RecipeOutput consumer) {
        hedge(hedge, leaves, AetherTags.Items.SKYROOT_LOGS, DEFAULT_FLAG, "skyroot_quark_blocks", consumer);
    }
    protected static RecipeBuilder verticalSlabRevertBuilder(ItemLike itemLike, Ingredient ingredient, String flag) {
//        return ConditionalShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, itemLike).condition(ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "flag"), "vertical_slabs")
//                .condition(DEFAULT_FLAG, flag).requires(ingredient);
        return null;
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

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, RecipeOutput consumer, Pair<ResourceLocation, String>... condition) {
        stonecuttingRecipe(item, ingredient, 1, consumer, condition);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, int count, RecipeOutput consumer, Pair<ResourceLocation, String>[] condition) {
        /*
        ConditionalSingleItemBuilder.stonecutting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, item, count)
                .condition(condition)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, name(getConversionRecipeName(item, ingredient) + "_stonecutting"));

         */
    }
}
