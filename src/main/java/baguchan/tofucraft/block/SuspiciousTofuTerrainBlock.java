package baguchan.tofucraft.block;

import baguchan.tofucraft.blockentity.SuspiciousTofuBlockEntity;
import baguchan.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuspiciousTofuTerrainBlock extends BrushableBlock {
	public SuspiciousTofuTerrainBlock(Properties properties) {
		super(TofuBlocks.TOFU_TERRAIN.get(), SoundEvents.WOOL_HIT, SoundEvents.WOOL_BREAK, properties);
	}

	public BlockEntity newBlockEntity(BlockPos p_272913_, BlockState p_273465_) {
		return new SuspiciousTofuBlockEntity(p_272913_, p_273465_);
	}
}
