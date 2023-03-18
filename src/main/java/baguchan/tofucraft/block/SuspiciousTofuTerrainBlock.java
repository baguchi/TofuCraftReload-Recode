package baguchan.tofucraft.block;

import baguchan.tofucraft.blockentity.SuspiciousTofuBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SuspiciousSandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuspiciousTofuTerrainBlock extends SuspiciousSandBlock {
	public SuspiciousTofuTerrainBlock(Properties properties) {
		super(properties);
	}

	public BlockEntity newBlockEntity(BlockPos p_272913_, BlockState p_273465_) {
		return new SuspiciousTofuBlockEntity(p_272913_, p_273465_);
	}

	public void tick(BlockState p_273332_, ServerLevel p_272998_, BlockPos p_273141_, RandomSource p_272775_) {
		BlockEntity blockentity = p_272998_.getBlockEntity(p_273141_);
		if (blockentity instanceof SuspiciousTofuBlockEntity suspicioussandblockentity) {
			suspicioussandblockentity.checkReset();
		}

		if (FallingBlock.isFree(p_272998_.getBlockState(p_273141_.below())) && p_273141_.getY() >= p_272998_.getMinBuildHeight()) {
			FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(p_272998_, p_273141_, p_273332_);
			fallingblockentity.disableDrop();
		}
	}
}
