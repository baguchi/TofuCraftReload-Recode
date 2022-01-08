package baguchan.tofucraft.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class StackableBowlItem extends Item {
	public StackableBowlItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
		super.finishUsingItem(p_41409_, p_41410_, p_41411_);
		if (p_41411_ instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) p_41411_;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, p_41409_);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		if (p_41411_ instanceof Player && !((Player) p_41411_).getAbilities().instabuild) {
			p_41409_.shrink(1);
		}

		if (p_41409_.isEmpty())
			return new ItemStack(Items.BOWL);
		if (p_41411_ instanceof Player && !((Player) p_41411_).getAbilities().instabuild) {
			ItemStack itemstack = new ItemStack(Items.BOWL);
			Player playerentity = (Player) p_41411_;
			if (!playerentity.getInventory().add(itemstack)) {
				playerentity.drop(itemstack, false);
			}
		}
		return p_41409_;
	}


	public int getUseDuration(ItemStack p_77626_1_) {
		return 32;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		return UseAnim.EAT;
	}
}

