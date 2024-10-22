package baguchi.tofucraft.blockentity.tfenergy.base;

import baguchi.tofucraft.api.tfenergy.ITofuEnergy;
import baguchi.tofucraft.api.tfenergy.TFEnergyData;
import baguchi.tofucraft.api.tfenergy.TofuNetwork;
import baguchi.tofucraft.registry.TofuDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
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
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider p_338445_) {
		super.saveAdditional(compound, p_338445_);
		compound.putInt(TAG_ENERGY, energy);
		compound.putString(TAG_UUID, uuid);
		compound.putInt(TAG_ENERGY_MAX, energyMax);
	}

	@Override
	protected void loadAdditional(CompoundTag compound, HolderLookup.Provider p_338445_) {
		super.loadAdditional(compound, p_338445_);
		this.energyMax = compound.getInt(TAG_ENERGY_MAX);
		this.energy = compound.getInt(TAG_ENERGY);
		this.uuid = compound.getString(TAG_UUID);
	}

	@Override
	protected void applyImplicitComponents(BlockEntity.DataComponentInput p_338244_) {
		super.applyImplicitComponents(p_338244_);

		TFEnergyData tfEnergyData = p_338244_.getOrDefault(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(energy, energyMax));

		this.energy = tfEnergyData.storeTF();
		this.energyMax = tfEnergyData.maxTF();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder p_338540_) {
		super.collectImplicitComponents(p_338540_);
		p_338540_.set(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(energy, energyMax));
	}

	@Override
	public void removeComponentsFromTag(CompoundTag p_331127_) {
		super.removeComponentsFromTag(p_331127_);
		p_331127_.remove("tf_uuid");
		p_331127_.remove("tf_energy");
		p_331127_.remove("tf_energy_max");
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
	public void onChunkUnloaded() {
		super.onChunkUnloaded();
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
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
		super.onDataPacket(net, pkt, lookupProvider);
		loadAdditional(pkt.getTag(), lookupProvider);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider p_323910_) {
		return saveWithoutMetadata(p_323910_);
	}

	protected void inventoryChanged() {
		super.setChanged();
		if (level != null) {
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
		}
	}
}
