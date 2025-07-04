package baguchi.tofucraft.blockentity;

import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class TofuChestBlockEntity extends ChestBlockEntity {
	private final ContainerOpenersCounter openersCounter;

	protected TofuChestBlockEntity(BlockEntityType<?> p_155327_, BlockPos p_155328_, BlockState p_155329_) {
		super(p_155327_, p_155328_, p_155329_);
		this.openersCounter = new ContainerOpenersCounter() {
			protected void onOpen(Level p_155357_, BlockPos p_155358_, BlockState p_155359_) {
				TofuChestBlockEntity.playSound(p_155357_, p_155358_, p_155359_, TofuSounds.TOFU_CHEST_OPEN.get());
			}

			protected void onClose(Level p_155367_, BlockPos p_155368_, BlockState p_155369_) {
				TofuChestBlockEntity.playSound(p_155367_, p_155368_, p_155369_, TofuSounds.TOFU_CHEST_CLOSE.get());
			}

			protected void openerCountChanged(Level p_155361_, BlockPos p_155362_, BlockState p_155363_, int p_155364_, int p_155365_) {
				TofuChestBlockEntity.this.signalOpenCount(p_155361_, p_155362_, p_155363_, p_155364_, p_155365_);
			}

			protected boolean isOwnContainer(Player p_155355_) {
				if (!(p_155355_.containerMenu instanceof ChestMenu)) {
					return false;
				} else {
					Container container = ((ChestMenu) p_155355_.containerMenu).getContainer();
					return container == TofuChestBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer) container).contains(TofuChestBlockEntity.this);
				}
			}
		};
	}

	public TofuChestBlockEntity(BlockPos p_155331_, BlockState p_155332_) {
		this(TofuBlockEntitys.TOFUCHEST.get(), p_155331_, p_155332_);
	}

	static void playSound(Level p_155339_, BlockPos p_155340_, BlockState p_155341_, SoundEvent p_155342_) {
		ChestType chesttype = (ChestType) p_155341_.getValue(ChestBlock.TYPE);
		if (chesttype != ChestType.LEFT) {
			double d0 = (double) p_155340_.getX() + (double) 0.5F;
			double d1 = (double) p_155340_.getY() + (double) 0.5F;
			double d2 = (double) p_155340_.getZ() + (double) 0.5F;
			if (chesttype == ChestType.RIGHT) {
				Direction direction = ChestBlock.getConnectedDirection(p_155341_);
				d0 += (double) direction.getStepX() * (double) 0.5F;
				d2 += (double) direction.getStepZ() * (double) 0.5F;
			}

			p_155339_.playSound((Entity) null, d0, d1, d2, p_155342_, SoundSource.BLOCKS, 0.5F, p_155339_.random.nextFloat() * 0.1F + 0.9F);
		}
	}

	@Override
	public void startOpen(Player p_59120_) {
		if (!this.remove && !p_59120_.isSpectator()) {
			this.openersCounter.incrementOpeners(p_59120_, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}

	}

	@Override
	public void stopOpen(Player p_59118_) {
		if (!this.remove && !p_59118_.isSpectator()) {
			this.openersCounter.decrementOpeners(p_59118_, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}

	}

	@Override
	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}
}
