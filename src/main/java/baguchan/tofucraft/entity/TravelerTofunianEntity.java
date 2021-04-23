package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTrades;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class TravelerTofunianEntity extends AbstractTofunianEntity {
	@Nullable
	private BlockPos wanderTarget;

	private int despawnDelay;

	public TravelerTofunianEntity(EntityType<? extends TravelerTofunianEntity> type, World worldIn) {
		super(type, worldIn);
		this.forcedLoading = true;
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, ZombieEntity.class, 8.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, AbstractIllagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, RavagerEntity.class, 12.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal(this, ZoglinEntity.class, 10.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
		this.goalSelector.addGoal(2, new MoveToGoal(this, 10.0D, 1.149999976158142D));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.23999999463558197D).add(Attributes.MAX_HEALTH, 20.0D);
	}

	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("DespawnDelay", this.despawnDelay);
		if (this.wanderTarget != null)
			compound.put("WanderTarget", NBTUtil.writeBlockPos(this.wanderTarget));
	}

	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DespawnDelay", 99))
			this.despawnDelay = compound.getInt("DespawnDelay");
		if (compound.contains("WanderTarget"))
			this.wanderTarget = NBTUtil.readBlockPos(compound.getCompound("WanderTarget"));
		setAge(Math.max(0, getAge()));
	}

	public boolean showProgressBar() {
		return false;
	}

	public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
		if (itemstack.getItem() != TofuItems.TRAVELER_TOFUNIAN_SPAWNEGG && this.isAlive() && !this.isTrading() && !this.isBaby()) {

			if (this.getOffers().isEmpty()) {
				return ActionResultType.sidedSuccess(this.level.isClientSide);
			} else {
				if (!this.level.isClientSide) {
					this.setTradingPlayer(p_230254_1_);
					this.openTradingScreen(p_230254_1_, this.getDisplayName(), 1);
				}

				return ActionResultType.sidedSuccess(this.level.isClientSide);
			}
		} else {
			return super.mobInteract(p_230254_1_, p_230254_2_);
		}
	}

	protected void updateTrades() {
		VillagerTrades.ITrade[] avillagertrades$itrade = TofuTrades.TRAVELER_TOFUNIAN_TRADE.get(1);
		VillagerTrades.ITrade[] avillagertrades$itrade1 = TofuTrades.TRAVELER_TOFUNIAN_TRADE.get(2);
		if (avillagertrades$itrade != null && avillagertrades$itrade1 != null) {
			MerchantOffers merchantoffers = this.getOffers();
			addOffersFromItemListings(merchantoffers, avillagertrades$itrade, 3);
			addOffersFromItemListings(merchantoffers, avillagertrades$itrade1, 3);
		}
	}

	public boolean removeWhenFarAway(double p_213397_1_) {
		return false;
	}

	protected void rewardTradeXp(MerchantOffer p_213713_1_) {
		if (p_213713_1_.shouldRewardExp()) {
			int i = 3 + this.random.nextInt(4);
			this.level.addFreshEntity(new ExperienceOrbEntity(this.level, getX(), getY() + 0.5D, getZ(), i));
		}
	}

	public void setDespawnDelay(int p_213728_1_) {
		this.despawnDelay = p_213728_1_;
	}

	public int getDespawnDelay() {
		return this.despawnDelay;
	}

	public void aiStep() {
		super.aiStep();
		if (!this.level.isClientSide)
			maybeDespawn();
	}

	private void maybeDespawn() {
		if (this.despawnDelay > 0 && !this.isTrading() && --this.despawnDelay == 0) {
			this.remove();
		}
	}

	public void setWanderTarget(@Nullable BlockPos p_213726_1_) {
		this.wanderTarget = p_213726_1_;
	}

	@Nullable
	private BlockPos getWanderTarget() {
		return this.wanderTarget;
	}

	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}

	class MoveToGoal extends Goal {
		final TravelerTofunianEntity trader;
		final double stopDistance;
		final double speedModifier;

		MoveToGoal(TravelerTofunianEntity p_i50459_2_, double p_i50459_3_, double p_i50459_5_) {
			this.trader = p_i50459_2_;
			this.stopDistance = p_i50459_3_;
			this.speedModifier = p_i50459_5_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public void stop() {
			this.trader.setWanderTarget(null);
			TravelerTofunianEntity.this.navigation.stop();
		}

		public boolean canUse() {
			BlockPos blockpos = this.trader.getWanderTarget();
			return blockpos != null && this.isTooFarAway(blockpos, this.stopDistance);
		}

		public void tick() {
			BlockPos blockpos = this.trader.getWanderTarget();
			if (blockpos != null && TravelerTofunianEntity.this.navigation.isDone()) {
				if (this.isTooFarAway(blockpos, 10.0D)) {
					Vector3d vector3d = (new Vector3d((double) blockpos.getX() - this.trader.getX(), (double) blockpos.getY() - this.trader.getY(), (double) blockpos.getZ() - this.trader.getZ())).normalize();
					Vector3d vector3d1 = vector3d.scale(10.0D).add(this.trader.getX(), this.trader.getY(), this.trader.getZ());
					TravelerTofunianEntity.this.navigation.moveTo(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
				} else {
					TravelerTofunianEntity.this.navigation.moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ(), this.speedModifier);
				}
			}

		}

		private boolean isTooFarAway(BlockPos p_220846_1_, double p_220846_2_) {
			return !p_220846_1_.closerThan(this.trader.position(), p_220846_2_);
		}
	}
}
