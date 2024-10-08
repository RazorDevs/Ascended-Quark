package org.razordevs.ascended_quark.datagen.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
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
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public class ConditionalShapedRecipeBuilder extends CraftingRecipeBuilder implements RecipeBuilder {
    private final List<Pair<ResourceLocation, String>> condition = new ArrayList<>();
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;
    private boolean showNotification = true;

    public ConditionalShapedRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this.category = category;
        this.result = result.asItem();
        this.count = count;
    }

    public static ConditionalShapedRecipeBuilder shaped(RecipeCategory category, ItemLike itemLike) {
        return shaped(category, itemLike, 1);
    }

    public static ConditionalShapedRecipeBuilder shaped(RecipeCategory category, ItemLike itemLike, int count) {
        return new ConditionalShapedRecipeBuilder(category, itemLike, count);
    }

    public ConditionalShapedRecipeBuilder define(Character character, TagKey<Item> tagKey) {
        return this.define(character, Ingredient.of(tagKey));
    }

    public ConditionalShapedRecipeBuilder define(Character character, ItemLike itemLike) {
        return this.define(character, Ingredient.of(itemLike));
    }

    public ConditionalShapedRecipeBuilder define(Character character, Ingredient ingredient) {
        if (this.key.containsKey(character)) {
            throw new IllegalArgumentException("Symbol '" + character + "' is already defined!");
        } else if (character == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(character, ingredient);
            return this;
        }
    }

    public ConditionalShapedRecipeBuilder pattern(String pattern) {
        if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(pattern);
            return this;
        }
    }

    public ConditionalShapedRecipeBuilder unlockedBy(String name, CriterionTriggerInstance triggerInstance) {
        this.advancement.addCriterion(name, triggerInstance);
        return this;
    }

    public ConditionalShapedRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    public ConditionalShapedRecipeBuilder showNotification(boolean b) {
        this.showNotification = b;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    private void ensureValid(ResourceLocation location) {
        if (this.rows.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + location + "!");
        } else {
            Set<Character> $$1 = Sets.newHashSet(this.key.keySet());
            $$1.remove(' ');
            Iterator var3 = this.rows.iterator();

            while(var3.hasNext()) {
                String $$2 = (String)var3.next();

                for(int $$3 = 0; $$3 < $$2.length(); ++$$3) {
                    char $$4 = $$2.charAt($$3);
                    if (!this.key.containsKey($$4) && $$4 != ' ') {
                        throw new IllegalStateException("Pattern in recipe " + location + " uses undefined symbol '" + $$4 + "'");
                    }

                    $$1.remove($$4);
                }
            }

            if (!$$1.isEmpty()) {
                throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + location);
            } else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
                throw new IllegalStateException("Shaped recipe " + location + " only takes in a single item - should it be a shapeless recipe instead?");
            } else if (this.advancement.getCriteria().isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + location);
            }
        }
    }


    public ConditionalShapedRecipeBuilder condition(ResourceLocation condition, String name) {
        this.condition.add(new Pair<>(condition, name));
        return this;
    }

    @Override
    public void save(Consumer<FinishedRecipe> recipeConsumer, ResourceLocation location) {
        this.ensureValid(location);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(location)).requirements(RequirementsStrategy.OR);
        recipeConsumer.accept(new ConditionalShapedRecipeBuilder.Result(location, this.result, this.count, this.group == null ? "" : this.group, determineBookCategory(this.category), this.rows, this.key, this.advancement, location.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.showNotification, this.condition));
    }

    public static class Result extends CraftingRecipeBuilder.CraftingResult {
        @NotNull
        private final List<Pair<ResourceLocation, String>> condition;
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<String> pattern;
        private final Map<Character, Ingredient> key;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final boolean showNotification;

        public Result(ResourceLocation location, Item item, int count, String group, CraftingBookCategory bookCategory, List<String> pattern, Map<Character, Ingredient> key, Advancement.Builder builder, ResourceLocation advLocation, boolean notify, List<Pair<ResourceLocation, String>> condition) {
            super(bookCategory);
            this.condition = condition;
            this.id = location;
            this.result = item;
            this.count = count;
            this.group = group;
            this.pattern = pattern;
            this.key = key;
            this.advancement = builder;
            this.advancementId = advLocation;
            this.showNotification = notify;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            super.serializeRecipeData(jsonObject);
            if (!this.group.isEmpty()) {
                jsonObject.addProperty("group", this.group);
            }

            JsonArray $$1 = new JsonArray();

            for (String $$2 : this.pattern) {
                $$1.add($$2);
            }

            jsonObject.add("pattern", $$1);
            JsonObject $$3 = new JsonObject();

            for (Map.Entry<Character, Ingredient> characterIngredientEntry : this.key.entrySet()) {
                $$3.add(String.valueOf(characterIngredientEntry.getKey()), characterIngredientEntry.getValue().toJson());
            }

            jsonObject.add("key", $$3);
            JsonObject $$5 = new JsonObject();
            $$5.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                $$5.addProperty("count", this.count);
            }

            jsonObject.add("result", $$5);
            jsonObject.addProperty("show_notification", this.showNotification);

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

        public RecipeSerializer<?> getType() {
            return RecipeSerializer.SHAPED_RECIPE;
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
