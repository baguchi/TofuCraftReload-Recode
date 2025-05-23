package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;

import static baguchi.tofucraft.client.render.layer.ZundaLayer.enchantSwirl;

public class ZundaSlimeOuterLayer extends RenderLayer<SlimeRenderState, SlimeModel> {
	private final SlimeModel model;

	public ZundaSlimeOuterLayer(RenderLayerParent<SlimeRenderState, SlimeModel> p_174536_, EntityModelSet p_174537_) {
		super(p_174536_);
		this.model = new SlimeModel(p_174537_.bakeLayer(ModelLayers.SLIME_OUTER));
	}

	public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/misc/zunda.png");


	@Override
	public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, SlimeRenderState entitylivingbaseIn, float v, float v1) {
		if (entitylivingbaseIn.getRenderDataOrDefault(ZundaLayer.ZUNDA_KEY, false)) {
			float tick = (float) entitylivingbaseIn.ageInTicks;
			float f = (float) entitylivingbaseIn.ageInTicks;
			VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(enchantSwirl(TEXTURE));
			this.model.setupAnim(entitylivingbaseIn);
			this.model.renderToBuffer(poseStack, ivertexbuilder, i, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F));
		}
	}

}