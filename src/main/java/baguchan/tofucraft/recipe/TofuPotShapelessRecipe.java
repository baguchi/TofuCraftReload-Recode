package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import java.util.Optional;

public class TofuPotShapelessRecipe implements TofuPotRecipe {
	final String group;
	final TofuPotCategory category;
	final ItemStack result;
	final NonNullList<Ingredient> ingredients;
	final Optional<SizedFluidIngredient> ingredientFluid;
	private final int cookTime;
	private final float experience;
	private final boolean isSimple;

	public TofuPotShapelessRecipe(String group, TofuPotCategory category, ItemStack result, NonNullList<Ingredient> ingredients, Optional<SizedFluidIngredient> ingredientFluid, int cookTime, float experience) {
		this.group = group;
		this.category = category;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
		this.ingredientFluid = ingredientFluid;
		this.cookTime = cookTime;
		this.experience = experience;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return TofuRecipes.RECIPE_TOFU_POT_SHAPELESS.get();
	}

	@Override
	public String getGroup() {
		return this.group;
	}

	@Override
	public TofuPotCategory category() {
		return this.category;
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
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		return this.result;
	}

	@Override
	public ItemStack getResult() {
		return result;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
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

	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
		return this.result.copy();
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.ingredients.size();
	}

	public static class Serializer implements RecipeSerializer<TofuPotShapelessRecipe> {
		public static final StreamCodec<RegistryFriendlyByteBuf, Optional<SizedFluidIngredient>> OPTIONAL_STREAM_CODEC = ByteBufCodecs.optional(SizedFluidIngredient.STREAM_CODEC);



		private static final MapCodec<TofuPotShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(
				p_340779_ -> p_340779_.group(
								Codec.STRING.optionalFieldOf("group", "").forGetter(p_301127_ -> p_301127_.group),
								TofuPotCategory.CODEC.fieldOf("category").orElse(TofuPotCategory.MISC).forGetter(p_301133_ -> p_301133_.category),
								ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_301142_ -> p_301142_.result),
								Ingredient.CODEC_NONEMPTY
										.listOf()
										.fieldOf("ingredients")
										.flatXmap(
												p_301021_ -> {
													Ingredient[] aingredient = p_301021_.toArray(Ingredient[]::new); // Neo skip the empty check and immediately create the array.
													if (aingredient.length == 0) {
														return DataResult.error(() -> "No ingredients for shapeless recipe");
													} else {
														return aingredient.length > 4 * 3
																? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(4 * 3))
																: DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
													}
												},
												DataResult::success
										)
										.forGetter(p_300975_ -> p_300975_.ingredients),
								SizedFluidIngredient.NESTED_CODEC.optionalFieldOf("fluid").forGetter(potShapelessRecipe -> potShapelessRecipe.ingredientFluid),
								Codec.INT.fieldOf("cook_time").orElse(300).forGetter(potShapelessRecipe -> potShapelessRecipe.cookTime),
								Codec.FLOAT.fieldOf("experience").orElse(0.1F).forGetter(potShapelessRecipe -> potShapelessRecipe.experience)
						)
						.apply(p_340779_, (group1, category1, result1, ingredients1, fluidIngredient, cookTime, experience) -> new TofuPotShapelessRecipe(group1, category1, result1, ingredients1, fluidIngredient, cookTime, experience))
		);
		public static final StreamCodec<RegistryFriendlyByteBuf, TofuPotShapelessRecipe> STREAM_CODEC = StreamCodec.of(
				TofuPotShapelessRecipe.Serializer::toNetwork, TofuPotShapelessRecipe.Serializer::fromNetwork
		);

		@Override
		public MapCodec<TofuPotShapelessRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, TofuPotShapelessRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static TofuPotShapelessRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			String s = buffer.readUtf();
			TofuPotCategory craftingbookcategory = buffer.readEnum(TofuPotCategory.class);
			int i = buffer.readVarInt();
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
			nonnulllist.replaceAll(p_319735_ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
			ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
			Optional<SizedFluidIngredient> fluidIngredient = OPTIONAL_STREAM_CODEC.decode(buffer);
			return new TofuPotShapelessRecipe(s, craftingbookcategory, itemstack, nonnulllist, fluidIngredient, buffer.readInt(), buffer.readFloat());
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, TofuPotShapelessRecipe recipe) {
			buffer.writeUtf(recipe.group);
			buffer.writeEnum(recipe.category);
			buffer.writeVarInt(recipe.ingredients.size());

			for (Ingredient ingredient : recipe.ingredients) {
				Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
			}

			ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
			OPTIONAL_STREAM_CODEC.encode(buffer, recipe.ingredientFluid);
			buffer.writeInt(recipe.cookTime);
			buffer.writeFloat(recipe.experience);
		}
	}
}
