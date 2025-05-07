package baguchi.tofucraft.data.generator.recipe.builder;

import baguchi.tofucraft.recipe.TofuPotCategory;
import baguchi.tofucraft.recipe.TofuPotShapelessRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class TofuPotShapelessRecipeBuilder implements RecipeBuilder {
	private final HolderGetter<Item> items;
	private final TofuPotCategory category;
	private final Item result;
	private final int count;
	private final ItemStack resultStack; // Neo: add stack result support
	private final NonNullList<Ingredient> ingredients = NonNullList.create();
	private final Optional<SizedFluidIngredient> fluidIngredient;
	private final int cookTime;
	private final float experience;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	@Nullable
	private String group;

	public TofuPotShapelessRecipeBuilder(HolderGetter<Item> items, TofuPotCategory category, ItemLike result, Optional<SizedFluidIngredient> fluidIngredient, int count, int cookTime, float experience) {
		this(items, category, new ItemStack(result, count), fluidIngredient, cookTime, experience);
	}

	public TofuPotShapelessRecipeBuilder(HolderGetter<Item> items, TofuPotCategory p_250837_, ItemStack result, Optional<SizedFluidIngredient> fluidIngredient, int cookTime, float experience) {
		this.items = items;
		this.category = p_250837_;
		this.result = result.getItem();
		this.count = result.getCount();
		this.resultStack = result;
		this.fluidIngredient = fluidIngredient;
		this.cookTime = cookTime;
		this.experience = experience;
	}

	/**
	 * Creates a new builder for a shapeless recipe.
	 */
	public static TofuPotShapelessRecipeBuilder shapeless(HolderGetter<Item> holderGetter, TofuPotCategory category, Optional<SizedFluidIngredient> fluidIngredient, ItemLike result, int cookTime, float experience) {
		return new TofuPotShapelessRecipeBuilder(holderGetter, category, result, fluidIngredient, 1, cookTime, experience);
	}

	/**
	 * Creates a new builder for a shapeless recipe.
	 */
	public static TofuPotShapelessRecipeBuilder shapeless(HolderGetter<Item> holderGetter, TofuPotCategory category, Optional<SizedFluidIngredient> fluidIngredient, ItemLike result, int count, int cookTime, float experience) {
		return new TofuPotShapelessRecipeBuilder(holderGetter, category, result, fluidIngredient, count, cookTime, experience);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(HolderGetter<Item> holderGetter, TofuPotCategory p_252339_, Optional<SizedFluidIngredient> fluidIngredient, ItemStack result, int cookTime, float experience) {
		return new TofuPotShapelessRecipeBuilder(holderGetter, p_252339_, result, fluidIngredient, cookTime, experience);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(HolderGetter<Item> holderGetter, TofuPotCategory p_252339_, Optional<SizedFluidIngredient> fluidIngredient, ItemStack result, int cookTime) {
		return new TofuPotShapelessRecipeBuilder(holderGetter, p_252339_, result, fluidIngredient, cookTime, 0.1F);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(HolderGetter<Item> holderGetter, TofuPotCategory p_252339_, Optional<SizedFluidIngredient> fluidIngredient, ItemStack result) {
		return new TofuPotShapelessRecipeBuilder(holderGetter, p_252339_, result, fluidIngredient, 300, 0.1F);
	}

	public static TofuPotShapelessRecipeBuilder shapeless(HolderGetter<Item> holderGetter, TofuPotCategory p_252339_, ItemStack result) {
		return new TofuPotShapelessRecipeBuilder(holderGetter, p_252339_, result, Optional.empty(), 300, 0.1F);
	}

	/**
	 * Adds an ingredient that can be any item in the given tag.
	 */
	public TofuPotShapelessRecipeBuilder requires(TagKey<Item> p_206420_) {
		return this.requires(Ingredient.of(this.items.getOrThrow(p_206420_)));
	}

	/**
	 * Adds an ingredient of the given item.
	 */
	public TofuPotShapelessRecipeBuilder requires(ItemLike item) {
		return this.requires(item, 1);
	}

	/**
	 * Adds the given ingredient multiple times.
	 */
	public TofuPotShapelessRecipeBuilder requires(ItemLike item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.requires(Ingredient.of(item));
		}

		return this;
	}

	/**
	 * Adds an ingredient.
	 */
	public TofuPotShapelessRecipeBuilder requires(Ingredient ingredient) {
		return this.requires(ingredient, 1);
	}

	/**
	 * Adds an ingredient multiple times.
	 */
	public TofuPotShapelessRecipeBuilder requires(Ingredient ingredient, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.ingredients.add(ingredient);
		}

		return this;
	}

	public TofuPotShapelessRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	public TofuPotShapelessRecipeBuilder group(@Nullable String groupName) {
		this.group = groupName;
		return this;
	}

	@Override
	public Item getResult() {
		return this.result;
	}


	@Override
	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
		this.ensureValid(resourceKey.location());
		Advancement.Builder advancement$builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
				.rewards(AdvancementRewards.Builder.recipe(resourceKey))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		TofuPotShapelessRecipe shapelessrecipe = new TofuPotShapelessRecipe(
				Objects.requireNonNullElse(this.group, ""),
				this.category,
				this.resultStack,
				this.ingredients,
				this.fluidIngredient,
				this.cookTime,
				this.experience
		);
		recipeOutput.accept(resourceKey, shapelessrecipe, advancement$builder.build(resourceKey.location().withPrefix("recipes/" + this.category.getSerializedName() + "/")));

	}

	/**
	 * Makes sure that this recipe is valid and obtainable.
	 */
	private void ensureValid(ResourceLocation id) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}
}
