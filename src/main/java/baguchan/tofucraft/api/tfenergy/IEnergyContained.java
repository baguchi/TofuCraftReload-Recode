package baguchan.tofucraft.api.tfenergy;

import net.minecraft.world.item.ItemStack;

public interface IEnergyContained {
	int getEnergy(ItemStack inst);

	int getEnergyMax(ItemStack inst);

	void setEnergy(ItemStack inst, int amount);

	void setEnergyMax(ItemStack inst, int amount);
}