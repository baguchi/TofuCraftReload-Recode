package baguchan.tofucraft.entity.path;

import baguchan.tofucraft.registry.TofuTags;
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

	public BlockPathTypes getBlockPathType(BlockGetter p_77472_, int p_77473_, int p_77474_, int p_77475_, Mob p_77476_) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int i = p_77473_; i < p_77473_ + this.entityWidth; ++i) {
			for (int j = p_77474_; j < p_77474_ + this.entityHeight; ++j) {
				for (int k = p_77475_; k < p_77475_ + this.entityDepth; ++k) {
					FluidState fluidstate = p_77472_.getFluidState(blockpos$mutableblockpos.set(i, j, k));
					BlockState blockstate = p_77472_.getBlockState(blockpos$mutableblockpos.set(i, j, k));
					if (fluidstate.isEmpty() && blockstate.isPathfindable(p_77472_, blockpos$mutableblockpos.below(), PathComputationType.WATER) && blockstate.isAir()) {
						return BlockPathTypes.BREACH;
					}

					if (!fluidstate.is(FluidTags.WATER) && !fluidstate.is(TofuTags.Fluids.SOYMILK)) {
						return BlockPathTypes.BLOCKED;
					}
				}
			}
		}

		BlockState blockstate1 = p_77472_.getBlockState(blockpos$mutableblockpos);
		return blockstate1.isPathfindable(p_77472_, blockpos$mutableblockpos, PathComputationType.WATER) ? BlockPathTypes.WATER : BlockPathTypes.BLOCKED;
	}
}
