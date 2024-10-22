package baguchi.tofucraft.block.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

public class TofuDoorBlock extends DoorBlock {
	public TofuDoorBlock(Properties properties, BlockSetType p_272854_) {
		super(p_272854_, properties);
	}

	@Override
	public @Nullable PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return !state.getValue(OPEN) ? PathType.DOOR_WOOD_CLOSED : super.getBlockPathType(state, level, pos, mob);
	}
}
