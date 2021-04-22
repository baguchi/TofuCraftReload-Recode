package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.api.TofunianJobBlocks;
import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class RestockGoal extends MoveToBlockGoal {
	private final TofunianEntity creature;

	public RestockGoal(TofunianEntity creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.getRole() != TofunianEntity.Roles.TOFUNIAN && this.creature.canResetStock() && this.creature.getLevel().isDay() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.getRole() != TofunianEntity.Roles.TOFUNIAN && this.creature.canResetStock() && this.creature.getLevel().isDay() && this.blockPos != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.setTofunainJobBlock(this.blockPos);
			this.creature.restock();
			this.creature.swing(Hand.MAIN_HAND);
			this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
		}
	}

	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Block block = blockstate.getBlock();
		return TofunianJobBlocks.getJobBlockList().entrySet().stream().findFirst().filter(entry ->
				(entry.getValue() == this.creature.getRole() && entry.getKey() == block))
				.isPresent();
	}

	protected boolean findNearestBlock() {
		if (this.creature.getTofunainJobBlock() != null &&
				isValidTarget(this.creature.getLevel(), this.creature.getTofunainJobBlock())) {
			this.blockPos = this.creature.getTofunainJobBlock();
			return true;
		}
		return super.findNearestBlock();
	}
}
