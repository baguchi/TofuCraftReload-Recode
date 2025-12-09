package baguchi.tofucraft.item.armor;

import baguchi.tofucraft.registry.TofuDataComponents;
import com.google.common.base.Suppliers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BreakableTofuBootsItem extends BreakableTofuArmorItem {
	private final Supplier<ItemAttributeModifiers> defaultModifiers;

	public BreakableTofuBootsItem(ArmorMaterial tofuArmorMaterial, ArmorType type, float reduceFallDamage, Properties properties) {
		super(tofuArmorMaterial, type, properties);
		this.defaultModifiers = Suppliers.memoize(
				() -> {
					ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
					EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
					Identifier resourcelocation = Identifier.withDefaultNamespace("armor." + type.getName());
					itemattributemodifiers$builder.add(
							Attributes.FALL_DAMAGE_MULTIPLIER, new AttributeModifier(resourcelocation, (double) reduceFallDamage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), equipmentslotgroup
					);

					return itemattributemodifiers$builder.build();
				}
		);
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
		return this.defaultModifiers.get();
	}


	public int getUnstability(ItemStack inst) {
		return inst.get(TofuDataComponents.UNSTABILITY) != null ? inst.get(TofuDataComponents.UNSTABILITY) : 0;
	}

	public int getFallDurability(ItemStack inst) {
		return inst.get(TofuDataComponents.MAX_FALL_DURABILITY) != null ? inst.get(TofuDataComponents.MAX_FALL_DURABILITY) : 1;
	}

	private boolean getShowState(ItemStack stack) {
		return getUnstability(stack) != 0;
	}

	@Override
	public boolean isBarVisible(ItemStack p_150899_) {
		return getShowState(p_150899_) || super.isBarVisible(p_150899_);
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		return Math.round(13.0F - (float) getUnstability(stack) * 13.0F / (float) this.getFallDurability(stack));
	}

	@Override
	public int getBarColor(ItemStack p_150901_) {
		return getShowState(p_150901_) ? Color.white.getRGB() : super.getBarColor(p_150901_);
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, TooltipDisplay p_399753_, Consumer<Component> p_399884_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_399753_, p_399884_, p_41424_);
		p_399884_.accept(Component.translatable("tooltip.tofucraft.tofu_boots", getUnstability(p_41421_), getFallDurability(p_41421_)));

	}
}
