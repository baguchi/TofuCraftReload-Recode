package baguchi.tofucraft.data.generator.recipe.builder;

import baguchi.tofucraft.recipe.HardenRecipe;
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

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class HardenRecipeBuilder implements RecipeBuilder {
	private final ItemStackTemplate stackResult; // Neo: add stack result support
	private final Ingredient ingredient;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	private HardenRecipeBuilder(
			ItemStackTemplate result,
			Ingredient fluidStack
	) {
		this.stackResult = result;
		this.ingredient = fluidStack;
	}

	public static HardenRecipeBuilder harden(ItemStackTemplate stack, Ingredient ingredient) {
		return new HardenRecipeBuilder(stack, ingredient);
	}

	public HardenRecipeBuilder unlockedBy(String p_176792_, Criterion<?> p_300970_) {
		this.criteria.put(p_176792_, p_300970_);
		return this;
	}

	public HardenRecipeBuilder group(@Nullable String p_176795_) {
		return this;
	}

	@Override
	public ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(this.stackResult);
	}

	public Item getResult() {
		return this.stackResult.create().getItem();
	}

	@Override
	public void save(RecipeOutput p_301266_, ResourceKey<Recipe<?>> p_126264_) {
		this.ensureValid(p_126264_);
		Advancement.Builder advancement$builder = p_301266_.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126264_))
				.rewards(AdvancementRewards.Builder.recipe(p_126264_))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		HardenRecipe recipe = new HardenRecipe(this.ingredient, this.stackResult);
		p_301266_.accept(p_126264_, recipe, advancement$builder.build(p_126264_.identifier().withPrefix("recipes/harden/")));
	}

	private void ensureValid(ResourceKey<Recipe<?>> p_126266_) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
		}
	}
}
