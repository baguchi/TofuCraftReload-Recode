package baguchan.tofucraft.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SoyHealthStorage<T extends SoyHealthCapability> implements Capability.IStorage<T> {
	public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
		return instance.serializeNBT();
	}

	public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
		instance.deserializeNBT((CompoundNBT) nbt);
	}
}
