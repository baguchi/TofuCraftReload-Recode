package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import baguchi.tofucraft.entity.Tofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;


public class TofunianRoleLayer extends RenderLayer<TofunianRenderState, TofunianModel<TofunianRenderState>> {
	public TofunianRoleLayer(RenderLayerParent<TofunianRenderState, TofunianModel<TofunianRenderState>> tofunianRender) {
		super(tofunianRender);
	}


	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, TofunianRenderState tofunianRenderState, float v, float v1) {
		if (!tofunianRenderState.isInvisible && tofunianRenderState.roles != Tofunian.Roles.TOFUNIAN) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(tofunianRenderState), poseStack, submitNodeCollector, i, tofunianRenderState, -1, 1);

		}
	}

	public ResourceLocation getTextureLocation(TofunianRenderState entity) {
		String role = "";
		if (entity.roles != Tofunian.Roles.TOFUNIAN)
			role = entity.roles.name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofunian/" + role + ".png");
	}
}
