package baguchi.tofucraft.api.event;

import baguchi.tofucraft.api.TofunianProfession;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.neoforged.bus.api.Event;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

public class TofunianTradeEvent extends Event {
	protected Int2ObjectMap<List<VillagerTrades.ItemListing>> trades;
	protected TofunianProfession type;
	private final HolderLookup.Provider registries;

	@ApiStatus.Internal
	public TofunianTradeEvent(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades, TofunianProfession type, HolderLookup.Provider registries) {
		this.trades = trades;
		this.type = type;
		this.registries = registries;
	}

	public Int2ObjectMap<List<VillagerTrades.ItemListing>> getTrades() {
		return trades;
	}

	public TofunianProfession getType() {
		return type;
	}

	public HolderLookup.Provider getRegistries() {
		return registries;
	}
}
