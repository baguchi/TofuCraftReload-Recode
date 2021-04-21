package baguchan.tofucraft.block.crop;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.Property;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SoybeanCropsBlock extends CropsBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)};

	public SoybeanCropsBlock(Properties builder) {
		super(builder);
	}

	protected boolean func_200014_a_(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (super.func_200014_a_(state, worldIn, pos) || state.func_203425_a(TofuBlocks.TOFU_FARMLAND));
	}

	protected IItemProvider getSeedsItem() {
		return (IItemProvider) TofuItems.SEEDS_SOYBEANS;
	}

	public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[((Integer) state.func_177229_b((Property) func_185524_e())).intValue()];
	}
}
