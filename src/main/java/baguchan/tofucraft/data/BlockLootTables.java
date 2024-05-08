package baguchan.tofucraft.data;

import baguchan.tofucraft.block.crop.RiceCropsBlock;
import baguchan.tofucraft.block.crop.SoybeanCropsBlock;
import baguchan.tofucraft.block.utils.MisoBarrelBlock;
import baguchan.tofucraft.block.utils.WeightBaseBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.HashSet;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
	private final Set<Block> knownBlocks = new HashSet<>();
	// [VanillaCopy] super
	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
	private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

	private static final Set<Item> EXPLOSION_RESISTANT = Set.of();


	protected BlockLootTables() {
		super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void add(Block block, LootTable.Builder builder) {
		super.add(block, builder);
		knownBlocks.add(block);
	}

	@Override
	protected void generate() {
		LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 6));
		LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));
		LootItemCondition.Builder lootitemcondition$builder_extra = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 5));


		add(TofuBlocks.SOYBEAN.get(), applyExplosionDecay(TofuBlocks.SOYBEAN.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get()).when(lootitemcondition$builder1).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder1).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))).withPool(LootPool.lootPool().when(lootitemcondition$builder).add(LootItem.lootTableItem(TofuItems.EDAMAME.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))).withPool(LootPool.lootPool().when(lootitemcondition$builder_extra).add(LootItem.lootTableItem(TofuItems.EDAMAME.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));

		LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_NETHER.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_NETHER.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_NETHER.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get()).when(lootitemcondition$builder2).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder2).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemcondition$builder3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_SOUL.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_SOUL.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_SOUL.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get()).when(lootitemcondition$builder3).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder3).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemcondition$builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.LEEK_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 3));

		add(TofuBlocks.LEEK_CROP.get(), applyExplosionDecay(TofuBlocks.LEEK_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.LEEK.get()).when(lootitemcondition$builder4).otherwise(LootItem.lootTableItem(TofuItems.LEEK.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder4).add(LootItem.lootTableItem(TofuItems.LEEK.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemconditon$chili_crop = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.CHILI_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.CHILI_CROP.get(), applyExplosionDecay(TofuBlocks.CHILI_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.CHILI.get()).when(lootitemconditon$chili_crop).otherwise(LootItem.lootTableItem(TofuItems.CHILI.get())))).withPool(LootPool.lootPool().when(lootitemconditon$chili_crop).add(LootItem.lootTableItem(TofuItems.CHILI.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemcondition$builder7 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.RICE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropsBlock.AGE, 7));

		this.add(TofuBlocks.RICE_CROP.get(), createCropDrops(TofuBlocks.RICE_CROP.get(), TofuItems.RICE.get(), TofuItems.SEEDS_RICE.get(), lootitemcondition$builder7));
		registerEmpty(TofuBlocks.RICE_ROOT.get());

		dropOther(TofuBlocks.SOYMILK_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(TofuBlocks.SOYMILK_NETHER_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(TofuBlocks.SOYMILK_SOUL_CAULDRON.get(), Blocks.CAULDRON);

		registerTofuDrop(TofuBlocks.KINUTOFU.get(), TofuItems.TOFUKINU.get());
		registerTofuDrop(TofuBlocks.MOMENTOFU.get(), TofuItems.TOFUMOMEN.get());
		registerTofuDrop(TofuBlocks.ISHITOFU.get(), TofuItems.TOFUISHI.get());
		dropSelf(TofuBlocks.ISHITOFU_BRICK.get());
		dropSelf(TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		registerTofuDrop(TofuBlocks.METALTOFU.get(), TofuItems.TOFUMETAL.get());
		registerTofuDrop(TofuBlocks.DIAMONDTOFU.get(), TofuItems.TOFUDIAMOND.get());
		dropSelf(TofuBlocks.TOFU_GEM_BLOCK.get());
		registerTofuDrop(TofuBlocks.GRILLEDTOFU.get(), TofuItems.TOFUGRILLED.get());
		registerTofuDrop(TofuBlocks.ZUNDATOFU.get(), TofuItems.TOFUZUNDA.get());

		dropSelf(TofuBlocks.ZUNDATOFU_BRICK.get());
		dropSelf(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ZUNDABRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_ZUNDABRICK.get());

		registerTofuDrop(TofuBlocks.HELLTOFU.get(), TofuItems.TOFUHELL.get());
		registerTofuDrop(TofuBlocks.SOULTOFU.get(), TofuItems.TOFUSOUL.get());
		registerTofuDrop(TofuBlocks.MISOTOFU.get(), TofuItems.TOFUMISO.get());
		registerTofuDrop(TofuBlocks.DRIEDTOFU.get(), TofuItems.TOFUDRIED.get());

		registerTofuDrop(TofuBlocks.EGGTOFU.get(), TofuItems.TOFUEGG.get());
		dropSelf(TofuBlocks.TOFUSTAIR_EGG.get());
		registerSlab(TofuBlocks.TOFUSLAB_EGG.get());

		dropSelf(TofuBlocks.EGGTOFU_BRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_EGGBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_EGGBRICK.get());

		registerTofuDrop(TofuBlocks.SESAMETOFU.get(), TofuItems.TOFUSESAME.get());
		dropSelf(TofuBlocks.TOFUSTAIR_SESAME.get());
		registerSlab(TofuBlocks.TOFUSLAB_SESAME.get());

		dropSelf(TofuBlocks.HELLTOFU_BRICK.get());
		dropSelf(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.SOULTOFU_BRICK.get());
		dropSelf(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		registerTofuDrop(TofuBlocks.MINCEDTOFU.get(), TofuItems.TOFU_MINCED.get());

		dropSelf(TofuBlocks.TOFUSTAIR_KINU.get());
		dropSelf(TofuBlocks.TOFUSTAIR_MOMEN.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ISHI.get());
		dropSelf(TofuBlocks.TOFUSTAIR_METAL.get());
		dropSelf(TofuBlocks.TOFUSTAIR_GRILLED.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ZUNDA.get());
		dropSelf(TofuBlocks.TOFUSTAIR_HELL.get());
		dropSelf(TofuBlocks.TOFUSTAIR_SOUL.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ISHIBRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_HELLBRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_SOULBRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_MISO.get());
		dropSelf(TofuBlocks.TOFUSTAIR_DRIED.get());

		registerSlab(TofuBlocks.TOFUSLAB_KINU.get());
		registerSlab(TofuBlocks.TOFUSLAB_MOMEN.get());
		registerSlab(TofuBlocks.TOFUSLAB_ISHI.get());
		registerSlab(TofuBlocks.TOFUSLAB_METAL.get());
		registerSlab(TofuBlocks.TOFUSLAB_GRILLED.get());
		registerSlab(TofuBlocks.TOFUSLAB_ZUNDA.get());
		registerSlab(TofuBlocks.TOFUSLAB_HELL.get());
		registerSlab(TofuBlocks.TOFUSLAB_SOUL.get());
		registerSlab(TofuBlocks.TOFUSLAB_ISHIBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_HELLBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_SOULBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_MISO.get());
		registerSlab(TofuBlocks.TOFUSLAB_DRIED.get());

		dropSelf(TofuBlocks.TOFUFENCE_KINU.get());
		dropSelf(TofuBlocks.TOFUFENCE_MOMEN.get());
		dropSelf(TofuBlocks.TOFUFENCE_ISHI.get());
		dropSelf(TofuBlocks.TOFUFENCE_METAL.get());
		dropSelf(TofuBlocks.TOFUFENCE_HELL.get());
		dropSelf(TofuBlocks.TOFUFENCE_SOUL.get());
		dropSelf(TofuBlocks.TOFUFENCE_GRILLED.get());
		dropSelf(TofuBlocks.TOFUFENCE_ZUNDA.get());

		this.add(TofuBlocks.TOFUDOOR_KINU.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_MOMEN.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_ISHI.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_METAL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_HELL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_SOUL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_GRILLED.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_ZUNDA.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));

		dropSelf(TofuBlocks.TOFUTRAPDOOR_KINU.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_MOMEN.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_ISHI.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_METAL.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_HELL.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_SOUL.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_GRILLED.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_ZUNDA.get());

		dropSelf(TofuBlocks.TOFUTORCH_KINU.get());
		dropSelf(TofuBlocks.TOFUTORCH_MOMEN.get());
		dropSelf(TofuBlocks.TOFUTORCH_ISHI.get());
		dropSelf(TofuBlocks.TOFUTORCH_METAL.get());
		dropSelf(TofuBlocks.TOFUTORCH_GRILLED.get());
		dropSelf(TofuBlocks.TOFUTORCH_ZUNDA.get());

		dropSelf(TofuBlocks.WALLTOFUTORCH_KINU.get());
		dropSelf(TofuBlocks.WALLTOFUTORCH_MOMEN.get());
		dropSelf(TofuBlocks.WALLTOFUTORCH_ISHI.get());
		dropSelf(TofuBlocks.WALLTOFUTORCH_METAL.get());
		dropSelf(TofuBlocks.WALLTOFUTORCH_GRILLED.get());
		dropSelf(TofuBlocks.WALLTOFUTORCH_ZUNDA.get());
		dropSelf(TofuBlocks.TOFULADDER_KINU.get());
		dropSelf(TofuBlocks.TOFULADDER_MOMEN.get());
		dropSelf(TofuBlocks.TOFULADDER_ISHI.get());
		dropSelf(TofuBlocks.TOFULADDER_ISHIBRICK.get());
		dropSelf(TofuBlocks.TOFULADDER_METAL.get());
		dropSelf(TofuBlocks.TOFULADDER_GRILLED.get());
		dropSelf(TofuBlocks.TOFULADDER_ZUNDA.get());

		dropSelf(TofuBlocks.TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.TOFU_TERRAIN_ZUNDA.get());
		dropSelf(TofuBlocks.TOFUSLATE.get());
		this.add(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get(), createTofuDiamondOreDrop(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get(), TofuItems.TOFUDIAMOND_NUGGET.get()));
		this.add(TofuBlocks.ORE_TOFU_DIAMOND.get(), createTofuDiamondOreDrop(TofuBlocks.ORE_TOFU_DIAMOND.get(), TofuItems.TOFUDIAMOND_NUGGET.get()));
		this.add(TofuBlocks.ORE_TOFUGEM.get(), createTofuDiamondOreDrop(TofuBlocks.ORE_TOFUGEM.get(), TofuItems.TOFUGEM.get()));

		dropSelf(TofuBlocks.TOFU_BEDROCK.get());
		dropSelf(TofuBlocks.SAPLING_TOFU.get());
		this.add(TofuBlocks.LEAVES_TOFU.get(), createTofuLeavesDrops(TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.SAPLING_TOFU.get(), DEFAULT_SAPLING_DROP_RATES));

		dropSelf(TofuBlocks.SAPLING_APRICOT.get());
		this.add(TofuBlocks.LEAVES_APRICOT.get(), createApricotLeavesDrop(TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.SAPLING_APRICOT.get(), DEFAULT_SAPLING_DROP_RATES));


		dropSelf(TofuBlocks.TOFU_FLOWER.get());

		dropSelf(TofuBlocks.LEEK_GREEN_STEM.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PLANKS.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.LEEK_GREEN_FENCE.get());
		dropSelf(TofuBlocks.LEEK_GREEN_FENCE_GATE.get());
		this.add(TofuBlocks.LEEK_GREEN_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.LEEK_GREEN_TRAPDOOR.get());

		dropSelf(TofuBlocks.LEEK_STEM.get());
		dropSelf(TofuBlocks.LEEK_PLANKS.get());
		dropSelf(TofuBlocks.LEEK_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.LEEK_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.LEEK_FENCE.get());
		dropSelf(TofuBlocks.LEEK_FENCE_GATE.get());


		dropSelf(TofuBlocks.ZUNDATOFU_MUSHROOM.get());

		dropSelf(TofuBlocks.TOFU_STEM.get());
		dropSelf(TofuBlocks.TOFU_STEM_PLANKS.get());
		dropSelf(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.TOFU_STEM_FENCE.get());
		dropSelf(TofuBlocks.TOFU_STEM_FENCE_GATE.get());
		this.add(TofuBlocks.TOFU_STEM_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.TOFU_STEM_TRAPDOOR.get());


		this.dropOther(TofuBlocks.LEEK.get(), TofuItems.LEEK.get());

		dropOther(TofuBlocks.TOFU_FARMLAND.get(), TofuBlocks.TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.SALTPAN.get());
		dropSelf(TofuBlocks.SALT_FURNACE.get());
		dropSelf(TofuBlocks.SPROUTSJAR.get());
		dropSelf(TofuBlocks.MORIJIO.get());
		dropSelf(TofuBlocks.FOODPLATE.get());
		dropSelf(TofuBlocks.ZUNDAMA_BLOCK.get());

		dropSelf(TofuBlocks.RICE_BLOCK.get());
		dropSelf(TofuBlocks.SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.SALT_BLOCK.get());

		LootItemCondition.Builder lootitemcondition$builder5 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_MISO.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MisoBarrelBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_MISO.get(), applyExplosionDecay(TofuBlocks.BARREL_MISO.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.MISO.get()).when(lootitemcondition$builder5).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL).when(ExplosionCondition.survivesExplosion())))));

		LootItemCondition.Builder lootitemcondition$builder6 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_MISOTOFU.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_MISOTOFU.get(), applyExplosionDecay(TofuBlocks.BARREL_MISOTOFU.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.TOFUMISO.get()).when(lootitemcondition$builder6).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL).when(ExplosionCondition.survivesExplosion())))));

		LootItemCondition.Builder lootitemcondition$builder8 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.NATTOBED.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.NATTOBED.get(), applyExplosionDecay(TofuBlocks.NATTOBED.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.NATTO.get()).when(lootitemcondition$builder8).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6.0F)))))));

		LootItemCondition.Builder lootitemcondition$builder9 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.NETHER_NATTOBED.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.NETHER_NATTOBED.get(), applyExplosionDecay(TofuBlocks.NETHER_NATTOBED.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.NETHER_NATTO.get()).when(lootitemcondition$builder9).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6.0F)))))));


		registerEmpty(TofuBlocks.TOFUCAKE.get());
		registerEmpty(TofuBlocks.ZUNDATOFUCAKE.get());
		registerEmpty(TofuBlocks.SOYCHEESE_TART.get());

		this.add(TofuBlocks.TOFUBED.get(), (p_124233_) -> {
			return createSinglePropConditionTable(p_124233_, BedBlock.PART, BedPart.HEAD);
		});
		dropSelf(TofuBlocks.TOFUCHEST.get());

		registerEmpty(TofuBlocks.YUBA.get());
		registerEmpty(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.TOFU_STEM_SIGN.get());
		dropOther(TofuBlocks.TOFU_STEM_WALL_SIGN.get(), TofuBlocks.TOFU_STEM_SIGN.get());
		dropSelf(TofuBlocks.LEEK_GREEN_SIGN.get());
		dropOther(TofuBlocks.LEEK_GREEN_WALL_SIGN.get(), TofuBlocks.LEEK_GREEN_SIGN.get());
		dropSelf(TofuBlocks.LEEK_SIGN.get());
		dropOther(TofuBlocks.LEEK_WALL_SIGN.get(), TofuBlocks.LEEK_SIGN.get());
		dropSelf(TofuBlocks.TOFU_STEM_HANGING_SIGN.get());
		dropOther(TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get());
		dropSelf(TofuBlocks.LEEK_GREEN_HANGING_SIGN.get());
		dropOther(TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get());
		dropSelf(TofuBlocks.LEEK_HANGING_SIGN.get());
		dropOther(TofuBlocks.LEEK_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get());
		dropSelf(TofuBlocks.TOFU_METAL_LANTERN.get());
		dropSelf(TofuBlocks.TOFU_METAL_SOUL_LANTERN.get());
		dropSelf(TofuBlocks.TOFU_METAL_CHAIN.get());

		dropPottedContents(TofuBlocks.POTTED_TOFU_SAPLING.get());
		dropPottedContents(TofuBlocks.POTTED_APRICOT_SAPLING.get());
		dropPottedContents(TofuBlocks.POTTED_ZUNDA_TOFU_MUSHROOM.get());
		dropPottedContents(TofuBlocks.POTTED_LEEK.get());
		dropPottedContents(TofuBlocks.POTTED_TOFU_FLOWER.get());
		dropSelf(TofuBlocks.TOFU_DETECTOR.get());
		dropSelf(TofuBlocks.TF_STORAGE.get());
		dropSelf(TofuBlocks.ANTENNA_BASIC.get());
		dropSelf(TofuBlocks.TOFU_WORK_STATION.get());
	}

	protected LootTable.Builder createTofuDiamondOreDrop(Block p_124140_, Item p_124141_) {
		return createSilkTouchDispatchTable(p_124140_, applyExplosionDecay(p_124140_, LootItem.lootTableItem(p_124141_).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
	}

	protected LootTable.Builder createApricotLeavesDrop(Block p_124264_, Block p_124265_, float... p_124266_) {
		return createLeavesDrops(p_124264_, p_124265_, p_124266_).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionCondition(TofuBlocks.LEAVES_APRICOT.get(), LootItem.lootTableItem(TofuItems.APRICOT.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
	}

	protected LootTable.Builder createTofuLeavesDrops(Block p_250088_, Block p_250731_, float... p_248949_) {
		return createSilkTouchOrShearsDispatchTable(p_250088_, this.applyExplosionCondition(p_250088_, LootItem.lootTableItem(p_250731_)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, p_248949_)));
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
	private LootTable.Builder silkAndStick(Block block, ItemLike nonSilk, float... nonSilkFortune) {
		LootItemCondition.Builder NOT_SILK_TOUCH_OR_SHEARS = ObfuscationReflectionHelper.getPrivateValue(BlockLootSubProvider.class, null, "HAS_NO_SHEARS_OR_SILK_TOUCH");
		return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(nonSilk.asItem())).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, nonSilkFortune))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
	}

	private void registerEmpty(Block b) {
		add(b, LootTable.lootTable());
	}

	private void registerSlab(Block b) {
		add(b, createSlabItemTable(b));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return knownBlocks;
	}
}