package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class SleepOnBedGoal extends MoveToBlockGoal {
	private final TofunianEntity creature;

	public SleepOnBedGoal(TofunianEntity creature, double speedIn, int length) {
		super((CreatureEntity) creature, speedIn, length);
		this.creature = creature;
	}

	public boolean canUse() {
		return (super.canUse() && !this.creature.level.isDay() && !this.creature.func_70608_bn());
	}

	public void tick() {
		super.tick();
		if (isReachedTarget()) {
			this.creature.func_213342_e(this.mob);
			this.creature.setTofunainHome(this.mob);
		}
	}

	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		Block block = blockstate.getBlock();
		return (blockstate.func_235714_a_((ITag) BlockTags.field_219747_F) && blockstate.func_177229_b((Property) BedBlock.field_176472_a) == BedPart.HEAD && !((Boolean) blockstate.func_177229_b((Property) BedBlock.field_176471_b)).booleanValue());
	}

	protected boolean func_179489_g() {
		if (this.creature.getTofunainHome() != null &&
				isValidTarget((IWorldReader) this.creature.level, this.creature.getTofunainHome())) {
			this.mob = this.creature.getTofunainHome();
			return true;
		}
		return super.func_179489_g();
	}
}
