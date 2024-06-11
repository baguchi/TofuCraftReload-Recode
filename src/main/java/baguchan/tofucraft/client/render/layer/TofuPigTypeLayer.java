package baguchan.tofucraft.client.render.layer;

import baguchan.tofucraft.entity.TofuPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuPigTypeLayer extends RenderLayer<TofuPig, PigModel<TofuPig>> {

	public TofuPigTypeLayer(RenderLayerParent<TofuPig, PigModel<TofuPig>> tofupigRender) {
		super(tofupigRender);
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, TofuPig p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
		if (!p_117352_.isInvisible() && p_117352_.getTofuPigType() != TofuPig.TofuPigType.NORMAL) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(p_117352_), p_117349_, p_117350_, p_117351_, p_117352_, -1);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(TofuPig entity) {
		String type = "";

		if (entity.getTofuPigType() != TofuPig.TofuPigType.NORMAL)
			type = entity.getTofuPigType().name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofupig/tofupig_" + type + ".png");
	}

}
