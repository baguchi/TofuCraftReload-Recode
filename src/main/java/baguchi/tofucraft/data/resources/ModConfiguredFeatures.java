package baguchi.tofucraft.data.resources;

import baguchi.tofucraft.world.gen.features.ModNetherFeatures;
import baguchi.tofucraft.world.gen.features.ModTreeFeatures;
import baguchi.tofucraft.world.gen.features.ModVegetationFeatures;
import baguchi.tofucraft.world.gen.features.TofuWorldFeatures;
import baguchi.tofucraft.world.gen.placement.ModNetherPlacements;
import baguchi.tofucraft.world.gen.placement.TofuWorldPlacements;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
	public static void bootstrapConfiguredFeature(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		ModNetherFeatures.bootstrap(context);
		ModTreeFeatures.bootstrap(context);
		ModVegetationFeatures.bootstrap(context);
		TofuWorldFeatures.bootstrap(context);
	}

	public static void bootstrapPlacedFeature(BootstrapContext<PlacedFeature> context) {
		ModNetherPlacements.bootstrap(context);
		TofuWorldPlacements.bootstrap(context);
	}
}
