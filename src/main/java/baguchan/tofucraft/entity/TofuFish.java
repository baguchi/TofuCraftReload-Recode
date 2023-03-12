package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Optional;

public class TofuFish extends AbstractTofuFish {

	public TofuFish(EntityType<? extends TofuFish> p_27523_, Level p_27524_) {
		super(p_27523_, p_27524_);
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(TofuItems.TOFUFISH_BUCKET.get());
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.COD_AMBIENT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.COD_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource p_28281_) {
		return SoundEvents.COD_HURT;
	}


	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}


	protected InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_) {
		return bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
	}

	static <T extends LivingEntity & Bucketable> Optional<InteractionResult> bucketMobPickup(Player p_148829_, InteractionHand p_148830_, T p_148831_) {
		ItemStack itemstack = p_148829_.getItemInHand(p_148830_);
		if (itemstack.getItem() == Items.WATER_BUCKET && p_148831_.isAlive()) {
			p_148831_.playSound(p_148831_.getPickupSound(), 1.0F, 1.0F);
			ItemStack itemstack1 = p_148831_.getBucketItemStack();
			p_148831_.saveToBucketTag(itemstack1);
			ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_148829_, itemstack1, false);
			p_148829_.setItemInHand(p_148830_, itemstack2);
			Level level = p_148831_.level;
			if (!level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) p_148829_, itemstack1);
			}

			p_148831_.discard();
			return Optional.of(InteractionResult.sidedSuccess(level.isClientSide));
		} else if (itemstack.getItem() == TofuItems.BUCKET_SOYMILK.get() && p_148831_.isAlive()) {
			p_148831_.playSound(p_148831_.getPickupSound(), 1.0F, 1.0F);
			ItemStack itemstack1 = TofuItems.TOFUFISH_SOYMILK_BUCKET.get().getDefaultInstance();
			p_148831_.saveToBucketTag(itemstack1);
			ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_148829_, itemstack1, false);
			p_148829_.setItemInHand(p_148830_, itemstack2);
			Level level = p_148831_.level;
			if (!level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) p_148829_, itemstack1);
			}

			p_148831_.discard();
			return Optional.of(InteractionResult.sidedSuccess(level.isClientSide));
		} else {
			return Bucketable.bucketMobPickup(p_148829_, p_148830_, p_148831_);
		}
	}

	public static boolean checkTofuFishSpawnRules(EntityType<? extends AbstractFish> p_27468_, LevelAccessor p_27469_, MobSpawnType p_27470_, BlockPos p_27471_, RandomSource p_27472_) {
		return p_27469_.getBlockState(p_27471_).is(TofuBlocks.SOYMILK.get()) && p_27469_.getBlockState(p_27471_.above()).is(TofuBlocks.SOYMILK.get());
	}
}
