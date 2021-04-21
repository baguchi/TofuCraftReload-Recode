package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.api.TofunianJobBlocks;
import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindJobBlockGoal extends MoveToBlockGoal {
	private final TofunianEntity creature;

	public FindJobBlockGoal(TofunianEntity creature, double speedIn, int length) {
		super((CreatureEntity) creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (this.creature.func_130014_f_().isDay() && this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN && !this.creature.func_70631_g_() && super.canUse());
	}

	public boolean canContinueToUse() {
		return (super.canContinueToUse() && this.creature.func_130014_f_().isDay() && !this.creature.func_70631_g_() && this.creature.getRole() == TofunianEntity.Roles.TOFUNIAN && this.mob != null);
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.setTofunainJobBlock(this.mob);
			BlockState blockstate = this.creature.func_130014_f_().getBlockState(this.mob);
			Block block = blockstate.getBlock();
			if (!TofunianJobBlocks.getJobBlockList().isEmpty() && TofunianJobBlocks.getJobBlockList().containsKey(block)) {
				this.creature.setRole((TofunianEntity.Roles) TofunianJobBlocks.getJobBlockList().get(block));
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
		return (blockstate.func_203425_a(Blocks.field_150462_ai) || blockstate.func_203425_a(Blocks.field_150460_al) || blockstate.func_203425_a(Blocks.field_150383_bp));
	}

	protected boolean func_179489_g() {
		if (this.creature.getTofunainHome() != null &&
				isValidTarget((IWorldReader) this.creature.func_130014_f_(), this.creature.getTofunainHome())) {
			this.mob = this.creature.getTofunainHome();
			return true;
		}
		return super.func_179489_g();
	}
}
