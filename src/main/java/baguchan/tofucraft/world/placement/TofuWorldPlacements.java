package baguchan.tofucraft.world.placement;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.gen.features.ModVegetationFeatures;
import baguchan.tofucraft.world.gen.features.TofuWorldFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.List;

public class TofuWorldPlacements {
	public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

	public static final ResourceKey<PlacedFeature> ORE_KINU_TOFU = registerKey("ore_kinu_tofu");
	public static final ResourceKey<PlacedFeature> ORE_MINCED_TOFU = registerKey("ore_minced_tofu");

	public static final ResourceKey<PlacedFeature> ORE_TOFU_DIAMOND = registerKey("ore_tofu_diamond");
	public static final ResourceKey<PlacedFeature> ORE_TOFU_DIAMOND_LARGE = registerKey("ore_tofu_diamond_large");
	public static final ResourceKey<PlacedFeature> ORE_TOFU_DIAMOND_BURIED = registerKey("ore_tofu_diamond_buried");

	public static final ResourceKey<PlacedFeature> ORE_TOFUGEM = registerKey("ore_tofugem");
	public static final ResourceKey<PlacedFeature> ORE_TOFUGEM_LARGE = registerKey("ore_tofugem_large");


	public static final ResourceKey<PlacedFeature> PATCH_LEEK = registerKey("patch_leek");
	public static final ResourceKey<PlacedFeature> PATCH_LEEK_WASTE = registerKey("patch_leek_waste");

	public static final ResourceKey<PlacedFeature> LEEK_BONEMEAL = registerKey("leek_bonemeal");

	public static final ResourceKey<PlacedFeature> BEG_LEEK = registerKey("big_leek");

	public static final ResourceKey<PlacedFeature> TOFU_BUILDING = registerKey("tofu_building");

	public static final ResourceKey<PlacedFeature> PATCH_ZUNDA_TOFU_MUSHROOM = registerKey("patch_zunda_tofu_mushroom");

	public static final ResourceKey<PlacedFeature> BIG_ZUNDA_TOFU_MUSHROOM = registerKey("big_zunda_tofu_mushroom");

	public static final ResourceKey<PlacedFeature> TOFU_TREES_FOREST = registerKey("tofu_trees_forest");
	public static final ResourceKey<PlacedFeature> TOFU_TREES_PLAINS = registerKey("tofu_trees_plains");

	public static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);


		PlacementUtils.register(context, ORE_KINU_TOFU, configuredFeature.getOrThrow(TofuWorldFeatures.ORE_KINU_TOFU), commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
		PlacementUtils.register(context, ORE_MINCED_TOFU, configuredFeature.getOrThrow(TofuWorldFeatures.ORE_MINCED_TOFU), commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

		PlacementUtils.register(context, ORE_TOFU_DIAMOND, configuredFeature.getOrThrow(TofuWorldFeatures.ORE_DIAMOND_SMALL), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
		PlacementUtils.register(context, ORE_TOFU_DIAMOND_LARGE, configuredFeature.getOrThrow(TofuWorldFeatures.ORE_DIAMOND_LARGE), rareOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
		PlacementUtils.register(context, ORE_TOFU_DIAMOND_BURIED, configuredFeature.getOrThrow(TofuWorldFeatures.ORE_DIAMOND_BURIED), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

		PlacementUtils.register(context, ORE_TOFUGEM, configuredFeature.getOrThrow(TofuWorldFeatures.ORE_TOFUGEM_SMALL), commonOrePlacement(25, HeightRangePlacement.triangle(VerticalAnchor.belowTop(180), VerticalAnchor.belowTop(-180))));
		PlacementUtils.register(context, ORE_TOFUGEM_LARGE, configuredFeature.getOrThrow(TofuWorldFeatures.ORE_TOFUGEM_LARGE), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.aboveBottom(64))));


		PlacementUtils.register(context, PATCH_LEEK, configuredFeature.getOrThrow(TofuWorldFeatures.LEEK), NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, PATCH_LEEK_WASTE, configuredFeature.getOrThrow(TofuWorldFeatures.LEEK), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		PlacementUtils.register(context, LEEK_BONEMEAL, configuredFeature.getOrThrow(TofuWorldFeatures.LEEK), PlacementUtils.isEmpty());

		PlacementUtils.register(context, BEG_LEEK, configuredFeature.getOrThrow(TofuWorldFeatures.BIG_LEEK), PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		PlacementUtils.register(context, TOFU_BUILDING, configuredFeature.getOrThrow(TofuWorldFeatures.TOFU_BUILDING), PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		PlacementUtils.register(context, PATCH_ZUNDA_TOFU_MUSHROOM, configuredFeature.getOrThrow(TofuWorldFeatures.ZUNDA_TOFU_MUSHROOM), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		PlacementUtils.register(context, BIG_ZUNDA_TOFU_MUSHROOM, configuredFeature.getOrThrow(TofuWorldFeatures.BIG_ZUNDA_TOFU_MUSHUROOM), PlacementUtils.countExtra(3, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		PlacementUtils.register(context, TOFU_TREES_FOREST, configuredFeature.getOrThrow(ModVegetationFeatures.TOFU_TREES), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), TofuBlocks.SAPLING_TOFU.get()));
		PlacementUtils.register(context, TOFU_TREES_PLAINS, configuredFeature.getOrThrow(ModVegetationFeatures.TOFU_TREES), treePlacement(PlacementUtils.countExtra(0, 0.01F, 1), TofuBlocks.SAPLING_TOFU.get()));

	}

	private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier p_195485_) {
		return ImmutableList.<PlacementModifier>builder().add(p_195485_).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
	}

	public static List<PlacementModifier> treePlacement(PlacementModifier p_195480_) {
		return treePlacementBase(p_195480_).build();
	}

	public static List<PlacementModifier> treePlacement(PlacementModifier p_195482_, Block p_195483_) {
		return treePlacementBase(p_195482_).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(p_195483_.defaultBlockState(), BlockPos.ZERO))).build();
	}

	private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
		return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
	}

	private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
		return orePlacement(CountPlacement.of(p_195344_), p_195345_);
	}

	private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
		return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
	}
}
