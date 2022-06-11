package baguchan.tofucraft.compat.jei;


/*
public class BitternCategory implements IRecipeCategory<BitternRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(TofuCraftReload.MODID, "bittern");
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public BitternCategory(IGuiHelper helper) {
		title = new TranslatableComponent("tofucraft.jei.bittern");
		ResourceLocation backgroundImage = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/general_jei_recipe.png");
		background = helper.createDrawable(backgroundImage, 16, 16, 144, 54);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TofuItems.BITTERN_BOTTLE.get()));
		arrow = helper.drawableBuilder(backgroundImage, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends BitternRecipe> getRecipeClass() {
		return BitternRecipe.class;
	}

	@Override
	public RecipeType<BitternRecipe> getRecipeType() {
		return JEIPlugin.BITTERN_JEI_TYPE;
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BitternRecipe recipe, IFocusGroup focuses) {
		FluidIngredient recipeIngredients = recipe.getFluid();
		int borderSlotSize = 18;
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 18)
				.setFluidRenderer(1000, false, 16, 16)
				.addIngredients(ForgeTypes.FLUID_STACK, Arrays.asList(recipeIngredients.getFluids()));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 18).addItemStack(recipe.getResultItem());

	}

	@Override
	public void draw(BitternRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {
		arrow.draw(matrixStack, 72 - 17, 35 - 17);
	}
}*/
