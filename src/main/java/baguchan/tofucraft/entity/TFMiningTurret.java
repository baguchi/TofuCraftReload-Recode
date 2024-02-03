package baguchan.tofucraft.entity;

import baguchan.tofucraft.blockentity.tfenergy.TFMinerBlockEntity;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class TFMiningTurret extends AbstractGolem {
	@Nullable
	private BlockPos miningPos;

	@Nullable
	private BlockPos homePos;

	public TFMiningTurret(EntityType<? extends TFMiningTurret> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.moveControl = new FlyingMoveControl(this, 20, true);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.FOLLOW_RANGE, 28F).add(Attributes.MOVEMENT_SPEED, 0.11D).add(Attributes.FLYING_SPEED, 0.11D).add(Attributes.ATTACK_KNOCKBACK, 0.6F).add(Attributes.KNOCKBACK_RESISTANCE, 0.85D).add(Attributes.ARMOR, 8.0F).add(Attributes.ATTACK_DAMAGE, 10.0D);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}


	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MiningPositionGoal(this, 1.2D));
		this.goalSelector.addGoal(1, new BackToHomeGoal(this, 1.0D));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	public void addAdditionalSaveData(CompoundTag p_28867_) {
		super.addAdditionalSaveData(p_28867_);
		if (this.miningPos != null) {
			p_28867_.put("MiningPos", NbtUtils.writeBlockPos(this.miningPos));
		}
		if (this.homePos != null) {
			p_28867_.put("HomePos", NbtUtils.writeBlockPos(this.homePos));
		}
	}

	public void readAdditionalSaveData(CompoundTag p_28857_) {
		super.readAdditionalSaveData(p_28857_);
		if (p_28857_.contains("MiningPos")) {
			this.miningPos = NbtUtils.readBlockPos(p_28857_.getCompound("MiningPos"));
		}
		if (p_28857_.contains("HomePos")) {
			this.homePos = NbtUtils.readBlockPos(p_28857_.getCompound("HomePos"));
		}
	}

	@Override
	protected PathNavigation createNavigation(Level p_218342_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	@Override
	public void travel(Vec3 p_218382_) {
		if (this.isControlledByLocalInstance()) {
			if (this.isInWater()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.8F));
			} else if (this.isInLava()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
			} else {
				this.moveRelative(this.getSpeed(), p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.91F));
			}
		}

		this.calculateEntityAnimation(false);
	}


	@Override
	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	@Override
	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	@Override
	public boolean removeWhenFarAway(double p_27519_) {
		return false;
	}

	@Override
	public void tick() {
		super.tick();
		if (this.tickCount % 30 + this.getId() == 0) {
			this.checkMining();
		}
	}

	public void setMiningPos(@Nullable BlockPos p_35884_) {
		this.miningPos = p_35884_;
	}

	@Nullable
	public BlockPos getMiningPos() {
		return this.miningPos;
	}

	public void setHomePos(@Nullable BlockPos homePos) {
		this.homePos = homePos;
	}

	@Nullable
	public BlockPos getHomePos() {
		return homePos;
	}

	public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
		if (p_28298_.isShiftKeyDown()) {
			ItemStack stack = new ItemStack(TofuItems.TF_MINING_TURRET.get());
			spawnAtLocation(stack);
			if (!this.level().isClientSide) {
				this.discard();
			}
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(p_28298_, p_28299_);
	}

	public void checkMining() {
		if (this.getHomePos() != null && this.miningPos == null) {
			BlockEntity blockEntity = this.level().getBlockEntity(this.getHomePos());
			if (blockEntity instanceof TFMinerBlockEntity tfMinerBlock) {
				this.miningPos = (tfMinerBlock.getWorkingBlockPos());
			}
		}
	}

	@Override
	public boolean canBeAffected(MobEffectInstance p_21197_) {
		return false;
	}

	class BackToHomeGoal extends Goal {
		private final TFMiningTurret creature;

		final double speedModifier;

		public BackToHomeGoal(TFMiningTurret creature, double speedIn) {
			this.creature = creature;
			this.speedModifier = speedIn;
		}


		public boolean canUse() {
			return (this.creature.homePos != null) && this.creature.distanceToSqr(this.creature.homePos.getCenter()) > 4;
		}

		public boolean canContinueToUse() {
			return super.canContinueToUse();
		}

		public void tick() {
			super.tick();
			if (this.creature.homePos != null) {
				this.creature.getNavigation().moveTo(this.creature.homePos.getCenter().x, this.creature.homePos.getCenter().y, this.creature.homePos.getCenter().z, this.speedModifier);
			}
		}

		@Override
		public void start() {
			super.start();
		}

		@Override
		public void stop() {
			super.stop();
		}
	}

	class MiningPositionGoal extends Goal {
		private final TFMiningTurret creature;

		final double speedModifier;
		private boolean miningComplete;
		protected int lastBreakProgress = -1;
		protected int doorBreakTime = -1;
		protected int breakTime;

		public MiningPositionGoal(TFMiningTurret creature, double speedIn) {
			this.creature = creature;
			this.speedModifier = speedIn;
		}

		protected int getBreakTime() {
			return Math.max(6, this.doorBreakTime);
		}

		public boolean canUse() {
			return (this.creature.miningPos != null);
		}

		public boolean canContinueToUse() {
			return (super.canContinueToUse() && !this.miningComplete && this.breakTime <= this.getBreakTime());
		}

		public void tick() {
			super.tick();
			if (!miningComplete && this.creature.miningPos != null && this.creature.distanceToSqr(this.creature.miningPos.getCenter()) < 4) {
				++this.breakTime;
				int i = (int) ((float) this.breakTime / (float) this.getBreakTime() * 10.0F);
				if (i != this.lastBreakProgress) {
					this.creature.level().destroyBlockProgress(this.creature.getId(), this.creature.miningPos, i);
					this.lastBreakProgress = i;
				}

				if (this.breakTime == this.getBreakTime()) {
					this.creature.level().destroyBlock(this.creature.miningPos, true);
					miningComplete = true;
					this.creature.level().levelEvent(2001, this.creature.miningPos, Block.getId(this.creature.level().getBlockState(this.creature.miningPos)));
				}


			}

			if (this.creature.miningPos != null) {
				this.creature.getLookControl().setLookAt(this.creature.miningPos.getCenter());
				this.creature.getNavigation().moveTo(this.creature.miningPos.getCenter().x, this.creature.miningPos.getCenter().y, this.creature.miningPos.getCenter().z, this.speedModifier);
			}
		}

		@Override
		public void start() {
			super.start();
			this.breakTime = 0;
		}

		@Override
		public void stop() {
			super.stop();

			miningComplete = false;
			this.creature.miningPos = null;
		}
	}
}
