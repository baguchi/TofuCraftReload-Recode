package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class InfernoNetherFukumameItem extends Item implements ProjectileItem {
	public InfernoNetherFukumameItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level levelIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		levelIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!levelIn.isClientSide) {
			for (int i = 0; i < 5; i++) {
				NetherFukumameEntity fukumamentity = new NetherFukumameEntity(levelIn, playerIn, itemstack);
				float d0 = i * levelIn.random.nextFloat() * 10.0F - 5.0F * i;
				fukumamentity.damage += EnchantmentHelper.getEnchantmentLevel(levelIn.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.POWER), playerIn) * 0.5F;
				fukumamentity.shootFromRotation(playerIn, playerIn.getXRot() + d0 * 0.325F, playerIn.getYRot() + d0, 0.0F, 1.5F, 0.8F);
				fukumamentity.igniteForTicks(60);
				levelIn.addFreshEntity(fukumamentity);
			}
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(itemstack, 10);
		if (!playerIn.level().isClientSide)
			itemstack.hurtAndBreak(1, (LivingEntity) playerIn, LivingEntity.getSlotForHand(handIn));
		return InteractionResult.SUCCESS_SERVER;
	}
	@Override
	public Projectile asProjectile(Level p_338465_, Position p_338661_, ItemStack p_338506_, Direction p_338517_) {
		NetherFukumameEntity thrownpotion = new NetherFukumameEntity(p_338465_, p_338661_.x(), p_338661_.y(), p_338661_.z());
		thrownpotion.igniteForTicks(60);
		return thrownpotion;
	}
}
