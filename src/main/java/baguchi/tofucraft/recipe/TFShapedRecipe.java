package baguchi.tofucraft.recipe;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuRecipes;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class TFShapedRecipe implements TFCraftingRecipe {
	public final ShapedRecipePattern pattern;
	final ItemStack result;
	final String group;
	final TFCraftingCategory category;
	final int tf;
	final boolean showNotification;
	@Nullable
	private PlacementInfo placementInfo;

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
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
		return TofuRecipes.RECIPE_TF_CRAFT_SHAPED.get();
	}

	@VisibleForTesting
	public List<Optional<Ingredient>> getIngredients() {
		return this.pattern.ingredients();
	}

	@Override
	public PlacementInfo placementInfo() {
		if (this.placementInfo == null) {
			this.placementInfo = PlacementInfo.createFromOptionals(this.pattern.ingredients());
		}

		return this.placementInfo;
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
		return this.tf;
	}

	@Override
	public boolean showNotification() {
		return this.showNotification;
	}


	public boolean matches(CraftingInput input, Level level) {
		return this.pattern.matches(input);
	}

	@Override
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
		return this.result.copy();
	}

	public int getWidth() {
		return this.pattern.width();
	}

	public int getHeight() {
		return this.pattern.height();
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new ShapedCraftingRecipeDisplay(this.pattern.width(), this.pattern.height(), this.pattern.ingredients().stream().map((p_380107_) -> p_380107_.map(Ingredient::display).orElse(SlotDisplay.Empty.INSTANCE)).toList(), new SlotDisplay.ItemStackSlotDisplay(this.result), new SlotDisplay.ItemSlotDisplay(TofuBlocks.TF_CRAFTING_TABLE.asItem())));
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
				Serializer::toNetwork, Serializer::fromNetwork
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
