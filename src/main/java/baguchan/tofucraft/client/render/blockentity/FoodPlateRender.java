package baguchan.tofucraft.client.render.blockentity;

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
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FoodPlateRender implements BlockEntityRenderer<FoodPlateBlockEntity> {
	private final RandomSource random = RandomSource.create();

	public FoodPlateRender(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(FoodPlateBlockEntity plateBlockEntity, float p_112308_, PoseStack poseStack, MultiBufferSource p_112310_, int p_112311_, int p_112312_) {
		Direction direction = plateBlockEntity.getBlockState().getValue(FoodPlateBlock.FACING).getOpposite();
		ItemStack boardStack = plateBlockEntity.getStoredItem();
		int posLong = (int) plateBlockEntity.getBlockPos().asLong();
		int j = this.getRenderAmount(boardStack);
		int i = boardStack.isEmpty() ? 187 : Item.getId(boardStack.getItem()) + boardStack.getDamageValue();

		this.random.setSeed((long) i);
		if (!boardStack.isEmpty()) {
			Block block = Block.byItem(boardStack.getItem());
			for (int k = 0; k < j; ++k) {
				ItemRenderer itemRenderer = Minecraft.getInstance()
						.getItemRenderer();
				if (boardStack.is(ItemTags.CANDLES) || block instanceof CakeBlock) {
					poseStack.pushPose();

					renderBlock(poseStack, direction, boardStack.is(ItemTags.CANDLES));

					BlockState state = block.defaultBlockState();
					if (boardStack.is(ItemTags.CANDLES)) {
						state = state.setValue(CandleBlock.LIT, plateBlockEntity.isFire());
					}

					Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, p_112310_, p_112311_, p_112312_);
					poseStack.popPose();
					return;
				} else {
					poseStack.pushPose();

					if (k > 0) {
						float f12 = (this.random.nextFloat()) * 0.15F * 0.5F;
						float f14 = (this.random.nextFloat()) * 0.15F * 0.5F;
						poseStack.translate(f12, k * 0.1F * 0.5F, f14);
					}
					renderItemLayingDown(poseStack, direction);

					Minecraft.getInstance().getItemRenderer().renderStatic(boardStack, ItemDisplayContext.FIXED, p_112311_, p_112312_, poseStack, p_112310_, plateBlockEntity.getLevel(), posLong);
					poseStack.popPose();
				}
			}
		}
	}

	protected int getRenderAmount(ItemStack p_115043_) {
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
		matrixStackIn.translate(0.0D, 0.05D, 0.0D);
		float f = -direction.toYRot();
		if (!candle) {
			matrixStackIn.translate(0.5D, 0.0D, 0.5D);

			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
			matrixStackIn.translate(-0.5D, 0.0D, -0.5D);
		}
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		//matrixStackIn.scale(0.8F, 0.8F, 0.8F);
	}
}