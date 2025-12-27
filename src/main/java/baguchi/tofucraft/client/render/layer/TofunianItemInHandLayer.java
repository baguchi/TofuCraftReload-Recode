package baguchi.tofucraft.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;

public class TofunianItemInHandLayer<S extends ArmedEntityRenderState, M extends EntityModel<S> & ArmedModel<S>> extends ItemInHandLayer<S, M> {
	public TofunianItemInHandLayer(RenderLayerParent<S, M> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack p_433803_, SubmitNodeCollector p_434482_, int p_433450_, S p_434546_, float p_433047_, float p_433527_) {
		if (!p_434546_.isBaby) {
			super.submit(p_433803_, p_434482_, p_433450_, p_434546_, p_433047_, p_433527_);
		}
	}
}
