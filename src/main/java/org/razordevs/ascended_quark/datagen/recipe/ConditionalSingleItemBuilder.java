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

    public ConditionalSingleItemBuilder(RecipeCategory category, RecipeSerializer<?> serializer, Ingredient ingredient, ItemLike result, int count) {
        this.category = category;
        this.type = serializer;
        this.result = result.asItem();
        this.ingredient = ingredient;
        this.count = count;
    }

    public static ConditionalSingleItemBuilder stonecutting(Ingredient ingredient, RecipeCategory category, ItemLike result) {
        return new ConditionalSingleItemBuilder(category, RecipeSerializer.STONECUTTER, ingredient, result, 1);
    }

    public static ConditionalSingleItemBuilder stonecutting(Ingredient ingredient, RecipeCategory category, ItemLike result, int count) {
        return new ConditionalSingleItemBuilder(category, RecipeSerializer.STONECUTTER, ingredient, result, count);
    }

    public ConditionalSingleItemBuilder unlockedBy(String name, CriterionTriggerInstance triggerInstance) {
        this.advancement.addCriterion(name, triggerInstance);
        return this;
    }

    public ConditionalSingleItemBuilder group(@Nullable String group) {
        this.group = group;
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

    public void save(Consumer<FinishedRecipe> recipeConsumer, ResourceLocation location) {
        this.ensureValid(location);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(RequirementsStrategy.OR);
        recipeConsumer.accept(new ConditionalSingleItemBuilder.Result(location, this.type, this.group == null ? "" : this.group, this.ingredient, this.result, this.count, this.advancement, location.withPrefix("recipes/" + this.category.getFolderName() + "/"), condition));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
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

        public Result(ResourceLocation location, RecipeSerializer<?> serializer, String group, Ingredient ingredient, Item result, int count, Advancement.Builder advBuilder, ResourceLocation advId, List<Pair<ResourceLocation, String>> condition) {
            this.id = location;
            this.type = serializer;
            this.group = group;
            this.ingredient = ingredient;
            this.result = result;
            this.count = count;
            this.advancement = advBuilder;
            this.advancementId = advId;
            this.condition = condition;
        }

        public void serializeRecipeData(JsonObject jsonObject) {
            if (!this.group.isEmpty()) {
                jsonObject.addProperty("group", this.group);
            }

            jsonObject.add("ingredient", this.ingredient.toJson());
            jsonObject.addProperty("result", BuiltInRegistries.ITEM.getKey(this.result).toString());
            jsonObject.addProperty("count", this.count);

            if(!condition.isEmpty()) {
                if(condition.size() == 1) {
                    JsonObject conditions = new JsonObject();
                    conditions.addProperty("type", condition.get(0).getA().toString());
                    conditions.addProperty("flag", this.condition.get(0).getB());
                    jsonObject.add("conditions", conditions);
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
                    jsonObject.add("conditions", array);

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
