package baguchi.tofucraft.compat.jei;


/*
@JeiPlugin
public class JEIPlugin implements IModPlugin {
	public static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "jei_plugin");

	public static final IRecipeType<HardenRecipe> HARDEN_JEI_TYPE =
			IRecipeType.create(TofuCraftReload.prefix("harden"), HardenRecipe.class);

	public static final IRecipeType<BitternRecipe> BITTERN_JEI_TYPE =
			IRecipeType.create(TofuCraftReload.prefix("bittern"), BitternRecipe.class);
	public static final IRecipeType<TFCraftingRecipe> TF_RECIPE_JEI_TYPE =
			IRecipeType.create(TofuCraftReload.prefix("tf_craft"), TFCraftingRecipe.class);

	public static final IRecipeType<TofuPotRecipe> TOFU_POT_RECIPE_JEI_TYPE =
			IRecipeType.create(TofuCraftReload.prefix("tofu_pot"), TofuPotRecipe.class);

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new HardenCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new BitternCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new TFRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new TofuPotCategory(registry.getJeiHelpers().getGuiHelper()));
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

		registration.addRecipes(HARDEN_JEI_TYPE, JEIContents.getAllHardenRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
		registration.addRecipes(BITTERN_JEI_TYPE, JEIContents.getAllBitternRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
		registration.addRecipes(TF_RECIPE_JEI_TYPE, JEIContents.getAllTFCraftRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
		registration.addRecipes(TOFU_POT_RECIPE_JEI_TYPE, JEIContents.getAllTofuPotRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(TFCraftingTableMenu.class, TofuMenus.TF_CRAFTING_TABLE.get(), TF_RECIPE_JEI_TYPE, 0, 9, 10, 36);
		registration.addRecipeTransferHandler(TofuPotMenu.class, TofuMenus.TOFU_POT.get(), TOFU_POT_RECIPE_JEI_TYPE, 0, 12, 13, 36);
	}


	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addCraftingStation(HARDEN_JEI_TYPE, new ItemStack(Blocks.COBBLESTONE));
		registration.addCraftingStation(BITTERN_JEI_TYPE, new ItemStack(TofuItems.BITTERN_BOTTLE.get()));
		registration.addCraftingStation(TF_RECIPE_JEI_TYPE, new ItemStack(TofuBlocks.TF_CRAFTING_TABLE.get()));
		registration.addCraftingStation(TOFU_POT_RECIPE_JEI_TYPE, new ItemStack(TofuBlocks.TOFU_POT.get()));
	}

	private static void addInfo(IRecipeRegistration registration, Item item) {
		registration.addIngredientInfo(
				new ItemStack(item),
				VanillaTypes.ITEM_STACK,
				Component.translatable(TofuCraftReload.MODID + "." + BuiltInRegistries.ITEM.getKey(item).getPath() + ".jei_desc"));
	}

	private static void addInfo(IRecipeRegistration registration, Item item, Item originalDescItem) {
		registration.addIngredientInfo(
				new ItemStack(item),
				VanillaTypes.ITEM_STACK,
				Component.translatable(TofuCraftReload.MODID + "." + BuiltInRegistries.ITEM.getKey(originalDescItem).getPath() + ".jei_desc"));
	}

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}
}
*/
