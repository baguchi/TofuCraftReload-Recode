package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class TofunianLoveGoal extends Goal {
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();

	protected final TofunianEntity tofunian;

	private final Class<? extends TofunianEntity> partnerClass;

	protected final World level;

	protected TofunianEntity partner;

	private int loveTime;

	private final double speedModifier;

	public TofunianLoveGoal(TofunianEntity p_i1619_1_, double p_i1619_2_) {
		this(p_i1619_1_, p_i1619_2_, p_i1619_1_.getClass());
	}

	public TofunianLoveGoal(TofunianEntity p_i47306_1_, double p_i47306_2_, Class<? extends TofunianEntity> p_i47306_4_) {
		this.tofunian = p_i47306_1_;
		this.level = p_i47306_1_.level;
		this.partnerClass = p_i47306_4_;
		this.speedModifier = p_i47306_2_;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (!this.tofunian.func_213743_em())
			return false;
		this.partner = getFreePartner();
		return (this.partner != null);
	}

	public boolean canContinueToUse() {
		return (this.partner.isAlive() && this.loveTime < 160);
	}

	public void start() {
		this.partner = null;
		this.loveTime = 0;
	}

	public void tick() {
		this.tofunian.getLookControl().setLookAt(this.partner, 10.0F, this.tofunian.getMaxHeadXRot());
		this.tofunian.getNavigation().moveTo(this.partner, this.speedModifier);
		this.loveTime++;
		if (this.loveTime % 40 == 0)
			this.level.broadcastEntityEvent(this.tofunian, (byte) 12);
		if (this.loveTime >= 160 && this.tofunian.distanceToSqr(this.partner) < 9.0D)
			breed();
	}

	@Nullable
	private TofunianEntity getFreePartner() {
		List<TofunianEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.tofunian, this.tofunian.getBoundingBox().inflate(8.0D));
		double d0 = Double.MAX_VALUE;
		TofunianEntity tofunian2 = null;
		for (TofunianEntity tofunianEntity1 : list) {
			if (tofunianEntity1.func_213743_em() && this.tofunian.distanceToSqr(tofunianEntity1) < d0) {
				tofunian2 = tofunianEntity1;
				d0 = this.tofunian.distanceToSqr(tofunianEntity1);
			}
		}
		return tofunian2;
	}

	protected void breed() {
		if (this.level instanceof ServerWorld) {
			TofunianEntity tofunianEntity = (TofunianEntity) this.tofunian.getBreedOffspring((ServerWorld) this.level, this.partner);
			this.tofunian.setAge(6000);
			this.partner.setAge(6000);
			tofunianEntity.setAge(-24000);
			tofunianEntity.moveTo(this.tofunian.getX(), this.tofunian.getY(), this.tofunian.getZ(), 0.0F, 0.0F);
			((ServerWorld) this.level).addFreshEntityWithPassengers(tofunianEntity);
			this.level.broadcastEntityEvent(tofunianEntity, (byte) 12);
		}
	}
}
