package baguchi.tofucraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

public class SaltBlock extends FallFoodBlock {

	public static final MapCodec<SaltBlock> CODEC = simpleCodec(SaltBlock::new);

	public SaltBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends FallFoodBlock> codec() {
		return CODEC;
	}

	@Override
	public @Nullable PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return PathType.DAMAGING;
	}

	@Override
	public @Nullable PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
		return PathType.DAMAGING_IN_NEIGHBOR;
	}
}