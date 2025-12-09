package baguchi.tofucraft.data.generator.recipe.builder;

import baguchi.tofucraft.recipe.TFCraftingCategory;
import baguchi.tofucraft.recipe.TFShapelessRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TFShapelessRecipeBuilder implements RecipeBuilder {
	private final HolderGetter<Item> items;
	private final TFCraftingCategory category;
	private final Item result;
	private final int count;
	private final ItemStack resultStack; // Neo: add stack result support
	private final int tf;
	private final NonNullList<Ingredient> ingredients = NonNullList.create();
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	@Nullable
	private String group;

	public TFShapelessRecipeBuilder(HolderGetter<Item> items, TFCraftingCategory category, ItemLike result, int count, int tf) {
		this(items, category, new ItemStack(result, count), tf);
	}

	public TFShapelessRecipeBuilder(HolderGetter<Item> items, TFCraftingCategory p_250837_, ItemStack result, int tf) {
		this.items = items;
		this.category = p_250837_;
		this.result = result.getItem();
		this.count = result.getCount();
		this.resultStack = result;
		this.tf = tf;
	}

	/**
	 * Creates a new builder for a shapeless recipe.
	 */
	public static TFShapelessRecipeBuilder shapeless(HolderGetter<Item> items, TFCraftingCategory category, ItemLike result) {
		return new TFShapelessRecipeBuilder(items, category, result, 1, 500);
	}

	/**
	 * Creates a new builder for a shapeless recipe.
	 */
	public static TFShapelessRecipeBuilder shapeless(HolderGetter<Item> items, TFCraftingCategory category, ItemLike result, int count, int tf) {
		return new TFShapelessRecipeBuilder(items, category, result, count, tf);
	}

	public static TFShapelessRecipeBuilder shapeless(HolderGetter<Item> items, TFCraftingCategory p_252339_, ItemStack result, int tf) {
		return new TFShapelessRecipeBuilder(items, p_252339_, result, tf);
	}

	/**
	 * Adds an ingredient that can be any item in the given tag.
	 */
	public TFShapelessRecipeBuilder requires(TagKey<Item> tag) {
		return this.requires(Ingredient.of(items.getOrThrow(tag)));
	}

	/**
	 * Adds an ingredient of the given item.
	 */
	public TFShapelessRecipeBuilder requires(ItemLike item) {
		return this.requires(item, 1);
	}

	/**
	 * Adds the given ingredient multiple times.
	 */
	public TFShapelessRecipeBuilder requires(ItemLike item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.requires(Ingredient.of(item));
		}

		return this;
	}

	/**
	 * Adds an ingredient.
	 */
	public TFShapelessRecipeBuilder requires(Ingredient ingredient) {
		return this.requires(ingredient, 1);
	}

	/**
	 * Adds an ingredient multiple times.
	 */
	public TFShapelessRecipeBuilder requires(Ingredient ingredient, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.ingredients.add(ingredient);
		}

		return this;
	}

	public TFShapelessRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	public TFShapelessRecipeBuilder group(@Nullable String groupName) {
		this.group = groupName;
		return this;
	}

	@Override
	public Item getResult() {
		return this.result;
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> id) {
		this.ensureValid(id.identifier());
		Advancement.Builder advancement$builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
				.rewards(AdvancementRewards.Builder.recipe(id))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		baguchi.tofucraft.recipe.TFShapelessRecipe shapelessrecipe = new TFShapelessRecipe(
				Objects.requireNonNullElse(this.group, ""),
				this.category,
				this.resultStack,
				this.ingredients,
				this.tf
		);
		recipeOutput.accept(id, shapelessrecipe, advancement$builder.build(id.identifier().withPrefix("recipes/" + this.category.getSerializedName() + "/")));
	}

	/**
	 * Makes sure that this recipe is valid and obtainable.
	 */
	private void ensureValid(Identifier id) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}
}
