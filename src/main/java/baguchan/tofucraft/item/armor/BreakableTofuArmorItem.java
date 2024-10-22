package baguchan.tofucraft.item.armor;

import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchan.tofucraft.registry.TofuArmorMaterials;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;

public class BreakableTofuArmorItem extends Item implements IEnergyInsertable {
	public static final String TAG_TF = "tf_energy";
	public static final String TAG_TFMAX = "tf_energymax";

	public BreakableTofuArmorItem(TofuArmorMaterials.TofuArmorMaterial tofuArmorMaterial, ArmorType type, Properties properties) {
		super(tofuArmorMaterial.humanoidProperties(properties, type));
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

	@Override
	public int getMaxDamage(ItemStack stack) {
		return 1;
	}
}
