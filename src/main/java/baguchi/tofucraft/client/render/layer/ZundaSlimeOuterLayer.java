package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

import static baguchi.tofucraft.client.render.layer.ZundaLayer.ZUNDA_KEY;
import static baguchi.tofucraft.client.render.layer.ZundaLayer.enchantSwirl;

public class ZundaSlimeOuterLayer extends RenderLayer<SlimeRenderState, SlimeModel> {
	private final SlimeModel model;

	public ZundaSlimeOuterLayer(RenderLayerParent<SlimeRenderState, SlimeModel> p_174536_, EntityModelSet p_174537_) {
		super(p_174536_);
		this.model = new SlimeModel(p_174537_.bakeLayer(ModelLayers.SLIME_OUTER));
	}

	public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/misc/zunda.png");


	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, SlimeRenderState t, float v, float v1) {
		if (t.getRenderDataOrDefault(ZUNDA_KEY, false)) {
			this.model.setupAnim(t);
			submitNodeCollector.submitModel(this.model, t, poseStack, enchantSwirl(TEXTURE), t.lightCoords, OverlayTexture.NO_OVERLAY, t.outlineColor, null);
		}
	}

}