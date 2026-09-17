package baguchi.tofucraft.data.generator.recipe.builder;

import baguchi.tofucraft.recipe.HardenRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger;
import net.minecraft.core.registries.Registries;
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
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
		this.ensureValid(id);
		Advancement.Builder advancement$builder = output.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(output.lookup(Registries.RECIPE).getOrThrow(id)))
				.rewards(AdvancementRewards.Builder.recipe(id))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		HardenRecipe recipe = new HardenRecipe(this.ingredient, this.stackResult);
		output.accept(id, recipe, advancement$builder.build(id.identifier().withPrefix("recipes/harden/")));
	}

	private void ensureValid(ResourceKey<Recipe<?>> p_126266_) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
		}
	}
}
