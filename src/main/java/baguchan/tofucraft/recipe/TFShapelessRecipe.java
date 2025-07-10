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

public class TFShapelessRecipe implements TFCraftingRecipe {
	final String group;
	final TFCraftingCategory category;
	final ItemStack result;
	final NonNullList<Ingredient> ingredients;
	private final int tfNeed;
	private final boolean isSimple;
	private final ResourceLocation id;

	public TFShapelessRecipe(ResourceLocation id, String group, TFCraftingCategory category, ItemStack result, NonNullList<Ingredient> ingredients, int tfNeed) {
		this.id = id;
		this.group = group;
		this.category = category;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
		this.tfNeed = tfNeed;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return TofuRecipes.RECIPE_TF_CRAFT_SHAPELESS.get();
	}

	@Override
	public String getGroup() {
		return this.group;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public TFCraftingCategory category() {
		return this.category;
	}

	@Override
	public int getNeedTF() {
		return this.tfNeed;
	}


	@Override
	public ItemStack getResultItem(RegistryAccess p_267052_) {
		return this.result;
	}

	@Override
	public ItemStack getResult() {
		return result;
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
	public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
		return this.result.copy();
	}


	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.ingredients.size();
	}

	public static class Serializer implements RecipeSerializer<TFShapelessRecipe> {
		private static final ResourceLocation NAME = new ResourceLocation("minecraft", "crafting_shapeless");

		public TFShapelessRecipe fromJson(ResourceLocation p_44290_, JsonObject p_44291_) {
			String s = GsonHelper.getAsString(p_44291_, "group", "");
			TFCraftingCategory craftingbookcategory = TFCraftingCategory.CODEC.byName(GsonHelper.getAsString(p_44291_, "category", (String) null), TFCraftingCategory.MISC);
			NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(p_44291_, "ingredients"));
			int tf = GsonHelper.getAsInt(p_44291_, "tf", 0);
			if (nonnulllist.isEmpty()) {
				throw new JsonParseException("No ingredients for shapeless recipe");
			} else if (nonnulllist.size() > TFShapedRecipe.MAX_WIDTH * TFShapedRecipe.MAX_HEIGHT) {
				throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + (TFShapedRecipe.MAX_WIDTH * TFShapedRecipe.MAX_HEIGHT));
			} else {
				ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44291_, "result"));
				return new TFShapelessRecipe(p_44290_, s, craftingbookcategory, itemstack, nonnulllist, tf);
			}
		}

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

		public TFShapelessRecipe fromNetwork(ResourceLocation p_44293_, FriendlyByteBuf p_44294_) {
			String s = p_44294_.readUtf();
			TFCraftingCategory craftingbookcategory = p_44294_.readEnum(TFCraftingCategory.class);
			int i = p_44294_.readVarInt();
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < nonnulllist.size(); ++j) {
				nonnulllist.set(j, Ingredient.fromNetwork(p_44294_));
			}

			ItemStack itemstack = p_44294_.readItem();
			int tf = p_44294_.readInt();

			return new TFShapelessRecipe(p_44293_, s, craftingbookcategory, itemstack, nonnulllist, tf);
		}

		public void toNetwork(FriendlyByteBuf p_44281_, TFShapelessRecipe p_44282_) {
			p_44281_.writeUtf(p_44282_.group);
			p_44281_.writeEnum(p_44282_.category);
			p_44281_.writeVarInt(p_44282_.ingredients.size());

			for (Ingredient ingredient : p_44282_.ingredients) {
				ingredient.toNetwork(p_44281_);
			}

			p_44281_.writeItem(p_44282_.result);
			p_44281_.writeInt(p_44282_.tfNeed);
		}
	}
}
