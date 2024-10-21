package baguchan.tofucraft.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class ShuDofuSpiderRenderState extends LivingEntityRenderState {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState deathAnimationState = new AnimationState();
	public final AnimationState jumpAnimationState = new AnimationState();

	public final AnimationState graspAnimationState = new AnimationState();
	public final AnimationState graspPreAnimationState = new AnimationState();

	public float leftLegAnimation;
	public float rightLegAnimation;
	public boolean jump;
	public boolean angry;
}
