package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;

public class TofunianSleepOnBedGoal extends SleepOnBedGoal {
	private final TofunianEntity creature;

	public TofunianSleepOnBedGoal(TofunianEntity creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}


	public void tick() {
		if (isReachedTarget()) {
			this.creature.setTofunainHome(this.blockPos);
		}
		super.tick();
	}

}