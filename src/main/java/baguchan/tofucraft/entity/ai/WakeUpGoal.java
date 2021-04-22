package baguchan.tofucraft.entity.ai;

import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

public class WakeUpGoal extends Goal {
	private final CreatureEntity creature;

	public WakeUpGoal(CreatureEntity creature) {
		this.creature = creature;
	}

	public boolean canUse() {
		return ((this.creature.level.isDay() && this.creature.isSleeping()) || (this.creature.getSleepingPos().isPresent() && this.creature.getY() <= ((BlockPos) this.creature.getSleepingPos().get()).getY() + 0.4D && !((BlockPos) this.creature.getSleepingPos().get()).closerThan((IPosition) this.creature.position(), 1.14D) && this.creature.isSleeping()));
	}

	public void start() {
		super.start();
		this.creature.clearSleepingPos();
	}
}
