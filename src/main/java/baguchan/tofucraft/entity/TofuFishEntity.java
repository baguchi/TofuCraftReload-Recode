package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class TofuFishEntity extends AbstractGroupFishEntity {
	public TofuFishEntity(EntityType<? extends TofuFishEntity> p_i50279_1_, World p_i50279_2_) {
		super(p_i50279_1_, p_i50279_2_);
	}

	public static boolean canTofuFishSpawnOn(EntityType<? extends AbstractFishEntity> type, IWorld worldIn, SpawnReason reason, BlockPos p_223363_3_, Random randomIn) {
		return (worldIn.getBlockState(p_223363_3_).is(TofuBlocks.SOYMILK) && worldIn.getBlockState(p_223363_3_.above()).is(TofuBlocks.SOYMILK));
	}

	protected ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
		if (itemstack.getItem() == TofuItems.BUCKET_SOYMILK && this.isAlive()) {
			this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
			itemstack.shrink(1);
			ItemStack itemstack1 = TofuItems.TOFUFISH_SOYMILK_BUCKET.getDefaultInstance();
			this.saveToBucketTag(itemstack1);
			if (!this.level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) p_230254_1_, itemstack1);
			}

			if (itemstack.isEmpty()) {
				p_230254_1_.setItemInHand(p_230254_2_, itemstack1);
			} else if (!p_230254_1_.inventory.add(itemstack1)) {
				p_230254_1_.drop(itemstack1, false);
			}

			this.remove();
			return ActionResultType.sidedSuccess(this.level.isClientSide);
		} else {
			return super.mobInteract(p_230254_1_, p_230254_2_);
		}
	}

	protected ItemStack getBucketItemStack() {
		return new ItemStack(TofuItems.TOFUFISH_BUCKET);
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.COD_AMBIENT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.COD_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return SoundEvents.COD_HURT;
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}
}
