package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.fluid.FluidContainer;
import baguchi.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import baguchi.tofucraft.client.ClientProxy;
import baguchi.tofucraft.inventory.TFStorageMenu;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.utils.ClientUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

import java.util.Optional;


public class TFStorageScreen extends AbstractContainerScreen<TFStorageMenu> {
	private static final Identifier texture = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_storage.png");
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
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		super.extractRenderState(graphics, mouseX, mouseY, a);
		this.extractOnboardingTooltips(graphics, mouseX, mouseY);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(graphics, mouseX, mouseY, partialTicks);

		int i = this.leftPos;
		int j = this.topPos;
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		if (ClientProxy.PROXY.getRefrencedTE() instanceof TFStorageBlockEntity && !((TFStorageBlockEntity) ClientProxy.PROXY.getRefrencedTE()).getTank().getResource(0).isEmpty()) {
			FluidContainer fluidTank = ((TFStorageBlockEntity) ClientProxy.PROXY.getRefrencedTE()).getTank();
			int heightInd = (int) (44.0F * fluidTank.getAmountAsInt(0) / fluidTank.getCapacityAsInt(0, FluidResource.of(TofuFluids.SOYMILK)));
			if (heightInd > 0)
				ClientUtils.renderFluidStack(graphics, graphics.pose(), i + 145, j + 69, 10, heightInd, fluidTank.getResource(0).getFluid());
		}
		graphics.pose().pushMatrix();
		FluidStack fluidTank2 = new FluidStack(TofuFluids.SOYMILK_FLOW.get(), 1000);
		int heightInd2 = (int) (44.0F * menu.getTFEnergy() / menu.getTFMaxEnergy());
		if (heightInd2 > 0)
			ClientUtils.renderFluidStack(graphics, graphics.pose(), i + 76, j + 69, 10, heightInd2, fluidTank2.getFluid());
		graphics.pose().popMatrix();
	}

	private void extractOnboardingTooltips(GuiGraphicsExtractor p_281668_, int p_267192_, int p_266859_) {
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