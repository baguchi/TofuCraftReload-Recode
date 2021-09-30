package baguchan.tofucraft.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class BitternSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<BitternInfo> {

	public static final BitternSerializer INSTANCE = new BitternSerializer();

	@Override
	public BitternInfo fromJson(ResourceLocation id, JsonObject json) {

		final FluidIngredient fluid = FluidIngredient.fromJson(GsonHelper.getAsJsonObject(json, "process"));
		final Ingredient results = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "result"));

		return new BitternInfo(id, fluid, results);
	}

	@Nullable
	@Override
	public BitternInfo fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
		try {

			final FluidIngredient fluid = FluidIngredient.fromNetwork(buf);
			final Ingredient results = Ingredient.fromNetwork(buf);

			return new BitternInfo(id, fluid, results);
		} catch (final Exception e) {

			throw new IllegalStateException("Failed to read bittern info from packet buffer. This is not good.");
		}
	}

	@Override
	public void toNetwork(FriendlyByteBuf p_44101_, BitternInfo p_44102_) {
		p_44102_.getFluid().toNetwork(p_44101_);
		p_44102_.getResults().toNetwork(p_44101_);
	}
}