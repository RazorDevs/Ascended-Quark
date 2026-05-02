package org.razordevs.ascended_quark.datagen.normal;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.nitrogen.data.providers.NitrogenRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.zeta.config.FlagCondition;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AQRecipeData extends NitrogenRecipeProvider {
    protected final HashMap<String, Item> aqItems;
    protected final HashMap<String, Block> aqBlocks;

    public AQRecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, HashMap<String, Item> aqItems, HashMap<String, Block> aqBlocks) {
        super(output, provider, AscendedQuark.MODID);
        this.aqItems = aqItems;
        this.aqBlocks = aqBlocks;
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        this.woodset("skyroot", AetherBlocks.SKYROOT_PLANKS.get(),
                AetherBlocks.SKYROOT_LOG.get(), AetherBlocks.SKYROOT_WOOD.get(),
                AetherBlocks.STRIPPED_SKYROOT_WOOD.get(), AetherBlocks.SKYROOT_LEAVES.get(),
                AetherBlocks.SKYROOT_SLAB.get(), "skyroot_quark_blocks", consumer);

        this.skyrootHedge(aqBlocks.get("crystal_skyroot_hedge"), AetherBlocks.CRYSTAL_LEAVES.get(), consumer);
        this.skyrootHedge(aqBlocks.get("crystal_fruit_skyroot_hedge"), AetherBlocks.CRYSTAL_FRUIT_LEAVES.get(), consumer);
        this.skyrootHedge(aqBlocks.get("holiday_skyroot_hedge"), AetherBlocks.HOLIDAY_LEAVES.get(), consumer);
        this.skyrootHedge(aqBlocks.get("decorated_holiday_skyroot_hedge"), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), consumer);
        this.skyrootHedge(aqBlocks.get("golden_skyroot_hedge"), AetherBlocks.GOLDEN_OAK_LEAVES.get(), consumer);

        this.carpet(aqBlocks.get("crystal_leaf_carpet"), AetherBlocks.CRYSTAL_LEAVES, "skyroot_quark_blocks", consumer);
        this.carpet(aqBlocks.get("crystal_fruit_leaf_carpet"), AetherBlocks.CRYSTAL_FRUIT_LEAVES, "skyroot_quark_blocks", consumer);
        this.carpet(aqBlocks.get("holiday_leaf_carpet"), AetherBlocks.HOLIDAY_LEAVES.get(), "skyroot_quark_blocks", consumer);
        this.carpet(aqBlocks.get("decorated_holiday_leaf_carpet"), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), "skyroot_quark_blocks", consumer);
        this.carpet(aqBlocks.get("golden_oak_leaf_carpet"), AetherBlocks.GOLDEN_OAK_LEAVES.get(), "skyroot_quark_blocks", consumer);

        // VERTICAL SLABS
        // Why didn't I do it like this before?
        aqBlocks.keySet().stream()
                .filter(s -> s.contains("vertical_slab") && !s.contains("skyroot"))
                .forEach(
                (key) -> {
                    String stripKey = key.replace("_vertical", "");
                    var res = AetherBlocks.BLOCKS.getRegistry().get().get(
                            ResourceLocation.fromNamespaceAndPath(Aether.MODID, stripKey)
                    );
                    this.verticalSlab(aqBlocks.get(key), !res.equals(Blocks.AIR) ? res : aqBlocks.get(stripKey), consumer);
                    //this.stonecuttingRecipe(aqBlocks.get(key), !cut.equals(Blocks.AIR) ? cut : aqItems.get(cutKey), 2, consumer, zetaFlag("vertical_slabs"));
                }
        );

        aqBlocks.keySet().stream()
                .filter(s -> s.contains("_stone") || s.contains("_brick"))
                .forEach(
                        (key) -> {
                            var res = AetherBlocks.BLOCKS.getRegistry().get().get(
                                    ResourceLocation.fromNamespaceAndPath(Aether.MODID, key)
                            );
                            this.stonecuttingRecipe(aqBlocks.get(key.replace("_stone", "_vertical_slab")),
                                    !res.equals(Blocks.AIR) ? res : aqBlocks.get(res), 2, consumer, zetaFlag("vertical_slabs"));
                        }
                );

        this.fullBlock(
                RecipeCategory.BUILDING_BLOCKS, aqBlocks.get("skyroot_stick_block"), 1, AetherItems.SKYROOT_STICK.get()
        ).save(consumer.withConditions(
                        zetaFlag("stick_block"))
        );

        this.fullBlock(
                RecipeCategory.BUILDING_BLOCKS, aqBlocks.get("blue_berry_crate"), 1, AetherItems.BLUE_BERRY.get()
        ).save(consumer.withConditions(
                        zetaFlag("blue_berry_crate"))
        );
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

    private ShapedRecipeBuilder fullBlock(RecipeCategory cat, ItemLike result, int count, ItemLike ing) {
        return ShapedRecipeBuilder.shaped(cat, result, count)
                .define('#', ing)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(ing), has(ing));
    }

    private ShapedRecipeBuilder fullBlock(RecipeCategory cat, ItemLike result, int count, ItemLike ing, int ingCount, RecipeOutput consumer) {
        this.fullBlockRevert(cat, ing, ingCount, result)
                .save(consumer);
        return this.fullBlock(cat, result, count, ing);
    }

    private ShapelessRecipeBuilder fullBlockRevert(RecipeCategory cat, ItemLike result, int count, ItemLike ing) {
        return ShapelessRecipeBuilder.shapeless(cat, result, count)
                .requires(ing);
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
                                zetaFlag("variant_bookshelves"),
                                zetaFlag(flag)
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
                            zetaFlag("vertical_slabs"),
                            zetaFlag(flag)
                    )
            );
        verticalSlabRevert(vertical, slab, consumer, flag);
    }

    void verticalSlab(Block vertical, Block slab, RecipeOutput consumer) {
        verticalSlabBuilder(vertical, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab))
                .save(consumer
                        .withConditions(
                                zetaFlag("vertical_slabs")
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
                                zetaFlag("leaf_carpet"),
                                zetaFlag(flag)
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
                                zetaFlag("wooden_posts"),
                                zetaFlag(flag)
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
                                zetaFlag("vertical_planks"),
                                zetaFlag(flag)
                        )
                );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks)
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer
                    .withConditions(
                            zetaFlag("vertical_planks"),
                            zetaFlag(flag)
                    ), name(getItemName(verticalPlanks) + "_from_" + getItemName(planks))
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
                                zetaFlag("variant_chests"),
                                zetaFlag(flag)
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
                                zetaFlag("hollow_logs"),
                                zetaFlag(flag)
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
                                zetaFlag("variant_ladders"),
                                zetaFlag(flag)
                        )
                );
    }

    void verticalSlabRevert(Block slab, Block reverted, RecipeOutput consumer, String flag) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab))
                .save(consumer
                        .withConditions(
                                zetaFlag("vertical_slabs"),
                                zetaFlag(flag)
                        ), name(getItemName(reverted) + "_from_" + getItemName(slab))
                );
    }

    void verticalSlabRevert(Block slab, Block reverted, RecipeOutput consumer) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab))
                .save(consumer
                        .withConditions(
                                zetaFlag("vertical_slabs")
                        ), name(getItemName(reverted) + "_from_" + getItemName(slab))
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
                                zetaFlag("hedges"),
                                zetaFlag(flag)
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
                                zetaFlag("hedges"),
                                zetaFlag(flag)
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

    public static FlagCondition zetaFlag(String flag) {
        return new FlagCondition(flag, Optional.empty());
    }
}
