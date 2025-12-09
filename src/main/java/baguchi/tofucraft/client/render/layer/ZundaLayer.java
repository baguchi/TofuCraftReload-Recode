package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.ClientRegistrar;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.TextureTransform;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKey;

public class ZundaLayer<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {

	public static final ContextKey<Boolean> ZUNDA_KEY = new ContextKey<>(TofuCraftReload.prefix("zunda"));

	public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/misc/zunda.png");

	public ZundaLayer(RenderLayerParent<T, M> p_i50947_1_) {
		super(p_i50947_1_);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T t, float v, float v1) {
		if (t.getRenderDataOrDefault(ZUNDA_KEY, false)) {
			EntityModel<T> entitymodel = this.getParentModel();
			entitymodel.setupAnim(t);
			submitNodeCollector.submitModel(entitymodel, t, poseStack, enchantSwirl(TEXTURE), t.lightCoords, OverlayTexture.NO_OVERLAY, t.outlineColor, null);
		}
	}

	public static RenderType enchantSwirl(Identifier resourceLocation) {
		return RenderType.create(
				"zunda_effect", RenderSetup.builder(ClientRegistrar.ZUNDA).withTexture("Sampler0", resourceLocation).setTextureTransform(TextureTransform.ARMOR_ENTITY_GLINT_TEXTURING).createRenderSetup());
	}
}