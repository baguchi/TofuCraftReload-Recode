package baguchi.tofucraft.data.generator.recipe.builder;

import baguchi.tofucraft.recipe.TFCraftingCategory;
import baguchi.tofucraft.recipe.TFShapedRecipe;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TFShapedRecipeBuilder implements RecipeBuilder {
	private final HolderGetter<Item> items;
	private final TFCraftingCategory category;
	private final ItemStackTemplate resultStack; // Neo: add stack result support
	private final int tf;
	private final List<String> rows = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	@Nullable
	private String group;
	private boolean showNotification = true;

	public TFShapedRecipeBuilder(HolderGetter<Item> items, TFCraftingCategory category, ItemLike result, int count, int tf) {
		this(items, category, new ItemStackTemplate(result.asItem(), count), tf);
	}

	public TFShapedRecipeBuilder(HolderGetter<Item> items, TFCraftingCategory p_249996_, ItemStackTemplate result, int tf) {
		this.items = items;
		this.category = p_249996_;
		this.resultStack = result;
		this.tf = tf;
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static TFShapedRecipeBuilder shaped(HolderGetter<Item> items, TFCraftingCategory category, ItemLike result) {
		return shaped(items, category, result, 1, 500);
	}

	/**
	 * Creates a new builder for a shaped recipe.
	 */
	public static TFShapedRecipeBuilder shaped(HolderGetter<Item> items, TFCraftingCategory category, ItemLike result, int count, int tf) {
		return new TFShapedRecipeBuilder(items, category, result, count, tf);
	}

	public static TFShapedRecipeBuilder shaped(HolderGetter<Item> items, TFCraftingCategory p_251325_, ItemStackTemplate result, int tf) {
		return new TFShapedRecipeBuilder(items, p_251325_, result, tf);
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public TFShapedRecipeBuilder define(Character symbol, TagKey<Item> tag) {
		return this.define(symbol, Ingredient.of(this.items.getOrThrow(tag)));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public TFShapedRecipeBuilder define(Character symbol, ItemLike item) {
		return this.define(symbol, Ingredient.of(item));
	}

	/**
	 * Adds a key to the recipe pattern.
	 */
	public TFShapedRecipeBuilder define(Character symbol, Ingredient ingredient) {
		if (this.key.containsKey(symbol)) {
			throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
		} else if (symbol == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.key.put(symbol, ingredient);
			return this;
		}
	}

	/**
	 * Adds a new entry to the patterns for this recipe.
	 */
	public TFShapedRecipeBuilder pattern(String pattern) {
		if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.rows.add(pattern);
			return this;
		}
	}

	public TFShapedRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	public TFShapedRecipeBuilder group(@Nullable String groupName) {
		this.group = groupName;
		return this;
	}

	@Override
	public ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(this.resultStack);
	}

	public TFShapedRecipeBuilder showNotification(boolean showNotification) {
		this.showNotification = showNotification;
		return this;
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> id) {
		ShapedRecipePattern shapedrecipepattern = this.ensureValid(id.identifier());
		Advancement.Builder advancement$builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
				.rewards(AdvancementRewards.Builder.recipe(id))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		TFShapedRecipe shapedrecipe = new TFShapedRecipe(
				Objects.requireNonNullElse(this.group, ""),
				this.category,
				shapedrecipepattern,
				this.resultStack,
				this.tf,
				this.showNotification
		);
		recipeOutput.accept(id, shapedrecipe, advancement$builder.build(id.identifier().withPrefix("recipes/" + this.category.getSerializedName() + "/")));
	}

	private ShapedRecipePattern ensureValid(Identifier loaction) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + loaction);
		} else {
			return ShapedRecipePattern.of(this.key, this.rows);
		}
	}
}
