package baguchan.tofucraft.client.render.tileentity;

import baguchan.tofucraft.blockentity.SuspiciousTofuBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class SuspiciousTofuRenderer implements BlockEntityRenderer<SuspiciousTofuBlockEntity> {
	private final ItemRenderer itemRenderer;

	public SuspiciousTofuRenderer(BlockEntityRendererProvider.Context p_272848_) {
		this.itemRenderer = p_272848_.getItemRenderer();
	}

	public void render(SuspiciousTofuBlockEntity p_272652_, float p_273378_, PoseStack p_273698_, MultiBufferSource p_273630_, int p_273435_, int p_273364_) {
		if (p_272652_.getLevel() != null) {
			int i = p_272652_.getBlockState().getValue(BlockStateProperties.DUSTED);
			if (i > 0) {
				Direction direction = p_272652_.getHitDirection();
				if (direction != null) {
					ItemStack itemstack = p_272652_.getItem();
					if (!itemstack.isEmpty()) {
						p_273698_.pushPose();
						p_273698_.translate(0.0F, 0.5F, 0.0F);
						float[] afloat = this.translations(direction, i);
						p_273698_.translate(afloat[0], afloat[1], afloat[2]);
						p_273698_.mulPose(Axis.YP.rotationDegrees(75.0F));
						boolean flag = direction == Direction.EAST || direction == Direction.WEST;
						p_273698_.mulPose(Axis.YP.rotationDegrees((float) ((flag ? 90 : 0) + 11)));
						p_273698_.scale(0.5F, 0.5F, 0.5F);
						int j = LevelRenderer.getLightColor(p_272652_.getLevel(), p_272652_.getBlockState(), p_272652_.getBlockPos().relative(direction));
						this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, j, OverlayTexture.NO_OVERLAY, p_273698_, p_273630_, p_272652_.getLevel(), 0);
						p_273698_.popPose();
					}
				}
			}
		}
	}

	private float[] translations(Direction p_273678_, int p_273299_) {
		float[] afloat = new float[]{0.5F, 0.0F, 0.5F};
		float f = (float) p_273299_ / 10.0F * 0.75F;
		switch (p_273678_) {
			case EAST:
				afloat[0] = 0.73F + f;
				break;
			case WEST:
				afloat[0] = 0.25F - f;
				break;
			case UP:
				afloat[1] = 0.25F + f;
				break;
			case DOWN:
				afloat[1] = -0.23F - f;
				break;
			case NORTH:
				afloat[2] = 0.25F - f;
				break;
			case SOUTH:
				afloat[2] = 0.73F + f;
		}

		return afloat;
	}
}