package baguchan.tofucraft.recipe;

import baguchan.tofucraft.registry.TofuRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class HardenInfo implements Recipe<Inventory> {

	protected final ResourceLocation id;
	/**
	 * The ingredient used for the Before it hardens tofu.
	 */
	private Ingredient tofu;
	/**
	 * This ingredient used for the harden tofu.
	 */
	private Ingredient results;

	public HardenInfo(ResourceLocation id, Ingredient tofu, Ingredient results) {

		this.id = id;
		this.tofu = tofu;
		this.results = results;
	}

	/**
	 * Gets an ingredient that can be used to match an Block as a tofu for this tofu.
	 *
	 * @return An ingredient that can used to match an Block as a tofu for the tofu.
	 */
	public Ingredient getTofu() {

		return this.tofu;
	}

	/**
	 * Gets all the possible results when harden the tofu.
	 *
	 * @return An array of harden results for the tofu.
	 */
	public Ingredient getResults() {

		return this.results;
	}

	public void setTofu(Ingredient tofu) {

		this.tofu = tofu;
	}

	public void setResults(Ingredient results) {

		this.results = results;
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
		return null;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {

		return TofuRecipes.RECIPE_HARDER.get();
	}

	@Override
	public RecipeType<?> getType() {

		return TofuRecipes.RECIPETYPE_HARDER;
	}

	@Override
	public String toString() {

		return this.getId().toString();
	}
}