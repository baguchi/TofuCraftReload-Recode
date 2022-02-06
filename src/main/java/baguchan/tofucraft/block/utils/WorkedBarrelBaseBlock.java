package baguchan.tofucraft.block.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class WorkedBarrelBaseBlock extends Block {
	public static final EnumProperty<Stat> STAT = EnumProperty.create("stat", Stat.class);
	public static final IntegerProperty TIME = IntegerProperty.create("time", 0, 5);

	public WorkedBarrelBaseBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(STAT, Stat.USING));
	}

	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		Stat stat = getStat(state);
		int time = state.getValue(TIME);

		if (isUnderWeight(worldIn, pos)) {
			if (time < 5) {
				state.setValue(TIME, time + 1);
			}

			if (time >= 5 && stat == Stat.USING) {
				state.setValue(STAT, Stat.USED);
			}
		}
	}

	public boolean isUnderWeight(Level world, BlockPos pos) {
		BlockState weightBlock = world.getBlockState(pos.above());
		BlockState baseBlock = world.getBlockState(pos.below());
		boolean isWeightValid = (this != weightBlock.getBlock() && weightBlock != null && (weightBlock.getMaterial() == Material.STONE || weightBlock.getMaterial() == Material.METAL || weightBlock.getMaterial() == Material.HEAVY_METAL));
		float baseHardness = baseBlock.getDestroySpeed(world, pos.below());
		boolean isBaseValid = (baseBlock.isCollisionShapeFullBlock(world, pos) && (baseBlock.getMaterial() == Material.STONE || baseBlock.getMaterial() == Material.METAL || baseHardness >= 1.0F || baseHardness < 0.0F));
		return (isWeightValid && isBaseValid);
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
		USED("used"),
		FLUID("fluid");

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
