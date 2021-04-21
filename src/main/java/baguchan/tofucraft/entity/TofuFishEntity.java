package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;

import java.util.Random;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class TofuFishEntity extends AbstractGroupFishEntity {
	public TofuFishEntity(EntityType<? extends TofuFishEntity> p_i50279_1_, World p_i50279_2_) {
		super(p_i50279_1_, p_i50279_2_);
	}

	public static boolean canTofuFishSpawnOn(EntityType<? extends AbstractFishEntity> type, IWorld worldIn, SpawnReason reason, BlockPos p_223363_3_, Random randomIn) {
		return (worldIn.getBlockState(p_223363_3_).func_203425_a(TofuBlocks.SOYMILK) && worldIn.getBlockState(p_223363_3_.func_177984_a()).func_203425_a(TofuBlocks.SOYMILK));
	}

	protected ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.func_184586_b(p_230254_2_);
		if (itemstack.func_77973_b() == TofuItems.BUCKET_SOYMILK && func_70089_S()) {
			playSound(SoundEvents.field_203814_aa, 1.0F, 1.0F);
			itemstack.func_190918_g(1);
			ItemStack itemstack1 = new ItemStack((IItemProvider) TofuItems.TOFUFISH_SOYMILK_BUCKET);
			func_204211_f(itemstack1);
			if (!this.level.field_72995_K)
				CriteriaTriggers.field_204813_j.func_204817_a((ServerPlayerEntity) p_230254_1_, itemstack1);
			if (itemstack.func_190926_b()) {
				p_230254_1_.func_184611_a(p_230254_2_, itemstack1);
			} else if (!p_230254_1_.field_71071_by.func_70441_a(itemstack1)) {
				p_230254_1_.func_71019_a(itemstack1, false);
			}
			func_70106_y();
			return ActionResultType.func_233537_a_(this.level.field_72995_K);
		}
		return super.func_230254_b_(p_230254_1_, p_230254_2_);
	}

	protected ItemStack func_203707_dx() {
		return new ItemStack((IItemProvider) TofuItems.TOFUFISH_BUCKET);
	}

	protected SoundEvent func_184639_G() {
		return SoundEvents.field_203815_ax;
	}

	protected SoundEvent func_184615_bR() {
		return SoundEvents.field_203816_ay;
	}

	protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
		return SoundEvents.field_203813_aA;
	}

	protected SoundEvent func_203701_dz() {
		return SoundEvents.field_203818_az;
	}
}
