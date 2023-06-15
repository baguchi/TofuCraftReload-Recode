package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
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
		long i = this.creature.level().getDayTime() / 24000L;
		return (this.creature.level().isDay() && this.creature.getRole() != Tofunian.Roles.TOFUNIAN && this.creature.getTofunainJobBlock() != null && i > 2000L && i < 9000L && !this.creature.isBaby() && super.canUse());
	}

	protected int nextStartTick(PathfinderMob p_25618_) {
		return reducedTickDelay(20 + p_25618_.getRandom().nextInt(40));
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.level().isDay() && this.creature.getTofunainJobBlock() != null && this.creature.getRole() != Tofunian.Roles.TOFUNIAN);
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		return Tofunian.Roles.getJobBlock(this.creature.getRole().getPoiType()).contains(blockstate);
	}


	protected boolean findNearestBlock() {
		if (this.creature.getTofunainJobBlock() != null &&
				isValidTarget(this.creature.level(), this.creature.getTofunainJobBlock())) {
			this.blockPos = this.creature.getTofunainJobBlock();
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