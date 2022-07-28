package baguchan.tofucraft.entity.goal;

import baguchan.tofucraft.entity.TofuGandlem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SpinAttackGoal extends Goal {

	private final TofuGandlem gandlem;
	private int attackTime;

	private int cooldown;
	private int maxCooldown;
	private final UniformInt timeBetweenCooldown;

	public SpinAttackGoal(TofuGandlem p_32247_, UniformInt rushCooldown) {
		this.gandlem = p_32247_;
		this.timeBetweenCooldown = rushCooldown;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.maxCooldown <= 0) {
			this.maxCooldown = timeBetweenCooldown.sample(this.gandlem.getRandom());
			return false;
		} else {
			if (cooldown > this.maxCooldown) {
				this.cooldown = 0;
				this.maxCooldown = timeBetweenCooldown.sample(this.gandlem.getRandom());
				LivingEntity livingentity = this.gandlem.getTarget();
				return livingentity != null && livingentity.isAlive() && this.gandlem.canAttack(livingentity) && this.gandlem.hasLineOfSight(livingentity);
			} else {
				++this.cooldown;
				return false;
			}
		}

	}

	@Override
	public boolean canContinueToUse() {
		return this.attackTime < 74;
	}

	@Override
	public void start() {
		super.start();
		this.attackTime = 0;
		this.gandlem.level.broadcastEntityEvent(this.gandlem, (byte) 6);
	}

	@Override
	public void tick() {
		super.tick();
		++this.attackTime;

		if (this.attackTime <= 10) {

			LivingEntity livingentity = this.gandlem.getTarget();
			if (livingentity != null) {
				this.gandlem.lookAt(livingentity, 90.0F, 90.0F);
			}
		}

		if (this.attackTime == 24) {
			this.gandlem.setRush(true);

			this.gandlem.playSound(SoundEvents.TRIDENT_RIPTIDE_3, 3.0F, 1.0F);
		}

		if (this.attackTime < 40 && this.attackTime > 24) {
			float f7 = this.gandlem.getYRot();
			float f = this.gandlem.getXRot();
			float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
			float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
			float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
			float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
			float f5 = 0.8F * ((2.75F) / 4.0F);
			f1 *= f5 / f4;
			f2 *= f5 / f4;
			f3 *= f5 / f4;
			this.gandlem.push((double) f1, (double) f2, (double) f3);
		}

		if (this.attackTime == 40) {
			this.gandlem.setRush(false);
		}
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}
}
