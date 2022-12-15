package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.data.resources.ModConfiguredFeatures;
import baguchan.tofucraft.data.resources.TofuConfiguredWorldCarvers;
import baguchan.tofucraft.data.resources.TofuNoiseBuilder;
import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuDimensionTypes;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuNoiseSettings;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;

import java.util.Map;

public class WorldGenerator extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrapConfiguredFeature)
			.add(Registries.PLACED_FEATURE, ModConfiguredFeatures::bootstrapPlacedFeature)
			.add(Registries.CONFIGURED_CARVER, TofuConfiguredWorldCarvers::bootstrap)
			.add(Registries.NOISE_SETTINGS, TofuNoiseBuilder::bootstrap)
			.add(Registries.DIMENSION_TYPE, TofuDimensionTypes::bootstrap)
			.add(Registries.BIOME, TofuBiomes::bootstrap);


	public WorldGenerator(PackOutput output) {
		super(output, WorldGenerator::createLookup);
	}

	public static HolderLookup.Provider createLookup() {
		return BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());
	}

	public static DataProvider createLevelStem(PackOutput output, ExistingFileHelper helper) {
		HolderLookup.Provider registry = createLookup();
		RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registry);
		HolderGetter<Biome> biomeRegistry = registry.lookupOrThrow(Registries.BIOME);
		HolderGetter<DimensionType> dimTypes = registry.lookupOrThrow(Registries.DIMENSION_TYPE);
		HolderGetter<NoiseGeneratorSettings> noiseGenSettings = registry.lookupOrThrow(Registries.NOISE_SETTINGS);

		NoiseBasedChunkGenerator wrappedChunkGenerator =
				new NoiseBasedChunkGenerator(
						TofuBiomes.TOFU_WORLD.biomeSource(biomeRegistry, true),
						noiseGenSettings.getOrThrow(TofuNoiseSettings.TOFU_WORLD));

		LevelStem stem = new LevelStem(
				dimTypes.getOrThrow(TofuDimensionTypes.TOFU_WORLD_TYPE),
				wrappedChunkGenerator);
		return new JsonCodecProvider<>(output, helper, TofuCraftReload.MODID, ops, PackType.SERVER_DATA, Registries.LEVEL_STEM.location().getPath(), LevelStem.CODEC, Map.of(TofuDimensions.tofu_world_stem.location(), stem));
	}
}