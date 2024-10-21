package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.utils.TileScanner;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
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
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;

import javax.annotation.Nullable;

public class SaltPanBlock extends Block implements SimpleWaterloggedBlock {
	public static VoxelShape SALT_PAN_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

	public static final EnumProperty<Stat> STAT = EnumProperty.create("stat", Stat.class);

	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;

	public static final BooleanProperty EAST = BlockStateProperties.EAST;

	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;

	public static final BooleanProperty WEST = BlockStateProperties.WEST;

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public SaltPanBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource p_222948_) {
		super.tick(state, level, pos, p_222948_);
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
		}
	}

	@Override
	protected BlockState updateShape(BlockState stateIn, LevelReader levelReader, ScheduledTickAccess access, BlockPos currentPos, Direction direction, BlockPos facingPos, BlockState facingState, RandomSource randomSource) {
		if (!stateIn.canSurvive(levelReader, currentPos) && !access.getBlockTicks().hasScheduledTick(currentPos, this)) {
			access.scheduleTick(currentPos, this, 1);
		}
		if (((Boolean) stateIn.getValue((Property) WATERLOGGED)).booleanValue()) {
			access.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
			Stat stat = getStat(stateIn);
			if (stat == Stat.EMPTY || stat == Stat.BITTERN) {
				return stateIn.setValue(STAT, Stat.WATER);
			} else if (stat == Stat.SALT) {
				return stateIn.setValue(STAT, Stat.WATER);
			}
		}
		return direction.getAxis().isHorizontal() ? stateIn.setValue(NORTH, Boolean.valueOf(canConnectTo(levelReader, currentPos.north())))
				.setValue(EAST, Boolean.valueOf(canConnectTo(levelReader, currentPos.east())))
				.setValue(SOUTH, Boolean.valueOf(canConnectTo(levelReader, currentPos.south())))
				.setValue(WEST, Boolean.valueOf(canConnectTo(levelReader, currentPos.west()))) : super.updateShape(stateIn, levelReader, access, currentPos, direction, facingPos, facingState, randomSource);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return (blockstate.getBlock() == this) ? context.getLevel().getBlockState(context.getClickedPos()).setValue(NORTH, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().north())))
				.setValue(EAST, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().east())))
				.setValue(SOUTH, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().south())))
				.setValue(WEST, Boolean.valueOf(canConnectTo(context.getLevel(), context.getClickedPos().west()))) : super.getStateForPlacement(context);
	}


	@Override
	public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
		return p_196260_2_.getBlockState(p_196260_3_.below()).isFaceSturdy(p_196260_2_, p_196260_3_, Direction.UP);
	}

	@Override
	protected InteractionResult useItemOn(ItemStack p_316304_, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack itemHeld = player.getItemInHand(handIn);
		Stat stat = getStat(state);
		if (!((Boolean) state.getValue((Property) WATERLOGGED)).booleanValue()) {
			if (stat == Stat.EMPTY && itemHeld != null) {
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(itemHeld.copyWithCount(1)).orElse(null);
				if (handler != null && handler instanceof FluidBucketWrapper && ((FluidBucketWrapper) handler).getFluid().getFluid() == Fluids.WATER) {

					if (!player.isCreative()) {
						handler.drain(1000, IFluidHandler.FluidAction.EXECUTE);
						player.setItemInHand(handIn, new ItemStack(handler.getContainer().getItem()));
					}
					level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
					TileScanner tileScanner = new TileScanner(level, pos);
					tileScanner.scan(1, TileScanner.Method.fullSimply, new TileScanner.Impl<Object>() {
						public void apply(Level world, BlockPos pos) {
							if (SaltPanBlock.this.getStat(world.getBlockState(pos)) == Stat.EMPTY)
								world.setBlock(pos, world.getBlockState(pos).setValue(SaltPanBlock.STAT, Stat.WATER), 3);
						}
					});
					level.setBlock(pos, state.setValue(STAT, Stat.WATER), 3);
					return InteractionResult.SUCCESS;
				}
				PotionContents potioncontents = itemHeld.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
				if (potioncontents.is(Potions.WATER)) {
					ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
					level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
					if (!player.isCreative()) {
						if (itemHeld.getCount() == 1) {
							player.setItemInHand(handIn, bottle);
						} else {
							if (!player.getInventory().add(bottle))
								level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, bottle));

							itemHeld.shrink(1);
						}
					}
					level.setBlock(pos, state.setValue(SaltPanBlock.STAT, Stat.WATER), 3);
					return InteractionResult.SUCCESS;
				}
			}
			if (stat == Stat.BITTERN && itemHeld != null && itemHeld.getItem() == Items.GLASS_BOTTLE) {
				ItemStack nigari = new ItemStack(TofuItems.BITTERN_BOTTLE.get());
				level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (itemHeld.getCount() == 1) {
					player.setItemInHand(handIn, nigari);
				} else {
					if (!player.getInventory().add(nigari))
						level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, nigari));
					itemHeld.shrink(1);
				}
				level.setBlock(pos, state.setValue(STAT, Stat.EMPTY), 3);
				return InteractionResult.SUCCESS;
			}
			if (stat == Stat.BITTERN && itemHeld == null) {
				level.setBlock(pos, state.setValue(STAT, Stat.EMPTY), 3);
				return InteractionResult.SUCCESS;
			}
			if (stat == Stat.SALT) {
				ItemStack salt = new ItemStack(TofuItems.SALT.get(), 2);
				float f = 0.7F;
				double d0 = (level.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				double d1 = (level.random.nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
				double d2 = (level.random.nextFloat() * f) + (1.0F - f) * 0.5D;
				ItemEntity itemEntity = new ItemEntity(level, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, salt);
				itemEntity.setPickUpDelay(10);
				level.addFreshEntity(itemEntity);
				level.setBlock(pos, state.setValue(STAT, Stat.BITTERN), 3);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.TRY_WITH_EMPTY_HAND;
	}


	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
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

	public boolean canConnectTo(LevelReader worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		return block instanceof SaltPanBlock;
	}

	private float calcAdaptation(Level world, BlockPos pos) {
		float rate;
		Biome biome = world.getBiome(pos).value();
		boolean isUnderTheSun = world.getBrightness(LightLayer.SKY, pos) > 10;
		boolean isRaining = world.isRaining();
		boolean isDaytime = (world.dayTime() % 24000L < 12000L);
		float humidity = biome.getModifiedClimateSettings().downfall();
		float temperature = biome.getBaseTemperature();
		if (!isUnderTheSun || isRaining) {
			rate = 0.0F;
		} else {
			rate = isDaytime ? 2.0F : 1.0F;
			rate = (float) (rate * ((humidity < 0.2D) ? 4.0D : ((humidity < 0.7D) ? 2.0D : ((humidity < 0.9D) ? 1.0D : 0.5D))));
			rate = (float) (rate * ((temperature < 0.0D) ? 1.0D : ((temperature < 0.6D) ? 1.5D : ((temperature < 1.0D) ? 2.0D : 4.0D))));
		}
		return rate;
	}

	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
		return SALT_PAN_AABB;
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
		public int getMeta() {
			return this.meta;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
