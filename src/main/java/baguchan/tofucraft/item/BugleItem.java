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

	public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) {
		int i = func_77626_a(p_77615_1_) - p_77615_4_;
		if (p_77615_3_ instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) p_77615_3_;
			playerentity.getCooldowns().addCooldown(this, 80);
		}
		if (i >= 20)
			p_77615_3_.playSound(TofuSounds.TOFUBUGLE, 3.0F, 1.0F);
	}

	public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
		ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
		p_77659_2_.func_184598_c(p_77659_3_);
		return ActionResult.func_226249_b_(itemstack);
	}

	public int func_77626_a(ItemStack p_77626_1_) {
		return 72000;
	}

	public UseAction func_77661_b(ItemStack p_77661_1_) {
		return UseAction.BOW;
	}
}
