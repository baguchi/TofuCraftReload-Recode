package baguchi.tofucraft.block.crop;

import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SesameCropsBlock extends CropBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.box(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 3.0D, 12.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 5.0D, 12.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 6.0D, 12.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 7.0D, 12.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D)};

	public SesameCropsBlock(Properties builder) {
		super(builder);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return TofuItems.SEEDS_SESAME.get();
	}


	@Override
	public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
		return SHAPES[p_52297_.getValue(AGE)];
	}
}
