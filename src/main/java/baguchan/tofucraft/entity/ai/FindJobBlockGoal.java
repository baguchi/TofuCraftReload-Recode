package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.api.TofunianJobBlocks;
import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindJobBlockGoal extends MoveToBlockGoal {
	private final TofunianEntity creature;

	public FindJobBlockGoal(TofunianEntity creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.getLevel().isDay() && this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.getLevel().isDay() && !this.creature.isBaby() && this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN && this.mob != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.setTofunainJobBlock(this.blockPos);
			BlockState blockstate = this.creature.getLevel().getBlockState(this.blockPos);
			Block block = blockstate.getBlock();
			if (!TofunianJobBlocks.getJobBlockList().isEmpty() && TofunianJobBlocks.getJobBlockList().containsKey(block)) {
				this.creature.setRole(TofunianJobBlocks.getJobBlockList().get(block));
				this.creature.swing(Hand.MAIN_HAND);
				this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
			}
		}
	}

	public double func_203110_f() {
		return 2.0D;
	}

	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Block block = blockstate.getBlock();
		return (blockstate.is(Blocks.CRAFTING_TABLE) || blockstate.is(Blocks.FURNACE) || blockstate.is(Blocks.CAULDRON));
	}

	protected boolean findNearestBlock() {
		if (this.creature.getTofunainHome() != null &&
				isValidTarget(this.creature.getLevel(), this.creature.getTofunainHome())) {
			this.blockPos = this.creature.getTofunainHome();
			return true;
		}
		return super.findNearestBlock();
	}
}
