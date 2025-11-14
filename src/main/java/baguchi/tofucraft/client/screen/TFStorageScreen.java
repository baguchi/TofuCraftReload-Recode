package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.fluid.FluidContainer;
import baguchi.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import baguchi.tofucraft.client.ClientProxy;
import baguchi.tofucraft.inventory.TFStorageMenu;
import baguchi.tofucraft.registry.TofuFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.joml.Matrix3x2fStack;

import java.util.Optional;


public class TFStorageScreen extends AbstractContainerScreen<TFStorageMenu> {
	private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_storage.png");
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
		super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
		this.renderOnboardingTooltips(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	protected void renderBg(GuiGraphics p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_230450_1_.blit(RenderPipelines.GUI_TEXTURED, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		if (ClientProxy.PROXY.getRefrencedTE() instanceof TFStorageBlockEntity && !((TFStorageBlockEntity) ClientProxy.PROXY.getRefrencedTE()).getTank().getResource(0).isEmpty()) {
			FluidContainer fluidTank = ((TFStorageBlockEntity) ClientProxy.PROXY.getRefrencedTE()).getTank();
			int heightInd = (int) (44.0F * fluidTank.getAmountAsInt(0) / fluidTank.getCapacityAsInt(0, FluidResource.of(TofuFluids.SOYMILK)));
			if (heightInd > 0)
				renderFluidStack(p_230450_1_, p_230450_1_.pose(), i + 145, j + 69, 10, heightInd, fluidTank.getResource(0).getFluid());
		}
		p_230450_1_.pose().pushMatrix();
		FluidStack fluidTank2 = new FluidStack(TofuFluids.SOYMILK_FLOW.get(), 1000);
		int heightInd2 = (int) (44.0F * menu.getTFEnergy() / menu.getTFMaxEnergy());
		if (heightInd2 > 0)
			renderFluidStack(p_230450_1_, p_230450_1_.pose(), i + 76, j + 69, 10, heightInd2, fluidTank2.getFluid());
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
	private void renderOnboardingTooltips(GuiGraphics p_281668_, int p_267192_, int p_266859_) {
		Optional<Component> optional = Optional.empty();

		if (this.hoveredSlot != null) {
			ItemStack itemstack = this.menu.getSlot(0).getItem();
			ItemStack itemstack2 = this.menu.getSlot(1).getItem();
			if (itemstack.isEmpty()) {
				if (this.hoveredSlot.index == 0) {
					optional = Optional.of(MISSING_ITEM_TOOLTIP);
				}
			}
			if (itemstack2.isEmpty()) {
				if (this.hoveredSlot.index == 1) {
					optional = Optional.of(MISSING_ITEM_TOOLTIP_2);
				}
			}
		}

		optional.ifPresent((p_280863_) -> {
			p_281668_.setTooltipForNextFrame(this.font, this.font.split(p_280863_, 115), p_267192_, p_266859_);
		});
	}
}