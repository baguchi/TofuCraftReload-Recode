package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.utils.TileScanner;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SproutsJarBlock extends Block implements SimpleWaterloggedBlock {
	public static VoxelShape SPROUTSJAR_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

	public static final EnumProperty<SproutsJarBlock.Stat> STAT = EnumProperty.create("stat", SproutsJarBlock.Stat.class);

	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;

	public static final BooleanProperty EAST = BlockStateProperties.EAST;

	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;

	public static final BooleanProperty WEST = BlockStateProperties.WEST;

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public SproutsJarBlock(BlockBehaviour.Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.canSurvive(worldIn, currentPos) && !worldIn.getBlockTicks().hasScheduledTick(currentPos, this))
			worldIn.scheduleTick(currentPos, this, 1);
		if (((Boolean) stateIn.getValue((Property) WATERLOGGED)).booleanValue()) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			SproutsJarBlock.Stat stat = getStat(stateIn);
			if (stat == SproutsJarBlock.Stat.EMPTY) {
				worldIn.setBlock(currentPos, stateIn.setValue(STAT, SproutsJarBlock.Stat.WATER), 3);
			} else if (stat == Stat.SPROUTS_3) {
				ItemStack sprouts = new ItemStack(TofuItems.SALT.get(), 1);
				if (worldIn instanceof Level) {
					float f = 0.7F;
					double d0 = (worldIn.getRandom().nextFloat() * f) + (1.0F - f) * 0.5D;
					double d1 = (worldIn.getRandom().nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
					double d2 = (worldIn.getRandom().nextFloat() * f) + (1.0F - f) * 0.5D;
					ItemEntity itemEntity = new ItemEntity((Level) worldIn, currentPos.getX() + d0, currentPos.getY() + d1, currentPos.getZ() + d2, sprouts);
					itemEntity.setPickUpDelay(10);
					worldIn.addFreshEntity(itemEntity);
				}
				worldIn.setBlock(currentPos, stateIn.setValue(STAT, SproutsJarBlock.Stat.WATER), 3);
			}
		}
		return facing.getAxis().isHorizontal() ? stateIn.setValue(NORTH, Boolean.valueOf(canConnectTo(worldIn, currentPos.north())))
				.setValue(EAST, Boolean.valueOf(canConnectTo(worldIn, currentPos.east())))
				.setValue(SOUTH, Boolean.valueOf(canConnectTo(worldIn, currentPos.south())))
				.setValue(WEST, Boolean.valueOf(canConnectTo(worldIn, currentPos.west()))) : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return (blockstate.getBlock() == this) ? context.getLevel().getBlockState(context.getClickedPos()).setValue(NORTH, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().north())))
				.setValue(EAST, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().east())))
				.setValue(SOUTH, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().south())))
				.setValue(WEST, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().west()))) : super.getStateForPlacement(context);
	}


	public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
		return p_196260_2_.getBlockState(p_196260_3_.below()).getMaterial().isSolid();
	}

	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack itemHeld = player.getItemInHand(handIn);
		SproutsJarBlock.Stat stat = getStat(state);
		if (!((Boolean) state.getValue((Property) WATERLOGGED)).booleanValue()) {
			if (stat == Stat.EMPTY && itemHeld != null && itemHeld.getItem() == Items.WATER_BUCKET) {
				if (!player.isCreative())
					player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
				worldIn.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				TileScanner tileScanner = new TileScanner(worldIn, pos);
				tileScanner.scan(1, TileScanner.Method.fullSimply, new TileScanner.Impl<Object>() {
					public void apply(Level world, BlockPos pos) {
						if (SproutsJarBlock.this.getStat(world.getBlockState(pos)) == SproutsJarBlock.Stat.EMPTY)
							world.setBlock(pos, TofuBlocks.SPROUTSJAR.get().defaultBlockState().setValue(SproutsJarBlock.STAT, SproutsJarBlock.Stat.WATER), 3);
					}
				});
				worldIn.setBlock(pos, state.setValue(STAT, SproutsJarBlock.Stat.WATER), 3);
				return InteractionResult.SUCCESS;
			}
			if (stat == Stat.WATER && itemHeld != null && itemHeld.getItem() == Items.BUCKET) {
				ItemStack water = new ItemStack(Items.WATER_BUCKET);
				worldIn.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (itemHeld.getCount() == 1) {
					player.setItemInHand(handIn, water);
				} else {
					if (player.getInventory().add(water))
						worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, water));
					itemHeld.shrink(1);
				}
				worldIn.setBlock(pos, state.setValue(STAT, SproutsJarBlock.Stat.EMPTY), 3);
				return InteractionResult.SUCCESS;
			}
			if (stat == Stat.WATER && itemHeld != null && itemHeld.getItem() == TofuItems.SEEDS_SOYBEANS.get()) {
				if (!player.isCreative())
					itemHeld.shrink(1);
				worldIn.playSound(null, pos, SoundEvents.CORAL_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
				worldIn.setBlock(pos, state.setValue(STAT, Stat.SPROUTS_0), 3);
				return InteractionResult.SUCCESS;
			}
			if (stat == Stat.SPROUTS_3) {
				ItemStack salt = new ItemStack(TofuItems.SPROUTS.get(), 1);
				float f = 0.7F;
				double d0 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				double d1 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
				double d2 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, salt);
				itemEntity.setPickUpDelay(10);
				worldIn.addFreshEntity(itemEntity);
				worldIn.setBlock(pos, state.setValue(STAT, SproutsJarBlock.Stat.WATER), 3);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(worldIn, pos))
			worldIn.destroyBlock(pos, true);
		SproutsJarBlock.Stat stat = getStat(state);
		int l = stat.getMeta();
		if (stat == Stat.SPROUTS_0) {
			if (canGrowing(worldIn, pos))
				worldIn.setBlock(pos, state.setValue(STAT, Stat.SPROUTS_1), 1);
		}
		if (stat == Stat.SPROUTS_1) {
			if (canGrowing(worldIn, pos))
				worldIn.setBlock(pos, state.setValue(STAT, Stat.SPROUTS_2), 1);
		}
		if (stat == Stat.SPROUTS_2) {
			if (canGrowing(worldIn, pos))
				worldIn.setBlock(pos, state.setValue(STAT, Stat.SPROUTS_3), 1);
		}
	}

	public SproutsJarBlock.Stat getStat(BlockState meta) {
		if (meta.getBlock() == this)
			return (SproutsJarBlock.Stat) meta.getValue((Property) STAT);
		return SproutsJarBlock.Stat.NA;
	}

	public boolean canConnectTo(LevelAccessor worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		return block instanceof SproutsJarBlock;
	}

	public boolean canGrowing(Level world, BlockPos pos) {
		return world.getRawBrightness(pos, 0) < 6;
	}

	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
		return SPROUTSJAR_AABB;
	}

	@Override
	public RenderShape getRenderShape(BlockState p_60550_) {
		return RenderShape.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAT, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState p_204507_1_) {
		return ((Boolean) p_204507_1_.getValue((Property) WATERLOGGED)).booleanValue() ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
	}

	public enum Stat implements StringRepresentable {
		EMPTY(0, "empty"),
		WATER(1, "water"),
		SPROUTS_0(2, "sprouts_0"),
		SPROUTS_1(3, "sprouts_1"),
		SPROUTS_2(4, "sprouts_2"),
		SPROUTS_3(5, "sprouts_3"),
		NA(6, "na");

		private static final SproutsJarBlock.Stat[] META_LOOKUP = new SproutsJarBlock.Stat[(values()).length];

		private final int meta;

		private final String name;

		static {
			for (SproutsJarBlock.Stat enumtype : values())
				META_LOOKUP[enumtype.getMeta()] = enumtype;
		}

		Stat(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		public int getMeta() {
			return this.meta;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
