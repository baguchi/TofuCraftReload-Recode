package baguchan.tofucraft.compat.jei;

/*

@JeiPlugin
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
		addInfo(registration, TofuItems.BITTERN_BOTTLE.get());
		addInfo(registration, TofuBlocks.KINUTOFU.get().asItem());
		addInfo(registration, TofuBlocks.MOMENTOFU.get().asItem());
		addInfo(registration, TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_APPLE.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_ANNIN.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_PUDDING.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_PUMPKIN.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_STRAWBERRY.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_FRUITS.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_TEA.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_HONEY.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_RAMUNE.get(), TofuItems.SOYMILK.get());
		addInfo(registration, TofuItems.SOYMILK_SAKURA.get(), TofuItems.SOYMILK.get());
		registration.addRecipes(HARDEN_JEI_TYPE, findRecipesByType(TofuRecipes.RECIPETYPE_HARDER));
		registration.addRecipes(BITTERN_JEI_TYPE, findRecipesByType(TofuRecipes.RECIPETYPE_BITTERN));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(Blocks.COBBLESTONE), HARDEN_JEI_TYPE);
		registration.addRecipeCatalyst(new ItemStack(TofuItems.BITTERN_BOTTLE.get()), BITTERN_JEI_TYPE);
	}

	private static void addInfo(IRecipeRegistration registration, Item item) {
		registration.addIngredientInfo(
				new ItemStack(item),
				VanillaTypes.ITEM_STACK,
				Component.translatable(TofuCraftReload.MODID + "." + ForgeRegistries.ITEMS.getKey(item).getPath() + ".jei_desc"));
	}

	private static void addInfo(IRecipeRegistration registration, Item item, Item originalDescItem) {
		registration.addIngredientInfo(
				new ItemStack(item),
				VanillaTypes.ITEM_STACK,
				Component.translatable(TofuCraftReload.MODID + "." + ForgeRegistries.ITEMS.getKey(originalDescItem).getPath() + ".jei_desc"));
	}

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}
}

*/
