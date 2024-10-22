package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.render.state.TofuPigRenderState;
import baguchi.tofucraft.entity.TofuPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuPigTypeLayer extends RenderLayer<TofuPigRenderState, PigModel> {

	public TofuPigTypeLayer(RenderLayerParent<TofuPigRenderState, PigModel> tofupigRender) {
		super(tofupigRender);
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, TofuPigRenderState p_117352_, float p_117353_, float p_117354_) {
		if (!p_117352_.isInvisible && p_117352_.type != TofuPig.TofuPigType.NORMAL) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(p_117352_), p_117349_, p_117350_, p_117351_, p_117352_, -1);
		}
	}

	public ResourceLocation getTextureLocation(TofuPigRenderState entity) {
		String type = "";

		if (entity.type != TofuPig.TofuPigType.NORMAL)
			type = entity.type.name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofupig/tofupig_" + type + ".png");
	}

}
