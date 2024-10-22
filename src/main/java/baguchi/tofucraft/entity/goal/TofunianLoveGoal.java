package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.Tofunian;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class TofunianLoveGoal extends Goal {
	private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreInvisibilityTesting();

	protected final Tofunian tofunian;

	private final Class<? extends Tofunian> partnerClass;

	protected final Level level;

	protected Tofunian partner;

	private int loveTime;

	private final double speedModifier;

	public TofunianLoveGoal(Tofunian p_i1619_1_, double p_i1619_2_) {
		this(p_i1619_1_, p_i1619_2_, p_i1619_1_.getClass());
	}

	public TofunianLoveGoal(Tofunian p_i47306_1_, double p_i47306_2_, Class<? extends Tofunian> p_i47306_4_) {
		this.tofunian = p_i47306_1_;
		this.level = p_i47306_1_.level();
		this.partnerClass = p_i47306_4_;
		this.speedModifier = p_i47306_2_;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (!this.tofunian.canMate())
			return false;
		this.partner = getFreePartner();
		return (this.partner != null);
	}

	public boolean canContinueToUse() {
		return (this.partner.isAlive() && this.loveTime < 160);
	}

	public void start() {
		this.loveTime = 0;
	}

	public void tick() {
		this.tofunian.eatAndDigestFood();
		this.tofunian.getLookControl().setLookAt(this.partner, 10.0F, this.tofunian.getMaxHeadXRot());
		this.tofunian.getNavigation().moveTo(this.partner, this.speedModifier);
		this.loveTime++;
		if (this.loveTime % 40 == 0)
			this.level.broadcastEntityEvent(this.tofunian, (byte) 12);
		if (this.loveTime >= 160 && this.tofunian.distanceToSqr(this.partner) < 9.0D)
			breed();
	}

	@Nullable
	private Tofunian getFreePartner() {
		List<Tofunian> list = this.tofunian.level().getEntitiesOfClass(Tofunian.class, this.tofunian.getBoundingBox().inflate(8.0D), tofunian1 -> {
			return tofunian1 != tofunian;
		});
		double d0 = Double.MAX_VALUE;
		Tofunian tofunian2 = null;
		for (Tofunian tofunianEntity1 : list) {
			if (tofunianEntity1.canMate() && this.tofunian.distanceToSqr(tofunianEntity1) < d0) {
				tofunian2 = tofunianEntity1;
				d0 = this.tofunian.distanceToSqr(tofunianEntity1);
			}
		}
		return tofunian2;
	}

	protected void breed() {
		if (this.level instanceof ServerLevel) {
			Tofunian tofunianEntity = (Tofunian) this.tofunian.getBreedOffspring((ServerLevel) this.level, this.partner);
			this.tofunian.setAge(6000);
			this.partner.setAge(6000);
			tofunianEntity.setAge(-24000);
			tofunianEntity.moveTo(this.tofunian.getX(), this.tofunian.getY(), this.tofunian.getZ(), 0.0F, 0.0F);
			((ServerLevel) this.level).addFreshEntityWithPassengers(tofunianEntity);
			this.level.broadcastEntityEvent(tofunianEntity, (byte) 12);
		}
	}
}