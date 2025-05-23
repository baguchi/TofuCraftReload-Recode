package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.ClientRegistrar;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.TriState;
import net.minecraft.util.context.ContextKey;
import org.joml.Matrix4f;

public class ZundaLayer<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {
	protected static final RenderStateShard.LightmapStateShard LIGHTMAP = new RenderStateShard.LightmapStateShard(true);
	protected static final RenderStateShard.TexturingStateShard ENTITY_GLINT_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> {
		setupGlintTexturing(0.16F);
	}, () -> {
		RenderSystem.resetTextureMatrix();
	});

	public static final ContextKey<Boolean> ZUNDA_KEY = new ContextKey<>(TofuCraftReload.prefix("zunda"));

	public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/misc/zunda.png");

	public ZundaLayer(RenderLayerParent<T, M> p_i50947_1_) {
		super(p_i50947_1_);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T entitylivingbaseIn, float v, float v1) {
		if (entitylivingbaseIn.getRenderDataOrDefault(ZUNDA_KEY, false)) {
			float tick = (float) entitylivingbaseIn.ageInTicks;
			float f = (float) entitylivingbaseIn.ageInTicks;
			EntityModel<T> entitymodel = this.getParentModel();
			VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(enchantSwirl(TEXTURE));
			entitymodel.setupAnim(entitylivingbaseIn);
			entitymodel.renderToBuffer(poseStack, ivertexbuilder, i, OverlayTexture.NO_OVERLAY);
		}
	}

	public static RenderType enchantSwirl(ResourceLocation resourceLocation) {
		return RenderType.create("zunda_effect", 256, false, true, ClientRegistrar.ZUNDA, RenderType.CompositeState.builder().setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, TriState.FALSE, false)).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));
	}

	private static void setupGlintTexturing(float p_110187_) {
		long i = Util.getMillis() * 8L;
		float f = (float) (i % 110000L) / 110000.0F;
		float f1 = (float) (i % 30000L) / 30000.0F;
		Matrix4f matrix4f = (new Matrix4f()).translation(-f, f1, 0.0F);
		matrix4f.rotateZ(0.17453292F).scale(p_110187_);
		RenderSystem.setTextureMatrix(matrix4f);
	}
}