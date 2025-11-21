package baguchi.tofucraft.client.render.state;

import baguchi.tofucraft.entity.Tofunian;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.AnimationState;

import javax.annotation.Nullable;

public class TofunianRenderState extends AbstractTofunianRenderState {
	public final AnimationState happyAnimationState = new AnimationState();
	public final AnimationState eatFoodAnimationState = new AnimationState();
	public final AnimationState waveAnimationState = new AnimationState();

	public Tofunian.Actions actions;
	public Tofunian.Roles roles;
	@Nullable
	public Identifier clothTexture;
	@Nullable
	public Identifier texture;
}
