package baguchan.tofucraft.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;

public class BitternSerializer implements RecipeSerializer<BitternRecipe> {

	public static final BitternSerializer INSTANCE = new BitternSerializer();

	@Override
	public BitternRecipe fromJson(ResourceLocation id, JsonObject json) {

		final FluidIngredient fluid = FluidIngredient.fromJson(GsonHelper.getAsJsonObject(json, "process"));
		final ItemStack results = itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

		return new BitternRecipe(id, fluid, results);
	}

	public static ItemStack itemStackFromJson(JsonObject p_151275_) {
		return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(p_151275_, false, true);
	}

	@Nullable
	@Override
	public BitternRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
		try {

			final FluidIngredient fluid = FluidIngredient.fromNetwork(buf);
			ItemStack results = buf.readItem();

			return new BitternRecipe(id, fluid, results);
		} catch (final Exception e) {

			throw new IllegalStateException("Failed to read bittern info from packet buffer. This is not good.");
		}
	}

	@Override
	public void toNetwork(FriendlyByteBuf p_44101_, BitternRecipe p_44102_) {
		p_44102_.getFluid().toNetwork(p_44101_);
		p_44101_.writeItem(p_44102_.result);
	}
}