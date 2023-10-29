package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.Tofunian;
import net.minecraft.server.level.ServerLevel;
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

public class ShareItemAndGossipGoal extends Goal {
	private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreInvisibilityTesting();

	protected final Tofunian tofunian;

	protected final double speedModifier;

	protected int nextStartTick;

	protected Tofunian partner;

	private boolean needPassed;

	private int tryingTalkingTick;

	public ShareItemAndGossipGoal(Tofunian entity, double speed) {
		this.tofunian = entity;
		this.speedModifier = speed;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (this.nextStartTick > 0) {
			this.nextStartTick--;
			return false;
		}
		if (this.tofunian.level().isDay() && !this.tofunian.isBaby()) {
			this.nextStartTick = this.tofunian.getRandom().nextInt(200) + 200;
			this.partner = getFreePartner();
			return (this.partner != null);
		}
		return false;
	}

	public boolean canContinueToUse() {
		return (this.tryingTalkingTick < 300 && this.partner != null && this.partner.isAlive() && this.tofunian.distanceToSqr(this.partner) < 64);
	}

	public void start() {
		if (this.partner.wantsMoreFood() && this.tofunian.hasExcessFood()) {
			this.needPassed = true;
		}
	}

	@Override
	public void stop() {
		super.stop();
		this.tryingTalkingTick = 0;
	}

	@Nullable
	private Tofunian getFreePartner() {
		List<Tofunian> list = this.tofunian.level().getNearbyEntities(Tofunian.class, PARTNER_TARGETING, this.tofunian, this.tofunian.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		Tofunian tofunian2 = null;
		for (Tofunian tofunianEntity1 : list) {
			if (this.tofunian.distanceToSqr(tofunianEntity1) < d0) {
				tofunian2 = tofunianEntity1;
				d0 = this.tofunian.distanceToSqr(tofunianEntity1);
			}
		}
		return tofunian2;
	}

	public void tick() {
		super.tick();
		++this.tryingTalkingTick;
		if (this.tofunian.hasLineOfSight(this.partner)) {

			if (this.tofunian.distanceToSqr(this.partner) < 6.0F) {
				this.tofunian.getLookControl().setLookAt(this.partner, 30.0F, 30.0F);
				this.tofunian.getNavigation().stop();
				if (this.needPassed) {

					throwHalfStack(this.tofunian, Tofunian.FOOD_POINTS.keySet(), this.partner);
					this.needPassed = false;
					this.tofunian.level().broadcastEntityEvent(this.tofunian, (byte) 5);
					this.partner.setAction(Tofunian.Actions.HAPPY);

				}
				if (this.tofunian.level() instanceof ServerLevel) {
					this.tofunian.gossip((ServerLevel) this.tofunian.level(), this.partner, this.tofunian.level().getGameTime());
				}
			} else {
				this.tofunian.getNavigation().moveTo(this.partner, 1.0D);
				this.tofunian.getLookControl().setLookAt(this.partner, 30.0F, 30.0F);
			}
		} else {
			this.tofunian.getNavigation().moveTo(this.partner, 1.0D);
		}
	}

	private static void throwHalfStack(Tofunian p_220586_0_, Set<Item> p_220586_1_, LivingEntity p_220586_2_) {
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