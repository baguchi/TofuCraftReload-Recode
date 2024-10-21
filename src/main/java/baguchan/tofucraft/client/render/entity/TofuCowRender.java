package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.render.state.TofuCowRenderState;
import baguchan.tofucraft.entity.TofuCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuCowRender extends MobRenderer<TofuCow, TofuCowRenderState, CowModel> {
	public TofuCowRender(EntityRendererProvider.Context p_174304_) {
		super(p_174304_, new CowModel(p_174304_.bakeLayer(ModelLayers.COW)), 0.5F);
	}

	@Override
	public TofuCowRenderState createRenderState() {
		return new TofuCowRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(TofuCowRenderState entity) {
		String type = "";

		if (entity.type != TofuCow.TofuCowType.NORMAL)
			type = "_" + entity.type.name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofucow/tofucow" + type + ".png");
	}

	@Override
	public void extractRenderState(TofuCow p_362733_, TofuCowRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.type = p_362733_.getTofuCowType();
	}
}