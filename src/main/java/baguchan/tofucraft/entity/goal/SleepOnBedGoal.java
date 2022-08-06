package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

public class SleepOnBedGoal extends MoveToBlockGoal {
	private final Tofunian creature;

	public SleepOnBedGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return this.creature.getTarget() == null && this.creature.level.isNight() && !this.creature.isSleeping() && super.canUse();
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.startSleeping(this.blockPos);
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		return blockstate.hasProperty(BedBlock.PART) && blockstate.is(BlockTags.BEDS) && blockstate.getValue(BedBlock.PART) == BedPart.HEAD;

	}

	protected boolean findNearestBlock() {
		if (this.creature.getTofunainHome() != null) {
			this.blockPos = this.creature.getTofunainJobBlock();
			return true;
		}

		return super.findNearestBlock();
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}