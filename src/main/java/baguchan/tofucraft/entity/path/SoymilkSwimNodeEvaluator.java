package baguchan.tofucraft.entity.path;

import baguchan.tofucraft.registry.TofuFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;

public class SoymilkSwimNodeEvaluator extends SwimNodeEvaluator {
	public SoymilkSwimNodeEvaluator(boolean allowBreaching) {
		super(allowBreaching);
	}

	public BlockPathTypes getBlockPathType(BlockGetter p_77472_, int p_77473_, int p_77474_, int p_77475_, Mob p_77476_, int p_77477_, int p_77478_, int p_77479_, boolean p_77480_, boolean p_77481_) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int i = p_77473_; i < p_77473_ + p_77477_; ++i) {
			for (int j = p_77474_; j < p_77474_ + p_77478_; ++j) {
				for (int k = p_77475_; k < p_77475_ + p_77479_; ++k) {
					FluidState fluidstate = p_77472_.getFluidState(blockpos$mutableblockpos.set(i, j, k));
					BlockState blockstate = p_77472_.getBlockState(blockpos$mutableblockpos.set(i, j, k));
					if (fluidstate.isEmpty() && (blockstate.isPathfindable(p_77472_, blockpos$mutableblockpos.below(), PathComputationType.WATER) || p_77472_.getFluidState(blockpos$mutableblockpos).is(TofuFluids.SOYMILK.get())) && blockstate.isAir()) {
						return BlockPathTypes.BREACH;
					}

					if (!fluidstate.is(FluidTags.WATER) && !fluidstate.is(TofuFluids.SOYMILK.get())) {
						return BlockPathTypes.BLOCKED;
					}
				}
			}
		}

		BlockState blockstate1 = p_77472_.getBlockState(blockpos$mutableblockpos);
		return blockstate1.isPathfindable(p_77472_, blockpos$mutableblockpos, PathComputationType.WATER) || p_77472_.getFluidState(blockpos$mutableblockpos).is(TofuFluids.SOYMILK.get()) ? BlockPathTypes.WATER : BlockPathTypes.BLOCKED;
	}
}
