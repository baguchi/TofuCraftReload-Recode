package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TFOvenScreen extends AbstractContainerScreen<baguchan.tofucraft.inventory.TFOvenMenu> implements RecipeUpdateListener {
	private static final ResourceLocation texture = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/tf_oven.png");

	public final SmeltingRecipeBookComponent recipeBookComponent = new SmeltingRecipeBookComponent();
	private boolean widthTooNarrow;

	public TFOvenScreen(
			baguchan.tofucraft.inventory.TFOvenMenu p_97825_,
			Inventory p_97827_,
			Component p_97828_
	) {
		super(p_97825_, p_97827_, p_97828_);
	}

	@Override
	public void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		this.addRenderableWidget(new ImageButton(this.leftPos + 20, this.height / 2 - 49, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, p_313431_ -> {
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
			p_313431_.setPosition(this.leftPos + 20, this.height / 2 - 49);
		}));
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.recipeBookComponent.tick();
	}

	@Override
	public void render(GuiGraphics p_282573_, int p_97859_, int p_97860_, float p_97861_) {
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBackground(p_282573_, p_97859_, p_97860_, p_97861_);
			this.recipeBookComponent.render(p_282573_, p_97859_, p_97860_, p_97861_);
		} else {
			super.render(p_282573_, p_97859_, p_97860_, p_97861_);
			this.recipeBookComponent.render(p_282573_, p_97859_, p_97860_, p_97861_);
			this.recipeBookComponent.renderGhostRecipe(p_282573_, this.leftPos, this.topPos, true, p_97861_);
		}

		this.renderTooltip(p_282573_, p_97859_, p_97860_);
		this.recipeBookComponent.renderTooltip(p_282573_, this.leftPos, this.topPos, p_97859_, p_97860_);
	}

	@Override
	protected void renderBg(GuiGraphics p_282928_, float p_281631_, int p_281252_, int p_281891_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_282928_.blit(texture, i, j, 0, 0, this.imageWidth, this.imageHeight);
		int k = 14;
		int l = Mth.ceil(this.menu.getTFForce() * 16.0F);
		p_282928_.blit(texture, i + 72, j + 35, 176, 39, 24, l);


		int j1 = Mth.ceil(this.menu.getProgress() * 28.0F);
		p_282928_.blit(texture, i + 67, j + 15, 176, 14, j1, 17);
	}

	@Override
	public boolean mouseClicked(double p_97834_, double p_97835_, int p_97836_) {
		if (this.recipeBookComponent.mouseClicked(p_97834_, p_97835_, p_97836_)) {
			return true;
		} else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true : super.mouseClicked(p_97834_, p_97835_, p_97836_);
		}
	}

	@Override
	protected void slotClicked(Slot p_97848_, int p_97849_, int p_97850_, ClickType p_97851_) {
		super.slotClicked(p_97848_, p_97849_, p_97850_, p_97851_);
		this.recipeBookComponent.slotClicked(p_97848_);
	}

	@Override
	public boolean keyPressed(int p_97844_, int p_97845_, int p_97846_) {
		return this.recipeBookComponent.keyPressed(p_97844_, p_97845_, p_97846_) ? false : super.keyPressed(p_97844_, p_97845_, p_97846_);
	}

	@Override
	protected boolean hasClickedOutside(double p_97838_, double p_97839_, int p_97840_, int p_97841_, int p_97842_) {
		boolean flag = p_97838_ < (double) p_97840_
				|| p_97839_ < (double) p_97841_
				|| p_97838_ >= (double) (p_97840_ + this.imageWidth)
				|| p_97839_ >= (double) (p_97841_ + this.imageHeight);
		return this.recipeBookComponent.hasClickedOutside(p_97838_, p_97839_, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, p_97842_) && flag;
	}

	@Override
	public boolean charTyped(char p_97831_, int p_97832_) {
		return this.recipeBookComponent.charTyped(p_97831_, p_97832_) ? true : super.charTyped(p_97831_, p_97832_);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}
