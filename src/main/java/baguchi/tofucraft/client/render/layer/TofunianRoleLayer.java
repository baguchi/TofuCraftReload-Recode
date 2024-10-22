package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import baguchi.tofucraft.entity.Tofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianRoleLayer extends RenderLayer<TofunianRenderState, TofunianModel<TofunianRenderState>> {
	public TofunianRoleLayer(RenderLayerParent<TofunianRenderState, TofunianModel<TofunianRenderState>> tofunianRender) {
		super(tofunianRender);
	}

	public void render(PoseStack p_117720_, MultiBufferSource p_117721_, int p_117722_, TofunianRenderState p_117723_, float p_117724_, float p_117725_) {
		if (!p_117723_.isInvisible && p_117723_.roles != Tofunian.Roles.TOFUNIAN) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(p_117723_), p_117720_, p_117721_, p_117722_, p_117723_, -1);
		}
	}

	public ResourceLocation getTextureLocation(TofunianRenderState entity) {
		String role = "";
		if (entity.roles != Tofunian.Roles.TOFUNIAN)
			role = entity.roles.name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofunian/" + role + ".png");
	}
}
