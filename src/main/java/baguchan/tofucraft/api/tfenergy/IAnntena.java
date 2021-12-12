package baguchan.tofucraft.api.tfenergy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IAnntena {
	public double getRadius(BlockPos pos, Level world);

	public int getPower(BlockPos pos, Level world);
}
