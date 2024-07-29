package baguchan.tofucraft.entity.path;

import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;

public class DoubanjiangSwimNodeEvaluator extends SwimNodeEvaluator {
	public DoubanjiangSwimNodeEvaluator(boolean allowBreaching) {
		super(allowBreaching);
	}

	@Override
	public PathType getPathTypeOfMob(PathfindingContext p_77472_, int p_77473_, int p_77474_, int p_77475_, Mob p_77476_) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int i = p_77473_; i < p_77473_ + this.entityWidth; ++i) {
			for (int j = p_77474_; j < p_77474_ + this.entityHeight; ++j) {
				for (int k = p_77475_; k < p_77475_ + this.entityDepth; ++k) {
					BlockState blockstate = p_77472_.getBlockState(blockpos$mutableblockpos.set(i, j, k));
					FluidState fluidstate = blockstate.getFluidState();
					if (fluidstate.isEmpty() && blockstate.isPathfindable(PathComputationType.WATER) && blockstate.isAir()) {
						return PathType.BREACH;
					}

					if (!fluidstate.is(FluidTags.WATER) && !fluidstate.is(TofuTags.Fluids.SOYMILK) && fluidstate.getFluidType() != TofuFluidTypes.DOUBANJIANG) {
						return PathType.BLOCKED;
					}
				}
			}
		}

		BlockState blockstate1 = p_77472_.getBlockState(blockpos$mutableblockpos);
		return blockstate1.isPathfindable(PathComputationType.WATER) ? PathType.WATER : PathType.BLOCKED;
	}
}
