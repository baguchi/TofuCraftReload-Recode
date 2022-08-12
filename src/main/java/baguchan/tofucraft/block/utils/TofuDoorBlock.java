package baguchan.tofucraft.block.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

public class TofuDoorBlock extends DoorBlock {
	public TofuDoorBlock(Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return !state.getValue(OPEN) && state.getMaterial() != Material.METAL ? BlockPathTypes.DOOR_WOOD_CLOSED : super.getBlockPathType(state, level, pos, mob);
	}
}
