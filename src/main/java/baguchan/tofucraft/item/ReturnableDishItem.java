package baguchan.tofucraft.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ReturnableDishItem extends Item {
	private final Item dishItem;

	public ReturnableDishItem(Item dishItem, Properties p_41383_) {
		super(p_41383_);
		this.dishItem = dishItem;
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
		var resultItem = super.finishUsingItem(itemStack, level, livingEntity);
		if (livingEntity instanceof ServerPlayer serverPlayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
			serverPlayer.awardStat(Stats.ITEM_USED.get(this));
		}
		if (itemStack.isEmpty()) {
			resultItem = new ItemStack(dishItem);
		}
		if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
			ItemStack itemstack = new ItemStack(dishItem);
			if (!player.getInventory().add(itemstack)) {
				player.drop(itemstack, false);
			}
		}
		return resultItem;
	}
}
