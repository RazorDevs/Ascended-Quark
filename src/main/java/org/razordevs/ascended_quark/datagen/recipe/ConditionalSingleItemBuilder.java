package org.razordevs.ascended_quark.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConditionalSingleItemBuilder implements RecipeBuilder {
    private final List<Pair<ResourceLocation, String>> condition = new ArrayList<>();
    private final RecipeCategory category;
    private final Item result;
    private final Ingredient ingredient;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;
    private final RecipeSerializer<?> type;

    public ConditionalSingleItemBuilder(RecipeCategory p_251425_, RecipeSerializer<?> p_249762_, Ingredient p_251221_, ItemLike p_251302_, int p_250964_) {
        this.category = p_251425_;
        this.type = p_249762_;
        this.result = p_251302_.asItem();
        this.ingredient = p_251221_;
        this.count = p_250964_;
    }

    public static ConditionalSingleItemBuilder stonecutting(Ingredient p_248596_, RecipeCategory p_250503_, ItemLike p_250269_) {
        return new ConditionalSingleItemBuilder(p_250503_, RecipeSerializer.STONECUTTER, p_248596_, p_250269_, 1);
    }

    public static ConditionalSingleItemBuilder stonecutting(Ingredient p_251375_, RecipeCategory p_248984_, ItemLike p_250105_, int p_249506_) {
        return new ConditionalSingleItemBuilder(p_248984_, RecipeSerializer.STONECUTTER, p_251375_, p_250105_, p_249506_);
    }

    public ConditionalSingleItemBuilder unlockedBy(String p_176810_, CriterionTriggerInstance p_176811_) {
        this.advancement.addCriterion(p_176810_, p_176811_);
        return this;
    }

    public ConditionalSingleItemBuilder group(@Nullable String p_176808_) {
        this.group = p_176808_;
        return this;
    }

    public ConditionalSingleItemBuilder condition(ResourceLocation condition, String name) {
        this.condition.add(new Pair<>(condition, name));
        return this;
    }

    public ConditionalSingleItemBuilder condition(Pair<ResourceLocation, String>... condition) {
        this.condition.addAll(List.of(condition));
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> p_126327_, ResourceLocation p_126328_) {
        this.ensureValid(p_126328_);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126328_)).rewards(AdvancementRewards.Builder.recipe(p_126328_)).requirements(RequirementsStrategy.OR);
        p_126327_.accept(new ConditionalSingleItemBuilder.Result(p_126328_, this.type, this.group == null ? "" : this.group, this.ingredient, this.result, this.count, this.advancement, p_126328_.withPrefix("recipes/" + this.category.getFolderName() + "/"), condition));
    }

    private void ensureValid(ResourceLocation p_126330_) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126330_);
        }
    }

    public static class Result implements FinishedRecipe {
        @NotNull
        private final List<Pair<ResourceLocation, String>> condition;
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<?> type;

        public Result(ResourceLocation p_126340_, RecipeSerializer<?> p_126341_, String p_126342_, Ingredient p_126343_, Item p_126344_, int p_126345_, Advancement.Builder p_126346_, ResourceLocation p_126347_, List<Pair<ResourceLocation, String>> condition) {
            this.id = p_126340_;
            this.type = p_126341_;
            this.group = p_126342_;
            this.ingredient = p_126343_;
            this.result = p_126344_;
            this.count = p_126345_;
            this.advancement = p_126346_;
            this.advancementId = p_126347_;
            this.condition = condition;
        }

        public void serializeRecipeData(JsonObject p_126349_) {
            if (!this.group.isEmpty()) {
                p_126349_.addProperty("group", this.group);
            }

            p_126349_.add("ingredient", this.ingredient.toJson());
            p_126349_.addProperty("result", BuiltInRegistries.ITEM.getKey(this.result).toString());
            p_126349_.addProperty("count", this.count);

            if(!condition.isEmpty()) {
                if(condition.size() == 1) {
                    JsonObject conditions = new JsonObject();
                    conditions.addProperty("type", condition.get(0).getA().toString());
                    conditions.addProperty("flag", this.condition.get(0).getB());
                    p_126349_.add("conditions", conditions);
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
                    p_126349_.add("conditions", array);

                }
            }
        }

        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return this.type;
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
