package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchan.tofucraft.inventory.SaltFurnaceMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

@OnlyIn(Dist.CLIENT)
public class SaltFurnaceScreen extends AbstractContainerScreen<SaltFurnaceMenu> {
	private static final ResourceLocation texture = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/salt_furnace.png");

	public SaltFurnaceScreen(SaltFurnaceMenu p_i51104_1_, Inventory p_i51104_3_, Component p_i51104_4_) {
		super(p_i51104_1_, p_i51104_3_, p_i51104_4_);
	}

	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	public void render(GuiGraphics p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.renderBackground(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	protected void renderBg(GuiGraphics p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_230450_1_.blit(texture, i, j, 0, 0, this.imageWidth, this.imageHeight);
		if (this.menu.isLit()) {
			int k = this.menu.getLitProgress();
			p_230450_1_.blit(texture, i + 23, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		int l = this.menu.getBurnProgress();
		p_230450_1_.blit(texture, i + 54, j + 54, 176, 14, l + 1, 16);
		p_230450_1_.pose().pushPose();
		if (TofuCraftReload.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity && ((SaltFurnaceBlockEntity) TofuCraftReload.PROXY.getRefrencedTE()).bitternTank.getFluid() != null) {
			FluidTank fluidTank = ((SaltFurnaceBlockEntity) TofuCraftReload.PROXY.getRefrencedTE()).bitternTank;
			int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
			if (heightInd > 0)
				renderFluidStack(i + 145, j + 69 - heightInd, 10, heightInd, 0.0F, fluidTank.getFluid());
		}
		p_230450_1_.pose().popPose();
		p_230450_1_.pose().pushPose();
		if (TofuCraftReload.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity && ((SaltFurnaceBlockEntity) TofuCraftReload.PROXY.getRefrencedTE()).waterTank.getFluid() != null) {
			FluidTank fluidTank2 = ((SaltFurnaceBlockEntity) TofuCraftReload.PROXY.getRefrencedTE()).waterTank;
			int heightInd2 = (int) (44.0F * fluidTank2.getFluidAmount() / fluidTank2.getCapacity());
			if (heightInd2 > 0)
				renderFluidStack(i + 158, j + 69 - heightInd2, 10, heightInd2, 0.0F, fluidTank2.getFluid());
		}
		p_230450_1_.pose().popPose();
	}


	public static void renderFluidStack(int x, int y, int width, int height, float depth, FluidStack fluidStack) {
		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		IClientFluidTypeExtensions props = IClientFluidTypeExtensions.of(fluidStack.getFluid());
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(props.getStillTexture());

		int col = props.getTintColor(fluidStack);
		Tesselator tessellator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuilder();
		float u1 = sprite.getU0();
		float v1 = sprite.getV0();
		float u2 = sprite.getU1();
		float v2 = sprite.getV1();
		do {
			int currentHeight = Math.min(sprite.getX(), height);
			height -= currentHeight;
			int x2 = x;
			int width2 = width;
			do {
				int currentWidth = Math.min(sprite.getY(), width2);
				width2 -= currentWidth;
				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
				bufferbuilder.vertex(x2, y, depth).uv(u1, v1).color((col >> 16 & 255), (col >> 8 & 255), (col & 255), 255).endVertex();
				bufferbuilder.vertex(x2, y + currentHeight, depth).uv(u1, v2).color((col >> 16 & 255), (col >> 8 & 255), (col & 255), 255).endVertex();
				bufferbuilder.vertex(x2 + currentWidth, y + currentHeight, depth).uv(u2, v2).color((col >> 16 & 255), (col >> 8 & 255), (col & 255), 255).endVertex();
				bufferbuilder.vertex(x2 + currentWidth, y, depth).uv(u2, v1).color((col >> 16 & 255), (col >> 8 & 255), (col & 255), 255).endVertex();
				tessellator.end();
				x2 += currentWidth;
			} while (width2 > 0);

			y += currentHeight;
		} while (height > 0);
		bufferbuilder.unsetDefaultColor();
		RenderSystem.disableBlend();
	}
}