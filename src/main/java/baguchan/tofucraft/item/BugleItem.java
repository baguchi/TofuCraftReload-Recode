package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BugleItem extends Item {
	public BugleItem(Properties tab) {
		super(tab);
	}

	@Override
	public void releaseUsing(ItemStack p_41412_, Level p_41413_, LivingEntity p_41414_, int p_41415_) {
		int i = getUseDuration(p_41412_) - p_41415_;

		if (p_41414_ instanceof Player) {
			Player playerentity = (Player) p_41414_;
			if (i >= 20) {
				playerentity.getCooldowns().addCooldown(this, 80);
			}
		}
		if (i >= 20)
			p_41414_.playSound(TofuSounds.TOFUBUGLE, 3.0F, 1.0F);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		ItemStack itemstack = p_41433_.getItemInHand(p_41434_);
		p_41433_.startUsingItem(p_41434_);

		return InteractionResultHolder.success(itemstack);
	}

	public int getUseDuration(ItemStack p_77626_1_) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		return UseAnim.BOW;
	}
}
