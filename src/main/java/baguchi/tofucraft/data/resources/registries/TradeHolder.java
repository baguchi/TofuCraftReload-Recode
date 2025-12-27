package baguchi.tofucraft.data.resources.registries;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;

import java.util.Map;

public class TradeHolder {
	protected final Int2ObjectOpenHashMap<ResourceKey<TradeSet>> trades;

	public TradeHolder(Map<Integer, ResourceKey<TradeSet>> trades) {
		this.trades = new Int2ObjectOpenHashMap<>(trades);
	}

	public TradeHolder(Int2ObjectOpenHashMap<ResourceKey<TradeSet>> trades) {
		this.trades = trades;
	}
}
