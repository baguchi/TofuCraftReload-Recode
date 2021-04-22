package baguchan.tofucraft.world.dimension;

import baguchan.tofucraft.world.SeedHolder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.MaxMinNoiseMixer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class TofuBiomeProvider extends BiomeProvider {
	private static final Noise DEFAULT_NOISE_PARAMETERS = new Noise(-7, ImmutableList.of(Double.valueOf(1.0D), Double.valueOf(1.0D)));

	public static final MapCodec<TofuBiomeProvider> DIRECT_CODEC;

	public static final Codec<TofuBiomeProvider> CODEC;

	private final Noise temperatureParams;

	private final Noise humidityParams;

	private final Noise altitudeParams;

	private final Noise weirdnessParams;

	private final MaxMinNoiseMixer temperatureNoise;

	private final MaxMinNoiseMixer humidityNoise;

	private final MaxMinNoiseMixer altitudeNoise;

	private final MaxMinNoiseMixer weirdnessNoise;

	private final List<Pair<Biome.Attributes, Supplier<Biome>>> parameters;

	private final boolean useY;

	private final long seed;

	private final Optional<Pair<Registry<Biome>, Preset>> preset;

	static {
		DIRECT_CODEC = RecordCodecBuilder.mapCodec(p_242602_0_ -> p_242602_0_.group((App) Codec.LONG.fieldOf("seed").orElseGet(SeedHolder::getSeed).forGetter(()), (App) RecordCodecBuilder.create(()).listOf().fieldOf("biomes").forGetter(()), (App) Noise.CODEC.fieldOf("temperature_noise").forGetter(()), (App) Noise.CODEC.fieldOf("humidity_noise").forGetter(()), (App) Noise.CODEC.fieldOf("altitude_noise").forGetter(()), (App) Noise.CODEC.fieldOf("weirdness_noise").forGetter(())).apply(p_242602_0_, TofuBiomeProvider::new));
		CODEC = Codec.mapEither(DefaultBuilder.CODEC, DIRECT_CODEC).xmap(p_235277_0_ -> p_235277_0_.map(DefaultBuilder::biomeSource, Function.identity()), p_235275_0_ -> p_235275_0_.preset().<Either>map(Either::left).orElseGet(())).codec();
	}

	private TofuBiomeProvider(long p_i231640_1_, List<Pair<Biome.Attributes, Supplier<Biome>>> p_i231640_3_, Optional<Pair<Registry<Biome>, Preset>> p_i231640_4_) {
		this(p_i231640_1_, p_i231640_3_, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, p_i231640_4_);
	}

	private TofuBiomeProvider(long p_i241951_1_, List<Pair<Biome.Attributes, Supplier<Biome>>> p_i241951_3_, Noise p_i241951_4_, Noise p_i241951_5_, Noise p_i241951_6_, Noise p_i241951_7_) {
		this(p_i241951_1_, p_i241951_3_, p_i241951_4_, p_i241951_5_, p_i241951_6_, p_i241951_7_, Optional.empty());
	}

	private TofuBiomeProvider(long p_i241952_1_, List<Pair<Biome.Attributes, Supplier<Biome>>> p_i241952_3_, Noise p_i241952_4_, Noise p_i241952_5_, Noise p_i241952_6_, Noise p_i241952_7_, Optional<Pair<Registry<Biome>, Preset>> p_i241952_8_) {
		super(p_i241952_3_.stream().map(Pair::getSecond));
		this.seed = p_i241952_1_;
		this.preset = p_i241952_8_;
		this.temperatureParams = p_i241952_4_;
		this.humidityParams = p_i241952_5_;
		this.altitudeParams = p_i241952_6_;
		this.weirdnessParams = p_i241952_7_;
		this.temperatureNoise = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(p_i241952_1_), p_i241952_4_.firstOctave(), p_i241952_4_.amplitudes());
		this.humidityNoise = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(p_i241952_1_ + 1L), p_i241952_5_.firstOctave(), p_i241952_5_.amplitudes());
		this.altitudeNoise = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(p_i241952_1_ + 2L), p_i241952_6_.firstOctave(), p_i241952_6_.amplitudes());
		this.weirdnessNoise = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(p_i241952_1_ + 3L), p_i241952_7_.firstOctave(), p_i241952_7_.amplitudes());
		this.parameters = p_i241952_3_;
		this.useY = false;
	}

	protected Codec<? extends BiomeProvider> func_230319_a_() {
		return CODEC;
	}

	@OnlyIn(Dist.CLIENT)
	public BiomeProvider func_230320_a_(long p_230320_1_) {
		return new TofuBiomeProvider(p_230320_1_, this.parameters, this.temperatureParams, this.humidityParams, this.altitudeParams, this.weirdnessParams, this.preset);
	}

	private Optional<DefaultBuilder> preset() {
		return this.preset.map(p_242601_1_ -> new DefaultBuilder(p_242601_1_.getSecond(), p_242601_1_.getFirst(), this.seed));
	}

	public Biome func_225526_b_(int p_225526_1_, int p_225526_2_, int p_225526_3_) {
		int i = this.useY ? p_225526_2_ : 0;
		Biome.Attributes biome$attributes = new Biome.Attributes((float) this.temperatureNoise.func_237211_a_(p_225526_1_, i, p_225526_3_), (float) this.humidityNoise.func_237211_a_(p_225526_1_, i, p_225526_3_), (float) this.altitudeNoise.func_237211_a_(p_225526_1_, i, p_225526_3_), (float) this.weirdnessNoise.func_237211_a_(p_225526_1_, i, p_225526_3_), 0.0F);
		return this.parameters.stream().min(Comparator.comparing(p_235272_1_ -> Float.valueOf(((Biome.Attributes) p_235272_1_.getFirst()).func_235110_a_(biome$attributes))))

				.map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.field_244201_b);
	}

	public boolean stable(long p_235280_1_) {
		return (this.seed == p_235280_1_ && this.preset.isPresent() && Objects.equals(((Pair) this.preset.get()).getSecond(), Preset.NETHER));
	}

	static final class DefaultBuilder {
		public static final MapCodec<DefaultBuilder> CODEC;

		private final Preset preset;

		private final Registry<Biome> biomes;

		private final long seed;

		static {
			CODEC = RecordCodecBuilder.mapCodec(p_242630_0_ -> p_242630_0_.group((App) ResourceLocation.field_240908_a_.flatXmap((), ()).fieldOf("preset").stable().forGetter(DefaultBuilder::preset), (App) RegistryLookupCodec.func_244331_a(Registry.field_239720_u_).forGetter(DefaultBuilder::biomes), (App) Codec.LONG.fieldOf("seed").stable().forGetter(DefaultBuilder::seed)).apply(p_242630_0_, p_242630_0_.stable(DefaultBuilder::new)));
		}

		private DefaultBuilder(Preset p_i241956_1_, Registry<Biome> p_i241956_2_, long p_i241956_3_) {
			this.preset = p_i241956_1_;
			this.biomes = p_i241956_2_;
			this.seed = p_i241956_3_;
		}

		public Preset preset() {
			return this.preset;
		}

		public Registry<Biome> biomes() {
			return this.biomes;
		}

		public long seed() {
			return this.seed;
		}

		public TofuBiomeProvider biomeSource() {
			return this.preset.biomeSource(this.biomes, this.seed);
		}
	}

	static class Noise {
		private final int firstOctave;

		private final DoubleList amplitudes;

		public static final Codec<Noise> CODEC;

		static {
			CODEC = RecordCodecBuilder.create(p_242613_0_ -> p_242613_0_.group((App) Codec.INT.fieldOf("firstOctave").forGetter(Noise::firstOctave), (App) Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(Noise::amplitudes)).apply((Applicative) p_242613_0_, Noise::new));
		}

		public Noise(int p_i241954_1_, List<Double> p_i241954_2_) {
			this.firstOctave = p_i241954_1_;
			this.amplitudes = new DoubleArrayList(p_i241954_2_);
		}

		public int firstOctave() {
			return this.firstOctave;
		}

		public DoubleList amplitudes() {
			return this.amplitudes;
		}
	}

	public static class Preset {
		private static final Map<ResourceLocation, Preset> BY_NAME = Maps.newHashMap();

		public static final Preset NETHER;

		private final ResourceLocation name;

		private final Function3<Preset, Registry<Biome>, Long, TofuBiomeProvider> biomeSource;

		static {
			NETHER = new Preset(new ResourceLocation("nether"), (p_242617_0_, p_242617_1_, p_242617_2_) -> new TofuBiomeProvider(p_242617_2_.longValue(), ImmutableList.of(Pair.of(new Biome.Attributes(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), ()), Pair.of(new Biome.Attributes(0.0F, -0.5F, 0.0F, 0.0F, 0.0F), ()), Pair.of(new Biome.Attributes(0.4F, 0.0F, 0.0F, 0.0F, 0.0F), ()), Pair.of(new Biome.Attributes(0.0F, 0.5F, 0.0F, 0.0F, 0.375F), ()), Pair.of(new Biome.Attributes(-0.5F, 0.0F, 0.0F, 0.0F, 0.175F), ())), Optional.of(Pair.of(p_242617_1_, p_242617_0_))));
		}

		public Preset(ResourceLocation p_i241955_1_, Function3<Preset, Registry<Biome>, Long, TofuBiomeProvider> p_i241955_2_) {
			this.name = p_i241955_1_;
			this.biomeSource = p_i241955_2_;
			BY_NAME.put(p_i241955_1_, this);
		}

		public TofuBiomeProvider biomeSource(Registry<Biome> p_242619_1_, long p_242619_2_) {
			return this.biomeSource.apply(this, p_242619_1_, Long.valueOf(p_242619_2_));
		}
	}
}
