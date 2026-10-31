package baguchi.tofucraft.data.loot;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class TofuChestLootTables implements LootTableSubProvider {
	private final LootTableSubProvider.Context output;
	private final HolderGetter<Biome> biomes;
	private final HolderGetter<Enchantment> enchantments;
	private final HolderGetter<Instrument> instruments;
	private final HolderGetter<Structure> structures;

	public TofuChestLootTables(LootTableSubProvider.Context output) {
		this.output = output;
		this.biomes = output.lookup(Registries.BIOME);
		this.enchantments = output.lookup(Registries.ENCHANTMENT);
		this.instruments = output.lookup(Registries.INSTRUMENT);
		this.structures = output.lookup(Registries.STRUCTURE);
	}

	public void run() {
		this.output.accept(prefix("chests/tofu_mineshaft"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SHOVEL.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_PICKAXE.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_HELMET.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_BOOTS.get()).setWeight(5))
						.add(EmptyLootItem.emptyItem().setWeight(5))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(4, 6))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 5))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_NUGGET.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 8))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.TOFUGEM.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.TOFU_DIAMOND_NUGGET.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 9))).setWeight(4))
						.add(LootItem.lootTableItem(TofuItems.TOFU_DIAMOND.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))).setWeight(3))
						.add(LootItem.lootTableItem(TofuItems.SOY_FORCE_SHARD.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(2))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(2, 4))
						.add(LootItem.lootTableItem(TofuItems.TOFUCOOKIE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4))).setWeight(10))
						.add(LootItem.lootTableItem(TofuItems.SOYMILK_TEA_BOTTLE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.SOYMILK_STRAWBERRY_BOTTLE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.OKARA_DONUT.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4))).setWeight(10))
						.add(EmptyLootItem.emptyItem().setWeight(5))
				)
		);

		this.output.accept(prefix("chests/tofu_ruins_chest"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SHOVEL.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_AXE.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_PICKAXE.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SWORD.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_HELMET.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_CHESTPLATE.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_LEGGINGS.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_BOOTS.get()).setWeight(5))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(2, 3))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 5))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_NUGGET.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 8))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.TOFUGEM.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))).setWeight(15))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(2, 4))
						.add(LootItem.lootTableItem(TofuItems.TOFUCOOKIE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4))).setWeight(10))
						.add(LootItem.lootTableItem(TofuItems.SOYMILK_BOTTLE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.ZUNDA_ARROW.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 5))).setWeight(5))
						.add(EmptyLootItem.emptyItem().setWeight(4))
				)
		);


		this.output.accept(prefix("chests/tofu_village/soyworker_chest"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(6, 8))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_BUCKET.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.SOYMILK_BOTTLE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))).setWeight(10))
						.add(LootItem.lootTableItem(Items.GLASS_BOTTLE).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))).setWeight(15))
				)
		);
		this.output.accept(prefix("chests/tofu_village/tofu_village_chest"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(6, 10))
						.add(LootItem.lootTableItem(TofuItems.SOYMILK_BOTTLE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))).setWeight(10))
						.add(LootItem.lootTableItem(TofuItems.TOFUCOOKIE.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.ZUNDARUBY.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.LEEK.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(10))
				)
		);
		this.output.accept(prefix("chests/tofu_village/tofusmith_chest"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(6, 8))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))).setWeight(10))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_NUGGET.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.TOFU_ISHI.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))).setWeight(15))
						.add(LootItem.lootTableItem(TofuItems.ZUNDARUBY.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_BUCKET.get()).setWeight(3))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SHOVEL.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_AXE.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_PICKAXE.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SWORD.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SPEAR.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_HELMET.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_CHESTPLATE.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_LEGGINGS.get()).setWeight(5))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_BOOTS.get()).setWeight(5))
				)
		);

		this.output.accept(prefix("chests/tofu_castle/tofu_castle_chest"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.between(3, 4))
						.add(LootItem.lootTableItem(TofuItems.TOFU_DIAMOND.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 6))).setWeight(15))
						.add(LootItem.lootTableItem(TofuBlocks.DIAMOND_TOFU.get()).setWeight(5))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(3))
						.add(LootItem.lootTableItem(TofuItems.ZUNDARUBY.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
						.add(LootItem.lootTableItem(TofuItems.ZUNDA_ARROW.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4)))
						.add(LootItem.lootTableItem(TofuItems.TOFU_DIAMOND_NUGGET.get()).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4))).setWeight(3))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
				)
		);
		this.output.accept(prefix("chests/tofu_castle/tofu_castle_food_container"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(8))
						.add(LootItem.lootTableItem(TofuItems.APRICOT.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
						.add(LootItem.lootTableItem(TofuItems.BOTTLE_SOYSAUSE.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4)))
						.add(LootItem.lootTableItem(TofuItems.ZUNDAMA.get()).setWeight(3)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4)))
						.add(LootItem.lootTableItem(TofuItems.ZUNDA.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4)))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(2))
						.add(LootItem.lootTableItem(TofuBlocks.SAPLING_APRICOT.get()).setWeight(5)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
				)
		);
		this.output.accept(prefix("chests/tofu_castle/tofu_castle_hidden_bridge"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(4))
						.add(LootItem.lootTableItem(TofuItems.TOFU_SESAME.get()).setWeight(15)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 5)))
						.add(LootItem.lootTableItem(TofuItems.TOFU_STRAWBERRY.get()).setWeight(15)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 5)))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(3))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL.get()).setWeight(15)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
						.add(LootItem.lootTableItem(TofuItems.ZUNDARUBY.get()).setWeight(10)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_NUGGET.get()).setWeight(15)).apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 5)))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_HELMET.get()).apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(15))))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_CHESTPLATE.get()).apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(15))))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_LEGGINGS.get()).apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(15))))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_BOOTS.get()).apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(15))))
				).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SWORD.get()).apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(15))))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_AXE.get()).apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(15))))
						.add(LootItem.lootTableItem(TofuItems.TOFU_METAL_SPEAR.get()).apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(15)))))
		);
	}

	protected ResourceKey<LootTable> prefix(String name) {
		return ResourceKey.create(Registries.LOOT_TABLE, TofuCraftReload.prefix(name));
	}
}
