package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ShareItemGoal extends Goal {
	private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreInvisibilityTesting();

	protected final TofunianEntity tofunian;

	protected final double speedModifier;

	protected int nextStartTick;

	protected TofunianEntity partner;

	private boolean hasPassed;

	public ShareItemGoal(TofunianEntity entity, double speed) {
		this.tofunian = entity;
		this.speedModifier = speed;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (this.nextStartTick > 0) {
			this.nextStartTick--;
			return false;
		}
		if (this.tofunian.level.isDay() && !this.tofunian.isBaby() && this.tofunian.hasExcessFood()) {
			this.nextStartTick = this.tofunian.getRandom().nextInt(60) + 200;
			this.partner = getFreePartner();
			return (this.partner != null);
		}
		return false;
	}

	public boolean canContinueToUse() {
		return (!this.hasPassed && this.partner != null && this.partner.isAlive());
	}

	public void start() {
		this.hasPassed = false;
	}

	@Nullable
	private TofunianEntity getFreePartner() {
		List<TofunianEntity> list = this.tofunian.level.getNearbyEntities(TofunianEntity.class, PARTNER_TARGETING, this.tofunian, this.tofunian.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		TofunianEntity tofunian2 = null;
		for (TofunianEntity tofunianEntity1 : list) {
			if (tofunianEntity1.wantsMoreFood() && this.tofunian.distanceToSqr(tofunianEntity1) < d0) {
				tofunian2 = tofunianEntity1;
				d0 = this.tofunian.distanceToSqr(tofunianEntity1);
			}
		}
		return tofunian2;
	}

	public void tick() {
		super.tick();
		if (this.tofunian.hasLineOfSight(this.partner)) {
			if (!this.hasPassed) {
				this.tofunian.getNavigation().moveTo(this.partner, 2.0D);
				this.tofunian.getLookControl().setLookAt(this.partner, 30.0F, 30.0F);
				throwHalfStack(this.tofunian, TofunianEntity.FOOD_POINTS.keySet(), this.partner);
				this.hasPassed = true;
			}
		} else {
			this.hasPassed = true;
		}
	}

	private static void throwHalfStack(TofunianEntity p_220586_0_, Set<Item> p_220586_1_, LivingEntity p_220586_2_) {
		SimpleContainer inventory = p_220586_0_.getInventory();
		ItemStack itemstack = ItemStack.EMPTY;
		int i = 0;

		while (i < inventory.getContainerSize()) {
			ItemStack itemstack1;
			Item item;
			int j;
			label28:
			{
				itemstack1 = inventory.getItem(i);
				if (!itemstack1.isEmpty()) {
					item = itemstack1.getItem();
					if (p_220586_1_.contains(item)) {
						if (itemstack1.getCount() > itemstack1.getMaxStackSize() / 2) {
							j = itemstack1.getCount() / 2;
							break label28;
						}

						if (itemstack1.getCount() > 24) {
							j = itemstack1.getCount() - 24;
							break label28;
						}
					}
				}

				++i;
				continue;
			}

			itemstack1.shrink(j);
			itemstack = new ItemStack(item, j);
			break;
		}

		if (!itemstack.isEmpty()) {
			BehaviorUtils.throwItem(p_220586_0_, itemstack, p_220586_2_.position());
		}

	}

}