package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyContained;
import baguchan.tofucraft.api.tfenergy.IEnergyExtractable;
import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.api.tfenergy.TFEnergyData;
import baguchan.tofucraft.registry.TofuDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.List;

public class TFBatteryItem extends Item implements IEnergyInsertable, IEnergyContained, IEnergyExtractable {

	public TFBatteryItem(Properties p_43089_) {
		super(p_43089_);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stackBattery = player.getItemInHand(hand);
		ItemStack stack = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
		if (stack.getItem() instanceof IEnergyInsertable energyContained) {
			this.drain(stackBattery, energyContained.fill(stack, 50, false), false);
			return InteractionResultHolder.success(stackBattery);
		}

		return super.use(level, player, hand);
	}

	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		if (!simulate) {
			int calculated2 = Math.min(energy, getEnergyMax(inst) - getEnergy(inst));
			setEnergy(inst, getEnergy(inst) + calculated2);
			return calculated2;
		}
		return 0;
	}

	@Override
	public int getEnergy(ItemStack inst) {
		return inst.get(TofuDataComponents.TF_ENERGY_DATA) != null ? inst.get(TofuDataComponents.TF_ENERGY_DATA).storeTF() : 0;
	}

	@Override
	public int getEnergyMax(ItemStack inst) {
		return inst.get(TofuDataComponents.TF_ENERGY_DATA) != null ? inst.get(TofuDataComponents.TF_ENERGY_DATA).maxTF() : 10000;
	}

	@Override
	public void setEnergy(ItemStack inst, int amount) {
		inst.set(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(amount, this.getEnergyMax(inst)));
	}

	@Override
	public void setEnergyMax(ItemStack inst, int amount) {
		inst.set(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(this.getEnergy(inst), amount));
	}

	private boolean getShowState(ItemStack stack) {
		return getEnergy(stack) != 0;
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
	public void appendHoverText(ItemStack p_43094_, TooltipContext p_339613_, List<Component> p_43096_, TooltipFlag p_43097_) {
		super.appendHoverText(p_43094_, p_339613_, p_43096_, p_43097_);
		p_43096_.add(Component.translatable("tooltip.tofucraft.energy", getEnergy(p_43094_), getEnergyMax(p_43094_)));

	}

	@Override
	public int drain(ItemStack inst, int amount, boolean simulate) {
		if (!simulate) {
			int calculated2 = Math.min(getEnergy(inst), amount);
			setEnergy(inst, getEnergy(inst) - calculated2);
			return calculated2;
		}
		return 0;
	}
}