package baguchan.tofucraft.data;

import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.HashSet;
import java.util.Set;

public class BlockLootTables extends net.minecraft.data.loot.BlockLoot {
	private final Set<Block> knownBlocks = new HashSet<>();
	// [VanillaCopy] super
	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
	private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

	@Override
	protected void add(Block block, LootTable.Builder builder) {
		super.add(block, builder);
		knownBlocks.add(block);
	}

	@Override
	protected void addTables() {
		LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 6));
		LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));
		LootItemCondition.Builder lootitemcondition$builder_extra = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 5));


		add(TofuBlocks.SOYBEAN, applyExplosionDecay(TofuBlocks.SOYBEAN, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS).when(lootitemcondition$builder1).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS)))).withPool(LootPool.lootPool().when(lootitemcondition$builder1).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))).withPool(LootPool.lootPool().when(lootitemcondition$builder).add(LootItem.lootTableItem(TofuItems.EDAMAME).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))).withPool(LootPool.lootPool().when(lootitemcondition$builder_extra).add(LootItem.lootTableItem(TofuItems.EDAMAME).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));

		LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_NETHER).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_NETHER, applyExplosionDecay(TofuBlocks.SOYBEAN_NETHER, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER).when(lootitemcondition$builder2).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER)))).withPool(LootPool.lootPool().when(lootitemcondition$builder2).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemcondition$builder3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_SOUL).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_SOUL, applyExplosionDecay(TofuBlocks.SOYBEAN_SOUL, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL).when(lootitemcondition$builder3).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL)))).withPool(LootPool.lootPool().when(lootitemcondition$builder3).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

		registerTofuDrop(TofuBlocks.KINUTOFU, TofuItems.TOFUKINU);
		registerTofuDrop(TofuBlocks.MOMENTOFU, TofuItems.TOFUMOMEN);
		registerTofuDrop(TofuBlocks.ISHITOFU, TofuItems.TOFUISHI);
		dropSelf(TofuBlocks.ISHITOFU_BRICK);
		dropSelf(TofuBlocks.ISHITOFU_SMOOTH_BRICK);
		dropSelf(TofuBlocks.ISHITOFU_CHISELED_BRICK);
		registerTofuDrop(TofuBlocks.METALTOFU, TofuItems.TOFUMETAL);
		registerTofuDrop(TofuBlocks.DIAMONDTOFU, TofuItems.TOFUDIAMOND);
		registerTofuDrop(TofuBlocks.GRILLEDTOFU, TofuItems.TOFUGRILLED);
		registerTofuDrop(TofuBlocks.ZUNDATOFU, TofuItems.TOFUZUNDA);
		registerTofuDrop(TofuBlocks.HELLTOFU, TofuItems.TOFUHELL);
		registerTofuDrop(TofuBlocks.SOULTOFU, TofuItems.TOFUSOUL);

		dropSelf(TofuBlocks.HELLTOFU_BRICK);
		dropSelf(TofuBlocks.HELLTOFU_SMOOTH_BRICK);
		dropSelf(TofuBlocks.SOULTOFU_BRICK);
		dropSelf(TofuBlocks.SOULTOFU_SMOOTH_BRICK);

		dropSelf(TofuBlocks.TOFUSTAIR_KINU);
		dropSelf(TofuBlocks.TOFUSTAIR_MOMEN);
		dropSelf(TofuBlocks.TOFUSTAIR_ISHI);
		dropSelf(TofuBlocks.TOFUSTAIR_METAL);
		dropSelf(TofuBlocks.TOFUSTAIR_ZUNDA);
		dropSelf(TofuBlocks.TOFUSTAIR_ISHIBRICK);
		dropSelf(TofuBlocks.TOFUSTAIR_HELLBRICK);
		dropSelf(TofuBlocks.TOFUSTAIR_SOULBRICK);

		dropSelf(TofuBlocks.TOFUSLAB_KINU);
		dropSelf(TofuBlocks.TOFUSLAB_MOMEN);
		dropSelf(TofuBlocks.TOFUSLAB_ISHI);
		dropSelf(TofuBlocks.TOFUSLAB_METAL);
		dropSelf(TofuBlocks.TOFUSLAB_ZUNDA);
		dropSelf(TofuBlocks.TOFUSLAB_ISHIBRICK);
		dropSelf(TofuBlocks.TOFUSLAB_HELLBRICK);
		dropSelf(TofuBlocks.TOFUSLAB_SOULBRICK);

		dropSelf(TofuBlocks.TOFUFENCE_KINU);
		dropSelf(TofuBlocks.TOFUFENCE_MOMEN);
		dropSelf(TofuBlocks.TOFUFENCE_ISHI);
		dropSelf(TofuBlocks.TOFUFENCE_METAL);

		dropSelf(TofuBlocks.TOFUTORCH_KINU);
		dropSelf(TofuBlocks.TOFUTORCH_MOMEN);
		dropSelf(TofuBlocks.TOFUTORCH_ISHI);
		dropSelf(TofuBlocks.TOFUTORCH_METAL);
		dropSelf(TofuBlocks.WALLTOFUTORCH_KINU);
		dropSelf(TofuBlocks.WALLTOFUTORCH_MOMEN);
		dropSelf(TofuBlocks.WALLTOFUTORCH_ISHI);
		dropSelf(TofuBlocks.WALLTOFUTORCH_METAL);

		dropSelf(TofuBlocks.TOFULADDER_KINU);
		dropSelf(TofuBlocks.TOFULADDER_MOMEN);
		dropSelf(TofuBlocks.TOFULADDER_ISHI);
		dropSelf(TofuBlocks.TOFULADDER_ISHIBRICK);
		dropSelf(TofuBlocks.TOFULADDER_METAL);

		dropSelf(TofuBlocks.TOFU_TERRAIN);
		dropSelf(TofuBlocks.TOFUSLATE);
		this.add(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE, createTofuDiamondOreDrop(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE, TofuItems.TOFUDIAMOND_NUGGET));
		this.add(TofuBlocks.ORE_TOFU_DIAMOND, createTofuDiamondOreDrop(TofuBlocks.ORE_TOFU_DIAMOND, TofuItems.TOFUDIAMOND_NUGGET));
		dropSelf(TofuBlocks.TOFU_BEDROCK);
		dropSelf(TofuBlocks.SAPLING_TOFU);
		this.add(TofuBlocks.LEAVES_TOFU, createLeavesDrops(TofuBlocks.LEAVES_TOFU, TofuBlocks.SAPLING_TOFU, DEFAULT_SAPLING_DROP_RATES));

		dropSelf(TofuBlocks.LEEK_GREEN_STEM);
		dropSelf(TofuBlocks.LEEK_STEM);
		dropSelf(TofuBlocks.ZUNDATOFU_MUSHROOM);
		dropSelf(TofuBlocks.TOFU_STEM);
		dropSelf(TofuBlocks.TOFU_STEM_PLANKS);

		this.dropOther(TofuBlocks.LEEK, TofuItems.LEEK);

		dropOther(TofuBlocks.TOFU_FARMLAND, TofuBlocks.TOFU_TERRAIN);
		dropSelf(TofuBlocks.SALTPAN);
		dropSelf(TofuBlocks.SALT_FURNACE);

		this.add(TofuBlocks.TOFUBED, (p_124233_) -> {
			return createSinglePropConditionTable(p_124233_, BedBlock.PART, BedPart.HEAD);
		});
		dropSelf(TofuBlocks.TOFUCHEST);

		dropSelf(TofuBlocks.TF_STORAGE);
	}

	protected static LootTable.Builder createTofuDiamondOreDrop(Block p_124140_, Item p_124141_) {
		return createSilkTouchDispatchTable(p_124140_, applyExplosionDecay(p_124140_, LootItem.lootTableItem(p_124141_).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
	}

	private void registerLeavesNoSapling(Block leaves) {
		LootPoolEntryContainer.Builder<?> sticks = applyExplosionDecay(leaves, LootItem.lootTableItem(Items.STICK)
				.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
				.when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F)));
		add(leaves, createSilkTouchOrShearsDispatchTable(leaves, sticks));
	}

	private void registerTofuDrop(Block tofu, Item dropItem) {
		LootPoolEntryContainer.Builder<?> sticks = applyExplosionDecay(tofu, LootItem.lootTableItem(dropItem)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))));
		add(tofu, createSilkTouchOrShearsDispatchTable(tofu, sticks));
	}


	// [VanillaCopy] super.droppingWithChancesAndSticks, but non-silk touch parameter can be an item instead of a block
	private static LootTable.Builder silkAndStick(Block block, ItemLike nonSilk, float... nonSilkFortune) {
		LootItemCondition.Builder NOT_SILK_TOUCH_OR_SHEARS = ObfuscationReflectionHelper.getPrivateValue(net.minecraft.data.loot.BlockLoot.class, null, "HAS_NO_SHEARS_OR_SILK_TOUCH");
		return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(nonSilk.asItem())).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, nonSilkFortune))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
	}

	private void registerEmpty(Block b) {
		add(b, LootTable.lootTable());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return knownBlocks;
	}
}