package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class TofuBushBlock extends BushBlock {
	public TofuBushBlock(Properties properties) {
		super(properties);
	}

	protected boolean func_200014_a_(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.func_235714_a_((ITag) TofuTags.Blocks.TOFU_TERRAIN);
	}
}
