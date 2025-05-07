package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.inventory.TofuPotMenu;
import baguchi.tofucraft.mixin.client.GuiGraphicsAccessor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.joml.Matrix4f;

import java.awt.*;

public class TofuPotScreen extends AbstractRecipeBookScreen<TofuPotMenu> {
	private static final WidgetSprites RECIPE_BUTTON = new WidgetSprites(ResourceLocation.withDefaultNamespace("recipe_book/button"), ResourceLocation.withDefaultNamespace("recipe_book/button"));
	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tofu_pot.png");
	private static final Rectangle HEAT_ICON = new Rectangle(14, 43, 14, 14);
	private static final Rectangle PROGRESS_ARROW = new Rectangle(103, 38, 24, 17);

	private int tick;

	public TofuPotScreen(TofuPotMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, new TofuPotRecipeBookComponent(screenContainer), inv, titleIn);
	}

	@Override
	public void init() {
		super.init();
		this.imageHeight = 177;
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

		gui.blit(RenderType::guiTextured, BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		// Render heat icon
		if (this.menu.isHeated()) {
			gui.blit(RenderType::guiTextured, BACKGROUND_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height, 256, 256);
		}

		FluidTank fluidTank = this.menu.blockEntity.fluidTank;
		int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
		if (heightInd > 0)
			renderFluidStack(gui, gui.pose(), this.leftPos + 158, this.topPos + 69, 10, heightInd, fluidTank.getFluid().getFluid());

		// Render progress arrow
		int l = this.menu.getCookProgressionScaled();
		gui.blit(RenderType::guiTextured, BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height, 256, 256);
	}

	public void renderMissingFluidStack(GuiGraphics guiGraphics, PoseStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
		int color = IClientFluidTypeExtensions.of(fluid).getTintColor();

		float alpha = (float) (color >> 24 & 255) / 255.0F;
		float red = (float) (color >> 16 & 0xFF) / 255.0F;
		float green = (float) (color >> 8 & 0xFF) / 255.0F;
		float blue = (float) (color & 0xFF) / 255.0F;

		alpha = Mth.cos(alpha * this.tick * 0.1F);

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
		VertexConsumer vertexBuffer = ((GuiGraphicsAccessor) guiGraphics).bufferSource().getBuffer(RenderType.guiTextured(TextureAtlas.LOCATION_BLOCKS));
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
	}

	public static void renderFluidStack(GuiGraphics guiGraphics, PoseStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
		int color = IClientFluidTypeExtensions.of(fluid).getTintColor();

		float alpha = (float) (color >> 24 & 255) / 255.0F;
		float red = (float) (color >> 16 & 0xFF) / 255.0F;
		float green = (float) (color >> 8 & 0xFF) / 255.0F;
		float blue = (float) (color & 0xFF) / 255.0F;

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
		VertexConsumer vertexBuffer = ((GuiGraphicsAccessor) guiGraphics).bufferSource().getBuffer(RenderType.guiTextured(TextureAtlas.LOCATION_BLOCKS));
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
	}
}