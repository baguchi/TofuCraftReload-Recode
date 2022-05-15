package baguchan.tofucraft.block.tfenergy;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import baguchan.tofucraft.message.TFStorageSoymilkMessage;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.Random;

public class TFStorageBlock extends BaseEntityBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;


	public TFStorageBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
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
			if (handler != null && handler instanceof net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper) {
				FluidUtil.interactWithFluidHandler(p_48709_, p_48710_, blockentity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null));
				flag = true;
			}

			if (flag) {
				TofuCraftReload.CHANNEL.send(PacketDistributor.ALL.noArg(), new TFStorageSoymilkMessage(p_48708_, ((TFStorageBlockEntity) blockentity).getTank().getFluid()));
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

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState p_180655_1_, Level p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
		if ((Boolean) p_180655_1_.getValue(LIT)) {
			double d0 = p_180655_3_.getX() + 0.5D;
			double d1 = p_180655_3_.getY() + 0.1D;
			double d2 = p_180655_3_.getZ() + 0.5D;
			if (p_180655_4_.nextDouble() < 0.1D)
				p_180655_2_.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
			for (Direction direction : Direction.values()) {
				Direction.Axis direction$axis = direction.getAxis();
				double d3 = 0.52D;
				double d4 = p_180655_4_.nextDouble() * 0.6D - 0.3D;
				double d5 = (direction$axis == Direction.Axis.X) ? (direction.getStepX() * d3) : d4;
				double d6 = p_180655_4_.nextDouble() * 6.0D / 16.0D;
				double d7 = (direction$axis == Direction.Axis.Z) ? (direction.getStepZ() * d3) : d4;
				p_180655_2_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
				p_180655_2_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			}
		}
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