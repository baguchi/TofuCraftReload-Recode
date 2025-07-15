package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class LeekBlock extends BushBlock implements BonemealableBlock {
	public static final MapCodec<LeekBlock> CODEC = simpleCodec(LeekBlock::new);
	public LeekBlock(Properties p_51021_) {
		super(p_51021_);
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(TofuTags.Blocks.TOFU_TERRAIN) || p_51042_.is(TofuBlocks.MOMENTOFU.get());
	}

	public void growLeek(ServerLevel serverLevel, BlockPos pos, BlockState p_54862_, RandomSource randomSource) {
		BlockState blockstate = TofuBlocks.TALL_LEEK.get().defaultBlockState();
		BlockState blockstate1 = blockstate.setValue(TallLeekBlock.HALF, DoubleBlockHalf.UPPER);
		BlockPos blockpos = pos.above();
		serverLevel.setBlock(pos, blockstate, 2);
		serverLevel.setBlock(blockpos, blockstate1, 2);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader p_256655_, BlockPos p_256553_, BlockState p_256213_) {
		BlockState blockstate = p_256655_.getBlockState(p_256553_.below());
		return blockstate.is(TofuTags.Blocks.TOFU_TERRAIN) || blockstate.is(TofuBlocks.MOMENTOFU.get());
	}

	@Override
	public boolean isBonemealSuccess(Level p_222428_, RandomSource p_222429_, BlockPos p_222430_, BlockState p_222431_) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel p_50893_, RandomSource p_50894_, BlockPos p_50895_, BlockState p_50896_) {
		growLeek(p_50893_, p_50895_, p_50896_, p_50894_);
	}
}
