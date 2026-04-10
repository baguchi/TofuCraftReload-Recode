package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.fluid.FluidContainer;
import baguchi.tofucraft.client.recipe.TofuPotRecipeBookComponent;
import baguchi.tofucraft.inventory.TofuPotMenu;
import baguchi.tofucraft.utils.ClientUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class TofuPotScreen extends AbstractRecipeBookScreen<TofuPotMenu> {
	private static final WidgetSprites RECIPE_BUTTON = new WidgetSprites(Identifier.withDefaultNamespace("recipe_book/button"), Identifier.withDefaultNamespace("recipe_book/button"));
	private static final Identifier BACKGROUND_TEXTURE = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tofu_pot.png");
	private static final Rectangle HEAT_ICON = new Rectangle(14, 43, 14, 14);
	private static final Rectangle PROGRESS_ARROW = new Rectangle(103, 38, 24, 17);

	private int tick;

	public TofuPotScreen(TofuPotMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, new TofuPotRecipeBookComponent(screenContainer), inv, titleIn);
	}

	@Override
	public void init() {
		super.init();
		this.imageHeight = 178;
		this.titleLabelX = 100;
		this.inventoryLabelY = this.imageHeight - 96 + 2;
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.tick++;
	}

	@Override
	protected ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(this.leftPos + 5, this.height / 2 - 19);
	}


	@Override
	public void extractBackground(GuiGraphicsExtractor gui, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(gui, mouseX, mouseY, partialTicks);
		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		// Render heat icon
		if (this.menu.isHeated()) {
			gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height, 256, 256);
		}

		FluidContainer fluidTank = this.menu.blockEntity.fluidTank;
		int heightInd = (int) (44.0F * fluidTank.getAmountAsInt(0) / fluidTank.getCapacityAsInt(0, fluidTank.getResource(0)));
		if (heightInd > 0)
			ClientUtils.renderFluidStack(gui, gui.pose(), this.leftPos + 158, this.topPos + 69, 10, heightInd, fluidTank.getResource(0).getFluid());

		// Render progress arrow
		int l = this.menu.getCookProgressionScaled();
		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height, 256, 256);
	}
}