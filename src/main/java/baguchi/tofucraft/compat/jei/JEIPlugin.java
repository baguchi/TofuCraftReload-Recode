package baguchi.tofucraft.compat.jei;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.screen.TFOvenScreen;
import baguchi.tofucraft.client.screen.TfCraftingTableScreen;
import baguchi.tofucraft.client.screen.TofuPotScreen;
import baguchi.tofucraft.compat.jei.extension.BucketToBottleExtension;
import baguchi.tofucraft.compat.jei.extension.FluidBucketExtension;
import baguchi.tofucraft.inventory.TFCraftingTableMenu;
import baguchi.tofucraft.inventory.TFOvenMenu;
import baguchi.tofucraft.inventory.TofuPotMenu;
import baguchi.tofucraft.recipe.BitternRecipe;
import baguchi.tofucraft.recipe.BucketToBottleRecipe;
import baguchi.tofucraft.recipe.FluidBucketRecipe;
import baguchi.tofucraft.recipe.HardenRecipe;
import baguchi.tofucraft.recipe.TFCraftingRecipe;
import baguchi.tofucraft.recipe.TofuPotRecipe;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuMenus;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.common.Internal;
import mezz.jei.library.plugins.vanilla.gui.RecipeBookGuiHandler;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
	public static final Identifier PLUGIN_ID = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "jei_plugin");

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
		addInfo(registration, TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_APPLE_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_ANNIN_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_PUDDING_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_PUMPKIN_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_STRAWBERRY_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_FRUITS_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_TEA_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_HONEY_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_RAMUNE_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());
		addInfo(registration, TofuItems.SOYMILK_SAKURA_BOTTLE.get(), TofuItems.SOYMILK_BOTTLE.get());

		registration.addRecipes(HARDEN_JEI_TYPE, JEIContents.getAllHardenRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
		registration.addRecipes(BITTERN_JEI_TYPE, JEIContents.getAllBitternRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
		registration.addRecipes(TF_RECIPE_JEI_TYPE, JEIContents.getAllTFCraftRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
		registration.addRecipes(TOFU_POT_RECIPE_JEI_TYPE, JEIContents.getAllTofuPotRecipes(Internal.getClientSyncedRecipes()).stream().map(RecipeHolder::value).toList());
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		registration.getCraftingCategory().addExtension(FluidBucketRecipe.class, new FluidBucketExtension());
		registration.getCraftingCategory().addExtension(BucketToBottleRecipe.class, new BucketToBottleExtension());
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(TFCraftingTableMenu.class, TofuMenus.TF_CRAFTING_TABLE.get(), TF_RECIPE_JEI_TYPE, 0, 9, 10, 36);
		registration.addRecipeTransferHandler(TofuPotMenu.class, TofuMenus.TOFU_POT.get(), TOFU_POT_RECIPE_JEI_TYPE, 0, 12, 13, 36);
		registration.addRecipeTransferHandler(TFOvenMenu.class, TofuMenus.TF_OVEN.get(), RecipeTypes.SMELTING, 0, 1, 2, 36);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(TofuPotScreen.class, 103, 36, 24, 23, TOFU_POT_RECIPE_JEI_TYPE);
		registration.addRecipeClickArea(TfCraftingTableScreen.class, 88, 32, 24, 23, TF_RECIPE_JEI_TYPE);
		registration.addRecipeClickArea(TFOvenScreen.class, 70, 14, 24, 23, RecipeTypes.SMELTING);
		registration.addGuiContainerHandler(TofuPotScreen.class, new RecipeBookGuiHandler<>());
		registration.addGuiContainerHandler(TfCraftingTableScreen.class, new RecipeBookGuiHandler<>());
		registration.addGuiContainerHandler(TFOvenScreen.class, new RecipeBookGuiHandler<>());

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
	public Identifier getPluginUid() {
		return PLUGIN_ID;
	}
}
