package baguchi.tofucraft.block.tfenergy;

import baguchi.tofucraft.api.tfenergy.IEnergyContained;
import baguchi.tofucraft.api.tfenergy.TFEnergyData;
import baguchi.tofucraft.registry.TofuDataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;

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
}
