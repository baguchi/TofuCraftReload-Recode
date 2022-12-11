package baguchan.tofucraft.data;

import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuDimensionTypes;
import baguchan.tofucraft.registry.TofuNoiseSettings;
import baguchan.tofucraft.world.carver.TofuConfiguredWorldCarvers;
import baguchan.tofucraft.world.gen.ModWorldFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class WorldGenerator extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, ModWorldFeatures::bootstrapConfiguredFeature)
			.add(Registries.PLACED_FEATURE, ModWorldFeatures::bootstrapPlacedFeature)
			.add(Registries.BIOME, TofuBiomes::bootstrap)
			.add(Registries.CONFIGURED_CARVER, TofuConfiguredWorldCarvers::bootstrap)
			.add(Registries.NOISE_SETTINGS, TofuNoiseSettings::bootstrap)
			.add(Registries.DIMENSION_TYPE, TofuDimensionTypes::bootstrap);


	public WorldGenerator(PackOutput output) {
		super(output, WorldGenerator::createLookup);
	}

	public static HolderLookup.Provider createLookup() {
		return BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());
	}
}