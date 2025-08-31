package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import baguchan.tofucraft.utils.DataGenUtil;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class TofuPotShapelessRecipe implements TofuPotRecipe {
	protected ResourceLocation id;
	@Expose
	public String group;
	@Expose
	public TofuPotCategory category;
	@Expose
	@SerializedName("ingredients")
	public NonNullList<Ingredient> ingredients;
	@Expose
	@SerializedName("fluid")
	public FluidIngredient ingredientFluid;
	@Expose
	@SerializedName("result")
	public ItemStack result;
	@Expose
	public int cookTime;
	@Expose
	public float experience;

	@Override
	public RecipeSerializer<?> getSerializer() {
		return TofuRecipes.RECIPE_TOFU_POT_SHAPELESS.get();
	}

	@Override
	public String getGroup() {
		return this.group;
	}

	@Override
	public TofuPotCategory category() {
		return this.category;
	}

	@Override
	public int getCookTime() {
		return this.cookTime;
	}

	@Override
	public FluidIngredient fluidIngredient() {
		return this.ingredientFluid;
	}

	@Override
	public float getExperience() {
		return experience;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registries) {
		return this.result;
	}

	@Override
	public ItemStack getResult() {
		return result;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}


	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	@Override
	public boolean matches(Container p_44002_, Level p_44003_) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for (int j = 0; j < p_44002_.getContainerSize(); ++j) {
			ItemStack itemstack = p_44002_.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
				inputs.add(itemstack);
			}
		}

		return i == this.ingredients.size() && (net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.ingredients) != null);

	}

	@Override
	public ItemStack assemble(Container input, RegistryAccess registries) {
		return this.result.copy();
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.ingredients.size();
	}

	public static class Serializer implements RecipeSerializer<TofuPotShapelessRecipe> {

		@Override
		public TofuPotShapelessRecipe fromJson(ResourceLocation recipeID, JsonObject recipeJson) {
			TofuPotShapelessRecipe result = DataGenUtil.NETWORK_GSON.fromJson(recipeJson, TofuPotShapelessRecipe.class);
			result.setId(recipeID);
			return result;
		}

		@Override
		public TofuPotShapelessRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer) {
			TofuPotShapelessRecipe result = DataGenUtil.NETWORK_GSON.fromJson(buffer.readUtf(), TofuPotShapelessRecipe.class);
			result.setId(recipeID);
			return result;
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, TofuPotShapelessRecipe recipe) {
			buffer.writeUtf(DataGenUtil.NETWORK_GSON.toJson(recipe));
		}

		public JsonObject toJson(TofuPotShapelessRecipe recipe) {
			return DataGenUtil.NETWORK_GSON.toJsonTree(recipe).getAsJsonObject();
		}
	}

	public void setId(ResourceLocation recipeID) {
		this.id = recipeID;
	}
}
