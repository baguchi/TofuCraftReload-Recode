package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.entity.projectile.FallingTofuEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.FallingBlockRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

/**
 * <p>Revamped Falling Block Renderer.</p>
 * <p>Structure based on <a href=https://github.com/TeamTwilight/twilightforest/blob/1.19.x/src/main/java/twilightforest/client/renderer/entity/ThrownBlockRenderer.java>ThrownBlockRenderer</a></p>
 *
 * @author bagu_chan
 */

public class FallingTofuRenderer extends EntityRenderer<FallingTofuEntity, FallingBlockRenderState> {

	public FallingTofuRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.shadowRadius = 0.5F;
	}

	public boolean shouldRender(FallingTofuEntity p_362415_, Frustum p_364047_, double p_362218_, double p_363427_, double p_361722_) {
		return !super.shouldRender(p_362415_, p_364047_, p_362218_, p_363427_, p_361722_)
				? false
				: p_362415_.getBlockState() != p_362415_.level().getBlockState(p_362415_.blockPosition());
	}

	@Override
	public void submit(FallingBlockRenderState renderState, PoseStack poseStack, SubmitNodeCollector p_114638_, CameraRenderState cameraRenderState) {
		BlockState blockstate = renderState.movingBlockRenderState.blockState;
		if (blockstate.getRenderShape() == RenderShape.MODEL) {
			poseStack.pushPose();
			poseStack.translate(-0.5, 0.0, -0.5);
			p_114638_.submitMovingBlock(poseStack, renderState.movingBlockRenderState, renderState.outlineColor);

			poseStack.popPose();
			super.submit(renderState, poseStack, p_114638_, cameraRenderState);
		}
	}

	@Override
	public FallingBlockRenderState createRenderState() {
		return new FallingBlockRenderState();
	}

	@Override
	public void extractRenderState(FallingTofuEntity entity, FallingBlockRenderState state, float p_361019_) {
		super.extractRenderState(entity, state, p_361019_);
		BlockPos blockpos = BlockPos.containing(entity.getX(), entity.getBoundingBox().maxY, entity.getZ());
		BlockPos pos = BlockPos.containing(entity.getX(), entity.getBoundingBox().maxY, entity.getZ());
		//state.movingBlockRenderState.randomSeedPos = entity.getStartPos();
		state.movingBlockRenderState.blockPos = pos;
		state.movingBlockRenderState.blockState = entity.getBlockState();
		if (entity.level() instanceof ClientLevel clientLevel) {
			state.movingBlockRenderState.biome = clientLevel.getBiome(pos);
			state.movingBlockRenderState.cardinalLighting = clientLevel.cardinalLighting();
			state.movingBlockRenderState.lightEngine = clientLevel.getLightEngine();
		}
	}
}