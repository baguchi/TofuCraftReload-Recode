package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuGandlemModel;
import baguchan.tofucraft.client.render.layer.TofuGandlemChargedLayer;
import baguchan.tofucraft.client.render.layer.TofuGandlemEmissiveLayer;
import baguchan.tofucraft.client.render.state.TofuGandlemRenderState;
import baguchan.tofucraft.entity.TofuGandlem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuGandlemRender extends MobRenderer<TofuGandlem, TofuGandlemRenderState, TofuGandlemModel<TofuGandlemRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem.png");
	private static final ResourceLocation SHOOTING_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem_shooting.png");

	private static final ResourceLocation CHARGE_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem_charge.png");

	public TofuGandlemRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofuGandlemModel(p_173956_.bakeLayer(TofuModelLayers.TOFU_GANDLEM)), 0.5F);
		this.addLayer(new TofuGandlemEmissiveLayer<>(this, LOCATION, (p_234809_, p_234810_, p_234811_) -> {
			return p_234809_.deathTime <= 0 && !p_234809_.sleep ? Mth.clamp(p_234809_.health / p_234809_.maxHealth + 0.4F, 0.4F, 1.0F) : 0.0F;
		}, TofuGandlemModel::getCoreModelParts));
		this.addLayer(new TofuGandlemChargedLayer<>(this, CHARGE_LOCATION, (p_234809_, p_234810_, p_234811_) -> {
			return p_234809_.health > 0 ? 1.0F : 0.0F;
		}, TofuGandlemModel::getCoreModelParts));
	}

	@Override
	public TofuGandlemRenderState createRenderState() {
		return new TofuGandlemRenderState();
	}

	@Override
	public void extractRenderState(TofuGandlem p_362733_, TofuGandlemRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.idleAnimationState.copyFrom(p_362733_.idleAnimationState);
		p_360515_.attackAnimationState.copyFrom(p_362733_.attackAnimationState);
		p_360515_.shootAnimationState.copyFrom(p_362733_.shootAnimationState);
		p_360515_.shootingAnimationState.copyFrom(p_362733_.shootingAnimationState);
		p_360515_.rushAnimationState.copyFrom(p_362733_.rushAnimationState);
		p_360515_.deathAnimationState.copyFrom(p_362733_.deathAnimationState);
		p_360515_.chargeAnimationState.copyFrom(p_362733_.chargeAnimationState);
		p_360515_.chargeStopAnimationState.copyFrom(p_362733_.chargeStopAnimationState);
		p_360515_.chargeFailAnimationState.copyFrom(p_362733_.chargeFailAnimationState);
		p_360515_.chargeHealth = p_362733_.getChargeHealth();
		p_360515_.charge = p_362733_.isCharging();
		p_360515_.shoot = p_362733_.isShoot();
		p_360515_.sleep = p_362733_.isSleepSelf();
		p_360515_.health = p_362733_.getHealth();
		p_360515_.maxHealth = p_362733_.getMaxHealth();
		p_360515_.fullCharge = p_362733_.isFullCharge();
	}

	protected float getFlipDegrees() {
		return 0.0F;
	}


	public ResourceLocation getTextureLocation(TofuGandlemRenderState p_114029_) {
		return p_114029_.charge || p_114029_.shoot ? SHOOTING_LOCATION : LOCATION;
	}
}
