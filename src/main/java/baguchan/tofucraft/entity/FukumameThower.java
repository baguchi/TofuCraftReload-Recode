package baguchan.tofucraft.entity;

import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class FukumameThower extends Piglin {
	private static final EntityDataAccessor<Boolean> DATA_CHARGE = SynchedEntityData.defineId(FukumameThower.class, EntityDataSerializers.BOOLEAN);

	public FukumameThower(EntityType<? extends FukumameThower> p_34683_, Level p_34684_) {
		super(p_34683_, p_34684_);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_CHARGE, false);
	}

	public void setCharge(boolean charge) {
		this.entityData.set(DATA_CHARGE, charge);
	}

	public boolean isCharge() {
		return this.entityData.get(DATA_CHARGE);
	}

	@Override
	public boolean canHunt() {
		return true;
	}

	protected Brain.Provider<FukumameThower> alchemistBrainProvider() {
		return Brain.provider(Piglin.MEMORY_TYPES, SENSOR_TYPES);
	}


	@Override
	protected Brain<?> makeBrain(Dynamic<?> p_34723_) {
		return FukumameThowerAi.makeBrain(this, this.alchemistBrainProvider().makeBrain(p_34723_));
	}

	public ItemStack addToInventory(ItemStack p_34779_) {
		return super.addToInventory(p_34779_);
	}

	public boolean canAddToInventory(ItemStack p_34781_) {
		return super.canAddToInventory(p_34781_);
	}

	public boolean canReplaceCurrentItem(ItemStack p_34788_) {
		EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(p_34788_);
		ItemStack itemstack = this.getItemBySlot(equipmentslot);
		return this.canReplaceCurrentItem(p_34788_, itemstack);
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

	public static boolean checkFukumameSpawnRules(EntityType<? extends FukumameThower> p_219198_, LevelAccessor p_219199_, MobSpawnType p_219200_, BlockPos p_219201_, RandomSource p_219202_) {
		return !p_219199_.getBlockState(p_219201_.below()).is(Blocks.NETHER_WART_BLOCK);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource source, DifficultyInstance pDifficulty) {
		super.populateDefaultEquipmentSlots(source, pDifficulty);
		if (this.isAdult()) {
			this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
		}
	}
}
