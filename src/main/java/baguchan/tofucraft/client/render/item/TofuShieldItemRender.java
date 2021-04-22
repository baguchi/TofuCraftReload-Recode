package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuShieldItemRender extends ItemStackTileEntityRenderer {
	private static final ResourceLocation ISHI_TEXTURE = new ResourceLocation("tofucraft", "textures/entity/tofuishi_shield.png");

	private static final ResourceLocation METAL_TEXTURE = new ResourceLocation("tofucraft", "textures/entity/tofumetal_shield.png");

	private final ShieldModel shieldModel = new ShieldModel();

	public void func_239207_a_(ItemStack itemStackIn, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Item item = itemStackIn.getItem();
		if (item == TofuItems.TOFUISHI_SHIELD) {
			matrixStackIn.func_227860_a_();
			matrixStackIn.func_227862_a_(1.0F, -1.0F, -1.0F);
			IVertexBuilder ivertexbuilder = ItemRenderer.func_239391_c_(bufferIn, this.shieldModel.func_228282_a_(ISHI_TEXTURE), true, itemStackIn.func_77962_s());
			this.shieldModel.func_228294_b_().func_228309_a_(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			this.shieldModel.func_228293_a_().func_228309_a_(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			matrixStackIn.func_227865_b_();
		} else if (item == TofuItems.TOFUMETAL_SHIELD) {
			matrixStackIn.func_227860_a_();
			matrixStackIn.func_227862_a_(1.0F, -1.0F, -1.0F);
			IVertexBuilder ivertexbuilder = ItemRenderer.func_239391_c_(bufferIn, this.shieldModel.func_228282_a_(METAL_TEXTURE), true, itemStackIn.func_77962_s());
			this.shieldModel.func_228294_b_().func_228309_a_(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			this.shieldModel.func_228293_a_().func_228309_a_(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			matrixStackIn.func_227865_b_();
		}
	}
}
