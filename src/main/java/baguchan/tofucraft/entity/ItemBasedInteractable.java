package baguchan.tofucraft.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;

public class ItemBasedInteractable {
	private static final int HEALING_TIME = 100;
	private final SynchedEntityData entityData;
	private final EntityDataAccessor<Integer> healTimeAccessor;
	public boolean on;
	public int healTime;
	public int healTimeTotal;

	public ItemBasedInteractable(SynchedEntityData entityData, EntityDataAccessor<Integer> healTimeAccessor) {
		this.entityData = entityData;
		this.healTimeAccessor = healTimeAccessor;
	}

	public void onSynced() {
		this.on = true;
		this.healTime = 0;
		this.healTimeTotal = this.entityData.get(this.healTimeAccessor);
	}

	public boolean active(RandomSource p_217033_) {
		if (this.on) {
			return false;
		} else {
			this.on = true;
			this.healTime = 0;
			this.healTimeTotal = p_217033_.nextInt(100) + 100;
			this.entityData.set(this.healTimeAccessor, this.healTimeTotal);
			return true;
		}
	}

	public void deActive() {
		this.on = false;
	}
}
