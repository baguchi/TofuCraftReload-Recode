package baguchi.tofucraft.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;

public class HealInteractable extends ItemBasedInteractable {
	public int healTime;
	public int healTimeTotal;

	public HealInteractable(SynchedEntityData entityData, EntityDataAccessor<Integer> healTimeAccessor) {
		super(entityData, healTimeAccessor);
	}

	public void onSynced() {
		super.onSynced();
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
}
