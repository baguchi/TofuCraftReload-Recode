package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class EatItemGoal<T extends Tofunian> extends Goal {
	private final T mob;
	private final Predicate<? super T> canUseSelector;

	public EatItemGoal(T p_25972_, @Nullable SoundEvent p_25974_, Predicate<? super T> p_25975_) {
		this.mob = p_25972_;
		this.canUseSelector = p_25975_;
	}

	public boolean canUse() {
		if (this.mob.getAction() == Tofunian.Actions.HAPPY) {
			return false;
		}
		if (!this.mob.getMainHandItem().isEmpty() && this.mob.getMainHandItem().getFoodProperties(this.mob) != null) {
			return true;
		}
		if (this.canUseSelector.test(this.mob)) {
			return this.mob.eatFood();
		}

		return false;
	}

	public boolean canContinueToUse() {
		return this.mob.isUsingItem();
	}

	public void start() {
		this.mob.setAction(Tofunian.Actions.EAT);
		this.mob.startUsingItem(InteractionHand.MAIN_HAND);
	}

	public void stop() {
		this.mob.setAction(Tofunian.Actions.NORMAL);
		this.mob.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
	}
}