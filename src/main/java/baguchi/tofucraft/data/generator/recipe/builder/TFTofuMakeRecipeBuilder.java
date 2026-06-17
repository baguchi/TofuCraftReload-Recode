package baguchi.tofucraft.data.generator.recipe.builder;

import baguchi.tofucraft.recipe.TFTofuMakerRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class TFTofuMakeRecipeBuilder implements RecipeBuilder {
	;
	private final ItemStackTemplate stackResult;
	private final Ingredient ingredient;
	private final int cookTime;
	private final float exp;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	private TFTofuMakeRecipeBuilder(
			ItemStackTemplate result,
			Ingredient ingredient,
			float exp,
			int cookTime
	) {
		this.stackResult = result;
		this.ingredient = ingredient;
		this.exp = exp;
		this.cookTime = cookTime;
	}

	public static TFTofuMakeRecipeBuilder tofuMake(ItemStackTemplate stack, Ingredient ingredient, float exp, int cookTime) {
		return new TFTofuMakeRecipeBuilder(stack, ingredient, exp, cookTime);
	}

	public TFTofuMakeRecipeBuilder unlockedBy(String p_176792_, Criterion<?> p_300970_) {
		this.criteria.put(p_176792_, p_300970_);
		return this;
	}

	public TFTofuMakeRecipeBuilder group(@Nullable String p_176795_) {
		return this;
	}

	@Override
	public ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(this.stackResult);
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> p_379998_) {
		this.ensureValid(p_379998_);
		Advancement.Builder advancement$builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_379998_))
				.rewards(AdvancementRewards.Builder.recipe(p_379998_))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		TFTofuMakerRecipe recipe = new TFTofuMakerRecipe(new Recipe.CommonInfo(true), this.ingredient, this.stackResult, this.exp, this.cookTime);
		recipeOutput.accept(p_379998_, recipe, advancement$builder.build(p_379998_.identifier().withPrefix("recipes/tf_tofu_maker/")));
	}

	private void ensureValid(ResourceKey<Recipe<?>> p_126266_) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
		}
	}
}
