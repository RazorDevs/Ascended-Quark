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
    private List<Pair<ResourceLocation, String>> condition = new ArrayList<>();

    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;
    private boolean showNotification = true;

    public ConditionalShapedRecipeBuilder(RecipeCategory p_249996_, ItemLike p_251475_, int p_248948_) {
        this.category = p_249996_;
        this.result = p_251475_.asItem();
        this.count = p_248948_;
    }

    public static ConditionalShapedRecipeBuilder shaped(RecipeCategory p_250853_, ItemLike p_249747_) {
        return shaped(p_250853_, p_249747_, 1);
    }

    public static ConditionalShapedRecipeBuilder shaped(RecipeCategory p_251325_, ItemLike p_250636_, int p_249081_) {
        return new ConditionalShapedRecipeBuilder(p_251325_, p_250636_, p_249081_);
    }

    public ConditionalShapedRecipeBuilder define(Character p_206417_, TagKey<Item> p_206418_) {
        return this.define(p_206417_, Ingredient.of(p_206418_));
    }

    public ConditionalShapedRecipeBuilder define(Character p_126128_, ItemLike p_126129_) {
        return this.define(p_126128_, Ingredient.of(new ItemLike[]{p_126129_}));
    }

    public ConditionalShapedRecipeBuilder define(Character p_126125_, Ingredient p_126126_) {
        if (this.key.containsKey(p_126125_)) {
            throw new IllegalArgumentException("Symbol '" + p_126125_ + "' is already defined!");
        } else if (p_126125_ == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(p_126125_, p_126126_);
            return this;
        }
    }

    public ConditionalShapedRecipeBuilder pattern(String p_126131_) {
        if (!this.rows.isEmpty() && p_126131_.length() != ((String)this.rows.get(0)).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(p_126131_);
            return this;
        }
    }

    public ConditionalShapedRecipeBuilder unlockedBy(String p_126133_, CriterionTriggerInstance p_126134_) {
        this.advancement.addCriterion(p_126133_, p_126134_);
        return this;
    }

    public ConditionalShapedRecipeBuilder group(@Nullable String p_126146_) {
        this.group = p_126146_;
        return this;
    }

    public ConditionalShapedRecipeBuilder showNotification(boolean p_273326_) {
        this.showNotification = p_273326_;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    private void ensureValid(ResourceLocation p_126144_) {
        if (this.rows.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + p_126144_ + "!");
        } else {
            Set<Character> $$1 = Sets.newHashSet(this.key.keySet());
            $$1.remove(' ');
            Iterator var3 = this.rows.iterator();

            while(var3.hasNext()) {
                String $$2 = (String)var3.next();

                for(int $$3 = 0; $$3 < $$2.length(); ++$$3) {
                    char $$4 = $$2.charAt($$3);
                    if (!this.key.containsKey($$4) && $$4 != ' ') {
                        throw new IllegalStateException("Pattern in recipe " + p_126144_ + " uses undefined symbol '" + $$4 + "'");
                    }

                    $$1.remove($$4);
                }
            }

            if (!$$1.isEmpty()) {
                throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + p_126144_);
            } else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
                throw new IllegalStateException("Shaped recipe " + p_126144_ + " only takes in a single item - should it be a shapeless recipe instead?");
            } else if (this.advancement.getCriteria().isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + p_126144_);
            }
        }
    }


    public ConditionalShapedRecipeBuilder condition(ResourceLocation condition, String name) {
        this.condition.add(new Pair<>(condition, name));
        return this;
    }

    @Override
    public void save(Consumer<FinishedRecipe> p_126141_, ResourceLocation p_126142_) {
        this.ensureValid(p_126142_);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126142_)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(p_126142_)).requirements(RequirementsStrategy.OR);
        p_126141_.accept(new ConditionalShapedRecipeBuilder.Result(p_126142_, this.result, this.count, this.group == null ? "" : this.group, determineBookCategory(this.category), this.rows, this.key, this.advancement, p_126142_.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.showNotification, this.condition));
    }

    public static class Result extends CraftingRecipeBuilder.CraftingResult {
        @NotNull
        private final List<Pair<ResourceLocation, String>> condition; //= Maps.newLinkedHashMap();
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<String> pattern;
        private final Map<Character, Ingredient> key;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final boolean showNotification;

        public Result(ResourceLocation p_273548_, Item p_273530_, int p_272738_, String p_273549_, CraftingBookCategory p_273500_, List<String> p_273744_, Map<Character, Ingredient> p_272991_, Advancement.Builder p_273260_, ResourceLocation p_273106_, boolean p_272862_, List<Pair<ResourceLocation, String>> condition) {
            super(p_273500_);
            this.condition = condition;
            this.id = p_273548_;
            this.result = p_273530_;
            this.count = p_272738_;
            this.group = p_273549_;
            this.pattern = p_273744_;
            this.key = p_272991_;
            this.advancement = p_273260_;
            this.advancementId = p_273106_;
            this.showNotification = p_272862_;
        }

        @Override
        public void serializeRecipeData(JsonObject p_126167_) {
            super.serializeRecipeData(p_126167_);
            if (!this.group.isEmpty()) {
                p_126167_.addProperty("group", this.group);
            }

            JsonArray $$1 = new JsonArray();

            for (String $$2 : this.pattern) {
                $$1.add($$2);
            }

            p_126167_.add("pattern", $$1);
            JsonObject $$3 = new JsonObject();

            for (Map.Entry<Character, Ingredient> characterIngredientEntry : this.key.entrySet()) {
                $$3.add(String.valueOf(characterIngredientEntry.getKey()), characterIngredientEntry.getValue().toJson());
            }

            p_126167_.add("key", $$3);
            JsonObject $$5 = new JsonObject();
            $$5.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                $$5.addProperty("count", this.count);
            }

            p_126167_.add("result", $$5);
            p_126167_.addProperty("show_notification", this.showNotification);

            if(!condition.isEmpty()) {
                if(condition.size() == 1) {
                    JsonObject conditions = new JsonObject();
                    conditions.addProperty("type", condition.get(0).getA().toString());
                    conditions.addProperty("flag", this.condition.get(0).getB());
                    p_126167_.add("conditions", conditions);
                }
                else {
                    p_126167_.addProperty("type", "forge:and");

                    JsonArray values = new JsonArray();


                    for(int i = 0; i < condition.size(); i++) {
                        JsonObject conditions = new JsonObject();
                        conditions.addProperty("type", condition.get(i).getA().toString());
                        conditions.addProperty("flag", this.condition.get(i).getB());
                        values.add(conditions);
                    }

                    p_126167_.add("values", values);

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
