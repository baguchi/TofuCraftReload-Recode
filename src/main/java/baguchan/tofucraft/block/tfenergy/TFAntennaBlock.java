package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.api.tfenergy.IAnntena;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class TFAntennaBlock extends Block implements IAnntena {
	public TFAntennaBlock(Properties properties) {
		super(properties);
	}

	@Override
	public double getRadius(BlockPos pos, Level world) {
		return 7;
	}

	@Override
	public int getPower(BlockPos pos, Level world) {
		return 20;
	}
}
