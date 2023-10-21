package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TofuWorkStationRecipe implements IWorkRecipe {
	protected final ResourceLocation id;

	protected Ingredient baseIngredient;
	protected Ingredient ingredient;
	protected Ingredient subIngredient;

	final ItemStack result;

	public TofuWorkStationRecipe(ResourceLocation id, Ingredient baseIngredient, Ingredient ingredient, Ingredient subIngredient, ItemStack results) {
		this.id = id;
		this.baseIngredient = baseIngredient;
		this.ingredient = ingredient;
		this.subIngredient = subIngredient;
		this.result = results;
	}

	@Override
	public boolean matches(Container p_266855_, Level p_266781_) {
		return this.baseIngredient.test(p_266855_.getItem(0)) && this.ingredient.test(p_266855_.getItem(1)) && this.subIngredient.test(p_266855_.getItem(2));
	}

	@Override
	public ItemStack assemble(Container p_267036_, RegistryAccess p_266699_) {
		ItemStack itemstack = this.result.copy();
		CompoundTag compoundtag = p_267036_.getItem(0).getTag();
		if (compoundtag != null) {
			itemstack.setTag(compoundtag.copy());
		}

		return itemstack;
	}

	@Override
	public boolean isIngredient(ItemStack p_266982_) {
		return this.ingredient.test(p_266982_);
	}

	@Override
	public boolean isBaseIngredient(ItemStack p_266962_) {
		return this.baseIngredient.test(p_266962_);
	}

	@Override
	public boolean isSubIngredient(ItemStack p_267132_) {
		return this.subIngredient.test(p_267132_);
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public Ingredient getSubIngredient() {
		return subIngredient;
	}

	public Ingredient getBaseIngredient() {
		return baseIngredient;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return this.result;
	}

	public ItemStack getResult() {
		return result;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {

		return TofuRecipes.RECIPE_TOFU_WORK_STATION.get();
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}


	@Override
	public RecipeType<?> getType() {

		return TofuRecipes.RECIPETYPE_TOFU_WORK_STATION.get();
	}

	public static class Serializer implements RecipeSerializer<TofuWorkStationRecipe> {
		@Override
		public TofuWorkStationRecipe fromJson(ResourceLocation p_44103_, JsonObject p_44104_) {
			final Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44104_, "base"));
			final Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44104_, "ingredient"));
			final Ingredient sub = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44104_, "sub"));
			final ItemStack results = itemStackFromJson(GsonHelper.getAsJsonObject(p_44104_, "result"));

			return new TofuWorkStationRecipe(p_44103_, base, ingredient1, sub, results);
		}

		public static ItemStack itemStackFromJson(JsonObject p_151275_) {
			return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(p_151275_, false, true);
		}

		@Override
		public @Nullable TofuWorkStationRecipe fromNetwork(ResourceLocation p_44105_, FriendlyByteBuf p_44106_) {
			Ingredient ingredient = Ingredient.fromNetwork(p_44106_);
			Ingredient ingredient1 = Ingredient.fromNetwork(p_44106_);
			Ingredient ingredient2 = Ingredient.fromNetwork(p_44106_);
			ItemStack itemstack = p_44106_.readItem();
			return new TofuWorkStationRecipe(p_44105_, ingredient, ingredient1, ingredient2, itemstack);

		}

		public void toNetwork(FriendlyByteBuf p_266746_, TofuWorkStationRecipe p_266927_) {
			p_266927_.baseIngredient.toNetwork(p_266746_);
			p_266927_.ingredient.toNetwork(p_266746_);
			p_266927_.subIngredient.toNetwork(p_266746_);
			p_266746_.writeItem(p_266927_.result);
		}
	}
}