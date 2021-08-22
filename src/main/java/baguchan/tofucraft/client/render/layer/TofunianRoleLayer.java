package baguchan.tofucraft.client.render.layer;

import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.entity.TofunianEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianRoleLayer extends RenderLayer<TofunianEntity, TofunianModel<TofunianEntity>> {
	private static final ResourceLocation WOLF_COLLAR_LOCATION = new ResourceLocation("textures/entity/wolf/wolf_collar.png");

	public TofunianRoleLayer(RenderLayerParent<TofunianEntity, TofunianModel<TofunianEntity>> tofunianRender) {
		super(tofunianRender);
	}

	public void render(PoseStack p_117720_, MultiBufferSource p_117721_, int p_117722_, TofunianEntity p_117723_, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
		if (!p_117723_.isInvisible() && p_117723_.getRole() != TofunianEntity.Roles.TOFUNIAN) {
			renderColoredCutoutModel(this.getParentModel(), WOLF_COLLAR_LOCATION, p_117720_, p_117721_, p_117722_, p_117723_, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(TofunianEntity entity) {
		String role = "";
		if (entity.getRole() != TofunianEntity.Roles.TOFUNIAN)
			role = entity.getRole().name().toLowerCase();
		return new ResourceLocation("tofucraft:textures/entity/tofunian/" + role + ".png");
	}
}
