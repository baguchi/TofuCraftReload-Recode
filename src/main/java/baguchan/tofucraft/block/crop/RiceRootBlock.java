package baguchan.tofucraft.block.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class RiceRootBlock extends BushBlock {
    public RiceRootBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(BlockTags.DIRT) && p_51043_.getBlockState(p_51044_.above()).getFluidState().is(Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState p_60577_) {
        return Fluids.WATER.defaultFluidState();
    }
}
