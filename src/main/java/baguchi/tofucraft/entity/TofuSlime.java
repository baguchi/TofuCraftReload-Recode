package baguchi.tofucraft.entity;

import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import java.util.EnumSet;

public class TofuSlime extends Slime {
	private static final EntityDataAccessor<Boolean> DATA_CONVERSION_ID = SynchedEntityData.defineId(TofuSlime.class, EntityDataSerializers.BOOLEAN);

	private int onZundaTime;
	private int conversionTime;


	public TofuSlime(EntityType<? extends TofuSlime> p_33588_, Level p_33589_) {
		super(p_33588_, p_33589_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_CONVERSION_ID, false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new TofuSlimeFloatGoal(this));
	}

	public void shoot(double p_37266_, double p_37267_, double p_37268_, float p_37269_, float p_37270_) {
		Vec3 vec3 = new Vec3(p_37266_, p_37267_, p_37268_)
				.normalize()
				.add(
						this.random.triangle(0.0, 0.0172275 * (double) p_37270_),
						this.random.triangle(0.0, 0.0172275 * (double) p_37270_),
						this.random.triangle(0.0, 0.0172275 * (double) p_37270_)
				)
				.scale((double) p_37269_);
		this.setDeltaMovement(vec3);
		double d0 = vec3.horizontalDistance();
		this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * 180.0F / (float) Math.PI));
		this.setXRot((float) (Mth.atan2(vec3.y, d0) * 180.0F / (float) Math.PI));
		this.yRotO = this.getYRot();
		this.xRotO = this.getXRot();
	}

	@Override
	public void thunderHit(ServerLevel p_19927_, LightningBolt p_19928_) {
		if (!this.level().isClientSide() && this.isAlive()) {
			this.doZundaConversion();
		}
	}

	public boolean isZundaConverting() {
		return this.getEntityData().get(DATA_CONVERSION_ID);
	}

	@Override
	public void tick() {
		if (!this.level().isClientSide() && this.isAlive() && !this.isNoAi()) {
			if (this.isZundaConverting()) {
				--this.conversionTime;
				if (this.conversionTime < 0 && EventHooks.canLivingConvert(this, EntityType.DROWNED, (timer) -> this.conversionTime = timer)) {
					this.doZundaConversion();
				}
			} else if (this.convertsOnZunda()) {
				if (this.level().getBiome(this.blockPosition()).is(TofuBiomes.ZUNDA_FOREST) || this.hasData(TofuAttachments.TOFU_LIVING.get()) && this.getData(TofuAttachments.TOFU_LIVING.get()).isZundafied()) {
					++this.onZundaTime;
					if (this.onZundaTime >= 600) {
						this.startZundaConversion(300);
					}
				} else {
					this.onZundaTime = -1;
				}
			}
		}

		super.tick();
	}

	protected boolean convertsOnZunda() {
		return true;
	}

	private void startZundaConversion(int p_34279_) {
		this.conversionTime = p_34279_;
		this.getEntityData().set(DATA_CONVERSION_ID, true);
	}

	protected void doZundaConversion() {
		this.spawnAtLocation((ServerLevel) this.level(), new ItemStack(TofuItems.TOFUZUNDA.get(), 2 * getSize()));
		this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED);
		this.discard();
	}

	@Override
	public void addAdditionalSaveData(ValueOutput p_34319_) {
		super.addAdditionalSaveData(p_34319_);
		p_34319_.putInt("OnZundaTime", this.convertsOnZunda() ? this.onZundaTime : -1);
		p_34319_.putInt("ZundaConversionTime", this.isZundaConverting() ? this.conversionTime : -1);
		p_34319_.putBoolean("ZundaConverting", this.isZundaConverting());
	}

	@Override
	public void readAdditionalSaveData(ValueInput p_34305_) {
		super.readAdditionalSaveData(p_34305_);
		this.onZundaTime = p_34305_.getIntOr("OnZundaTime", -1);
		if (p_34305_.getIntOr("ZundaConversionTime", -1) > -1) {
			this.startZundaConversion(p_34305_.getIntOr("ZundaConversionTime", -1));
		}
	}

	@Override
	protected ParticleOptions getParticleType() {
		return new ItemParticleOption(ParticleTypes.ITEM, TofuItems.TOFUKINU.get());
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_33009_, BlockPos p_33010_, RandomSource p_33011_) {
		if (p_33009_.getBrightness(LightLayer.SKY, p_33010_) > p_33011_.nextInt(32)) {
			return false;
		} else {
			int var3 = p_33009_.getLevel().isThundering() ? p_33009_.getMaxLocalRawBrightness(p_33010_, 10) : p_33009_.getMaxLocalRawBrightness(p_33010_);
			return var3 <= p_33011_.nextInt(8);
		}
	}

	public static boolean checkMonsterSpawnRules(EntityType<? extends TofuSlime> p_33018_, ServerLevelAccessor p_33019_, EntitySpawnReason p_33020_, BlockPos p_33021_, RandomSource p_33022_) {
		return p_33019_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_33019_, p_33021_, p_33022_) && checkMobSpawnRules(p_33018_, p_33019_, p_33020_, p_33021_, p_33022_);
	}

	public static class TofuSlimeFloatGoal extends Goal {
		private final Slime slime;

		public TofuSlimeFloatGoal(Slime p_33655_) {
			this.slime = p_33655_;
			this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
			p_33655_.getNavigation().setCanFloat(true);
		}

		public boolean canUse() {
			return (this.slime.isInFluidType()) && this.slime.getMoveControl() instanceof SlimeMoveControl;
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			if (this.slime.getRandom().nextFloat() < 0.8F) {
				this.slime.getJumpControl().jump();
			}

			MoveControl var2 = this.slime.getMoveControl();
			if (var2 instanceof SlimeMoveControl slime$slimemovecontrol) {
				slime$slimemovecontrol.setWantedMovement(1.2);
			}

		}
	}
}
