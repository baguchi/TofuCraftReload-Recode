package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.registry.TofuItemTier;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;

public class TofuShovelItem extends ShovelItem implements IEnergyInsertable {
	public TofuShovelItem(TofuItemTier tofuItemTier, float daamge, float speed, Properties properties) {
		super(tofuItemTier, daamge, speed, properties);
	}


	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.min(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return energy;
			}
		}
		return energy;
	}
}
