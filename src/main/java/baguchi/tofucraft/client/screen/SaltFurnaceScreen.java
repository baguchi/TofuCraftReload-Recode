package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchi.tofucraft.blockentity.fluid.FluidContainer;
import baguchi.tofucraft.client.ClientProxy;
import baguchi.tofucraft.inventory.SaltFurnaceMenu;
import baguchi.tofucraft.registry.TofuFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
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
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float p_230430_4_) {
		super.extractRenderState(guiGraphics, mouseX, mouseY, p_230430_4_);
		this.extractTooltip(guiGraphics, mouseX, mouseY);
	}
	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float particalTick) {
		super.extractBackground(graphics, mouseX, mouseY, particalTick);

		int i = this.leftPos;
		int j = this.topPos;
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
		if (this.menu.isLit()) {
			int k = this.menu.getLitProgress();
			graphics.blit(RenderPipelines.GUI_TEXTURED, texture, i + 23, j + 36 + 12 - k, 176, 12 - k, 14, k + 1, 256, 256);
		}
		int l = this.menu.getBurnProgress();
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, i + 54, j + 54, 176, 14, l + 1, 16, 256, 256);
		graphics.pose().pushMatrix();
		if (ClientProxy.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity) {
			FluidContainer fluidTank = ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).bitternTank;
			int heightInd = (int) (44.0F * fluidTank.getAmountAsInt(0) / fluidTank.getCapacityAsInt(0, FluidResource.of(TofuFluids.BITTERN)));
			if (heightInd > 0)
				renderFluidStack(graphics, graphics.pose(), i + 145, j + 69, 10, heightInd, fluidTank.getResource(0).value());
		}
		graphics.pose().popMatrix();
		graphics.pose().pushMatrix();
		if (ClientProxy.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity) {
			FluidContainer fluidTank2 = ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).waterTank;
			int heightInd2 = (int) (44.0F * fluidTank2.getAmountAsInt(0) / fluidTank2.getCapacityAsInt(0, FluidResource.of(Fluids.WATER)));
			if (heightInd2 > 0)
				renderFluidStack(graphics, graphics.pose(), i + 158, j + 69, 10, heightInd2, fluidTank2.getResource(0).getFluid());
		}
		graphics.pose().popMatrix();
	}


	public static void renderFluidStack(GuiGraphicsExtractor guiGraphics, Matrix3x2fStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(fluid.defaultFluidState()).stillMaterial().sprite();

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
		guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, xPosition, yPosition - desiredHeight, desiredWidth, desiredHeight);

	}
}