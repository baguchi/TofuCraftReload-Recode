package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;


public class TofunianRoleLayer extends RenderLayer<TofunianRenderState, TofunianModel<TofunianRenderState>> {
	public TofunianRoleLayer(RenderLayerParent<TofunianRenderState, TofunianModel<TofunianRenderState>> tofunianRender) {
		super(tofunianRender);
	}


	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, TofunianRenderState tofunianRenderState, float v, float v1) {
		if (!tofunianRenderState.isBaby && !tofunianRenderState.isInvisible && tofunianRenderState.rolesTexture != null) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(tofunianRenderState), poseStack, submitNodeCollector, i, tofunianRenderState, -1, 3);

		}
	}

	public Identifier getTextureLocation(TofunianRenderState entity) {
		String role = "";
		String roleNameSpace = "tofucraft";
		if (entity.rolesTexture != null) {
			roleNameSpace = entity.rolesTexture.getNamespace().toLowerCase();
			role = entity.rolesTexture.getPath().toLowerCase();
		}

		return Identifier.parse(roleNameSpace + ":textures/entity/tofunian/" + role + ".png");
	}
}
