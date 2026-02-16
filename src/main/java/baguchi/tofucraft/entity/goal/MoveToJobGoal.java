package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.Tofunian;
import baguchi.tofucraft.registry.TofunianProfessions;
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
		return (this.creature.level().isBrightOutside() && !this.creature.getRole().is(TofunianProfessions.NONE.getKey()) && this.creature.getTofunianJobBlock() != null && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.level().isBrightOutside() && this.creature.getTofunianJobBlock() != null && !this.creature.getRole().is(TofunianProfessions.NONE.getKey()));
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		return this.creature.getRole().value().isValidTarget(blockstate);
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