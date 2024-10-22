package baguchi.tofucraft.block.tfenergy;

import baguchi.tofucraft.api.tfenergy.IEnergyContained;
import baguchi.tofucraft.api.tfenergy.TFEnergyData;
import baguchi.tofucraft.registry.TofuDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.BaseEntityBlock;

import java.util.List;

public abstract class TFBaseEntityBlock extends BaseEntityBlock implements IEnergyContained {
	protected TFBaseEntityBlock(Properties p_49224_) {
		super(p_49224_);
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

	@Override
	public void appendHoverText(ItemStack p_43094_, Item.TooltipContext p_339613_, List<Component> p_43096_, TooltipFlag p_43097_) {
		super.appendHoverText(p_43094_, p_339613_, p_43096_, p_43097_);
		p_43096_.add(Component.translatable("tooltip.tofucraft.energy", getEnergy(p_43094_), getEnergyMax(p_43094_)));

	}
}
