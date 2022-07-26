package baguchan.tofucraft.entity;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TofuGandlem extends Monster {
	public final AnimationState attackAnimationState = new AnimationState();

	public TofuGandlem(EntityType<? extends TofuGandlem> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.moveControl = new FlyingMoveControl(this, 20, false);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(8, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(9, new RandomStrollGoal(this, 0.9D));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Mob.class, 6.0F));

		this.goalSelector.addGoal(12, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractTofunian.class, true));

	}

	protected PathNavigation createNavigation(Level p_218342_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 5) {
			this.attackAnimationState.start(this.tickCount);
		} else {
			super.handleEntityEvent(p_70103_1_);
		}

	}


	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 300.0D).add(Attributes.MOVEMENT_SPEED, 0.26D).add(Attributes.KNOCKBACK_RESISTANCE, 0.9D).add(Attributes.ARMOR, 10.0F).add(Attributes.ARMOR_TOUGHNESS, 0.5F).add(Attributes.ATTACK_DAMAGE, 10.0D);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}
}
