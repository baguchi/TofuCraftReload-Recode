package baguchan.tofucraft.client.screen;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import baguchan.tofucraft.inventory.TFStorageMenu;
import baguchan.tofucraft.registry.TofuFluids;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.joml.Matrix4f;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class TFStorageScreen extends AbstractContainerScreen<TFStorageMenu> {
	private static final ResourceLocation texture = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/tf_storage.png");

	private static final Component MISSING_ITEM_TOOLTIP = Component.translatable("container.tofucraft.tf_storage.missing_item_tooltip_consume");
	private static final Component MISSING_ITEM_TOOLTIP_2 = Component.translatable("container.tofucraft.tf_storage.missing_item_tooltip_repair");
	public TFStorageScreen(TFStorageMenu p_i51104_1_, Inventory p_i51104_3_, Component p_i51104_4_) {
		super(p_i51104_1_, p_i51104_3_, p_i51104_4_);
	}

	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void render(GuiGraphics p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.renderBackground(p_230430_1_);
		super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
		this.renderOnboardingTooltips(p_230430_1_, p_230430_2_, p_230430_3_);
	}


	protected void renderBg(GuiGraphics p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_230450_1_.blit(texture, i, j, 0, 0, this.imageWidth, this.imageHeight);

		if (TofuCraftReload.PROXY.getRefrencedTE() instanceof TFStorageBlockEntity && ((TFStorageBlockEntity) TofuCraftReload.PROXY.getRefrencedTE()).getTank().getFluid() != null) {
			FluidTank fluidTank = ((TFStorageBlockEntity) TofuCraftReload.PROXY.getRefrencedTE()).getTank();
			int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
			if (heightInd > 0)
				renderFluidStack(p_230450_1_.pose(), i + 145, j + 69 - heightInd, 10, heightInd, fluidTank.getFluid().getFluid());
		}
		p_230450_1_.pose().pushPose();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		FluidStack fluidTank2 = new FluidStack(TofuFluids.SOYMILK_FLOW.get(), 1000);
		int heightInd2 = (int) (44.0F * menu.getTFEnergy() / menu.getTFMaxEnergy());
		if (heightInd2 > 0)
			renderFluidStack(p_230450_1_.pose(), i + 76, j + 69 - heightInd2, 10, heightInd2, fluidTank2.getFluid());
		p_230450_1_.pose().popPose();
	}


	public static void renderFluidStack(PoseStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
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
		BufferBuilder vertexBuffer = Tesselator.getInstance().getBuilder();
		vertexBuffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
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

				vertexBuffer.vertex(matrix4f, x, y + 16, 0).uv(uMin + uLocalDif, vMax).endVertex();
				vertexBuffer.vertex(matrix4f, shiftedX, y + 16, 0).uv(uMax, vMax).endVertex();
				vertexBuffer.vertex(matrix4f, shiftedX, y + maskTop, 0).uv(uMax, vMin + vLocalDif).endVertex();
				vertexBuffer.vertex(matrix4f, x, y + maskTop, 0).uv(uMin + uLocalDif, vMin + vLocalDif).endVertex();
			}
		}
		BufferUploader.drawWithShader(vertexBuffer.end());
		RenderSystem.disableBlend();
	}

	private void renderOnboardingTooltips(GuiGraphics p_281668_, int p_267192_, int p_266859_) {
		Optional<Component> optional = Optional.empty();

		if (this.hoveredSlot != null) {
			ItemStack itemstack = this.menu.getSlot(0).getItem();
			ItemStack itemstack1 = this.hoveredSlot.getItem();
			if (itemstack.isEmpty()) {
				if (this.hoveredSlot.index == 0) {
					optional = Optional.of(MISSING_ITEM_TOOLTIP);
				}

				if (this.hoveredSlot.index == 1) {
					optional = Optional.of(MISSING_ITEM_TOOLTIP_2);
				}
			}
		}

		optional.ifPresent((p_280863_) -> {
			p_281668_.renderTooltip(this.font, this.font.split(p_280863_, 115), p_267192_, p_266859_);
		});
	}
}