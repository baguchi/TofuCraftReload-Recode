package baguchan.tofucraft.api.tfenergy;

import net.minecraft.world.level.block.entity.BlockEntity;

public interface ITofuEnergy {


	int getEnergyStored();

	int getMaxEnergyStored();

	int receive(int energy, boolean simulate);

	int drain(int energy, boolean simulate);

	//Can the machine gets energy
	boolean canReceive(BlockEntity from);

	//Can the machine gives out energy
	boolean canDrain(BlockEntity to);

	default boolean isEnergyFull() {
		return getEnergyStored() == getMaxEnergyStored();
	}

	default boolean isEnergyEmpty() {
		return getEnergyStored() == 0;
	}
}