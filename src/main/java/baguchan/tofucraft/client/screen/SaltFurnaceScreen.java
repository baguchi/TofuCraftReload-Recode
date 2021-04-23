package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.inventory.SaltFurnaceContainer;
import baguchan.tofucraft.tileentity.SaltFurnaceTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class SaltFurnaceScreen extends ContainerScreen<SaltFurnaceContainer> {
	private static final ResourceLocation texture = new ResourceLocation("tofucraft", "textures/gui/salt_furnace.png");

	public SaltFurnaceScreen(SaltFurnaceContainer p_i51104_1_, PlayerInventory p_i51104_3_, ITextComponent p_i51104_4_) {
		super(p_i51104_1_, p_i51104_3_, p_i51104_4_);
	}

	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.renderBackground(p_230430_1_);
		super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(texture);
		int i = this.leftPos;
		int j = this.topPos;
		this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, this.imageHeight);
		if (this.menu.isLit()) {
			int k = this.menu.getLitProgress();
			blit(p_230450_1_, i + 23, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		int l = this.menu.getBurnProgress();
		blit(p_230450_1_, i + 54, j + 54, 176, 14, l + 1, 16);
		p_230450_1_.pushPose();
		if (TofuCraftReload.PROXY.getRefrencedTE() instanceof SaltFurnaceTileEntity && ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).bitternTank.getFluid() != null) {
			FluidTank fluidTank = ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).bitternTank;
			int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
			if (heightInd > 0)
				renderFluidStack(i + 145, j + 69 - heightInd, 10, heightInd, 0.0F, fluidTank.getFluid());
		}
		p_230450_1_.popPose();
		p_230450_1_.pushPose();
		RenderSystem.color4f(0.6F, 0.6F, 1.0F, 1.0F);
		if (TofuCraftReload.PROXY.getRefrencedTE() instanceof SaltFurnaceTileEntity && ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).waterTank.getFluid() != null) {
			FluidTank fluidTank2 = ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).waterTank;
			int heightInd2 = (int) (44.0F * fluidTank2.getFluidAmount() / fluidTank2.getCapacity());
			if (heightInd2 > 0)
				renderFluidStack(i + 158, j + 69 - heightInd2, 10, heightInd2, 0.0F, fluidTank2.getFluid());
		}
		p_230450_1_.popPose();
	}


	public static void renderFluidStack(int x, int y, int width, int height, float depth, FluidStack fluidStack) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(fluidStack.getFluid().getAttributes().getStillTexture());
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuilder();
		float u1 = sprite.getU0();
		float v1 = sprite.getV0();
		do {
			int currentHeight = Math.min(sprite.getHeight(), height);
			height -= currentHeight;
			float v2 = sprite.getV((16 * currentHeight) / (float) sprite.getHeight());
			int x2 = x;
			int width2 = width;
			do {
				int currentWidth = Math.min(sprite.getWidth(), width2);
				width2 -= currentWidth;
				float u2 = sprite.getU((16 * currentWidth) / (float) sprite.getWidth());
				bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				bufferbuilder.vertex(x2, y, depth).uv(u1, v1).endVertex();
				bufferbuilder.vertex(x2, y + currentHeight, depth).uv(u1, v2).endVertex();
				bufferbuilder.vertex(x2 + currentWidth, y + currentHeight, depth).uv(u2, v2).endVertex();
				bufferbuilder.vertex(x2 + currentWidth, y, depth).uv(u2, v1).endVertex();
				tessellator.end();
				x2 += currentWidth;
			} while (width2 > 0);

			y += currentHeight;
		} while (height > 0);
	}
}
