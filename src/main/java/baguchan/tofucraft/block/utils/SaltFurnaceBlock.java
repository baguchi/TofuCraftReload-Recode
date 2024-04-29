package baguchan.tofucraft.block.utils;

import baguchan.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchan.tofucraft.client.ClientProxy;
import baguchan.tofucraft.network.SaltFurnaceWaterPacket;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class SaltFurnaceBlock extends BaseEntityBlock {
	public static final MapCodec<SaltFurnaceBlock> CODEC = simpleCodec(SaltFurnaceBlock::new);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public SaltFurnaceBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack p_316304_, BlockState p_48706_, Level p_48707_, BlockPos p_48708_, Player p_48709_, InteractionHand p_48710_, BlockHitResult p_48711_) {

		boolean flag = false;
		ItemStack stack = p_48709_.getItemInHand(p_48710_);
		BlockEntity blockentity = p_48707_.getBlockEntity(p_48708_);
		if (blockentity instanceof SaltFurnaceBlockEntity) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack.copyWithCount(1)).orElse(null);
			if (handler instanceof FluidBucketWrapper) {
				FluidUtil.interactWithFluidHandler(p_48709_, p_48710_, p_48707_, p_48708_, null);
				flag = true;
			}

			if (flag) {
				if (!p_48707_.isClientSide) {
					PacketDistributor.sendToAllPlayers(new SaltFurnaceWaterPacket(p_48708_, ((SaltFurnaceBlockEntity) blockentity).waterTank.getFluid()));
				}
			}
		}


		if (!flag) {
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			return ItemInteractionResult.SUCCESS;
		}
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, BlockHitResult p_60508_) {
		if (p_60504_.isClientSide) {
			ClientProxy.PROXY.setRefrencedTE(p_60504_.getBlockEntity(p_60505_));
			return InteractionResult.SUCCESS;
		} else {
			this.openContainer(p_60504_, p_60505_, p_60506_);
			return InteractionResult.CONSUME;
		}
	}

	protected void openContainer(Level p_53631_, BlockPos p_53632_, Player p_53633_) {
		BlockEntity blockentity = p_53631_.getBlockEntity(p_53632_);
		if (blockentity instanceof MenuProvider saltFurnaceBlock) {
			p_53633_.openMenu(saltFurnaceBlock);
		}

	}

	@Override
	public void onRemove(BlockState p_48713_, Level p_48714_, BlockPos p_48715_, BlockState p_48716_, boolean p_48717_) {
		if (!p_48713_.is(p_48716_.getBlock())) {
			BlockEntity blockentity = p_48714_.getBlockEntity(p_48715_);
			if (blockentity instanceof SaltFurnaceBlockEntity) {
				if (p_48714_ instanceof ServerLevel) {
					Containers.dropContents(p_48714_, p_48715_, (SaltFurnaceBlockEntity) blockentity);
					//((SaltFurnaceBlockEntity) blockentity).getRecipesToAwardAndPopExperience((ServerLevel) p_48714_, Vec3.atCenterOf(p_48715_));
				}

				p_48714_.updateNeighbourForOutputSignal(p_48715_, this);
			}

			super.onRemove(p_48713_, p_48714_, p_48715_, p_48716_, p_48717_);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_48700_) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState p_48702_, Level p_48703_, BlockPos p_48704_) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_48703_.getBlockEntity(p_48704_));
	}

	@Override
	public RenderShape getRenderShape(BlockState p_48727_) {
		return RenderShape.MODEL;
	}


	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
		p_48725_.add(LIT);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState p_180655_1_, Level p_180655_2_, BlockPos p_180655_3_, RandomSource p_180655_4_) {
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
				double d5 = (direction$axis == Direction.Axis.X) ? (direction.getStepX() * 0.52D) : d4;
				double d6 = p_180655_4_.nextDouble() * 6.0D / 16.0D;
				double d7 = (direction$axis == Direction.Axis.Z) ? (direction.getStepZ() * 0.52D) : d4;
				p_180655_2_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
				p_180655_2_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new SaltFurnaceBlockEntity(p_153215_, p_153216_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152382_, BlockState p_152383_, BlockEntityType<T> p_152384_) {
		return createFurnaceTicker(p_152382_, p_152384_, TofuBlockEntitys.SALT_FURNACE.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends SaltFurnaceBlockEntity> p_151990_) {
		return createTickerHelper(p_151989_, p_151990_, SaltFurnaceBlockEntity::tick);
	}
}