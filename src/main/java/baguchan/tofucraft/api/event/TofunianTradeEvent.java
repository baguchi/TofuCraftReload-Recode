package baguchan.tofucraft.api.event;

import baguchan.tofucraft.api.TofunianProfession;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.bus.api.Event;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

public class TofunianTradeEvent extends Event {
	protected Int2ObjectMap<List<VillagerTrades.ItemListing>> trades;
	protected ResourceKey<TofunianProfession> type;
	private final HolderLookup.Provider registries;

	@ApiStatus.Internal
	public TofunianTradeEvent(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades, ResourceKey<TofunianProfession> type, HolderLookup.Provider registries) {
		this.trades = trades;
		this.type = type;
		this.registries = registries;
	}

	public Int2ObjectMap<List<VillagerTrades.ItemListing>> getTrades() {
		return trades;
	}

	public ResourceKey<TofunianProfession> getType() {
		return type;
	}

	public HolderLookup.Provider getRegistries() {
		return registries;
	}
}
