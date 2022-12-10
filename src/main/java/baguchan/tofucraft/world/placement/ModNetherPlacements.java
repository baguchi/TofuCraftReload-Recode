package baguchan.tofucraft.world.placement;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.features.ModNetherFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class ModNetherPlacements {

	public static final PlacementModifier NETHER_SOYBEAN_HEIGHT = HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.belowTop(-20));

	public static final ResourceKey<PlacedFeature> PATCH_NETHER_SOYBEAN_NORMAL = registerKey("patch_nether_soybean");
	public static final ResourceKey<PlacedFeature> PATCH_NETHER_SOYBEAN_CRIMSON = registerKey("patch_nether_soybean_crimson");
	public static final ResourceKey<PlacedFeature> PATCH_SOUL_SOYBEAN = registerKey("patch_soul_soybean");

	public static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(context, PATCH_NETHER_SOYBEAN_NORMAL, configuredFeature.getOrThrow(ModNetherFeatures.NETHER_SOYBEAN), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), NETHER_SOYBEAN_HEIGHT, BiomeFilter.biome()));

		PlacementUtils.register(context, PATCH_NETHER_SOYBEAN_CRIMSON, configuredFeature.getOrThrow(ModNetherFeatures.NETHER_SOYBEAN), List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), NETHER_SOYBEAN_HEIGHT, BiomeFilter.biome()));

		PlacementUtils.register(context, PATCH_SOUL_SOYBEAN, configuredFeature.getOrThrow(ModNetherFeatures.SOUL_SOYBEAN), List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), NETHER_SOYBEAN_HEIGHT, BiomeFilter.biome()));
	}
}
