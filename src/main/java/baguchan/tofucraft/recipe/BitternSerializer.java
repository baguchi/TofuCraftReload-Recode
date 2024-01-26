package baguchan.tofucraft.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;

public class BitternSerializer implements RecipeSerializer<BitternRecipe> {
	private static final Codec<BitternRecipe> codec = RecordCodecBuilder.create((p_296927_) -> {
		return p_296927_.group(FluidIngredient.CODEC.fieldOf("process").forGetter((p_296920_) -> {
			return p_296920_.fluid;
		}), Ingredient.CODEC.fieldOf("ingredient").forGetter((p_296920_) -> {
			return p_296920_.ingredient;
		}), BuiltInRegistries.ITEM.byNameCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("result").forGetter((p_296923_) -> {
			return p_296923_.result;
		})).apply(p_296927_, BitternRecipe::new);
	});


	@Override
	public Codec<BitternRecipe> codec() {
		return codec;
	}

	@Nullable
	@Override
	public BitternRecipe fromNetwork(FriendlyByteBuf buf) {
		try {
			final FluidIngredient fluid = FluidIngredient.fromNetwork(buf);
			final Ingredient ingredient = Ingredient.fromNetwork(buf);
			ItemStack results = buf.readItem();

			return new BitternRecipe(fluid, ingredient, results);
		} catch (final Exception e) {

			throw new IllegalStateException("Failed to read bittern info from packet buffer. This is not good.");
		}
	}

	@Override
	public void toNetwork(FriendlyByteBuf p_44101_, BitternRecipe p_44102_) {
		p_44102_.getFluid().toNetwork(p_44101_);
		p_44102_.getIngredient().toNetwork(p_44101_);
		p_44101_.writeItem(p_44102_.result);
	}
}