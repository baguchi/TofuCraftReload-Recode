package baguchi.tofucraft.item.armor;

import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

public class BreakableTofuArmorItem extends Item implements IEnergyInsertable {
	public static final String TAG_TF = "tf_energy";
	public static final String TAG_TFMAX = "tf_energymax";

	public BreakableTofuArmorItem(ArmorMaterial tofuArmorMaterial, ArmorType type, Properties properties) {
		super(tofuArmor(properties, tofuArmorMaterial, type));
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

	public static Properties tofuArmor(Properties properties, ArmorMaterial p_394389_, ArmorType p_393823_) {
		return properties.durability(1).attributes(p_394389_.createAttributes(p_393823_)).enchantable(p_394389_.enchantmentValue()).component(DataComponents.EQUIPPABLE, Equippable.builder(p_393823_.getSlot()).setEquipSound(p_394389_.equipSound()).setAsset(p_394389_.assetId()).build()).repairable(p_394389_.repairIngredient());
	}
}
