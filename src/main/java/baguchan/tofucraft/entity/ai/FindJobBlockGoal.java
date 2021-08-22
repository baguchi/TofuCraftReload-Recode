package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FindJobBlockGoal extends MoveToBlockGoal {
	private final TofunianEntity creature;

	public FindJobBlockGoal(TofunianEntity creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.level.isDay() && (this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN || this.creature.getTofunainJobBlock() == null) && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.level.isDay() && !this.creature.isBaby() && (this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN || this.creature.getTofunainJobBlock() == null) && this.mob != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.setTofunainJobBlock(this.blockPos);
			BlockState blockstate = this.creature.getLevel().getBlockState(this.blockPos);
			Block block = blockstate.getBlock();

			TofunianEntity.Roles role = TofunianEntity.Roles.get(block);
			if (role != null) {
				if (this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN || this.creature.getTofunainLevel() == 1 && this.creature.getVillagerXp() == 0) {
					this.creature.setRole(role);
				}


				this.creature.swing(InteractionHand.MAIN_HAND);
				this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
			}
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Block block = blockstate.getBlock();
		return (this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN || this.creature.getTofunainLevel() == 1 && this.creature.getVillagerXp() == 0) && TofunianEntity.Roles.getJobBlock(block) != null || this.creature.getRole() != TofunianEntity.Roles.TOFUNIAN && TofunianEntity.Roles.getJobMatch(this.creature.getRole(), block) != null;
	}

	protected boolean findNearestBlock() {
		return super.findNearestBlock();
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}