package baguchi.tofucraft.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;

public abstract class ItemBasedInteractable {
	protected final SynchedEntityData entityData;
	protected final EntityDataAccessor<Integer> healTimeAccessor;
	public boolean on = false;


	public ItemBasedInteractable(SynchedEntityData entityData, EntityDataAccessor<Integer> healTimeAccessor) {
		this.entityData = entityData;
		this.healTimeAccessor = healTimeAccessor;
	}

	public void onSynced() {
		this.on = true;
	}

	public abstract boolean active(RandomSource p_217033_);

	public void deActive() {
		this.on = false;
	}
}
