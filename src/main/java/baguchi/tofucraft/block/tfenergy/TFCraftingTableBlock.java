package baguchi.tofucraft.block.tfenergy;

import baguchi.tofucraft.blockentity.tfenergy.TFCraftingTableBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class TFCraftingTableBlock extends TFBaseEntityBlock {
	public static final MapCodec<TFCraftingTableBlock> CODEC = simpleCodec(TFCraftingTableBlock::new);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final EnumProperty<Direction> HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

	public TFCraftingTableBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(LIT, false));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	public BlockState rotate(BlockState p_307240_, Rotation p_307431_) {
		return p_307240_.setValue(HORIZONTAL_FACING, p_307431_.rotation().rotate(p_307240_.getValue(HORIZONTAL_FACING)));
	}

	public BlockState mirror(BlockState p_307514_, Mirror p_307198_) {
		return p_307514_.setValue(HORIZONTAL_FACING, p_307198_.rotation().rotate(p_307514_.getValue(HORIZONTAL_FACING)));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState p_307454_, Level p_307255_, BlockPos p_307303_, Player p_307670_, BlockHitResult p_307546_) {
		if (p_307255_.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			BlockEntity blockentity = p_307255_.getBlockEntity(p_307303_);
			if (blockentity instanceof TFCraftingTableBlockEntity) {
				p_307670_.openMenu((TFCraftingTableBlockEntity) blockentity, p_307303_);
			}

			return InteractionResult.CONSUME;
		}
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState p_394424_, ServerLevel p_394241_, BlockPos p_393520_, boolean p_394545_) {
		super.affectNeighborsAfterRemoval(p_394424_, p_394241_, p_393520_, p_394545_);

		BlockEntity blockentity = p_394241_.getBlockEntity(p_393520_);
		if (blockentity instanceof TFCraftingTableBlockEntity) {
			if (p_394241_ instanceof ServerLevel) {
				Containers.dropContents(p_394241_, p_393520_, (TFCraftingTableBlockEntity) blockentity);
			}

			p_394241_.updateNeighbourForOutputSignal(p_393520_, this);
		}

	}

	public void onRemove(BlockState p_48713_, Level p_48714_, BlockPos p_48715_, BlockState p_48716_, boolean p_48717_) {

	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_307445_) {
		return true;
	}

	/*@Override
	public int getAnalogOutputSignal(BlockState p_307633_, Level p_307264_, BlockPos p_307557_) {
		BlockEntity blockentity = p_307264_.getBlockEntity(p_307557_);
		return blockentity instanceof TFCraftingTableBlockEntity crafterblockentity ? crafterblockentity.getRedstoneSignal() : 0;
	}*/

	public RenderShape getRenderShape(BlockState p_48727_) {
		return RenderShape.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_307200_) {
		p_307200_.add(HORIZONTAL_FACING, LIT);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, p_48689_.getHorizontalDirection().getOpposite());
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new TFCraftingTableBlockEntity(p_153215_, p_153216_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152382_, BlockState p_152383_, BlockEntityType<T> p_152384_) {
		return createTicker(p_152382_, p_152384_, TofuBlockEntitys.TF_CRAFTING_TABLE.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends TFCraftingTableBlockEntity> p_151990_) {
		return createTickerHelper(p_151989_, p_151990_, TFCraftingTableBlockEntity::tick);
	}
}