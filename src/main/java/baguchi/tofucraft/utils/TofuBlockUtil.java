package baguchi.tofucraft.utils;

import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;

public class TofuBlockUtil {
	public static boolean isSaltAround(LevelReader levelReader, BlockPos blockpos1) {
		for (Direction direction : Direction.values()) {
			if (levelReader.getBlockState(blockpos1.offset(direction.getUnitVec3i())).is(TofuBlocks.SALT_BLOCK)) {
				return true;
			}
		}
		return false;
	}
}
