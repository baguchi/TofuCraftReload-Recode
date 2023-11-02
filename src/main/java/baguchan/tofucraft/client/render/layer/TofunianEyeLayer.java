package baguchan.tofucraft.client.render.layer;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.model.AbstractTofunianModel;
import baguchan.tofucraft.entity.AbstractTofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianEyeLayer<T extends AbstractTofunian, M extends AbstractTofunianModel<T>> extends RenderLayer<T, M> {
	public static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian_eye.png");

	public TofunianEyeLayer(RenderLayerParent<T, M> tofunianRender) {
		super(tofunianRender);
	}

	public void render(PoseStack p_117720_, MultiBufferSource p_117721_, int p_117722_, T p_117723_, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
		float f3 = (p_117723_.tickCount + p_117726_ + p_117723_.getId());

		if (!p_117723_.isInvisible() && 0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(p_117723_), p_117720_, p_117721_, p_117722_, p_117723_, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return LOCATION;
	}
}
