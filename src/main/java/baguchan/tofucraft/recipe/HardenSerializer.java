package baguchan.tofucraft.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class HardenSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<HardenInfo> {

	public static final HardenSerializer INSTANCE = new HardenSerializer();

	@Override
	public HardenInfo fromJson(ResourceLocation id, JsonObject json) {

		final Ingredient tofu = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "tofu"));
		final Ingredient results = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "result"));

		return new HardenInfo(id, tofu, results);
	}

	@Nullable
	@Override
	public HardenInfo fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
		try {

			final Ingredient tofu = Ingredient.fromNetwork(buf);
			final Ingredient results = Ingredient.fromNetwork(buf);

			return new HardenInfo(id, tofu, results);
		} catch (final Exception e) {

			throw new IllegalStateException("Failed to read harden info from packet buffer. This is not good.");
		}
	}

	@Override
	public void toNetwork(FriendlyByteBuf p_44101_, HardenInfo p_44102_) {
		p_44102_.getTofu().toNetwork(p_44101_);
		p_44102_.getResults().toNetwork(p_44101_);
	}
}