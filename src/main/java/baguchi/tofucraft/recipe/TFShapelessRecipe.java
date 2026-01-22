package baguchi.tofucraft.recipe;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuRecipes;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class TFShapelessRecipe implements TFCraftingRecipe {
	private final String group;
	private final TFCraftingCategory category;
	private final ItemStackTemplate result;
	private final List<Ingredient> ingredients;
	private final int tfNeed;
	private final boolean isSimple;
	@Nullable
	private PlacementInfo placementInfo;

	public TFShapelessRecipe(String group, TFCraftingCategory category, ItemStackTemplate result, List<Ingredient> ingredients, int tfNeed) {
		this.group = group;
		this.category = category;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
		this.tfNeed = tfNeed;
	}

	@Override
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
		return TofuRecipes.RECIPE_TF_CRAFT_SHAPELESS.get();
	}

	@Override
	public PlacementInfo placementInfo() {
		if (this.placementInfo == null) {
			this.placementInfo = PlacementInfo.create(this.ingredients);
		}

		return this.placementInfo;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new ShapelessCraftingRecipeDisplay(this.ingredients.stream().map(Ingredient::display).toList(), new SlotDisplay.ItemStackSlotDisplay(this.result), new SlotDisplay.ItemSlotDisplay(TofuBlocks.TF_CRAFTING_TABLE.asItem())));
	}

	public String getGroup() {
		return this.group;
	}

	@Override
	public TFCraftingCategory category() {
		return this.category;
	}

	@Override
	public int getNeedTF() {
		return this.tfNeed;
	}

	public boolean matches(CraftingInput input, Level level) {
		if (input.ingredientCount() != this.ingredients.size()) {
			return false;
		} else if (!isSimple) {
			var nonEmptyItems = new java.util.ArrayList<ItemStack>(input.ingredientCount());
			for (var item : input.items())
				if (!item.isEmpty())
					nonEmptyItems.add(item);
			return net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
		} else {
			return input.size() == 1 && this.ingredients.size() == 1
					? this.ingredients.getFirst().test(input.getItem(0))
					: input.stackedContents().canCraft(this, null);
		}
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		return this.result.create();
	}

	@Override
	public ItemStack getResult() {
		return result.create();
	}

	@VisibleForTesting
	public List<Optional<Ingredient>> getIngredients() {
		return this.ingredients.stream().map(Optional::of).toList();
	}


	public static class Serializer implements RecipeSerializer<TFShapelessRecipe> {
		private static final MapCodec<TFShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(
				p_340779_ -> p_340779_.group(
								Codec.STRING.optionalFieldOf("group", "").forGetter(p_301127_ -> p_301127_.group),
								TFCraftingCategory.CODEC.fieldOf("category").orElse(TFCraftingCategory.MISC).forGetter(p_301133_ -> p_301133_.category),
								ItemStackTemplate.CODEC.fieldOf("result").forGetter(p_301142_ -> p_301142_.result),
								Codec.lazyInitialized(() -> Ingredient.CODEC
												.listOf(1, 4 * 3)).fieldOf("ingredients")
										.forGetter((p_360071_) -> p_360071_.ingredients),
								Codec.INT.fieldOf("cook_time").orElse(300).forGetter(potShapelessRecipe -> potShapelessRecipe.tfNeed)
						)
						.apply(p_340779_, (group1, category1, result1, ingredients1, tfNeed) -> new TFShapelessRecipe(group1, category1, result1, ingredients1, tfNeed))
		);

		public static final StreamCodec<RegistryFriendlyByteBuf, TFShapelessRecipe> STREAM_CODEC = StreamCodec.composite(
				ByteBufCodecs.STRING_UTF8,
				p_360074_ -> p_360074_.group,
				TFCraftingCategory.STREAM_CODEC,
				p_360073_ -> p_360073_.category,
				ItemStackTemplate.STREAM_CODEC,
				p_360070_ -> p_360070_.result,
				Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
				p_360069_ -> p_360069_.ingredients,
				ByteBufCodecs.INT,
				p_360069_ -> p_360069_.tfNeed,
				TFShapelessRecipe::new
		);
		@Override
		public MapCodec<TFShapelessRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, TFShapelessRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
