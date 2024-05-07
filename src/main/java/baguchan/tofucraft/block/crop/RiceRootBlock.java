package baguchan.tofucraft.block.crop;

import baguchan.tofucraft.registry.TofuBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class RiceRootBlock extends BushBlock implements BonemealableBlock {
	public static final MapCodec<RiceRootBlock> CODEC = simpleCodec(RiceRootBlock::new);
	public RiceRootBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected void randomTick(BlockState p_222954_, ServerLevel p_222955_, BlockPos p_222956_, RandomSource p_222957_) {
		super.randomTick(p_222954_, p_222955_, p_222956_, p_222957_);
		if (!p_222955_.isAreaLoaded(p_222956_, 1))
			return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (p_222955_.getRawBrightness(p_222956_, 0) >= 9) {
			if (p_222955_.getBlockState(p_222956_.above()).isAir() && p_222957_.nextInt(3) == 0) {
				p_222955_.setBlock(p_222956_.above(), TofuBlocks.RICE_CROP.get().defaultBlockState(), 3);
			}
		}
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(BlockTags.DIRT) && p_51043_.getBlockState(p_51044_.above()).getFluidState().is(Fluids.WATER);
	}

	@Override
	public FluidState getFluidState(BlockState p_60577_) {
		return Fluids.WATER.defaultFluidState();
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos p_50898_, BlockState p_50899_) {
		return p_256559_.getBlockState(p_50898_.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel p_220874_, RandomSource p_220875_, BlockPos p_220876_, BlockState p_220877_) {
		p_220874_.setBlock(p_220876_.above(), TofuBlocks.RICE_CROP.get().defaultBlockState(), 3);
	}
}
