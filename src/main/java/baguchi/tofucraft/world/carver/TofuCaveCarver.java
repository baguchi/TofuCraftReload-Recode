package baguchi.tofucraft.world.carver;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

public class TofuCaveCarver extends CaveWorldCarver {
	public TofuCaveCarver(Codec<CaveCarverConfiguration> p_159194_) {
		super(p_159194_);
		this.liquids = ImmutableSet.of(Fluids.WATER, TofuFluids.SOYMILK.get());
	}

	@Override
	protected float getThickness(RandomSource random) {
		return super.getThickness(random) * 1.5F;
	}

	@Override
	protected boolean carveEllipsoid(CarvingContext context, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, Aquifer aquifer, double x, double y, double z, double horizontalRadius, double verticalRadius, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
		ChunkPos chunkPos = chunk.getPos();
		double middleX = chunkPos.getMiddleBlockX();
		double middleZ = chunkPos.getMiddleBlockZ();
		double d2 = 16.0D + horizontalRadius * 2.0D;
		if (!(Math.abs(x - middleX) > d2) && !(Math.abs(z - middleZ) > d2)) {
			int minX = chunkPos.getMinBlockX();
			int minZ = chunkPos.getMinBlockZ();
			int k = Math.max(Mth.floor(x - horizontalRadius) - minX - 1, 0);
			int l = Math.min(Mth.floor(x + horizontalRadius) - minX, 15);
			int i1 = Math.max(Mth.floor(y - verticalRadius) - 1, context.getMinGenY() + 1);
			int k1 = Math.min(Mth.floor(y + verticalRadius) + 1, context.getMinGenY() + context.getGenDepth() - 7);
			int l1 = Math.max(Mth.floor(z - horizontalRadius) - minZ - 1, 0);
			int i2 = Math.min(Mth.floor(z + horizontalRadius) - minZ, 15);

			boolean flag = false;
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

			for (int j2 = k; j2 <= l; ++j2) {
				int k2 = chunkPos.getBlockX(j2);
				double d3 = ((double) k2 + 0.5D - x) / horizontalRadius;

				for (int l2 = l1; l2 <= i2; ++l2) {
					int i3 = chunkPos.getBlockZ(l2);
					double d4 = ((double) i3 + 0.5D - z) / horizontalRadius;
					if (!(d3 * d3 + d4 * d4 >= 1.0D)) {
						MutableBoolean mutableboolean = new MutableBoolean(false);

						for (int j3 = k1; j3 > i1; --j3) {
							double d5 = ((double) j3 - 0.5D - y) / verticalRadius;
							if (!skipChecker.shouldSkip(context, d3, d5, d4, j3) && !carvingMask.get(j2, j3, l2)) {
								carvingMask.set(j2, j3, l2);
								blockpos$mutableblockpos.set(k2, j3, i3);
								flag |= this.carveBlock(context, config, chunk, biomeAccessor, carvingMask, blockpos$mutableblockpos, blockpos$mutableblockpos1, aquifer, mutableboolean);
							}
						}
					}
				}
			}

			return flag;
		} else {
			return false;
		}
	}

	@Override
	protected boolean carveBlock(CarvingContext context, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, CarvingMask carvingMask, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos checkPos, Aquifer aquifer, MutableBoolean reachedSurface) {
		BlockState chunkState = chunk.getBlockState(pos);
		if (chunkState.is(TofuBlocks.TOFU_TERRAIN_ZUNDA.get())) {
			reachedSurface.setTrue();
		}

		if (!this.canReplaceBlock(config, chunkState)) {
			return false;
		} else {
			BlockState carveState = this.getCarveState(context, config, pos, aquifer);
			if (carveState == null) {
				return false;
			} else {
				chunk.setBlockState(pos, carveState);
				if (aquifer.shouldScheduleFluidUpdate() && !carveState.getFluidState().isEmpty()) {
					chunk.markPosForPostprocessing(pos);
				}

				if (reachedSurface.isTrue()) {
					checkPos.setWithOffset(pos, Direction.DOWN);
					if (chunk.getBlockState(checkPos).is(TofuBlocks.TOFU_TERRAIN.get())) {
						context.topMaterial(biomeAccessor, chunk, checkPos, !carveState.getFluidState().isEmpty()).ifPresent((state) -> {
							chunk.setBlockState(checkPos, state);
							if (!state.getFluidState().isEmpty()) {
								chunk.markPosForPostprocessing(checkPos);
							}
						});
					}
				}
				return true;
			}
		}
	}

	private @Nullable BlockState getCarveState(CarvingContext context, CaveCarverConfiguration configuration, BlockPos blockPos, Aquifer aquifer) {
		if (blockPos.getY() <= configuration.lavaLevel.resolveY(context)) {
			return TofuBlocks.DOUBANJIANG.get().defaultBlockState();
		} else {
			BlockState state = aquifer.computeSubstance(new DensityFunction.SinglePointContext(blockPos.getX(), blockPos.getY(), blockPos.getZ()), 0.0);
			if (state == null) {
				return CAVE_AIR;
			} else {
				return state;
			}
		}
	}
}