package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.render.entity.TofuSlimeRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class TofuSlimeOuterLayer<T extends SlimeRenderState> extends RenderLayer<T, SlimeModel> {
    private final SlimeModel model;

    public TofuSlimeOuterLayer(RenderLayerParent<T, SlimeModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new SlimeModel(modelSet.bakeLayer(ModelLayers.SLIME_OUTER));
    }

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T t, float v, float v1) {
		boolean flag = t.appearsGlowing() && t.isInvisible;
		if (!t.isInvisible || flag) {
			RenderType renderType;
			if (flag) {
				renderType = RenderTypes.outline(TofuSlimeRender.LOCATION);
			} else {
				renderType = RenderTypes.entityTranslucent(TofuSlimeRender.LOCATION);
			}

			this.model.setupAnim(t);
			submitNodeCollector.submitModel(this.model, t, poseStack, renderType, t.lightCoords, LivingEntityRenderer.getOverlayCoords(t, 0.0F), t.outlineColor, null);
		}
	}
}
