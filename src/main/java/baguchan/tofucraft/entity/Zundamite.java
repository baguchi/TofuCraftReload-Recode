package baguchan.tofucraft.entity;

import baguchan.tofucraft.client.particle.ParticleZundaCloud;
import baguchan.tofucraft.registry.TofuParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

public class Zundamite extends Monster {
	public Zundamite(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.xpReward = 3;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TofuSlime.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose p_32604_, EntityDimensions p_32605_) {
		return 0.13F;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 2.0);
	}

	@Override
	public void thunderHit(ServerLevel p_19927_, LightningBolt p_19928_) {
	}

	@Override
	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.EVENTS;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENDERMITE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_32615_) {
		return SoundEvents.ENDERMITE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENDERMITE_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos p_32607_, BlockState p_32608_) {
		this.playSound(SoundEvents.ENDERMITE_STEP, 0.15F, 1.0F);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_32595_) {
		super.readAdditionalSaveData(p_32595_);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_32610_) {
		super.addAdditionalSaveData(p_32610_);
	}

	/*@Override
	public void setYBodyRot(float p_32621_) {
		this.setYRot(p_32621_);
		super.setYBodyRot(p_32621_);
	}*/

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.level().isClientSide) {
			for (int i = 0; i < 2; ++i) {
				double xSpeed = (this.random.nextDouble() - 0.5) * 0.5F;
				double ySpeed = (this.random.nextDouble() - 0.5) * 0.5F;
				double zSpeed = (this.random.nextDouble() - 0.5) * 0.5F;
				this.level().addParticle(new ParticleZundaCloud.CloudData(TofuParticleTypes.ZUNDA_CLOUD.get(), 5f, 10, ParticleZundaCloud.EnumCloudBehavior.GROW, 0.98f), this.getX(), this.getY(), this.getZ(), xSpeed, ySpeed, zSpeed);
			}
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	@Override
	protected Vector3f getPassengerAttachmentPoint(Entity p_295545_, EntityDimensions p_295894_, float p_295337_) {
		return new Vector3f(0.0F, p_295894_.height - 0.0625F * p_295337_, 0.0F);
	}
}
