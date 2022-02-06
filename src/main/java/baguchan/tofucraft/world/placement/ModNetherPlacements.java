package baguchan.tofucraft.world.placement;

import baguchan.tofucraft.world.gen.feature.ModNetherFeature;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

public class ModNetherPlacements {

	public static final PlacementModifier NETHER_SOYBEAN_HEIGHT = HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.belowTop(-20));

	public static final PlacedFeature PATCH_NETHER_SOYBEAN_NORMAL = PlacementUtils.register("tofucraft:patch_nether_soybean", ModNetherFeature.NETHER_SOYBEAN.placed(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), NETHER_SOYBEAN_HEIGHT, BiomeFilter.biome()));
	public static final PlacedFeature PATCH_NETHER_SOYBEAN_CRIMSON = PlacementUtils.register("tofucraft:patch_nether_soybean_crimson", ModNetherFeature.NETHER_SOYBEAN.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), NETHER_SOYBEAN_HEIGHT, BiomeFilter.biome()));
	public static final PlacedFeature PATCH_SOUL_SOYBEAN = PlacementUtils.register("tofucraft:patch_soul_soybean", ModNetherFeature.NETHER_SOYBEAN.placed(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), NETHER_SOYBEAN_HEIGHT, BiomeFilter.biome()));

	public static void init() {

	}
}
