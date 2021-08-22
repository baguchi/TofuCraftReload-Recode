package baguchan.tofucraft.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class WakeUpGoal extends Goal {
	private final PathfinderMob creature;

	public WakeUpGoal(PathfinderMob creature) {
		this.creature = creature;
	}

	public boolean canUse() {
		return ((this.creature.level.isDay() && this.creature.isSleeping()) || (this.creature.getSleepingPos().isPresent() && this.creature.getY() <= this.creature.getSleepingPos().get().getY() + 0.4D && !this.creature.getSleepingPos().get().closerThan(this.creature.position(), 1.14D) && this.creature.isSleeping()));
	}

	public void start() {
		super.start();
		this.creature.stopSleeping();
	}
}