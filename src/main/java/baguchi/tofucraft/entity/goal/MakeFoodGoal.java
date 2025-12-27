package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.tofunian.Tofunian;
import baguchi.tofucraft.registry.TofunianProfessions;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class MakeFoodGoal extends MoveToBlockGoal {
	private final Tofunian creature;

	private int cookTick;

	public MakeFoodGoal(Tofunian creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.creature = creature;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		return (this.creature.getRole().is(TofunianProfessions.FARMER.getKey()) && !this.creature.hasExcessFood() && this.creature.hasFarmSeeds() && this.creature.level().isBrightOutside() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.getRole().is(TofunianProfessions.FARMER.getKey()) && !this.creature.hasExcessFood() && this.creature.hasFarmSeeds() && this.creature.level().isBrightOutside() && this.mob != null);
	}

	public void start() {
		super.start();
		this.cookTick = 0;
	}

	public void tick() {
		super.tick();
		this.creature.getLookControl().setLookAt(this.blockPos.getX(), this.blockPos.getY(), this.blockPos.getZ(), 30.0F, 30.0F);
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
		return this.creature.getRole().value().jobSite().isPresent() && this.creature.getRole().value().jobSite().get().contains(blockstate);
	}

	protected boolean findNearestBlock() {
		if (this.creature.getTofunianJobBlock() != null &&
				isValidTarget(this.creature.level(), this.creature.getTofunianJobBlock())) {
			this.blockPos = this.creature.getTofunianJobBlock();
			return true;
		}
		return false;
	}

	public double acceptedDistance() {
		return 2.0D;
	}
}