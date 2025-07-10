package baguchan.tofucraft.data.recipe.builder;

import baguchan.tofucraft.recipe.TFCraftingCategory;
import baguchan.tofucraft.registry.TofuRecipes;
import com.google.common.collect.Lists;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class TFShapelessRecipeBuilder implements RecipeBuilder {
	private final TFCraftingCategory category;
	private final Item result;
	private final int count;
	private final List<Ingredient> ingredients = Lists.newArrayList();
	private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
	@Nullable
	private String group;
	private final int tfNeed;

	public TFShapelessRecipeBuilder(TFCraftingCategory p_250837_, ItemLike p_251897_, int p_252227_, int tfNeed) {
		this.category = p_250837_;
		this.result = p_251897_.asItem();
		this.count = p_252227_;
		this.tfNeed = tfNeed;
	}

	public static TFShapelessRecipeBuilder shapeless(TFCraftingCategory p_250714_, ItemLike p_249659_) {
		return new TFShapelessRecipeBuilder(p_250714_, p_249659_, 1, 500);
	}

	public static TFShapelessRecipeBuilder shapeless(TFCraftingCategory p_252339_, ItemLike p_250836_, int p_249928_, int tf) {
		return new TFShapelessRecipeBuilder(p_252339_, p_250836_, p_249928_, tf);
	}

	public TFShapelessRecipeBuilder requires(TagKey<Item> p_206420_) {
		return this.requires(Ingredient.of(p_206420_));
	}

	public TFShapelessRecipeBuilder requires(ItemLike p_126210_) {
		return this.requires(p_126210_, 1);
	}

	public TFShapelessRecipeBuilder requires(ItemLike p_126212_, int p_126213_) {
		for (int i = 0; i < p_126213_; ++i) {
			this.requires(Ingredient.of(p_126212_));
		}

		return this;
	}

	public TFShapelessRecipeBuilder requires(Ingredient p_126185_) {
		return this.requires(p_126185_, 1);
	}

	public TFShapelessRecipeBuilder requires(Ingredient p_126187_, int p_126188_) {
		for (int i = 0; i < p_126188_; ++i) {
			this.ingredients.add(p_126187_);
		}

		return this;
	}

	public TFShapelessRecipeBuilder unlockedBy(String p_126197_, CriterionTriggerInstance p_126198_) {
		this.advancement.addCriterion(p_126197_, p_126198_);
		return this;
	}

	public TFShapelessRecipeBuilder group(@Nullable String p_126195_) {
		this.group = p_126195_;
		return this;
	}

	public Item getResult() {
		return this.result;
	}

	public void save(Consumer<FinishedRecipe> p_126205_, ResourceLocation p_126206_) {
		this.ensureValid(p_126206_);
		this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126206_)).rewards(AdvancementRewards.Builder.recipe(p_126206_)).requirements(RequirementsStrategy.OR);
		p_126205_.accept(new TFShapelessRecipeBuilder.Result(p_126206_, this.result, this.count, this.group == null ? "" : this.group, this.category, this.ingredients, this.advancement, p_126206_.withPrefix("recipes/" + this.category.getSerializedName() + "/"), this.tfNeed));
	}

	private void ensureValid(ResourceLocation p_126208_) {
		if (this.advancement.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + p_126208_);
		}
	}

	public static class Result implements FinishedRecipe {
		private final TFCraftingCategory category;
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<Ingredient> ingredients;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;
		private final int tfNeed;

		public Result(ResourceLocation p_249007_, Item p_248667_, int p_249014_, String p_248592_, TFCraftingCategory p_249485_, List<Ingredient> p_252312_, Advancement.Builder p_249909_, ResourceLocation p_249109_, int tfNeed) {
			this.category = p_249485_;
			this.id = p_249007_;
			this.result = p_248667_;
			this.count = p_249014_;
			this.group = p_248592_;
			this.ingredients = p_252312_;
			this.advancement = p_249909_;
			this.advancementId = p_249109_;
			this.tfNeed = tfNeed;
		}

		public void serializeRecipeData(JsonObject p_126230_) {
			p_126230_.addProperty("category", this.category.getSerializedName());
			if (!this.group.isEmpty()) {
				p_126230_.addProperty("group", this.group);
			}

			JsonArray jsonarray = new JsonArray();

			for (Ingredient ingredient : this.ingredients) {
				jsonarray.add(ingredient.toJson());
			}

			p_126230_.add("ingredients", jsonarray);
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject.addProperty("count", this.count);
			}

			p_126230_.add("result", jsonobject);
			p_126230_.addProperty("tf", this.tfNeed);
		}

		public RecipeSerializer<?> getType() {
			return TofuRecipes.RECIPE_TF_CRAFT_SHAPELESS.get();
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