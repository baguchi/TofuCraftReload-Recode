package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TofuGandlemModel;
import baguchi.tofucraft.client.render.state.TofuGandlemRenderState;
import baguchi.tofucraft.entity.TofuGandlem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;


public class TofuGandlemRender extends MobRenderer<TofuGandlem, TofuGandlemRenderState, TofuGandlemModel<TofuGandlemRenderState>> {
	private static final Identifier LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem.png");
	private static final Identifier SHOOTING_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem_shooting.png");
	private static final Identifier EYE_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem_eye.png");

	private static final Identifier CHARGE_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem_charge.png");

	public TofuGandlemRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofuGandlemModel(p_173956_.bakeLayer(TofuModelLayers.TOFU_GANDLEM)), 0.5F);

		this.addLayer(
				new LivingEntityEmissiveLayer<>(
						this,
						renderState -> EYE_LOCATION,
						(renderState, ageInTicks) -> {
							if (renderState.health <= 0.0F) {
								return 0.0F;
							}
							return renderState.maxHealth / 2 >= renderState.health ? Math.max(0.2F, Mth.cos(ageInTicks * 0.045F) * 1.0F) : 1.0F;
						},
						this.getModel(),
						RenderTypes::entityTranslucentEmissive,
						false
				)
		);

		this.addLayer(
				new LivingEntityEmissiveLayer<>(
						this,
						renderState -> CHARGE_LOCATION,
						(renderState, ageInTicks) -> renderState.chargeHealth > 0.0F ? 1.0F : 0.0F,
						this.getModel(),
						RenderTypes::entityTranslucentEmissive,
						false
				)
		);
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
		p_360515_.preShootAnimationState.copyFrom(p_362733_.preShootAnimationState);
		p_360515_.stopShootAnimationState.copyFrom(p_362733_.stopShootAnimationState);
		p_360515_.shootAnimationState.copyFrom(p_362733_.shootAnimationState);
		p_360515_.shootingAnimationState.copyFrom(p_362733_.shootingAnimationState);
		p_360515_.rushAnimationState.copyFrom(p_362733_.rushAnimationState);
		p_360515_.deathAnimationState.copyFrom(p_362733_.deathAnimationState);
		p_360515_.chargeAnimationState.copyFrom(p_362733_.chargeAnimationState);
		p_360515_.chargeStopAnimationState.copyFrom(p_362733_.chargeStopAnimationState);
		p_360515_.chargeFailAnimationState.copyFrom(p_362733_.chargeFailAnimationState);
		p_360515_.chargeHealth = p_362733_.getChargeHealth();
		p_360515_.charge = p_362733_.isCharging();
		p_360515_.shoot = p_362733_.getAction() == TofuGandlem.Actions.PRE_SHOOT || p_362733_.getAction() == TofuGandlem.Actions.SHOOT;
		p_360515_.sleep = p_362733_.isSleepSelf();
		p_360515_.health = p_362733_.getHealth();
		p_360515_.maxHealth = p_362733_.getMaxHealth();
		p_360515_.fullCharge = p_362733_.isFullCharge();
	}

	protected float getFlipDegrees() {
		return 0.0F;
	}


	public Identifier getTextureLocation(TofuGandlemRenderState p_114029_) {
		return p_114029_.charge || p_114029_.shoot ? SHOOTING_LOCATION : LOCATION;
	}
}
