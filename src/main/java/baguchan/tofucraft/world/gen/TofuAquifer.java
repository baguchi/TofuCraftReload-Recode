package baguchan.tofucraft.world.gen;

import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.Arrays;

public class TofuAquifer implements Aquifer {
	private static final int X_RANGE = 10;
	private static final int Y_RANGE = 9;
	private static final int Z_RANGE = 10;
	private static final int X_SEPARATION = 6;
	private static final int Y_SEPARATION = 3;
	private static final int Z_SEPARATION = 6;
	private static final int X_SPACING = 16;
	private static final int Y_SPACING = 12;
	private static final int Z_SPACING = 16;
	private final NormalNoise barrierNoise;
	private final NormalNoise waterLevelNoise;
	private final NormalNoise lavaNoise;
	private final NoiseGeneratorSettings noiseGeneratorSettings;
	private final TofuAquifer.AquiferStatus[] aquiferCache;
	private final long[] aquiferLocationCache;
	private boolean shouldScheduleFluidUpdate;
	private final NoiseSampler sampler;
	private final int minGridX;
	private final int minGridY;
	private final int minGridZ;
	private final int gridSizeX;
	private final int gridSizeZ;

	public TofuAquifer(ChunkPos p_158008_, NormalNoise p_158009_, NormalNoise p_158010_, NormalNoise p_158011_, NoiseGeneratorSettings p_158012_, NoiseSampler p_158013_, int p_158014_, int p_158015_) {
		this.barrierNoise = p_158009_;
		this.waterLevelNoise = p_158010_;
		this.lavaNoise = p_158011_;
		this.noiseGeneratorSettings = p_158012_;
		this.sampler = p_158013_;
		this.minGridX = this.gridX(p_158008_.getMinBlockX()) - 1;
		int var9 = this.gridX(p_158008_.getMaxBlockX()) + 1;
		this.gridSizeX = var9 - this.minGridX + 1;
		this.minGridY = this.gridY(p_158014_) - 1;
		int var10 = this.gridY(p_158014_ + p_158015_) + 1;
		int var11 = var10 - this.minGridY + 1;
		this.minGridZ = this.gridZ(p_158008_.getMinBlockZ()) - 1;
		int var12 = this.gridZ(p_158008_.getMaxBlockZ()) + 1;
		this.gridSizeZ = var12 - this.minGridZ + 1;
		int var13 = this.gridSizeX * var11 * this.gridSizeZ;
		this.aquiferCache = new TofuAquifer.AquiferStatus[var13];
		this.aquiferLocationCache = new long[var13];
		Arrays.fill(this.aquiferLocationCache, 9223372036854775807L);
	}

	public static Aquifer create(ChunkPos p_157960_, NormalNoise p_157961_, NormalNoise p_157962_, NormalNoise p_157963_, NoiseGeneratorSettings p_157964_, NoiseSampler p_157965_, int p_157966_, int p_157967_) {
		return new TofuAquifer(p_157960_, p_157961_, p_157962_, p_157963_, p_157964_, p_157965_, p_157966_, p_157967_);
	}

	private int getIndex(int p_158028_, int p_158029_, int p_158030_) {
		int var4 = p_158028_ - this.minGridX;
		int var5 = p_158029_ - this.minGridY;
		int var6 = p_158030_ - this.minGridZ;
		return (var5 * this.gridSizeZ + var6) * this.gridSizeX + var4;
	}

	public BlockState computeState(BaseStoneSource p_158034_, int p_158035_, int p_158036_, int p_158037_, double p_158038_) {
		if (p_158038_ <= 0.0D) {
			double var7;
			BlockState var9;
			boolean var10;
			if (this.isLavaLevel(p_158036_)) {
				var9 = Blocks.LAVA.defaultBlockState();
				var7 = 0.0D;
				var10 = false;
			} else {
				int var11 = Math.floorDiv(p_158035_ - 5, 16);
				int var12 = Math.floorDiv(p_158036_ + 1, 12);
				int var13 = Math.floorDiv(p_158037_ - 5, 16);
				int var14 = 2147483647;
				int var15 = 2147483647;
				int var16 = 2147483647;
				long var17 = 0L;
				long var19 = 0L;
				long var21 = 0L;
				int var23 = 0;

				while (true) {
					if (var23 > 1) {
						TofuAquifer.AquiferStatus var48 = this.getAquiferStatus(var17);
						TofuAquifer.AquiferStatus var49 = this.getAquiferStatus(var19);
						TofuAquifer.AquiferStatus var50 = this.getAquiferStatus(var21);
						double var51 = this.similarity(var14, var15);
						double var52 = this.similarity(var14, var16);
						double var53 = this.similarity(var15, var16);
						var10 = var51 > 0.0D;
						if (var48.fluidLevel >= p_158036_ && var48.fluidType.is(Blocks.WATER) && this.isLavaLevel(p_158036_ - 1)) {
							var7 = 1.0D;
						} else if (var51 > -1.0D) {
							double var54 = 1.0D + (this.barrierNoise.getValue((double) p_158035_, (double) p_158036_, (double) p_158037_) + 0.05D) / 4.0D;
							double var56 = this.calculatePressure(p_158036_, var54, var48, var49);
							double var57 = this.calculatePressure(p_158036_, var54, var48, var50);
							double var38 = this.calculatePressure(p_158036_, var54, var49, var50);
							double var40 = Math.max(0.0D, var51);
							double var42 = Math.max(0.0D, var52);
							double var44 = Math.max(0.0D, var53);
							double var46 = 2.0D * var40 * Math.max(var56, Math.max(var57 * var42, var38 * var44));
							var7 = Math.max(0.0D, var46);
						} else {
							var7 = 0.0D;
						}

						var9 = p_158036_ >= var48.fluidLevel ? Blocks.AIR.defaultBlockState() : var48.fluidType;
						break;
					}

					for (int var24 = -1; var24 <= 1; ++var24) {
						for (int var25 = 0; var25 <= 1; ++var25) {
							int var26 = var11 + var23;
							int var27 = var12 + var24;
							int var28 = var13 + var25;
							int var29 = this.getIndex(var26, var27, var28);
							long var32 = this.aquiferLocationCache[var29];
							long var30;
							if (var32 != 9223372036854775807L) {
								var30 = var32;
							} else {
								WorldgenRandom var34 = new WorldgenRandom(Mth.getSeed(var26, var27 * 3, var28) + 1L);
								var30 = BlockPos.asLong(var26 * 16 + var34.nextInt(10), var27 * 12 + var34.nextInt(9), var28 * 16 + var34.nextInt(10));
								this.aquiferLocationCache[var29] = var30;
							}

							int var55 = BlockPos.getX(var30) - p_158035_;
							int var35 = BlockPos.getY(var30) - p_158036_;
							int var36 = BlockPos.getZ(var30) - p_158037_;
							int var37 = var55 * var55 + var35 * var35 + var36 * var36;
							if (var14 >= var37) {
								var21 = var19;
								var19 = var17;
								var17 = var30;
								var16 = var15;
								var15 = var14;
								var14 = var37;
							} else if (var15 >= var37) {
								var21 = var19;
								var19 = var30;
								var16 = var15;
								var15 = var37;
							} else if (var16 >= var37) {
								var21 = var30;
								var16 = var37;
							}
						}
					}

					++var23;
				}
			}

			if (p_158038_ + var7 <= 0.0D) {
				this.shouldScheduleFluidUpdate = var10;
				return var9;
			}
		}

		this.shouldScheduleFluidUpdate = false;
		return p_158034_.getBaseBlock(p_158035_, p_158036_, p_158037_);
	}

	public boolean shouldScheduleFluidUpdate() {
		return this.shouldScheduleFluidUpdate;
	}

	private boolean isLavaLevel(int p_158018_) {
		return p_158018_ - this.noiseGeneratorSettings.noiseSettings().minY() <= 9;
	}

	private double similarity(int p_158025_, int p_158026_) {
		double var3 = 25.0D;
		return 1.0D - (double) Math.abs(p_158026_ - p_158025_) / 25.0D;
	}

	private double calculatePressure(int p_158020_, double p_158021_, TofuAquifer.AquiferStatus p_158022_, TofuAquifer.AquiferStatus p_158023_) {
		if (p_158020_ <= p_158022_.fluidLevel && p_158020_ <= p_158023_.fluidLevel && p_158022_.fluidType != p_158023_.fluidType) {
			return 1.0D;
		} else {
			int var6 = Math.abs(p_158022_.fluidLevel - p_158023_.fluidLevel);
			double var7 = 0.5D * (double) (p_158022_.fluidLevel + p_158023_.fluidLevel);
			double var9 = Math.abs(var7 - (double) p_158020_ - 0.5D);
			return 0.5D * (double) var6 * p_158021_ - var9;
		}
	}

	private int gridX(int p_158040_) {
		return Math.floorDiv(p_158040_, 16);
	}

	private int gridY(int p_158046_) {
		return Math.floorDiv(p_158046_, 12);
	}

	private int gridZ(int p_158048_) {
		return Math.floorDiv(p_158048_, 16);
	}

	private TofuAquifer.AquiferStatus getAquiferStatus(long p_158032_) {
		int var3 = BlockPos.getX(p_158032_);
		int var4 = BlockPos.getY(p_158032_);
		int var5 = BlockPos.getZ(p_158032_);
		int var6 = this.gridX(var3);
		int var7 = this.gridY(var4);
		int var8 = this.gridZ(var5);
		int var9 = this.getIndex(var6, var7, var8);
		TofuAquifer.AquiferStatus var10 = this.aquiferCache[var9];
		if (var10 != null) {
			return var10;
		} else {
			TofuAquifer.AquiferStatus var11 = this.computeAquifer(var3, var4, var5);
			this.aquiferCache[var9] = var11;
			return var11;
		}
	}

	private TofuAquifer.AquiferStatus computeAquifer(int p_158042_, int p_158043_, int p_158044_) {
		int var4 = this.noiseGeneratorSettings.seaLevel();
		if (p_158043_ > 30) {
			return new TofuAquifer.AquiferStatus(var4, Blocks.WATER.defaultBlockState());
		} else {
			double var8 = this.waterLevelNoise.getValue((double) Math.floorDiv(p_158042_, 64), (double) Math.floorDiv(p_158043_, 40) / 1.4D, (double) Math.floorDiv(p_158044_, 64)) * 30.0D + -10.0D;
			boolean var10 = false;
			if (Math.abs(var8) > 8.0D) {
				var8 *= 4.0D;
			}

			int var11 = Math.floorDiv(p_158043_, 40) * 40 + 20;
			int var12 = var11 + Mth.floor(var8);
			if (var11 == -20) {
				double var13 = this.lavaNoise.getValue((double) Math.floorDiv(p_158042_, 64), (double) Math.floorDiv(p_158043_, 40) / 1.4D, (double) Math.floorDiv(p_158044_, 64));
				var10 = Math.abs(var13) > 0.2199999988079071D;
			}

			return new TofuAquifer.AquiferStatus(Math.min(56, var12), var10 ? Blocks.LAVA.defaultBlockState() : TofuBlocks.SOYMILK.defaultBlockState());
		}
	}

	static final class AquiferStatus {
		final int fluidLevel;
		final BlockState fluidType;

		public AquiferStatus(int p_158052_, BlockState p_158053_) {
			this.fluidLevel = p_158052_;
			this.fluidType = p_158053_;
		}
	}
}