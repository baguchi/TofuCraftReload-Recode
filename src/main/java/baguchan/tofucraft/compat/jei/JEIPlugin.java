package baguchan.tofucraft.compat.jei;


/*@JeiPlugin
public class JEIPlugin implements IModPlugin {
	public static final ResourceLocation PLUGIN_ID = new ResourceLocation(TofuCraftReload.MODID, "jei_plugin");

	private static final Minecraft MC = Minecraft.getInstance();

	private static <C extends Container, T extends Recipe<C>> List<T> findRecipesByType(RecipeType<T> type) {
		return MC.level.getRecipeManager().getAllRecipesFor(type);
	}

	public static final mezz.jei.api.recipe.RecipeType<HardenRecipe> HARDEN_JEI_TYPE =
			mezz.jei.api.recipe.RecipeType.create(TofuCraftReload.MODID, "harden", HardenRecipe.class);

	public static final mezz.jei.api.recipe.RecipeType<BitternRecipe> BITTERN_JEI_TYPE =
			mezz.jei.api.recipe.RecipeType.create(TofuCraftReload.MODID, "bittern", BitternRecipe.class);


	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new HardenCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new BitternCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(HARDEN_JEI_TYPE, findRecipesByType(TofuRecipes.RECIPETYPE_HARDER));
		registration.addRecipes(BITTERN_JEI_TYPE, findRecipesByType(TofuRecipes.RECIPETYPE_BITTERN));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(Blocks.COBBLESTONE), HARDEN_JEI_TYPE);
		registration.addRecipeCatalyst(new ItemStack(TofuItems.BITTERN_BOTTLE.get()), BITTERN_JEI_TYPE);
	}

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}
}*/
