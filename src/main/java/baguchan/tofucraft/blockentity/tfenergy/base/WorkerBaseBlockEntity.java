package baguchan.tofucraft.blockentity.tfenergy.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class WorkerBaseBlockEntity extends EnergyBaseBlockEntity {
	public WorkerBaseBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int energyMax) {
		super(p_155228_, p_155229_, p_155230_, energyMax);
	}

	@Override
	public boolean canReceive(BlockEntity from) {
		return true;
	}

	@Override
	public boolean canDrain(BlockEntity to) {
		return false;
	}

}
