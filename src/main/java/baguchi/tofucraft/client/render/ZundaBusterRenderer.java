package baguchi.tofucraft.client.render;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.state.ProjectileRenderState;
import baguchi.tofucraft.entity.projectile.ZundaBuster;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class ZundaBusterRenderer<T extends ZundaBuster> extends EntityRenderer<T, ProjectileRenderState> {
	public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/projectiles/zunda_buster.png");

	public ZundaBusterRenderer(EntityRendererProvider.Context p_173917_) {
		super(p_173917_);
	}


	@Override
	public void submit(ProjectileRenderState projectileRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		poseStack.pushPose();

		poseStack.translate(0.0F, 0.5F, 0.0F);
		poseStack.scale(0.1F, 0.1F, 0.1F);
		poseStack.mulPose(Axis.YP.rotationDegrees(projectileRenderState.yRot - 90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(projectileRenderState.xRot));

		poseStack.mulPose(Axis.XP.rotationDegrees(45.0F));

		submitNodeCollector.submitCustomGeometry(poseStack, RenderType.entityTranslucentEmissive(this.getTextureLocation(projectileRenderState)), (pose, vertexConsumer) -> {
			for (int j = 0; j < 4; j++) {
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				this.vertex(pose, vertexConsumer, -8, -8, 0, 0.0F, 0.0F, 0, 1, 0, projectileRenderState.lightCoords);
				this.vertex(pose, vertexConsumer, 8, -8, 0, 1F, 0.0F, 0, 1, 0, projectileRenderState.lightCoords);
				this.vertex(pose, vertexConsumer, 8, 8, 0, 1F, 1F, 0, 1, 0, projectileRenderState.lightCoords);
				this.vertex(pose, vertexConsumer, -8, 8, 0, 0.0F, 1F, 0, 1, 0, projectileRenderState.lightCoords);
			}
		});
		poseStack.popPose();
		super.submit(projectileRenderState, poseStack, submitNodeCollector, cameraRenderState);
	}

	@Override
	public ProjectileRenderState createRenderState() {
		return new ProjectileRenderState();
	}

	@Override
	public void extractRenderState(T p_362104_, ProjectileRenderState p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		p_361028_.xRot = p_362104_.getXRot(p_362204_);
		p_361028_.yRot = p_362104_.getYRot(p_362204_);
	}

	protected ResourceLocation getTextureLocation(ProjectileRenderState entity) {
		return LOCATION;
	}

	protected int getBlockLightLevel(T p_114606_, BlockPos p_114607_) {
		return Mth.clamp(super.getBlockLightLevel(p_114606_, p_114607_) + 7, 0, 15);
	}

	public void vertex(
			PoseStack.Pose p_324380_,
			VertexConsumer p_253902_,
			int p_254058_,
			int p_254338_,
			int p_254196_,
			float p_254003_,
			float p_254165_,
			int p_253982_,
			int p_254037_,
			int p_254038_,
			int p_254271_
	) {
		p_253902_.addVertex(p_324380_, (float) p_254058_, (float) p_254338_, (float) p_254196_)
				.setColor(-1)
				.setUv(p_254003_, p_254165_)
				.setOverlay(OverlayTexture.NO_OVERLAY)
				.setLight(p_254271_)
				.setNormal(p_324380_, (float) p_253982_, (float) p_254038_, (float) p_254037_);
	}
}
