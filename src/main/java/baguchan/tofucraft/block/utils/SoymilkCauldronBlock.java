package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoymilkCauldronBlock extends Block {
	public static final BooleanProperty SOYCHEESE = BooleanProperty.create("soycheese");

	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), BooleanOp.ONLY_FIRST);

	public SoymilkCauldronBlock(BlockBehaviour.Properties p_151946_) {
		super(p_151946_);
		this.registerDefaultState(this.stateDefinition.any().setValue(SOYCHEESE, false));
	}

	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack itemHeld = player.getItemInHand(handIn);
		if (state.getValue(SOYCHEESE)) {
			ItemStack cheese = new ItemStack(TofuItems.SOY_CHEESE.get(), 4);
			float f = 0.7F;
			double d0 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
			double d1 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
			double d2 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
			ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, cheese);
			itemEntity.setPickUpDelay(10);
			worldIn.addFreshEntity(itemEntity);
			worldIn.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 2);
			return InteractionResult.SUCCESS;
		} else if (itemHeld.is(Items.BUCKET)) {
			ItemStack bucket = new ItemStack(TofuItems.BUCKET_SOYMILK.get());
			worldIn.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
			if (itemHeld.getCount() == 1) {
				player.setItemInHand(handIn, bucket);
			} else {
				if (!player.getInventory().add(bucket))
					worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, bucket));
				itemHeld.shrink(1);
			}
			worldIn.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 2);
			return InteractionResult.SUCCESS;
		}

		return super.use(state, worldIn, pos, player, handIn, hit);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!state.getValue(SOYCHEESE) && random.nextInt(3) == 0) {
			worldIn.setBlock(pos, state.setValue(SOYCHEESE, true), 2);
		}
	}

	public VoxelShape getShape(BlockState p_151964_, BlockGetter p_151965_, BlockPos p_151966_, CollisionContext p_151967_) {
		return SHAPE;
	}

	public VoxelShape getInteractionShape(BlockState p_151955_, BlockGetter p_151956_, BlockPos p_151957_) {
		return INSIDE;
	}

	public boolean isPathfindable(BlockState p_151959_, BlockGetter p_151960_, BlockPos p_151961_, PathComputationType p_151962_) {
		return false;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
		super.createBlockStateDefinition(p_49915_);
		p_49915_.add(SOYCHEESE);
	}
}