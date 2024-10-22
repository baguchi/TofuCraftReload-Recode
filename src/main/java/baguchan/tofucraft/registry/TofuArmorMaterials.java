package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public interface TofuArmorMaterials {
	TofuArmorMaterial KINU = new TofuArmorMaterial(
			1,
			Util.make(new EnumMap<>(ArmorType.class), p_323384_ -> {
				p_323384_.put(ArmorType.BOOTS, 0);
				p_323384_.put(ArmorType.LEGGINGS, 0);
				p_323384_.put(ArmorType.CHESTPLATE, 0);
				p_323384_.put(ArmorType.HELMET, 0);
				p_323384_.put(ArmorType.BODY, 0);
			}),
			1,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			TofuItems.TOFUKINU,
			ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "kinu"));
	TofuArmorMaterial MOMEN = new TofuArmorMaterial(
			1,
			Util.make(new EnumMap<>(ArmorType.class), p_323384_ -> {
				p_323384_.put(ArmorType.BOOTS, 0);
				p_323384_.put(ArmorType.LEGGINGS, 0);
				p_323384_.put(ArmorType.CHESTPLATE, 0);
				p_323384_.put(ArmorType.HELMET, 0);
				p_323384_.put(ArmorType.BODY, 0);
			}),
			1,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			TofuItems.TOFUMOMEN,
			ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "momen"));
	TofuArmorMaterial METAL = new TofuArmorMaterial(14, Util.make(new EnumMap<>(ArmorType.class), p_323378_ -> {
		p_323378_.put(ArmorType.BOOTS, 2);
		p_323378_.put(ArmorType.LEGGINGS, 5);
		p_323378_.put(ArmorType.CHESTPLATE, 6);
		p_323378_.put(ArmorType.HELMET, 2);
		p_323378_.put(ArmorType.BODY, 6);
	}), 11, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, TofuItems.TOFUMETAL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "metal"));
	TofuArmorMaterial SOLID = new TofuArmorMaterial(10, Util.make(new EnumMap<>(ArmorType.class), p_323383_ -> {
		p_323383_.put(ArmorType.BOOTS, 1);
		p_323383_.put(ArmorType.LEGGINGS, 3);
		p_323383_.put(ArmorType.CHESTPLATE, 5);
		p_323383_.put(ArmorType.HELMET, 2);
		p_323383_.put(ArmorType.BODY, 5);
	}), 9, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, TofuItems.TOFUISHI, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "solid"));
	TofuArmorMaterial DIAMOND = new TofuArmorMaterial(38, Util.make(new EnumMap<>(ArmorType.class), p_323379_ -> {
		p_323379_.put(ArmorType.BOOTS, 4);
		p_323379_.put(ArmorType.LEGGINGS, 6);
		p_323379_.put(ArmorType.CHESTPLATE, 8);
		p_323379_.put(ArmorType.HELMET, 4);
		p_323379_.put(ArmorType.BODY, 18);
	}), 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.05F, TofuItems.TOFUDIAMOND, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "diamond"));

	public record TofuArmorMaterial(
			int durability,
			Map<ArmorType, Integer> defense,
			int enchantmentValue,
			Holder<SoundEvent> equipSound,
			float toughness,
			float knockbackResistance,
			Supplier<Item> repairIngredient,
			ResourceLocation modelId
	) {
		public Item.Properties humanoidProperties(Item.Properties p_371701_, ArmorType p_371660_) {
			return p_371701_.durability(p_371660_.getDurability(this.durability))
					.attributes(this.createAttributes(p_371660_))
					.enchantable(this.enchantmentValue)
					.component(DataComponents.EQUIPPABLE, Equippable.builder(p_371660_.getSlot()).setEquipSound(this.equipSound).setModel(this.modelId).build())
					.repairable(this.repairIngredient.get());
		}

		public Item.Properties animalProperties(Item.Properties p_371435_, HolderSet<EntityType<?>> p_371268_) {
			return p_371435_.durability(ArmorType.BODY.getDurability(this.durability))
					.attributes(this.createAttributes(ArmorType.BODY))
					.repairable(this.repairIngredient.get())
					.component(
							DataComponents.EQUIPPABLE,
							Equippable.builder(EquipmentSlot.BODY).setEquipSound(this.equipSound).setModel(this.modelId).setAllowedEntities(p_371268_).build()
					);
		}

		public Item.Properties animalProperties(Item.Properties p_376672_, Holder<SoundEvent> p_381624_, boolean p_380284_, HolderSet<EntityType<?>> p_376684_) {
			if (p_380284_) {
				p_376672_ = p_376672_.durability(ArmorType.BODY.getDurability(this.durability)).repairable(this.repairIngredient.get());
			}

			return p_376672_.attributes(this.createAttributes(ArmorType.BODY))
					.component(
							DataComponents.EQUIPPABLE,
							Equippable.builder(EquipmentSlot.BODY)
									.setEquipSound(p_381624_)
									.setModel(this.modelId)
									.setAllowedEntities(p_376684_)
									.setDamageOnHurt(p_380284_)
									.build()
					);
		}

		private ItemAttributeModifiers createAttributes(ArmorType p_371239_) {
			int i = this.defense.getOrDefault(p_371239_, 0);
			ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
			EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(p_371239_.getSlot());
			ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + p_371239_.getName());
			itemattributemodifiers$builder.add(
					Attributes.ARMOR, new AttributeModifier(resourcelocation, (double) i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
			);
			itemattributemodifiers$builder.add(
					Attributes.ARMOR_TOUGHNESS,
					new AttributeModifier(resourcelocation, (double) this.toughness, AttributeModifier.Operation.ADD_VALUE),
					equipmentslotgroup
			);
			if (this.knockbackResistance > 0.0F) {
				itemattributemodifiers$builder.add(
						Attributes.KNOCKBACK_RESISTANCE,
						new AttributeModifier(resourcelocation, (double) this.knockbackResistance, AttributeModifier.Operation.ADD_VALUE),
						equipmentslotgroup
				);
			}

			return itemattributemodifiers$builder.build();
		}
	}

}