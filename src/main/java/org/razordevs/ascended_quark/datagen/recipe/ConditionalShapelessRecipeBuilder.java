package org.razordevs.ascended_quark.datagen.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConditionalShapelessRecipeBuilder extends CraftingRecipeBuilder implements RecipeBuilder {
    private final List<Pair<ResourceLocation, String>> condition = new ArrayList<>();
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;

    public ConditionalShapelessRecipeBuilder(RecipeCategory p_250837_, ItemLike p_251897_, int p_252227_) {
        this.category = p_250837_;
        this.result = p_251897_.asItem();
        this.count = p_252227_;
    }

    public static ConditionalShapelessRecipeBuilder shapeless(RecipeCategory p_250714_, ItemLike p_249659_) {
        return new ConditionalShapelessRecipeBuilder(p_250714_, p_249659_, 1);
    }

    public static ConditionalShapelessRecipeBuilder shapeless(RecipeCategory p_252339_, ItemLike p_250836_, int p_249928_) {
        return new ConditionalShapelessRecipeBuilder(p_252339_, p_250836_, p_249928_);
    }

    public ConditionalShapelessRecipeBuilder requires(TagKey<Item> p_206420_) {
        return this.requires(Ingredient.of(p_206420_));
    }

    public ConditionalShapelessRecipeBuilder condition(ResourceLocation condition, String name) {
        this.condition.add(new Pair<>(condition, name));
        return this;
    }

    public ConditionalShapelessRecipeBuilder requires(ItemLike p_126210_) {
        return this.requires(p_126210_, 1);
    }

    public ConditionalShapelessRecipeBuilder requires(ItemLike p_126212_, int p_126213_) {
        for(int i = 0; i < p_126213_; ++i) {
            this.requires(Ingredient.of(p_126212_));
        }

        return this;
    }

    public ConditionalShapelessRecipeBuilder requires(Ingredient p_126185_) {
        return this.requires(p_126185_, 1);
    }

    public ConditionalShapelessRecipeBuilder requires(Ingredient p_126187_, int p_126188_) {
        for(int i = 0; i < p_126188_; ++i) {
            this.ingredients.add(p_126187_);
        }

        return this;
    }

    public ConditionalShapelessRecipeBuilder unlockedBy(String p_126197_, CriterionTriggerInstance p_126198_) {
        this.advancement.addCriterion(p_126197_, p_126198_);
        return this;
    }

    public ConditionalShapelessRecipeBuilder group(@Nullable String p_126195_) {
        this.group = p_126195_;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> p_126205_, ResourceLocation p_126206_) {
        this.ensureValid(p_126206_);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126206_)).rewards(AdvancementRewards.Builder.recipe(p_126206_)).requirements(RequirementsStrategy.OR);
        p_126205_.accept(new ConditionalShapelessRecipeBuilder.Result(p_126206_, this.result, this.count, this.group == null ? "" : this.group, determineBookCategory(this.category), this.ingredients, this.advancement, p_126206_.withPrefix("recipes/" + this.category.getFolderName() + "/"), condition));
    }

    private void ensureValid(ResourceLocation p_126208_) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126208_);
        }
    }

    public static class Result extends CraftingRecipeBuilder.CraftingResult {
        private final List<Pair<ResourceLocation, String>> condition;
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation p_249007_, Item p_248667_, int p_249014_, String p_248592_, CraftingBookCategory p_249485_, List<Ingredient> p_252312_, Advancement.Builder p_249909_, ResourceLocation p_249109_, List<Pair<ResourceLocation, String>> condition) {
            super(p_249485_);
            this.id = p_249007_;
            this.result = p_248667_;
            this.count = p_249014_;
            this.group = p_248592_;
            this.ingredients = p_252312_;
            this.advancement = p_249909_;
            this.advancementId = p_249109_;
            this.condition = condition;
        }

        public void serializeRecipeData(JsonObject p_126230_) {
            super.serializeRecipeData(p_126230_);
            if (!this.group.isEmpty()) {
                p_126230_.addProperty("group", this.group);
            }

            JsonArray jsonarray = new JsonArray();

            for(Ingredient ingredient : this.ingredients) {
                jsonarray.add(ingredient.toJson());
            }

            p_126230_.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            p_126230_.add("result", jsonobject);

            if(!condition.isEmpty()) {
                if(condition.size() == 1) {
                    JsonObject conditions = new JsonObject();
                    conditions.addProperty("type", condition.get(0).getA().toString());
                    conditions.addProperty("flag", this.condition.get(0).getB());
                    p_126230_.add("conditions", conditions);
                }
                else {
                    JsonArray values = new JsonArray();


                    for(int i = 0; i < condition.size(); i++) {
                        JsonObject conditions = new JsonObject();
                        conditions.addProperty("type", condition.get(i).getA().toString());
                        conditions.addProperty("flag", this.condition.get(i).getB());
                        values.add(conditions);
                    }

                    JsonArray array = new JsonArray();
                    JsonObject object = new JsonObject();
                    object.addProperty("type", "forge:and");
                    object.add("values", values);

                    array.add(object);
                    p_126230_.add("conditions", array);

                }
            }
        }

        public RecipeSerializer<?> getType() {
            return RecipeSerializer.SHAPELESS_RECIPE;
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}