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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class TofuPotShapelessRecipe implements TofuPotRecipe {
	final String group;
	final TofuPotCategory category;
	final ItemStackTemplate result;
	final List<Ingredient> ingredients;
	final Optional<SizedFluidIngredient> ingredientFluid;
	private final int cookTime;
	private final float experience;
	private final boolean isSimple;
	@Nullable
	private PlacementInfo placementInfo;

	public TofuPotShapelessRecipe(String p_249640_, TofuPotCategory category, ItemStackTemplate result, List<Ingredient> ingredients, Optional<SizedFluidIngredient> ingredientFluid, int cookTime, float experience) {
		this.group = p_249640_;
		this.category = category;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
		this.ingredientFluid = ingredientFluid;
		this.cookTime = cookTime;
		this.experience = experience;
	}

	@Override
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
		return TofuRecipes.RECIPE_TOFU_POT_SHAPELESS.get();
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
		return List.of(new ShapelessCraftingRecipeDisplay(this.ingredients.stream().map(Ingredient::display).toList(), new SlotDisplay.ItemStackSlotDisplay(this.result), new SlotDisplay.ItemSlotDisplay(TofuBlocks.TOFU_POT.get().asItem())));
	}

	@Override
	public TofuPotCategory category() {
		return category;
	}

	@Override
	public int getCookTime() {
		return this.cookTime;
	}

	@Override
	public Optional<SizedFluidIngredient> fluidIngredient() {
		return this.ingredientFluid;
	}

	@Override
	public float getExperience() {
		return experience;
	}

	@Override
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
	public boolean showNotification() {
		return false;
	}

	@Override
	public String group() {
		return "";
	}

	public ItemStack getResult() {
		return result.create();
	}

	@VisibleForTesting
	public List<Optional<Ingredient>> getIngredients() {
		return this.ingredients.stream().map(Optional::of).toList();
	}

	public static class Serializer {
		public static final StreamCodec<RegistryFriendlyByteBuf, Optional<SizedFluidIngredient>> OPTIONAL_STREAM_CODEC = ByteBufCodecs.optional(SizedFluidIngredient.STREAM_CODEC);


		private static final MapCodec<TofuPotShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(
				p_340779_ -> p_340779_.group(
								Codec.STRING.optionalFieldOf("group", "").forGetter(p_301127_ -> p_301127_.group),
								TofuPotCategory.CODEC.fieldOf("category").orElse(TofuPotCategory.MISC).forGetter(p_301133_ -> p_301133_.category),
								ItemStackTemplate.CODEC.fieldOf("result").forGetter(p_301142_ -> p_301142_.result),
								Codec.lazyInitialized(() -> Ingredient.CODEC
												.listOf(1, 4 * 3)).fieldOf("ingredients")
										.forGetter((p_360071_) -> p_360071_.ingredients),
								SizedFluidIngredient.CODEC.optionalFieldOf("fluid").forGetter(potShapelessRecipe -> potShapelessRecipe.ingredientFluid),
								Codec.INT.fieldOf("cook_time").orElse(300).forGetter(potShapelessRecipe -> potShapelessRecipe.cookTime),
								Codec.FLOAT.fieldOf("experience").orElse(0.1F).forGetter(potShapelessRecipe -> potShapelessRecipe.experience)
						)
						.apply(p_340779_, (group1, category1, result1, ingredients1, fluidIngredient, cookTime, experience) -> new TofuPotShapelessRecipe(group1, category1, result1, ingredients1, fluidIngredient, cookTime, experience)));
		public static final StreamCodec<RegistryFriendlyByteBuf, TofuPotShapelessRecipe> STREAM_CODEC = StreamCodec.composite(
				ByteBufCodecs.STRING_UTF8,
				p_360074_ -> p_360074_.group,
				TofuPotCategory.STREAM_CODEC,
				p_360073_ -> p_360073_.category,
				ItemStackTemplate.STREAM_CODEC,
				p_360070_ -> p_360070_.result,
				Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
				p_360069_ -> p_360069_.ingredients,
				OPTIONAL_STREAM_CODEC,
				p_360069_ -> p_360069_.ingredientFluid,
				ByteBufCodecs.INT,
				p_360069_ -> p_360069_.cookTime,
				ByteBufCodecs.FLOAT,
				p_360069_ -> p_360069_.experience,
				TofuPotShapelessRecipe::new
		);

		public static final RecipeSerializer<TofuPotShapelessRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);
	}
}
