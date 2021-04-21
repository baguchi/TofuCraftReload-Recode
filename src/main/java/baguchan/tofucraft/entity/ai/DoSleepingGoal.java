package baguchan.tofucraft.entity.ai;

import java.util.EnumSet;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;

public class DoSleepingGoal extends Goal {
	private final CreatureEntity creature;

	public DoSleepingGoal(CreatureEntity creature) {
		this.creature = creature;
		func_220684_a(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		return this.creature.func_70608_bn();
	}
}
