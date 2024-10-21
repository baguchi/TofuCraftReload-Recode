package baguchan.tofucraft.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class TofuGandlemRenderState extends LivingEntityRenderState {

	public AnimationState idleAnimationState = new AnimationState();
	public AnimationState attackAnimationState = new AnimationState();
	public AnimationState shootAnimationState = new AnimationState();
	public AnimationState shootingAnimationState = new AnimationState();
	public AnimationState rushAnimationState = new AnimationState();
	public AnimationState deathAnimationState = new AnimationState();

	public AnimationState chargeAnimationState = new AnimationState();
	public AnimationState chargeStopAnimationState = new AnimationState();
	public AnimationState chargeFailAnimationState = new AnimationState();
	public float health;
	public float maxHealth;
	public float chargeHealth;
	public boolean fullCharge;
	public boolean charge;
	public boolean shoot;
	public boolean sleep;
}
