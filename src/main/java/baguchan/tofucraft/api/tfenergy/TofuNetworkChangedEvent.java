package baguchan.tofucraft.api.tfenergy;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.Event;

public class TofuNetworkChangedEvent extends Event {

	private String uuid;
	private BlockEntity te;
	private boolean isSystem; //This tells the event if the post is caused by the game mechanism itself.

	public String getUUID() {
		return uuid;
	}

	public BlockEntity getTE() {
		return te;
	}

	public boolean isSystem() {
		return isSystem;
	}

	public TofuNetworkChangedEvent(String uuid, BlockEntity te, boolean isSystem) {
		this.uuid = uuid;
		this.te = te;
		this.isSystem = isSystem;
	}

	public static class NetworkLoaded extends TofuNetworkChangedEvent {

		public NetworkLoaded(String uuid, BlockEntity te, boolean isSystem) {
			super(uuid, te, isSystem);
		}
	}

	public static class NetworkRemoved extends TofuNetworkChangedEvent {

		public NetworkRemoved(String uuid, BlockEntity te, boolean isSystem) {
			super(uuid, te, isSystem);
		}
	}

	public static class NetworkCleared extends TofuNetworkChangedEvent {

		public NetworkCleared(boolean isSystem) {
			super(null, null, isSystem);
		}
	}
}