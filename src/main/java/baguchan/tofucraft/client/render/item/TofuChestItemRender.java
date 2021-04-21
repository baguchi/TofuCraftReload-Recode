package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.tileentity.TofuChestTileEntity;
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
public class TofuChestItemRender extends ItemStackTileEntityRenderer {
	private final TofuChestTileEntity tofuchest = new TofuChestTileEntity();

	public void func_239207_a_(ItemStack itemStackIn, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		TileEntityRendererDispatcher.field_147556_a.func_228852_a_((TileEntity) this.tofuchest, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}
}
