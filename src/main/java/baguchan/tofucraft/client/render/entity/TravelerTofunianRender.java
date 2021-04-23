package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.client.model.BipedTofunianModel;
import baguchan.tofucraft.client.render.layer.AdvancedHeldItemLayer;
import baguchan.tofucraft.entity.TravelerTofunianEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TravelerTofunianRender<T extends TravelerTofunianEntity> extends MobRenderer<T, BipedTofunianModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("tofucraft:textures/entity/tofunian/traveler_tofunian.png");

	public TravelerTofunianRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new BipedTofunianModel(), 0.5F);
		addLayer(new BipedArmorLayer(this, new BipedModel(0.25F), new BipedModel(0.5F)));
		addLayer(new HeadLayer(this));
		addLayer(new ElytraLayer(this));
		addLayer(new AdvancedHeldItemLayer(this));
	}

	public ResourceLocation getTextureLocation(T entity) {
		return TEXTURE;
	}
}
