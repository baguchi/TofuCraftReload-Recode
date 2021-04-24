package baguchan.tofucraft.item;

import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BugleItem extends Item {
	public BugleItem(Properties tab) {
		super(tab);
	}

	public void releaseUsing(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) {
		int i = getUseDuration(p_77615_1_) - p_77615_4_;
		if (p_77615_3_ instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) p_77615_3_;
			playerentity.getCooldowns().addCooldown(this, 80);
		}
		if (i >= 20)
			p_77615_3_.playSound(TofuSounds.TOFUBUGLE, 3.0F, 1.0F);
	}

	public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
		ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
		p_77659_2_.startUsingItem(p_77659_3_);
		return ActionResult.success(itemstack);
	}

	public int getUseDuration(ItemStack p_77626_1_) {
		return 72000;
	}

	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return UseAction.BOW;
	}
}
