package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.inventory.TofuPotMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.joml.Matrix4f;

import javax.annotation.Nonnull;
import java.awt.*;

public class TofuPotScreen extends AbstractContainerScreen<TofuPotMenu> implements RecipeUpdateListener {
	private static final WidgetSprites RECIPE_BUTTON = new WidgetSprites(ResourceLocation.withDefaultNamespace("recipe_book/button"), ResourceLocation.withDefaultNamespace("recipe_book/button"));
	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tofu_pot.png");
	private static final Rectangle HEAT_ICON = new Rectangle(14, 43, 14, 14);
	private static final Rectangle PROGRESS_ARROW = new Rectangle(103, 38, 24, 17);

	private final TofuPotRecipeBookComponent recipeBookComponent = new TofuPotRecipeBookComponent();
	private boolean widthTooNarrow;

	public TofuPotScreen(TofuPotMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	public void init() {
		super.init();
		this.imageHeight = 177;
		this.widthTooNarrow = this.width < 379;
		this.titleLabelX = 100;
		this.inventoryLabelY = this.imageHeight - 96 + 2;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 19, 20, 18, RECIPE_BUTTON, (button) ->
		{
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
			button.setPosition(this.leftPos + 5, this.height / 2 - 19);
		}));

		this.addWidget(this.recipeBookComponent);
		this.setInitialFocus(this.recipeBookComponent);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.recipeBookComponent.tick();
	}

	@Override
	public void render(GuiGraphics gui, final int mouseX, final int mouseY, float partialTicks) {
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBackground(gui, mouseX, mouseY, partialTicks);
			this.recipeBookComponent.render(gui, mouseX, mouseY, partialTicks);
		} else {
			super.render(gui, mouseX, mouseY, partialTicks);
			this.recipeBookComponent.render(gui, mouseX, mouseY, partialTicks);
			this.recipeBookComponent.renderGhostRecipe(gui, this.leftPos, this.topPos, false, partialTicks);
		}
		this.recipeBookComponent.renderTooltip(gui, this.leftPos, this.topPos, mouseX, mouseY);
		this.renderTooltip(gui, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) {
		super.renderLabels(gui, mouseX, mouseY);
		//gui.drawString(this.font, this.playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 4210752, false);
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
		// Render UI background
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		if (this.minecraft == null)
			return;

		gui.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

		// Render heat icon
		if (this.menu.isHeated()) {
			gui.blit(BACKGROUND_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height);
		}

		FluidTank fluidTank = this.menu.blockEntity.fluidTank;
		int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
		if (heightInd > 0)
			renderFluidStack(gui.pose(), this.leftPos + 158, this.topPos + 69, 10, heightInd, fluidTank.getFluid().getFluid());


		// Render progress arrow
		int l = this.menu.getCookProgressionScaled();
		gui.blit(BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
	}

	@Override
	protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
		return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(x, y, width, height, mouseX, mouseY);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int buttonId) {
		if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, buttonId)) {
			this.setFocused(this.recipeBookComponent);
			return true;
		}
		return this.widthTooNarrow && this.recipeBookComponent.isVisible() || super.mouseClicked(mouseX, mouseY, buttonId);
	}

	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int x, int y, int buttonIdx) {
		boolean flag = mouseX < (double) x || mouseY < (double) y || mouseX >= (double) (x + this.imageWidth) || mouseY >= (double) (y + this.imageHeight);
		return flag && this.recipeBookComponent.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, buttonIdx);
	}

	@Override
	protected void slotClicked(Slot slot, int mouseX, int mouseY, ClickType clickType) {
		super.slotClicked(slot, mouseX, mouseY, clickType);
		this.recipeBookComponent.slotClicked(slot);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

//	@Override
//	public void removed() {
//		this.recipeBookComponent.removed();
//		super.removed();
//	}

	@Override
	@Nonnull
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}

	public static void renderFluidStack(PoseStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
		int color = IClientFluidTypeExtensions.of(fluid).getTintColor();

		float alpha = (float) (color >> 24 & 255) / 255.0F;
		float red = (float) (color >> 16 & 0xFF) / 255.0F;
		float green = (float) (color >> 8 & 0xFF) / 255.0F;
		float blue = (float) (color & 0xFF) / 255.0F;

		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
		int xTileCount = desiredWidth / 16;
		int xRemainder = desiredWidth - (xTileCount * 16);
		int yTileCount = desiredHeight / 16;
		int yRemainder = desiredHeight - (yTileCount * 16);
		float uMin = sprite.getU0();
		float uMax = sprite.getU1();
		float vMin = sprite.getV0();
		float vMax = sprite.getV1();
		float uDif = uMax - uMin;
		float vDif = vMax - vMin;
		RenderSystem.enableBlend();
		BufferBuilder vertexBuffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
		Matrix4f matrix4f = stack.last().pose();
		for (int xTile = 0; xTile <= xTileCount; xTile++) {
			int width = (xTile == xTileCount) ? xRemainder : 16;
			if (width == 0) {
				break;
			}
			int x = xPosition + (xTile * 16);
			int maskRight = 16 - width;
			int shiftedX = x + 16 - maskRight;
			float uLocalDif = uDif * maskRight / 16;

			for (int yTile = 0; yTile <= yTileCount; yTile++) {
				int height = (yTile == yTileCount) ? yRemainder : 16;
				if (height == 0) {
					break;
				}
				int y = yPosition - ((yTile + 1) * 16);
				int maskTop = 16 - height;
				float vLocalDif = vDif * maskTop / 16;

				vertexBuffer.addVertex(matrix4f, x, y + 16, 0).setUv(uMin + uLocalDif, vMax).setColor(red, green, blue, alpha);
				vertexBuffer.addVertex(matrix4f, shiftedX, y + 16, 0).setUv(uMax, vMax).setColor(red, green, blue, alpha);
				vertexBuffer.addVertex(matrix4f, shiftedX, y + maskTop, 0).setUv(uMax, vMin + vLocalDif).setColor(red, green, blue, alpha);
				vertexBuffer.addVertex(matrix4f, x, y + maskTop, 0).setUv(uMin + uLocalDif, vMin + vLocalDif).setColor(red, green, blue, alpha);
			}
		}
		BufferUploader.drawWithShader(vertexBuffer.buildOrThrow());
		RenderSystem.disableBlend();
	}
}