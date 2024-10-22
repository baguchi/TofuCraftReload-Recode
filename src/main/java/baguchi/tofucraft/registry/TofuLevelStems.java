package baguchi.tofucraft.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class TofuLevelStems {
	public static void bootstrapLevelStem(BootstrapContext<LevelStem> context) {
		HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
		HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

		HolderGetter<MultiNoiseBiomeSourceParameterList> multiNoiseBiomeSourceParameterLists = context.lookup(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST);
		Holder.Reference<MultiNoiseBiomeSourceParameterList> reference = multiNoiseBiomeSourceParameterLists.getOrThrow(TofuBiomeSources.TOFU_WORLD);

		NoiseBasedChunkGenerator wrappedChunkGenerator =
				new NoiseBasedChunkGenerator(
						MultiNoiseBiomeSource.createFromPreset(reference),
						noiseGenSettings.getOrThrow(TofuNoiseSettings.TOFU_WORLD));

		LevelStem stem = new LevelStem(
				dimTypes.getOrThrow(TofuDimensionTypes.TOFU_WORLD_TYPE),
				wrappedChunkGenerator);
		context.register(TofuDimensions.tofu_world_stem, stem);
	}
}
