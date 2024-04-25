package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.blockentity.tfenergy.TFCrafterBlockEntity;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class TFCrafterBlock extends BaseEntityBlock {
	public static final MapCodec<TFCrafterBlock> CODEC = simpleCodec(TFCrafterBlock::new);
	public static final BooleanProperty CRAFTING = BlockStateProperties.CRAFTING;
	private static final EnumProperty<FrontAndTop> ORIENTATION = BlockStateProperties.ORIENTATION;

	public TFCrafterBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(ORIENTATION, FrontAndTop.NORTH_UP).setValue(CRAFTING, false));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	public BlockState rotate(BlockState p_307240_, Rotation p_307431_) {
		return p_307240_.setValue(ORIENTATION, p_307431_.rotation().rotate(p_307240_.getValue(ORIENTATION)));
	}

	public BlockState mirror(BlockState p_307514_, Mirror p_307198_) {
		return p_307514_.setValue(ORIENTATION, p_307198_.rotation().rotate(p_307514_.getValue(ORIENTATION)));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState p_307454_, Level p_307255_, BlockPos p_307303_, Player p_307670_, BlockHitResult p_307546_) {
		if (p_307255_.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			BlockEntity blockentity = p_307255_.getBlockEntity(p_307303_);
			if (blockentity instanceof TFCrafterBlockEntity) {
				p_307670_.openMenu((TFCrafterBlockEntity) blockentity);
			}

			return InteractionResult.CONSUME;
		}
	}

	public void onRemove(BlockState p_48713_, Level p_48714_, BlockPos p_48715_, BlockState p_48716_, boolean p_48717_) {
		if (!p_48713_.is(p_48716_.getBlock())) {
			BlockEntity blockentity = p_48714_.getBlockEntity(p_48715_);
			if (blockentity instanceof TFCrafterBlockEntity) {
				if (p_48714_ instanceof ServerLevel) {
					Containers.dropContents(p_48714_, p_48715_, (TFCrafterBlockEntity) blockentity);
					//((TFCrafterBlockEntity) blockentity).getRecipesToAwardAndPopExperience((ServerLevel) p_48714_, Vec3.atCenterOf(p_48715_));
				}

				p_48714_.updateNeighbourForOutputSignal(p_48715_, this);
			}

			super.onRemove(p_48713_, p_48714_, p_48715_, p_48716_, p_48717_);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_307445_) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState p_307633_, Level p_307264_, BlockPos p_307557_) {
		BlockEntity blockentity = p_307264_.getBlockEntity(p_307557_);
		return blockentity instanceof TFCrafterBlockEntity crafterblockentity ? crafterblockentity.getRedstoneSignal() : 0;
	}

	public RenderShape getRenderShape(BlockState p_48727_) {
		return RenderShape.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_307200_) {
		p_307200_.add(ORIENTATION, CRAFTING);
	}

	public BlockState getStateForPlacement(BlockPlaceContext p_307251_) {
		Direction direction = p_307251_.getNearestLookingDirection().getOpposite();
		Direction var10000;
		switch (direction) {
			case DOWN:
				var10000 = p_307251_.getHorizontalDirection().getOpposite();
				break;
			case UP:
				var10000 = p_307251_.getHorizontalDirection();
				break;
			case NORTH:
			case SOUTH:
			case WEST:
			case EAST:
				var10000 = Direction.UP;
				break;
			default:
				throw new IncompatibleClassChangeError();
		}

		Direction direction1 = var10000;
		return this.defaultBlockState().setValue(ORIENTATION, FrontAndTop.fromFrontAndTop(direction, direction1));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new TFCrafterBlockEntity(p_153215_, p_153216_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152382_, BlockState p_152383_, BlockEntityType<T> p_152384_) {
		return createTicker(p_152382_, p_152384_, TofuBlockEntitys.TF_CRAFTER.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends TFCrafterBlockEntity> p_151990_) {
		return createTickerHelper(p_151989_, p_151990_, TFCrafterBlockEntity::tick);
	}
}