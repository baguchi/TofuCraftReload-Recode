package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.Tofunian;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class MoveToStatueGoal extends MoveToBlockGoal {
	private final Tofunian creature;


	public MoveToStatueGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.isMeeting() && this.creature.getVillageCenter() != null && !this.creature.level().isDay() && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (this.creature.level().isDay() && this.creature.isMeeting() && super.canContinueToUse());
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		return blockstate.is(TofuBlocks.TOFUNIAN_STATUE.get());
	}

	@Override
	public void tick() {
		BlockPos blockpos = this.getMoveToTarget();
		if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
			if (this.shouldRecalculatePath()) {
				this.mob.getNavigation().moveTo((double) blockpos.getX() + 0.5, (double) blockpos.getY(), (double) blockpos.getZ() + 0.5, this.speedModifier);
			}
			if (this.creature.getAction() != Tofunian.Actions.NORMAL) {
				this.creature.setAction(Tofunian.Actions.NORMAL);
			}
		} else {
			this.creature.getNavigation().stop();
			if (this.creature.getAction() != Tofunian.Actions.SIT) {
				this.creature.setAction(Tofunian.Actions.SIT);
			}
		}
	}


	protected boolean findNearestBlock() {
		if (this.creature.getVillageCenter() != null &&
				isValidTarget(this.creature.level(), this.creature.getVillageCenter())) {
			this.blockPos = this.creature.getVillageCenter();
			return true;
		}
		return false;
	}

	@Override
	public void stop() {
		super.stop();
		this.creature.setAction(Tofunian.Actions.NORMAL);
	}

	public double acceptedDistance() {
		return 4.5D;
	}
}