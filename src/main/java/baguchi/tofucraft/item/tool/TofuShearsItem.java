package baguchi.tofucraft.item.tool;

import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;

public class TofuShearsItem extends ShearsItem implements IEnergyInsertable {
	public TofuShearsItem(Properties p_43074_) {
		super(p_43074_);
	}

	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.min(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return calculated * 5;
			}
		}
		return 0;
	}
}
