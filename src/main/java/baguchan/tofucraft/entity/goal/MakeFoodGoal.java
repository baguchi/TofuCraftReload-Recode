package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class MakeFoodGoal extends MoveToBlockGoal {
	private final Tofunian creature;

	private int cookTick;

	public MakeFoodGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.getRole() == Tofunian.Roles.TOFUCOOK && !this.creature.hasExcessFood() && this.creature.hasFarmSeeds() && this.creature.level().isDay() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.getRole() == Tofunian.Roles.TOFUCOOK && !this.creature.hasExcessFood() && this.creature.hasFarmSeeds() && this.creature.level().isDay() && this.mob != null);
	}

	public void start() {
		super.start();
		this.cookTick = 0;
	}

	public void tick() {
		super.tick();
		this.creature.getLookControl().setLookAt(this.mob.getX(), this.mob.getY(), this.mob.getZ());
		if (this.cookTick > 0)
			this.cookTick--;
		if (isReachedTarget() && this.cookTick <= 0) {
			this.creature.cookingFood();
			this.creature.swing(InteractionHand.MAIN_HAND);
			this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
			this.cookTick = 20;
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

	public double acceptedDistance() {
		return 2.0D;
	}
}