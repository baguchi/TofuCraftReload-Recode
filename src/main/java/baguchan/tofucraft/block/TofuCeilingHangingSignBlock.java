package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class TofuCeilingHangingSignBlock extends CeilingHangingSignBlock {
	public TofuCeilingHangingSignBlock(Properties p_58068_, WoodType p_58069_) {
		super(p_58068_, p_58069_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
		return TofuBlockEntitys.TOFU_HANGING_SIGN.get().create(p_154556_, p_154557_);
	}
}
