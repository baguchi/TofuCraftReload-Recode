package baguchan.tofucraft.entity.effect;

import baguchan.tofucraft.entity.ShuDofuSpider;
import baguchan.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
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

public class NattoCobWebEntity extends Entity {
	private int lifeTime;
	private static final int discardTime = 400;

	public NattoCobWebEntity(EntityType<?> p_19870_, Level p_19871_) {
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
		if (lifeTime >= discardTime || !level.canSeeSkyFromBelowWater(blockPosition())) {
			this.discard();
		}
		lifeTime++;
		super.tick();
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
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D);
	}


	@Override
	public boolean hurt(DamageSource p_31461_, float p_31462_) {
		if (this.isInvulnerableTo(p_31461_)) {
			return false;
		} else if (p_31461_ != DamageSource.DROWN && p_31461_ != DamageSource.IN_FIRE && p_31461_ != DamageSource.ON_FIRE && !(p_31461_.getEntity() instanceof ShuDofuSpider)) {
			Entity entity = p_31461_.getDirectEntity();
			if (entity instanceof Projectile) {
				return false;
			}
			return super.hurt(p_31461_, p_31462_);
		} else {
			return false;
		}
	}

	@Override
	protected void defineSynchedData() {

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_20052_) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_20139_) {

	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
