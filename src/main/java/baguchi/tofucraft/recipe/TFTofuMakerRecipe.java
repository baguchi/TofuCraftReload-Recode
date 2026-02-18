package baguchi.tofucraft.recipe;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuRecipeBookCategory;
import baguchi.tofucraft.registry.TofuRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;

public class TFTofuMakerRecipe extends SingleItemRecipe {
	protected final Recipe.CommonInfo commonInfo;

	private final float experience;
	private final int cookingTime;

	public TFTofuMakerRecipe(CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime) {
		super(commonInfo, ingredient, result);
		this.commonInfo = commonInfo;
		this.experience = experience;
		this.cookingTime = cookingTime;
	}

	@Override
	public String group() {
		return "tofu_maker";
	}

	@Override
	public RecipeSerializer<? extends TFTofuMakerRecipe> getSerializer() {
		return TofuRecipes.RECIPE_TF_TOFU_MAKER.get();
	}

	@Override
	public RecipeType<? extends TFTofuMakerRecipe> getType() {
		return TofuRecipes.RECIPETYPE_TF_TOFU_MAKER.get();
	}

	public float experience() {
		return this.experience;
	}

	public int cookingTime() {
		return this.cookingTime;
	}

	@Override
	public ItemStackTemplate result() {
		return super.result();
	}

	public List<RecipeDisplay> display() {
		return List.of(new FurnaceRecipeDisplay(this.input().display(), SlotDisplay.AnyFuel.INSTANCE, new SlotDisplay.ItemStackSlotDisplay(this.result()), new SlotDisplay.ItemSlotDisplay(TofuBlocks.TF_TOFU_MAKER.asItem()), this.cookingTime, this.experience));
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return TofuRecipeBookCategory.TF_TOFU_MAKER.get();
	}

	public static class Serializer {
		private static final MapCodec<TFTofuMakerRecipe> CODEC = RecordCodecBuilder.mapCodec(
				r -> r.group(
								Recipe.CommonInfo.MAP_CODEC.forGetter(o -> o.commonInfo),
								Ingredient.CODEC.fieldOf("ingredient").forGetter(SingleItemRecipe::input),
								ItemStackTemplate.CODEC.fieldOf("result").forGetter(TFTofuMakerRecipe::result),
								Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(TFTofuMakerRecipe::experience),
								Codec.INT.fieldOf("cookingtime").orElse(200).forGetter(TFTofuMakerRecipe::cookingTime)
						)
						.apply(r, TFTofuMakerRecipe::new)
		);
		private static final StreamCodec<RegistryFriendlyByteBuf, TFTofuMakerRecipe> STREAM_CODEC = StreamCodec.composite(
				Recipe.CommonInfo.STREAM_CODEC,
				o -> o.commonInfo,
				Ingredient.CONTENTS_STREAM_CODEC,
				SingleItemRecipe::input,
				ItemStackTemplate.STREAM_CODEC,
				TFTofuMakerRecipe::result,
				ByteBufCodecs.FLOAT,
				TFTofuMakerRecipe::experience,
				ByteBufCodecs.INT,
				TFTofuMakerRecipe::cookingTime,
				TFTofuMakerRecipe::new
		);
		public static final RecipeSerializer<TFTofuMakerRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

	}
}