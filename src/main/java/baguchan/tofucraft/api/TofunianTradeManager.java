package baguchan.tofucraft.api;

import baguchan.tofucraft.api.event.TofunianTradeEvent;
import baguchan.tofucraft.api.event.TravelerTofunianTradesEvent;
import baguchan.tofucraft.registry.TofunianProfessions;
import baguchan.tofucraft.registry.TofunianTrades;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TofunianTradeManager {
	public static void loadTrades(TagsUpdatedEvent e) {
		if (e.getUpdateCause() == TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
			postWandererEvent(e.getRegistryAccess());
			postTofunianEvents(e.getRegistryAccess());
		}
	}

	private static void postWandererEvent(HolderLookup.Provider registries) {
		List<VillagerTrades.ItemListing> generic = NonNullList.create();
		List<VillagerTrades.ItemListing> rare = NonNullList.create();
		Arrays.stream(TofunianTrades.TRAVELER_TOFUNIAN_TRADE.get(1)).forEach(generic::add);
		Arrays.stream(TofunianTrades.TRAVELER_TOFUNIAN_TRADE.get(2)).forEach(rare::add);
		NeoForge.EVENT_BUS.post(new TravelerTofunianTradesEvent(generic, rare, registries));
		TofunianTrades.TRAVELER_TOFUNIAN_TRADE.put(1, generic.toArray(new VillagerTrades.ItemListing[0]));
		TofunianTrades.TRAVELER_TOFUNIAN_TRADE.put(2, rare.toArray(new VillagerTrades.ItemListing[0]));
	}

	private static void postTofunianEvents(HolderLookup.Provider registries) {
		for (Map.Entry<ResourceKey<TofunianProfession>, TofunianProfession> prof : TofunianProfessions.getRegistry().entrySet()) {
			Int2ObjectMap<VillagerTrades.ItemListing[]> trades = TofunianTrades.TOFUNIAN_TRADE.getOrDefault(prof.getKey(), new Int2ObjectOpenHashMap<>());
			Int2ObjectMap<List<VillagerTrades.ItemListing>> mutableTrades = new Int2ObjectOpenHashMap<>();
			for (int i = 1; i < 6; i++) {
				mutableTrades.put(i, NonNullList.create());
			}
			trades.int2ObjectEntrySet().forEach(e -> {
				Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
			});
			NeoForge.EVENT_BUS.post(new TofunianTradeEvent(mutableTrades, prof.getKey(), registries));
			Int2ObjectMap<VillagerTrades.ItemListing[]> newTrades = new Int2ObjectOpenHashMap<>();
			mutableTrades.int2ObjectEntrySet().forEach(e -> newTrades.put(e.getIntKey(), e.getValue().toArray(new VillagerTrades.ItemListing[0])));
			TofunianTrades.TOFUNIAN_TRADE.put(prof.getKey(), newTrades);
		}
	}
}
