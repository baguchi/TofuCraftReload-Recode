package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class TofuEnchantments {
	public static final ResourceKey<Enchantment> BATCH = key("batch");
	public static final ResourceKey<Enchantment> EFFECT_PROTECTION = key("effect_protection");
	public static final ResourceKey<Enchantment> DRAIN = key("drain");

	private static ResourceKey<Enchantment> key(String p_345314_) {
		return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_345314_));
	}

	public static void bootstrap(BootstrapContext<Enchantment> p_345935_) {
		HolderGetter<DamageType> holdergetter = p_345935_.lookup(Registries.DAMAGE_TYPE);
		HolderGetter<Enchantment> holdergetter1 = p_345935_.lookup(Registries.ENCHANTMENT);
		HolderGetter<Item> holdergetter2 = p_345935_.lookup(Registries.ITEM);
		HolderGetter<Block> holdergetter3 = p_345935_.lookup(Registries.BLOCK);
		register(
				p_345935_,
				EFFECT_PROTECTION,
				Enchantment.enchantment(
						Enchantment.definition(
								holdergetter2.getOrThrow(TofuTags.Items.TOFU_DIAMOND_ARMOR_ENCHANTABLE),
								2,
								1,
								Enchantment.dynamicCost(25, 25),
								Enchantment.dynamicCost(75, 25),
								4,
								EquipmentSlotGroup.ARMOR
						)
				)
		);
		register(
				p_345935_,
				BATCH,
				Enchantment.enchantment(
						Enchantment.definition(
								holdergetter2.getOrThrow(TofuTags.Items.TOFU_DIAMOND_MINEABLE_ENCHANTABLE),
								1,
								1,
								Enchantment.dynamicCost(25, 25),
								Enchantment.dynamicCost(75, 25),
								6,
								EquipmentSlotGroup.MAINHAND
						)
				)
		);
		register(
				p_345935_,
				DRAIN,
				Enchantment.enchantment(
						Enchantment.definition(
								holdergetter2.getOrThrow(TofuTags.Items.TOFU_DIAMOND_SWORD_ENCHANTABLE),
								1,
								1,
								Enchantment.dynamicCost(25, 25),
								Enchantment.dynamicCost(75, 25),
								6,
								EquipmentSlotGroup.MAINHAND
						)
				)
		);
	}

	private static void register(BootstrapContext<Enchantment> p_346019_, ResourceKey<Enchantment> p_345703_, Enchantment.Builder p_345607_) {
		p_346019_.register(p_345703_, p_345607_.build(p_345703_.location()));
	}
}