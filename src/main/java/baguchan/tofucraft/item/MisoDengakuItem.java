package baguchan.tofucraft.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MisoDengakuItem extends Item {
	public MisoDengakuItem(Properties properties) {
		super(properties);
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
		super.finishUsingItem(itemStack, level, livingEntity);
		if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
			var stickItem = new ItemStack(Items.STICK);
			if (!player.getInventory().add(stickItem)) {
				player.drop(itemStack, false);
			}
		}
		return itemStack;
	}
}
