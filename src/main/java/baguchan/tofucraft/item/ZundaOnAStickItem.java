package baguchan.tofucraft.item;

import baguchan.tofucraft.entity.ItemInteractable;
import baguchan.tofucraft.entity.TofuPig;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ZundaOnAStickItem<T extends Entity & ItemSteerable> extends Item {
	private final EntityType<T> canInteractWith;
	private final int consumeItemDamage;

	public ZundaOnAStickItem(Properties p_41383_, EntityType<T> canInteractWith, int consumeItemDamage) {
		super(p_41383_);
		this.canInteractWith = canInteractWith;
		this.consumeItemDamage = consumeItemDamage;
	}

	public InteractionResultHolder<ItemStack> use(Level p_41314_, Player p_41315_, InteractionHand p_41316_) {
		ItemStack itemstack = p_41315_.getItemInHand(p_41316_);
		if (p_41314_.isClientSide) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			Entity entity = p_41315_.getVehicle();
			if (p_41315_.isPassenger() && entity instanceof ItemInteractable && entity.getType() == this.canInteractWith && entity instanceof TofuPig pig) {
				ItemInteractable itemInteractable = (ItemInteractable) entity;
				if (pig.getTofuPigType() == TofuPig.TofuPigType.ZUNDA && itemInteractable.canHeal()) {
					p_41315_.getCooldowns().addCooldown(itemstack.getItem(), 200);
					itemstack.hurtAndBreak(this.consumeItemDamage, p_41315_, (p_41312_) -> {
						p_41312_.broadcastBreakEvent(p_41316_);
					});
					if (itemstack.isEmpty()) {
						ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);
						itemstack1.setTag(itemstack.getTag());
						return InteractionResultHolder.success(itemstack1);
					}

					return InteractionResultHolder.success(itemstack);
				}
			}

			p_41315_.awardStat(Stats.ITEM_USED.get(this));
			return InteractionResultHolder.pass(itemstack);
		}
	}
}
