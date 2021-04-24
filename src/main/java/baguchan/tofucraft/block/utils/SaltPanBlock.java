package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.utils.TileScanner;
import net.minecraft.block.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class SaltPanBlock extends Block implements IWaterLoggable {
	public static VoxelShape SALT_PAN_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

	public static final EnumProperty<Stat> STAT = EnumProperty.create("stat", Stat.class);

	public static final BooleanProperty NORTH = SixWayBlock.NORTH;

	public static final BooleanProperty EAST = SixWayBlock.EAST;

	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;

	public static final BooleanProperty WEST = SixWayBlock.WEST;

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public SaltPanBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.canSurvive(worldIn, currentPos))
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
		if (((Boolean) stateIn.getValue((Property) WATERLOGGED)).booleanValue()) {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			Stat stat = getStat(stateIn);
			if (stat == Stat.EMPTY || stat == Stat.BITTERN) {
				worldIn.setBlock(currentPos, stateIn.setValue(STAT, Stat.WATER), 3);
			} else if (stat == Stat.SALT) {
				ItemStack salt = new ItemStack(TofuItems.SALT, 1);
				if (worldIn instanceof World) {
					float f = 0.7F;
					double d0 = (worldIn.getRandom().nextFloat() * f) + (1.0F - f) * 0.5D;
					double d1 = (worldIn.getRandom().nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
					double d2 = (worldIn.getRandom().nextFloat() * f) + (1.0F - f) * 0.5D;
					ItemEntity itemEntity = new ItemEntity((World) worldIn, currentPos.getX() + d0, currentPos.getY() + d1, currentPos.getZ() + d2, salt);
					itemEntity.setPickUpDelay(10);
					worldIn.addFreshEntity(itemEntity);
				}
				worldIn.setBlock(currentPos, stateIn.setValue(STAT, Stat.WATER), 3);
			}
		}
		return facing.getAxis().isHorizontal() ? stateIn.setValue(NORTH, Boolean.valueOf(canConnectTo(worldIn, currentPos.north())))
				.setValue(EAST, Boolean.valueOf(canConnectTo(worldIn, currentPos.east())))
				.setValue(SOUTH, Boolean.valueOf(canConnectTo(worldIn, currentPos.south())))
				.setValue(WEST, Boolean.valueOf(canConnectTo(worldIn, currentPos.west()))) : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return (blockstate.getBlock() == this) ? context.getLevel().getBlockState(context.getClickedPos()).setValue(NORTH, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().north())))
				.setValue(EAST, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().east())))
				.setValue(SOUTH, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().south())))
				.setValue(WEST, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().west()))) : super.getStateForPlacement(context);
	}


	public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
		return p_196260_2_.getBlockState(p_196260_3_.below()).getMaterial().isSolid();
	}

	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isClientSide)
			return ActionResultType.SUCCESS;
		ItemStack itemHeld = player.getItemInHand(handIn);
		Stat stat = getStat(state);
		if (!((Boolean) state.getValue((Property) WATERLOGGED)).booleanValue()) {
			if (stat == Stat.EMPTY && itemHeld != null && itemHeld.getItem() == Items.WATER_BUCKET) {
				if (!player.isCreative())
					player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
				worldIn.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				TileScanner tileScanner = new TileScanner(worldIn, pos);
				tileScanner.scan(1, TileScanner.Method.fullSimply, new TileScanner.Impl<Object>() {
					public void apply(World world, BlockPos pos) {
						if (SaltPanBlock.this.getStat(world.getBlockState(pos)) == Stat.EMPTY)
							world.setBlock(pos, TofuBlocks.SALTPAN.defaultBlockState().setValue(SaltPanBlock.STAT, Stat.WATER), 3);
					}
				});
				worldIn.setBlock(pos, state.setValue(STAT, Stat.WATER), 3);
				return ActionResultType.SUCCESS;
			}
			if (stat == Stat.BITTERN && itemHeld != null && itemHeld.getItem() == Items.GLASS_BOTTLE) {
				ItemStack nigari = new ItemStack(TofuItems.BITTERN);
				worldIn.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (itemHeld.getCount() == 1) {
					player.setItemInHand(handIn, nigari);
				} else {
					if (!player.inventory.add(nigari))
						worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, nigari));
					itemHeld.shrink(1);
				}
				worldIn.setBlock(pos, state.setValue(STAT, Stat.EMPTY), 3);
				return ActionResultType.SUCCESS;
			}
			if (stat == Stat.BITTERN && itemHeld == null) {
				worldIn.setBlock(pos, state.setValue(STAT, Stat.EMPTY), 3);
				return ActionResultType.SUCCESS;
			}
			if (stat == Stat.SALT) {
				ItemStack salt = new ItemStack(TofuItems.SALT, 1);
				float f = 0.7F;
				double d0 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				double d1 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
				double d2 = (worldIn.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, salt);
				itemEntity.setPickUpDelay(10);
				worldIn.addFreshEntity(itemEntity);
				worldIn.setBlock(pos, state.setValue(STAT, Stat.BITTERN), 3);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.PASS;
	}

	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!state.canSurvive(worldIn, pos))
			worldIn.destroyBlock(pos, true);
		Stat stat = getStat(state);
		int l = stat.getMeta();
		if (stat == Stat.WATER && !((Boolean) state.getValue((Property) WATERLOGGED)).booleanValue()) {
			float f = calcAdaptation(worldIn, pos);
			if (f > 0.0F && random.nextInt((int) (25.0F / f) + 1) == 0) {
				l++;
				worldIn.setBlock(pos, state.setValue(STAT, Stat.SALT), 2);
			}
		}
	}

	public Stat getStat(BlockState meta) {
		if (meta.getBlock() == this)
			return (Stat) meta.getValue((Property) STAT);
		return Stat.NA;
	}

	public boolean canConnectTo(IWorld worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		return block instanceof SaltPanBlock;
	}

	private float calcAdaptation(World world, BlockPos pos) {
		float rate;
		Biome biome = world.getBiome(pos);
		boolean isUnderTheSun = world.canSeeSky(pos);
		boolean isRaining = world.isRaining();
		boolean isDaytime = (world.dayTime() % 24000L < 12000L);
		float humidity = biome.getDownfall();
		float temperature = biome.getTemperature(pos);
		if (!isUnderTheSun || isRaining) {
			rate = 0.0F;
		} else {
			rate = isDaytime ? 2.0F : 1.0F;
			rate = (float) (rate * ((humidity < 0.2D) ? 4.0D : ((humidity < 0.7D) ? 2.0D : ((humidity < 0.9D) ? 1.0D : 0.5D))));
			rate = (float) (rate * ((temperature < 0.0D) ? 1.0D : ((temperature < 0.6D) ? 1.5D : ((temperature < 1.0D) ? 2.0D : 4.0D))));
		}
		return rate;
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SALT_PAN_AABB;
	}

	@Override
	public BlockRenderType getRenderShape(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAT, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState p_204507_1_) {
		return ((Boolean) p_204507_1_.getValue((Property) WATERLOGGED)).booleanValue() ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
	}

	public enum Stat implements IStringSerializable {
		EMPTY(0, "empty"),
		WATER(1, "water"),
		SALT(2, "salt"),
		BITTERN(3, "bittern"),
		NA(4, "na");

		private static final Stat[] META_LOOKUP = new Stat[(values()).length];

		private final int meta;

		private final String name;

		static {
			for (Stat enumtype : values())
				META_LOOKUP[enumtype.getMeta()] = enumtype;
		}

		Stat(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		public static Stat byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length)
				meta = 0;
			return META_LOOKUP[meta];
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
