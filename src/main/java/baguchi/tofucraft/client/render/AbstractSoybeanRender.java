package baguchi.tofucraft.client.render;

import baguchi.tofucraft.entity.projectile.FukumameEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;


public abstract class AbstractSoybeanRender<T extends FukumameEntity, S extends ArrowRenderState> extends EntityRenderer<T, S> {
	private final ArrowModel model;


	public AbstractSoybeanRender(EntityRendererProvider.Context p_173917_) {
		super(p_173917_);
		this.model = new ArrowModel(p_173917_.bakeLayer(ModelLayers.ARROW));
	}

	@Override
	public void submit(S p_113839_, PoseStack p_113842_, SubmitNodeCollector p_113843_, CameraRenderState p_451076_) {
		p_113842_.pushPose();
		p_113842_.mulPose(Axis.YP.rotationDegrees(p_113839_.yRot - 90.0F));
		p_113842_.mulPose(Axis.ZP.rotationDegrees(p_113839_.xRot));
		p_113843_.submitModel(this.model, p_113839_, p_113842_, RenderTypes.entityCutout(this.getTextureLocation(p_113839_)), p_113839_.lightCoords, OverlayTexture.NO_OVERLAY, p_113839_.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);


		p_113842_.popPose();
		super.submit(p_113839_, p_113842_, p_113843_, p_451076_);
	}

	protected abstract Identifier getTextureLocation(S p113839);

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
