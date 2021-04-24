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

	public void renderByItem(ItemStack itemStackIn, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Item item = itemStackIn.getItem();
		if (item == TofuItems.TOFUISHI_SHIELD) {
			matrixStackIn.pushPose();
			matrixStackIn.scale(1.0F, -1.0F, -1.0F);
			IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBufferDirect(bufferIn, this.shieldModel.renderType(ISHI_TEXTURE), true, itemStackIn.hasFoil());
			this.shieldModel.handle().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			this.shieldModel.plate().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			matrixStackIn.popPose();
		} else if (item == TofuItems.TOFUMETAL_SHIELD) {
			matrixStackIn.pushPose();
			matrixStackIn.scale(1.0F, -1.0F, -1.0F);
			IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBufferDirect(bufferIn, this.shieldModel.renderType(METAL_TEXTURE), true, itemStackIn.hasFoil());
			this.shieldModel.handle().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			this.shieldModel.plate().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			matrixStackIn.popPose();
		}
	}
}
