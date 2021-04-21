package baguchan.tofucraft.world.dimension;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.world.SeedHolder;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.util.Function3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.INoiseGenerator;
import net.minecraft.world.gen.ImprovedNoiseGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.NoiseSettings;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.world.StructureSpawnManager;

public class TofuChunkGenerator extends ChunkGenerator {
	public static final Codec<TofuChunkGenerator> CODEC;

	private static final float[] BEARD_KERNEL;

	private static final float[] BIOME_WEIGHTS;

	static {
		CODEC = RecordCodecBuilder.create(p_236091_0_ -> p_236091_0_.group((App) BiomeProvider.field_235202_a_.fieldOf("biome_source").forGetter(()), (App) Codec.LONG.fieldOf("seed").orElseGet(SeedHolder::getSeed).forGetter(()), (App) DimensionSettings.field_236098_b_.fieldOf("settings").forGetter(())).apply((Applicative) p_236091_0_, p_236091_0_.stable(TofuChunkGenerator::new)));
		BEARD_KERNEL = (float[]) Util.func_200696_a(new float[13824], p_236094_0_ -> {
			for (int i = 0; i < 24; i++) {
				for (int j = 0; j < 24; j++) {
					for (int k = 0; k < 24; k++)
						p_236094_0_[i * 24 * 24 + j * 24 + k] = (float) computeContribution(j - 12, k - 12, i - 12);
				}
			}
		});
		BIOME_WEIGHTS = (float[]) Util.func_200696_a(new float[25], p_236092_0_ -> {
			for (int i = -2; i <= 2; i++) {
				for (int j = -2; j <= 2; j++) {
					float f = 10.0F / MathHelper.func_76129_c((i * i + j * j) + 0.2F);
					p_236092_0_[i + 2 + (j + 2) * 5] = f;
				}
			}
		});
	}

	private static final BlockState AIR = Blocks.field_150350_a.func_176223_P();

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

	protected final Supplier<DimensionSettings> field_222543_d;

	private final int height;

	public TofuChunkGenerator(BiomeProvider p_i241975_1_, long p_i241975_2_, Supplier<DimensionSettings> p_i241975_4_) {
		this(p_i241975_1_, p_i241975_1_, p_i241975_2_, p_i241975_4_);
	}

	private TofuChunkGenerator(BiomeProvider p_i241976_1_, BiomeProvider p_i241976_2_, long p_i241976_3_, Supplier<DimensionSettings> p_i241976_5_) {
		super(p_i241976_1_, p_i241976_2_, ((DimensionSettings) p_i241976_5_.get()).func_236108_a_(), p_i241976_3_);
		this.seed = p_i241976_3_;
		DimensionSettings dimensionsettings = p_i241976_5_.get();
		this.field_222543_d = p_i241976_5_;
		NoiseSettings noisesettings = dimensionsettings.func_236113_b_();
		this.height = noisesettings.func_236169_a_();
		this.chunkHeight = noisesettings.func_236175_f_() * 4;
		this.chunkWidth = noisesettings.func_236174_e_() * 4;
		this.defaultBlock = dimensionsettings.func_236115_c_();
		this.defaultFluid = dimensionsettings.func_236116_d_();
		this.chunkCountX = 16 / this.chunkWidth;
		this.chunkCountY = noisesettings.func_236169_a_() / this.chunkHeight;
		this.chunkCountZ = 16 / this.chunkWidth;
		this.random = new SharedSeedRandom(p_i241976_3_);
		this.minLimitPerlinNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-15, 0));
		this.maxLimitPerlinNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-15, 0));
		this.mainPerlinNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-7, 0));
		this.surfaceNoise = noisesettings.func_236178_i_() ? (INoiseGenerator) new PerlinNoiseGenerator(this.random, IntStream.rangeClosed(-3, 0)) : (INoiseGenerator) new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-3, 0));
		this.random.func_202423_a(2620);
		this.depthNoise = new OctavesNoiseGenerator(this.random, IntStream.rangeClosed(-15, 0));
		if (noisesettings.func_236180_k_()) {
			SharedSeedRandom sharedseedrandom = new SharedSeedRandom(p_i241976_3_);
			sharedseedrandom.func_202423_a(17292);
			this.islandNoise = new SimplexNoiseGenerator((Random) sharedseedrandom);
		} else {
			this.islandNoise = null;
		}
	}

	protected Codec<? extends ChunkGenerator> func_230347_a_() {
		return (Codec) CODEC;
	}

	@OnlyIn(Dist.CLIENT)
	public ChunkGenerator func_230349_a_(long p_230349_1_) {
		return (ChunkGenerator) new NoiseChunkGenerator(this.field_222542_c.func_230320_a_(p_230349_1_), p_230349_1_, this.field_222543_d);
	}

	public boolean stable(long p_236088_1_, RegistryKey<DimensionSettings> p_236088_3_) {
		return (this.seed == p_236088_1_ && ((DimensionSettings) this.field_222543_d.get()).func_242744_a(p_236088_3_));
	}

	private double sampleAndClampNoise(int p_222552_1_, int p_222552_2_, int p_222552_3_, double p_222552_4_, double p_222552_6_, double p_222552_8_, double p_222552_10_) {
		double d0 = 0.0D;
		double d1 = 0.0D;
		double d2 = 0.0D;
		boolean flag = true;
		double d3 = 1.0D;
		for (int i = 0; i < 16; i++) {
			double d4 = OctavesNoiseGenerator.func_215461_a(p_222552_1_ * p_222552_4_ * d3);
			double d5 = OctavesNoiseGenerator.func_215461_a(p_222552_2_ * p_222552_6_ * d3);
			double d6 = OctavesNoiseGenerator.func_215461_a(p_222552_3_ * p_222552_4_ * d3);
			double d7 = p_222552_6_ * d3;
			ImprovedNoiseGenerator improvednoisegenerator = this.minLimitPerlinNoise.func_215463_a(i);
			if (improvednoisegenerator != null)
				d0 += improvednoisegenerator.func_215456_a(d4, d5, d6, d7, p_222552_2_ * d7) / d3;
			ImprovedNoiseGenerator improvednoisegenerator1 = this.maxLimitPerlinNoise.func_215463_a(i);
			if (improvednoisegenerator1 != null)
				d1 += improvednoisegenerator1.func_215456_a(d4, d5, d6, d7, p_222552_2_ * d7) / d3;
			if (i < 8) {
				ImprovedNoiseGenerator improvednoisegenerator2 = this.mainPerlinNoise.func_215463_a(i);
				if (improvednoisegenerator2 != null)
					d2 += improvednoisegenerator2.func_215456_a(OctavesNoiseGenerator.func_215461_a(p_222552_1_ * p_222552_8_ * d3), OctavesNoiseGenerator.func_215461_a(p_222552_2_ * p_222552_10_ * d3), OctavesNoiseGenerator.func_215461_a(p_222552_3_ * p_222552_8_ * d3), p_222552_10_ * d3, p_222552_2_ * p_222552_10_ * d3) / d3;
			}
			d3 /= 2.0D;
		}
		return MathHelper.func_151238_b(d0 / 512.0D, d1 / 512.0D, (d2 / 10.0D + 1.0D) / 2.0D);
	}

	private double[] makeAndFillNoiseColumn(int p_222547_1_, int p_222547_2_) {
		double[] adouble = new double[this.chunkCountY + 1];
		fillNoiseColumn(adouble, p_222547_1_, p_222547_2_);
		return adouble;
	}

	private void fillNoiseColumn(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
		double d0, d1;
		NoiseSettings noisesettings = ((DimensionSettings) this.field_222543_d.get()).func_236113_b_();
		if (this.islandNoise != null) {
			d0 = (EndBiomeProvider.func_235317_a_(this.islandNoise, p_222548_2_, p_222548_3_) - 8.0F);
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
			int j = func_230356_f_();
			float f3 = this.field_222542_c.func_225526_b_(p_222548_2_, j, p_222548_3_).func_185355_j();
			for (int k = -2; k <= 2; k++) {
				for (int l = -2; l <= 2; l++) {
					float f6, f7;
					Biome biome = this.field_222542_c.func_225526_b_(p_222548_2_ + k, j, p_222548_3_ + l);
					float f4 = biome.func_185355_j();
					float f5 = biome.func_185360_m();
					if (noisesettings.func_236181_l_() && f4 > 0.0F) {
						f6 = 1.0F + f4 * 2.0F;
						f7 = 1.0F + f5 * 4.0F;
					} else {
						f6 = f4;
						f7 = f5;
					}
					float f8 = (f4 > f3) ? 0.5F : 1.0F;
					float f9 = f8 * BIOME_WEIGHTS[k + 2 + (l + 2) * 5] / (f6 + 2.0F);
					f += f7 * f9;
					f1 += f6 * f9;
					f2 += f9;
				}
			}
			float f10 = f1 / f2;
			float f11 = f / f2;
			double d16 = (f10 * 0.5F - 0.125F);
			double d18 = (f11 * 0.9F + 0.1F);
			d0 = d16 * 0.265625D;
			d1 = 96.0D / d18;
		}
		double d12 = 684.412D * noisesettings.func_236171_b_().func_236151_a_();
		double d13 = 684.412D * noisesettings.func_236171_b_().func_236153_b_();
		double d14 = d12 / noisesettings.func_236171_b_().func_236154_c_();
		double d15 = d13 / noisesettings.func_236171_b_().func_236155_d_();
		double d17 = noisesettings.func_236172_c_().func_236186_a_();
		double d19 = noisesettings.func_236172_c_().func_236188_b_();
		double d20 = noisesettings.func_236172_c_().func_236189_c_();
		double d21 = noisesettings.func_236173_d_().func_236186_a_();
		double d2 = noisesettings.func_236173_d_().func_236188_b_();
		double d3 = noisesettings.func_236173_d_().func_236189_c_();
		double d4 = noisesettings.func_236179_j_() ? getRandomDensity(p_222548_2_, p_222548_3_) : 0.0D;
		double d5 = noisesettings.func_236176_g_();
		double d6 = noisesettings.func_236177_h_();
		for (int i1 = 0; i1 <= this.chunkCountY; i1++) {
			double d7 = sampleAndClampNoise(p_222548_2_, i1, p_222548_3_, d12, d13, d14, d15);
			double d8 = 1.0D - i1 * 2.0D / this.chunkCountY + d4;
			double d9 = d8 * d5 + d6;
			double d10 = (d9 + d0) * d1;
			if (d10 > 0.0D) {
				d7 += d10 * 4.0D;
			} else {
				d7 += d10;
			}
			if (d19 > 0.0D) {
				double d11 = ((this.chunkCountY - i1) - d20) / d19;
				d7 = MathHelper.func_151238_b(d17, d7, d11);
			}
			if (d2 > 0.0D) {
				double d22 = (i1 - d3) / d2;
				d7 = MathHelper.func_151238_b(d21, d7, d22);
			}
			p_222548_1_[i1] = d7;
		}
	}

	private double getRandomDensity(int p_236095_1_, int p_236095_2_) {
		double d1, d0 = this.depthNoise.func_215462_a((p_236095_1_ * 200), 10.0D, (p_236095_2_ * 200), 1.0D, 0.0D, true);
		if (d0 < 0.0D) {
			d1 = -d0 * 0.3D;
		} else {
			d1 = d0;
		}
		double d2 = d1 * 24.575625D - 2.0D;
		return (d2 < 0.0D) ? (d2 * 0.009486607142857142D) : (Math.min(d2, 1.0D) * 0.006640625D);
	}

	public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type p_222529_3_) {
		return iterateNoiseColumn(p_222529_1_, p_222529_2_, (BlockState[]) null, p_222529_3_.func_222684_d());
	}

	public IBlockReader func_230348_a_(int p_230348_1_, int p_230348_2_) {
		BlockState[] ablockstate = new BlockState[this.chunkCountY * this.chunkHeight];
		iterateNoiseColumn(p_230348_1_, p_230348_2_, ablockstate, (Predicate<BlockState>) null);
		return (IBlockReader) new Blockreader(ablockstate);
	}

	private int iterateNoiseColumn(int p_236087_1_, int p_236087_2_, @Nullable BlockState[] p_236087_3_, @Nullable Predicate<BlockState> p_236087_4_) {
		int i = Math.floorDiv(p_236087_1_, this.chunkWidth);
		int j = Math.floorDiv(p_236087_2_, this.chunkWidth);
		int k = Math.floorMod(p_236087_1_, this.chunkWidth);
		int l = Math.floorMod(p_236087_2_, this.chunkWidth);
		double d0 = k / this.chunkWidth;
		double d1 = l / this.chunkWidth;
		double[][] adouble = {makeAndFillNoiseColumn(i, j), makeAndFillNoiseColumn(i, j + 1), makeAndFillNoiseColumn(i + 1, j), makeAndFillNoiseColumn(i + 1, j + 1)};
		for (int i1 = this.chunkCountY - 1; i1 >= 0; i1--) {
			double d2 = adouble[0][i1];
			double d3 = adouble[1][i1];
			double d4 = adouble[2][i1];
			double d5 = adouble[3][i1];
			double d6 = adouble[0][i1 + 1];
			double d7 = adouble[1][i1 + 1];
			double d8 = adouble[2][i1 + 1];
			double d9 = adouble[3][i1 + 1];
			for (int j1 = this.chunkHeight - 1; j1 >= 0; j1--) {
				double d10 = j1 / this.chunkHeight;
				double d11 = MathHelper.func_219807_a(d10, d0, d1, d2, d6, d4, d8, d3, d7, d5, d9);
				int k1 = i1 * this.chunkHeight + j1;
				BlockState blockstate = generateBaseState(d11, k1);
				if (p_236087_3_ != null)
					p_236087_3_[k1] = blockstate;
				if (p_236087_4_ != null && p_236087_4_.test(blockstate))
					return k1 + 1;
			}
		}
		return 0;
	}

	protected BlockState generateBaseState(double p_236086_1_, int p_236086_3_) {
		BlockState blockstate;
		if (p_236086_1_ > 0.0D) {
			blockstate = this.defaultBlock;
		} else if (p_236086_3_ < func_230356_f_()) {
			blockstate = this.defaultFluid;
		} else {
			blockstate = AIR;
		}
		return blockstate;
	}

	public void func_225551_a_(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
		ChunkPos chunkpos = p_225551_2_.func_76632_l();
		int i = chunkpos.field_77276_a;
		int j = chunkpos.field_77275_b;
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.func_202422_a(i, j);
		ChunkPos chunkpos1 = p_225551_2_.func_76632_l();
		int k = chunkpos1.func_180334_c();
		int l = chunkpos1.func_180333_d();
		double d0 = 0.0625D;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		for (int i1 = 0; i1 < 16; i1++) {
			for (int j1 = 0; j1 < 16; j1++) {
				int k1 = k + i1;
				int l1 = l + j1;
				int i2 = p_225551_2_.func_201576_a(Heightmap.Type.WORLD_SURFACE_WG, i1, j1) + 1;
				double d1 = this.surfaceNoise.func_215460_a(k1 * 0.0625D, l1 * 0.0625D, 0.0625D, i1 * 0.0625D) * 15.0D;
				p_225551_1_.func_226691_t_((BlockPos) blockpos$mutable.func_181079_c(k + i1, i2, l + j1)).func_206854_a((Random) sharedseedrandom, p_225551_2_, k1, l1, i2, d1, this.defaultBlock, this.defaultFluid, func_230356_f_(), p_225551_1_.func_72905_C());
			}
		}
		setBedrock(p_225551_2_, (Random) sharedseedrandom);
	}

	private void setBedrock(IChunk p_222555_1_, Random p_222555_2_) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		int i = p_222555_1_.func_76632_l().func_180334_c();
		int j = p_222555_1_.func_76632_l().func_180333_d();
		DimensionSettings dimensionsettings = this.field_222543_d.get();
		int k = dimensionsettings.func_236118_f_();
		int l = this.height - 1 - dimensionsettings.func_236117_e_();
		int i1 = 5;
		boolean flag = (l + 4 >= 0 && l < this.height);
		boolean flag1 = (k + 4 >= 0 && k < this.height);
		if (flag || flag1)
			for (BlockPos blockpos : BlockPos.func_191531_b(i, 0, j, i + 15, 0, j + 15)) {
				if (flag)
					for (int j1 = 0; j1 < 5; j1++) {
						if (j1 <= p_222555_2_.nextInt(5))
							p_222555_1_.func_177436_a((BlockPos) blockpos$mutable.func_181079_c(blockpos.getX(), l - j1, blockpos.getZ()), TofuBlocks.TOFUBEDROCK.func_176223_P(), false);
					}
				if (flag1)
					for (int k1 = 4; k1 >= 0; k1--) {
						if (k1 <= p_222555_2_.nextInt(5))
							p_222555_1_.func_177436_a((BlockPos) blockpos$mutable.func_181079_c(blockpos.getX(), k + k1, blockpos.getZ()), TofuBlocks.TOFUBEDROCK.func_176223_P(), false);
					}
			}
	}

	public void func_230352_b_(IWorld p_230352_1_, StructureManager p_230352_2_, IChunk p_230352_3_) {
		ObjectArrayList objectArrayList1 = new ObjectArrayList(10);
		ObjectArrayList objectArrayList2 = new ObjectArrayList(32);
		ChunkPos chunkpos = p_230352_3_.func_76632_l();
		int i = chunkpos.field_77276_a;
		int j = chunkpos.field_77275_b;
		int k = i << 4;
		int l = j << 4;
		for (Iterator<Structure> iterator = Structure.field_236384_t_.iterator(); iterator.hasNext(); ) {
			Structure<?> structure = iterator.next();
			p_230352_2_.func_235011_a_(SectionPos.func_218156_a(chunkpos, 0), structure).forEach(p_236089_5_ -> {
				for (StructurePiece structurepiece1 : p_236089_5_.func_186161_c()) {
					if (structurepiece1.func_214810_a(chunkpos, 12)) {
						if (structurepiece1 instanceof AbstractVillagePiece) {
							AbstractVillagePiece abstractvillagepiece = (AbstractVillagePiece) structurepiece1;
							JigsawPattern.PlacementBehaviour jigsawpattern$placementbehaviour = abstractvillagepiece.func_214826_b().func_214854_c();
							if (jigsawpattern$placementbehaviour == JigsawPattern.PlacementBehaviour.RIGID)
								objectlist.add(abstractvillagepiece);
							for (JigsawJunction jigsawjunction1 : abstractvillagepiece.func_214829_e()) {
								int l5 = jigsawjunction1.func_214895_a();
								int i6 = jigsawjunction1.func_214893_c();
								if (l5 > k - 12 && i6 > l - 12 && l5 < k + 15 + 12 && i6 < l + 15 + 12)
									objectlist1.add(jigsawjunction1);
							}
							continue;
						}
						objectlist.add(structurepiece1);
					}
				}
			});
		}
		double[][][] adouble = new double[2][this.chunkCountZ + 1][this.chunkCountY + 1];
		for (int i5 = 0; i5 < this.chunkCountZ + 1; i5++) {
			adouble[0][i5] = new double[this.chunkCountY + 1];
			fillNoiseColumn(adouble[0][i5], i * this.chunkCountX, j * this.chunkCountZ + i5);
			adouble[1][i5] = new double[this.chunkCountY + 1];
		}
		ChunkPrimer chunkprimer = (ChunkPrimer) p_230352_3_;
		Heightmap heightmap = chunkprimer.func_217303_b(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap heightmap1 = chunkprimer.func_217303_b(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		ObjectListIterator<StructurePiece> objectlistiterator = objectArrayList1.iterator();
		ObjectListIterator<JigsawJunction> objectlistiterator1 = objectArrayList2.iterator();
		for (int i1 = 0; i1 < this.chunkCountX; i1++) {
			for (int j1 = 0; j1 < this.chunkCountZ + 1; j1++)
				fillNoiseColumn(adouble[1][j1], i * this.chunkCountX + i1 + 1, j * this.chunkCountZ + j1);
			for (int j5 = 0; j5 < this.chunkCountZ; j5++) {
				ChunkSection chunksection = chunkprimer.func_217332_a(15);
				chunksection.func_222635_a();
				for (int k1 = this.chunkCountY - 1; k1 >= 0; k1--) {
					double d0 = adouble[0][j5][k1];
					double d1 = adouble[0][j5 + 1][k1];
					double d2 = adouble[1][j5][k1];
					double d3 = adouble[1][j5 + 1][k1];
					double d4 = adouble[0][j5][k1 + 1];
					double d5 = adouble[0][j5 + 1][k1 + 1];
					double d6 = adouble[1][j5][k1 + 1];
					double d7 = adouble[1][j5 + 1][k1 + 1];
					for (int l1 = this.chunkHeight - 1; l1 >= 0; l1--) {
						int i2 = k1 * this.chunkHeight + l1;
						int j2 = i2 & 0xF;
						int k2 = i2 >> 4;
						if (chunksection.func_222632_g() >> 4 != k2) {
							chunksection.func_222637_b();
							chunksection = chunkprimer.func_217332_a(k2);
							chunksection.func_222635_a();
						}
						double d8 = l1 / this.chunkHeight;
						double d9 = MathHelper.func_219803_d(d8, d0, d4);
						double d10 = MathHelper.func_219803_d(d8, d2, d6);
						double d11 = MathHelper.func_219803_d(d8, d1, d5);
						double d12 = MathHelper.func_219803_d(d8, d3, d7);
						for (int l2 = 0; l2 < this.chunkWidth; l2++) {
							int i3 = k + i1 * this.chunkWidth + l2;
							int j3 = i3 & 0xF;
							double d13 = l2 / this.chunkWidth;
							double d14 = MathHelper.func_219803_d(d13, d9, d10);
							double d15 = MathHelper.func_219803_d(d13, d11, d12);
							for (int k3 = 0; k3 < this.chunkWidth; k3++) {
								int l3 = l + j5 * this.chunkWidth + k3;
								int i4 = l3 & 0xF;
								double d16 = k3 / this.chunkWidth;
								double d17 = MathHelper.func_219803_d(d16, d14, d15);
								double d18 = MathHelper.func_151237_a(d17 / 200.0D, -1.0D, 1.0D);
								for (d18 = d18 / 2.0D - d18 * d18 * d18 / 24.0D; objectlistiterator.hasNext(); d18 += getContribution(j4, k4, l4) * 0.8D) {
									StructurePiece structurepiece = (StructurePiece) objectlistiterator.next();
									MutableBoundingBox mutableboundingbox = structurepiece.func_74874_b();
									int j4 = Math.max(0, Math.max(mutableboundingbox.field_78897_a - i3, i3 - mutableboundingbox.field_78893_d));
									int k4 = i2 - mutableboundingbox.field_78895_b + ((structurepiece instanceof AbstractVillagePiece) ? ((AbstractVillagePiece) structurepiece).func_214830_d() : 0);
									int l4 = Math.max(0, Math.max(mutableboundingbox.field_78896_c - l3, l3 - mutableboundingbox.field_78892_f));
								}
								objectlistiterator.back(objectArrayList1.size());
								while (objectlistiterator1.hasNext()) {
									JigsawJunction jigsawjunction = (JigsawJunction) objectlistiterator1.next();
									int k5 = i3 - jigsawjunction.func_214895_a();
									int j4 = i2 - jigsawjunction.func_214896_b();
									int k4 = l3 - jigsawjunction.func_214893_c();
									d18 += getContribution(k5, j4, k4) * 0.4D;
								}
								objectlistiterator1.back(objectArrayList2.size());
								BlockState blockstate = generateBaseState(d18, i2);
								if (blockstate != AIR) {
									blockpos$mutable.func_181079_c(i3, i2, l3);
									if (blockstate.getLightValue((IBlockReader) chunkprimer, (BlockPos) blockpos$mutable) != 0)
										chunkprimer.func_201637_h((BlockPos) blockpos$mutable);
									chunksection.func_177484_a(j3, j2, i4, blockstate, false);
									heightmap.func_202270_a(j3, i2, i4, blockstate);
									heightmap1.func_202270_a(j3, i2, i4, blockstate);
								}
							}
						}
					}
				}
				chunksection.func_222637_b();
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
			if (j >= 0 && j < 24)
				return (k >= 0 && k < 24) ? BEARD_KERNEL[k * 24 * 24 + i * 24 + j] : 0.0D;
			return 0.0D;
		}
		return 0.0D;
	}

	private static double computeContribution(int p_222554_0_, int p_222554_1_, int p_222554_2_) {
		double d0 = (p_222554_0_ * p_222554_0_ + p_222554_2_ * p_222554_2_);
		double d1 = p_222554_1_ + 0.5D;
		double d2 = d1 * d1;
		double d3 = Math.pow(Math.E, -(d2 / 16.0D + d0 / 16.0D));
		double d4 = -d1 * MathHelper.func_181161_i(d2 / 2.0D + d0 / 2.0D) / 2.0D;
		return d4 * d3;
	}

	public int func_230355_e_() {
		return this.height;
	}

	public int func_230356_f_() {
		return ((DimensionSettings) this.field_222543_d.get()).func_236119_g_();
	}

	public List<MobSpawnInfo.Spawners> func_230353_a_(Biome p_230353_1_, StructureManager p_230353_2_, EntityClassification p_230353_3_, BlockPos p_230353_4_) {
		List<MobSpawnInfo.Spawners> spawns = StructureSpawnManager.getStructureSpawns(p_230353_2_, p_230353_3_, p_230353_4_);
		if (spawns != null)
			return spawns;
		return super.func_230353_a_(p_230353_1_, p_230353_2_, p_230353_3_, p_230353_4_);
	}

	public void func_230354_a_(WorldGenRegion p_230354_1_) {
		int i = p_230354_1_.func_201679_a();
		int j = p_230354_1_.func_201680_b();
		Biome biome = p_230354_1_.func_226691_t_((new ChunkPos(i, j)).func_206849_h());
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.func_202424_a(p_230354_1_.func_72905_C(), i << 4, j << 4);
		WorldEntitySpawner.func_77191_a((IServerWorld) p_230354_1_, biome, i, j, (Random) sharedseedrandom);
	}
}
