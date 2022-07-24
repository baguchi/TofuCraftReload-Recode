package baguchan.tofucraft.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class StickFoodItem extends Item {
	public StickFoodItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
		if (p_41409_.isEmpty())
			return new ItemStack(Items.GLASS_BOTTLE);
		if (p_41411_ instanceof Player && !((Player) p_41411_).getAbilities().instabuild) {
			ItemStack itemstack = new ItemStack(Items.STICK);
			Player playerentity = (Player) p_41411_;
			if (!playerentity.getInventory().add(itemstack)) {
				playerentity.drop(itemstack, false);
			}
		}
		return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
	}
}
