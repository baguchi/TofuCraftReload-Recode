package baguchi.tofucraft.block;

import baguchi.tofucraft.blockentity.FoodPlateBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;

public class FoodPlateBlock extends BaseEntityBlock {
	public static final MapCodec<FoodPlateBlock> CODEC = simpleCodec(FoodPlateBlock::new);
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D);

	public FoodPlateBlock(Properties p_49224_) {
		super(p_49224_);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void animateTick(BlockState p_220697_, Level level, BlockPos blockPos, RandomSource p_220700_) {
		BlockEntity tileEntity = level.getBlockEntity(blockPos);
		if (tileEntity instanceof FoodPlateBlockEntity foodPlate) {
			if (foodPlate.isFire()) {
				addParticlesAndSound(level, new Vec3(0.5F, 0.5F, 0.5F).add((double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ()), p_220700_);
			}
		}

	}

	private static void addParticlesAndSound(Level p_220688_, Vec3 p_220689_, RandomSource p_220690_) {
		float f = p_220690_.nextFloat();
		if (f < 0.3F) {
			p_220688_.addParticle(ParticleTypes.SMOKE, p_220689_.x, p_220689_.y, p_220689_.z, (double) 0.0F, (double) 0.0F, (double) 0.0F);
			if (f < 0.17F) {
				p_220688_.playLocalSound(p_220689_.x + (double) 0.5F, p_220689_.y + (double) 0.5F, p_220689_.z + (double) 0.5F, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + p_220690_.nextFloat(), p_220690_.nextFloat() * 0.7F + 0.3F, false);
			}
		}

		p_220688_.addParticle(ParticleTypes.SMALL_FLAME, p_220689_.x, p_220689_.y, p_220689_.z, (double) 0.0F, (double) 0.0F, (double) 0.0F);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		BlockEntity tileEntity = level.getBlockEntity(blockPos);
		if (tileEntity instanceof FoodPlateBlockEntity) {
			FoodPlateBlockEntity plateBlockEntity = (FoodPlateBlockEntity) tileEntity;
			ItemStack heldStack = player.getItemInHand(hand);

			if (plateBlockEntity.isEmpty()) {
				if (heldStack.isEmpty()) {
					return InteractionResult.TRY_WITH_EMPTY_HAND;
				} else if (plateBlockEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
					level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
					return InteractionResult.SUCCESS;
				}
			} else if (!heldStack.isEmpty()) {
				if (heldStack.is(Items.FLINT_AND_STEEL) || heldStack.is(Items.FIRE_CHARGE)) {
					if (plateBlockEntity.getStoredItem().is(ItemTags.CANDLES)) {
						if (!plateBlockEntity.isFire()) {
							level.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS);
							plateBlockEntity.setFire(true);
							return InteractionResult.SUCCESS;
						}
					}
				}

				return InteractionResult.CONSUME;
			} else if (hand.equals(InteractionHand.MAIN_HAND)) {
				if (!player.isCreative()) {
					if (!player.getInventory().add(plateBlockEntity.removeItem())) {
						Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), plateBlockEntity.removeItem());
					}
				} else {
					plateBlockEntity.removeItem();
				}
				level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.WOOD_HIT, SoundSource.BLOCKS, 0.25F, 0.5F);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.TRY_WITH_EMPTY_HAND;
	}

	@Override
	public @org.jetbrains.annotations.Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}

	@Override
	public void affectNeighborsAfterRemoval(BlockState state, ServerLevel worldIn, BlockPos pos, boolean isMoving) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof FoodPlateBlockEntity) {
			worldIn.updateNeighbourForOutputSignal(pos, this);
		}
		super.affectNeighborsAfterRemoval(state, worldIn, pos, isMoving);
	}
	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof FoodPlateBlockEntity) {
			return !((FoodPlateBlockEntity) tileEntity).isEmpty() ? 15 : 0;
		}
		return 0;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return TofuBlockEntitys.FOODPLATE.get().create(pos, state);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess p_374457_, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource randomSource) {
		return facing == Direction.DOWN && !state.canSurvive(worldIn, currentPos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, worldIn, p_374457_, currentPos, facing, facingPos, facingState, randomSource);
	}

	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState rotate(BlockState pState, Rotation pRot) {
		return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}


	@Override
	public BlockState playerWillDestroy(Level p_56212_, BlockPos p_56213_, BlockState p_56214_, Player p_56215_) {
		BlockEntity blockentity = p_56212_.getBlockEntity(p_56213_);
		if (blockentity instanceof FoodPlateBlockEntity foodPlateBlockEntity) {
			if (!p_56212_.isClientSide && p_56215_.preventsBlockDrops() && !foodPlateBlockEntity.isEmpty()) {
				ItemStack itemstack = this.asItem().getDefaultInstance();
				itemstack.applyComponents(blockentity.collectComponents());
				ItemEntity itementity = new ItemEntity(p_56212_, p_56213_.getX() + 0.5, p_56213_.getY() + 0.5, p_56213_.getZ() + 0.5, itemstack);
				itementity.setDefaultPickUpDelay();
				p_56212_.addFreshEntity(itementity);
			}
		}

		return super.playerWillDestroy(p_56212_, p_56213_, p_56214_, p_56215_);
	}
}
