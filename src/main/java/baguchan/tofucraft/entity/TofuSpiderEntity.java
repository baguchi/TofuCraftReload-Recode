package baguchan.tofucraft.entity;

import baguchan.tofucraft.entity.projectile.FukumameEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TofuSpiderEntity extends SpiderEntity implements IRangedAttackMob {
	public TofuSpiderEntity(EntityType<? extends SpiderEntity> p_i48550_1_, World p_i48550_2_) {
		super(p_i48550_1_, p_i48550_2_);
	}

	protected void func_184651_r() {
		this.field_70714_bg.func_75776_a(1, (Goal) new SwimGoal((MobEntity) this));
		this.field_70714_bg.func_75776_a(4, (Goal) new RangedAttackGoal(this, 1.0D, 80, 15.0F));
		this.field_70714_bg.func_75776_a(5, (Goal) new WaterAvoidingRandomWalkingGoal((CreatureEntity) this, 0.8D));
		this.field_70714_bg.func_75776_a(6, (Goal) new LookAtGoal((MobEntity) this, PlayerEntity.class, 8.0F));
		this.field_70714_bg.func_75776_a(6, (Goal) new LookRandomlyGoal((MobEntity) this));
		this.field_70715_bh.func_75776_a(1, (Goal) new HurtByTargetGoal((CreatureEntity) this, new Class[0]));
		this.field_70715_bh.func_75776_a(2, (Goal) new TargetGoal<>(this, PlayerEntity.class));
		this.field_70715_bh.func_75776_a(3, (Goal) new TargetGoal<>(this, IronGolemEntity.class));
	}

	public void func_82196_d(LivingEntity p_82196_1_, float p_82196_2_) {
		playSound(SoundEvents.field_191255_dF, 1.0F, 0.4F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
		for (int i = 0; i < 4; i++) {
			FukumameEntity snowballentity = new FukumameEntity(this.level, (LivingEntity) this);
			double d0 = p_82196_1_.func_226280_cw_() - func_226280_cw_();
			double d1 = p_82196_1_.func_226277_ct_() - func_226277_ct_();
			double d2 = d0;
			double d3 = p_82196_1_.func_226281_cx_() - func_226281_cx_();
			float f = MathHelper.func_76133_a(d1 * d1 + d3 * d3) * 0.2F;
			snowballentity.func_70186_c(d1, d2 + f, d3, 1.0F, 3.0F);
			this.level.func_217376_c((Entity) snowballentity);
		}
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(SpiderEntity p_i45818_1_, Class<T> p_i45818_2_) {
			super((MobEntity) p_i45818_1_, p_i45818_2_, true);
		}

		public boolean canUse() {
			float f = this.field_75299_d.func_70013_c();
			return (f >= 0.5F) ? false : super.canUse();
		}
	}

	protected float func_213348_b(Pose p_213348_1_, EntitySize p_213348_2_) {
		return 0.35F;
	}
}
