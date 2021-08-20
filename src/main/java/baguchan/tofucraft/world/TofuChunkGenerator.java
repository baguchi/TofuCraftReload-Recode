package baguchan.tofucraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.synth.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TofuChunkGenerator extends ChunkGenerator {
	public static final Codec<TofuChunkGenerator> CODEC = RecordCodecBuilder.create((p_64405_) -> {
		return p_64405_.group(BiomeSource.CODEC.fieldOf("biome_source").forGetter((p_158489_) -> {
			return p_158489_.biomeSource;
		}), Codec.LONG.fieldOf("seed").stable().orElseGet(SeedHolder::getSeed).forGetter((p_158487_) -> {
			return p_158487_.seed;
		}), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((p_158458_) -> {
			return p_158458_.settings;
		})).apply(p_64405_, p_64405_.stable(TofuChunkGenerator::new));
	});
	private static final BlockState AIR = Blocks.AIR.defaultBlockState();
	private static final BlockState[] EMPTY_COLUMN = new BlockState[0];
	private final int cellHeight;
	private final int cellWidth;
	final int cellCountX;
	final int cellCountY;
	final int cellCountZ;
	private final SurfaceNoise surfaceNoise;
	private final NormalNoise barrierNoise;
	private final NormalNoise waterLevelNoise;
	private final NormalNoise lavaNoise;
	protected final BlockState defaultBlock;
	protected final BlockState defaultFluid;
	private final long seed;
	protected final Supplier<NoiseGeneratorSettings> settings;
	private final int height;
	private final NoiseSampler sampler;
	private final BaseStoneSource baseStoneSource;
	final OreVeinifier oreVeinifier;
	final NoodleCavifier noodleCavifier;

	public TofuChunkGenerator(BiomeSource p_64337_, long p_64338_, Supplier<NoiseGeneratorSettings> p_64339_) {
		this(p_64337_, p_64337_, p_64338_, p_64339_);
	}

	private TofuChunkGenerator(BiomeSource p_64341_, BiomeSource p_64342_, long p_64343_, Supplier<NoiseGeneratorSettings> p_64344_) {
		super(p_64341_, p_64342_, p_64344_.get().structureSettings(), p_64343_);
		this.seed = p_64343_;
		NoiseGeneratorSettings noisegeneratorsettings = p_64344_.get();
		this.settings = p_64344_;
		NoiseSettings noisesettings = noisegeneratorsettings.noiseSettings();
		this.height = noisesettings.height();
		this.cellHeight = QuartPos.toBlock(noisesettings.noiseSizeVertical());
		this.cellWidth = QuartPos.toBlock(noisesettings.noiseSizeHorizontal());
		this.defaultBlock = noisegeneratorsettings.getDefaultBlock();
		this.defaultFluid = noisegeneratorsettings.getDefaultFluid();
		this.cellCountX = 16 / this.cellWidth;
		this.cellCountY = noisesettings.height() / this.cellHeight;
		this.cellCountZ = 16 / this.cellWidth;
		WorldgenRandom worldgenrandom = new WorldgenRandom(p_64343_);
		BlendedNoise blendednoise = new BlendedNoise(worldgenrandom);
		this.surfaceNoise = (SurfaceNoise) (noisesettings.useSimplexSurfaceNoise() ? new PerlinSimplexNoise(worldgenrandom, IntStream.rangeClosed(-3, 0)) : new PerlinNoise(worldgenrandom, IntStream.rangeClosed(-3, 0)));
		worldgenrandom.consumeCount(2620);
		PerlinNoise perlinnoise = new PerlinNoise(worldgenrandom, IntStream.rangeClosed(-15, 0));
		SimplexNoise simplexnoise;
		if (noisesettings.islandNoiseOverride()) {
			WorldgenRandom worldgenrandom1 = new WorldgenRandom(p_64343_);
			worldgenrandom1.consumeCount(17292);
			simplexnoise = new SimplexNoise(worldgenrandom1);
		} else {
			simplexnoise = null;
		}

		this.barrierNoise = NormalNoise.create(new SimpleRandomSource(worldgenrandom.nextLong()), -3, 1.0D);
		this.waterLevelNoise = NormalNoise.create(new SimpleRandomSource(worldgenrandom.nextLong()), -3, 1.0D, 0.0D, 2.0D);
		this.lavaNoise = NormalNoise.create(new SimpleRandomSource(worldgenrandom.nextLong()), -1, 1.0D, 0.0D);
		NoiseModifier noisemodifier;
		if (noisegeneratorsettings.isNoiseCavesEnabled()) {
			noisemodifier = new Cavifier(worldgenrandom, noisesettings.minY() / this.cellHeight);
		} else {
			noisemodifier = NoiseModifier.PASSTHROUGH;
		}

		this.sampler = new NoiseSampler(p_64341_, this.cellWidth, this.cellHeight, this.cellCountY, noisesettings, blendednoise, simplexnoise, perlinnoise, noisemodifier);
		this.baseStoneSource = new DepthBasedReplacingBaseStoneSource(p_64343_, this.defaultBlock, Blocks.DEEPSLATE.defaultBlockState(), noisegeneratorsettings);
		this.oreVeinifier = new OreVeinifier(p_64343_, this.defaultBlock, this.cellWidth, this.cellHeight, noisegeneratorsettings.noiseSettings().minY());
		this.noodleCavifier = new NoodleCavifier(p_64343_);
	}

	private boolean isAquifersEnabled() {
		return this.settings.get().isAquifersEnabled();
	}

	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	public ChunkGenerator withSeed(long p_64374_) {
		return new TofuChunkGenerator(this.biomeSource.withSeed(p_64374_), p_64374_, this.settings);
	}

	public boolean stable(long p_64376_, ResourceKey<NoiseGeneratorSettings> p_64377_) {
		return this.seed == p_64376_ && this.settings.get().stable(p_64377_);
	}

	private double[] makeAndFillNoiseColumn(int p_158392_, int p_158393_, int p_158394_, int p_158395_) {
		double[] adouble = new double[p_158395_ + 1];
		this.fillNoiseColumn(adouble, p_158392_, p_158393_, p_158394_, p_158395_);
		return adouble;
	}

	private void fillNoiseColumn(double[] p_158467_, int p_158468_, int p_158469_, int p_158470_, int p_158471_) {
		NoiseSettings noisesettings = this.settings.get().noiseSettings();
		this.sampler.fillNoiseColumn(p_158467_, p_158468_, p_158469_, noisesettings, this.getSeaLevel(), p_158470_, p_158471_);
	}

	public int getBaseHeight(int p_158405_, int p_158406_, Heightmap.Types p_158407_, LevelHeightAccessor p_158408_) {
		int i = Math.max(this.settings.get().noiseSettings().minY(), p_158408_.getMinBuildHeight());
		int j = Math.min(this.settings.get().noiseSettings().minY() + this.settings.get().noiseSettings().height(), p_158408_.getMaxBuildHeight());
		int k = Mth.intFloorDiv(i, this.cellHeight);
		int l = Mth.intFloorDiv(j - i, this.cellHeight);
		return l <= 0 ? p_158408_.getMinBuildHeight() : this.iterateNoiseColumn(p_158405_, p_158406_, (BlockState[]) null, p_158407_.isOpaque(), k, l).orElse(p_158408_.getMinBuildHeight());
	}

	public NoiseColumn getBaseColumn(int p_158401_, int p_158402_, LevelHeightAccessor p_158403_) {
		int i = Math.max(this.settings.get().noiseSettings().minY(), p_158403_.getMinBuildHeight());
		int j = Math.min(this.settings.get().noiseSettings().minY() + this.settings.get().noiseSettings().height(), p_158403_.getMaxBuildHeight());
		int k = Mth.intFloorDiv(i, this.cellHeight);
		int l = Mth.intFloorDiv(j - i, this.cellHeight);
		if (l <= 0) {
			return new NoiseColumn(i, EMPTY_COLUMN);
		} else {
			BlockState[] ablockstate = new BlockState[l * this.cellHeight];
			this.iterateNoiseColumn(p_158401_, p_158402_, ablockstate, (Predicate<BlockState>) null, k, l);
			return new NoiseColumn(i, ablockstate);
		}
	}

	public BaseStoneSource getBaseStoneSource() {
		return this.baseStoneSource;
	}

	private OptionalInt iterateNoiseColumn(int p_158414_, int p_158415_, @Nullable BlockState[] p_158416_, @Nullable Predicate<BlockState> p_158417_, int p_158418_, int p_158419_) {
		int i = SectionPos.blockToSectionCoord(p_158414_);
		int j = SectionPos.blockToSectionCoord(p_158415_);
		int k = Math.floorDiv(p_158414_, this.cellWidth);
		int l = Math.floorDiv(p_158415_, this.cellWidth);
		int i1 = Math.floorMod(p_158414_, this.cellWidth);
		int j1 = Math.floorMod(p_158415_, this.cellWidth);
		double d0 = (double) i1 / (double) this.cellWidth;
		double d1 = (double) j1 / (double) this.cellWidth;
		double[][] adouble = new double[][]{this.makeAndFillNoiseColumn(k, l, p_158418_, p_158419_), this.makeAndFillNoiseColumn(k, l + 1, p_158418_, p_158419_), this.makeAndFillNoiseColumn(k + 1, l, p_158418_, p_158419_), this.makeAndFillNoiseColumn(k + 1, l + 1, p_158418_, p_158419_)};
		Aquifer aquifer = this.getAquifer(p_158418_, p_158419_, new ChunkPos(i, j));

		for (int k1 = p_158419_ - 1; k1 >= 0; --k1) {
			double d2 = adouble[0][k1];
			double d3 = adouble[1][k1];
			double d4 = adouble[2][k1];
			double d5 = adouble[3][k1];
			double d6 = adouble[0][k1 + 1];
			double d7 = adouble[1][k1 + 1];
			double d8 = adouble[2][k1 + 1];
			double d9 = adouble[3][k1 + 1];

			for (int l1 = this.cellHeight - 1; l1 >= 0; --l1) {
				double d10 = (double) l1 / (double) this.cellHeight;
				double d11 = Mth.lerp3(d10, d0, d1, d2, d6, d4, d8, d3, d7, d5, d9);
				int i2 = k1 * this.cellHeight + l1;
				int j2 = i2 + p_158418_ * this.cellHeight;
				BlockState blockstate = this.updateNoiseAndGenerateBaseState(aquifer, this.baseStoneSource, NoiseModifier.PASSTHROUGH, p_158414_, j2, p_158415_, d11);
				if (p_158416_ != null) {
					p_158416_[i2] = blockstate;
				}

				if (p_158417_ != null && p_158417_.test(blockstate)) {
					return OptionalInt.of(j2 + 1);
				}
			}
		}

		return OptionalInt.empty();
	}

	private Aquifer getAquifer(int p_158397_, int p_158398_, ChunkPos p_158399_) {
		return !this.isAquifersEnabled() ? Aquifer.createDisabled(this.getSeaLevel(), this.defaultFluid) : Aquifer.create(p_158399_, this.barrierNoise, this.waterLevelNoise, this.lavaNoise, this.settings.get(), this.sampler, p_158397_ * this.cellHeight, p_158398_ * this.cellHeight);
	}

	protected BlockState updateNoiseAndGenerateBaseState(Aquifer p_158441_, BaseStoneSource p_158442_, NoiseModifier p_158443_, int p_158444_, int p_158445_, int p_158446_, double p_158447_) {
		double d0 = Mth.clamp(p_158447_ / 200.0D, -1.0D, 1.0D);
		d0 = d0 / 2.0D - d0 * d0 * d0 / 24.0D;
		d0 = p_158443_.modifyNoise(d0, p_158444_, p_158445_, p_158446_);
		return p_158441_.computeState(p_158442_, p_158444_, p_158445_, p_158446_, d0);
	}

	public void buildSurfaceAndBedrock(WorldGenRegion p_64381_, ChunkAccess p_64382_) {
		ChunkPos chunkpos = p_64382_.getPos();
		int i = chunkpos.x;
		int j = chunkpos.z;
		WorldgenRandom worldgenrandom = new WorldgenRandom();
		worldgenrandom.setBaseChunkSeed(i, j);
		ChunkPos chunkpos1 = p_64382_.getPos();
		int k = chunkpos1.getMinBlockX();
		int l = chunkpos1.getMinBlockZ();
		double d0 = 0.0625D;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int i1 = 0; i1 < 16; ++i1) {
			for (int j1 = 0; j1 < 16; ++j1) {
				int k1 = k + i1;
				int l1 = l + j1;
				int i2 = p_64382_.getHeight(Heightmap.Types.WORLD_SURFACE_WG, i1, j1) + 1;
				double d1 = this.surfaceNoise.getSurfaceNoiseValue((double) k1 * 0.0625D, (double) l1 * 0.0625D, 0.0625D, (double) i1 * 0.0625D) * 15.0D;
				int j2 = this.settings.get().getMinSurfaceLevel();
				p_64381_.getBiome(blockpos$mutableblockpos.set(k + i1, i2, l + j1)).buildSurfaceAt(worldgenrandom, p_64382_, k1, l1, i2, d1, this.defaultBlock, this.defaultFluid, this.getSeaLevel(), j2, p_64381_.getSeed());
			}
		}

		this.setBedrock(p_64382_, worldgenrandom);
	}

	private void setBedrock(ChunkAccess p_64400_, Random p_64401_) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int i = p_64400_.getPos().getMinBlockX();
		int j = p_64400_.getPos().getMinBlockZ();
		NoiseGeneratorSettings noisegeneratorsettings = this.settings.get();
		int k = noisegeneratorsettings.noiseSettings().minY();
		int l = k + noisegeneratorsettings.getBedrockFloorPosition();
		int i1 = this.height - 1 + k - noisegeneratorsettings.getBedrockRoofPosition();
		int j1 = 5;
		int k1 = p_64400_.getMinBuildHeight();
		int l1 = p_64400_.getMaxBuildHeight();
		boolean flag = i1 + 5 - 1 >= k1 && i1 < l1;
		boolean flag1 = l + 5 - 1 >= k1 && l < l1;
		if (flag || flag1) {
			for (BlockPos blockpos : BlockPos.betweenClosed(i, 0, j, i + 15, 0, j + 15)) {
				if (flag) {
					for (int i2 = 0; i2 < 5; ++i2) {
						if (i2 <= p_64401_.nextInt(5)) {
							p_64400_.setBlockState(blockpos$mutableblockpos.set(blockpos.getX(), i1 - i2, blockpos.getZ()), Blocks.BEDROCK.defaultBlockState(), false);
						}
					}
				}

				if (flag1) {
					for (int j2 = 4; j2 >= 0; --j2) {
						if (j2 <= p_64401_.nextInt(5)) {
							p_64400_.setBlockState(blockpos$mutableblockpos.set(blockpos.getX(), l + j2, blockpos.getZ()), Blocks.BEDROCK.defaultBlockState(), false);
						}
					}
				}
			}

		}
	}

	public CompletableFuture<ChunkAccess> fillFromNoise(Executor p_158463_, StructureFeatureManager p_158464_, ChunkAccess p_158465_) {
		NoiseSettings noisesettings = this.settings.get().noiseSettings();
		int i = Math.max(noisesettings.minY(), p_158465_.getMinBuildHeight());
		int j = Math.min(noisesettings.minY() + noisesettings.height(), p_158465_.getMaxBuildHeight());
		int k = Mth.intFloorDiv(i, this.cellHeight);
		int l = Mth.intFloorDiv(j - i, this.cellHeight);
		if (l <= 0) {
			return CompletableFuture.completedFuture(p_158465_);
		} else {
			int i1 = p_158465_.getSectionIndex(l * this.cellHeight - 1 + i);
			int j1 = p_158465_.getSectionIndex(i);
			return CompletableFuture.supplyAsync(() -> {
				Set<LevelChunkSection> set = Sets.newHashSet();

				ChunkAccess chunkaccess;
				try {
					for (int k1 = i1; k1 >= j1; --k1) {
						LevelChunkSection levelchunksection = p_158465_.getOrCreateSection(k1);
						levelchunksection.acquire();
						set.add(levelchunksection);
					}

					chunkaccess = this.doFill(p_158464_, p_158465_, k, l);
				} finally {
					for (LevelChunkSection levelchunksection1 : set) {
						levelchunksection1.release();
					}

				}

				return chunkaccess;
			}, Util.backgroundExecutor());
		}
	}

	private ChunkAccess doFill(StructureFeatureManager p_158428_, ChunkAccess p_158429_, int p_158430_, int p_158431_) {
		Heightmap heightmap = p_158429_.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
		Heightmap heightmap1 = p_158429_.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
		ChunkPos chunkpos = p_158429_.getPos();
		int i = chunkpos.getMinBlockX();
		int j = chunkpos.getMinBlockZ();
		Aquifer aquifer = this.getAquifer(p_158430_, p_158431_, chunkpos);
		NoiseInterpolator noiseinterpolator = new NoiseInterpolator(this.cellCountX, p_158431_, this.cellCountZ, chunkpos, p_158430_, this::fillNoiseColumn);
		List<NoiseInterpolator> list = Lists.newArrayList(noiseinterpolator);
		Consumer<NoiseInterpolator> consumer = list::add;
		DoubleFunction<BaseStoneSource> doublefunction = this.createBaseStoneSource(p_158430_, chunkpos, consumer);
		DoubleFunction<NoiseModifier> doublefunction1 = this.createCaveNoiseModifier(p_158430_, chunkpos, consumer);
		list.forEach(NoiseInterpolator::initializeForFirstCellX);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int k = 0; k < this.cellCountX; ++k) {
			int l = k;
			list.forEach((p_158426_) -> {
				p_158426_.advanceCellX(l);
			});

			for (int i1 = 0; i1 < this.cellCountZ; ++i1) {
				LevelChunkSection levelchunksection = p_158429_.getOrCreateSection(p_158429_.getSectionsCount() - 1);

				for (int j1 = p_158431_ - 1; j1 >= 0; --j1) {
					int k1 = i1;
					int l1 = j1;
					list.forEach((p_158412_) -> {
						p_158412_.selectCellYZ(l1, k1);
					});

					for (int i2 = this.cellHeight - 1; i2 >= 0; --i2) {
						int j2 = (p_158430_ + j1) * this.cellHeight + i2;
						int k2 = j2 & 15;
						int l2 = p_158429_.getSectionIndex(j2);
						if (p_158429_.getSectionIndex(levelchunksection.bottomBlockY()) != l2) {
							levelchunksection = p_158429_.getOrCreateSection(l2);
						}

						double d0 = (double) i2 / (double) this.cellHeight;
						list.forEach((p_158476_) -> {
							p_158476_.updateForY(d0);
						});

						for (int i3 = 0; i3 < this.cellWidth; ++i3) {
							int j3 = i + k * this.cellWidth + i3;
							int k3 = j3 & 15;
							double d1 = (double) i3 / (double) this.cellWidth;
							list.forEach((p_158390_) -> {
								p_158390_.updateForX(d1);
							});

							for (int l3 = 0; l3 < this.cellWidth; ++l3) {
								int i4 = j + i1 * this.cellWidth + l3;
								int j4 = i4 & 15;
								double d2 = (double) l3 / (double) this.cellWidth;
								double d3 = noiseinterpolator.calculateValue(d2);
								BlockState blockstate = this.updateNoiseAndGenerateBaseState(aquifer, doublefunction.apply(d2), doublefunction1.apply(d2), j3, j2, i4, d3);
								if (blockstate != AIR) {
									if (blockstate.getLightEmission() != 0 && p_158429_ instanceof ProtoChunk) {
										blockpos$mutableblockpos.set(j3, j2, i4);
										((ProtoChunk) p_158429_).addLight(blockpos$mutableblockpos);
									}

									levelchunksection.setBlockState(k3, k2, j4, blockstate, false);
									heightmap.update(k3, j2, j4, blockstate);
									heightmap1.update(k3, j2, j4, blockstate);
									if (aquifer.shouldScheduleFluidUpdate() && !blockstate.getFluidState().isEmpty()) {
										blockpos$mutableblockpos.set(j3, j2, i4);
										p_158429_.getLiquidTicks().scheduleTick(blockpos$mutableblockpos, blockstate.getFluidState().getType(), 0);
									}
								}
							}
						}
					}
				}
			}

			list.forEach(NoiseInterpolator::swapSlices);
		}

		return p_158429_;
	}

	private DoubleFunction<NoiseModifier> createCaveNoiseModifier(int p_158421_, ChunkPos p_158422_, Consumer<NoiseInterpolator> p_158423_) {
		if (!this.settings.get().isNoodleCavesEnabled()) {
			return (p_158473_) -> {
				return NoiseModifier.PASSTHROUGH;
			};
		} else {
			TofuChunkGenerator.NoodleCaveNoiseModifier noisebasedchunkgenerator$noodlecavenoisemodifier = new TofuChunkGenerator.NoodleCaveNoiseModifier(p_158422_, p_158421_);
			noisebasedchunkgenerator$noodlecavenoisemodifier.listInterpolators(p_158423_);
			return noisebasedchunkgenerator$noodlecavenoisemodifier::prepare;
		}
	}

	private DoubleFunction<BaseStoneSource> createBaseStoneSource(int p_158478_, ChunkPos p_158479_, Consumer<NoiseInterpolator> p_158480_) {
		if (!this.settings.get().isOreVeinsEnabled()) {
			return (p_158387_) -> {
				return this.baseStoneSource;
			};
		} else {
			TofuChunkGenerator.OreVeinNoiseSource noisebasedchunkgenerator$oreveinnoisesource = new TofuChunkGenerator.OreVeinNoiseSource(p_158479_, p_158478_, this.seed + 1L);
			noisebasedchunkgenerator$oreveinnoisesource.listInterpolators(p_158480_);
			BaseStoneSource basestonesource = (p_158450_, p_158451_, p_158452_) -> {
				BlockState blockstate = noisebasedchunkgenerator$oreveinnoisesource.getBaseBlock(p_158450_, p_158451_, p_158452_);
				return blockstate != this.defaultBlock ? blockstate : this.baseStoneSource.getBaseBlock(p_158450_, p_158451_, p_158452_);
			};
			return (p_158456_) -> {
				noisebasedchunkgenerator$oreveinnoisesource.prepare(p_158456_);
				return basestonesource;
			};
		}
	}

	protected Aquifer createAquifer(ChunkAccess p_158438_) {
		ChunkPos chunkpos = p_158438_.getPos();
		int i = Math.max(this.settings.get().noiseSettings().minY(), p_158438_.getMinBuildHeight());
		int j = Mth.intFloorDiv(i, this.cellHeight);
		return this.getAquifer(j, this.cellCountY, chunkpos);
	}

	public int getGenDepth() {
		return this.height;
	}

	public int getSeaLevel() {
		return this.settings.get().seaLevel();
	}

	public int getMinY() {
		return this.settings.get().noiseSettings().minY();
	}

	public WeightedRandomList<MobSpawnSettings.SpawnerData> getMobsAt(Biome p_158433_, StructureFeatureManager p_158434_, MobCategory p_158435_, BlockPos p_158436_) {
		WeightedRandomList<MobSpawnSettings.SpawnerData> spawns = net.minecraftforge.common.world.StructureSpawnManager.getStructureSpawns(p_158434_, p_158435_, p_158436_);
		if (spawns != null) return spawns;
		if (false) {//Forge: We handle these hardcoded cases above in StructureSpawnManager#getStructureSpawns, but allow for insideOnly to be changed and allow for creatures to be spawned in ones other than just the witch hut
			if (p_158434_.getStructureAt(p_158436_, true, StructureFeature.SWAMP_HUT).isValid()) {
				if (p_158435_ == MobCategory.MONSTER) {
					return StructureFeature.SWAMP_HUT.getSpecialEnemies();
				}

				if (p_158435_ == MobCategory.CREATURE) {
					return StructureFeature.SWAMP_HUT.getSpecialAnimals();
				}
			}

			if (p_158435_ == MobCategory.MONSTER) {
				if (p_158434_.getStructureAt(p_158436_, false, StructureFeature.PILLAGER_OUTPOST).isValid()) {
					return StructureFeature.PILLAGER_OUTPOST.getSpecialEnemies();
				}

				if (p_158434_.getStructureAt(p_158436_, false, StructureFeature.OCEAN_MONUMENT).isValid()) {
					return StructureFeature.OCEAN_MONUMENT.getSpecialEnemies();
				}

				if (p_158434_.getStructureAt(p_158436_, true, StructureFeature.NETHER_BRIDGE).isValid()) {
					return StructureFeature.NETHER_BRIDGE.getSpecialEnemies();
				}
			}
		}

		return p_158435_ == MobCategory.UNDERGROUND_WATER_CREATURE && p_158434_.getStructureAt(p_158436_, false, StructureFeature.OCEAN_MONUMENT).isValid() ? StructureFeature.OCEAN_MONUMENT.getSpecialUndergroundWaterAnimals() : super.getMobsAt(p_158433_, p_158434_, p_158435_, p_158436_);
	}

	public void spawnOriginalMobs(WorldGenRegion p_64379_) {
		if (!this.settings.get().disableMobGeneration()) {
			ChunkPos chunkpos = p_64379_.getCenter();
			Biome biome = p_64379_.getBiome(chunkpos.getWorldPosition());
			WorldgenRandom worldgenrandom = new WorldgenRandom();
			worldgenrandom.setDecorationSeed(p_64379_.getSeed(), chunkpos.getMinBlockX(), chunkpos.getMinBlockZ());
			NaturalSpawner.spawnMobsForChunkGeneration(p_64379_, biome, chunkpos, worldgenrandom);
		}
	}

	class NoodleCaveNoiseModifier implements NoiseModifier {
		private final NoiseInterpolator toggle;
		private final NoiseInterpolator thickness;
		private final NoiseInterpolator ridgeA;
		private final NoiseInterpolator ridgeB;
		private double factorZ;

		public NoodleCaveNoiseModifier(ChunkPos p_158501_, int p_158502_) {
			this.toggle = new NoiseInterpolator(TofuChunkGenerator.this.cellCountX, TofuChunkGenerator.this.cellCountY, TofuChunkGenerator.this.cellCountZ, p_158501_, p_158502_, TofuChunkGenerator.this.noodleCavifier::fillToggleNoiseColumn);
			this.thickness = new NoiseInterpolator(TofuChunkGenerator.this.cellCountX, TofuChunkGenerator.this.cellCountY, TofuChunkGenerator.this.cellCountZ, p_158501_, p_158502_, TofuChunkGenerator.this.noodleCavifier::fillThicknessNoiseColumn);
			this.ridgeA = new NoiseInterpolator(TofuChunkGenerator.this.cellCountX, TofuChunkGenerator.this.cellCountY, TofuChunkGenerator.this.cellCountZ, p_158501_, p_158502_, TofuChunkGenerator.this.noodleCavifier::fillRidgeANoiseColumn);
			this.ridgeB = new NoiseInterpolator(TofuChunkGenerator.this.cellCountX, TofuChunkGenerator.this.cellCountY, TofuChunkGenerator.this.cellCountZ, p_158501_, p_158502_, TofuChunkGenerator.this.noodleCavifier::fillRidgeBNoiseColumn);
		}

		public NoiseModifier prepare(double p_158504_) {
			this.factorZ = p_158504_;
			return this;
		}

		public double modifyNoise(double p_158508_, int p_158509_, int p_158510_, int p_158511_) {
			double d0 = this.toggle.calculateValue(this.factorZ);
			double d1 = this.thickness.calculateValue(this.factorZ);
			double d2 = this.ridgeA.calculateValue(this.factorZ);
			double d3 = this.ridgeB.calculateValue(this.factorZ);
			return TofuChunkGenerator.this.noodleCavifier.noodleCavify(p_158508_, p_158509_, p_158510_, p_158511_, d0, d1, d2, d3, TofuChunkGenerator.this.getMinY());
		}

		public void listInterpolators(Consumer<NoiseInterpolator> p_158506_) {
			p_158506_.accept(this.toggle);
			p_158506_.accept(this.thickness);
			p_158506_.accept(this.ridgeA);
			p_158506_.accept(this.ridgeB);
		}
	}

	class OreVeinNoiseSource implements BaseStoneSource {
		private final NoiseInterpolator veininess;
		private final NoiseInterpolator veinA;
		private final NoiseInterpolator veinB;
		private double factorZ;
		private final long seed;
		private final WorldgenRandom random = new WorldgenRandom();

		public OreVeinNoiseSource(ChunkPos p_158521_, int p_158522_, long p_158523_) {
			this.veininess = new NoiseInterpolator(TofuChunkGenerator.this.cellCountX, TofuChunkGenerator.this.cellCountY, TofuChunkGenerator.this.cellCountZ, p_158521_, p_158522_, TofuChunkGenerator.this.oreVeinifier::fillVeininessNoiseColumn);
			this.veinA = new NoiseInterpolator(TofuChunkGenerator.this.cellCountX, TofuChunkGenerator.this.cellCountY, TofuChunkGenerator.this.cellCountZ, p_158521_, p_158522_, TofuChunkGenerator.this.oreVeinifier::fillNoiseColumnA);
			this.veinB = new NoiseInterpolator(TofuChunkGenerator.this.cellCountX, TofuChunkGenerator.this.cellCountY, TofuChunkGenerator.this.cellCountZ, p_158521_, p_158522_, TofuChunkGenerator.this.oreVeinifier::fillNoiseColumnB);
			this.seed = p_158523_;
		}

		public void listInterpolators(Consumer<NoiseInterpolator> p_158527_) {
			p_158527_.accept(this.veininess);
			p_158527_.accept(this.veinA);
			p_158527_.accept(this.veinB);
		}

		public void prepare(double p_158525_) {
			this.factorZ = p_158525_;
		}

		public BlockState getBaseBlock(int p_158529_, int p_158530_, int p_158531_) {
			double d0 = this.veininess.calculateValue(this.factorZ);
			double d1 = this.veinA.calculateValue(this.factorZ);
			double d2 = this.veinB.calculateValue(this.factorZ);
			this.random.setBaseStoneSeed(this.seed, p_158529_, p_158530_, p_158531_);
			return TofuChunkGenerator.this.oreVeinifier.oreVeinify(this.random, p_158529_, p_158530_, p_158531_, d0, d1, d2);
		}
	}
}
