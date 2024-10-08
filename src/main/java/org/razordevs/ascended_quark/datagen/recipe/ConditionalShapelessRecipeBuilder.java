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

    public ConditionalShapelessRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this.category = category;
        this.result = result.asItem();
        this.count = count;
    }

    public static ConditionalShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result) {
        return new ConditionalShapelessRecipeBuilder(category, result, 1);
    }

    public static ConditionalShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count) {
        return new ConditionalShapelessRecipeBuilder(category, result, count);
    }

    public ConditionalShapelessRecipeBuilder requires(TagKey<Item> tagKey) {
        return this.requires(Ingredient.of(tagKey));
    }

    public ConditionalShapelessRecipeBuilder condition(ResourceLocation condition, String name) {
        this.condition.add(new Pair<>(condition, name));
        return this;
    }

    public ConditionalShapelessRecipeBuilder requires(ItemLike itemLike) {
        return this.requires(itemLike, 1);
    }

    public ConditionalShapelessRecipeBuilder requires(ItemLike itemLike, int count) {
        for(int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(itemLike));
        }

        return this;
    }

    public ConditionalShapelessRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public ConditionalShapelessRecipeBuilder requires(Ingredient ingredient, int count) {
        for(int i = 0; i < count; ++i) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    public ConditionalShapelessRecipeBuilder unlockedBy(String name, CriterionTriggerInstance triggerInstance) {
        this.advancement.addCriterion(name, triggerInstance);
        return this;
    }

    public ConditionalShapelessRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> recipeConsumer, ResourceLocation location) {
        this.ensureValid(location);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(RequirementsStrategy.OR);
        recipeConsumer.accept(new ConditionalShapelessRecipeBuilder.Result(location, this.result, this.count, this.group == null ? "" : this.group, determineBookCategory(this.category), this.ingredients, this.advancement, location.withPrefix("recipes/" + this.category.getFolderName() + "/"), condition));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
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

        public Result(ResourceLocation location, Item result, int count, String group, CraftingBookCategory bookCategory, List<Ingredient> ingredients, Advancement.Builder advBuilder, ResourceLocation advId, List<Pair<ResourceLocation, String>> condition) {
            super(bookCategory);
            this.id = location;
            this.result = result;
            this.count = count;
            this.group = group;
            this.ingredients = ingredients;
            this.advancement = advBuilder;
            this.advancementId = advId;
            this.condition = condition;
        }

        public void serializeRecipeData(JsonObject jsonObject) {
            super.serializeRecipeData(jsonObject);
            if (!this.group.isEmpty()) {
                jsonObject.addProperty("group", this.group);
            }

            JsonArray jsonarray = new JsonArray();

            for(Ingredient ingredient : this.ingredients) {
                jsonarray.add(ingredient.toJson());
            }

            jsonObject.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            jsonObject.add("result", jsonobject);

            if(!condition.isEmpty()) {
                if(condition.size() == 1) {
                    JsonObject conditions = new JsonObject();
                    conditions.addProperty("type", condition.get(0).getA().toString());
                    conditions.addProperty("flag", this.condition.get(0).getB());
                    jsonObject.add("conditions", conditions);
                }
                else {
                    JsonArray values = new JsonArray();


                    for (Pair<ResourceLocation, String> resourceLocationStringPair : condition) {
                        JsonObject conditions = new JsonObject();
                        conditions.addProperty("type", resourceLocationStringPair.getA().toString());
                        conditions.addProperty("flag", resourceLocationStringPair.getB());
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