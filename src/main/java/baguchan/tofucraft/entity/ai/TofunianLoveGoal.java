package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;

import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TofunianLoveGoal extends Goal {
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).func_221013_a(8.0D).func_221008_a().func_221011_b().func_221014_c();

	protected final TofunianEntity tofunian;

	private final Class<? extends TofunianEntity> partnerClass;

	protected final World level;

	protected TofunianEntity partner;

	private int loveTime;

	private final double speedModifier;

	public TofunianLoveGoal(TofunianEntity p_i1619_1_, double p_i1619_2_) {
		this(p_i1619_1_, p_i1619_2_, (Class) p_i1619_1_.getClass());
	}

	public TofunianLoveGoal(TofunianEntity p_i47306_1_, double p_i47306_2_, Class<? extends TofunianEntity> p_i47306_4_) {
		this.tofunian = p_i47306_1_;
		this.level = p_i47306_1_.level;
		this.partnerClass = p_i47306_4_;
		this.speedModifier = p_i47306_2_;
		func_220684_a(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (!this.tofunian.func_213743_em())
			return false;
		this.partner = getFreePartner();
		return (this.partner != null);
	}

	public boolean canContinueToUse() {
		return (this.partner.func_70089_S() && this.loveTime < 160);
	}

	public void start() {
		this.partner = null;
		this.loveTime = 0;
	}

	public void tick() {
		this.tofunian.getLookControl().func_75651_a((Entity) this.partner, 10.0F, this.tofunian.func_70646_bf());
		this.tofunian.func_70661_as().func_75497_a((Entity) this.partner, this.speedModifier);
		this.loveTime++;
		if (this.loveTime % 40 == 0)
			this.level.func_72960_a((Entity) this.tofunian, (byte) 12);
		if (this.loveTime >= 160 && this.tofunian.func_70068_e((Entity) this.partner) < 9.0D)
			breed();
	}

	@Nullable
	private TofunianEntity getFreePartner() {
		List<TofunianEntity> list = this.level.func_217374_a(this.partnerClass, PARTNER_TARGETING, (LivingEntity) this.tofunian, this.tofunian.func_174813_aQ().func_186662_g(8.0D));
		double d0 = Double.MAX_VALUE;
		TofunianEntity tofunian2 = null;
		for (TofunianEntity tofunianEntity1 : list) {
			if (tofunianEntity1.func_213743_em() && this.tofunian.func_70068_e((Entity) tofunianEntity1) < d0) {
				tofunian2 = tofunianEntity1;
				d0 = this.tofunian.func_70068_e((Entity) tofunianEntity1);
			}
		}
		return tofunian2;
	}

	protected void breed() {
		if (this.level instanceof ServerWorld) {
			TofunianEntity tofunianEntity = (TofunianEntity) this.tofunian.func_241840_a((ServerWorld) this.level, (AgeableEntity) this.partner);
			this.tofunian.func_70873_a(6000);
			this.partner.func_70873_a(6000);
			tofunianEntity.func_70873_a(-24000);
			tofunianEntity.func_70012_b(this.tofunian.func_226277_ct_(), this.tofunian.func_226278_cu_(), this.tofunian.func_226281_cx_(), 0.0F, 0.0F);
			((ServerWorld) this.level).func_242417_l((Entity) tofunianEntity);
			this.level.func_72960_a((Entity) tofunianEntity, (byte) 12);
		}
	}
}
