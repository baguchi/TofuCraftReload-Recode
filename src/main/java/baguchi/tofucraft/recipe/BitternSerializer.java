package baguchi.tofucraft.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.FluidStackTemplate;

public class BitternSerializer {
	private static final MapCodec<BitternRecipe> CODEC = RecordCodecBuilder.mapCodec(
			p_301227_ -> p_301227_.group(
							FluidStackTemplate.CODEC.fieldOf("process").forGetter(p_301070_ -> p_301070_.fluid),
							Ingredient.CODEC.fieldOf("extra_fluid").forGetter(p_300969_ -> p_300969_.ingredient),
							ItemStackTemplate.CODEC.fieldOf("result").forGetter(p_300977_ -> p_300977_.result)
					)
					.apply(p_301227_, BitternRecipe::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, BitternRecipe> STREAM_CODEC = StreamCodec.of(
			BitternSerializer::toNetwork, BitternSerializer::fromNetwork
	);
	public static final RecipeSerializer<BitternRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);


	private static BitternRecipe fromNetwork(RegistryFriendlyByteBuf p_320719_) {
		FluidStackTemplate ingredient = FluidStackTemplate.STREAM_CODEC.decode(p_320719_);
		Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(p_320719_);
		ItemStackTemplate ingredient2 = ItemStackTemplate.STREAM_CODEC.decode(p_320719_);
		return new BitternRecipe(ingredient, ingredient1, ingredient2);
	}

	private static void toNetwork(RegistryFriendlyByteBuf p_319922_, BitternRecipe p_320655_) {
		FluidStackTemplate.STREAM_CODEC.encode(p_319922_, p_320655_.fluid);
		Ingredient.CONTENTS_STREAM_CODEC.encode(p_319922_, p_320655_.ingredient);
		ItemStackTemplate.STREAM_CODEC.encode(p_319922_, p_320655_.result);
	}
}