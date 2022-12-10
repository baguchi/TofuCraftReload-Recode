package baguchan.tofucraft.data;

import baguchan.tofucraft.registry.TofuCarvers;
import baguchan.tofucraft.registry.TofuDimensionSettings;
import baguchan.tofucraft.world.gen.ModWorldFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class WorldGenerator extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, ModWorldFeatures::bootstrapConfiguredFeature)
			.add(Registries.PLACED_FEATURE, ModWorldFeatures::bootstrapPlacedFeature)
			.add(Registries.CONFIGURED_CARVER, TofuCarvers::bootstrap)
			//.add(Registries.NOISE_SETTINGS, TofuDimensionSettings::bootstrapNoiseGen)
			.add(Registries.DIMENSION_TYPE, TofuDimensionSettings::bootstrapDimensionType);

	public WorldGenerator(PackOutput output) {
		super(output, BUILDER);
	}
}