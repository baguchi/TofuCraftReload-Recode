package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.render.state.TofuPigRenderState;
import baguchi.tofucraft.entity.TofuPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;


public class TofuPigTypeLayer extends RenderLayer<TofuPigRenderState, PigModel> {

	public TofuPigTypeLayer(RenderLayerParent<TofuPigRenderState, PigModel> tofupigRender) {
		super(tofupigRender);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, TofuPigRenderState tofuPigRenderState, float v, float v1) {
		if (!tofuPigRenderState.isInvisible && tofuPigRenderState.type != TofuPig.TofuPigType.NORMAL) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(tofuPigRenderState), poseStack, submitNodeCollector, i, tofuPigRenderState, -1, 1);
		}
	}

	public ResourceLocation getTextureLocation(TofuPigRenderState entity) {
		String type = "";

		if (entity.type != TofuPig.TofuPigType.NORMAL)
			type = entity.type.name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofu_pig/tofu_pig_" + type + ".png");
	}

}
