package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RestockGoal extends MoveToBlockGoal {
	private final Tofunian creature;

	public RestockGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.level.isDay() && this.creature.getRole() != Tofunian.Roles.TOFUNIAN && this.creature.canResetStock() && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.level.isDay() && this.creature.canResetStock() && !this.creature.isBaby() && this.creature.getRole() != Tofunian.Roles.TOFUNIAN && this.mob != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.restock();
			this.creature.swing(InteractionHand.MAIN_HAND);
			this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Block block = blockstate.getBlock();
		return this.creature.getRole().getBlock() == block;
	}


	protected boolean findNearestBlock() {
		if (this.creature.getTofunainJobBlock() != null &&
				isValidTarget(this.creature.getLevel(), this.creature.getTofunainJobBlock())) {
			this.blockPos = this.creature.getTofunainJobBlock();
			return true;
		}
		return super.findNearestBlock();
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}