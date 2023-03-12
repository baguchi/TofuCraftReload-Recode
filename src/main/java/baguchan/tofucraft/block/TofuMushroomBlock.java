package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;
import java.util.function.Supplier;

public class TofuMushroomBlock extends BushBlock implements BonemealableBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 11.0D, 12.0D);

    private final Supplier<Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>>> feature;


    public TofuMushroomBlock(Properties properties, Supplier<Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>>> p_53601_) {
        super(properties);
        this.feature = p_53601_;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(TofuTags.Blocks.TOFU_TERRAIN);
	}

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_53608_, BlockPos p_53609_, BlockState p_53610_, boolean p_53611_) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level p_53613_, Random p_53614_, BlockPos p_53615_, BlockState p_53616_) {
        return (double) p_53614_.nextFloat() < 0.4D;
    }

    @Override
    public void performBonemeal(ServerLevel p_53603_, Random p_53604_, BlockPos p_53605_, BlockState p_53606_) {
        this.feature.get().value().place(p_53603_, p_53603_.getChunkSource().getGenerator(), p_53604_, p_53605_);
    }
}
