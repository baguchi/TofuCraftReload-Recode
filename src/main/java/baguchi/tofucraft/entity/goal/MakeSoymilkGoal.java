package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.TofuCow;
import baguchi.tofucraft.entity.Tofunian;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;

public class MakeSoymilkGoal extends Goal {
	private final Tofunian creature;
	private final double speedIn;

	private int cookTick;
	private int cooldown = -100;
	private boolean stop;
	private TofuCow cow;

	public MakeSoymilkGoal(Tofunian creature, double speedIn) {
		this.creature = creature;
		this.speedIn = speedIn;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (cooldown == -100) {
			this.cooldown = 400 + this.creature.getRandom().nextInt(400);
		}

		if ((this.creature.getRole() == Tofunian.Roles.SOYWORKER && !this.creature.hasExcessFood() && this.creature.level().isBrightOutside())) {
			if (cooldown-- <= 0) {
				List<TofuCow> list = this.creature.level()
						.getEntities(
								EntityTypeTest.forClass(TofuCow.class),
								new AABB(
										this.creature.getX() - 16, this.creature.getY() - 16, this.creature.getZ() - 16, this.creature.getX() + 16, this.creature.getY() + 16, this.creature.getZ() + 16
								),
								p_147140_ -> p_147140_.isAlive()
						);

				this.cooldown = 400 + this.creature.getRandom().nextInt(400);
				if (!list.isEmpty()) {
					cow = list.get(this.creature.getRandom().nextInt(list.size()));
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	public boolean canContinueToUse() {
		return (this.creature.getRole() == Tofunian.Roles.SOYWORKER && !this.creature.hasExcessFood() && this.creature.level().isBrightOutside() && this.cow != null) && this.stop;
	}

	public void start() {
		super.start();
		this.cookTick = 0;
	}

	public void tick() {
		super.tick();
		if (cow != null && cow.isAlive()) {
			this.creature.getLookControl().setLookAt(this.cow, 30, 30);
			if (this.cookTick > 0)
				this.cookTick--;
			if (this.creature.distanceTo(cow) < 1.5F) {
				if (this.cookTick <= 0) {
					this.creature.getInventory().addItem(new ItemStack(TofuItems.SOYMILK.get()));
					this.creature.swing(InteractionHand.MAIN_HAND);
					this.creature.playSound(SoundEvents.COW_MILK, 1.0F, 1F);
					this.cookTick = 60;
					this.stop = true;
				}
			} else {
				this.creature.getNavigation().moveTo(this.cow, this.speedIn);
			}
		}
	}

	@Override
	public void stop() {
		super.stop();
		this.stop = false;
	}
}