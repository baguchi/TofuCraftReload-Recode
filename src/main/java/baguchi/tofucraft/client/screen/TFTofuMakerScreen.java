package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.recipe.TFTofuMakerRecipeBookComponent;
import baguchi.tofucraft.inventory.TFTofuMakerMenu;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuRecipeBookCategory;
import baguchi.tofucraft.utils.ClientUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.List;

public class TFTofuMakerScreen extends AbstractRecipeBookScreen<TFTofuMakerMenu> implements RecipeUpdateListener {
	private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.smeltable");
	private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
			new RecipeBookComponent.TabInfo(TofuItems.TOFU_CRAFTERS_BOOK.get(), TofuRecipeBookCategory.TF_TOFU_MAKER_SEARCH),
			new RecipeBookComponent.TabInfo(TofuItems.TOFUKINU.get(), TofuRecipeBookCategory.TF_TOFU_MAKER.get())
	);


	private static final Identifier texture = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_tofu_maker.png");
	private boolean widthTooNarrow;

	public TFTofuMakerScreen(
			TFTofuMakerMenu p_97825_,
			Inventory p_97827_,
			Component p_97828_
	) {
		super(p_97825_, new TFTofuMakerRecipeBookComponent(p_97825_, FILTER_NAME, TABS), p_97827_, p_97828_);
	}

	@Override
	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	protected ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(this.leftPos + 10, this.height / 2 - 49);
	}


	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int p_281252_, int p_281891_, float p_281631_) {
		super.extractBackground(guiGraphics, p_281252_, p_281891_, p_281631_);
		int i = this.leftPos;
		int j = this.topPos;
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		int j1 = Mth.ceil(this.menu.getProgress() * 21.0F);

		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, i + 47 + 40, j + 26, 176, 0, j1, 30, 256, 256);


		guiGraphics.pose().pushMatrix();
		FluidStack fluidTank2 = new FluidStack(TofuFluids.SOYMILK_FLOW.get(), 1000);
		int heightInd2 = (int) (44.0F * menu.getTFForce());
		if (heightInd2 > 0)
			ClientUtils.renderFluidStack(guiGraphics, guiGraphics.pose(), i + 76 + 40, j + 69, 10, heightInd2, fluidTank2.getFluid());
		guiGraphics.pose().popMatrix();
	}
}
