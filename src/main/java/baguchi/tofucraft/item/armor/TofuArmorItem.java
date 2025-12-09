package baguchi.tofucraft.item.armor;

import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class TofuArmorItem extends Item implements IEnergyInsertable {
	public static final String TAG_TF = "tf_energy";
	public static final String TAG_TFMAX = "tf_energymax";

	public TofuArmorItem(ArmorMaterial tofuArmorMaterial, ArmorType type, Properties properties) {
		super(properties.humanoidArmor(tofuArmorMaterial, type).attributes(createAttributes(tofuArmorMaterial, type)));
	}

	public static ItemAttributeModifiers createAttributes(ArmorMaterial armorMaterial, ArmorType p_371239_) {
		int i = (Integer) armorMaterial.defense().getOrDefault(p_371239_, 0);
		ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
		EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(p_371239_.getSlot());
		Identifier resourcelocation = Identifier.withDefaultNamespace("armor." + p_371239_.getName());
		itemattributemodifiers$builder.add(Attributes.ARMOR, new AttributeModifier(resourcelocation, (double) i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
		itemattributemodifiers$builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, (double) armorMaterial.toughness(), AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
		if (armorMaterial.knockbackResistance() > 0.0F) {
			itemattributemodifiers$builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourcelocation, (double) armorMaterial.knockbackResistance(), AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
		}
		//itemattributemodifiers$builder.add(Attributes., new AttributeModifier(resourcelocation, (double)armorMaterial.toughness(), AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);

		return itemattributemodifiers$builder.build();
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
