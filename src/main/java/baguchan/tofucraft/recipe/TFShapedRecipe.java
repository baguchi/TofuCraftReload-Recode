package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.Level;

public class TFShapedRecipe implements TFCraftingRecipe {
	public final ShapedRecipePattern pattern;
	final ItemStack result;
	final String group;
	final TFCraftingCategory category;
	final int tf;
	final boolean showNotification;

	public TFShapedRecipe(String group, TFCraftingCategory category, ShapedRecipePattern pattern, ItemStack result, int tf, boolean showNotification) {
		this.group = group;
		this.category = category;
		this.pattern = pattern;
		this.result = result;
		this.tf = tf;
		this.showNotification = showNotification;
	}

	public TFShapedRecipe(String group, TFCraftingCategory category, ShapedRecipePattern pattern, ItemStack result, int tf) {
		this(group, category, pattern, result, tf, true);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return TofuRecipes.RECIPE_TF_CRAFT_SHAPED.get();
	}

	@Override
	public String getGroup() {
		return this.group;
	}

	@Override
	public TFCraftingCategory category() {
		return this.category;
	}

	@Override
	public int getNeedTF() {
		return this.tf;
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
		return this.pattern.ingredients();
	}

	@Override
	public boolean showNotification() {
		return this.showNotification;
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= this.pattern.width() && height >= this.pattern.height();
	}

	public boolean matches(CraftingInput input, Level level) {
		return this.pattern.matches(input);
	}

	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
		return this.getResultItem(registries).copy();
	}

	public int getWidth() {
		return this.pattern.width();
	}

	public int getHeight() {
		return this.pattern.height();
	}

	@Override
	public boolean isIncomplete() {
		NonNullList<Ingredient> nonnulllist = this.getIngredients();
		return nonnulllist.isEmpty() || nonnulllist.stream().filter(p_151277_ -> !p_151277_.isEmpty()).anyMatch(Ingredient::hasNoItems);
	}

	public static class Serializer implements RecipeSerializer<TFShapedRecipe> {
		public static final MapCodec<TFShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(
				p_340778_ -> p_340778_.group(
								Codec.STRING.optionalFieldOf("group", "").forGetter(p_311729_ -> p_311729_.group),
								TFCraftingCategory.CODEC.fieldOf("category").orElse(TFCraftingCategory.MISC).forGetter(p_311732_ -> p_311732_.category),
								ShapedRecipePattern.MAP_CODEC.forGetter(p_311733_ -> p_311733_.pattern),
								ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_311730_ -> p_311730_.result),
								Codec.INT.fieldOf("tf").forGetter(tfShapedRecipe -> tfShapedRecipe.tf),
								Codec.BOOL.optionalFieldOf("show_notification", Boolean.valueOf(true)).forGetter(p_311731_ -> p_311731_.showNotification)
						)
						.apply(p_340778_, TFShapedRecipe::new)
		);
		public static final StreamCodec<RegistryFriendlyByteBuf, TFShapedRecipe> STREAM_CODEC = StreamCodec.of(
				TFShapedRecipe.Serializer::toNetwork, TFShapedRecipe.Serializer::fromNetwork
		);

		@Override
		public MapCodec<TFShapedRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, TFShapedRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static TFShapedRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			String s = buffer.readUtf();
			TFCraftingCategory craftingbookcategory = buffer.readEnum(TFCraftingCategory.class);
			ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
			ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
			int i = buffer.readInt();
			boolean flag = buffer.readBoolean();
			return new TFShapedRecipe(s, craftingbookcategory, shapedrecipepattern, itemstack, i, flag);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, TFShapedRecipe recipe) {
			buffer.writeUtf(recipe.group);
			buffer.writeEnum(recipe.category);
			ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
			ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
			buffer.writeInt(recipe.tf);
			buffer.writeBoolean(recipe.showNotification);
		}
	}
}
