package baguchi.tofucraft.data.resources.registries;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record TofunianProfession(Optional<List<BlockState>> jobSite,
								 Int2ObjectOpenHashMap<ResourceKey<TradeSet>> tradeSetsByLevel) {
	public @Nullable ResourceKey<TradeSet> getTrades(int level) {
		return this.tradeSetsByLevel.get(level);
	}
}
