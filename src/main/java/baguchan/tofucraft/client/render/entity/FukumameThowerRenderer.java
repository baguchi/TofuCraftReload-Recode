package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.FukumameThowerModel;
import baguchan.tofucraft.client.render.state.FukumameThowerRenderState;
import baguchan.tofucraft.entity.FukumameThower;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.item.CrossbowItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FukumameThowerRenderer extends HumanoidMobRenderer<FukumameThower, FukumameThowerRenderState, FukumameThowerModel> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/piglin_fukumame_thower.png");

	public FukumameThowerRenderer(EntityRendererProvider.Context p_174344_) {
		super(p_174344_, new FukumameThowerModel(p_174344_.bakeLayer(TofuModelLayers.FUKUMAME_THOWER)), 0.5F);
		this.addLayer(
				new HumanoidArmorLayer(
						this,
						new HumanoidArmorModel(p_174344_.bakeLayer(ModelLayers.PIGLIN_INNER_ARMOR)),
						new HumanoidArmorModel(p_174344_.bakeLayer(ModelLayers.PIGLIN_OUTER_ARMOR)),
						new HumanoidArmorModel(p_174344_.bakeLayer(ModelLayers.PIGLIN_BABY_INNER_ARMOR)),
						new HumanoidArmorModel(p_174344_.bakeLayer(ModelLayers.PIGLIN_BABY_OUTER_ARMOR)),
						p_174344_.getEquipmentRenderer()
				)
		);
	}


	protected boolean isShaking(FukumameThowerRenderState p_115712_) {
		return super.isShaking(p_115712_) || p_115712_.isConverting;
	}


	public void extractRenderState(FukumameThower p_361113_, FukumameThowerRenderState p_364996_, float p_362352_) {
		super.extractRenderState(p_361113_, p_364996_, p_362352_);
		p_364996_.isBrute = p_361113_.getType() == EntityType.PIGLIN_BRUTE;
		p_364996_.armPose = p_361113_.getArmPose();
		p_364996_.maxCrossbowChageDuration = (float) CrossbowItem.getChargeDuration(p_361113_.getUseItem(), p_361113_);
		p_364996_.isConverting = p_361113_.isConverting();
		p_364996_.isCharge = p_361113_.isCharge();
	}

	@Override
	public ResourceLocation getTextureLocation(FukumameThowerRenderState p_110775_1_) {
		return TEXTURE;
	}

	@Override
	public FukumameThowerRenderState createRenderState() {
		return new FukumameThowerRenderState();
	}
}