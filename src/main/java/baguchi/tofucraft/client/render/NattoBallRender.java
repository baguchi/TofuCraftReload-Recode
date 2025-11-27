package baguchi.tofucraft.client.render;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.state.ProjectileRenderState;
import baguchi.tofucraft.entity.projectile.NattoBallEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import org.joml.Matrix4f;


public class NattoBallRender extends EntityRenderer<NattoBallEntity, ProjectileRenderState> {
	private static final Identifier TEXTURE_LOCATION = TofuCraftReload.prefix("textures/item/natto_cobweb.png");
	private static final RenderType RENDER_TYPE = RenderTypes.entityCutoutNoCull(TEXTURE_LOCATION);

	public NattoBallRender(EntityRendererProvider.Context p_173962_) {
		super(p_173962_);
	}

	protected int getBlockLightLevel(NattoBallEntity p_114087_, BlockPos p_114088_) {
		return 15;
	}

	@Override
	public ProjectileRenderState createRenderState() {
		return new ProjectileRenderState();
	}

	@Override
	public void submit(ProjectileRenderState p_433712_, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState p_451076_) {
		poseStack.pushPose();
		poseStack.scale(0.5F, 0.5F, 0.5F);
		poseStack.mulPose(p_451076_.orientation);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
		submitNodeCollector.submitCustomGeometry(poseStack, RENDER_TYPE, (pose, vertexConsumer) -> {
			vertex(vertexConsumer, pose.pose(), pose, p_433712_.lightCoords, 0.0F, 0, 0, 1);
			vertex(vertexConsumer, pose.pose(), pose, p_433712_.lightCoords, 1.0F, 0, 1, 1);
			vertex(vertexConsumer, pose.pose(), pose, p_433712_.lightCoords, 1.0F, 1, 1, 0);
			vertex(vertexConsumer, pose.pose(), pose, p_433712_.lightCoords, 0.0F, 1, 0, 0);

		});
		poseStack.popPose();

		super.submit(p_433712_, poseStack, submitNodeCollector, p_451076_);
	}


	private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, PoseStack.Pose p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
		p_114090_.addVertex(p_114091_, p_114094_ - 0.5F, (float) p_114095_ - 0.25F, 0.0F).setColor(255, 255, 255, 255).setUv((float) p_114096_, (float) p_114097_).setOverlay(OverlayTexture.NO_OVERLAY).setLight(p_114093_).setNormal(p_114092_, 0.0F, 1.0F, 0.0F);
	}

	public Identifier getTextureLocation(NattoBallEntity p_114078_) {
		return TEXTURE_LOCATION;
	}
}
