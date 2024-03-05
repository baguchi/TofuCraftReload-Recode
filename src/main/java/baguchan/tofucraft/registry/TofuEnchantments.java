package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.enchantment.BatchEnchantment;
import baguchan.tofucraft.enchantment.DrainEnchantment;
import baguchan.tofucraft.item.TofuPickaxeItem;
import baguchan.tofucraft.item.TofuSwordItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuEnchantments {
	public static final DeferredRegister<Enchantment> DEFERRED_REGISTRY_ENCHANTMET = DeferredRegister.create(Registries.ENCHANTMENT, TofuCraftReload.MODID);


	public static final EnchantmentCategory TOFU_DIAMOND_PICKAXE_CATEGORY = EnchantmentCategory.create("tofu_diamond_pickaxe", (item) -> {
		return item instanceof TofuPickaxeItem pickaxeItem && pickaxeItem.getTier() == TofuItemTier.TOFUDIAMOND;
	});
	public static final EnchantmentCategory TOFU_DIAMOND_SWORD_CATEGORY = EnchantmentCategory.create("tofu_diamond_sword", (item) -> {
		return item instanceof TofuSwordItem sword && sword.getTier() == TofuItemTier.TOFUDIAMOND;
	});

	public static final Supplier<Enchantment> BATCH = DEFERRED_REGISTRY_ENCHANTMET.register("batch", () -> new BatchEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
	public static final Supplier<Enchantment> DRAIN = DEFERRED_REGISTRY_ENCHANTMET.register("drain", () -> new DrainEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

}