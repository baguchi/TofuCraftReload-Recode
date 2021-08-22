package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;

public class TofunianSleepOnBedGoal extends SleepOnBedGoal {
	private final TofunianEntity creature;

	public TofunianSleepOnBedGoal(TofunianEntity creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}


	public void tick() {
		super.tick();
	}

	protected boolean findNearestBlock() {
		if (this.creature.getTofunainHome() != null &&
				isValidTarget(this.creature.getLevel(), this.creature.getTofunainHome())) {
			this.blockPos = this.creature.getTofunainHome();
			return true;
		}
		return super.findNearestBlock();
	}

}