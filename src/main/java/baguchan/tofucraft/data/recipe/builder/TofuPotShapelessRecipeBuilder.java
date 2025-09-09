package baguchan.tofucraft.data.recipe.builder;

import baguchan.tofucraft.recipe.FluidIngredient;
import baguchan.tofucraft.recipe.TofuPotCategory;
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

public class TofuPotShapelessRecipeBuilder implements RecipeBuilder {
	private final TofuPotCategory category;
	private final Item result;
	private final int count;
	private final List<Ingredient> ingredients = Lists.newArrayList();
	private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
	@Nullable
	private String group;
	final FluidIngredient ingredientFluid;
	private final int cookTime;
	private final float experience;

	public TofuPotShapelessRecipeBuilder(TofuPotCategory p_250837_, ItemLike p_251897_, int p_252227_, FluidIngredient ingredientFluid, int cookTime, float experience) {
		this.category = p_250837_;
		this.result = p_251897_.asItem();
		this.count = p_252227_;
		this.ingredientFluid = ingredientFluid;
		this.cookTime = cookTime;
		this.experience = experience;
	}

	public static TofuPotShapelessRecipeBuilder shapeless(TofuPotCategory p_250714_, ItemLike p_249659_) {
		return new TofuPotShapelessRecipeBuilder(p_250714_, p_249659_, 1, FluidIngredient.EMPTY, 300, 0.1F);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(TofuPotCategory p_250714_, FluidIngredient fluidIngredient, ItemLike p_249659_, int cookTime, float experience) {
		return new TofuPotShapelessRecipeBuilder(p_250714_, p_249659_, 1, fluidIngredient, cookTime, experience);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(TofuPotCategory p_250714_, FluidIngredient fluidIngredient, ItemLike p_249659_, int count, int cookTime, float experience) {
		return new TofuPotShapelessRecipeBuilder(p_250714_, p_249659_, count, fluidIngredient, cookTime, experience);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(TofuPotCategory p_250714_, ItemLike p_249659_, int cookTime, float experience) {
		return new TofuPotShapelessRecipeBuilder(p_250714_, p_249659_, 1, FluidIngredient.EMPTY, cookTime, experience);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(TofuPotCategory p_250714_, ItemLike p_249659_, int cookTime) {
		return new TofuPotShapelessRecipeBuilder(p_250714_, p_249659_, 1, FluidIngredient.EMPTY, cookTime, 0.1F);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(TofuPotCategory p_252339_, ItemLike p_250836_, int p_249928_, int cookTime, float experimence) {
		return new TofuPotShapelessRecipeBuilder(p_252339_, p_250836_, p_249928_, FluidIngredient.EMPTY, cookTime, experimence);
	}

	public TofuPotShapelessRecipeBuilder requires(TagKey<Item> p_206420_) {
		return this.requires(Ingredient.of(p_206420_));
	}

	public TofuPotShapelessRecipeBuilder requires(ItemLike p_126210_) {
		return this.requires(p_126210_, 1);
	}

	public TofuPotShapelessRecipeBuilder requires(ItemLike p_126212_, int p_126213_) {
		for (int i = 0; i < p_126213_; ++i) {
			this.requires(Ingredient.of(p_126212_));
		}

		return this;
	}

	public TofuPotShapelessRecipeBuilder requires(Ingredient p_126185_) {
		return this.requires(p_126185_, 1);
	}

	public TofuPotShapelessRecipeBuilder requires(Ingredient p_126187_, int p_126188_) {
		for (int i = 0; i < p_126188_; ++i) {
			this.ingredients.add(p_126187_);
		}

		return this;
	}

	public TofuPotShapelessRecipeBuilder unlockedBy(String p_126197_, CriterionTriggerInstance p_126198_) {
		this.advancement.addCriterion(p_126197_, p_126198_);
		return this;
	}

	public TofuPotShapelessRecipeBuilder group(@Nullable String p_126195_) {
		this.group = p_126195_;
		return this;
	}

	public Item getResult() {
		return this.result;
	}

	public void save(Consumer<FinishedRecipe> p_126205_, ResourceLocation p_126206_) {
		this.ensureValid(p_126206_);
		this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126206_)).rewards(AdvancementRewards.Builder.recipe(p_126206_)).requirements(RequirementsStrategy.OR);
		p_126205_.accept(new Result(p_126206_, this.result, this.count, this.group == null ? "" : this.group, this.category, this.ingredients, this.advancement, p_126206_.withPrefix("recipes/" + this.category.getSerializedName() + "/"), this.ingredientFluid, this.cookTime, this.experience));
	}

	private void ensureValid(ResourceLocation p_126208_) {
		if (this.advancement.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + p_126208_);
		}
	}

	public static class Result implements FinishedRecipe {
		private final TofuPotCategory category;
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<Ingredient> ingredients;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;
		final FluidIngredient ingredientFluid;
		private final int cookTime;
		private final float experience;

		public Result(ResourceLocation p_249007_, Item p_248667_, int p_249014_, String p_248592_, TofuPotCategory p_249485_, List<Ingredient> p_252312_, Advancement.Builder p_249909_, ResourceLocation p_249109_, FluidIngredient ingredientFluid, int cookTime, float experience) {
			this.category = p_249485_;
			this.id = p_249007_;
			this.result = p_248667_;
			this.count = p_249014_;
			this.group = p_248592_;
			this.ingredients = p_252312_;
			this.advancement = p_249909_;
			this.advancementId = p_249109_;
			this.ingredientFluid = ingredientFluid;
			this.cookTime = cookTime;
			this.experience = experience;
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
			p_126230_.add("fluid", this.ingredientFluid.toJson());
			p_126230_.addProperty("cook_time", this.cookTime);
			p_126230_.addProperty("exp", this.experience);
		}

		public RecipeSerializer<?> getType() {
			return TofuRecipes.RECIPE_TOFU_POT_SHAPELESS.get();
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