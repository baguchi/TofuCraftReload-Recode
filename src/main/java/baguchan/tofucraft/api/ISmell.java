package baguchan.tofucraft.api;

import net.minecraft.world.entity.AnimationState;

public interface ISmell {

	AnimationState getCoughAnimationState();

	boolean cannotSmell();

	void setCannotSmell(boolean cannotSmell);
}
