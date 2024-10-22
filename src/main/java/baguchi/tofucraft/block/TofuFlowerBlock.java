package baguchi.tofucraft.block;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TofuFlowerBlock extends FlowerBlock {
	public TofuFlowerBlock(Holder<MobEffect> p_316154_, float p_332744_, Properties p_53514_) {
		super(p_316154_, p_332744_, p_53514_);
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return super.mayPlaceOn(p_51042_, p_51043_, p_51044_) || p_51042_.is(TofuTags.Blocks.TOFU_TERRAIN);
	}
}
