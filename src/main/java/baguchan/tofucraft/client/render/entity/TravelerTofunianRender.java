package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TravelerTofunianModel;
import baguchan.tofucraft.client.render.layer.TofunianEyeLayer;
import baguchan.tofucraft.entity.TravelerTofunian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TravelerTofunianRender extends MobRenderer<TravelerTofunian, TravelerTofunianModel<TravelerTofunian>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/traveler_tofunian.png");

	public TravelerTofunianRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TravelerTofunianModel<>(p_173956_.bakeLayer(TofuModelLayers.TRAVELER_TOFUNIAN)), 0.5F);
		this.addLayer(new CustomHeadLayer<>(this, p_173956_.getModelSet(), p_173956_.getItemInHandRenderer()));
		this.addLayer(new TofunianEyeLayer<>(this));
		this.addLayer(new ItemInHandLayer<>(this, p_173956_.getItemInHandRenderer()));
	}


	public ResourceLocation getTextureLocation(TravelerTofunian p_114029_) {
		return LOCATION;
	}
}
