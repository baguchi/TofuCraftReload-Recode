package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.block.FoodPlateBlock;
import baguchi.tofucraft.blockentity.FoodPlateBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FoodPlateRender implements BlockEntityRenderer<FoodPlateBlockEntity> {
	private final RandomSource random = RandomSource.create();


	public FoodPlateRender(BlockEntityRendererProvider.Context context) {
	}

	public FoodPlateRender(EntityModelSet context) {
	}


	public void renderInHand(Optional<Block> foodplate, @org.jetbrains.annotations.Nullable ItemContainerContents itemContainerContents, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_112311_, int p_112312_) {
		float f = 90.0F;
		poseStack.pushPose();
		poseStack.scale(-1.5F, -1.5F, 1.5F);
		poseStack.translate(-0.4, -1F, 0);

		if (foodplate.isPresent()) {
			poseStack.pushPose();
			BlockState state = foodplate.get().defaultBlockState();
			BlockStateModel blockstatemodel = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
			//poseStack.translate(-0.5F, 0F, -0.5F);
			VertexConsumer vertexconsumer = multiBufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(state));
			ModelBlockRenderer.renderModel(poseStack.last(), vertexconsumer, blockstatemodel, 0.0F, 0.0F, 0.0F, p_112311_,
					p_112312_);
			poseStack.popPose();
		}

		if (itemContainerContents != null) {
			int j = this.getRenderAmount(itemContainerContents.getStackInSlot(0));

			renderPlacedItem(itemContainerContents.getStackInSlot(0), j, Direction.NORTH, null, 0, false, poseStack, multiBufferSource, p_112311_,
					p_112312_);
		}
		poseStack.popPose();
	}

	@Override
	public void render(FoodPlateBlockEntity plateBlockEntity, float p_112308_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_112311_, int p_112312_, Vec3 vec3) {
		Direction direction = plateBlockEntity.getBlockState().getValue(FoodPlateBlock.FACING).getOpposite();
		ItemStack boardStack = plateBlockEntity.getStoredItem();
		int posLong = (int) plateBlockEntity.getBlockPos().asLong();
		int j = this.getRenderAmount(boardStack);
		int i = boardStack.isEmpty() ? 187 : Item.getId(boardStack.getItem()) + boardStack.getDamageValue();

		this.random.setSeed((long) i);


		renderPlacedItem(boardStack, j, direction, plateBlockEntity.getLevel(), posLong, plateBlockEntity.isFire(), poseStack, multiBufferSource, p_112311_,
				p_112312_);

	}

	private void renderPlacedItem(ItemStack boardStack, int j, Direction direction, @Nullable Level level, int posLong, boolean fire, PoseStack poseStack, MultiBufferSource multiBufferSource, int p112311, int p112312) {
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
						state = state.setValue(CandleBlock.LIT, fire);
					}
					BlockStateModel blockstatemodel = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
					//poseStack.translate(-0.5F, 0F, -0.5F);
					VertexConsumer vertexconsumer = multiBufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(state));
					ModelBlockRenderer.renderModel(poseStack.last(), vertexconsumer, blockstatemodel, 0.0F, 0.0F, 0.0F, p112311,
							p112312);
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

					Minecraft.getInstance().getItemRenderer().renderStatic(boardStack, ItemDisplayContext.FIXED, p112311, p112312, poseStack, multiBufferSource, level, posLong);
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
		float f = -direction.toYRot();
		matrixStackIn.translate(0.5D, 0.0D, 0.5D);
		if (!candle) {
			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
		}
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.translate(-0.5D, 0.0D, -0.5D);
		//matrixStackIn.scale(0.8F, 0.8F, 0.8F);
	}
}