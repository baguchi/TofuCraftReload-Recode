package baguchan.tofucraft.api.tfenergy;

import net.minecraft.world.item.ItemStack;

public interface IEnergyInsertable {
	int fill(ItemStack inst, int energy, boolean simulate);
}