package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.projectile.TFShulkerBullet;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class TFShulker extends Shulker implements HasCustomInventoryScreen, ContainerEntity {
	private static final UUID COVERED_ARMOR_MODIFIER_UUID = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
	private static final AttributeModifier COVERED_ARMOR_MODIFIER = new AttributeModifier(
			COVERED_ARMOR_MODIFIER_UUID, "Covered armor bonus", 20.0, AttributeModifier.Operation.ADD_VALUE
	);

	public static final int MOVING_LIMIT_DISTANCE = 256;
	protected static final EntityDataAccessor<Optional<BlockPos>> DATA_TARGET_POS = SynchedEntityData.defineId(TFShulker.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);

	private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);

	private boolean alreadyDroped = false;

	public TFShulker(EntityType<? extends TFShulker> p_33404_, Level p_33405_) {
		super(p_33404_, p_33405_);
		this.xpReward = 0;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_326069_) {
		super.defineSynchedData(p_326069_);
		p_326069_.define(DATA_TARGET_POS, Optional.empty());
	}


	protected void registerGoals() {
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F, 0.02F, true));
		this.goalSelector.addGoal(4, new TFShulkerAttackGoal());
		this.goalSelector.addGoal(7, new TFShulkerPeekGoal());
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	private ItemStack findItem() {
		for (int i = 0; i < this.itemStacks.size(); ++i) {
			ItemStack itemstack = this.itemStacks.get(i);
			if (!itemstack.isEmpty()) {
				return itemstack;
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		this.addChestVehicleSaveData(compoundTag, this.registryAccess());
		if (this.getTargetPos().isPresent()) {
			compoundTag.put("TargetPos", NbtUtils.writeBlockPos(this.getTargetPos().get()));
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);
		this.readChestVehicleSaveData(compoundTag, this.registryAccess());
		if (compoundTag.contains("TargetPos")) {
			this.setTargetPos(NbtUtils.readBlockPos(compoundTag, "TargetPos"));
		}
	}

	public void destroy(DamageSource p_219892_) {
		if (!this.alreadyDroped) {
			ItemStack stack = new ItemStack(TofuItems.TF_SHULKER.get());
			if (this.getColor() != null) {
				stack.set(DataComponents.BASE_COLOR, this.getColor());
			}
			this.destroy(stack);
			this.chestVehicleDestroyed(p_219892_, this.level(), this);
			this.alreadyDroped = true;
		}
	}

	public void destroy(ItemStack p_306235_) {
		this.discard();

		if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
			ItemStack itemstack = p_306235_;
			itemstack.set(DataComponents.CUSTOM_NAME, this.getCustomName());
			this.spawnAtLocation(itemstack);
		}
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource p_21385_, int p_21386_, boolean p_21387_) {
		super.dropCustomDeathLoot(p_21385_, p_21386_, p_21387_);
		this.destroy(p_21385_);
	}

	private Optional<BlockPos> getTargetPos() {
		return this.entityData.get(DATA_TARGET_POS);
	}

	public void setTargetPos(Optional<BlockPos> targetPos) {
		this.entityData.set(DATA_TARGET_POS, targetPos);
	}

	private int getRawPeekAmount() {
		return (Byte) this.entityData.get(DATA_PEEK_ID);
	}

	public void setRawPeekAmount(int p_33419_) {
		if (!this.level().isClientSide) {
			//this.getAttribute(Attributes.ARMOR).removeModifier(COVERED_ARMOR_MODIFIER.id());
			if (p_33419_ == 0) {
				//this.getAttribute(Attributes.ARMOR).addPermanentModifier(COVERED_ARMOR_MODIFIER);
				this.playSound(SoundEvents.SHULKER_CLOSE, 1.0F, 1.0F);
				this.gameEvent(GameEvent.CONTAINER_CLOSE);
			} else {
				this.playSound(SoundEvents.SHULKER_OPEN, 1.0F, 1.0F);
				this.gameEvent(GameEvent.CONTAINER_OPEN);
			}
		}

		this.entityData.set(DATA_PEEK_ID, (byte) p_33419_);
	}

	@Override
	protected boolean teleportSomewhere() {
		this.destroy(this.damageSources().cactus());
		return true;
	}

	@Override
	protected InteractionResult mobInteract(Player p_21472_, InteractionHand p_21473_) {
		InteractionResult interactionresult = this.interactWithContainerVehicle(p_21472_);
		if (interactionresult.consumesAction()) {
			this.gameEvent(GameEvent.CONTAINER_OPEN, p_21472_);
			PiglinAi.angerNearbyPiglins(p_21472_, true);
		}

		return interactionresult;
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double p_27519_) {
		return false;
	}

	@Override
	public void clearContent() {
		this.clearChestVehicleContent();
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	public ItemStack getItem(int p_219880_) {
		return this.itemStacks.get(p_219880_);
	}

	@Override
	public ItemStack removeItem(int p_219882_, int p_219883_) {
		return this.removeChestVehicleItem(p_219882_, p_219883_);
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_219904_) {
		return this.removeChestVehicleItemNoUpdate(p_219904_);
	}

	@Override
	public void setItem(int p_219885_, ItemStack p_219886_) {
		this.setChestVehicleItem(p_219885_, p_219886_);
	}

	@Override
	public SlotAccess getSlot(int p_219918_) {
		return this.getChestVehicleSlot(p_219918_);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player p_219896_) {
		return this.isChestVehicleStillValid(p_219896_);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_219910_, Inventory p_219911_, Player p_219912_) {
		return ChestMenu.threeRows(p_219910_, p_219911_, this);
	}

	@Override
	public void setLootTable(@org.jetbrains.annotations.Nullable ResourceKey<LootTable> p_336019_) {

	}

	@Override
	public void setLootTableSeed(long p_219925_) {

	}

	@Override
	public NonNullList<ItemStack> getItemStacks() {
		return this.itemStacks;
	}

	@Override
	public void clearItemStacks() {
		this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	}

	@Override
	public void stopOpen(Player p_270286_) {
		this.level().gameEvent(GameEvent.CONTAINER_CLOSE, this.position(), GameEvent.Context.of(p_270286_));
	}

	@Override
	public void openCustomInventoryScreen(Player p_219906_) {
		p_219906_.openMenu(this);

		if (!p_219906_.level().isClientSide) {
			this.gameEvent(GameEvent.CONTAINER_OPEN, p_219906_);
			PiglinAi.angerNearbyPiglins(p_219906_, true);
		}
	}

	class TFShulkerAttackGoal extends Goal {
		private int attackTime;

		public TFShulkerAttackGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			return TFShulker.this.getTargetPos().isPresent() && !TFShulker.this.itemStacks.isEmpty() && TFShulker.this.getTargetPos().get().distManhattan(TFShulker.this.blockPosition()) < MOVING_LIMIT_DISTANCE;
		}

		@Override
		public void start() {
			this.attackTime = 20;
			TFShulker.this.setRawPeekAmount(100);
		}

		@Override
		public void stop() {
			TFShulker.this.setRawPeekAmount(0);
		}

		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}

		@Override
		public void tick() {
			this.attackTime--;
			if (TFShulker.this.getTargetPos().isPresent()) {
				TFShulker.this.getLookControl().setLookAt(TFShulker.this.getTargetPos().get().getCenter());
				double d0 = TFShulker.this.distanceToSqr(TFShulker.this.getTargetPos().get().getCenter());
				if (d0 < MOVING_LIMIT_DISTANCE) {
					if (this.attackTime <= 0) {
						this.attackTime = 20 + TFShulker.this.random.nextInt(10) * 20 / 2;
						if (TFShulker.this.level().getBlockEntity(TFShulker.this.getTargetPos().get()) instanceof Container) {
							TFShulkerBullet bullet = new TFShulkerBullet(TFShulker.this.level(), TFShulker.this, TFShulker.this.getTargetPos().get(), TFShulker.this.getAttachFace().getAxis());
							bullet.setItemstack(TFShulker.this.findItem().copyAndClear());
							TFShulker.this.level()
									.addFreshEntity(bullet);
							TFShulker.this.playSound(
									SoundEvents.SHULKER_SHOOT, 2.0F, (TFShulker.this.random.nextFloat() - TFShulker.this.random.nextFloat()) * 0.2F + 1.0F
							);
						}
					}
				}

				super.tick();
			}

		}
	}

	class TFShulkerPeekGoal extends Goal {
		private int peekTime;

		TFShulkerPeekGoal() {
		}

		public boolean canUse() {
			return TFShulker.this.getTargetPos().isEmpty() && TFShulker.this.random.nextInt(reducedTickDelay(40)) == 0;
		}

		public boolean canContinueToUse() {
			return TFShulker.this.getTargetPos().isEmpty() && this.peekTime > 0;
		}

		public void start() {
			this.peekTime = this.adjustedTickDelay(20 * (1 + TFShulker.this.random.nextInt(3)));
			TFShulker.this.setRawPeekAmount(30);
		}

		public void stop() {
			if (TFShulker.this.getTargetPos().isEmpty()) {
				TFShulker.this.setRawPeekAmount(0);
			}

		}

		public void tick() {
			--this.peekTime;
		}
	}
}
