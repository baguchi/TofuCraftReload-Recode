package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FindJobBlockGoal extends MoveToBlockGoal {
	private final Tofunian creature;
	private boolean findBlock;

	public FindJobBlockGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.level.isDay() && (this.creature.getRole() == Tofunian.Roles.TOFUNIAN || this.creature.getTofunainJobBlock() == null) && !this.creature.isBaby() && super.canUse());
	}

	public boolean canContinueToUse() {
		return !this.findBlock && (super.canContinueToUse() && this.creature.level.isDay() && !this.creature.isBaby() && (this.creature.getRole() == Tofunian.Roles.TOFUNIAN || this.creature.getTofunainJobBlock() == null) && this.mob != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.setTofunainJobBlock(this.blockPos);
			BlockState blockstate = this.creature.getLevel().getBlockState(this.blockPos);
			Block block = blockstate.getBlock();

			Tofunian.Roles role = Tofunian.Roles.get(block);
			if (role != null && !this.findBlock) {
				if (this.creature.getRole() == Tofunian.Roles.TOFUNIAN) {
					this.creature.setRole(role);
					this.creature.setOffers(null);
				}
				this.findBlock = true;


				this.creature.swing(InteractionHand.MAIN_HAND);
				this.creature.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 0.7F);
			}
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Block block = blockstate.getBlock();
		return (this.creature.getRole() == Tofunian.Roles.TOFUNIAN || this.creature.getTofunainJobBlock() == null) && Tofunian.Roles.getJobBlock(block) != null || this.creature.getRole() != Tofunian.Roles.TOFUNIAN && Tofunian.Roles.getJobMatch(this.creature.getRole(), block) != null;
	}

	@Override
	public void start() {
		super.start();
		this.findBlock = false;
	}

	@Override
	public void stop() {
		super.stop();
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}