package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.client.render.layer.TFShulkerHeadLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class TFShulkerRenderer extends MobRenderer<Shulker, ShulkerModel<Shulker>> {
	private static final float HALF_SQRT_3 = (float) (Math.sqrt(30.0) / 2.0);


	private static final ResourceLocation DEFAULT_TEXTURE_LOCATION = new ResourceLocation(
			"textures/" + Sheets.DEFAULT_SHULKER_TEXTURE_LOCATION.texture().getPath() + ".png"
	);
	private static final ResourceLocation[] TEXTURE_LOCATION = Sheets.SHULKER_TEXTURE_LOCATION
			.stream()
			.map(p_115919_ -> new ResourceLocation("textures/" + p_115919_.texture().getPath() + ".png"))
			.toArray(ResourceLocation[]::new);

	public TFShulkerRenderer(EntityRendererProvider.Context p_174370_) {
		super(p_174370_, new ShulkerModel<>(p_174370_.bakeLayer(ModelLayers.SHULKER)), 0.0F);
		this.addLayer(new TFShulkerHeadLayer(this));
	}

	@Override
	public void render(Shulker p_115455_, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource p_115459_, int p_115460_) {
		super.render(p_115455_, p_115456_, p_115457_, poseStack, p_115459_, p_115460_);

		float length = 0.5F;
		float f4 = 0.15F;
		int j = (int) (255.0F * (1F));
		poseStack.pushPose();
		VertexConsumer vertexconsumer2 = p_115459_.getBuffer(RenderType.lightning());
		poseStack.rotateAround(p_115455_.getAttachFace().getOpposite().getRotation(), 0F, 0.5F, 0F);
		poseStack.mulPose(Axis.ZP.rotationDegrees((float) -180.0F));
		poseStack.translate(0, 0F, 0);

		poseStack.scale(-1.0F, -1.0F, 1.0F);
		Matrix4f matrix4f = poseStack.last().pose();
		PoseStack.Pose pose = poseStack.last();
		originVertex(vertexconsumer2, matrix4f, pose, 255);
		leftVertex(vertexconsumer2, matrix4f, pose, length, f4);
		rightVertex(vertexconsumer2, matrix4f, pose, length, f4);
		leftVertex(vertexconsumer2, matrix4f, pose, length, f4);
		poseStack.popPose();
	}

	private static void originVertex(VertexConsumer p_254498_, Matrix4f p_253891_, PoseStack.Pose p_114092_, int p_254278_) {
		p_254498_.vertex(p_253891_, 0.0F, 0.0F, 0.0F).color(255, 255, 255, p_254278_).uv(0 + 0.5F, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(240).normal(p_114092_, 0.0F, 1.0F, 0.0F).endVertex();
	}

	private static void leftVertex(VertexConsumer p_253956_, Matrix4f p_254053_, PoseStack.Pose p_114092_, float p_253704_, float p_253701_) {
		p_253956_.vertex(p_254053_, -HALF_SQRT_3 * p_253701_, p_253704_, 0).color(0, 0, 255, 0).uv(0, 0 + 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(240).normal(p_114092_, 0.0F, -1.0F, 0.0F).endVertex();
	}

	private static void rightVertex(VertexConsumer p_253850_, Matrix4f p_254379_, PoseStack.Pose p_114092_, float p_253729_, float p_254030_) {
		p_253850_.vertex(p_254379_, HALF_SQRT_3 * p_254030_, p_253729_, 0).color(0, 0, 255, 0).uv(0 + 1, 0 + 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(240).normal(p_114092_, 0.0F, -1.0F, 0.0F).endVertex();
	}



	public Vec3 getRenderOffset(Shulker p_115904_, float p_115905_) {
		return p_115904_.getRenderPosition(p_115905_).orElse(super.getRenderOffset(p_115904_, p_115905_)).scale((double) p_115904_.getScale());
	}

	public boolean shouldRender(Shulker p_115913_, Frustum p_115914_, double p_115915_, double p_115916_, double p_115917_) {
		return super.shouldRender(p_115913_, p_115914_, p_115915_, p_115916_, p_115917_)
				? true
				: p_115913_.getRenderPosition(0.0F)
				.filter(
						p_174374_ -> {
							EntityType<?> entitytype = p_115913_.getType();
							float f = entitytype.getHeight() / 2.0F;
							float f1 = entitytype.getWidth() / 2.0F;
							Vec3 vec3 = Vec3.atBottomCenterOf(p_115913_.blockPosition());
							return p_115914_.isVisible(
									new AABB(p_174374_.x, p_174374_.y + (double) f, p_174374_.z, vec3.x, vec3.y + (double) f, vec3.z)
											.inflate((double) f1, (double) f, (double) f1)
							);
						}
				)
				.isPresent();
	}

	public ResourceLocation getTextureLocation(Shulker p_115902_) {
		return getTextureLocation(p_115902_.getColor());
	}

	public static ResourceLocation getTextureLocation(@Nullable DyeColor p_174376_) {
		return p_174376_ == null ? DEFAULT_TEXTURE_LOCATION : TEXTURE_LOCATION[p_174376_.getId()];
	}

	protected void setupRotations(Shulker p_320913_, PoseStack p_115891_, float p_115892_, float p_115893_, float p_115894_, float p_319950_) {
		super.setupRotations(p_320913_, p_115891_, p_115892_, p_115893_ + 180.0F, p_115894_, p_319950_);
		p_115891_.rotateAround(p_320913_.getAttachFace().getOpposite().getRotation(), 0.0F, 0.5F, 0.0F);
	}
}
