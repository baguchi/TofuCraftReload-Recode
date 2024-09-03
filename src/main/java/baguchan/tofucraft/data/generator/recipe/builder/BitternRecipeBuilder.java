package baguchan.tofucraft.data.generator.recipe.builder;

import baguchan.tofucraft.recipe.BitternRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class BitternRecipeBuilder implements RecipeBuilder {
	;
	private final ItemStack stackResult;
	private final FluidStack ingredient;
	private final FluidStack extraIngredient;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	private BitternRecipeBuilder(
			ItemStack result,
			FluidStack fluidStack,
			FluidStack extraIngredient
	) {
		this.stackResult = result;
		this.ingredient = fluidStack;
		this.extraIngredient = extraIngredient;
	}

	public static BitternRecipeBuilder bittern(ItemStack stack, FluidStack fluidStack, FluidStack extraFluid) {
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
	public Item getResult() {
		return this.stackResult.getItem();
	}

	@Override
	public void save(RecipeOutput p_301266_, ResourceLocation p_126264_) {
		this.ensureValid(p_126264_);
		Advancement.Builder advancement$builder = p_301266_.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126264_))
				.rewards(AdvancementRewards.Builder.recipe(p_126264_))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		BitternRecipe recipe = new BitternRecipe(this.ingredient, this.extraIngredient, this.stackResult);
		p_301266_.accept(p_126264_, recipe, advancement$builder.build(p_126264_.withPrefix("recipes/bittern/")));
	}

	private void ensureValid(ResourceLocation p_126266_) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
		}
	}
}
