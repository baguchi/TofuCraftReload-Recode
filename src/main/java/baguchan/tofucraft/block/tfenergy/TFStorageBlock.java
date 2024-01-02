package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import baguchan.tofucraft.message.TFStorageSoymilkMessage;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class TFStorageBlock extends BaseEntityBlock {
	public static final MapCodec<TFStorageBlock> CODEC = simpleCodec(TFStorageBlock::new);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;


	public TFStorageBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
		return this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState p_48722_, Rotation p_48723_) {
		return p_48722_.setValue(FACING, p_48723_.rotate(p_48722_.getValue(FACING)));
	}

	public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
		return p_48719_.rotate(p_48720_.getRotation(p_48719_.getValue(FACING)));
	}

	public InteractionResult use(BlockState p_48706_, Level p_48707_, BlockPos p_48708_, Player p_48709_, InteractionHand p_48710_, BlockHitResult p_48711_) {

		boolean flag = false;
		ItemStack stack = p_48709_.getItemInHand(p_48710_);
		BlockEntity blockentity = p_48707_.getBlockEntity(p_48708_);
		if (blockentity instanceof TFStorageBlockEntity) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(stack, 1)).orElse(null);
			if (handler != null && handler instanceof FluidBucketWrapper) {
				FluidUtil.interactWithFluidHandler(p_48709_, p_48710_, p_48707_, p_48708_, null);
				flag = true;
			}

			if (flag) {
				if (!p_48707_.isClientSide) {
					PacketDistributor.ALL.noArg().send(new TFStorageSoymilkMessage(p_48708_, ((TFStorageBlockEntity) blockentity).getTank().getFluid()));
				}
			}
		}


		if (!flag) {
			if (p_48707_.isClientSide) {
				TofuCraftReload.PROXY.setRefrencedTE(p_48707_.getBlockEntity(p_48708_));
				return InteractionResult.SUCCESS;
			} else {
				this.openContainer(p_48707_, p_48708_, p_48709_);
				return InteractionResult.CONSUME;
			}
		} else {
			return InteractionResult.SUCCESS;
		}
	}

	protected void openContainer(Level p_53631_, BlockPos p_53632_, Player p_53633_) {
		BlockEntity blockentity = p_53631_.getBlockEntity(p_53632_);
		if (blockentity instanceof TFStorageBlockEntity) {
			p_53633_.openMenu((MenuProvider) blockentity);
		}

	}

	public void setPlacedBy(Level p_48694_, BlockPos p_48695_, BlockState p_48696_, LivingEntity p_48697_, ItemStack p_48698_) {
		if (p_48698_.hasCustomHoverName()) {
			BlockEntity blockentity = p_48694_.getBlockEntity(p_48695_);
			if (blockentity instanceof TFStorageBlockEntity) {
				//((TFStorageBlockEntity) blockentity).setCustomName(p_48698_.getHoverName());
			}
		}

	}

	public void onRemove(BlockState p_48713_, Level p_48714_, BlockPos p_48715_, BlockState p_48716_, boolean p_48717_) {
		if (!p_48713_.is(p_48716_.getBlock())) {
			BlockEntity blockentity = p_48714_.getBlockEntity(p_48715_);
			if (blockentity instanceof TFStorageBlockEntity) {
				if (p_48714_ instanceof ServerLevel) {
					Containers.dropContents(p_48714_, p_48715_, (TFStorageBlockEntity) blockentity);
					//((TFStorageBlockEntity) blockentity).getRecipesToAwardAndPopExperience((ServerLevel) p_48714_, Vec3.atCenterOf(p_48715_));
				}

				p_48714_.updateNeighbourForOutputSignal(p_48715_, this);
			}

			super.onRemove(p_48713_, p_48714_, p_48715_, p_48716_, p_48717_);
		}
	}

	public boolean hasAnalogOutputSignal(BlockState p_48700_) {
		return true;
	}

	public int getAnalogOutputSignal(BlockState p_48702_, Level p_48703_, BlockPos p_48704_) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_48703_.getBlockEntity(p_48704_));
	}

	public RenderShape getRenderShape(BlockState p_48727_) {
		return RenderShape.MODEL;
	}


	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
		p_48725_.add(FACING, LIT);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new TFStorageBlockEntity(p_153215_, p_153216_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152382_, BlockState p_152383_, BlockEntityType<T> p_152384_) {
		return createTicker(p_152382_, p_152384_, TofuBlockEntitys.TF_STORAGE.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends TFStorageBlockEntity> p_151990_) {
		return createTickerHelper(p_151989_, p_151990_, TFStorageBlockEntity::tick);
	}
}