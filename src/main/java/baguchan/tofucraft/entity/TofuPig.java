package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.IExtensibleEnum;

import javax.annotation.Nullable;

public class TofuPig extends Pig {
	private static final Ingredient FOOD_ITEMS = Ingredient.of(TofuItems.LEEK.get(), Items.CARROT);

	private static final EntityDataAccessor<String> TOFUPIG_TYPE = SynchedEntityData.defineId(TofuPig.class, EntityDataSerializers.STRING);

	public TofuPig(EntityType<? extends Pig> p_29462_, Level p_29463_) {
		super(p_29462_, p_29463_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	public static boolean checkTofuAnimalSpawnRules(EntityType<? extends Animal> p_27578_, LevelAccessor p_27579_, MobSpawnType p_27580_, BlockPos p_27581_, RandomSource p_27582_) {
		return p_27579_.getBlockState(p_27581_.below()).is(TofuTags.Blocks.TOFU_TERRAIN) && p_27579_.getRawBrightness(p_27581_, 0) > 8;
	}

	public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
		ItemStack var3 = p_28298_.getItemInHand(p_28299_);
		if (var3.is(TofuItems.TOFUMETAL.get()) && !this.isBaby() && this.getTofuPigType().equals(TofuPigType.NORMAL)) {
			p_28298_.playSound(SoundEvents.ANVIL_USE, 1.0F, 1.0F);
			var3.shrink(1);
			this.setTofuPigType(TofuPigType.METAL);
			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else if (var3.is(TofuItems.TOFUGRILLED.get()) && !this.isBaby() && this.getTofuPigType().equals(TofuPigType.NORMAL)) {
			p_28298_.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
			var3.shrink(1);
			this.setTofuPigType(TofuPigType.GRILLED);
			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else if (var3.is(TofuItems.TOFUZUNDA.get()) && !this.isBaby() && this.getTofuPigType().equals(TofuPigType.NORMAL)) {
			p_28298_.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
			var3.shrink(1);
			this.setTofuPigType(TofuPigType.ZUNDA);
			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else {
			return super.mobInteract(p_28298_, p_28299_);
		}
	}

	@Nullable
	public Entity getControllingPassenger() {
		Entity entity = this.getFirstPassenger();
		return entity != null && this.canBeControlledBy(entity) ? entity : null;
	}

	private boolean canBeControlledBy(Entity p_218248_) {
		if (this.isSaddled() && p_218248_ instanceof Player player && this.getTofuPigType() == TofuPigType.ZUNDA) {
			return player.getMainHandItem().is(TofuItems.ZUNDAMUSROOM_ON_A_STICK.get()) || player.getOffhandItem().is(TofuItems.ZUNDAMUSROOM_ON_A_STICK.get());
		} else if (this.isSaddled() && p_218248_ instanceof Player player) {
			return player.getMainHandItem().is(Items.CARROT_ON_A_STICK) || player.getOffhandItem().is(Items.CARROT_ON_A_STICK);
		} else {
			return false;
		}
	}

	public void thunderHit(ServerLevel p_29473_, LightningBolt p_29474_) {
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		if (this.getTofuPigType() == TofuPigType.METAL) {
			damage = (float) (damage * 0.5);
		} else if (this.getTofuPigType() == TofuPigType.GRILLED && source.isFire()) {
			return false;
		}
		return super.hurt(source, damage);
	}

	public void setTofuPigType(TofuPigType type) {
		this.entityData.set(TOFUPIG_TYPE, type.name());
	}

	public TofuPig.TofuPigType getTofuPigType() {
		return TofuPig.TofuPigType.get(this.entityData.get(TOFUPIG_TYPE));
	}

	public enum TofuPigType implements IExtensibleEnum {
		NORMAL,
		METAL,
		GRILLED,
		ZUNDA;

		private TofuPigType() {

		}

		public static TofuPig.TofuPigType get(String nameIn) {
			for (TofuPig.TofuPigType role : values()) {
				if (role.name().equals(nameIn))
					return role;
			}
			return NORMAL;
		}

		public static TofuPig.TofuPigType create(String name) {
			throw new IllegalStateException("Enum not extended");
		}
	}

	@Override
	public TofuPig getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
		return TofuEntityTypes.TOFUPIG.get().create(p_148890_);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("TofuPigType", getTofuPigType().name());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("TofuPigType")) {
			setTofuPigType(TofuPig.TofuPigType.get(compound.getString("TofuPigType")));
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TOFUPIG_TYPE, TofuPig.TofuPigType.NORMAL.name());
	}

	public float getSteeringSpeed() {
		return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.35F;
	}

	public boolean isFood(ItemStack p_27600_) {
		return FOOD_ITEMS.test(p_27600_);
	}
}