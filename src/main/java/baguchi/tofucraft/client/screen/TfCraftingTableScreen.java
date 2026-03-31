package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.recipe.TFCraftingTableRecipeBookComponent;
import baguchi.tofucraft.inventory.TFCraftingTableMenu;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.utils.ClientUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class TfCraftingTableScreen extends AbstractRecipeBookScreen<TFCraftingTableMenu> {
	private static final Identifier BACKGROUND_TEXTURE = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_crafting_table.png");
	private static final Rectangle PROGRESS_ARROW = new Rectangle(89, 34, 24, 17);
	private boolean needRefresh;
	private int needRefreshTimer;
	public TfCraftingTableScreen(TFCraftingTableMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, new TFCraftingTableRecipeBookComponent(screenContainer), inv, titleIn);
	}

	@Override
	protected ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(this.leftPos + 5, this.height / 2 - 49);
	}

	public void setNeedRefresh() {
		this.needRefreshTimer = 30;
		this.needRefresh = true;
	}

	@Override
	protected void init() {
		super.init();
		if (this.menu.blockEntity.getRecipeDisplay() != null) {
			this.fillGhostRecipe(this.menu.blockEntity.getRecipeDisplay());
		}
	}

	@Override
	public void containerTick() {
		super.containerTick();

		if (this.needRefresh) {
			if (--this.needRefreshTimer <= 0) {
				this.needRefresh = false;
				if (this.menu.blockEntity.getRecipeDisplay() != null) {
					this.fillGhostRecipe(this.menu.blockEntity.getRecipeDisplay());
				}
			}
		}
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor gui, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(gui, mouseX, mouseY, partialTicks);
		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		int heightInd = (int) (44.0F * this.menu.getTFEnergy() / this.menu.getTFMaxEnergy());
		if (heightInd > 0)
			ClientUtils.renderFluidStack(gui, gui.pose(), this.leftPos + 158, this.topPos + 69, 10, heightInd, TofuFluids.SOYMILK.get());


		// Render progress arrow
		int l = this.menu.getCookProgressionScaled();
		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height, 256, 256);
	}
}