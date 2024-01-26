package baguchan.tofucraft.blockentity.tfenergy.base;

import baguchan.tofucraft.api.tfenergy.IAnntena;
import baguchan.tofucraft.api.tfenergy.ITofuEnergy;
import baguchan.tofucraft.api.tfenergy.TofuNetwork;
import baguchan.tofucraft.api.tfenergy.TofuNetworkChangedEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class SenderBaseBlockEntity extends EnergyBaseBlockEntity {

	/*
	 * Comment:
	 * This is the base of a TESTE which will send energy 'through' the antenna.
	 * They are not able to receive energy by default, but there are some exceptions, for example the battery box.
	 * */

	protected List<BlockEntity> cache = new ArrayList<>();
	protected boolean isCached = false;

	protected int findCooldown;

	private Block antenna;

	public SenderBaseBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int energyMax) {
		super(p_155228_, p_155229_, p_155230_, energyMax);
	}

	//Notify all the registered BlockEntitySenderBase instance on network change
	@SubscribeEvent
	public static void onLoaded(TofuNetworkChangedEvent.NetworkLoaded event) {
		List<BlockEntity> tes = TofuNetwork.toTiles(
				TofuNetwork.Instance.getReference()
						.entrySet()
						.stream()
						.filter(entry -> entry.getValue() instanceof SenderBaseBlockEntity &&
								((SenderBaseBlockEntity) entry.getValue()).isValid()));
		tes.forEach(te -> ((SenderBaseBlockEntity) te).onCache());
	}

	@SubscribeEvent
	public static void onRemoved(TofuNetworkChangedEvent.NetworkRemoved event) {
		List<BlockEntity> tes = TofuNetwork.toTiles(
				TofuNetwork.Instance.getReference()
						.entrySet()
						.stream()
						.filter(entry -> entry.getValue() instanceof SenderBaseBlockEntity &&
								((SenderBaseBlockEntity) entry.getValue()).isValid()));
		tes.forEach(te -> ((SenderBaseBlockEntity) te).onCache());
	}

	public static void senderUpdate(SenderBaseBlockEntity senderBaseBlockEntity) {
		if (!senderBaseBlockEntity.level.isClientSide() && senderBaseBlockEntity.getEnergyStored() > 0) {
			if (senderBaseBlockEntity.isValid()) {
				if (!senderBaseBlockEntity.isCached) senderBaseBlockEntity.onCache();
				if (senderBaseBlockEntity.cache.size() > 0) {
					List<BlockEntity> toSend = new ArrayList<>();

					senderBaseBlockEntity.cache.forEach(tileEntity -> {
						if (((EnergyBaseBlockEntity) tileEntity).getEnergyStored() < ((EnergyBaseBlockEntity) tileEntity).getMaxEnergyStored())
							toSend.add(tileEntity);
					});
					if (toSend.size() > 0) {
						int packSize = Math.max(Math.min(senderBaseBlockEntity.getTransferPower(),
								senderBaseBlockEntity.getEnergyStored()) / toSend.size(), 1);
						for (BlockEntity te : toSend) {
							senderBaseBlockEntity.drain(((ITofuEnergy) te).receive(Math.min(packSize, senderBaseBlockEntity.getEnergyStored()), false), false);
						}
					}

				}
			} else {
				senderBaseBlockEntity.cache.clear();
				senderBaseBlockEntity.isCached = false;
			}
		}
	}

	public boolean isValid() {
		if (level.isLoaded(getBlockPos().above()))
			antenna = level.getBlockState(getBlockPos().above()).getBlock();
		return antenna instanceof IAnntena;
	}

	//The onCache decides what TileEntities will be cached into the function and be send energy to.
	public void onCache() {
		cache = TofuNetwork.toTiles(TofuNetwork.Instance.getInsertableWithinRadius(this, ((IAnntena) antenna).getRadius(getBlockPos().above(), level)));
		isCached = true;
		findCooldown = 100;
	}

	public int getTransferPower() {
		return isValid() ? ((IAnntena) level.getBlockState(getBlockPos().above()).getBlock()).getPower(getBlockPos().above(), level) : 0;
	}

	public double getRadius() {
		return ((IAnntena) antenna).getRadius(getBlockPos(), level);
	}

	@Override
	public boolean canDrain(BlockEntity to) {
		return true;
	}

	@Override
	public boolean canReceive(BlockEntity from) {
		return false;
	}
}