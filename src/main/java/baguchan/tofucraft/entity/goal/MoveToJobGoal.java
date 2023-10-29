package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class MoveToJobGoal extends MoveToBlockGoal {
	private final Tofunian creature;


	public MoveToJobGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.level().isDay() && this.creature.getRole() != Tofunian.Roles.TOFUNIAN && this.creature.getTofunianJobBlock() != null && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.level().isDay() && this.creature.getTofunianJobBlock() != null && this.creature.getRole() != Tofunian.Roles.TOFUNIAN);
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		return Tofunian.Roles.getJobBlock(this.creature.getRole().getPoiType()).contains(blockstate);
	}


	protected boolean findNearestBlock() {
		if (this.creature.getTofunianJobBlock() != null &&
				isValidTarget(this.creature.level(), this.creature.getTofunianJobBlock())) {
			this.blockPos = this.creature.getTofunianJobBlock();
			return true;
		}
		return false;
	}

	@Override
	public void stop() {
		super.stop();
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}