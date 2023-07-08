package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.model.FukumameThowerModel;
import baguchan.tofucraft.entity.FukumameThower;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FukumameThowerRenderer<T extends FukumameThower> extends HumanoidMobRenderer<T, FukumameThowerModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/piglin_fukumame_thower.png");

	public FukumameThowerRenderer(EntityRendererProvider.Context p_174344_, ModelLayerLocation p_174345_, ModelLayerLocation p_174346_, ModelLayerLocation p_174347_, boolean p_174348_) {
		super(p_174344_, (FukumameThowerModel<T>) createModel(p_174344_.getModelSet(), p_174345_, p_174348_), 0.5F, 1.0019531F, 1.0F, 1.0019531F);
		this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel(p_174344_.bakeLayer(p_174346_)), new HumanoidArmorModel(p_174344_.bakeLayer(p_174347_)), p_174344_.getModelManager()));
	}

	private static FukumameThowerModel<FukumameThower> createModel(EntityModelSet p_174350_, ModelLayerLocation p_174351_, boolean p_174352_) {
		FukumameThowerModel<FukumameThower> piglinmodel = new FukumameThowerModel<>(p_174350_.bakeLayer(p_174351_));
		if (p_174352_) {
			piglinmodel.rightEar.visible = false;
		}

		return piglinmodel;
	}

	protected boolean isShaking(T p_115712_) {
		return super.isShaking(p_115712_) || p_115712_ instanceof AbstractPiglin && ((AbstractPiglin) p_115712_).isConverting();
	}


	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return TEXTURE;
	}
}