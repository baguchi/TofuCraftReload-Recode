package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuTrades;

import java.util.EnumSet;
import javax.annotation.Nullable;

import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtCustomerGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookAtWithoutMovingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TravelerTofunianEntity extends AbstractTofunianEntity {
	@Nullable
	private BlockPos wanderTarget;

	private int despawnDelay;

	public TravelerTofunianEntity(EntityType<? extends TravelerTofunianEntity> type, World worldIn) {
		super((EntityType) type, worldIn);
		func_98053_h(false);
		this.field_98038_p = true;
	}

	protected void func_184651_r() {
		super.func_184651_r();
		this.field_70714_bg.func_75776_a(0, (Goal) new SwimGoal((MobEntity) this));
		this.field_70714_bg.func_75776_a(1, (Goal) new TradeWithPlayerGoal(this));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, ZombieEntity.class, 8.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, AbstractIllagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, RavagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new AvoidEntityGoal((CreatureEntity) this, ZoglinEntity.class, 10.0F, 1.2D, 1.2D));
		this.field_70714_bg.func_75776_a(1, (Goal) new LookAtCustomerGoal(this));
		this.field_70714_bg.func_75776_a(2, new MoveToGoal(this, 10.0D, 1.149999976158142D));
		this.field_70714_bg.func_75776_a(8, (Goal) new WaterAvoidingRandomWalkingGoal((CreatureEntity) this, 1.0D));
		this.field_70714_bg.func_75776_a(9, (Goal) new LookAtWithoutMovingGoal((MobEntity) this, PlayerEntity.class, 3.0F, 1.0F));
		this.field_70714_bg.func_75776_a(10, (Goal) new LookAtGoal((MobEntity) this, MobEntity.class, 8.0F));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().func_233815_a_(Attributes.field_233821_d_, 0.23999999463558197D).func_233815_a_(Attributes.field_233819_b_, 20.0D);
	}

	public void func_213281_b(CompoundNBT compound) {
		super.func_213281_b(compound);
		compound.func_74768_a("DespawnDelay", this.despawnDelay);
		if (this.wanderTarget != null)
			compound.func_218657_a("WanderTarget", (INBT) NBTUtil.func_186859_a(this.wanderTarget));
	}

	public void func_70037_a(CompoundNBT compound) {
		super.func_70037_a(compound);
		if (compound.func_150297_b("DespawnDelay", 99))
			this.despawnDelay = compound.func_74762_e("DespawnDelay");
		if (compound.func_74764_b("WanderTarget"))
			this.wanderTarget = NBTUtil.func_186861_c(compound.func_74775_l("WanderTarget"));
		func_70873_a(Math.max(0, func_70874_b()));
	}

	@Nullable
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}

	public boolean func_213705_dZ() {
		return false;
	}

	public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.func_184586_b(p_230254_2_);
		if (itemstack.func_77973_b() != Items.field_196172_da && func_70089_S() && !func_213716_dX() && !func_70631_g_()) {
			if (p_230254_2_ == Hand.MAIN_HAND)
				p_230254_1_.func_195066_a(Stats.field_188074_H);
			if (func_213706_dY().isEmpty())
				return ActionResultType.func_233537_a_(this.level.field_72995_K);
			if (!this.level.field_72995_K) {
				func_70932_a_(p_230254_1_);
				func_213707_a(p_230254_1_, func_145748_c_(), 1);
			}
			return ActionResultType.func_233537_a_(this.level.field_72995_K);
		}
		return super.func_230254_b_(p_230254_1_, p_230254_2_);
	}

	protected void func_213712_ef() {
		VillagerTrades.ITrade[] avillagertrades$itrade = (VillagerTrades.ITrade[]) TofuTrades.TRAVELER_TOFUNIAN_TRADE.get(1);
		VillagerTrades.ITrade[] avillagertrades$itrade1 = (VillagerTrades.ITrade[]) TofuTrades.TRAVELER_TOFUNIAN_TRADE.get(2);
		if (avillagertrades$itrade != null && avillagertrades$itrade1 != null) {
			MerchantOffers merchantoffers = func_213706_dY();
			func_213717_a(merchantoffers, avillagertrades$itrade, 3);
			func_213717_a(merchantoffers, avillagertrades$itrade1, 3);
		}
	}

	public boolean func_213397_c(double p_213397_1_) {
		return false;
	}

	protected void func_213713_b(MerchantOffer p_213713_1_) {
		if (p_213713_1_.func_222221_q()) {
			int i = 3 + this.field_70146_Z.nextInt(4);
			this.level.func_217376_c((Entity) new ExperienceOrbEntity(this.level, func_226277_ct_(), func_226278_cu_() + 0.5D, func_226281_cx_(), i));
		}
	}

	public void setDespawnDelay(int p_213728_1_) {
		this.despawnDelay = p_213728_1_;
	}

	public int getDespawnDelay() {
		return this.despawnDelay;
	}

	public void func_70636_d() {
		super.func_70636_d();
		if (!this.level.field_72995_K)
			maybeDespawn();
	}

	private void maybeDespawn() {
		if (this.despawnDelay > 0 && !func_213716_dX() && --this.despawnDelay == 0)
			func_70106_y();
	}

	public void setWanderTarget(@Nullable BlockPos p_213726_1_) {
		this.wanderTarget = p_213726_1_;
	}

	@Nullable
	private BlockPos getWanderTarget() {
		return this.wanderTarget;
	}

	class MoveToGoal extends Goal {
		final TravelerTofunianEntity trader;

		final double stopDistance;

		final double speedModifier;

		MoveToGoal(TravelerTofunianEntity p_i50459_2_, double p_i50459_3_, double p_i50459_5_) {
			this.trader = p_i50459_2_;
			this.stopDistance = p_i50459_3_;
			this.speedModifier = p_i50459_5_;
			func_220684_a(EnumSet.of(Flag.MOVE));
		}

		public void start() {
			this.trader.setWanderTarget((BlockPos) null);
			TravelerTofunianEntity.this.field_70699_by.func_75499_g();
		}

		public boolean canUse() {
			BlockPos blockpos = this.trader.getWanderTarget();
			return (blockpos != null && isTooFarAway(blockpos, this.stopDistance));
		}

		public void tick() {
			BlockPos blockpos = this.trader.getWanderTarget();
			if (blockpos != null && TravelerTofunianEntity.this.field_70699_by.func_75500_f())
				if (isTooFarAway(blockpos, 10.0D)) {
					Vector3d vector3d = (new Vector3d(blockpos.getX() - this.trader.func_226277_ct_(), blockpos.getY() - this.trader.func_226278_cu_(), blockpos.getZ() - this.trader.func_226281_cx_())).func_72432_b();
					Vector3d vector3d1 = vector3d.func_186678_a(10.0D).func_72441_c(this.trader.func_226277_ct_(), this.trader.func_226278_cu_(), this.trader.func_226281_cx_());
					TravelerTofunianEntity.this.field_70699_by.func_75492_a(vector3d1.field_72450_a, vector3d1.field_72448_b, vector3d1.field_72449_c, this.speedModifier);
				} else {
					TravelerTofunianEntity.this.field_70699_by.func_75492_a(blockpos.getX(), blockpos.getY(), blockpos.getZ(), this.speedModifier);
				}
		}

		private boolean isTooFarAway(BlockPos p_220846_1_, double p_220846_2_) {
			return !p_220846_1_.func_218137_a((IPosition) this.trader.func_213303_ch(), p_220846_2_);
		}
	}
}
