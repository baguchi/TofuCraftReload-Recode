package baguchi.tofucraft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FukumameThrower extends Piglin {
	private static final EntityDataAccessor<Boolean> DATA_CHARGE = SynchedEntityData.defineId(FukumameThrower.class, EntityDataSerializers.BOOLEAN);

	private static final EntityDataAccessor<Integer> DATA_FUKUMAME_COUNT = SynchedEntityData.defineId(FukumameThrower.class, EntityDataSerializers.INT);
	private static final Brain.Provider<Piglin> BRAIN_PROVIDER = Brain.<Piglin>provider(
			List.of(
					MemoryModuleType.UNIVERSAL_ANGER,
					MemoryModuleType.ATE_RECENTLY,
					MemoryModuleType.SPEAR_FLEEING_TIME,
					MemoryModuleType.SPEAR_FLEEING_POSITION,
					MemoryModuleType.SPEAR_CHARGE_POSITION,
					MemoryModuleType.SPEAR_ENGAGE_TIME
			),
			List.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.HURT_BY, SensorType.PIGLIN_SPECIFIC_SENSOR),
			FukumameThrowerAi::getActivities
	);
	public FukumameThrower(EntityType<? extends FukumameThrower> p_34683_, Level p_34684_) {
		super(p_34683_, p_34684_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_CHARGE, false);
		builder.define(DATA_FUKUMAME_COUNT, 64);
	}

	public void setCharge(boolean charge) {
		this.entityData.set(DATA_CHARGE, charge);
	}

	public boolean isCharge() {
		return this.entityData.get(DATA_CHARGE);
	}

	public void setFukumameCount(int fukumame) {
		this.entityData.set(DATA_FUKUMAME_COUNT, fukumame);
	}

	public int getFukumameCount() {
		return this.entityData.get(DATA_FUKUMAME_COUNT);
	}

	@Override
	public void addAdditionalSaveData(ValueOutput p_34751_) {
		super.addAdditionalSaveData(p_34751_);
		p_34751_.putInt("FukumameCount", this.getFukumameCount());
	}

	@Override
	public void readAdditionalSaveData(ValueInput p_34725_) {
		super.readAdditionalSaveData(p_34725_);
		this.setFukumameCount(p_34725_.getIntOr("FukumameCount", 64));
	}

	@Override
	public boolean canHunt() {
		return true;
	}

	@Override
	protected Brain<Piglin> makeBrain(Brain.Packed packedBrain) {
		return BRAIN_PROVIDER.makeBrain(this, packedBrain);
	}


	public ItemStack addToInventory(ItemStack p_34779_) {
		return super.addToInventory(p_34779_);
	}

	public boolean canAddToInventory(ItemStack p_34781_) {
		return super.canAddToInventory(p_34781_);
	}

	public void eatFukumame() {
		this.heal(1);
		this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600));
	}

	public boolean canReplaceCurrentItem(ItemStack p_34788_) {
		EquipmentSlot equipmentslot = this.getEquipmentSlotForItem(p_34788_);
		ItemStack itemstack = this.getItemBySlot(equipmentslot);
		return this.canReplaceCurrentItem(p_34788_, itemstack, equipmentslot);
	}

	public void holdInMainHand(ItemStack p_34784_) {
		this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, p_34784_);
	}

	public void holdInOffHand(ItemStack p_34786_) {
		if (p_34786_.isPiglinCurrency()) {
			this.setItemSlot(EquipmentSlot.OFFHAND, p_34786_);
			this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
		} else {
			this.setItemSlotAndDropWhenKilled(EquipmentSlot.OFFHAND, p_34786_);
		}

	}

	@Override
	public boolean isBaby() {
		return false;
	}

	public static boolean checkFukumameSpawnRules(EntityType<? extends FukumameThrower> p_219198_, LevelAccessor p_219199_, EntitySpawnReason p_219200_, BlockPos p_219201_, RandomSource p_219202_) {
		return !p_219199_.getBlockState(p_219201_.below()).is(Blocks.NETHER_WART_BLOCK);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34717_, DifficultyInstance p_34718_, EntitySpawnReason p_34719_, @Nullable SpawnGroupData p_34720_) {

		return super.finalizeSpawn(p_34717_, p_34718_, p_34719_, p_34720_);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource source, DifficultyInstance pDifficulty) {
		super.populateDefaultEquipmentSlots(source, pDifficulty);
		if (this.isAdult()) {
			this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
		}
		this.setFukumameCount(32 + random.nextInt(32));
	}
}
