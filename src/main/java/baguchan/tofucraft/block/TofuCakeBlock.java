package baguchan.tofucraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class TofuCakeBlock extends Block {
	public static final IntegerProperty BITES = BlockStateProperties.field_208173_Z;

	protected static final VoxelShape[] SHAPES = new VoxelShape[]{Block.func_208617_a(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.func_208617_a(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.func_208617_a(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.func_208617_a(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.func_208617_a(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.func_208617_a(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.func_208617_a(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D)};

	private final int foodLevel;

	private final float foodSaturation;

	public TofuCakeBlock(int foodLevel, float foodSaturation, Properties properties) {
		super(properties);
		this.foodLevel = foodLevel;
		this.foodSaturation = foodSaturation;
		func_180632_j((BlockState) ((BlockState) this.field_176227_L.func_177621_b()).setValue(BITES, Integer.valueOf(0)));
	}

	public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[((Integer) state.func_177229_b((Property) BITES)).intValue()];
	}

	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isClientSide) {
			ItemStack itemstack = player.getItemInHand(handIn);
			if (eatSlice(worldIn, pos, state, player).func_226246_a_())
				return ActionResultType.SUCCESS;
			if (itemstack.isEmpty())
				return ActionResultType.CONSUME;
		}
		return eatSlice(worldIn, pos, state, player);
	}

	private ActionResultType eatSlice(IWorld world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!player.func_71043_e(false))
			return ActionResultType.PASS;
		player.func_195066_a(Stats.field_188076_J);
		player.func_71024_bL().func_75122_a(this.foodLevel, this.foodSaturation);
		int i = ((Integer) state.func_177229_b((Property) BITES)).intValue();
		if (i < 6) {
			world.setBlock(pos, state.setValue(BITES, Integer.valueOf(i + 1)), 3);
		} else {
			world.removeBlock(pos, false);
		}
		return ActionResultType.SUCCESS;
	}

	public BlockState func_196271_a(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
		return (p_196271_2_ == Direction.DOWN && !p_196271_1_.func_196955_c((IWorldReader) p_196271_4_, p_196271_5_)) ? Blocks.field_150350_a.defaultBlockState() : super.func_196271_a(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
	}

	public boolean func_196260_a(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.func_177977_b()).func_185904_a().func_76220_a();
	}

	protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
		builder.func_206894_a(new Property[]{BITES});
	}

	public int func_180641_l(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
		return (7 - ((Integer) p_180641_1_.func_177229_b((Property) BITES)).intValue()) * 2;
	}

	public boolean func_149740_M(BlockState p_149740_1_) {
		return true;
	}

	public boolean func_196266_a(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
		return false;
	}
}
