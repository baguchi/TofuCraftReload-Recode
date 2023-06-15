package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class RestockGoal extends MoveToBlockGoal {
	private final Tofunian creature;

	private boolean restockComplete;

	public RestockGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.level().isDay() && this.creature.getRole() != Tofunian.Roles.TOFUNIAN && this.creature.canResetStock() && this.creature.level().dayTime() > 2000 && this.creature.level().dayTime() < 9000 && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.level().isDay() && this.creature.canResetStock() && !this.creature.isBaby() && this.creature.getRole() != Tofunian.Roles.TOFUNIAN);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget() && !restockComplete) {
			this.creature.restock();
			this.creature.swing(InteractionHand.MAIN_HAND);
			this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
			restockComplete = true;
		}
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
		restockComplete = false;
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}