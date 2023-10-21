package baguchan.tofucraft.item;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.registry.TofuArmorMaterial;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TofuArmorItem extends ArmorItem implements IEnergyInsertable {
	public static final String TAG_TF = "tf_energy";
	public static final String TAG_TFMAX = "tf_energymax";

	public TofuArmorItem(TofuArmorMaterial tofuArmorMaterial, ArmorItem.Type type, Item.Properties properties) {
		super(tofuArmorMaterial, type, properties);
	}

	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.max(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return energy;
			}
		}
		return 0;
	}
}