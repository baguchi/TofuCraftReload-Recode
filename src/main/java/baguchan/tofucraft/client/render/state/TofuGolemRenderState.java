package baguchan.tofucraft.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class TofuGolemRenderState extends LivingEntityRenderState {
	public AnimationState spitAnimationState = new AnimationState();
	public AnimationState idleAnimationState = new AnimationState();
}
