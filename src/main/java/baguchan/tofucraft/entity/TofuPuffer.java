package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.path.DoubanjiangPathNavigation;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuEntityDatas;
import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TofuPuffer extends AbstractTofuFish implements IFluidHandler {

	private static final EntityDataAccessor<FluidStack> DATA_FLUID = SynchedEntityData.defineId(TofuPuffer.class, TofuEntityDatas.FLUID_STACK.get());

	public TofuPuffer(EntityType<? extends TofuPuffer> p_27523_, Level p_27524_) {
		super(p_27523_, p_27524_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_FLUID, FluidStack.EMPTY);
	}

	public void setFluidStack(FluidStack fluidStack) {
		this.entityData.set(DATA_FLUID, fluidStack);
	}

	public FluidStack getFluidStack() {
		return this.entityData.get(DATA_FLUID);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		if (!this.getFluidStack().isEmpty()) {
			compound.put("AteFluids", this.getFluidStack().save(this.registryAccess()));
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("AteFluids")) {
			this.setFluidStack(FluidStack.parse(this.registryAccess(), compound.getCompound("AteFluids")).orElse(FluidStack.EMPTY));
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 1.0).add(Attributes.MAX_HEALTH, 12.0);
	}

	protected PathNavigation createNavigation(Level p_27480_) {
		return new DoubanjiangPathNavigation(this, p_27480_);
	}


	static <T extends LivingEntity & Bucketable> Optional<InteractionResult> bucketMobPickup(Player player, InteractionHand hand, T entity) {
		return Optional.empty();
	}

	public ItemStack getBucketItemStack() {
		return ItemStack.EMPTY;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.PUFFER_FISH_AMBIENT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.PUFFER_FISH_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource p_28281_) {
		return SoundEvents.PUFFER_FISH_HURT;
	}


	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.PUFFER_FISH_FLOP;
	}


	protected InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_) {
		return bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
	}
	public static boolean checkTofuPufferSpawnRules(EntityType<? extends TofuPuffer> p_27468_, LevelAccessor p_27469_, MobSpawnType p_27470_, BlockPos p_27471_, RandomSource p_27472_) {
		return p_27469_.getBlockState(p_27471_).is(TofuBlocks.DOUBANJIANG.get());
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return type != TofuFluidTypes.DOUBANJIANG.get() && super.canDrownInFluidType(type);
	}

	@Override
	public boolean isInWater() {
		return super.isInWater() || this.isInFluidType(TofuFluidTypes.DOUBANJIANG.get());
	}

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public FluidStack getFluidInTank(int tank) {
		return getFluidStack();
	}

	@Override
	public int getTankCapacity(int tank) {
		return 1000;
	}

	@Override
	public boolean isFluidValid(int tank, FluidStack stack) {
		return true;
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		FluidStack fluid = getFluidStack().copy();
		if (resource.isEmpty()) {
			return 0;
		}
		if (action.simulate()) {
			if (fluid.isEmpty()) {
				return Math.min(getTankCapacity(0), resource.getAmount());
			}
			if (!FluidStack.isSameFluidSameComponents(fluid, resource)) {
				return 0;
			}
			return Math.min(getTankCapacity(0) - fluid.getAmount(), resource.getAmount());
		}
		if (fluid.isEmpty()) {
			fluid = resource.copyWithAmount(Math.min(getTankCapacity(0), resource.getAmount()));
			setFluidStack(fluid);
			return fluid.getAmount();
		}
		if (!FluidStack.isSameFluidSameComponents(fluid, resource)) {
			return 0;
		}
		int filled = getTankCapacity(0) - fluid.getAmount();

		if (resource.getAmount() < filled) {
			fluid.grow(resource.getAmount());
			filled = resource.getAmount();
		} else {
			fluid.setAmount(getTankCapacity(0));
		}
		if (filled > 0)
			setFluidStack(fluid);
		return filled;
	}

	@Override
	public FluidStack drain(FluidStack resource, FluidAction action) {
		if (resource.isEmpty() || !FluidStack.isSameFluidSameComponents(resource, getFluidStack())) {
			return FluidStack.EMPTY;
		}
		return drain(resource.getAmount(), action);
	}

	@Override
	public FluidStack drain(int maxDrain, FluidAction action) {
		int drained = maxDrain;
		if (getFluidStack().getAmount() < drained) {
			drained = getFluidStack().getAmount();
		}
		FluidStack stack = getFluidStack().copyWithAmount(drained);
		if (action.execute() && drained > 0) {
			FluidStack fluidStack = getFluidStack().copy();
			fluidStack.shrink(drained);
			setFluidStack(fluidStack);
		}
		return stack;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		if (level.getFluidState(blockPosition()).is(TofuTags.Fluids.SOYMILK)) {
			this.fill(new FluidStack(TofuFluids.SOYMILK.get(), 1000), FluidAction.EXECUTE);
		} else {
			this.fill(new FluidStack(TofuFluids.DOUBANJIANG.get(), 1000), FluidAction.EXECUTE);
		}
		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}
}
