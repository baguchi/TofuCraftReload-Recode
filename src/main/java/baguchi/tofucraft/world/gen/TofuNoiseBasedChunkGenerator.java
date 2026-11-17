package baguchi.tofucraft.world.gen;

import baguchi.tofucraft.registry.TofuBlocks;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class TofuNoiseBasedChunkGenerator extends NoiseBasedChunkGenerator {

	public static final MapCodec<TofuNoiseBasedChunkGenerator> CODEC = RecordCodecBuilder.mapCodec((instance) ->
			instance.group(
					BiomeSource.CODEC.fieldOf("biome_source").forGetter((chunkGenerator) -> chunkGenerator.biomeSource),
					NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((chunkGenerator) -> chunkGenerator.settings)
			).apply(instance, instance.stable(TofuNoiseBasedChunkGenerator::new)));

	public TofuNoiseBasedChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
		super(biomeSource, settings);
		super.globalFluidPicker = Suppliers.memoize(() -> createFluidPicker(settings.value()));
	}

	private static Aquifer.FluidPicker createFluidPicker(NoiseGeneratorSettings settings) {
		Aquifer.FluidStatus lavaStatus = new Aquifer.FluidStatus(-54, TofuBlocks.DOUBANJIANG.get().defaultBlockState());
		int seaLevel = settings.seaLevel();
		Aquifer.FluidStatus seaStatus = new Aquifer.FluidStatus(seaLevel, settings.defaultFluid());
		return (x, y, z) -> {
			return y < Math.min(-54, seaLevel) ? lavaStatus : seaStatus;
		};
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return CODEC;
	}
}