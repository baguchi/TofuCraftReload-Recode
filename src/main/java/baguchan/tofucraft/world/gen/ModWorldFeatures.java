package baguchan.tofucraft.world.gen;

import baguchan.tofucraft.world.gen.features.ModNetherFeatures;
import baguchan.tofucraft.world.gen.features.ModTreeFeatures;
import baguchan.tofucraft.world.gen.features.ModVegetationFeatures;
import baguchan.tofucraft.world.gen.features.TofuWorldFeatures;
import baguchan.tofucraft.world.placement.ModNetherPlacements;
import baguchan.tofucraft.world.placement.TofuWorldPlacements;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModWorldFeatures {
	public static void bootstrapConfiguredFeature(BootstapContext<ConfiguredFeature<?, ?>> context) {
		ModNetherFeatures.bootstrap(context);
		ModTreeFeatures.bootstrap(context);
		ModVegetationFeatures.bootstrap(context);
		TofuWorldFeatures.bootstrap(context);
	}

	public static void bootstrapPlacedFeature(BootstapContext<PlacedFeature> context) {
		ModNetherPlacements.bootstrap(context);
		TofuWorldPlacements.bootstrap(context);
	}
}
