package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.recipe.TFCraftingTableRecipeBookComponent;
import baguchi.tofucraft.inventory.TFCraftingTableMenu;
import baguchi.tofucraft.mixin.client.AbstractRecipeBookScreenAccessor;
import baguchi.tofucraft.registry.TofuFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.joml.Matrix3x2fStack;

import java.awt.*;

public class TfCraftingTableScreen extends AbstractRecipeBookScreen<TFCraftingTableMenu> {
	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_crafting_table.png");
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
		this.needRefreshTimer = 5;
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
	protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
		// Render UI background
		if (this.minecraft == null)
			return;

		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		int heightInd = (int) (44.0F * this.menu.getTFEnergy() / this.menu.getTFMaxEnergy());
		if (heightInd > 0)
			renderFluidStack(gui, gui.pose(), this.leftPos + 158, this.topPos + 69, 10, heightInd, TofuFluids.SOYMILK.get());


		// Render progress arrow
		int l = this.menu.getCookProgressionScaled();
		gui.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height, 256, 256);
	}


	public static void renderFluidStack(GuiGraphics guiGraphics, Matrix3x2fStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
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
		guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, xPosition, yPosition - desiredHeight, desiredWidth, desiredHeight, color);

	}

	@Override
	protected void renderSlots(GuiGraphics p_376313_) {
		((AbstractRecipeBookScreenAccessor) this).getRecipeBookComponent().renderGhostRecipe(p_376313_, this.isBiggerResultSlot());

		for (Slot slot : this.menu.slots) {
			if (slot.isActive()) {
				this.renderSlot(p_376313_, slot);
			}
		}

	}
}