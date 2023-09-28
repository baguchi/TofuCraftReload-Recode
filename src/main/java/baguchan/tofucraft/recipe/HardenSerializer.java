package baguchan.tofucraft.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class HardenSerializer implements RecipeSerializer<HardenRecipe> {

	private static final Codec<HardenRecipe> codec = RecordCodecBuilder.create((p_296927_) -> {
		return p_296927_.group(Ingredient.CODEC_NONEMPTY.fieldOf("tofu").forGetter((p_296920_) -> {
			return p_296920_.tofu;
		}), BuiltInRegistries.ITEM.byNameCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("result").forGetter((p_296923_) -> {
			return p_296923_.result;
		})).apply(p_296927_, HardenRecipe::new);
	});

	@Override
	public Codec<HardenRecipe> codec() {
		return codec;
	}

	@Override
	public HardenRecipe fromNetwork(FriendlyByteBuf p_44106_) {
		Ingredient ingredient = Ingredient.fromNetwork(p_44106_);
		ItemStack itemstack = p_44106_.readItem();
		return new HardenRecipe(ingredient, itemstack);
	}

	@Override
	public void toNetwork(FriendlyByteBuf p_44101_, HardenRecipe p_44102_) {
		p_44102_.getTofu().toNetwork(p_44101_);
		p_44101_.writeItem(p_44102_.result);
	}
}