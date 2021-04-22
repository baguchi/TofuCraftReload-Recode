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
import net.minecraft.util.text.ITextProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

@OnlyIn(Dist.CLIENT)
public class SaltFurnaceScreen extends ContainerScreen<SaltFurnaceContainer> {
	private static final ResourceLocation texture = new ResourceLocation("tofucraft", "textures/gui/salt_furnace.png");

	public SaltFurnaceScreen(SaltFurnaceContainer p_i51104_1_, PlayerInventory p_i51104_3_, ITextComponent p_i51104_4_) {
		super(p_i51104_1_, p_i51104_3_, p_i51104_4_);
	}

	public void func_231160_c_() {
		super.func_231160_c_();
		this.field_238742_p_ = (this.field_146999_f - this.field_230712_o_.func_238414_a_((ITextProperties) this.field_230704_d_)) / 2;
	}

	public void func_231023_e_() {
		super.func_231023_e_();
	}

	public void func_230430_a_(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		func_230446_a_(p_230430_1_);
		super.func_230430_a_(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		func_230459_a_(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	protected void func_230450_a_(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.field_230706_i_.func_110434_K().func_110577_a(texture);
		int i = this.field_147003_i;
		int j = this.field_147009_r;
		func_238474_b_(p_230450_1_, i, j, 0, 0, this.field_146999_f, this.field_147000_g);
		if (((SaltFurnaceContainer) this.field_147002_h).isLit()) {
			int k = ((SaltFurnaceContainer) this.field_147002_h).getLitProgress();
			func_238474_b_(p_230450_1_, i + 23, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		int l = ((SaltFurnaceContainer) this.field_147002_h).getBurnProgress();
		func_238474_b_(p_230450_1_, i + 54, j + 54, 176, 14, l + 1, 16);
		p_230450_1_.func_227860_a_();
		if (TofuCraftReload.PROXY.getRefrencedTE() instanceof SaltFurnaceTileEntity && ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).bitternTank.getFluid() != null) {
			FluidTank fluidTank = ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).bitternTank;
			int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
			if (heightInd > 0)
				renderFluidStack(i + 145, j + 69 - heightInd, 10, heightInd, 0.0F, fluidTank.getFluid());
		}
		p_230450_1_.func_227865_b_();
		p_230450_1_.func_227860_a_();
		RenderSystem.color4f(0.6F, 0.6F, 1.0F, 1.0F);
		if (TofuCraftReload.PROXY.getRefrencedTE() instanceof SaltFurnaceTileEntity && ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).waterTank.getFluid() != null) {
			FluidTank fluidTank2 = ((SaltFurnaceTileEntity) TofuCraftReload.PROXY.getRefrencedTE()).waterTank;
			int heightInd2 = (int) (44.0F * fluidTank2.getFluidAmount() / fluidTank2.getCapacity());
			if (heightInd2 > 0)
				renderFluidStack(i + 158, j + 69 - heightInd2, 10, heightInd2, 0.0F, fluidTank2.getFluid());
		}
		p_230450_1_.func_227865_b_();
	}

	public void renderFluidStack(int x, int y, int width, int height, float depth, FluidStack fluidStack) {
		this.field_230706_i_.func_110434_K().func_110577_a(AtlasTexture.field_110575_b);
		TextureAtlasSprite sprite = Minecraft.func_71410_x().func_228015_a_(AtlasTexture.field_110575_b).apply(fluidStack.getFluid().getAttributes().getStillTexture());
		Tessellator tessellator = Tessellator.func_178181_a();
		BufferBuilder bufferbuilder = tessellator.func_178180_c();
		float u1 = sprite.func_94209_e();
		float v1 = sprite.func_94206_g();
		do {
			int currentHeight = Math.min(sprite.func_94216_b(), height);
			height -= currentHeight;
			float v2 = sprite.func_94207_b(((16 * currentHeight) / sprite.func_94216_b()));
			int x2 = x;
			int width2 = width;
			do {
				int currentWidth = Math.min(sprite.func_94211_a(), width2);
				width2 -= currentWidth;
				float u2 = sprite.func_94214_a(((16 * currentWidth) / sprite.func_94211_a()));
				bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
				bufferbuilder.func_225582_a_(x2, y, depth).func_225583_a_(u1, v1).func_181675_d();
				bufferbuilder.func_225582_a_(x2, (y + currentHeight), depth).func_225583_a_(u1, v2).func_181675_d();
				bufferbuilder.func_225582_a_((x2 + currentWidth), (y + currentHeight), depth).func_225583_a_(u2, v2).func_181675_d();
				bufferbuilder.func_225582_a_((x2 + currentWidth), y, depth).func_225583_a_(u2, v1).func_181675_d();
				tessellator.func_78381_a();
				x2 += currentWidth;
			} while (width2 > 0);
			y += currentHeight;
		} while (height > 0);
	}
}
