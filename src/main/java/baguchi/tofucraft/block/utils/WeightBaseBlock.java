package baguchi.tofucraft.block.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class WeightBaseBlock extends Block {
	public static final EnumProperty<Stat> STAT = EnumProperty.create("stat", Stat.class);
	public static final IntegerProperty TIME = IntegerProperty.create("time", 0, 5);

	public WeightBaseBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(STAT, Stat.USING).setValue(TIME, 0));
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		Stat stat = getStat(state);
		int time = state.getValue(TIME);

		if (isUnderWeight(worldIn, pos)) {
			if (time < 5 && random.nextInt(4) == 0) {
				worldIn.setBlock(pos, state.setValue(TIME, time + 1), 3);
			}

			if (time >= 5 && stat == Stat.USING) {
				worldIn.setBlock(pos, state.setValue(STAT, Stat.USED), 3);
			}
		}
	}

	public boolean isUnderWeight(Level world, BlockPos pos) {
		BlockState weightBlock = world.getBlockState(pos.above());
		float weightHardness = weightBlock.getDestroySpeed(world, pos.above());
		boolean isWeightValid = (weightBlock.isFaceSturdy(world, pos, Direction.DOWN) && (weightHardness >= 1.0F || weightHardness < 0.0F)) && !(weightBlock.getBlock() instanceof WeightBaseBlock);
		return (isWeightValid);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAT, TIME);
	}

	public Stat getStat(BlockState meta) {
		if (meta.getBlock() == this)
			return meta.getValue(STAT);
		return Stat.USING;
	}

	public enum Stat implements StringRepresentable {
		USING("using"),
		USED("used");

		private final String name;


		Stat(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
