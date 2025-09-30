package baguchi.tofucraft.client.render.entity;

import baguchi.bagus_lib.client.layer.CustomArmorLayer;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TravelerTofunianModel;
import baguchi.tofucraft.client.render.layer.TofunianEyeLayer;
import baguchi.tofucraft.client.render.state.TravelerTofunianRenderState;
import baguchi.tofucraft.entity.TravelerTofunian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;


public class TravelerTofunianRender extends MobRenderer<TravelerTofunian, TravelerTofunianRenderState, TravelerTofunianModel<TravelerTofunianRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/traveler_tofunian.png");

	public TravelerTofunianRender(EntityRendererProvider.Context context) {
		super(context, new TravelerTofunianModel<>(context.bakeLayer(TofuModelLayers.TRAVELER_TOFUNIAN)), 0.5F);
		this.addLayer(new TofunianEyeLayer<>(this));
		this.addLayer(new CustomArmorLayer<>(this, context));
		this.addLayer(new ItemInHandLayer<>(this));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));

	}

	@Override
	public TravelerTofunianRenderState createRenderState() {
		return new TravelerTofunianRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(TravelerTofunianRenderState p_368654_) {
		return LOCATION;
	}

	@Override
	public void extractRenderState(TravelerTofunian p_362733_, TravelerTofunianRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		HumanoidMobRenderer.extractHumanoidRenderState(p_362733_, p_360515_, p_361157_, this.itemModelResolver);

		p_360515_.id = p_362733_.getId();
		p_360515_.isPassenger = p_362733_.isPassenger() && (p_362733_.getVehicle() != null && p_362733_.getVehicle().shouldRiderSit());

		p_360515_.unhappyCounter = p_362733_.getUnhappyCounter();
		p_360515_.attackTime = p_362733_.attackAnim;
		p_360515_.waveAnimationState.copyFrom(p_362733_.waveAnimationState);
	}
}
