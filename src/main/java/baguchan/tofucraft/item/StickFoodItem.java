package baguchan.tofucraft.item;

import net.minecraft.world.entity.LivingEntity;
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
		if (this.isEdible()) {
			p_41411_.eat(p_41410_, p_41409_);
			return new ItemStack(Items.STICK);
		}
		return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
	}
}
