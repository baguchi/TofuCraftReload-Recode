package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchi.tofucraft.blockentity.fluid.FluidContainer;
import baguchi.tofucraft.client.ClientProxy;
import baguchi.tofucraft.inventory.SaltFurnaceMenu;
import baguchi.tofucraft.registry.TofuFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.joml.Matrix3x2fStack;


public class SaltFurnaceScreen extends AbstractContainerScreen<SaltFurnaceMenu> {
	private static final Identifier texture = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/salt_furnace.png");

	public SaltFurnaceScreen(SaltFurnaceMenu p_i51104_1_, Inventory p_i51104_3_, Component p_i51104_4_) {
		super(p_i51104_1_, p_i51104_3_, p_i51104_4_);
	}

	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float p_230430_4_) {
		super.render(guiGraphics, mouseX, mouseY, p_230430_4_);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_230450_1_.blit(RenderPipelines.GUI_TEXTURED, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
		if (this.menu.isLit()) {
			int k = this.menu.getLitProgress();
			p_230450_1_.blit(RenderPipelines.GUI_TEXTURED, texture, i + 23, j + 36 + 12 - k, 176, 12 - k, 14, k + 1, 256, 256);
		}
		int l = this.menu.getBurnProgress();
		p_230450_1_.blit(RenderPipelines.GUI_TEXTURED, texture, i + 54, j + 54, 176, 14, l + 1, 16, 256, 256);
		p_230450_1_.pose().pushMatrix();
		if (ClientProxy.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity) {
			FluidContainer fluidTank = ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).bitternTank;
			int heightInd = (int) (44.0F * fluidTank.getAmountAsInt(0) / fluidTank.getCapacityAsInt(0, FluidResource.of(TofuFluids.BITTERN)));
			if (heightInd > 0)
				renderFluidStack(p_230450_1_, p_230450_1_.pose(), i + 145, j + 69, 10, heightInd, fluidTank.getResource(0).value());
		}
		p_230450_1_.pose().popMatrix();
		p_230450_1_.pose().pushMatrix();
		if (ClientProxy.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity) {
			FluidContainer fluidTank2 = ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).waterTank;
			int heightInd2 = (int) (44.0F * fluidTank2.getAmountAsInt(0) / fluidTank2.getCapacityAsInt(0, FluidResource.of(Fluids.WATER)));
			if (heightInd2 > 0)
				renderFluidStack(p_230450_1_, p_230450_1_.pose(), i + 158, j + 69, 10, heightInd2, fluidTank2.getResource(0).getFluid());
		}
		p_230450_1_.pose().popMatrix();
	}


	public static void renderFluidStack(GuiGraphics guiGraphics, Matrix3x2fStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.BLOCKS).getSprite(IClientFluidTypeExtensions.of(fluid).getStillTexture());
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
}