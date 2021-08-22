package baguchan.tofucraft.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class DoSleepingGoal extends Goal {
	private final PathfinderMob creature;

	public DoSleepingGoal(PathfinderMob creature) {
		this.creature = creature;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		return this.creature.isSleeping();
	}
}