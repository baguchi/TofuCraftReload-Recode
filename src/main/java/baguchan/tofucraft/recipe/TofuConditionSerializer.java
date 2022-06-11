package baguchan.tofucraft.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;

public class TofuConditionSerializer implements RecipeSerializer<TofuConditionRecipe> {

	public static final TofuConditionSerializer INSTANCE = new TofuConditionSerializer();

	@Override
	public TofuConditionRecipe fromJson(ResourceLocation id, JsonObject json) {

		final Ingredient tofu = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "tofu"));
		final ItemStack results = itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
		String string = GsonHelper.getAsString(json, "recipe_type");
		return new TofuConditionRecipe(id, tofu, results, TofuConditionType.get(string));
	}

	public static ItemStack itemStackFromJson(JsonObject p_151275_) {
		return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(p_151275_, false, true);
	}

	@Nullable
	@Override
	public TofuConditionRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
		try {

			final Ingredient tofu = Ingredient.fromNetwork(buf);
			ItemStack results = buf.readItem();

			return new TofuConditionRecipe(id, tofu, results, buf.readEnum(TofuConditionType.class));
		} catch (final Exception e) {

			throw new IllegalStateException("Failed to read harden info from packet buffer. This is not good.");
		}
	}

	@Override
	public void toNetwork(FriendlyByteBuf p_44101_, TofuConditionRecipe p_44102_) {
		p_44102_.getTofu().toNetwork(p_44101_);
		p_44101_.writeItem(p_44102_.result);
		p_44101_.writeEnum(p_44102_.recipeType);
	}
}