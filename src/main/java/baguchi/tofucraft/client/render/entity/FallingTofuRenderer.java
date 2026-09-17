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

	@Override
	public boolean shouldRender(FallingTofuEntity entity, Frustum culler, double camX, double camY, double camZ, float partialTicks) {
		return super.shouldRender(entity, culler, camX, camY, camZ, partialTicks) ? false
				: entity.getBlockState() != entity.level().getBlockState(entity.blockPosition());
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