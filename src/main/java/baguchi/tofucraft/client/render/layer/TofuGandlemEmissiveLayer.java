package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.model.TofuGandlemModel;
import baguchi.tofucraft.client.render.state.TofuGandlemRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class TofuGandlemEmissiveLayer<T extends TofuGandlemRenderState, M extends TofuGandlemModel<T>> extends RenderLayer<T, M> {
	private final ResourceLocation texture;
	private final TofuGandlemEmissiveLayer.AlphaFunction<T> alphaFunction;
	private final TofuGandlemEmissiveLayer.DrawSelector<T, M> drawSelector;

	public TofuGandlemEmissiveLayer(RenderLayerParent<T, M> p_234885_, ResourceLocation p_234886_, TofuGandlemEmissiveLayer.AlphaFunction<T> p_234887_, TofuGandlemEmissiveLayer.DrawSelector<T, M> p_234888_) {
		super(p_234885_);
		this.texture = p_234886_;
		this.alphaFunction = p_234887_;
		this.drawSelector = p_234888_;
	}

	private void onlyDrawSelectedParts() {
		List<ModelPart> list = this.drawSelector.getPartsToDraw(this.getParentModel());
		this.getParentModel().root().getAllParts().forEach((p_234918_) -> {
			p_234918_.skipDraw = true;
		});
		list.forEach((p_234916_) -> {
			p_234916_.skipDraw = false;
		});
	}

	private void resetDrawForAllParts() {
		this.getParentModel().root().getAllParts().forEach((p_234913_) -> {
			p_234913_.skipDraw = false;
		});
	}
	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T state, float v, float v1) {
		if (!state.isInvisible) {
			float f = (float) state.ageInTicks;
			this.onlyDrawSelectedParts();
			submitNodeCollector.submitModel(this.getParentModel(), state, poseStack, RenderType.entityTranslucentEmissive(this.texture), state.lightCoords, LivingEntityRenderer.getOverlayCoords(state, 0.0F), state.outlineColor, null);
			this.resetDrawForAllParts();
		}
	}


	public interface AlphaFunction<T extends TofuGandlemRenderState> {
		float apply(T p_234920_, float p_234921_, float p_234922_);
	}


	public interface DrawSelector<T extends TofuGandlemRenderState, M extends EntityModel<T>> {
		List<ModelPart> getPartsToDraw(M p_234924_);
	}
}