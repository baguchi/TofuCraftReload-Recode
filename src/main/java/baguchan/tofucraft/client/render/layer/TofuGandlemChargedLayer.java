package baguchan.tofucraft.client.render.layer;

import baguchan.tofucraft.client.model.TofuGandlemModel;
import baguchan.tofucraft.entity.TofuGandlem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class TofuGandlemChargedLayer<T extends TofuGandlem, M extends TofuGandlemModel<T>> extends RenderLayer<T, M> {
	private final ResourceLocation texture;
	private final TofuGandlemChargedLayer.AlphaFunction<T> alphaFunction;
	private final TofuGandlemChargedLayer.DrawSelector<T, M> drawSelector;

	public TofuGandlemChargedLayer(RenderLayerParent<T, M> p_234885_, ResourceLocation p_234886_, TofuGandlemChargedLayer.AlphaFunction<T> p_234887_, TofuGandlemChargedLayer.DrawSelector<T, M> p_234888_) {
		super(p_234885_);
		this.texture = p_234886_;
		this.alphaFunction = p_234887_;
		this.drawSelector = p_234888_;
	}

	public void render(PoseStack p_234902_, MultiBufferSource p_234903_, int p_234904_, T p_234905_, float p_234906_, float p_234907_, float p_234908_, float p_234909_, float p_234910_, float p_234911_) {
		if (p_234905_.isFullCharge()) {
			float f = (float) p_234905_.tickCount + p_234908_;
			this.onlyDrawSelectedParts();
			VertexConsumer vertexconsumer = p_234903_.getBuffer(RenderType.energySwirl(this.texture, this.xOffset(f) % 1.0F, f * 0.01F % 1.0F));
			this.getParentModel().renderToBuffer(p_234902_, vertexconsumer, p_234904_, LivingEntityRenderer.getOverlayCoords(p_234905_, 0.0F));
			this.resetDrawForAllParts();
		}
	}

	protected float xOffset(float p_117702_) {
		return Mth.cos(p_117702_ * 0.02F) * 3.0F;
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

	@OnlyIn(Dist.CLIENT)
	public interface AlphaFunction<T extends TofuGandlem> {
		float apply(T p_234920_, float p_234921_, float p_234922_);
	}

	@OnlyIn(Dist.CLIENT)
	public interface DrawSelector<T extends TofuGandlem, M extends EntityModel<T>> {
		List<ModelPart> getPartsToDraw(M p_234924_);
	}
}