package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.inventory.TofuWorkStationMenu;
import baguchan.tofucraft.recipe.TofuWorkStationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TofuWorkStationScreen extends AbstractContainerScreen<TofuWorkStationMenu> {
	private static final ResourceLocation BG_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tofu_work_station.png");
	private static final int SCROLLER_WIDTH = 12;
	private static final int SCROLLER_HEIGHT = 15;
	private static final int RECIPES_COLUMNS = 4;
	private static final int RECIPES_ROWS = 3;
	private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
	private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
	private static final int SCROLLER_FULL_HEIGHT = 54;
	private static final int RECIPES_X = 52;
	private static final int RECIPES_Y = 14;
	private float scrollOffs;
	private boolean scrolling;
	private int startIndex;
	private boolean displayRecipes;
	private boolean widthTooNarrow;

	public TofuWorkStationScreen(TofuWorkStationMenu p_99310_, Inventory p_99311_, Component p_99312_) {
		super(p_99310_, p_99311_, p_99312_);
		p_99310_.registerUpdateListener(this::containerChanged);
		--this.titleLabelY;
	}

	@Override
	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	public void render(GuiGraphics p_282508_, int p_98480_, int p_98481_, float p_98482_) {
		super.render(p_282508_, p_98480_, p_98481_, p_98482_);


		this.renderTooltip(p_282508_, p_98480_, p_98481_);
	}

	protected void renderBg(GuiGraphics p_283115_, float p_282453_, int p_282940_, int p_282328_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_283115_.blit(BG_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
		int k = (int) (41.0F * this.scrollOffs);
		int l = this.leftPos + 52;
		int i1 = this.topPos + 14;
		int j1 = this.startIndex + 12;
		this.renderRecipes(p_283115_, l, i1, j1);
	}

	protected void renderTooltip(GuiGraphics p_282396_, int p_283157_, int p_282258_) {
		super.renderTooltip(p_282396_, p_283157_, p_282258_);
		if (this.displayRecipes) {
			int i = this.leftPos + 52;
			int j = this.topPos + 14;
			int k = this.startIndex + 12;
			List<RecipeHolder<TofuWorkStationRecipe>> list = this.menu.getRecipes();

			for (int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); ++l) {
				int i1 = l - this.startIndex;
				int j1 = i + i1 % 4 * 16;
				int k1 = j + i1 / 4 * 18 + 2;
				if (p_283157_ >= j1 && p_283157_ < j1 + 16 && p_282258_ >= k1 && p_282258_ < k1 + 18) {
					p_282396_.renderTooltip(this.font, list.get(l).value().getResultItem(this.minecraft.level.registryAccess()), p_283157_, p_282258_);
				}
			}
		}

	}
	private void renderRecipes(GuiGraphics p_281999_, int p_282658_, int p_282563_, int p_283352_) {
		List<RecipeHolder<TofuWorkStationRecipe>> list = this.menu.getRecipes();

		for (int i = this.startIndex; i < p_283352_ && i < this.menu.getNumRecipes(); ++i) {
			int j = i - this.startIndex;
			int k = p_282658_ + j % 4 * 16;
			int l = j / 4;
			int i1 = p_282563_ + l * 18 + 2;
			p_281999_.renderItem(list.get(i).value().getResultItem(this.minecraft.level.registryAccess()), k, i1);
		}

	}

	public boolean mouseClicked(double p_99318_, double p_99319_, int p_99320_) {
		this.scrolling = false;
		if (this.displayRecipes) {
			int i = this.leftPos + 52;
			int j = this.topPos + 14;
			int k = this.startIndex + 12;

			for (int l = this.startIndex; l < k; ++l) {
				int i1 = l - this.startIndex;
				double d0 = p_99318_ - (double) (i + i1 % 4 * 16);
				double d1 = p_99319_ - (double) (j + i1 / 4 * 18);
				if (d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D && this.menu.clickMenuButton(this.minecraft.player, l)) {
					Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
					this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
					return true;
				}
			}

			i = this.leftPos + 119;
			j = this.topPos + 9;
			if (p_99318_ >= (double) i && p_99318_ < (double) (i + 12) && p_99319_ >= (double) j && p_99319_ < (double) (j + 54)) {
				this.scrolling = true;
			}
		}
		return super.mouseClicked(p_99318_, p_99319_, p_99320_);

	}

	public boolean mouseDragged(double p_99322_, double p_99323_, int p_99324_, double p_99325_, double p_99326_) {
		if (this.scrolling && this.isScrollBarActive()) {
			int i = this.topPos + 14;
			int j = i + 54;
			this.scrollOffs = ((float) p_99323_ - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
			this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5D) * 4;
			return true;
		} else {
			return super.mouseDragged(p_99322_, p_99323_, p_99324_, p_99325_, p_99326_);
		}
	}

	public boolean mouseScrolled(double p_99314_, double p_99315_, double p_99316_, double p_297300_) {
		if (this.isScrollBarActive()) {
			int i = this.getOffscreenRows();
			float f = (float) p_297300_ / (float) i;
			this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) i) + 0.5D) * 4;
		}

		return true;
	}

	private boolean isScrollBarActive() {
		return this.displayRecipes && this.menu.getNumRecipes() > 12;
	}

	protected int getOffscreenRows() {
		return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
	}

	private void containerChanged() {
		this.displayRecipes = this.menu.hasInputItem();
		if (!this.displayRecipes) {
			this.scrollOffs = 0.0F;
			this.startIndex = 0;
		}

	}

	@Override
	protected boolean isHovering(int p_98462_, int p_98463_, int p_98464_, int p_98465_, double p_98466_, double p_98467_) {
		return (!this.widthTooNarrow) && super.isHovering(p_98462_, p_98463_, p_98464_, p_98465_, p_98466_, p_98467_);
	}

	@Override
	protected boolean hasClickedOutside(double p_98456_, double p_98457_, int p_98458_, int p_98459_, int p_98460_) {
		boolean flag = p_98456_ < (double) p_98458_
				|| p_98457_ < (double) p_98459_
				|| p_98456_ >= (double) (p_98458_ + this.imageWidth)
				|| p_98457_ >= (double) (p_98459_ + this.imageHeight);
		return flag;
	}
}