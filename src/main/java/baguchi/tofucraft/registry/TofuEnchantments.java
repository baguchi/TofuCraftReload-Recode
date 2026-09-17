package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public class TofuEnchantments {
	public static final ResourceKey<Enchantment> BATCH = key("batch");
	public static final ResourceKey<Enchantment> EFFECT_PROTECTION = key("effect_protection");
	public static final ResourceKey<Enchantment> DRAIN = key("drain");
	public static final ResourceKey<Enchantment> SHAPED_BEAN = key("shaped_bean");

	private static ResourceKey<Enchantment> key(String p_345314_) {
		return ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, p_345314_));
	}

	public static void bootstrap(BootstrapContext<Enchantment> p_345935_) {
		HolderGetter<DamageType> holdergetter = p_345935_.lookup(Registries.DAMAGE_TYPE);
		HolderGetter<Enchantment> holdergetter1 = p_345935_.lookup(Registries.ENCHANTMENT);
		HolderGetter<EntityType<?>> entityHolder = p_345935_.lookup(Registries.ENTITY_TYPE);
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
								2,
								2,
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
		register(
				p_345935_,
				SHAPED_BEAN,
				Enchantment.enchantment(
								Enchantment.definition(
										holdergetter2.getOrThrow(TofuTags.Items.FUKUMAME_ENCHANTABLE),
										10,
										5,
										Enchantment.dynamicCost(1, 10),
										Enchantment.dynamicCost(16, 10),
										1,
										EquipmentSlotGroup.MAINHAND
								)
						)
						.withEffect(
								EnchantmentEffectComponents.DAMAGE,
								new AddValue(LevelBasedValue.perLevel(0.2F)),
								LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(entityHolder, TofuTags.EntityTypes.FUKUMAME).build()
								)
						)
		);
	}

	private static void register(BootstrapContext<Enchantment> p_346019_, ResourceKey<Enchantment> p_345703_, Enchantment.Builder p_345607_) {
		p_346019_.register(p_345703_, p_345607_.build(p_345703_.identifier()));
	}
}