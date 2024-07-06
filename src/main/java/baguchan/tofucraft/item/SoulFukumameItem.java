package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class SoulFukumameItem extends Item implements ProjectileItem {
	public SoulFukumameItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		levelIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!levelIn.isClientSide) {
			for (int i = 0; i < 5; i++) {
				SoulFukumameEntity fukumamentity = new SoulFukumameEntity(levelIn, playerIn, itemstack);
				fukumamentity.damage = 2.0F;
				fukumamentity.damage += EnchantmentHelper.getEnchantmentLevel(levelIn.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.POWER), playerIn) * 0.5F;
				float d0 = i * levelIn.random.nextFloat() * 10.0F - 5.0F * i;
				fukumamentity.shootFromRotation(playerIn, playerIn.getXRot() + d0 * 0.325F, playerIn.getYRot() + d0, 0.0F, 1.5F, 0.8F);
				levelIn.addFreshEntity(fukumamentity);
			}
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(itemstack.getItem(), 10);
		if (!playerIn.level().isClientSide)
			itemstack.hurtAndBreak(1, (LivingEntity) playerIn, LivingEntity.getSlotForHand(handIn));
		return InteractionResultHolder.sidedSuccess(itemstack, levelIn.isClientSide());
	}
	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return 3;
	}

	@Override
	public Projectile asProjectile(Level p_338465_, Position p_338661_, ItemStack p_338506_, Direction p_338517_) {
		SoulFukumameEntity thrownpotion = new SoulFukumameEntity(p_338465_, p_338661_.x(), p_338661_.y(), p_338661_.z());
		return thrownpotion;
	}
}
