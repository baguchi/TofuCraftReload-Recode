package baguchan.tofucraft.entity.ai;

import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

public class WakeUpGoal extends Goal {
	private final CreatureEntity creature;

	public WakeUpGoal(CreatureEntity creature) {
		this.creature = creature;
	}

	public boolean canUse() {
		return ((this.creature.level.isDay() && this.creature.func_70608_bn()) || (this.creature.func_213374_dv().isPresent() && this.creature.func_226278_cu_() <= ((BlockPos) this.creature.func_213374_dv().get()).getY() + 0.4D && !((BlockPos) this.creature.func_213374_dv().get()).func_218137_a((IPosition) this.creature.func_213303_ch(), 1.14D) && this.creature.func_70608_bn()));
	}

	public void func_75249_e() {
		super.func_75249_e();
		this.creature.func_213366_dy();
	}
}
