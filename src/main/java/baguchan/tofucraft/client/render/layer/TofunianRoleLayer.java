package baguchan.tofucraft.client.render.layer;

import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.entity.Tofunian;
import baguchan.tofucraft.registry.TofunianProfessions;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianRoleLayer extends RenderLayer<Tofunian, TofunianModel<Tofunian>> {
	public TofunianRoleLayer(RenderLayerParent<Tofunian, TofunianModel<Tofunian>> tofunianRender) {
		super(tofunianRender);
	}

	public void render(PoseStack p_117720_, MultiBufferSource p_117721_, int p_117722_, Tofunian tofunian, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
		if (!tofunian.isInvisible() && !tofunian.getRole().is(TofunianProfessions.NONE)) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(tofunian), p_117720_, p_117721_, p_117722_, tofunian, -1);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(Tofunian entity) {
		String role = "";
		String roleNameSpace = "tofucraft";
		if (!entity.getRole().is(TofunianProfessions.NONE)) {
			role = entity.getRole().getKey().location().getPath().toLowerCase();
			roleNameSpace = entity.getRole().getKey().location().getNamespace().toLowerCase();
		}
		return ResourceLocation.parse(roleNameSpace + ":textures/entity/tofunian/" + role + ".png");
	}
}
