package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyContained;
import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.api.tfenergy.TFEnergyData;
import baguchan.tofucraft.registry.TofuDataComponents;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.awt.*;
import java.util.List;

public class ReflectTofuShieldItem extends ShieldItem implements IEnergyInsertable, IEnergyContained {

	public ReflectTofuShieldItem(Properties p_43089_) {
		super(p_43089_);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
		return itemAbility == ItemAbilities.SHIELD_BLOCK;
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
		return Minecraft.getInstance().player != null && !Minecraft.getInstance().player.isShiftKeyDown() && getEnergy(stack) != 0;
	}

	@Override
	public boolean isBarVisible(ItemStack p_150899_) {
		return getShowState(p_150899_) || super.isBarVisible(p_150899_);
	}

	@Override
	public int getDamage(ItemStack stack) {
		return super.getDamage(stack);
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
	public void appendHoverText(ItemStack p_43094_, TooltipContext p_339613_, List<Component> p_43096_, TooltipFlag p_43097_) {
		super.appendHoverText(p_43094_, p_339613_, p_43096_, p_43097_);
		p_43096_.add(Component.translatable("tooltip.tofucraft.energy", getEnergy(p_43094_), getEnergyMax(p_43094_)));

	}
}