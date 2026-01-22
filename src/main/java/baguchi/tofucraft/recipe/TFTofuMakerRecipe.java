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
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;

public class TFTofuMakerRecipe extends SingleItemRecipe {
	private final float experience;
	private final int cookingTime;

	public TFTofuMakerRecipe(String group, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime) {
		super(group, ingredient, result);
		this.experience = experience;
		this.cookingTime = cookingTime;
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

	public static class Serializer implements RecipeSerializer<TFTofuMakerRecipe> {
		private final MapCodec<TFTofuMakerRecipe> CODEC = RecordCodecBuilder.mapCodec(
				r -> r.group(
								Codec.STRING.optionalFieldOf("group", "").forGetter(SingleItemRecipe::group),
								Ingredient.CODEC.fieldOf("ingredient").forGetter(SingleItemRecipe::input),
								ItemStackTemplate.CODEC.fieldOf("result").forGetter(TFTofuMakerRecipe::result),
								Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(TFTofuMakerRecipe::experience),
								Codec.INT.fieldOf("cookingtime").orElse(200).forGetter(TFTofuMakerRecipe::cookingTime)
						)
						.apply(r, TFTofuMakerRecipe::new)
		);
		private final StreamCodec<RegistryFriendlyByteBuf, TFTofuMakerRecipe> STREAM_CODEC = StreamCodec.composite(
				ByteBufCodecs.STRING_UTF8,
				SingleItemRecipe::group,
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

		@Override
		public MapCodec<TFTofuMakerRecipe> codec() {
			return this.CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, TFTofuMakerRecipe> streamCodec() {
			return this.STREAM_CODEC;
		}
	}
}