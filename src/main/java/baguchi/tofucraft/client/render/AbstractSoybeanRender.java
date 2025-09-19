package baguchi.tofucraft.client.render;

import baguchi.tofucraft.client.render.state.ProjectileRenderState;
import baguchi.tofucraft.entity.projectile.FukumameEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public abstract class AbstractSoybeanRender<T extends FukumameEntity, S extends ProjectileRenderState> extends EntityRenderer<T, S> {
	public AbstractSoybeanRender(EntityRendererProvider.Context p_173917_) {
		super(p_173917_);
	}

	@Override
	public void submit(S p_113839_, PoseStack p_113842_, SubmitNodeCollector p_113843_, CameraRenderState p_451076_) {
		p_113842_.pushPose();
		p_113842_.mulPose(Axis.YP.rotationDegrees(p_113839_.yRot - 90.0F));
		p_113842_.mulPose(Axis.ZP.rotationDegrees(p_113839_.xRot));

		p_113842_.mulPose(Axis.XP.rotationDegrees(45.0F));
		p_113842_.scale(0.05625F, 0.05625F, 0.05625F);
		p_113842_.translate(-4.0F, 0.0F, 0.0F);
		p_113843_.submitCustomGeometry(p_113842_, RenderType.entityCutout(this.getTextureLocation(p_113839_)), (pose, vertexConsumer) -> {

			this.vertex(pose, vertexConsumer, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, p_113839_.lightCoords);
			this.vertex(pose, vertexConsumer, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, p_113839_.lightCoords);
			this.vertex(pose, vertexConsumer, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, p_113839_.lightCoords);
			this.vertex(pose, vertexConsumer, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, p_113839_.lightCoords);
			this.vertex(pose, vertexConsumer, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, p_113839_.lightCoords);
			this.vertex(pose, vertexConsumer, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, p_113839_.lightCoords);
			this.vertex(pose, vertexConsumer, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, p_113839_.lightCoords);
			this.vertex(pose, vertexConsumer, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, p_113839_.lightCoords);

			for (int j = 0; j < 4; j++) {
				p_113842_.mulPose(Axis.XP.rotationDegrees(90.0F));
				this.vertex(pose, vertexConsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, p_113839_.lightCoords);
				this.vertex(pose, vertexConsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, p_113839_.lightCoords);
				this.vertex(pose, vertexConsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, p_113839_.lightCoords);
				this.vertex(pose, vertexConsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, p_113839_.lightCoords);
			}

		});

		p_113842_.popPose();
		super.submit(p_113839_, p_113842_, p_113843_, p_451076_);
	}

	protected abstract ResourceLocation getTextureLocation(S p113839);

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
