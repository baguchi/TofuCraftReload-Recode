package baguchi.tofucraft.data;

import baguchi.tofucraft.block.crop.RiceCropsBlock;
import baguchi.tofucraft.block.crop.SesameCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanPaleCropsBlock;
import baguchi.tofucraft.block.crop.SproutsCropBlock;
import baguchi.tofucraft.block.utils.MisoBarrelBlock;
import baguchi.tofucraft.block.utils.WeightBaseBlock;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.advancements.predicates.BlockPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashSet;
import java.util.Set;

import static net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction.copyComponentsFromBlockEntity;

public class BlockLootTables extends BlockLootSubProvider {
	private final Set<Block> knownBlocks = new HashSet<>();
	// [VanillaCopy] super
	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
	private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.1F, 0.075F, 0.12F, 0.195F, 0.2F};

	private static final Set<Item> EXPLOSION_RESISTANT = Set.of();


	protected BlockLootTables(HolderLookup.Provider p_344943_) {
		super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), p_344943_);
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


		add(TofuBlocks.SOYBEAN.get(), applyExplosionDecay(TofuBlocks.SOYBEAN.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get()).when(lootitemcondition$builder1).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder1).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))).withPool(LootPool.lootPool().when(lootitemcondition$builder).add(LootItem.lootTableItem(TofuItems.EDAMAME.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))).withPool(LootPool.lootPool().when(lootitemcondition$builder_extra).add(LootItem.lootTableItem(TofuItems.EDAMAME.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))));

		LootItemCondition.Builder soybeanNether = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_NETHER.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_NETHER.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_NETHER.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get()).when(soybeanNether).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get())))).withPool(LootPool.lootPool().when(soybeanNether).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder soybeanSoul = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_SOUL.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_SOUL.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_SOUL.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get()).when(soybeanSoul).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get())))).withPool(LootPool.lootPool().when(soybeanSoul).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder sesame = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SESAME.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SesameCropsBlock.AGE, 7));

		add(TofuBlocks.SESAME.get(), applyExplosionDecay(TofuBlocks.SESAME.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SESAME.get()).when(sesame).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SESAME.get())))).withPool(LootPool.lootPool().when(sesame).add(LootItem.lootTableItem(TofuItems.SEEDS_SESAME.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder pale = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_PALE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanPaleCropsBlock.AGE, 3));
		LootItemCondition.Builder pale_glow = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_PALE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanPaleCropsBlock.AGE, 3).hasProperty(SoybeanPaleCropsBlock.BLOOM, true));

		add(TofuBlocks.SOYBEAN_PALE.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_PALE.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_PALE.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3).when(pale))).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_PALE_GLOW.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
		))));

		LootItemCondition.Builder lootitemcondition$builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.LEEK_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 3));

		add(TofuBlocks.LEEK_CROP.get(), applyExplosionDecay(TofuBlocks.LEEK_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.LEEK.get()).when(lootitemcondition$builder4).otherwise(LootItem.lootTableItem(TofuItems.LEEK.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder4).add(LootItem.lootTableItem(TofuItems.LEEK.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemconditon$chili_crop = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.CHILI_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.CHILI_CROP.get(), applyExplosionDecay(TofuBlocks.CHILI_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.CHILI.get()).when(lootitemconditon$chili_crop).otherwise(LootItem.lootTableItem(TofuItems.CHILI.get())))).withPool(LootPool.lootPool().when(lootitemconditon$chili_crop).add(LootItem.lootTableItem(TofuItems.CHILI.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemcondition$builder7 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.RICE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropsBlock.AGE, 7));

		this.add(TofuBlocks.RICE_CROP.get(), createCropDrops(TofuBlocks.RICE_CROP.get(), TofuItems.RICE.get(), TofuItems.SEEDS_RICE.get(), lootitemcondition$builder7));
		registerEmpty(TofuBlocks.RICE_ROOT.get());

		LootItemCondition.Builder lootitemconditon$sprouts = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.CHILI_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SproutsCropBlock.AGE, 3));

		add(TofuBlocks.SPROUTS.get(), applyExplosionDecay(TofuBlocks.SPROUTS.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SPROUTS.get()).when(lootitemconditon$sprouts).otherwise(LootItem.lootTableItem(TofuItems.SPROUTS.get())))).withPool(LootPool.lootPool().when(lootitemconditon$sprouts).add(LootItem.lootTableItem(TofuItems.SPROUTS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));
		add(TofuBlocks.WILD_SPROUTS.get(), applyExplosionDecay(TofuBlocks.WILD_SPROUTS.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SPROUTS.get())))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SPROUTS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))));


		dropOther(TofuBlocks.SOYMILK_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(TofuBlocks.SOYMILK_NETHER_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(TofuBlocks.SOYMILK_SOUL_CAULDRON.get(), Blocks.CAULDRON);

		dropSelf(TofuBlocks.WAXED_KINUTOFU.get());
		dropSelf(TofuBlocks.WAXED_MOMENTOFU.get());
		dropSelf(TofuBlocks.WAXED_ISHITOFU.get());
		registerTofuDrop(TofuBlocks.KINUTOFU.get(), TofuItems.TOFU_KINU.get());
		registerTofuDrop(TofuBlocks.MOMENTOFU.get(), TofuItems.TOFU_MOMEN.get());
		registerTofuDrop(TofuBlocks.ISHITOFU.get(), TofuItems.TOFU_ISHI.get());
		dropSelf(TofuBlocks.ISHITOFU_BRICK.get());
		dropSelf(TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		registerTofuDrop(TofuBlocks.METALTOFU.get(), TofuItems.TOFU_METAL.get());
		dropSelf(TofuBlocks.METAL_TOFU_GRATE.get());
		dropSelf(TofuBlocks.METAL_TOFU_LUMP.get());
		dropSelf(TofuBlocks.METAL_TOFU_BARS.get());
		registerTofuDrop(TofuBlocks.DIAMONDTOFU.get(), TofuItems.TOFU_DIAMOND.get());
		dropSelf(TofuBlocks.TOFU_GEM_BLOCK.get());
		dropSelf(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get());
		dropSelf(TofuBlocks.ZUNDA_ALLOY_TOFU_BLOCK.get());
		registerTofuDrop(TofuBlocks.GRILLED_TOFU.get(), TofuItems.TOFU_GRILLED.get());
		registerTofuDrop(TofuBlocks.ZUNDATOFU.get(), TofuItems.TOFU_ZUNDA.get());

		dropSelf(TofuBlocks.ZUNDATOFU_BRICK.get());
		dropSelf(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.TOFU_STAIR_ZUNDABRICK.get());
		registerSlab(TofuBlocks.TOFU_SLAB_ZUNDABRICK.get());

		registerTofuDrop(TofuBlocks.HELLTOFU.get(), TofuItems.TOFU_HELL.get());
		registerTofuDrop(TofuBlocks.SOULTOFU.get(), TofuItems.TOFU_SOUL.get());
		registerTofuDrop(TofuBlocks.MISOTOFU.get(), TofuItems.TOFU_MISO.get());
		registerTofuDrop(TofuBlocks.DRIEDTOFU.get(), TofuItems.TOFU_DRIED.get());

		registerTofuDrop(TofuBlocks.EGGTOFU.get(), TofuItems.TOFU_EGG.get());
		dropSelf(TofuBlocks.TOFU_STAIR_EGG.get());
		registerSlab(TofuBlocks.TOFU_SLAB_EGG.get());

		dropSelf(TofuBlocks.DRIEDTOFU_BRICK.get());
		dropSelf(TofuBlocks.DRIEDTOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.DRIEDTOFU_CHISELED_BRICK.get());
		dropSelf(TofuBlocks.TOFU_STAIR_DRIEDBRICK.get());
		registerSlab(TofuBlocks.TOFU_SLAB_DRIEDBRICK.get());

		dropSelf(TofuBlocks.EGGTOFU_BRICK.get());
		dropSelf(TofuBlocks.EGGTOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.EGGTOFU_CHISELED_BRICK.get());
		dropSelf(TofuBlocks.TOFU_STAIR_EGGBRICK.get());
		registerSlab(TofuBlocks.TOFU_SLAB_EGGBRICK.get());

		registerTofuDrop(TofuBlocks.SESAMETOFU.get(), TofuItems.TOFU_SESAME.get());
		dropSelf(TofuBlocks.TOFU_STAIR_SESAME.get());
		registerSlab(TofuBlocks.TOFU_SLAB_SESAME.get());

		dropSelf(TofuBlocks.HELLTOFU_BRICK.get());
		dropSelf(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.HELLTOFU_CHISELED_BRICK.get());
		dropSelf(TofuBlocks.SOULTOFU_BRICK.get());
		dropSelf(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.SOULTOFU_CHISELED_BRICK.get());
		registerTofuDrop(TofuBlocks.MINCEDTOFU.get(), TofuItems.TOFU_MINCED.get());

		dropSelf(TofuBlocks.TOFU_STAIR_KINU.get());
		dropSelf(TofuBlocks.TOFU_STAIR_MOMEN.get());
		dropSelf(TofuBlocks.TOFU_STAIR_ISHI.get());
		dropSelf(TofuBlocks.TOFU_STAIR_METAL.get());
		dropSelf(TofuBlocks.TOFU_STAIR_GRILLED.get());
		dropSelf(TofuBlocks.TOFU_STAIR_ZUNDA.get());
		dropSelf(TofuBlocks.TOFU_STAIR_HELL.get());
		dropSelf(TofuBlocks.TOFU_STAIR_SOUL.get());
		dropSelf(TofuBlocks.TOFU_STAIR_ISHIBRICK.get());
		dropSelf(TofuBlocks.TOFU_STAIR_HELLBRICK.get());
		dropSelf(TofuBlocks.TOFU_STAIR_SOULBRICK.get());
		dropSelf(TofuBlocks.TOFU_STAIR_MISO.get());
		dropSelf(TofuBlocks.TOFU_STAIR_DRIED.get());

		registerSlab(TofuBlocks.TOFU_SLAB_KINU.get());
		registerSlab(TofuBlocks.TOFU_SLAB_MOMEN.get());
		registerSlab(TofuBlocks.TOFU_SLAB_ISHI.get());
		registerSlab(TofuBlocks.TOFU_SLAB_METAL.get());
		registerSlab(TofuBlocks.TOFU_SLAB_GRILLED.get());
		registerSlab(TofuBlocks.TOFU_SLAB_ZUNDA.get());
		registerSlab(TofuBlocks.TOFU_SLAB_HELL.get());
		registerSlab(TofuBlocks.TOFU_SLAB_SOUL.get());
		registerSlab(TofuBlocks.TOFU_SLAB_ISHIBRICK.get());
		registerSlab(TofuBlocks.TOFU_SLAB_HELLBRICK.get());
		registerSlab(TofuBlocks.TOFU_SLAB_SOULBRICK.get());
		registerSlab(TofuBlocks.TOFU_SLAB_MISO.get());
		registerSlab(TofuBlocks.TOFU_SLAB_DRIED.get());

		dropSelf(TofuBlocks.TOFU_FENCE_KINU.get());
		dropSelf(TofuBlocks.TOFU_FENCE_MOMEN.get());
		dropSelf(TofuBlocks.TOFU_FENCE_ISHI.get());
		dropSelf(TofuBlocks.TOFU_FENCE_METAL.get());
		dropSelf(TofuBlocks.TOFU_FENCE_HELL.get());
		dropSelf(TofuBlocks.TOFU_FENCE_SOUL.get());
		dropSelf(TofuBlocks.TOFU_FENCE_GRILLED.get());
		dropSelf(TofuBlocks.TOFU_FENCE_ZUNDA.get());
		dropSelf(TofuBlocks.TOFU_FENCE_MISO.get());
		dropSelf(TofuBlocks.TOFU_FENCE_DRIED.get());
		dropSelf(TofuBlocks.TOFU_FENCE_EGG.get());
		dropSelf(TofuBlocks.TOFU_FENCE_SESAME.get());

		this.add(TofuBlocks.TOFU_DOOR_KINU.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_MOMEN.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_ISHI.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_METAL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_HELL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_SOUL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_GRILLED.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_ZUNDA.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_MISO.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_DRIED.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_EGG.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFU_DOOR_SESAME.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));

		dropSelf(TofuBlocks.TOFU_TRAPDOOR_KINU.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_MOMEN.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_ISHI.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_METAL.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_HELL.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_SOUL.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_GRILLED.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_ZUNDA.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_MISO.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_DRIED.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_EGG.get());
		dropSelf(TofuBlocks.TOFU_TRAPDOOR_SESAME.get());

		dropSelf(TofuBlocks.TOFU_TORCH_KINU.get());
		dropSelf(TofuBlocks.TOFU_TORCH_MOMEN.get());
		dropSelf(TofuBlocks.TOFU_TORCH_ISHI.get());
		dropSelf(TofuBlocks.TOFU_TORCH_METAL.get());
		dropSelf(TofuBlocks.TOFU_TORCH_GRILLED.get());
		dropSelf(TofuBlocks.TOFU_TORCH_ZUNDA.get());
		dropSelf(TofuBlocks.TOFU_TORCH_HELL.get());
		dropSelf(TofuBlocks.TOFU_TORCH_SOUL.get());

		dropOther(TofuBlocks.WALL_TOFU_TORCH_KINU.get(), TofuBlocks.TOFU_TORCH_KINU.get());
		dropOther(TofuBlocks.WALL_TOFU_TORCH_MOMEN.get(), TofuBlocks.TOFU_TORCH_MOMEN);
		dropOther(TofuBlocks.WALL_TOFU_TORCH_ISHI.get(), TofuBlocks.TOFU_TORCH_ISHI);
		dropOther(TofuBlocks.WALL_TOFU_TORCH_METAL.get(), TofuBlocks.TOFU_TORCH_METAL);
		dropOther(TofuBlocks.WALL_TOFU_TORCH_GRILLED.get(), TofuBlocks.TOFU_TORCH_GRILLED);
		dropOther(TofuBlocks.WALL_TOFU_TORCH_ZUNDA.get(), TofuBlocks.TOFU_TORCH_ZUNDA);
		dropOther(TofuBlocks.WALL_TOFU_TORCH_HELL.get(), TofuBlocks.TOFU_TORCH_HELL);
		dropOther(TofuBlocks.WALL_TOFU_TORCH_SOUL.get(), TofuBlocks.TOFU_TORCH_SOUL);
		dropSelf(TofuBlocks.TOFU_LADDER_KINU.get());
		dropSelf(TofuBlocks.TOFU_LADDER_MOMEN.get());
		dropSelf(TofuBlocks.TOFU_LADDER_ISHI.get());
		dropSelf(TofuBlocks.TOFU_LADDER_ISHIBRICK.get());
		dropSelf(TofuBlocks.TOFU_LADDER_METAL.get());
		dropSelf(TofuBlocks.TOFU_LADDER_GRILLED.get());
		dropSelf(TofuBlocks.TOFU_LADDER_ZUNDA.get());
		dropSelf(TofuBlocks.TOFU_LADDER_HELL.get());
		dropSelf(TofuBlocks.TOFU_LADDER_SOUL.get());

		dropSelf(TofuBlocks.TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.MABOU_TERRAIN.get());
		dropSelf(TofuBlocks.TOFU_TERRAIN_ZUNDA.get());
		dropSelf(TofuBlocks.TOFUSLATE.get());
		this.add(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get(), createTofuDiamondOreDrop(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get(), TofuItems.TOFU_DIAMOND_NUGGET.get()));
		this.add(TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.get(), createTofuForceOreDrop(TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.get(), TofuItems.SOY_FORCE_SHARD.get()));
		this.add(TofuBlocks.ORE_TOFU_DIAMOND.get(), createTofuDiamondOreDrop(TofuBlocks.ORE_TOFU_DIAMOND.get(), TofuItems.TOFU_DIAMOND_NUGGET.get()));
		this.add(TofuBlocks.ORE_TOFUGEM.get(), createTofuGemOreDrop(TofuBlocks.ORE_TOFUGEM.get(), TofuItems.TOFUGEM.get()));

		dropSelf(TofuBlocks.TOFU_BEDROCK.get());
		dropSelf(TofuBlocks.SAPLING_TOFU.get());
		this.add(TofuBlocks.LEAVES_TOFU.get(), createTofuLeavesDrops(TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.SAPLING_TOFU.get(), DEFAULT_SAPLING_DROP_RATES));

		dropSelf(TofuBlocks.SAPLING_APRICOT.get());
		this.add(TofuBlocks.LEAVES_APRICOT.get(), createApricotLeavesDrop(TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.SAPLING_APRICOT.get(), DEFAULT_SAPLING_DROP_RATES));

		dropSelf(TofuBlocks.TOFU_FLOWER.get());

		dropSelf(TofuBlocks.SPROUT_STEM.get());
		dropSelf(TofuBlocks.YELLOW_SPROUT_STEM.get());

		dropSelf(TofuBlocks.SPROUT_PLANKS.get());
		dropSelf(TofuBlocks.SPROUT_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.SPROUT_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.SPROUT_FENCE.get());
		dropSelf(TofuBlocks.SPROUT_FENCE_GATE.get());
		this.add(TofuBlocks.SPROUT_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.SPROUT_TRAPDOOR.get());
		dropSelf(TofuBlocks.SPROUT_PRESSURE_PLATE.get());
		dropSelf(TofuBlocks.SPROUT_SHELF.get());
		dropSelf(TofuBlocks.SPROUT_BUTTON.get());

		dropSelf(TofuBlocks.LEEK_GREEN_STEM.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PLANKS.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.LEEK_GREEN_FENCE.get());
		dropSelf(TofuBlocks.LEEK_GREEN_FENCE_GATE.get());
		this.add(TofuBlocks.LEEK_GREEN_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.LEEK_GREEN_TRAPDOOR.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.get());
		dropSelf(TofuBlocks.LEEK_GREEN_SHELF.get());
		dropSelf(TofuBlocks.LEEK_GREEN_BUTTON.get());

		dropSelf(TofuBlocks.LEEK_STEM.get());
		dropSelf(TofuBlocks.LEEK_PLANKS.get());
		dropSelf(TofuBlocks.LEEK_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.LEEK_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.LEEK_FENCE.get());
		dropSelf(TofuBlocks.LEEK_FENCE_GATE.get());
		this.add(TofuBlocks.LEEK_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.LEEK_TRAPDOOR.get());
		dropSelf(TofuBlocks.LEEK_PRESSURE_PLATE.get());
		dropSelf(TofuBlocks.LEEK_SHELF.get());
		dropSelf(TofuBlocks.LEEK_BUTTON.get());

		dropSelf(TofuBlocks.ZUNDA_TOFU_MUSHROOM.get());
		this.add(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get(), createZundaMushroomDrop(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get(), TofuBlocks.ZUNDA_TOFU_MUSHROOM.get(), RARE_SAPLING_DROP_RATES));

		registerTofuDrop(TofuBlocks.ISHI_TOFU_STEM.get(), TofuItems.TOFU_ISHI.get());


		dropSelf(TofuBlocks.TOFU_STEM.get());
		dropSelf(TofuBlocks.TOFU_STEM_PLANKS.get());
		dropSelf(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.TOFU_STEM_FENCE.get());
		dropSelf(TofuBlocks.TOFU_STEM_FENCE_GATE.get());
		this.add(TofuBlocks.TOFU_STEM_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.TOFU_STEM_TRAPDOOR.get());
		dropSelf(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.get());
		dropSelf(TofuBlocks.TOFU_STEM_SHELF.get());
		dropSelf(TofuBlocks.TOFU_STEM_BUTTON.get());

		this.registerLeek(TofuBlocks.LEEK.get(), TofuItems.LEEK.get());
		this.add(TofuBlocks.TALL_LEEK.get(), p_314410_ -> createDoublePlantWithLeekDrops(p_314410_, TofuBlocks.LEEK.get()));

		dropOther(TofuBlocks.TOFU_FARMLAND.get(), TofuBlocks.TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.SALTPAN.get());
		dropSelf(TofuBlocks.SALT_FURNACE.get());
		dropSelf(TofuBlocks.SPROUTSJAR.get());
		dropSelf(TofuBlocks.MORIJIO.get());
		add(TofuBlocks.FOODPLATE.get(), this::createFoodPlateDrop);
		dropSelf(TofuBlocks.ZUNDAMA_BLOCK.get());

		dropSelf(TofuBlocks.RICE_BLOCK.get());
		dropSelf(TofuBlocks.SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.SALT_BLOCK.get());
		dropSelf(TofuBlocks.OKARA_BLOCK.get());
		dropSelf(TofuBlocks.GIANT_OKARA_DONUT.get());

		LootItemCondition.Builder miso = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_MISO.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MisoBarrelBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_MISO.get(), applyExplosionDecay(TofuBlocks.BARREL_MISO.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.MISO.get())).when(miso).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))).when(miso).when(ExplosionCondition.survivesExplosion())).withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuBlocks.BARREL_MISO)).when(InvertedLootItemCondition.invert(miso)).when(ExplosionCondition.survivesExplosion())));

		LootItemCondition.Builder miso_tofu = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_MISOTOFU.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_MISOTOFU.get(), applyExplosionDecay(TofuBlocks.BARREL_MISOTOFU.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.TOFU_MISO.get())).when(miso_tofu).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL)).when(miso_tofu).when(ExplosionCondition.survivesExplosion())).withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuBlocks.BARREL_MISOTOFU)).when(InvertedLootItemCondition.invert(miso_tofu)).when(ExplosionCondition.survivesExplosion())));

		LootItemCondition.Builder natto = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.NATTOBED.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.NATTOBED.get(), applyExplosionDecay(TofuBlocks.NATTOBED.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.NATTO.get())).when(natto).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6.0F))))));

		LootItemCondition.Builder nether_natto = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.NETHER_NATTOBED.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.NETHER_NATTOBED.get(), applyExplosionDecay(TofuBlocks.NETHER_NATTOBED.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.NETHER_NATTO.get())).when(nether_natto).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6.0F))))));

		LootItemCondition.Builder tofugemAdvBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_ADV_TOFUGEM.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_ADV_TOFUGEM.get(), applyExplosionDecay(TofuBlocks.BARREL_ADV_TOFUGEM.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.ADVANCE_TOFUGEM.get())).when(tofugemAdvBuilder).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))).add(LootItem.lootTableItem(Blocks.BARREL)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).add(LootItem.lootTableItem(TofuBlocks.BARREL_ADV_TOFUGEM)).when(ExplosionCondition.survivesExplosion()))));
		dropSelf(TofuBlocks.TOFU_CHIKUWA_BLOCK.get());
		dropSelf(TofuBlocks.CHIKUWA_BLOCK.get());

		registerEmpty(TofuBlocks.TOFU_CAKE.get());
		registerEmpty(TofuBlocks.ZUNDA_TOFU_CAKE.get());
		registerEmpty(TofuBlocks.SOYCHEESE_TART.get());

		dropSelf(TofuBlocks.SOY_CHEESE_BLOCK.get());
		dropSelf(TofuBlocks.SOY_NETHER_CHEESE_BLOCK.get());
		dropSelf(TofuBlocks.SOY_SOUL_CHEESE_BLOCK.get());

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
		this.add(TofuBlocks.TF_STORAGE.get(), this::createTFMechaTable);
		this.add(TofuBlocks.TF_OVEN.get(), this::createTFMechaTable);
		dropSelf(TofuBlocks.ANTENNA_BASIC.get());
		dropSelf(TofuBlocks.TOFU_WORK_STATION.get());
		dropSelf(TofuBlocks.TOFU_POT.get());
		this.add(TofuBlocks.TF_COLLECTOR.get(), this::createTFMechaTable);
		this.add(TofuBlocks.TF_CRAFTING_TABLE.get(), this::createTFMechaTable);
		this.add(TofuBlocks.TF_SATURATOR.get(), this::createTFMechaTable);
	}

	protected LootTable.Builder createFoodPlateDrop(Block block) {
		return LootTable.lootTable()
				.withPool(
						this.applyExplosionCondition(
								block,
								LootPool.lootPool()
										.setRolls(ConstantValue.exactly(1.0F))
										.add(
												LootItem.lootTableItem(block)
														.apply(
																CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
																		.include(DataComponents.CUSTOM_NAME)
																		.include(DataComponents.CONTAINER)
														)
										)
						)
				);
	}

	private LootTable.Builder createTFMechaTable(Block p_277929_) {
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.add(
										LootItem.lootTableItem(p_277929_)
												.apply(
														copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
																.include(TofuDataComponents.TF_ENERGY_DATA.get())
												)
								)

				);
	}

	protected LootTable.Builder createTofuDiamondOreDrop(Block p_124140_, Item p_124141_) {
		return applyExplosionDecay(p_124140_, createSilkTouchDispatchTable(p_124140_, LootItem.lootTableItem(p_124141_)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))));
	}

	protected LootTable.Builder createTofuGemOreDrop(Block p_124140_, Item p_124141_) {
		return applyExplosionDecay(p_124140_, createSilkTouchDispatchTable(p_124140_, LootItem.lootTableItem(p_124141_)).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))));
	}

	protected LootTable.Builder createTofuForceOreDrop(Block p_124140_, Item p_124141_) {
		return applyExplosionDecay(p_124140_, createSilkTouchDispatchTable(p_124140_, LootItem.lootTableItem(p_124141_)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F)))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)));
	}

	protected LootTable.Builder createApricotLeavesDrop(Block p_124264_, Block p_124265_, float... p_124266_) {
		return createLeavesDrops(p_124264_, p_124265_, p_124266_).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionCondition(TofuBlocks.LEAVES_APRICOT.get(), LootItem.lootTableItem(TofuItems.APRICOT.get()))).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.1F, 0.16F, 0.14F, 0.12F, 0.2F)));
	}

	protected LootTable.Builder createTofuLeavesDrops(Block p_250088_, Block p_250731_, float... p_248949_) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return this.createSilkTouchOrShearsDispatchTable(
				p_250088_,
				((LootPoolSingletonContainer.Builder<?>) this.applyExplosionCondition(p_250088_, LootItem.lootTableItem(p_250731_)))
						.when(BonusLevelTableCondition.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE), p_248949_))
		);
	}

	private LootItemCondition.Builder hasShearsOrSilkTouch() {
		return this.hasShears().or(this.hasSilkTouch());
	}

	private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
		return this.hasShearsOrSilkTouch().invert();
	}


	protected LootTable.Builder createZundaMushroomDrop(Block p_124264_, Block p_124265_, float... p_124266_) {
		return createTofuLeavesDrops(p_124264_, p_124265_, p_124266_).withPool(applyExplosionCondition(p_124264_, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(TofuItems.TOFU_ZUNDA.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.1F, 0.05F, 0.0625F, 0.12F, 0.2F))).add(LootItem.lootTableItem(TofuItems.ZUNDAMA.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.1F, 0.05F, 0.15F, 0.125F, 0.105F)));
	}

	private void registerTofuDrop(Block tofu, Item dropItem) {
		add(tofu, applyExplosionDecay(tofu, createSilkTouchOrShearsDispatchTable(tofu, LootItem.lootTableItem(dropItem)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))))));
	}

	private void registerLeek(Block tofu, Item dropItem) {
		add(tofu, applyExplosionDecay(tofu, createSilkTouchOrShearsDispatchTable(tofu, LootItem.lootTableItem(dropItem)
				.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))));
	}
	protected LootTable.Builder createDoublePlantWithLeekDrops(Block p_248590_, Block p_248735_) {
		HolderLookup.RegistryLookup<Block> registrylookup = this.registries.lookupOrThrow(Registries.BLOCK);
		LootPoolEntryContainer.Builder<?> builder = (LootPoolEntryContainer.Builder<?>) LootItem.lootTableItem(p_248735_)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
				.when(this.hasShears());
		LootPoolEntryContainer.Builder<?> builder2 = builder.otherwise(
				LootItem.lootTableItem(TofuItems.LEEK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
		);
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
								.add(this.applyExplosionCondition(p_248590_, builder2))
								.when(
										LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_248590_)
												.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))
								)
								.when(
										LocationCheck.checkLocation(
												LocationPredicate.Builder.location()
														.setBlock(
																BlockPredicate.Builder.block()
																		.of(registrylookup, p_248590_)
																		.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))
														),
												new BlockPos(0, 1, 0)
										)
								)
				)
				.withPool(
						LootPool.lootPool()
								.add(this.applyExplosionCondition(p_248590_, builder2))
								.when(
										LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_248590_)
												.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))
								)
								.when(
										LocationCheck.checkLocation(
												LocationPredicate.Builder.location()
														.setBlock(
																BlockPredicate.Builder.block()
																		.of(registrylookup, p_248590_)
																		.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))
														),
												new BlockPos(0, -1, 0)
										)
								)
				);
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