package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.enchantment.BatchEnchantment;
import baguchan.tofucraft.enchantment.DrainEnchantment;
import baguchan.tofucraft.enchantment.EffectProtectionEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuEnchantments {

	private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

	public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(Registries.ENCHANTMENT, TofuCraftReload.MODID);


	public static final Supplier<Enchantment> BATCH = ENCHANTMENT.register("batch", () -> new BatchEnchantment(Enchantment.definition(TofuTags.Items.TOFU_DIAMOND_MINEABLE_ENCHANTABLE, 1, 2, Enchantment.dynamicCost(20, 15), Enchantment.dynamicCost(50, 15), 4, EquipmentSlot.MAINHAND)));
	public static final Supplier<Enchantment> DRAIN = ENCHANTMENT.register("drain", () -> new DrainEnchantment(Enchantment.definition(TofuTags.Items.TOFU_DIAMOND_SWORD_ENCHANTABLE, 1, 2, Enchantment.dynamicCost(20, 15), Enchantment.dynamicCost(50, 15), 3, EquipmentSlot.MAINHAND)));
	public static final Supplier<Enchantment> EFFECT_PROTECTION = ENCHANTMENT.register("effect_protection", () -> new EffectProtectionEnchantment(Enchantment.definition(TofuTags.Items.TOFU_DIAMOND_ARMOR_ENCHANTABLE, 1, 1, Enchantment.dynamicCost(20, 10), Enchantment.dynamicCost(50, 10), 4, ARMOR_SLOTS)));

}