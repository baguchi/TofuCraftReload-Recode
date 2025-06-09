package baguchi.tofucraft.item.tool;

import baguchi.tofucraft.api.tfenergy.IEnergyContained;
import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchi.tofucraft.api.tfenergy.TFEnergyData;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuToolMaterials;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.function.Consumer;

public class ZundaAlloySwordItem extends Item implements IEnergyInsertable, IEnergyContained {

	public ZundaAlloySwordItem(Properties p_43089_) {
		super(p_43089_.sword(TofuToolMaterials.ZUNDA_ALLOY, 3, -2.3F));
	}

	@Override
	public InteractionResult use(Level levelIn, Player playerIn, InteractionHand handIn) {

		ItemStack itemstack = playerIn.getItemInHand(handIn);
		if (playerIn.isShiftKeyDown() && getEnergy(itemstack) >= 100) {
			playerIn.awardStat(Stats.ITEM_USED.get(this));
			playerIn.getCooldowns().addCooldown(itemstack, 120);
			drain(itemstack, 100, false);
			playerIn.getData(TofuAttachments.TOFU_LIVING.get()).busterAnimation(playerIn, handIn);
			return InteractionResult.SUCCESS_SERVER;
		} else {
			return super.use(levelIn, playerIn, handIn);
		}
	}

	public int drain(ItemStack inst, int amount, boolean simulate) {
		if (!simulate) {
			int calculated2 = Math.min(getEnergy(inst), amount);
			setEnergy(inst, getEnergy(inst) - calculated2);
			return calculated2;
		}
		return 0;
	}

	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.min(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return calculated * 5;
			} else {
				int calculated2 = Math.min(energy, getEnergyMax(inst) - getEnergy(inst));
				setEnergy(inst, getEnergy(inst) + calculated2);
				return calculated2;
			}
		}
		return 0;
	}

	@Override
	public int getEnergy(ItemStack inst) {
		return inst.get(TofuDataComponents.TF_ENERGY_DATA) != null ? inst.get(TofuDataComponents.TF_ENERGY_DATA).storeTF() : 0;
	}

	@Override
	public int getEnergyMax(ItemStack inst) {
		return inst.get(TofuDataComponents.TF_ENERGY_DATA) != null ? inst.get(TofuDataComponents.TF_ENERGY_DATA).maxTF() : 5000;
	}


	@Override
	public void setEnergy(ItemStack inst, int amount) {
		inst.set(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(amount, getEnergyMax(inst)));
	}

	@Override
	public void setEnergyMax(ItemStack inst, int amount) {
		inst.set(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(this.getEnergy(inst), amount));
	}

	private boolean getShowState(ItemStack stack) {
		return getEnergy(stack) != 0 && Minecraft.getInstance().player != null && Minecraft.getInstance().player.isShiftKeyDown();
	}

	@Override
	public boolean isBarVisible(ItemStack p_150899_) {
		return getShowState(p_150899_) || super.isBarVisible(p_150899_);
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		return getShowState(stack) ? Math.round(((float) getEnergy(stack)) / (float) getEnergyMax(stack) * 13.0F) : super.getBarWidth(stack);
	}

	@Override
	public int getBarColor(ItemStack p_150901_) {
		return getShowState(p_150901_) ? Color.white.getRGB() : super.getBarColor(p_150901_);
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, Item.TooltipContext p_339594_, TooltipDisplay p_399753_, Consumer<Component> p_399884_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_399753_, p_399884_, p_41424_);
		p_399884_.accept(Component.translatable("tooltip.tofucraft.energy", getEnergy(p_41421_), getEnergyMax(p_41421_)));

	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return super.supportsEnchantment(stack, enchantment) || enchantment.is(Enchantments.POWER);
	}
}