package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.render.layer.TofuPigTypeLayer;
import baguchan.tofucraft.client.render.state.TofuPigRenderState;
import baguchan.tofucraft.entity.TofuPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuPigRender extends MobRenderer<TofuPig, TofuPigRenderState, PigModel> {
	private static final ResourceLocation PIG_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofupig/tofupig.png");

	public TofuPigRender(EntityRendererProvider.Context p_174304_) {
		super(p_174304_, new PigModel(p_174304_.bakeLayer(ModelLayers.PIG)), 0.5F);
		this.addLayer(new TofuPigTypeLayer(this));
		this.addLayer(new SaddleLayer(this, new PigModel(p_174304_.bakeLayer(ModelLayers.PIG_SADDLE)), ResourceLocation.parse("textures/entity/pig/pig_saddle.png")));
	}

	@Override
	public TofuPigRenderState createRenderState() {
		return new TofuPigRenderState();
	}

	@Override
	public void extractRenderState(TofuPig p_362733_, TofuPigRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.type = p_362733_.getTofuPigType();
		p_360515_.isSaddled = p_362733_.isSaddled();
	}

	@Override
	public ResourceLocation getTextureLocation(TofuPigRenderState p_114482_) {
		return PIG_LOCATION;
	}

}