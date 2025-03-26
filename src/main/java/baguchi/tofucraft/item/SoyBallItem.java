package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class SoyBallItem extends Item {
	public SoyBallItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		ItemStack stack = p_41433_.getItemInHand(p_41434_);
		p_41433_.getCooldowns().addCooldown(stack, 60);
		return repairTofuArmor(p_41433_, stack) ? InteractionResult.SUCCESS : super.use(p_41432_, p_41433_, p_41434_);
	}

	public static boolean repairTofuArmor(LivingEntity p_371788_, ItemStack stack) {
		for (EquipmentSlot equipmentSlot : EquipmentSlotGroup.ARMOR) {
			ItemStack itemstack = p_371788_.getItemBySlot(equipmentSlot);
			if (itemstack.is(TofuTags.Items.REPAIRABLE_TOFU) && itemstack.isDamaged()) {
				stack.shrink(1);
				p_371788_.playSound(SoundEvents.HONEYCOMB_WAX_ON);
				itemstack.setDamageValue(itemstack.getDamageValue() - 50);
				return true;
			}
		}

		for (EquipmentSlot equipmentSlot : EquipmentSlotGroup.HAND) {
			ItemStack itemstack = p_371788_.getItemBySlot(equipmentSlot);
			if (itemstack.is(TofuTags.Items.REPAIRABLE_TOFU) && itemstack.isDamaged()) {
				stack.shrink(1);
				p_371788_.playSound(SoundEvents.HONEYCOMB_WAX_ON);
				itemstack.setDamageValue(itemstack.getDamageValue() - 50);
				return true;
			}
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, TooltipDisplay p_399753_, Consumer<Component> p_399884_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_399753_, p_399884_, p_41424_);
		p_399884_.accept(Component.translatable("tooltip.tofucraft.soyball"));

	}
}
