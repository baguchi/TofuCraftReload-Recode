package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.SoyBallModel;
import baguchi.tofucraft.client.render.state.ProjectileRenderState;
import baguchi.tofucraft.entity.projectile.SoyballEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SoyballRenderer extends EntityRenderer<SoyballEntity, ProjectileRenderState> {
	private static final ResourceLocation LLAMA_SPIT_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/soyball.png");
	private final SoyBallModel model;

	public SoyballRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SoyBallModel(context.bakeLayer(TofuModelLayers.SOYBALL));
	}

	@Override
	public ProjectileRenderState createRenderState() {
		return new ProjectileRenderState();
	}

	@Override
	public void render(ProjectileRenderState llamaSpit, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
		poseStack.pushPose();
		poseStack.translate(0.0F, 4F / 16F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(llamaSpit.yRot - 180F));
		poseStack.mulPose(Axis.XP.rotationDegrees(llamaSpit.xRot));
		poseStack.translate(0.0F, -1.501F + 3F / 16F, -2.5F / 16F);
		this.model.setupAnim(llamaSpit);
		VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(LLAMA_SPIT_LOCATION));
		this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
		poseStack.popPose();
		super.render(llamaSpit, poseStack, multiBufferSource, i);
	}


	@Override
	public void extractRenderState(SoyballEntity p_362104_, ProjectileRenderState p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		p_361028_.xRot = p_362104_.getXRot(p_362204_);
		p_361028_.yRot = p_362104_.getYRot(p_362204_);
	}
}