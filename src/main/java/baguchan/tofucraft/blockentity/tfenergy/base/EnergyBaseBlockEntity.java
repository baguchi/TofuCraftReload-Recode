package baguchan.tofucraft.blockentity.tfenergy.base;

import baguchan.tofucraft.api.tfenergy.ITofuEnergy;
import baguchan.tofucraft.api.tfenergy.TofuNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.UUID;

public class EnergyBaseBlockEntity extends BlockEntity implements ITofuEnergy {
	public static final String TAG_ENERGY = "tf_energy";
	public static final String TAG_ENERGY_MAX = "tf_energy_max";
	public static final String TAG_UUID = "tf_uuid";

	protected String uuid = "";

	protected int energy;
	protected int energyMax;

	public EnergyBaseBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_, int energyMax) {
		super(p_155228_, p_155229_, p_155230_);
		this.energyMax = energyMax;
	}

	//A public interface to access how much energy does a tile have
	@Override
	public int getEnergyStored() {
		return energy;
	}

	//Then how much it can store
	@Override
	public int getMaxEnergyStored() {
		return energyMax;
	}

	/*
	 * It's used when you want to fill a certain amount of tf energy into the tile
	 * To receive means how much energy will be filled this time, and if simulate is set to true, the code will just
	 * simulate all the process.
	 * Returns how much energy is filled.*/
	@Override
	public int receive(int toReceive, boolean simulate) {
		if (getEnergyStored() > getMaxEnergyStored()) return 0;
		int calculated = Math.min(toReceive, getMaxEnergyStored() - getEnergyStored());
		if (!simulate) energy += calculated;
		return calculated;
	}

	/*
	 * Drains energy from the tile. Like what its counterpart above does.
	 * */
	@Override
	public int drain(int toDrain, boolean simulate) {
		int calculated = Math.min(toDrain, getEnergyStored());
		if (!simulate) energy -= calculated;
		return calculated;
	}

	/*
	 * Check if the tile can receive/drain energy from the give tile
	 * */
	@Override
	public boolean canReceive(BlockEntity from) {
		if (!(from instanceof ITofuEnergy)) throw new IllegalArgumentException("It must be a instance of ITofuEnergy!");
		return true;
	}

	@Override
	public boolean canDrain(BlockEntity to) {
		if (!(to instanceof ITofuEnergy)) throw new IllegalArgumentException("It must be a instance of ITofuEnergy!");
		return true;
	}


	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		compound.putInt(TAG_ENERGY, energy);
		compound.putString(TAG_UUID, uuid);
		compound.putInt(TAG_ENERGY_MAX, energyMax);
	}

	public void load(CompoundTag compound) {
		super.load(compound);
		this.energyMax = compound.getInt(TAG_ENERGY_MAX);
		this.energy = compound.getInt(TAG_ENERGY);
		this.uuid = compound.getString(TAG_UUID);
	}


	@Override
	public void onLoad() {
		super.onLoad();
		if (!this.getLevel().isClientSide()) {
			if (uuid.isEmpty()) uuid = UUID.randomUUID().toString();
			TofuNetwork.Instance.register(uuid, this, false);
		}
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		if (!this.getLevel().isClientSide()) {
			TofuNetwork.Instance.unload(uuid, false);
		}
	}

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return saveWithoutMetadata();
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		load(pkt.getTag());
	}

	protected void inventoryChanged() {
		super.setChanged();
		if (level != null) {
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
		}
	}
}
