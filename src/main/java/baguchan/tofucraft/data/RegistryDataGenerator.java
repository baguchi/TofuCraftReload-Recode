package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.data.resources.ModConfiguredFeatures;
import baguchan.tofucraft.data.resources.TofuConfiguredWorldCarvers;
import baguchan.tofucraft.data.resources.TofuNoiseBuilder;
import baguchan.tofucraft.registry.TofuBiomeSources;
import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuDimensionTypes;
import baguchan.tofucraft.registry.TofuEnchantments;
import baguchan.tofucraft.registry.TofuLevelStems;
import baguchan.tofucraft.registry.TofuTrimMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.NOISE, (context) -> {
			})
			.add(Registries.DENSITY_FUNCTION, TofuNoiseBuilder::bootstrapDensity)
			.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrapConfiguredFeature)
			.add(Registries.PLACED_FEATURE, ModConfiguredFeatures::bootstrapPlacedFeature)
			.add(Registries.CONFIGURED_CARVER, TofuConfiguredWorldCarvers::bootstrap)
			.add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, TofuBiomeSources::bootstrapPreset)
			.add(Registries.NOISE_SETTINGS, TofuNoiseBuilder::bootstrap)
			.add(Registries.DIMENSION_TYPE, TofuDimensionTypes::bootstrap)
			.add(Registries.BIOME, TofuBiomes::bootstrap)
			.add(Registries.LEVEL_STEM, TofuLevelStems::bootstrapLevelStem)
			.add(Registries.TRIM_MATERIAL, TofuTrimMaterials::bootstrap)
			.add(Registries.ENCHANTMENT, TofuEnchantments::bootstrap);


	public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of("minecraft", TofuCraftReload.MODID));
	}
}