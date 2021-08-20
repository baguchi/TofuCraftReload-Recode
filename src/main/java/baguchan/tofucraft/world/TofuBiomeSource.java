package baguchan.tofucraft.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class TofuBiomeSource extends BiomeSource {
	private static final TofuBiomeSource.NoiseParameters DEFAULT_NOISE_PARAMETERS = new TofuBiomeSource.NoiseParameters(-7, ImmutableList.of(1.0D, 1.0D));
	public static final MapCodec<TofuBiomeSource> DIRECT_CODEC = RecordCodecBuilder.mapCodec((p_48477_) -> {
		return p_48477_.group(Codec.LONG.fieldOf("seed").orElseGet(SeedHolder::getSeed).forGetter((p_151852_) -> {
			return p_151852_.seed;
		}), RecordCodecBuilder.<Pair<Biome.ClimateParameters, Supplier<Biome>>>create((p_151838_) -> {
			return p_151838_.group(Biome.ClimateParameters.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(p_151838_, Pair::of);
		}).listOf().fieldOf("biomes").forGetter((p_151850_) -> {
			return p_151850_.parameters;
		}), TofuBiomeSource.NoiseParameters.CODEC.fieldOf("temperature_noise").forGetter((p_151848_) -> {
			return p_151848_.temperatureParams;
		}), TofuBiomeSource.NoiseParameters.CODEC.fieldOf("humidity_noise").forGetter((p_151846_) -> {
			return p_151846_.humidityParams;
		}), TofuBiomeSource.NoiseParameters.CODEC.fieldOf("altitude_noise").forGetter((p_151844_) -> {
			return p_151844_.altitudeParams;
		}), TofuBiomeSource.NoiseParameters.CODEC.fieldOf("weirdness_noise").forGetter((p_151842_) -> {
			return p_151842_.weirdnessParams;
		})).apply(p_48477_, TofuBiomeSource::new);
	});
	public static final Codec<TofuBiomeSource> CODEC = Codec.mapEither(TofuBiomeSource.PresetInstance.CODEC, DIRECT_CODEC).xmap((p_48473_) -> {
		return p_48473_.map(TofuBiomeSource.PresetInstance::biomeSource, Function.identity());
	}, (p_48471_) -> {
		return p_48471_.preset().map(Either::<TofuBiomeSource.PresetInstance, TofuBiomeSource>left).orElseGet(() -> {
			return Either.right(p_48471_);
		});
	}).codec();
	private final TofuBiomeSource.NoiseParameters temperatureParams;
	private final TofuBiomeSource.NoiseParameters humidityParams;
	private final TofuBiomeSource.NoiseParameters altitudeParams;
	private final TofuBiomeSource.NoiseParameters weirdnessParams;
	private final NormalNoise temperatureNoise;
	private final NormalNoise humidityNoise;
	private final NormalNoise altitudeNoise;
	private final NormalNoise weirdnessNoise;
	private final List<Pair<Biome.ClimateParameters, Supplier<Biome>>> parameters;
	private final boolean useY;
	private final long seed;
	private final Optional<Pair<Registry<Biome>, TofuBiomeSource.Preset>> preset;

	public TofuBiomeSource(long p_151828_, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> p_151829_) {
		this(p_151828_, p_151829_, Optional.empty());
	}

	TofuBiomeSource(long p_48456_, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> p_48457_, Optional<Pair<Registry<Biome>, TofuBiomeSource.Preset>> p_48458_) {
		this(p_48456_, p_48457_, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, p_48458_);
	}

	private TofuBiomeSource(long p_48441_, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> p_48442_, TofuBiomeSource.NoiseParameters p_48443_, TofuBiomeSource.NoiseParameters p_48444_, TofuBiomeSource.NoiseParameters p_48445_, TofuBiomeSource.NoiseParameters p_48446_) {
		this(p_48441_, p_48442_, p_48443_, p_48444_, p_48445_, p_48446_, Optional.empty());
	}

	private TofuBiomeSource(long p_48448_, List<Pair<Biome.ClimateParameters, Supplier<Biome>>> p_48449_, TofuBiomeSource.NoiseParameters p_48450_, TofuBiomeSource.NoiseParameters p_48451_, TofuBiomeSource.NoiseParameters p_48452_, TofuBiomeSource.NoiseParameters p_48453_, Optional<Pair<Registry<Biome>, TofuBiomeSource.Preset>> p_48454_) {
		super(p_48449_.stream().map(Pair::getSecond));
		this.seed = p_48448_;
		this.preset = p_48454_;
		this.temperatureParams = p_48450_;
		this.humidityParams = p_48451_;
		this.altitudeParams = p_48452_;
		this.weirdnessParams = p_48453_;
		this.temperatureNoise = NormalNoise.create(new WorldgenRandom(p_48448_), p_48450_.firstOctave(), p_48450_.amplitudes());
		this.humidityNoise = NormalNoise.create(new WorldgenRandom(p_48448_ + 1L), p_48451_.firstOctave(), p_48451_.amplitudes());
		this.altitudeNoise = NormalNoise.create(new WorldgenRandom(p_48448_ + 2L), p_48452_.firstOctave(), p_48452_.amplitudes());
		this.weirdnessNoise = NormalNoise.create(new WorldgenRandom(p_48448_ + 3L), p_48453_.firstOctave(), p_48453_.amplitudes());
		this.parameters = p_48449_;
		this.useY = false;
	}

	public static TofuBiomeSource overworld(Registry<Biome> p_151833_, long p_151834_) {
		ImmutableList<Pair<Biome.ClimateParameters, Supplier<Biome>>> immutablelist = parameters(p_151833_);
		TofuBiomeSource.NoiseParameters multinoisebiomesource$noiseparameters = new TofuBiomeSource.NoiseParameters(-9, 1.0D, 0.0D, 3.0D, 3.0D, 3.0D, 3.0D);
		TofuBiomeSource.NoiseParameters multinoisebiomesource$noiseparameters1 = new TofuBiomeSource.NoiseParameters(-7, 1.0D, 2.0D, 4.0D, 4.0D);
		TofuBiomeSource.NoiseParameters multinoisebiomesource$noiseparameters2 = new TofuBiomeSource.NoiseParameters(-9, 1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0D);
		TofuBiomeSource.NoiseParameters multinoisebiomesource$noiseparameters3 = new TofuBiomeSource.NoiseParameters(-8, 1.2D, 0.6D, 0.0D, 0.0D, 1.0D, 0.0D);
		return new TofuBiomeSource(p_151834_, immutablelist, multinoisebiomesource$noiseparameters, multinoisebiomesource$noiseparameters1, multinoisebiomesource$noiseparameters2, multinoisebiomesource$noiseparameters3, Optional.empty());
	}

	protected Codec<? extends BiomeSource> codec() {
		return CODEC;
	}

	public BiomeSource withSeed(long p_48466_) {
		return new TofuBiomeSource(p_48466_, this.parameters, this.temperatureParams, this.humidityParams, this.altitudeParams, this.weirdnessParams, this.preset);
	}

	private Optional<TofuBiomeSource.PresetInstance> preset() {
		return this.preset.map((p_48475_) -> {
			return new TofuBiomeSource.PresetInstance(p_48475_.getSecond(), p_48475_.getFirst(), this.seed);
		});
	}

	public Biome getNoiseBiome(int p_48479_, int p_48480_, int p_48481_) {
		int i = this.useY ? p_48480_ : 0;
		Biome.ClimateParameters biome$climateparameters = new Biome.ClimateParameters((float) this.temperatureNoise.getValue((double) p_48479_, (double) i, (double) p_48481_), (float) this.humidityNoise.getValue((double) p_48479_, (double) i, (double) p_48481_), (float) this.altitudeNoise.getValue((double) p_48479_, (double) i, (double) p_48481_), (float) this.weirdnessNoise.getValue((double) p_48479_, (double) i, (double) p_48481_), 0.0F);
		return this.parameters.stream().min(Comparator.comparing((p_48469_) -> {
			return p_48469_.getFirst().fitness(biome$climateparameters);
		})).map(Pair::getSecond).map(Supplier::get).orElse(net.minecraft.data.worldgen.biome.Biomes.THE_VOID);
	}

	public static ImmutableList<Pair<Biome.ClimateParameters, Supplier<Biome>>> parameters(Registry<Biome> p_151831_) {
		return ImmutableList.of(Pair.of(new Biome.ClimateParameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
			return p_151831_.getOrThrow(Biomes.PLAINS);
		}));
	}

	public boolean stable(long p_48483_) {
		return this.seed == p_48483_ && this.preset.isPresent() && Objects.equals(this.preset.get().getSecond(), TofuBiomeSource.Preset.NETHER);
	}

	static class NoiseParameters {
		private final int firstOctave;
		private final DoubleList amplitudes;
		public static final Codec<TofuBiomeSource.NoiseParameters> CODEC = RecordCodecBuilder.create((p_48510_) -> {
			return p_48510_.group(Codec.INT.fieldOf("firstOctave").forGetter(TofuBiomeSource.NoiseParameters::firstOctave), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(TofuBiomeSource.NoiseParameters::amplitudes)).apply(p_48510_, TofuBiomeSource.NoiseParameters::new);
		});

		public NoiseParameters(int p_48506_, List<Double> p_48507_) {
			this.firstOctave = p_48506_;
			this.amplitudes = new DoubleArrayList(p_48507_);
		}

		public NoiseParameters(int p_151854_, double... p_151855_) {
			this.firstOctave = p_151854_;
			this.amplitudes = new DoubleArrayList(p_151855_);
		}

		public int firstOctave() {
			return this.firstOctave;
		}

		public DoubleList amplitudes() {
			return this.amplitudes;
		}
	}

	public static class Preset {
		static final Map<ResourceLocation, TofuBiomeSource.Preset> BY_NAME = Maps.newHashMap();
		public static final TofuBiomeSource.Preset NETHER = new TofuBiomeSource.Preset(new ResourceLocation("nether"), (p_48524_, p_48525_, p_48526_) -> {
			return new TofuBiomeSource(p_48526_, ImmutableList.of(Pair.of(new Biome.ClimateParameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
				return p_48525_.getOrThrow(Biomes.NETHER_WASTES);
			}), Pair.of(new Biome.ClimateParameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F), () -> {
				return p_48525_.getOrThrow(Biomes.SOUL_SAND_VALLEY);
			}), Pair.of(new Biome.ClimateParameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
				return p_48525_.getOrThrow(Biomes.CRIMSON_FOREST);
			}), Pair.of(new Biome.ClimateParameters(0.0F, 0.5F, 0.0F, 0.0F, 0.375F), () -> {
				return p_48525_.getOrThrow(Biomes.WARPED_FOREST);
			}), Pair.of(new Biome.ClimateParameters(-0.5F, 0.0F, 0.0F, 0.0F, 0.175F), () -> {
				return p_48525_.getOrThrow(Biomes.BASALT_DELTAS);
			})), Optional.of(Pair.of(p_48525_, p_48524_)));
		});
		final ResourceLocation name;
		private final Function3<TofuBiomeSource.Preset, Registry<Biome>, Long, TofuBiomeSource> biomeSource;

		public Preset(ResourceLocation p_48518_, Function3<TofuBiomeSource.Preset, Registry<Biome>, Long, TofuBiomeSource> p_48519_) {
			this.name = p_48518_;
			this.biomeSource = p_48519_;
			BY_NAME.put(p_48518_, this);
		}

		public TofuBiomeSource biomeSource(Registry<Biome> p_48530_, long p_48531_) {
			return this.biomeSource.apply(this, p_48530_, p_48531_);
		}
	}

	static final class PresetInstance {
		public static final MapCodec<TofuBiomeSource.PresetInstance> CODEC = RecordCodecBuilder.mapCodec((p_48558_) -> {
			return p_48558_.group(ResourceLocation.CODEC.flatXmap((p_151869_) -> {
				return Optional.ofNullable(TofuBiomeSource.Preset.BY_NAME.get(p_151869_)).map(DataResult::success).orElseGet(() -> {
					return DataResult.error("Unknown preset: " + p_151869_);
				});
			}, (p_151867_) -> {
				return DataResult.success(p_151867_.name);
			}).fieldOf("preset").stable().forGetter(TofuBiomeSource.PresetInstance::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(TofuBiomeSource.PresetInstance::biomes), Codec.LONG.fieldOf("seed").stable().forGetter(TofuBiomeSource.PresetInstance::seed)).apply(p_48558_, p_48558_.stable(TofuBiomeSource.PresetInstance::new));
		});
		private final TofuBiomeSource.Preset preset;
		private final Registry<Biome> biomes;
		private final long seed;

		PresetInstance(TofuBiomeSource.Preset p_48546_, Registry<Biome> p_48547_, long p_48548_) {
			this.preset = p_48546_;
			this.biomes = p_48547_;
			this.seed = p_48548_;
		}

		public TofuBiomeSource.Preset preset() {
			return this.preset;
		}

		public Registry<Biome> biomes() {
			return this.biomes;
		}

		public long seed() {
			return this.seed;
		}

		public TofuBiomeSource biomeSource() {
			return this.preset.biomeSource(this.biomes, this.seed);
		}
	}
}