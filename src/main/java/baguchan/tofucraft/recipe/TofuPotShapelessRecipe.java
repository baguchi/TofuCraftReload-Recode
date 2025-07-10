package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TofuPotShapelessRecipe implements TofuPotRecipe {
	final ResourceLocation id;
	final String group;
	final TofuPotCategory category;
	final ItemStack result;
	final NonNullList<Ingredient> ingredients;
	final FluidIngredient ingredientFluid;
	private final int cookTime;
	private final float experience;
	private final boolean isSimple;

	public TofuPotShapelessRecipe(ResourceLocation id, String group, TofuPotCategory category, ItemStack result, NonNullList<Ingredient> ingredients, FluidIngredient ingredientFluid, int cookTime, float experience) {
		this.id = id;
		this.group = group;
		this.category = category;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
		this.ingredientFluid = ingredientFluid;
		this.cookTime = cookTime;
		this.experience = experience;
	}

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
				if (isSimple)
					stackedcontents.accountStack(itemstack, 1);
				else inputs.add(itemstack);
			}
		}

		return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList) null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.ingredients) != null);

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

		private static NonNullList<Ingredient> itemsFromJson(JsonArray p_44276_) {
			NonNullList<Ingredient> nonnulllist = NonNullList.create();

			for (int i = 0; i < p_44276_.size(); ++i) {
				Ingredient ingredient = Ingredient.fromJson(p_44276_.get(i), false);
				if (true || !ingredient.isEmpty()) { // FORGE: Skip checking if an ingredient is empty during shapeless recipe deserialization to prevent complex ingredients from caching tags too early. Can not be done using a config value due to sync issues.
					nonnulllist.add(ingredient);
				}
			}

			return nonnulllist;
		}

		@Override
		public TofuPotShapelessRecipe fromJson(ResourceLocation p_44290_, JsonObject p_44291_) {
			String s = GsonHelper.getAsString(p_44291_, "group", "");
			TofuPotCategory craftingbookcategory = TofuPotCategory.CODEC.byName(GsonHelper.getAsString(p_44291_, "category", (String) null), TofuPotCategory.MISC);
			NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(p_44291_, "ingredients"));
			FluidIngredient fluid = FluidIngredient.fromJson(GsonHelper.getAsJsonObject(p_44291_, "fluid"));
			int cookTime = GsonHelper.getAsInt(p_44291_, "cook_time", 300);

			float experience = GsonHelper.getAsFloat(p_44291_, "exp", 0);

			if (nonnulllist.isEmpty()) {
				throw new JsonParseException("No ingredients for shapeless recipe");
			} else if (nonnulllist.size() > 3 * 4) {
				throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + (3 * 4));
			} else {
				ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44291_, "result"));
				return new TofuPotShapelessRecipe(p_44290_, s, craftingbookcategory, itemstack, nonnulllist, fluid, cookTime, experience);
			}
		}

		@Override
		public @Nullable TofuPotShapelessRecipe fromNetwork(ResourceLocation p_44105_, FriendlyByteBuf buffer) {
			String s = buffer.readUtf();
			TofuPotCategory craftingbookcategory = buffer.readEnum(TofuPotCategory.class);
			int i = buffer.readVarInt();
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < nonnulllist.size(); ++j) {
				nonnulllist.set(j, Ingredient.fromNetwork(buffer));
			}

			ItemStack itemstack = buffer.readItem();
			FluidIngredient fluidIngredient = FluidIngredient.fromNetwork(buffer);
			return new TofuPotShapelessRecipe(p_44105_, s, craftingbookcategory, itemstack, nonnulllist, fluidIngredient, buffer.readInt(), buffer.readFloat());

		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, TofuPotShapelessRecipe recipe) {
			buffer.writeUtf(recipe.group);
			buffer.writeEnum(recipe.category);
			buffer.writeVarInt(recipe.ingredients.size());

			buffer.writeVarInt(recipe.ingredients.size());

			for (Ingredient ingredient : recipe.ingredients) {
				ingredient.toNetwork(buffer);
			}
			buffer.writeItem(recipe.result);
			recipe.fluidIngredient().toNetwork(buffer);
			buffer.writeInt(recipe.cookTime);
			buffer.writeFloat(recipe.experience);
		}
	}
}
