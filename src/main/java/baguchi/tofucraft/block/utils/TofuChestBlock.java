package baguchi.tofucraft.block.utils;

import baguchi.tofucraft.blockentity.TofuChestBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TofuChestBlock extends ChestBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<TofuChestBlock> CODEC = simpleCodec(p_304364_ -> new TofuChestBlock(p_304364_, () -> TofuBlockEntitys.TOFUCHEST.get()));

	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final int EVENT_SET_OPEN_COUNT = 1;
	protected static final int AABB_OFFSET = 1;
	protected static final int AABB_HEIGHT = 14;
	protected static final VoxelShape NORTH_AABB = Block.box(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape SOUTH_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
	protected static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape EAST_AABB = Block.box(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
	protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);


	public TofuChestBlock(BlockBehaviour.Properties p_51490_, Supplier<BlockEntityType<? extends ChestBlockEntity>> p_51491_) {
		super(p_51491_, TofuSounds.TOFU_CHEST_OPEN.get(), TofuSounds.TOFU_CHEST_CLOSE.get(), p_51490_);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public MapCodec<? extends ChestBlock> codec() {
		return CODEC;
	}

	protected Stat<ResourceLocation> getOpenChestStat() {
		return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	}

	public BlockEntityType<? extends ChestBlockEntity> blockEntityType() {
		return this.blockEntityType.get();
	}


	@Override
	public BlockEntity newBlockEntity(BlockPos p_153064_, BlockState p_153065_) {
		return new TofuChestBlockEntity(p_153064_, p_153065_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153055_, BlockState p_153056_, BlockEntityType<T> p_153057_) {
		return p_153055_.isClientSide() ? createTickerHelper(p_153057_, this.blockEntityType(), TofuChestBlockEntity::lidAnimateTick) : null;
	}
}
