package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.block.FoodPlateBlock;
import baguchi.tofucraft.blockentity.FoodPlateBlockEntity;
import baguchi.tofucraft.client.render.blockentity.state.FoodPlateRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FoodPlateRender implements BlockEntityRenderer<FoodPlateBlockEntity, FoodPlateRenderState> {
	public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

	private final RandomSource random = RandomSource.create();
	private ItemModelResolver itemModelResolver;
	private BlockModelResolver blockModelResolver;

	public FoodPlateRender(BlockEntityRendererProvider.Context context) {
		this.itemModelResolver = context.itemModelResolver();
		this.blockModelResolver = context.blockModelResolver();
	}

	public int getRenderAmount(ItemStack p_115043_) {
		int i = 1;
		if (p_115043_.getCount() > 48) {
			i = 5;
		} else if (p_115043_.getCount() > 32) {
			i = 4;
		} else if (p_115043_.getCount() > 16) {
			i = 3;
		} else if (p_115043_.getCount() > 1) {
			i = 2;
		}

		return i;
	}

	public void renderItemLayingDown(PoseStack matrixStackIn, Direction direction) {
		matrixStackIn.translate(0.5D, 0.07D, 0.5D);
		float f = -direction.toYRot();
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
		//matrixStackIn.scale(0.6F, 0.6F, 0.6F);
	}

	public void renderBlock(PoseStack matrixStackIn, Direction direction) {
		float f = -direction.toYRot();
		matrixStackIn.translate(0.5D, 0.0D, 0.5D);
		matrixStackIn.scale(0.8F, 0.8F, 0.8F);

		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.translate(-0.5D, 0.0D, -0.5D);
		//matrixStackIn.scale(0.8F, 0.8F, 0.8F);
	}

	@Override
	public FoodPlateRenderState createRenderState() {
		return new FoodPlateRenderState();
	}

	@Override
	public void extractRenderState(FoodPlateBlockEntity foodPlateBlockEntity, FoodPlateRenderState state, float p_446851_, Vec3 p_445788_, @Nullable ModelFeatureRenderer.CrumblingOverlay p_446944_) {
		BlockEntityRenderer.super.extractRenderState(foodPlateBlockEntity, state, p_446851_, p_445788_, p_446944_);
		this.itemModelResolver.updateForTopItem(state.plateItem, foodPlateBlockEntity.getStoredItem(), ItemDisplayContext.GROUND, null, null, 0);
		state.fire = foodPlateBlockEntity.isFire();
		state.renderAmount = getRenderAmount(foodPlateBlockEntity.getStoredItem());
		state.direction = foodPlateBlockEntity.getBlockState().getValue(FoodPlateBlock.FACING).getOpposite();
		if (Block.byItem(foodPlateBlockEntity.getStoredItem().getItem()) instanceof CakeBlock || foodPlateBlockEntity.getStoredItem().is(ItemTags.CANDLES)) {
			this.blockModelResolver.update(state.plateBlock, Block.byItem(foodPlateBlockEntity.getStoredItem().getItem()).defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
		}
	}

	@Override
	public void submit(FoodPlateRenderState foodPlateRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		this.random.setSeed(187);
		renderPlacedItem(foodPlateRenderState, poseStack, submitNodeCollector);
	}

	private void renderPlacedItem(FoodPlateRenderState foodPlateRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
		for (int k = 0; k < foodPlateRenderState.renderAmount; ++k) {
			if (!foodPlateRenderState.plateBlock.isEmpty()) {
				poseStack.pushPose();
				renderBlock(poseStack, foodPlateRenderState.direction);
				//poseStack.translate(-0.5F, 0F, -0.5F);
				foodPlateRenderState.plateBlock.submit(poseStack, submitNodeCollector, foodPlateRenderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
				poseStack.popPose();
			} else if (!foodPlateRenderState.plateItem.isEmpty()) {
				poseStack.pushPose();

				if (k > 0) {
					float f12 = (this.random.nextFloat()) * 0.15F * 0.5F;
					float f14 = (this.random.nextFloat()) * 0.15F * 0.5F;
					poseStack.translate(f12, k * 0.1F * 0.5F, f14);
				}
				renderItemLayingDown(poseStack, foodPlateRenderState.direction);

				foodPlateRenderState.plateItem.submit(poseStack, submitNodeCollector, foodPlateRenderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
				poseStack.popPose();
			}
		}
	}
}