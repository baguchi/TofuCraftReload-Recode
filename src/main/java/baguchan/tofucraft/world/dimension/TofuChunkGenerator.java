package baguchan.tofucraft.world.dimension;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.SeedHolder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.settings.NoiseSettings;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TofuChunkGenerator extends ChunkGenerator {
	public static final Codec<TofuChunkGenerator> CODEC = RecordCodecBuilder.create((p_236091_0_) -> {
		return p_236091_0_.group(BiomeProvider.CODEC.fieldOf("biome_source").forGetter((p_236096_0_) -> {
			return p_236096_0_.biomeSource;
		}), Codec.LONG.fieldOf("seed").orElseGet(SeedHolder::getSeed).forGetter((p_236093_0_) -> {
			return p_236093_0_.seed;
		}), DimensionSettings.CODEC.fieldOf("settings").forGetter((p_236090_0_) -> {
			return p_236090_0_.settings;
		})).apply(p_236091_0_, p_236091_0_.stable(TofuChunkGenerator::new));
	});
	private static final float[] BEARD_KERNEL = Util.make(new float[13824], (p_236094_0_) -> {
		for (int i = 0; i < 24; ++i) {
			for (int j = 0; j < 24; ++j) {
				for (int k = 0; k < 24; ++k) {
					p_236094_0_[i * 24 * 24 + j * 24 + k] = (float) computeContribution(j - 12, k - 12, i - 12);
				}
			}
		}

	});
	private static final float[] BIOME_WEIGHTS = Util.make(new float[25], (p_236092_0_) -> {
		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
				p_236092_0_[i + 2 + (j + 2) * 5] = f;
			}
		}

	});
	private static final BlockState AIR = Blocks.AIR.defaultBlockState();
	private final int chunkHeight;
	private final int chunkWidth;
	private final int chunkCountX;
	private final int chunkCountY;
	private final int chunkCountZ;
	protected final SharedSeedRandom random;
	private final OctavesNoiseGenerator minLimitPerlinNoise;
	private final OctavesNoiseGenerator maxLimitPerlinNoise;
	private final OctavesNoiseGenerator mainPerlinNoise;
	private final INoiseGenerator surfaceNoise;
	private final OctavesNoiseGenerator depthNoise;
	@Nullable
	private final SimplexNoiseGenerator islandNoise;
	protected final BlockState defaultBlock;
	protected final BlockState defaultFluid;
	private final long seed;
	protected final Supplier<DimensionSettings> settings;
	private final int height;

	public TofuChunkGenerator(BiomeProvider p_i241975_1_, long p_i241975_2_, Supplier<DimensionSettings> p_i241975_4_) {
		this(p_i241975_1_, p_i241975_1_, p_i241975_2_, p_i241975_4_);
	}

	private TofuChunkGenerator(BiomeProvider p_i241976_1_, BiomeProvider p_i241976_2_, long p_i241976_3_, Supplier<DimensionSettings> p_i241976_5_) {
		super(p_i241976_1_, p_i241976_2_, p_i241976_5_.get().structureSettings(), p_i241976_3_);
		this.seed = p_i241976_3_;
		DimensionSettings dimensionsettings = p_i241976_5_.get();
		this.settings = p_i241976_5_;
		NoiseSettings noisesettings = dimensionsettings.noiseSettings();
		this.height = noisesettings.height();
		this.chunkHeight = noisesettings.noiseSizeVertical() * 4;
		this.chunkWidth = noisesettings.noiseSizeHorizontal() * 4;
		this.defaultBlock = dimensionsettings.getDefaultBlock();
		this.defaultFluid = dimensionsettings.getDefaultFluid();
		this.chunkCountX = 16 / this.chunkWidth;
		this.chunkCountY = noisesettings.height() / this.chunkHeight;
		this.chunkCountZ = 16 / this.chunkWidth;
		this.random = new SharedSeedRandom(p_i241976_3_);
		this.minLimitPerlinNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-15, 0));
		this.maxLimitPerlinNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-15, 0));
		this.mainPerlinNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-7, 0));
		this.surfaceNoise = noisesettings.useSimplexSurfaceNoise() ? new PerlinNoiseGenerator(this.random, IntStream.rangeClosed(-3, 0)) : new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-3, 0));
		this.random.consumeCount(2620);
		this.depthNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-15, 0));
		if (noisesettings.islandNoiseOverride()) {
			SharedSeedRandom sharedseedrandom = new SharedSeedRandom(p_i241976_3_);
			sharedseedrandom.consumeCount(17292);
			this.islandNoise = new SimplexNoiseGenerator(sharedseedrandom);
		} else {
			this.islandNoise = null;
		}

	}

	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@OnlyIn(Dist.CLIENT)
	public ChunkGenerator withSeed(long p_230349_1_) {
		return new TofuChunkGenerator(this.biomeSource.withSeed(p_230349_1_), p_230349_1_, this.settings);
	}

	public boolean stable(long p_236088_1_, RegistryKey<DimensionSettings> p_236088_3_) {
		return this.seed == p_236088_1_ && this.settings.get().stable(p_236088_3_);
	}

	private double sampleAndClampNoise(int p_222552_1_, int p_222552_2_, int p_222552_3_, double p_222552_4_, double p_222552_6_, double p_222552_8_, double p_222552_10_) {
		double d0 = 0.0D;
		double d1 = 0.0D;
		double d2 = 0.0D;
		boolean flag = true;
		double d3 = 1.0D;

		for (int i = 0; i < 16; ++i) {
			double d4 = OctavesNoiseGenerator.wrap((double) p_222552_1_ * p_222552_4_ * d3);
			double d5 = OctavesNoiseGenerator.wrap((double) p_222552_2_ * p_222552_6_ * d3);
			double d6 = OctavesNoiseGenerator.wrap((double) p_222552_3_ * p_222552_4_ * d3);
			double d7 = p_222552_6_ * d3;
			ImprovedNoiseGenerator improvednoisegenerator = this.minLimitPerlinNoise.getOctaveNoise(i);
			if (improvednoisegenerator != null) {
				d0 += improvednoisegenerator.noise(d4, d5, d6, d7, (double) p_222552_2_ * d7) / d3;
			}

			ImprovedNoiseGenerator improvednoisegenerator1 = this.maxLimitPerlinNoise.getOctaveNoise(i);
			if (improvednoisegenerator1 != null) {
				d1 += improvednoisegenerator1.noise(d4, d5, d6, d7, (double) p_222552_2_ * d7) / d3;
			}

			if (i < 8) {
				ImprovedNoiseGenerator improvednoisegenerator2 = this.mainPerlinNoise.getOctaveNoise(i);
				if (improvednoisegenerator2 != null) {
					d2 += improvednoisegenerator2.noise(OctavesNoiseGenerator.wrap((double) p_222552_1_ * p_222552_8_ * d3), OctavesNoiseGenerator.wrap((double) p_222552_2_ * p_222552_10_ * d3), OctavesNoiseGenerator.wrap((double) p_222552_3_ * p_222552_8_ * d3), p_222552_10_ * d3, (double) p_222552_2_ * p_222552_10_ * d3) / d3;
				}
			}

			d3 /= 2.0D;
		}

		return MathHelper.clampedLerp(d0 / 512.0D, d1 / 512.0D, (d2 / 10.0D + 1.0D) / 2.0D);
	}

	private double[] makeAndFillNoiseColumn(int p_222547_1_, int p_222547_2_) {
		double[] adouble = new double[this.chunkCountY + 1];
		this.fillNoiseColumn(adouble, p_222547_1_, p_222547_2_);
		return adouble;
	}

	private void fillNoiseColumn(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
		NoiseSettings noisesettings = this.settings.get().noiseSettings();
		double d0;
		double d1;
		if (this.islandNoise != null) {
			d0 = EndBiomeProvider.getHeightValue(this.islandNoise, p_222548_2_, p_222548_3_) - 8.0F;
			if (d0 > 0.0D) {
				d1 = 0.25D;
			} else {
				d1 = 1.0D;
			}
		} else {
			float f = 0.0F;
			float f1 = 0.0F;
			float f2 = 0.0F;
			int i = 2;
			int j = this.getSeaLevel();
			float f3 = this.biomeSource.getNoiseBiome(p_222548_2_, j, p_222548_3_).getDepth();

			for (int k = -2; k <= 2; ++k) {
				for (int l = -2; l <= 2; ++l) {
					Biome biome = this.biomeSource.getNoiseBiome(p_222548_2_ + k, j, p_222548_3_ + l);
					float f4 = biome.getDepth();
					float f5 = biome.getScale();
					float f6;
					float f7;
					if (noisesettings.isAmplified() && f4 > 0.0F) {
						f6 = 1.0F + f4 * 2.0F;
						f7 = 1.0F + f5 * 4.0F;
					} else {
						f6 = f4;
						f7 = f5;
					}

					float f8 = f4 > f3 ? 0.5F : 1.0F;
					float f9 = f8 * BIOME_WEIGHTS[k + 2 + (l + 2) * 5] / (f6 + 2.0F);
					f += f7 * f9;
					f1 += f6 * f9;
					f2 += f9;
				}
			}

			float f10 = f1 / f2;
			float f11 = f / f2;
			double d16 = f10 * 0.5F - 0.125F;
			double d18 = f11 * 0.9F + 0.1F;
			d0 = d16 * 0.265625D;
			d1 = 96.0D / d18;
		}

		double d12 = 684.412D * noisesettings.noiseSamplingSettings().xzScale();
		double d13 = 684.412D * noisesettings.noiseSamplingSettings().yScale();
		double d14 = d12 / noisesettings.noiseSamplingSettings().xzFactor();
		double d15 = d13 / noisesettings.noiseSamplingSettings().yFactor();
		double d17 = noisesettings.topSlideSettings().target();
		double d19 = noisesettings.topSlideSettings().size();
		double d20 = noisesettings.topSlideSettings().offset();
		double d21 = noisesettings.bottomSlideSettings().target();
		double d2 = noisesettings.bottomSlideSettings().size();
		double d3 = noisesettings.bottomSlideSettings().offset();
		double d4 = noisesettings.randomDensityOffset() ? this.getRandomDensity(p_222548_2_, p_222548_3_) : 0.0D;
		double d5 = noisesettings.densityFactor();
		double d6 = noisesettings.densityOffset();

		for (int i1 = 0; i1 <= this.chunkCountY; ++i1) {
			double d7 = this.sampleAndClampNoise(p_222548_2_, i1, p_222548_3_, d12, d13, d14, d15);
			double d8 = 1.0D - (double) i1 * 2.0D / (double) this.chunkCountY + d4;
			double d9 = d8 * d5 + d6;
			double d10 = (d9 + d0) * d1;
			if (d10 > 0.0D) {
				d7 = d7 + d10 * 4.0D;
			} else {
				d7 = d7 + d10;
			}

			if (d19 > 0.0D) {
				double d11 = ((double) (this.chunkCountY - i1) - d20) / d19;
				d7 = MathHelper.clampedLerp(d17, d7, d11);
			}

			if (d2 > 0.0D) {
				double d22 = ((double) i1 - d3) / d2;
				d7 = MathHelper.clampedLerp(d21, d7, d22);
			}

			p_222548_1_[i1] = d7;
		}

	}

	private double getRandomDensity(int p_236095_1_, int p_236095_2_) {
		double d0 = this.depthNoise.getValue(p_236095_1_ * 200, 10.0D, p_236095_2_ * 200, 1.0D, 0.0D, true);
		double d1;
		if (d0 < 0.0D) {
			d1 = -d0 * 0.3D;
		} else {
			d1 = d0;
		}

		double d2 = d1 * 24.575625D - 2.0D;
		return d2 < 0.0D ? d2 * 0.009486607142857142D : Math.min(d2, 1.0D) * 0.006640625D;
	}

	public int getBaseHeight(int p_222529_1_, int p_222529_2_, Heightmap.Type p_222529_3_) {
		return this.iterateNoiseColumn(p_222529_1_, p_222529_2_, null, p_222529_3_.isOpaque());
	}

	public IBlockReader getBaseColumn(int p_230348_1_, int p_230348_2_) {
		BlockState[] ablockstate = new BlockState[this.chunkCountY * this.chunkHeight];
		this.iterateNoiseColumn(p_230348_1_, p_230348_2_, ablockstate, null);
		return new Blockreader(ablockstate);
	}

	private int iterateNoiseColumn(int p_236087_1_, int p_236087_2_, @Nullable BlockState[] p_236087_3_, @Nullable Predicate<BlockState> p_236087_4_) {
		int i = Math.floorDiv(p_236087_1_, this.chunkWidth);
		int j = Math.floorDiv(p_236087_2_, this.chunkWidth);
		int k = Math.floorMod(p_236087_1_, this.chunkWidth);
		int l = Math.floorMod(p_236087_2_, this.chunkWidth);
		double d0 = (double) k / (double) this.chunkWidth;
		double d1 = (double) l / (double) this.chunkWidth;
		double[][] adouble = new double[][]{this.makeAndFillNoiseColumn(i, j), this.makeAndFillNoiseColumn(i, j + 1), this.makeAndFillNoiseColumn(i + 1, j), this.makeAndFillNoiseColumn(i + 1, j + 1)};

		for (int i1 = this.chunkCountY - 1; i1 >= 0; --i1) {
			double d2 = adouble[0][i1];
			double d3 = adouble[1][i1];
			double d4 = adouble[2][i1];
			double d5 = adouble[3][i1];
			double d6 = adouble[0][i1 + 1];
			double d7 = adouble[1][i1 + 1];
			double d8 = adouble[2][i1 + 1];
			double d9 = adouble[3][i1 + 1];

			for (int j1 = this.chunkHeight - 1; j1 >= 0; --j1) {
				double d10 = (double) j1 / (double) this.chunkHeight;
				double d11 = MathHelper.lerp3(d10, d0, d1, d2, d6, d4, d8, d3, d7, d5, d9);
				int k1 = i1 * this.chunkHeight + j1;
				BlockState blockstate = this.generateBaseState(d11, k1);
				if (p_236087_3_ != null) {
					p_236087_3_[k1] = blockstate;
				}

				if (p_236087_4_ != null && p_236087_4_.test(blockstate)) {
					return k1 + 1;
				}
			}
		}

		return 0;
	}

	protected BlockState generateBaseState(double p_236086_1_, int p_236086_3_) {
		BlockState blockstate;
		if (p_236086_1_ > 0.0D) {
			blockstate = this.defaultBlock;
		} else if (p_236086_3_ < this.getSeaLevel()) {
			blockstate = this.defaultFluid;
		} else {
			blockstate = AIR;
		}

		return blockstate;
	}

	public void buildSurfaceAndBedrock(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
		ChunkPos chunkpos = p_225551_2_.getPos();
		int i = chunkpos.x;
		int j = chunkpos.z;
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setBaseChunkSeed(i, j);
		ChunkPos chunkpos1 = p_225551_2_.getPos();
		int k = chunkpos1.getMinBlockX();
		int l = chunkpos1.getMinBlockZ();
		double d0 = 0.0625D;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int i1 = 0; i1 < 16; ++i1) {
			for (int j1 = 0; j1 < 16; ++j1) {
				int k1 = k + i1;
				int l1 = l + j1;
				int i2 = p_225551_2_.getHeight(Heightmap.Type.WORLD_SURFACE_WG, i1, j1) + 1;
				double d1 = this.surfaceNoise.getSurfaceNoiseValue((double) k1 * 0.0625D, (double) l1 * 0.0625D, 0.0625D, (double) i1 * 0.0625D) * 15.0D;
				p_225551_1_.getBiome(blockpos$mutable.set(k + i1, i2, l + j1)).buildSurfaceAt(sharedseedrandom, p_225551_2_, k1, l1, i2, d1, this.defaultBlock, this.defaultFluid, this.getSeaLevel(), p_225551_1_.getSeed());
			}
		}

		this.setBedrock(p_225551_2_, sharedseedrandom);
	}

	private void setBedrock(IChunk p_222555_1_, Random p_222555_2_) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		int i = p_222555_1_.getPos().getMinBlockX();
		int j = p_222555_1_.getPos().getMinBlockZ();
		DimensionSettings dimensionsettings = this.settings.get();
		int k = dimensionsettings.getBedrockFloorPosition();
		int l = this.height - 1 - dimensionsettings.getBedrockRoofPosition();
		int i1 = 5;
		boolean flag = l + 4 >= 0 && l < this.height;
		boolean flag1 = k + 4 >= 0 && k < this.height;
		if (flag || flag1) {
			for (BlockPos blockpos : BlockPos.betweenClosed(i, 0, j, i + 15, 0, j + 15)) {
				if (flag) {
					for (int j1 = 0; j1 < 5; ++j1) {
						if (j1 <= p_222555_2_.nextInt(5)) {
							p_222555_1_.setBlockState(blockpos$mutable.set(blockpos.getX(), l - j1, blockpos.getZ()), TofuBlocks.TOFUBEDROCK.defaultBlockState(), false);
						}
					}
				}

				if (flag1) {
					for (int k1 = 4; k1 >= 0; --k1) {
						if (k1 <= p_222555_2_.nextInt(5)) {
							p_222555_1_.setBlockState(blockpos$mutable.set(blockpos.getX(), k + k1, blockpos.getZ()), TofuBlocks.TOFUBEDROCK.defaultBlockState(), false);
						}
					}
				}
			}

		}
	}

	public void fillFromNoise(IWorld p_230352_1_, StructureManager p_230352_2_, IChunk p_230352_3_) {
		ObjectList<StructurePiece> objectlist = new ObjectArrayList<>(10);
		ObjectList<JigsawJunction> objectlist1 = new ObjectArrayList<>(32);
		ChunkPos chunkpos = p_230352_3_.getPos();
		int i = chunkpos.x;
		int j = chunkpos.z;
		int k = i << 4;
		int l = j << 4;

		for (Structure<?> structure : Structure.NOISE_AFFECTING_FEATURES) {
			p_230352_2_.startsForFeature(SectionPos.of(chunkpos, 0), structure).forEach((p_236089_5_) -> {
				for (StructurePiece structurepiece1 : p_236089_5_.getPieces()) {
					if (structurepiece1.isCloseToChunk(chunkpos, 12)) {
						if (structurepiece1 instanceof AbstractVillagePiece) {
							AbstractVillagePiece abstractvillagepiece = (AbstractVillagePiece) structurepiece1;
							JigsawPattern.PlacementBehaviour jigsawpattern$placementbehaviour = abstractvillagepiece.getElement().getProjection();
							if (jigsawpattern$placementbehaviour == JigsawPattern.PlacementBehaviour.RIGID) {
								objectlist.add(abstractvillagepiece);
							}

							for (JigsawJunction jigsawjunction1 : abstractvillagepiece.getJunctions()) {
								int l5 = jigsawjunction1.getSourceX();
								int i6 = jigsawjunction1.getSourceZ();
								if (l5 > k - 12 && i6 > l - 12 && l5 < k + 15 + 12 && i6 < l + 15 + 12) {
									objectlist1.add(jigsawjunction1);
								}
							}
						} else {
							objectlist.add(structurepiece1);
						}
					}
				}

			});
		}

		double[][][] adouble = new double[2][this.chunkCountZ + 1][this.chunkCountY + 1];

		for (int i5 = 0; i5 < this.chunkCountZ + 1; ++i5) {
			adouble[0][i5] = new double[this.chunkCountY + 1];
			this.fillNoiseColumn(adouble[0][i5], i * this.chunkCountX, j * this.chunkCountZ + i5);
			adouble[1][i5] = new double[this.chunkCountY + 1];
		}

		ChunkPrimer chunkprimer = (ChunkPrimer) p_230352_3_;
		Heightmap heightmap = chunkprimer.getOrCreateHeightmapUnprimed(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap heightmap1 = chunkprimer.getOrCreateHeightmapUnprimed(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		ObjectListIterator<StructurePiece> objectlistiterator = objectlist.iterator();
		ObjectListIterator<JigsawJunction> objectlistiterator1 = objectlist1.iterator();

		for (int i1 = 0; i1 < this.chunkCountX; ++i1) {
			for (int j1 = 0; j1 < this.chunkCountZ + 1; ++j1) {
				this.fillNoiseColumn(adouble[1][j1], i * this.chunkCountX + i1 + 1, j * this.chunkCountZ + j1);
			}

			for (int j5 = 0; j5 < this.chunkCountZ; ++j5) {
				ChunkSection chunksection = chunkprimer.getOrCreateSection(15);
				chunksection.acquire();

				for (int k1 = this.chunkCountY - 1; k1 >= 0; --k1) {
					double d0 = adouble[0][j5][k1];
					double d1 = adouble[0][j5 + 1][k1];
					double d2 = adouble[1][j5][k1];
					double d3 = adouble[1][j5 + 1][k1];
					double d4 = adouble[0][j5][k1 + 1];
					double d5 = adouble[0][j5 + 1][k1 + 1];
					double d6 = adouble[1][j5][k1 + 1];
					double d7 = adouble[1][j5 + 1][k1 + 1];

					for (int l1 = this.chunkHeight - 1; l1 >= 0; --l1) {
						int i2 = k1 * this.chunkHeight + l1;
						int j2 = i2 & 15;
						int k2 = i2 >> 4;
						if (chunksection.bottomBlockY() >> 4 != k2) {
							chunksection.release();
							chunksection = chunkprimer.getOrCreateSection(k2);
							chunksection.acquire();
						}

						double d8 = (double) l1 / (double) this.chunkHeight;
						double d9 = MathHelper.lerp(d8, d0, d4);
						double d10 = MathHelper.lerp(d8, d2, d6);
						double d11 = MathHelper.lerp(d8, d1, d5);
						double d12 = MathHelper.lerp(d8, d3, d7);

						for (int l2 = 0; l2 < this.chunkWidth; ++l2) {
							int i3 = k + i1 * this.chunkWidth + l2;
							int j3 = i3 & 15;
							double d13 = (double) l2 / (double) this.chunkWidth;
							double d14 = MathHelper.lerp(d13, d9, d10);
							double d15 = MathHelper.lerp(d13, d11, d12);

							for (int k3 = 0; k3 < this.chunkWidth; ++k3) {
								int l3 = l + j5 * this.chunkWidth + k3;
								int i4 = l3 & 15;
								double d16 = (double) k3 / (double) this.chunkWidth;
								double d17 = MathHelper.lerp(d16, d14, d15);
								double d18 = MathHelper.clamp(d17 / 200.0D, -1.0D, 1.0D);

								int j4;
								int k4;
								int l4;
								for (d18 = d18 / 2.0D - d18 * d18 * d18 / 24.0D; objectlistiterator.hasNext(); d18 += getContribution(j4, k4, l4) * 0.8D) {
									StructurePiece structurepiece = objectlistiterator.next();
									MutableBoundingBox mutableboundingbox = structurepiece.getBoundingBox();
									j4 = Math.max(0, Math.max(mutableboundingbox.x0 - i3, i3 - mutableboundingbox.x1));
									k4 = i2 - (mutableboundingbox.y0 + (structurepiece instanceof AbstractVillagePiece ? ((AbstractVillagePiece) structurepiece).getGroundLevelDelta() : 0));
									l4 = Math.max(0, Math.max(mutableboundingbox.z0 - l3, l3 - mutableboundingbox.z1));
								}

								objectlistiterator.back(objectlist.size());

								while (objectlistiterator1.hasNext()) {
									JigsawJunction jigsawjunction = objectlistiterator1.next();
									int k5 = i3 - jigsawjunction.getSourceX();
									j4 = i2 - jigsawjunction.getSourceGroundY();
									k4 = l3 - jigsawjunction.getSourceZ();
									d18 += getContribution(k5, j4, k4) * 0.4D;
								}

								objectlistiterator1.back(objectlist1.size());
								BlockState blockstate = this.generateBaseState(d18, i2);
								if (blockstate != AIR) {
									blockpos$mutable.set(i3, i2, l3);
									if (blockstate.getLightValue(chunkprimer, blockpos$mutable) != 0) {
										chunkprimer.addLight(blockpos$mutable);
									}

									chunksection.setBlockState(j3, j2, i4, blockstate, false);
									heightmap.update(j3, i2, i4, blockstate);
									heightmap1.update(j3, i2, i4, blockstate);
								}
							}
						}
					}
				}

				chunksection.release();
			}

			double[][] adouble1 = adouble[0];
			adouble[0] = adouble[1];
			adouble[1] = adouble1;
		}

	}

	private static double getContribution(int p_222556_0_, int p_222556_1_, int p_222556_2_) {
		int i = p_222556_0_ + 12;
		int j = p_222556_1_ + 12;
		int k = p_222556_2_ + 12;
		if (i >= 0 && i < 24) {
			if (j >= 0 && j < 24) {
				return k >= 0 && k < 24 ? (double) BEARD_KERNEL[k * 24 * 24 + i * 24 + j] : 0.0D;
			} else {
				return 0.0D;
			}
		} else {
			return 0.0D;
		}
	}

	private static double computeContribution(int p_222554_0_, int p_222554_1_, int p_222554_2_) {
		double d0 = p_222554_0_ * p_222554_0_ + p_222554_2_ * p_222554_2_;
		double d1 = (double) p_222554_1_ + 0.5D;
		double d2 = d1 * d1;
		double d3 = Math.pow(Math.E, -(d2 / 16.0D + d0 / 16.0D));
		double d4 = -d1 * MathHelper.fastInvSqrt(d2 / 2.0D + d0 / 2.0D) / 2.0D;
		return d4 * d3;
	}

	public int getGenDepth() {
		return this.height;
	}

	public int getSeaLevel() {
		return this.settings.get().seaLevel();
	}

	public List<MobSpawnInfo.Spawners> getMobsAt(Biome p_230353_1_, StructureManager p_230353_2_, EntityClassification p_230353_3_, BlockPos p_230353_4_) {
		List<MobSpawnInfo.Spawners> spawns = net.minecraftforge.common.world.StructureSpawnManager.getStructureSpawns(p_230353_2_, p_230353_3_, p_230353_4_);
		if (spawns != null) return spawns;
		if (false) {//Forge: We handle these hardcoded cases above in StructureSpawnManager#getStructureSpawns, but allow for insideOnly to be changed and allow for creatures to be spawned in ones other than just the witch hut
			if (p_230353_2_.getStructureAt(p_230353_4_, true, Structure.SWAMP_HUT).isValid()) {
				if (p_230353_3_ == EntityClassification.MONSTER) {
					return Structure.SWAMP_HUT.getSpecialEnemies();
				}

				if (p_230353_3_ == EntityClassification.CREATURE) {
					return Structure.SWAMP_HUT.getSpecialAnimals();
				}
			}

			if (p_230353_3_ == EntityClassification.MONSTER) {
				if (p_230353_2_.getStructureAt(p_230353_4_, false, Structure.PILLAGER_OUTPOST).isValid()) {
					return Structure.PILLAGER_OUTPOST.getSpecialEnemies();
				}

				if (p_230353_2_.getStructureAt(p_230353_4_, false, Structure.OCEAN_MONUMENT).isValid()) {
					return Structure.OCEAN_MONUMENT.getSpecialEnemies();
				}

				if (p_230353_2_.getStructureAt(p_230353_4_, true, Structure.NETHER_BRIDGE).isValid()) {
					return Structure.NETHER_BRIDGE.getSpecialEnemies();
				}
			}
		}

		return super.getMobsAt(p_230353_1_, p_230353_2_, p_230353_3_, p_230353_4_);
	}

	public void spawnOriginalMobs(WorldGenRegion p_230354_1_) {
		int i = p_230354_1_.getCenterX();
		int j = p_230354_1_.getCenterZ();
		Biome biome = p_230354_1_.getBiome((new ChunkPos(i, j)).getWorldPosition());
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setDecorationSeed(p_230354_1_.getSeed(), i << 4, j << 4);
		WorldEntitySpawner.spawnMobsForChunkGeneration(p_230354_1_, biome, i, j, sharedseedrandom);

	}
}
