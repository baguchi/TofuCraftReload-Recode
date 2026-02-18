package baguchi.tofucraft.data.generator.recipe.builder;

import baguchi.tofucraft.recipe.BitternRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.FluidStackTemplate;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class BitternRecipeBuilder implements RecipeBuilder {
	;
	private final ItemStackTemplate stackResult;
	private final FluidStackTemplate ingredient;
	private final Ingredient extraIngredient;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	private BitternRecipeBuilder(
			ItemStackTemplate result,
			FluidStackTemplate fluidStack,
			Ingredient extraIngredient
	) {
		this.stackResult = result;
		this.ingredient = fluidStack;
		this.extraIngredient = extraIngredient;
	}

	public static BitternRecipeBuilder bittern(ItemStackTemplate stack, FluidStackTemplate fluidStack, Ingredient extraFluid) {
		return new BitternRecipeBuilder(stack, fluidStack, extraFluid);
	}

	public BitternRecipeBuilder unlockedBy(String p_176792_, Criterion<?> p_300970_) {
		this.criteria.put(p_176792_, p_300970_);
		return this;
	}

	public BitternRecipeBuilder group(@Nullable String p_176795_) {
		return this;
	}

	@Override
	public ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(this.stackResult);
	}

	public Item getResult() {
		return this.stackResult.item().value();
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> p_379998_) {
		this.ensureValid(p_379998_);
		Advancement.Builder advancement$builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_379998_))
				.rewards(AdvancementRewards.Builder.recipe(p_379998_))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		BitternRecipe recipe = new BitternRecipe(this.ingredient, this.extraIngredient, this.stackResult);
		recipeOutput.accept(p_379998_, recipe, advancement$builder.build(p_379998_.identifier().withPrefix("recipes/bittern/")));

	}

	private void ensureValid(ResourceKey<Recipe<?>> p_126266_) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
		}
	}
}
