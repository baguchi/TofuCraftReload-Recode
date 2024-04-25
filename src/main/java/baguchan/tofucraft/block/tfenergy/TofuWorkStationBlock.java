package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.inventory.TofuWorkStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class TofuWorkStationBlock extends Block {
	private static final Component CONTAINER_TITLE = Component.translatable("container.tofucraft.tofu_work_station");
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D);

	public TofuWorkStationBlock(Properties p_57068_) {
		super(p_57068_);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	public BlockState getStateForPlacement(BlockPlaceContext p_57070_) {
		return this.defaultBlockState().setValue(FACING, p_57070_.getHorizontalDirection().getOpposite());
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, BlockHitResult p_60508_) {
		if (p_60504_.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			p_60506_.openMenu(p_60503_.getMenuProvider(p_60504_, p_60505_));
			return InteractionResult.CONSUME;
		}
	}

	@Nullable
	public MenuProvider getMenuProvider(BlockState p_57105_, Level p_57106_, BlockPos p_57107_) {
		return new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
			return new TofuWorkStationMenu(p_57074_, p_57075_, ContainerLevelAccess.create(p_57106_, p_57107_));
		}, CONTAINER_TITLE);
	}

	public VoxelShape getShape(BlockState p_57100_, BlockGetter p_57101_, BlockPos p_57102_, CollisionContext p_57103_) {
		return SHAPE;
	}

	public boolean useShapeForLightOcclusion(BlockState p_57109_) {
		return true;
	}

	public RenderShape getRenderShape(BlockState p_57098_) {
		return RenderShape.MODEL;
	}

	public BlockState rotate(BlockState p_57093_, Rotation p_57094_) {
		return p_57093_.setValue(FACING, p_57094_.rotate(p_57093_.getValue(FACING)));
	}

	public BlockState mirror(BlockState p_57090_, Mirror p_57091_) {
		return p_57090_.rotate(p_57091_.getRotation(p_57090_.getValue(FACING)));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57096_) {
		p_57096_.add(FACING);
	}

	@Override
	protected boolean isPathfindable(BlockState p_60475_, PathComputationType p_60478_) {
		return false;
	}
}