package baguchi.tofucraft.data.loot;

import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.CubeMobPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class TofuEntityLootTables extends EntityLootSubProvider {
	public TofuEntityLootTables(Context output) {
		super(FeatureFlags.REGISTRY.allFlags(), output);
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return TofuEntityTypes.ENTITIES.getEntries().stream().map(Supplier::get);
	}

	@Override
	public void generate() {
		this.add(TofuEntityTypes.TOFUNIAN.get(), LootTable.lootTable());
		this.add(TofuEntityTypes.TRAVELER_TOFUNIAN.get(), LootTable.lootTable());
		this.add(TofuEntityTypes.TOFU_GOLEM.get(), LootTable.lootTable());
		this.add(TofuEntityTypes.ZUNDAMITE.get(), LootTable.lootTable());
		this.add(TofuEntityTypes.FUKUMAME_THROWER.get(), LootTable.lootTable());
		this.add(
				TofuEntityTypes.OAGE_CUBE.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.OAGE.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
														.when(this.killedByFrog().invert())
										)
										.add(
												LootItem.lootTableItem(TofuItems.OAGE.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
														.when(this.killedByFrog())
										)
										.when(
												LootItemEntityPropertyCondition.hasProperties(
														LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().cubeMob(CubeMobPredicate.sized(MinMaxBounds.Ints.exactly(1)))
												)
										)
						)
		);
		this.add(
				TofuEntityTypes.TOFU_SLIME.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.TOFU_KINU.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
														.when(this.killedByFrog().invert())
										)
										.add(
												LootItem.lootTableItem(TofuItems.TOFU_KINU.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
														.when(this.killedByFrog())
										)
										.when(
												LootItemEntityPropertyCondition.hasProperties(
														LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().cubeMob(CubeMobPredicate.sized(MinMaxBounds.Ints.exactly(1)))
												)
										)
						)
		);

		this.add(
				TofuEntityTypes.TOFU_COW.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(Items.LEATHER)
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)
						)
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.TOFU_HAMBURG_RAW.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
														.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)
						)
		);
		this.add(
				TofuEntityTypes.TOFU_PIG.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.SOYMEAT.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
														.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)
						)
		);
		this.add(
				TofuEntityTypes.TOFU_FISH.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.RAW_TOFU_FISH.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
														.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
										)
						)
		);
		this.add(
				TofuEntityTypes.TOFU_SPIDER.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
														.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										).add(
												LootItem.lootTableItem(TofuItems.YUBA.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)
										.add(
												LootItem.lootTableItem(TofuItems.NATTO.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)
										.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.05F, 0.01F))
						)
		);
		this.add(
				TofuEntityTypes.TOFU_CREEPER.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.TOFU_KINU.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
														.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										).add(
												LootItem.lootTableItem(Items.GUNPOWDER)
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)

						)
		);


		this.add(
				TofuEntityTypes.TOFU_GANDLEM.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.SOY_FORCE_SHARD.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										).add(
												LootItem.lootTableItem(TofuItems.TOFU_DIAMOND.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 6)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)

						)
		);
		this.add(
				TofuEntityTypes.SHUDOFUSPIDER.get(),
				LootTable.lootTable()
						.withPool(
								LootPool.lootPool()
										.setRolls(ContextIntProviders.exactly(1))
										.add(
												LootItem.lootTableItem(TofuItems.NATTO.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(8, 12)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										).add(
												LootItem.lootTableItem(TofuItems.NATTO_BALL.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(16, 24)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										).add(
												LootItem.lootTableItem(TofuItems.SHUDOFU.get())
														.apply(SetItemCountFunction.setCount(ContextIntProviders.between(24, 32)))
														.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
										)

						)
		);
	}
}
