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
	public static final IntegerProperty BITES = BlockStateProperties.BITES;

	protected static final VoxelShape[] SHAPES = new VoxelShape[]{Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D)};

	private final int foodLevel;

	private final float foodSaturation;

	public TofuCakeBlock(int foodLevel, float foodSaturation, Properties properties) {
		super(properties);
		this.foodLevel = foodLevel;
		this.foodSaturation = foodSaturation;
		registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[((Integer) state.getValue((Property) BITES)).intValue()];
	}

	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isClientSide) {
			ItemStack itemstack = player.getItemInHand(handIn);
			if (eat(worldIn, pos, state, player).consumesAction())
				return ActionResultType.SUCCESS;
			if (itemstack.isEmpty())
				return ActionResultType.CONSUME;
		}
		return eat(worldIn, pos, state, player);
	}

	private ActionResultType eat(IWorld p_226911_1_, BlockPos p_226911_2_, BlockState p_226911_3_, PlayerEntity p_226911_4_) {
		if (!p_226911_4_.canEat(false)) {
			return ActionResultType.PASS;
		} else {
			p_226911_4_.awardStat(Stats.EAT_CAKE_SLICE);
			p_226911_4_.getFoodData().eat(2, 0.1F);
			int i = p_226911_3_.getValue(BITES);
			if (i < 6) {
				p_226911_1_.setBlock(p_226911_2_, p_226911_3_.setValue(BITES, Integer.valueOf(i + 1)), 3);
			} else {
				p_226911_1_.removeBlock(p_226911_2_, false);
			}

			return ActionResultType.SUCCESS;
		}
	}

	public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
		return p_196271_2_ == Direction.DOWN && !p_196271_1_.canSurvive(p_196271_4_, p_196271_5_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
	}

	public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
		return p_196260_2_.getBlockState(p_196260_3_.below()).getMaterial().isSolid();
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		p_206840_1_.add(BITES);
	}

	public int getAnalogOutputSignal(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
		return (7 - p_180641_1_.getValue(BITES)) * 2;
	}

	public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
		return true;
	}

	public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
		return false;
	}
}
