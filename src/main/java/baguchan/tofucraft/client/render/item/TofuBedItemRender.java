package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.tileentity.TofuBedTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuBedItemRender extends ItemStackTileEntityRenderer {
	private final TofuBedTileEntity bed = new TofuBedTileEntity();

	public void renderByItem(ItemStack itemStackIn, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		TileEntityRendererDispatcher.instance.renderItem((TileEntity) this.bed, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}
}
