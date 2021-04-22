package baguchan.tofucraft.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class DoSleepingGoal extends Goal {
	private final CreatureEntity creature;

	public DoSleepingGoal(CreatureEntity creature) {
		this.creature = creature;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		return this.creature.isSleeping();
	}
}
