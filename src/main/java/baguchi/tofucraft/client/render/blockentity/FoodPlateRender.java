package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.blockentity.FoodPlateBlockEntity;
import baguchi.tofucraft.client.render.blockentity.state.FoodPlateRenderState;
import baguchi.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FoodPlateRender implements BlockEntityRenderer<FoodPlateBlockEntity, FoodPlateRenderState> {
	private final RandomSource random = RandomSource.create();
	private ItemModelResolver itemModelResolver;

	public FoodPlateRender(BlockEntityRendererProvider.Context p_174114_) {
		this.itemModelResolver = p_174114_.itemModelResolver();
	}

	public FoodPlateRender() {
	}

	public void renderInHand(FoodPlateRenderState foodPlateRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
		float f = 90.0F;
		poseStack.pushPose();
		//poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.translate(0F, 0.1F, 0F);
		poseStack.pushPose();
		BlockState state = TofuBlocks.FOODPLATE.get().defaultBlockState();
		BlockStateModel blockstatemodel = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
		//poseStack.translate(-0.5F, 0F, -0.5F);
		submitNodeCollector.submitBlockModel(poseStack, ItemBlockRenderTypes.getRenderType(state), blockstatemodel, 0.0F, 0.0F, 0.0F, 15728880, OverlayTexture.NO_OVERLAY, 0);
		poseStack.popPose();
		renderPlacedItem(foodPlateRenderState, poseStack, submitNodeCollector);
		poseStack.popPose();
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
		matrixStackIn.scale(0.6F, 0.6F, 0.6F);
	}

	public void renderBlock(PoseStack matrixStackIn, Direction direction, boolean candle) {
		float f = -direction.toYRot();
		matrixStackIn.translate(0.5D, 0.0D, 0.5D);
		if (!candle) {
			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
		}
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.translate(-0.5D, 0.0D, -0.5D);
		//matrixStackIn.scale(0.8F, 0.8F, 0.8F);
	}

	@Override
	public FoodPlateRenderState createRenderState() {
		return new FoodPlateRenderState();
	}

	@Override
	public void extractRenderState(FoodPlateBlockEntity foodPlateBlockEntity, FoodPlateRenderState foodPlateRenderState, float p_446851_, Vec3 p_445788_, @Nullable ModelFeatureRenderer.CrumblingOverlay p_446944_) {
		BlockEntityRenderer.super.extractRenderState(foodPlateBlockEntity, foodPlateRenderState, p_446851_, p_445788_, p_446944_);
		this.itemModelResolver.updateForTopItem(foodPlateRenderState.plateItem, foodPlateBlockEntity.getStoredItem(), ItemDisplayContext.GROUND, null, null, 0);
		foodPlateRenderState.plateState = Block.byItem(foodPlateBlockEntity.getStoredItem().getItem()).defaultBlockState();
		foodPlateRenderState.candle = foodPlateBlockEntity.getStoredItem().is(ItemTags.CANDLES);
		foodPlateRenderState.cake = Block.byItem(foodPlateBlockEntity.getStoredItem().getItem()) instanceof CakeBlock;
		foodPlateRenderState.fire = foodPlateBlockEntity.isFire();
		foodPlateRenderState.renderAmount = getRenderAmount(foodPlateBlockEntity.getStoredItem());
		foodPlateRenderState.hasLevel = true;
	}

	@Override
	public void submit(FoodPlateRenderState foodPlateRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		this.random.setSeed(187);
		renderPlacedItem(foodPlateRenderState, poseStack, submitNodeCollector);
	}

	private void renderPlacedItem(FoodPlateRenderState foodPlateRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
		for (int k = 0; k < foodPlateRenderState.renderAmount; ++k) {
			if ((foodPlateRenderState.candle || foodPlateRenderState.cake) && foodPlateRenderState.plateState != null) {
				poseStack.pushPose();
				renderBlock(poseStack, foodPlateRenderState.direction, foodPlateRenderState.candle);

				BlockState state = foodPlateRenderState.plateState;
				if (foodPlateRenderState.candle) {
					state = state.setValue(CandleBlock.LIT, foodPlateRenderState.fire);
				}
				BlockStateModel blockstatemodel = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
				//poseStack.translate(-0.5F, 0F, -0.5F);
				submitNodeCollector.submitBlockModel(poseStack, ItemBlockRenderTypes.getRenderType(state), blockstatemodel, 0.0F, 0.0F, 0.0F, !foodPlateRenderState.hasLevel ? 15728880 : foodPlateRenderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
				poseStack.popPose();
				return;
			} else if (!foodPlateRenderState.plateItem.isEmpty()) {
					poseStack.pushPose();

					if (k > 0) {
						float f12 = (this.random.nextFloat()) * 0.15F * 0.5F;
						float f14 = (this.random.nextFloat()) * 0.15F * 0.5F;
						poseStack.translate(f12, k * 0.1F * 0.5F, f14);
					}
					renderItemLayingDown(poseStack, foodPlateRenderState.direction);

				foodPlateRenderState.plateItem.submit(poseStack, submitNodeCollector, !foodPlateRenderState.hasLevel ? 15728880 : foodPlateRenderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
					poseStack.popPose();
				}
			}
	}
}