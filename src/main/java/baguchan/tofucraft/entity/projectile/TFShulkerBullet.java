package baguchan.tofucraft.entity.projectile;

import baguchan.tofucraft.registry.TofuEntityTypes;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class TFShulkerBullet extends Projectile {
	protected static final EntityDataAccessor<ItemStack> DATA_ITEMSTACK = SynchedEntityData.defineId(TFShulkerBullet.class, EntityDataSerializers.ITEM_STACK);

	private static final double SPEED = 0.15;
	@Nullable
	private BlockPos finalTarget;
	@Nullable
	private Direction currentMoveDirection;
	private int flightSteps;
	private double targetDeltaX;
	private double targetDeltaY;
	private double targetDeltaZ;

	public TFShulkerBullet(EntityType<? extends TFShulkerBullet> p_37319_, Level p_37320_) {
		super(p_37319_, p_37320_);
	}

	public TFShulkerBullet(Level p_37330_, LivingEntity p_37331_, BlockPos p_37332_, Direction.Axis p_37333_) {
		this(TofuEntityTypes.TF_SHULKER_BULLET.get(), p_37330_);
		this.setOwner(p_37331_);
		Vec3 vec3 = p_37331_.getBoundingBox().getCenter();
		this.moveTo(vec3.x, vec3.y, vec3.z, this.getYRot(), this.getXRot());
		this.finalTarget = p_37332_;
		this.currentMoveDirection = Direction.UP;
		this.selectNextMoveDirection(p_37333_);
	}

	@Override
	public SoundSource getSoundSource() {
		return SoundSource.HOSTILE;
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_ITEMSTACK, ItemStack.EMPTY);
	}

	public void setItemstack(ItemStack stack) {
		this.entityData.set(DATA_ITEMSTACK, stack);
	}

	public ItemStack getItemstack() {
		return this.entityData.get(DATA_ITEMSTACK);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_37357_) {
		super.addAdditionalSaveData(p_37357_);
		if (this.finalTarget != null) {
			p_37357_.put("Target", NbtUtils.writeBlockPos(this.finalTarget));
		}

		if (this.currentMoveDirection != null) {
			p_37357_.putInt("Dir", this.currentMoveDirection.get3DDataValue());
		}

		if (!this.getItemstack().isEmpty()) {
			p_37357_.put("Item", this.getItemstack().save(this.registryAccess()));
		}

		p_37357_.putInt("Steps", this.flightSteps);
		p_37357_.putDouble("TXD", this.targetDeltaX);
		p_37357_.putDouble("TYD", this.targetDeltaY);
		p_37357_.putDouble("TZD", this.targetDeltaZ);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_37353_) {
		super.readAdditionalSaveData(p_37353_);
		this.flightSteps = p_37353_.getInt("Steps");
		this.targetDeltaX = p_37353_.getDouble("TXD");
		this.targetDeltaY = p_37353_.getDouble("TYD");
		this.targetDeltaZ = p_37353_.getDouble("TZD");
		if (p_37353_.contains("Dir", 99)) {
			this.currentMoveDirection = Direction.from3DDataValue(p_37353_.getInt("Dir"));
		}

		if (p_37353_.contains("Target")) {
			this.finalTarget = NbtUtils.readBlockPos(p_37353_, "Target").orElse(null);
		}

		if (p_37353_.contains("Item")) {
			this.setItemstack(ItemStack.parse(this.registryAccess(), p_37353_.getCompound("Item")).orElse(ItemStack.EMPTY));
		}
	}

	@Nullable
	private Direction getMoveDirection() {
		return this.currentMoveDirection;
	}

	private void setMoveDirection(@Nullable Direction p_37351_) {
		this.currentMoveDirection = p_37351_;
	}

	private void selectNextMoveDirection(@Nullable Direction.Axis p_37349_) {
		double d0 = 0.5;
		BlockPos blockpos;
		if (this.finalTarget == null) {
			blockpos = this.blockPosition().below();
		} else {
			blockpos = BlockPos.containing(this.finalTarget.getX(), this.finalTarget.getY() + d0, this.finalTarget.getZ());
		}

		double d1 = (double) blockpos.getX() + 0.5;
		double d2 = (double) blockpos.getY() + d0;
		double d3 = (double) blockpos.getZ() + 0.5;
		Direction direction = null;
		if (!blockpos.closerToCenterThan(this.position(), 2.0)) {
			BlockPos blockpos1 = this.blockPosition();
			List<Direction> list = Lists.newArrayList();
			if (p_37349_ != Direction.Axis.X) {
				if (blockpos1.getX() < blockpos.getX() && this.level().isEmptyBlock(blockpos1.east())) {
					list.add(Direction.EAST);
				} else if (blockpos1.getX() > blockpos.getX() && this.level().isEmptyBlock(blockpos1.west())) {
					list.add(Direction.WEST);
				}
			}

			if (p_37349_ != Direction.Axis.Y) {
				if (blockpos1.getY() < blockpos.getY() && this.level().isEmptyBlock(blockpos1.above())) {
					list.add(Direction.UP);
				} else if (blockpos1.getY() > blockpos.getY() && this.level().isEmptyBlock(blockpos1.below())) {
					list.add(Direction.DOWN);
				}
			}

			if (p_37349_ != Direction.Axis.Z) {
				if (blockpos1.getZ() < blockpos.getZ() && this.level().isEmptyBlock(blockpos1.south())) {
					list.add(Direction.SOUTH);
				} else if (blockpos1.getZ() > blockpos.getZ() && this.level().isEmptyBlock(blockpos1.north())) {
					list.add(Direction.NORTH);
				}
			}

			direction = Direction.getRandom(this.random);
			if (list.isEmpty()) {
				for (int i = 5; !this.level().isEmptyBlock(blockpos1.relative(direction)) && i > 0; i--) {
					direction = Direction.getRandom(this.random);
				}
			} else {
				direction = list.get(this.random.nextInt(list.size()));
			}

			d1 = this.getX() + (double) direction.getStepX();
			d2 = this.getY() + (double) direction.getStepY();
			d3 = this.getZ() + (double) direction.getStepZ();
		}

		this.setMoveDirection(direction);
		double d6 = d1 - this.getX();
		double d7 = d2 - this.getY();
		double d4 = d3 - this.getZ();
		double d5 = Math.sqrt(d6 * d6 + d7 * d7 + d4 * d4);
		if (d5 == 0.0) {
			this.targetDeltaX = 0.0;
			this.targetDeltaY = 0.0;
			this.targetDeltaZ = 0.0;
		} else {
			this.targetDeltaX = d6 / d5 * 0.15;
			this.targetDeltaY = d7 / d5 * 0.15;
			this.targetDeltaZ = d4 / d5 * 0.15;
		}

		this.hasImpulse = true;
		this.flightSteps = 10 + this.random.nextInt(5) * 10;
	}

	@Override
	public void checkDespawn() {
	}

	@Override
	protected double getDefaultGravity() {
		return 0.0;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level().isClientSide) {

			if (this.finalTarget != null && this.finalTarget.distManhattan(this.blockPosition()) <= 1.5F) {
				if (this.level().getBlockEntity(this.finalTarget) instanceof Container container) {
					for (int i = 0; i < container.getContainerSize(); ++i) {
						ItemStack stack = this.addItem(container, this.getItemstack().copyAndClear());
						if (!this.getItemstack().isEmpty()) {
							this.spawnAtLocation(stack);
						}
						this.destroy();
						((ServerLevel) this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2, 0.2, 0.2, 0.0);
						this.playSound(SoundEvents.SHULKER_BULLET_HIT, 1.0F, 1.0F);

						break;
					}
				}
			}

			if (this.finalTarget == null) {
				this.applyGravity();
			} else {
				this.targetDeltaX = Mth.clamp(this.targetDeltaX * 1.025, -1.0, 1.0);
				this.targetDeltaY = Mth.clamp(this.targetDeltaY * 1.025, -1.0, 1.0);
				this.targetDeltaZ = Mth.clamp(this.targetDeltaZ * 1.025, -1.0, 1.0);
				Vec3 vec3 = this.getDeltaMovement();
				this.setDeltaMovement(vec3.add((this.targetDeltaX - vec3.x) * 0.25, (this.targetDeltaY - vec3.y) * 0.25, (this.targetDeltaZ - vec3.z) * 0.25));
			}

			HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
			if (hitresult.getType() != HitResult.Type.MISS && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult)) {
				this.hitTargetOrDeflectSelf(hitresult);
			}

		}
		Vec3 vec31 = this.getDeltaMovement();
		this.move(MoverType.SELF, vec31);
		ProjectileUtil.rotateTowardsMovement(this, 0.5F);
		if (this.level().isClientSide) {
			this.level().addParticle(ParticleTypes.END_ROD, this.getX() - vec31.x, this.getY() - vec31.y + 0.15, this.getZ() - vec31.z, 0.0, 0.0, 0.0);
		} else if (this.finalTarget != null) {
			if (this.flightSteps > 0) {
				this.flightSteps--;
				if (this.flightSteps == 0) {
					this.selectNextMoveDirection(this.currentMoveDirection == null ? null : this.currentMoveDirection.getAxis());
				}
			}

			if (this.currentMoveDirection != null) {
				BlockPos blockpos = this.blockPosition();
				Direction.Axis direction$axis = this.currentMoveDirection.getAxis();
				if (this.level().loadedAndEntityCanStandOn(blockpos.relative(this.currentMoveDirection), this)) {
					this.selectNextMoveDirection(direction$axis);
				} else {
					BlockPos blockpos1 = this.finalTarget;
					if (direction$axis == Direction.Axis.X && blockpos.getX() == blockpos1.getX()
							|| direction$axis == Direction.Axis.Z && blockpos.getZ() == blockpos1.getZ()
							|| direction$axis == Direction.Axis.Y && blockpos.getY() == blockpos1.getY()) {
						this.selectNextMoveDirection(direction$axis);
					}
				}
			}
		}
	}

	@Override
	protected boolean canHitEntity(Entity p_37341_) {
		return super.canHitEntity(p_37341_) && !p_37341_.noPhysics;
	}

	@Override
	public boolean isOnFire() {
		return false;
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double p_37336_) {
		return p_37336_ < 16384.0;
	}

	@Override
	public float getLightLevelDependentMagicValue() {
		return 1.0F;
	}

	@Override
	protected void onHitBlock(BlockHitResult p_37343_) {
		super.onHitBlock(p_37343_);
		if (!this.getItemstack().isEmpty()) {
			if (this.level().getBlockEntity(p_37343_.getBlockPos()) instanceof Container container) {
				for (int i = 0; i < container.getContainerSize(); ++i) {
					ItemStack stack = this.addItem(container, this.getItemstack().copyAndClear());
					if (!this.getItemstack().isEmpty()) {
						this.spawnAtLocation(stack);
					}
					this.destroy();
					((ServerLevel) this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2, 0.2, 0.2, 0.0);
					this.playSound(SoundEvents.SHULKER_BULLET_HIT, 1.0F, 1.0F);

					break;
				}
			}
		} else {
			this.destroy();
		}
	}

	@Nullable
	public ItemStack addItem(Container chest, ItemStack stack) {
		ItemStack itemstack = stack.copy();

		for (int i = 0; i < chest.getContainerSize(); ++i) {
			ItemStack itemstack1 = chest.getItem(i);

			if (itemstack1.isEmpty()) {
				chest.setItem(i, itemstack);
				chest.setChanged();
				return ItemStack.EMPTY;
			}

			if (ItemStack.isSameItemSameComponents(itemstack1, itemstack)) {
				int j = Math.min(chest.getMaxStackSize(), itemstack1.getMaxStackSize());
				int k = Math.min(itemstack.getCount(), j - itemstack1.getCount());

				if (k > 0) {
					itemstack1.grow(k);
					itemstack.shrink(k);

					if (itemstack.getCount() <= 0) {
						chest.setChanged();
						return ItemStack.EMPTY;
					}
				}
			}
		}

		if (itemstack.getCount() != stack.getCount()) {
			chest.setChanged();
		}

		return itemstack;
	}

	private void destroy() {
		this.discard();
		if (!this.getItemstack().isEmpty()) {
			this.spawnAtLocation(this.getItemstack());
		}
		this.level().gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
	}

	@Override
	protected void onHit(HitResult p_37347_) {
		super.onHit(p_37347_);
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public boolean hurt(DamageSource p_37338_, float p_37339_) {
		if (!this.level().isClientSide) {
			this.playSound(SoundEvents.SHULKER_BULLET_HURT, 1.0F, 1.0F);
			((ServerLevel) this.level()).sendParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 15, 0.2, 0.2, 0.2, 0.0);
			this.destroy();
		}

		return true;
	}

	@Override
	public void recreateFromPacket(ClientboundAddEntityPacket p_150185_) {
		super.recreateFromPacket(p_150185_);
		double d0 = p_150185_.getXa();
		double d1 = p_150185_.getYa();
		double d2 = p_150185_.getZa();
		this.setDeltaMovement(d0, d1, d2);
	}
}
