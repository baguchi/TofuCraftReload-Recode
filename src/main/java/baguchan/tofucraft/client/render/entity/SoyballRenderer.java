package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.SoyBallModel;
import baguchan.tofucraft.entity.projectile.SoyballEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SoyballRenderer<T extends SoyballEntity> extends EntityRenderer<T> {
	private static final ResourceLocation LLAMA_SPIT_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/soyball.png");
	private final SoyBallModel<T> model;

	public SoyballRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SoyBallModel<>(context.bakeLayer(TofuModelLayers.SOYBALL));
	}

	public void render(T llamaSpit, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
		poseStack.pushPose();
		poseStack.translate(0.0F, -1.15F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, llamaSpit.yRotO, llamaSpit.getYRot()) - 180F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(g, llamaSpit.xRotO, llamaSpit.getXRot())));
		this.model.setupAnim(llamaSpit, g, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(LLAMA_SPIT_LOCATION));
		this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
		poseStack.popPose();
		super.render(llamaSpit, f, g, poseStack, multiBufferSource, i);
	}

	public ResourceLocation getTextureLocation(T llamaSpit) {
		return LLAMA_SPIT_LOCATION;
	}
}