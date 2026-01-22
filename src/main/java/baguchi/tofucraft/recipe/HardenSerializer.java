package baguchi.tofucraft.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class HardenSerializer implements RecipeSerializer<HardenRecipe> {

	private static final MapCodec<HardenRecipe> CODEC = RecordCodecBuilder.mapCodec((p_296927_) -> {
		return p_296927_.group(Ingredient.CODEC.fieldOf("tofu").forGetter((p_296920_) -> {
			return p_296920_.tofu;
		}), ItemStackTemplate.CODEC.fieldOf("result").forGetter((p_296923_) -> {
			return p_296923_.result;
		})).apply(p_296927_, HardenRecipe::new);
	});

	public static final StreamCodec<RegistryFriendlyByteBuf, HardenRecipe> STREAM_CODEC = StreamCodec.of(
			HardenSerializer::toNetwork, HardenSerializer::fromNetwork
	);

	@Override
	public MapCodec<HardenRecipe> codec() {
		return CODEC;
	}

	@Override
	public StreamCodec<RegistryFriendlyByteBuf, HardenRecipe> streamCodec() {
		return STREAM_CODEC;
	}

	private static HardenRecipe fromNetwork(RegistryFriendlyByteBuf p_320719_) {
		Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(p_320719_);
		ItemStackTemplate ingredient2 = ItemStackTemplate.STREAM_CODEC.decode(p_320719_);
		return new HardenRecipe(ingredient1, ingredient2);
	}

	private static void toNetwork(RegistryFriendlyByteBuf p_319922_, HardenRecipe p_320655_) {
		Ingredient.CONTENTS_STREAM_CODEC.encode(p_319922_, p_320655_.tofu);
		ItemStackTemplate.STREAM_CODEC.encode(p_319922_, p_320655_.result);
	}
}