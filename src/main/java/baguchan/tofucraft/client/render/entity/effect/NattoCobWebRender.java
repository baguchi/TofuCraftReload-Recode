package baguchan.tofucraft.client.render.entity.effect;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.effect.NattoCobWebEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class NattoCobWebRender extends EntityRenderer<NattoCobWebEntity> {
	public static final ResourceLocation NATTOCOBWEB_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/item/natto_cobweb.png");
	private static final float TEXTURE_WIDTH = 16;
	private static final float TEXTURE_HEIGHT = 16;

	public NattoCobWebRender(EntityRendererProvider.Context p_174008_) {
		super(p_174008_);
	}

	@Override
	public ResourceLocation getTextureLocation(NattoCobWebEntity p_114482_) {
		return NattoCobWebRender.NATTOCOBWEB_LOCATION;
	}

	public void render(NattoCobWebEntity entity, float yaw, float delta, PoseStack stack, int packedLightIn) {
		drawCobWeb(entity, delta, stack, packedLightIn);
	}

	private void drawCobWeb(NattoCobWebEntity entity, float delta, PoseStack matrixStack, int packedLightIn) {

	}

	public void drawVertex(Matrix4f matrix, Matrix3f normals, VertexConsumer vertexBuilder, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, float alpha, int packedLightIn) {
		vertexBuilder.vertex(matrix, offsetX, offsetY, offsetZ).color(1, 1, 1, 1 * alpha).uv(textureX, textureY).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
	}
}
