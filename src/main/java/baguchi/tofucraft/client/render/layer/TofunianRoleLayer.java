package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;


public class TofunianRoleLayer extends RenderLayer<TofunianRenderState, TofunianModel<TofunianRenderState>> {
	public TofunianRoleLayer(RenderLayerParent<TofunianRenderState, TofunianModel<TofunianRenderState>> tofunianRender) {
		super(tofunianRender);
	}


	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, TofunianRenderState tofunianRenderState, float v, float v1) {
		if (!tofunianRenderState.isInvisible && tofunianRenderState.rolesTexture != null) {
			renderColoredCutoutModel(this.getParentModel(), tofunianRenderState.rolesTexture, poseStack, submitNodeCollector, i, tofunianRenderState, -1, 1);

		}
	}
}
