package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class NetherFukumameItem extends Item {
	public NetherFukumameItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		levelIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!levelIn.isClientSide) {
			for (int i = 0; i < 5; i++) {
				NetherFukumameEntity fukumamentity = new NetherFukumameEntity(levelIn, playerIn);
				float d0 = levelIn.random.nextFloat() * 20.0F - 10.0F;
				fukumamentity.damage += EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER_ARROWS, playerIn) * 0.25F;
				fukumamentity.shootFromRotation(playerIn, playerIn.getXRot() + d0 * 0.325F, playerIn.getYRot() + d0, 0.0F, 1.5F, 0.8F);
				levelIn.addFreshEntity(fukumamentity);
			}
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(itemstack.getItem(), 10);
		if (!playerIn.level.isClientSide)
			itemstack.hurtAndBreak(1, (LivingEntity) playerIn, playerEntity -> playerEntity.broadcastBreakEvent(handIn));
		return InteractionResultHolder.sidedSuccess(itemstack, levelIn.isClientSide());
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.POWER_ARROWS || super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return 3;
	}
}
