package baguchan.tofucraft.entity.effect;

import baguchan.tofucraft.entity.ShuDofuSpider;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class NattoCobWebEntity extends LivingEntity {
	private final NonNullList<ItemStack> handItems = NonNullList.withSize(2, ItemStack.EMPTY);
	private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);
	private int lifeTime;
	private static final int discardTime = 600;

	public NattoCobWebEntity(EntityType<? extends NattoCobWebEntity> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
		noCulling = true;
	}

	public NattoCobWebEntity(Level level, double x, double y, double z) {
		this(TofuEntityTypes.NATTO_COBWEB.get(), level);
		this.setPos(x, y, z);
	}

	@Override
	public void tick() {
		downGround();
		stepSlow();
		if (lifeTime >= discardTime) {
			this.discard();
		}
		lifeTime++;
		super.tick();
	}

	public HumanoidArm getMainArm() {
		return HumanoidArm.RIGHT;
	}

	public void downGround() {
		HitResult rayTrace = rayTrace(this);
		if (rayTrace.getType() == HitResult.Type.BLOCK) {
			BlockHitResult hitResult = (BlockHitResult) rayTrace;
			if (hitResult.getDirection() == Direction.UP) {
				BlockState hitBlock = level.getBlockState(hitResult.getBlockPos());
				if (lifeTime > discardTime && hitBlock != level.getBlockState(blockPosition().below())) {
					this.discard();
				}
				if (hitBlock.getBlock() instanceof SlabBlock && hitBlock.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
					this.setPos(getX(), hitResult.getBlockPos().getY() + 1.0625F - 0.5f, getZ());
				} else {
					this.setPos(getX(), hitResult.getBlockPos().getY() + 1.0625F, getZ());
				}
				if (this.level instanceof ServerLevel) {
					((ServerLevel) this.level).getChunkSource().broadcast(this, new ClientboundTeleportEntityPacket(this));
				}
			}
		}
	}

	private HitResult rayTrace(NattoCobWebEntity entity) {
		Vec3 startPos = new Vec3(entity.getX(), entity.getY(), entity.getZ());
		Vec3 endPos = new Vec3(entity.getX(), 0, entity.getZ());
		return entity.level.clip(new ClipContext(startPos, endPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
	}

	public void stepSlow() {
		AABB area = new AABB(new BlockPos(this.getX(), this.getY() - 0.5f, this.getZ())).inflate(1, 0.5, 1);
		List<LivingEntity> entitiesHit = this.level.getEntitiesOfClass(LivingEntity.class, area);
		for (LivingEntity entity : entitiesHit) {
			double d0 = Math.abs(entity.getDeltaMovement().y);
			double d1 = Math.abs(entity.getDeltaMovement().x) + Math.abs(entity.getDeltaMovement().z);
			this.resetFallDistance();
			//It's not a block, but need blockState, so I'll put in cobweb.
			entity.makeStuckInBlock(Blocks.COBWEB.defaultBlockState(), new Vec3(0.3D, (double) 0.05F, 0.3D));
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 18.0D).add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
	}

	public boolean isSpawing() {
		return lifeTime < 4;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 3 && !this.level.isClientSide()) {
			this.discard();
		}

	}

	@Override
	public boolean hurt(DamageSource p_31461_, float p_31462_) {
		if (this.isInvulnerableTo(p_31461_)) {
			return false;
		} else if (p_31461_ != DamageSource.FALLING_BLOCK && p_31461_ != DamageSource.IN_WALL && p_31461_ != DamageSource.CRAMMING && p_31461_ != DamageSource.DROWN && p_31461_ != DamageSource.IN_FIRE && p_31461_ != DamageSource.ON_FIRE && !(p_31461_.getEntity() instanceof ShuDofuSpider)) {
			Entity entity = p_31461_.getDirectEntity();
			if (entity instanceof Projectile) {
				return false;
			}
			return super.hurt(p_31461_, p_31462_);
		} else {
			return false;
		}
	}

	protected void pushEntities() {
	}

	public Iterable<ItemStack> getHandSlots() {
		return this.handItems;
	}

	public Iterable<ItemStack> getArmorSlots() {
		return this.armorItems;
	}

	public ItemStack getItemBySlot(EquipmentSlot p_31612_) {
		switch (p_31612_.getType()) {
			case HAND:
				return this.handItems.get(p_31612_.getIndex());
			case ARMOR:
				return this.armorItems.get(p_31612_.getIndex());
			default:
				return ItemStack.EMPTY;
		}
	}

	public void setItemSlot(EquipmentSlot p_31584_, ItemStack p_31585_) {
		this.verifyEquippedItem(p_31585_);
		switch (p_31584_.getType()) {
			case HAND:
				this.onEquipItem(p_31584_, this.handItems.set(p_31584_.getIndex(), p_31585_), p_31585_);
				break;
			case ARMOR:
				this.onEquipItem(p_31584_, this.armorItems.set(p_31584_.getIndex(), p_31585_), p_31585_);
		}

	}

	public void addAdditionalSaveData(CompoundTag p_31619_) {
		super.addAdditionalSaveData(p_31619_);
		ListTag listtag = new ListTag();
		for (ItemStack itemstack : this.armorItems) {
			CompoundTag compoundtag = new CompoundTag();
			if (!itemstack.isEmpty()) {
				itemstack.save(compoundtag);
			}

			listtag.add(compoundtag);
		}

		p_31619_.put("ArmorItems", listtag);
		ListTag listtag1 = new ListTag();

		for (ItemStack itemstack1 : this.handItems) {
			CompoundTag compoundtag1 = new CompoundTag();
			if (!itemstack1.isEmpty()) {
				itemstack1.save(compoundtag1);
			}

			listtag1.add(compoundtag1);
		}
	}

	public void readAdditionalSaveData(CompoundTag p_31600_) {
		super.readAdditionalSaveData(p_31600_);
		if (p_31600_.contains("ArmorItems", 9)) {
			ListTag listtag = p_31600_.getList("ArmorItems", 10);

			for (int i = 0; i < this.armorItems.size(); ++i) {
				this.armorItems.set(i, ItemStack.of(listtag.getCompound(i)));
			}
		}

		if (p_31600_.contains("HandItems", 9)) {
			ListTag listtag1 = p_31600_.getList("HandItems", 10);

			for (int j = 0; j < this.handItems.size(); ++j) {
				this.handItems.set(j, ItemStack.of(listtag1.getCompound(j)));
			}
		}
	}

	public boolean isPushable() {
		return false;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	@Override
	public boolean canBeAffected(MobEffectInstance p_21197_) {
		return false;
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
