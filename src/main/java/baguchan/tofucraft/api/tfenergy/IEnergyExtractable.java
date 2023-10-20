package baguchan.tofucraft.api.tfenergy;

import net.minecraft.world.item.ItemStack;

public interface IEnergyExtractable {
	int drain(ItemStack inst, int amount, boolean simulate);
}