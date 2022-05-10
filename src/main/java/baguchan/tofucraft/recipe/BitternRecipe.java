package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class BitternRecipe implements Recipe<Inventory> {

	protected final ResourceLocation id;
	/**
	 * The ingredient used for the Before it hardens tofu.
	 */
	private FluidIngredient fluid;
	/**
	 * This ingredient used for the harden tofu.
	 */
	final ItemStack result;

	public BitternRecipe(ResourceLocation id, FluidIngredient fluid, ItemStack results) {

		this.id = id;
		this.fluid = fluid;
		this.result = results;
	}

	/**
	 * Gets an ingredient that can be used to match an Block as a tofu for this tofu.
	 *
	 * @return An ingredient that can used to match an Block as a tofu for the tofu.
	 */
	public FluidIngredient getFluid() {

		return this.fluid;
	}

	public void setFluid(FluidIngredient tofu) {

		this.fluid = tofu;
	}

	@Override
	public boolean matches(Inventory p_44002_, Level p_44003_) {
		return false;
	}

	@Override
	public ItemStack assemble(Inventory p_44001_) {
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return false;
	}

	@Override
	public ItemStack getResultItem() {
		return this.result;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {

		return TofuRecipes.RECIPE_BITTERN.get();
	}

	@Override
	public RecipeType<?> getType() {

		return TofuRecipes.RECIPETYPE_BITTERN;
	}

	@Override
	public String toString() {

		return this.getId().toString();
	}
}