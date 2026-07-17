package baguchi.tofucraft.data;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.loot.SeedDropModifier;
import baguchi.tofucraft.loot.ZundaModifier;
import baguchi.tofucraft.registry.TofuLootTables;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class LootModifierProviderFactory {

	public GlobalLootModifierProvider create(GatherDataEvent.Client event, DatapackBuiltinEntriesProvider provider) {
		return new GlobalLootModifierProvider(
				event.getGenerator().getPackOutput(),
				provider.getRegistryProvider(),
				TofuCraftReload.MODID
		) {
			@Override
			protected void start() {
				addSeeds(Blocks.SHORT_GRASS.getLootTable().get(), 0.075F);
				addSeeds(Blocks.TALL_GRASS.getLootTable().get(), 0.08F);
				addSeeds(Blocks.FERN.getLootTable().get(), 0.068F);
				addSeeds(Blocks.LARGE_FERN.getLootTable().get(), 0.08F);
				addTable(BuiltInLootTables.SIMPLE_DUNGEON, TofuLootTables.TOFUSTICK, 0.05F);
				addTable(BuiltInLootTables.JUNGLE_TEMPLE, TofuLootTables.TOFUSTICK, 0.4F);
				addTable(BuiltInLootTables.SHIPWRECK_TREASURE, TofuLootTables.TOFUSTICK, 0.675F);
				addTable(BuiltInLootTables.UNDERWATER_RUIN_BIG, TofuLootTables.TOFUSTICK, 0.5F);
				addTable(BuiltInLootTables.UNDERWATER_RUIN_SMALL, TofuLootTables.TOFUSTICK, 0.1F);
				addTable(BuiltInLootTables.ANCIENT_CITY, TofuLootTables.SEEDS_CHILI_ANCIENT_CITY, 0.5F);
				addTable(BuiltInLootTables.ANCIENT_CITY_ICE_BOX, TofuLootTables.SEEDS_CHILI_ANCIENT_CITY, 1F);
				addTable(BuiltInLootTables.SPAWN_BONUS_CHEST, TofuLootTables.TOFU_BOOK, 1F);
				addTable(EntityTypes.ZOGLIN.getDefaultLootTable().get(), TofuLootTables.ROTTEN_PORK, 1F);
				addTable(EntityTypes.ZOMBIFIED_PIGLIN.getDefaultLootTable().get(), TofuLootTables.ROTTEN_PORK, 1F);
				addZundaTable(0.5F);
			}

			private void addTable(ResourceKey<LootTable> table, ResourceKey<LootTable> rewriteTable, float chance) {
				add(TofuCraftReload.prefix(table), new AddTableLootModifier(new LootItemCondition[]{
						LootTableIdCondition.builder(table.identifier()).build(),
						LootItemRandomChanceCondition.randomChance(chance).build(),
						InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.registries.lookupOrThrow(Registries.ITEM), Items.SHEARS))).build()
				}, IGlobalLootModifier.DEFAULT_PRIORITY, rewriteTable));
			}

			private void addZundaTable(float chance) {
				add("zunda", new ZundaModifier(new LootItemCondition[]{
						LootItemRandomChanceCondition.randomChance(chance).build()
				}, IGlobalLootModifier.DEFAULT_PRIORITY));
			}

			private void addSeeds(ResourceKey<LootTable> table, float chance) {
				add(TofuCraftReload.prefix(table), new SeedDropModifier(new LootItemCondition[]{
						LootTableIdCondition.builder(table.identifier()).build(),
						LootItemRandomChanceCondition.randomChance(chance).build()
				}, IGlobalLootModifier.DEFAULT_PRIORITY));
			}
		};
	}

}