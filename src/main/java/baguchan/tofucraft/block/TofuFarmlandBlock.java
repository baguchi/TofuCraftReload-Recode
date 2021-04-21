package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuBlocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

public class TofuFarmlandBlock extends Block {
	public static final IntegerProperty MOISTURE = BlockStateProperties.field_208133_ah;

	protected static final VoxelShape SHAPE = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	public TofuFarmlandBlock(Properties properties) {
		super(properties);
		func_180632_j((BlockState) ((BlockState) this.field_176227_L.func_177621_b()).func_206870_a((Property) MOISTURE, Integer.valueOf(0)));
	}

	public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.UP && !stateIn.func_196955_c((IWorldReader) worldIn, currentPos))
			worldIn.func_205220_G_().func_205360_a(currentPos, this, 1);
		return super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean func_196260_a(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos.func_177984_a());
		return (!blockstate.func_185904_a().func_76220_a() || blockstate.getBlock() instanceof net.minecraft.block.FenceGateBlock || blockstate.getBlock() instanceof net.minecraft.block.MovingPistonBlock);
	}

	public BlockState func_196258_a(BlockItemUseContext context) {
		return !func_176223_P().func_196955_c((IWorldReader) context.func_195991_k(), context.func_195995_a()) ? TofuBlocks.TOFU_TERRAIN.func_176223_P() : super.func_196258_a(context);
	}

	public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!state.func_196955_c((IWorldReader) worldIn, pos))
			turnToDirt(state, (World) worldIn, pos);
	}

	public void func_225542_b_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		int i = ((Integer) state.func_177229_b((Property) MOISTURE)).intValue();
		if (!hasWater((IWorldReader) worldIn, pos) && !worldIn.func_175727_C(pos.func_177984_a())) {
			if (i > 0) {
				worldIn.func_180501_a(pos, (BlockState) state.func_206870_a((Property) MOISTURE, Integer.valueOf(i - 1)), 2);
			} else if (!hasCrops((IBlockReader) worldIn, pos)) {
				turnToDirt(state, (World) worldIn, pos);
			}
		} else if (i < 7) {
			worldIn.func_180501_a(pos, (BlockState) state.func_206870_a((Property) MOISTURE, Integer.valueOf(7)), 2);
		}
	}

	public void func_180658_a(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (!worldIn.field_72995_K && ForgeHooks.onFarmlandTrample(worldIn, pos, TofuBlocks.TOFU_TERRAIN.func_176223_P(), fallDistance, entityIn))
			turnToDirt(worldIn.getBlockState(pos), worldIn, pos);
		super.func_180658_a(worldIn, pos, entityIn, fallDistance);
	}

	public static void turnToDirt(BlockState p_199610_0_, World p_199610_1_, BlockPos p_199610_2_) {
		p_199610_1_.func_175656_a(p_199610_2_, func_199601_a(p_199610_0_, TofuBlocks.TOFU_TERRAIN.func_176223_P(), p_199610_1_, p_199610_2_));
	}

	private boolean hasCrops(IBlockReader worldIn, BlockPos pos) {
		BlockState plant = worldIn.getBlockState(pos.func_177984_a());
		BlockState state = worldIn.getBlockState(pos);
		return (plant.getBlock() instanceof IPlantable && state.canSustainPlant(worldIn, pos, Direction.UP, (IPlantable) plant.getBlock()));
	}

	private static boolean hasWater(IWorldReader worldIn, BlockPos pos) {
		for (BlockPos blockpos : BlockPos.func_218278_a(pos.func_177982_a(-4, 0, -4), pos.func_177982_a(4, 1, 4))) {
			if (worldIn.func_204610_c(blockpos).func_206884_a((ITag) FluidTags.field_206959_a))
				return true;
		}
		return FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
	}

	protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
		builder.func_206894_a(new Property[]{(Property) MOISTURE});
	}

	public boolean func_196266_a(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
		return false;
	}
}
