package baguchan.tofucraft.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;

public class AggregatorSerializer implements RecipeSerializer<AggregatorRecipe> {

	public static final AggregatorSerializer INSTANCE = new AggregatorSerializer();

	@Override
	public AggregatorRecipe fromJson(ResourceLocation id, JsonObject json) {

		final Ingredient fluid = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "process"));
		final ItemStack results = itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
		final int time = GsonHelper.getAsInt(json, "recipeTime");


		return new AggregatorRecipe(id, fluid, results, time);
	}

	public static ItemStack itemStackFromJson(JsonObject p_151275_) {
		return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(p_151275_, false, true);
	}

	@Nullable
	@Override
	public AggregatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
		try {

			final Ingredient fluid = Ingredient.fromNetwork(buf);
			ItemStack results = buf.readItem();

			return new AggregatorRecipe(id, fluid, results, buf.readInt());
		} catch (final Exception e) {

			throw new IllegalStateException("Failed to read bittern info from packet buffer. This is not good.");
		}
	}

	@Override
	public void toNetwork(FriendlyByteBuf p_44101_, AggregatorRecipe p_44102_) {
		p_44102_.inputItems.toNetwork(p_44101_);
		p_44101_.writeItem(p_44102_.getResultItem());
		p_44101_.writeInt(p_44102_.recipeTime);
	}
}