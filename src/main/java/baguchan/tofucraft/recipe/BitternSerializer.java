package baguchan.tofucraft.recipe;

import baguchan.tofucraft.utils.DataGenUtil;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BitternSerializer implements RecipeSerializer<BitternRecipe> {
	@Override
	public BitternRecipe fromJson(ResourceLocation recipeID, JsonObject recipeJson) {
		BitternRecipe result = DataGenUtil.NETWORK_GSON.fromJson(recipeJson, BitternRecipe.class);
		result.setId(recipeID);
		return result;
	}

	@Override
	public BitternRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer) {
		BitternRecipe result = DataGenUtil.NETWORK_GSON.fromJson(buffer.readUtf(), BitternRecipe.class);
		result.setId(recipeID);
		return result;
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, BitternRecipe recipe) {
		buffer.writeUtf(DataGenUtil.NETWORK_GSON.toJson(recipe));
	}

	public JsonObject toJson(BitternRecipe recipe) {
		return DataGenUtil.NETWORK_GSON.toJsonTree(recipe).getAsJsonObject();
	}
}