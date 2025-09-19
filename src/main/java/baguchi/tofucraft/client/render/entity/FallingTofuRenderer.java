package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.entity.projectile.FallingTofuEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.FallingBlockRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
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
	private final BlockRenderDispatcher dispatcher;

	public FallingTofuRenderer(EntityRendererProvider.Context context) {
		super(context);

		this.shadowRadius = 0.5F;
		this.dispatcher = context.getBlockRenderDispatcher();
	}

	public boolean shouldRender(FallingTofuEntity p_362415_, Frustum p_364047_, double p_362218_, double p_363427_, double p_361722_) {
		return !super.shouldRender(p_362415_, p_364047_, p_362218_, p_363427_, p_361722_)
				? false
				: p_362415_.getBlockState() != p_362415_.level().getBlockState(p_362415_.blockPosition());
	}

	public void submit(FallingBlockRenderState p_361300_, PoseStack p_114637_, SubmitNodeCollector p_114638_, CameraRenderState cameraRenderState) {
		BlockState blockstate = p_361300_.movingBlockRenderState.blockState;
		if (blockstate.getRenderShape() == RenderShape.MODEL) {
			p_114637_.pushPose();
			p_114637_.translate(-0.5, 0.0, -0.5);
			p_114638_.submitMovingBlock(p_114637_, p_361300_.movingBlockRenderState);

			p_114637_.popPose();
			super.submit(p_361300_, p_114637_, p_114638_, cameraRenderState);
		}
	}


	public FallingBlockRenderState createRenderState() {
		return new FallingBlockRenderState();
	}

	public void extractRenderState(FallingTofuEntity p_364559_, FallingBlockRenderState p_360509_, float p_361019_) {
		super.extractRenderState(p_364559_, p_360509_, p_361019_);
		BlockPos blockpos = BlockPos.containing(p_364559_.getX(), p_364559_.getBoundingBox().maxY, p_364559_.getZ());

		//p_360509_.movingBlockRenderState.randomSeedPos = p_364559_.getStartPos();
		p_360509_.movingBlockRenderState.blockPos = blockpos;
		p_360509_.movingBlockRenderState.blockState = p_364559_.getBlockState();
		p_360509_.movingBlockRenderState.biome = p_364559_.level().getBiome(blockpos);
		p_360509_.movingBlockRenderState.level = p_364559_.level();
	}
}