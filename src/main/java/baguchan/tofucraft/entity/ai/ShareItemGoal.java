package baguchan.tofucraft.entity.ai;

import baguchan.tofucraft.entity.TofunianEntity;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Item;

public class ShareItemGoal extends Goal {
	private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).func_221013_a(8.0D).func_221008_a().func_221011_b().func_221014_c();

	protected final TofunianEntity tofunian;

	protected final double speedModifier;

	protected int nextStartTick;

	protected TofunianEntity partner;

	private boolean hasPassed;

	public ShareItemGoal(TofunianEntity entity, double speed) {
		this.tofunian = entity;
		this.speedModifier = speed;
		func_220684_a(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (this.nextStartTick > 0) {
			this.nextStartTick--;
			return false;
		}
		if (this.tofunian.level.isDay() && !this.tofunian.func_70631_g_() && this.tofunian.hasExcessFood()) {
			this.nextStartTick = this.tofunian.func_70681_au().nextInt(60) + 200;
			this.partner = getFreePartner();
			return (this.partner != null);
		}
		return false;
	}

	public boolean canContinueToUse() {
		return (!this.hasPassed && this.partner != null && this.partner.func_70089_S());
	}

	public void start() {
		this.partner = null;
		this.hasPassed = false;
	}

	@Nullable
	private TofunianEntity getFreePartner() {
		List<TofunianEntity> list = this.tofunian.level.func_217374_a(TofunianEntity.class, PARTNER_TARGETING, (LivingEntity) this.tofunian, this.tofunian.func_174813_aQ().func_186662_g(8.0D));
		double d0 = Double.MAX_VALUE;
		TofunianEntity tofunian2 = null;
		for (TofunianEntity tofunianEntity1 : list) {
			if (tofunianEntity1.wantsMoreFood() && this.tofunian.func_70068_e((Entity) tofunianEntity1) < d0) {
				tofunian2 = tofunianEntity1;
				d0 = this.tofunian.func_70068_e((Entity) tofunianEntity1);
			}
		}
		return tofunian2;
	}

	public void tick() {
		super.tick();
		if (this.tofunian.func_70685_l((Entity) this.partner)) {
			if (!this.hasPassed) {
				this.tofunian.func_70661_as().func_75497_a((Entity) this.partner, 2.0D);
				this.tofunian.getLookControl().func_75651_a((Entity) this.partner, 30.0F, 30.0F);
				throwHalfStack(this.tofunian, TofunianEntity.FOOD_POINTS.keySet(), (LivingEntity) this.partner);
				this.hasPassed = true;
			}
		} else {
			this.hasPassed = true;
		}
	}

	private static void throwHalfStack(TofunianEntity p_220586_0_, Set<Item> p_220586_1_, LivingEntity p_220586_2_) {
		// Byte code:
		//   0: aload_0
		//   1: invokevirtual func_213715_ed : ()Lnet/minecraft/inventory/Inventory;
		//   4: astore_3
		//   5: getstatic net/minecraft/item/ItemStack.field_190927_a : Lnet/minecraft/item/ItemStack;
		//   8: astore #4
		//   10: iconst_0
		//   11: istore #5
		//   13: iload #5
		//   15: aload_3
		//   16: invokevirtual func_70302_i_ : ()I
		//   19: if_icmpge -> 135
		//   22: aload_3
		//   23: iload #5
		//   25: invokevirtual func_70301_a : (I)Lnet/minecraft/item/ItemStack;
		//   28: astore #6
		//   30: aload #6
		//   32: invokevirtual func_190926_b : ()Z
		//   35: ifne -> 106
		//   38: aload #6
		//   40: invokevirtual func_77973_b : ()Lnet/minecraft/item/Item;
		//   43: astore #7
		//   45: aload_1
		//   46: aload #7
		//   48: invokeinterface contains : (Ljava/lang/Object;)Z
		//   53: ifeq -> 106
		//   56: aload #6
		//   58: invokevirtual func_190916_E : ()I
		//   61: aload #6
		//   63: invokevirtual func_77976_d : ()I
		//   66: iconst_2
		//   67: idiv
		//   68: if_icmple -> 83
		//   71: aload #6
		//   73: invokevirtual func_190916_E : ()I
		//   76: iconst_2
		//   77: idiv
		//   78: istore #8
		//   80: goto -> 112
		//   83: aload #6
		//   85: invokevirtual func_190916_E : ()I
		//   88: bipush #24
		//   90: if_icmple -> 106
		//   93: aload #6
		//   95: invokevirtual func_190916_E : ()I
		//   98: bipush #24
		//   100: isub
		//   101: istore #8
		//   103: goto -> 112
		//   106: iinc #5, 1
		//   109: goto -> 13
		//   112: aload #6
		//   114: iload #8
		//   116: invokevirtual func_190918_g : (I)V
		//   119: new net/minecraft/item/ItemStack
		//   122: dup
		//   123: aload #7
		//   125: iload #8
		//   127: invokespecial <init> : (Lnet/minecraft/util/IItemProvider;I)V
		//   130: astore #4
		//   132: goto -> 135
		//   135: aload #4
		//   137: invokevirtual func_190926_b : ()Z
		//   140: ifne -> 153
		//   143: aload_0
		//   144: aload #4
		//   146: aload_2
		//   147: invokevirtual func_213303_ch : ()Lnet/minecraft/util/math/vector/Vector3d;
		//   150: invokestatic func_233865_a_ : (Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/math/vector/Vector3d;)V
		//   153: return
		// Line number table:
		//   Java source line number -> byte code offset
		//   #87	-> 0
		//   #88	-> 5
		//   #89	-> 10
		//   #91	-> 13
		//   #97	-> 22
		//   #98	-> 30
		//   #99	-> 38
		//   #100	-> 45
		//   #101	-> 56
		//   #102	-> 71
		//   #103	-> 80
		//   #106	-> 83
		//   #107	-> 93
		//   #108	-> 103
		//   #113	-> 106
		//   #114	-> 109
		//   #117	-> 112
		//   #118	-> 119
		//   #119	-> 132
		//   #122	-> 135
		//   #123	-> 143
		//   #125	-> 153
		// Local variable table:
		//   start	length	slot	name	descriptor
		//   80	3	8	j	I
		//   45	61	7	item	Lnet/minecraft/item/Item;
		//   103	3	8	j	I
		//   30	105	6	itemstack1	Lnet/minecraft/item/ItemStack;
		//   112	23	7	item	Lnet/minecraft/item/Item;
		//   112	23	8	j	I
		//   0	154	0	p_220586_0_	Lbaguchan/tofucraft/entity/TofunianEntity;
		//   0	154	1	p_220586_1_	Ljava/util/Set;
		//   0	154	2	p_220586_2_	Lnet/minecraft/entity/LivingEntity;
		//   5	149	3	inventory	Lnet/minecraft/inventory/Inventory;
		//   10	144	4	itemstack	Lnet/minecraft/item/ItemStack;
		//   13	141	5	i	I
		// Local variable type table:
		//   start	length	slot	name	signature
		//   0	154	1	p_220586_1_	Ljava/util/Set<Lnet/minecraft/item/Item;>;
	}
}
