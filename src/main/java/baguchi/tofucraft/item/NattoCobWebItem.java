package baguchi.tofucraft.item;

import baguchi.tofucraft.entity.projectile.NattoStringEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NattoCobWebItem extends Item {
	public NattoCobWebItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level levelIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		levelIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!levelIn.isClientSide) {
			NattoStringEntity fukumamentity = new NattoStringEntity(levelIn, playerIn);
			fukumamentity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 0.8F);
			levelIn.addFreshEntity(fukumamentity);
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(itemstack, 20);
		if (!playerIn.isCreative()) {
			itemstack.shrink(1);
		}
		return InteractionResult.SUCCESS_SERVER;
	}
}
