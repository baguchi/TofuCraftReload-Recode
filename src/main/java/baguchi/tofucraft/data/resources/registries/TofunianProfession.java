package baguchi.tofucraft.data.resources.registries;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

public record TofunianProfession(Optional<Set<BlockState>> jobSite,
								 Int2ObjectOpenHashMap<ResourceKey<TradeSet>> tradeSetsByLevel) {
	public @Nullable ResourceKey<TradeSet> getTrades(int level) {
		return this.tradeSetsByLevel.get(level);
	}

	public boolean is(BlockState state) {
		return this.jobSite.isPresent() && this.jobSite.get().contains(state);
	}
}
