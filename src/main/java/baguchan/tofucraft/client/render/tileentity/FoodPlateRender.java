package baguchan.tofucraft.client.render.tileentity;

import baguchan.tofucraft.block.FoodPlateBlock;
import baguchan.tofucraft.blockentity.FoodPlateBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class FoodPlateRender implements BlockEntityRenderer<FoodPlateBlockEntity> {

	public FoodPlateRender(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(FoodPlateBlockEntity plateBlockEntity, float p_112308_, PoseStack poseStack, MultiBufferSource p_112310_, int p_112311_, int p_112312_) {
		Direction direction = plateBlockEntity.getBlockState().getValue(FoodPlateBlock.FACING).getOpposite();
		ItemStack boardStack = plateBlockEntity.getStoredItem();
		int posLong = (int) plateBlockEntity.getBlockPos().asLong();
		if (!boardStack.isEmpty()) {
			poseStack.pushPose();

			ItemRenderer itemRenderer = Minecraft.getInstance()
					.getItemRenderer();
			boolean isBlockItem = itemRenderer.getModel(boardStack, plateBlockEntity.getLevel(), null, 0)
					.isGui3d();
			if (isBlockItem) {
				renderBlock(poseStack, direction);
			} else {
				renderItemLayingDown(poseStack, direction);
			}
			Minecraft.getInstance().getItemRenderer().renderStatic(boardStack, ItemDisplayContext.FIXED, p_112311_, p_112312_, poseStack, p_112310_, plateBlockEntity.getLevel(), posLong);
			poseStack.popPose();
		}
	}

	public void renderItemLayingDown(PoseStack matrixStackIn, Direction direction) {
		matrixStackIn.translate(0.5D, 0.07D, 0.5D);
		float f = -direction.toYRot();
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
		matrixStackIn.scale(0.6F, 0.6F, 0.6F);
	}

	public void renderBlock(PoseStack matrixStackIn, Direction direction) {
		matrixStackIn.translate(0.5D, 0.25D, 0.5D);
		float f = -direction.toYRot();
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.scale(0.8F, 0.8F, 0.8F);
	}
}
