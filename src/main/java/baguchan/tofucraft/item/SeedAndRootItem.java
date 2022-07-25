package baguchan.tofucraft.item;

import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class SeedAndRootItem extends PlaceOnWaterBlockItem {
	private final Block root;

	public SeedAndRootItem(Block p_220226_, Block root, Properties p_220227_) {
		super(p_220226_, p_220227_);
		this.root = root;
	}

	protected boolean placeBlock(BlockPlaceContext p_40578_, BlockState p_40579_) {
		if (p_40578_.getLevel().getFluidState(p_40578_.getClickedPos().below()).is(Fluids.WATER) && p_40578_.getLevel().getBlockState(p_40578_.getClickedPos().below()).is(Blocks.WATER) && this.root.defaultBlockState().canSurvive(p_40578_.getLevel(), p_40578_.getClickedPos().below())) {
			p_40578_.getLevel().setBlock(p_40578_.getClickedPos().below(), this.root.defaultBlockState(), 11);
			return p_40578_.getLevel().setBlock(p_40578_.getClickedPos(), p_40579_, 11);
		} else {
			return false;
		}
	}

	@Override
	protected boolean mustSurvive() {
		return false;
	}
}
