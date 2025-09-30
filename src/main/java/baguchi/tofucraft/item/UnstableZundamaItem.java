package baguchi.tofucraft.item;

import baguchi.tofucraft.entity.projectile.UnstableZundamaEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class UnstableZundamaItem extends Item implements ProjectileItem {
	public UnstableZundamaItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level levelIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		levelIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!levelIn.isClientSide()) {
			UnstableZundamaEntity fukumamentity = new UnstableZundamaEntity(levelIn, playerIn, itemstack);
			fukumamentity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 0.8F);
			levelIn.addFreshEntity(fukumamentity);
		}
		playerIn.awardStat(Stats.ITEM_USED.get(this));
		playerIn.getCooldowns().addCooldown(itemstack, 8);
		if (!playerIn.isCreative()) {
			itemstack.shrink(1);
		}
		return InteractionResult.SUCCESS_SERVER;
	}

	@Override
	public Projectile asProjectile(Level p_338465_, Position p_338661_, ItemStack p_338506_, Direction p_338517_) {
		UnstableZundamaEntity thrownpotion = new UnstableZundamaEntity(p_338465_, p_338661_.x(), p_338661_.y(), p_338661_.z(), p_338506_);
		return thrownpotion;
	}
}
