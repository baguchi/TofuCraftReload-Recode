package baguchi.tofucraft.client.render.state;

import baguchi.tofucraft.entity.Tofunian;
import net.minecraft.world.entity.AnimationState;

public class TofunianRenderState extends AbstractTofunianRenderState {
	public final AnimationState happyAnimationState = new AnimationState();
	public final AnimationState eatFoodAnimationState = new AnimationState();
	public Tofunian.Actions actions;
	public Tofunian.Roles roles;
	public Tofunian.TofunianType type;
}
