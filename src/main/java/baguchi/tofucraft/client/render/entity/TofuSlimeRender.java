package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.layer.TofuSlimeOuterLayer;
import baguchi.tofucraft.client.render.layer.ZundaTofuSlimeOuterLayer;
import baguchi.tofucraft.client.render.state.TofuSlimeRenderState;
import baguchi.tofucraft.entity.TofuSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class TofuSlimeRender extends MobRenderer<TofuSlime, TofuSlimeRenderState, SlimeModel> {
	public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_slime.png");

	public TofuSlimeRender(EntityRendererProvider.Context p_174391_) {
		super(p_174391_, new SlimeModel(p_174391_.bakeLayer(ModelLayers.SLIME)), 0.25F);
		this.addLayer(new TofuSlimeOuterLayer<>(this, p_174391_.getModelSet()));
		this.addLayer(new ZundaTofuSlimeOuterLayer(this, p_174391_.getModelSet()));
	}

	@Override
	public void submit(TofuSlimeRenderState p_433493_, PoseStack p_434615_, SubmitNodeCollector p_433768_, CameraRenderState p_450931_) {
		this.shadowRadius = 0.25F * (float) p_433493_.size;

		super.submit(p_433493_, p_434615_, p_433768_, p_450931_);
	}

	@Override
	public ResourceLocation getTextureLocation(TofuSlimeRenderState p_365351_) {
		return LOCATION;
	}

	@Override
	public TofuSlimeRenderState createRenderState() {
		return new TofuSlimeRenderState();
	}

	@Override
	public void extractRenderState(TofuSlime p_362664_, TofuSlimeRenderState p_365237_, float p_361099_) {
		super.extractRenderState(p_362664_, p_365237_, p_361099_);
		p_365237_.squish = Mth.lerp(p_361099_, p_362664_.oSquish, p_362664_.squish);
		p_365237_.size = p_362664_.getSize();
		p_365237_.convert = p_362664_.isZundaConverting();
	}

	@Override
	protected void scale(TofuSlimeRenderState p_362807_, PoseStack p_115390_) {
		int i = p_362807_.size;
		float f = p_362807_.squish / ((float) i * 0.5F + 1.0F);
		float f1 = 1.0F / (f + 1.0F);
		p_115390_.scale(f1 * (float) i, 1.0F / f1 * (float) i, f1 * (float) i);
	}

	@Override
	protected boolean isShaking(TofuSlimeRenderState p_361234_) {
		return super.isShaking(p_361234_) || p_361234_.convert;
	}
}
