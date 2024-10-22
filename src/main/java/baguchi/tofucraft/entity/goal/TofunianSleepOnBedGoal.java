package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.Tofunian;
import baguchi.tofucraft.utils.DayHelper;

public class TofunianSleepOnBedGoal extends SleepOnBedGoal {
	private final Tofunian creature;

	public TofunianSleepOnBedGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	@Override
	public boolean canUse() {
		if (DayHelper.isHalloween() || this.creature.isMeeting() && this.creature.getVillageCenter() != null) {
			return false;
		}
		return super.canUse();
	}

	public void tick() {
		super.tick();
	}

	protected boolean findNearestBlock() {
		if (this.creature.getTofunianHome() != null &&
				isValidTarget(this.creature.level(), this.creature.getTofunianHome())) {
			this.blockPos = this.creature.getTofunianHome();
			return true;
		}
		return super.findNearestBlock();
	}

}