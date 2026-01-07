package baguchi.tofucraft.data.resources.registries;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record TofunianProfession(Optional<TagKey<Block>> jobSite,
								 Int2ObjectOpenHashMap<ResourceKey<TradeSet>> tradeSetsByLevel) {
	public @Nullable ResourceKey<TradeSet> getTrades(int level) {
		return this.tradeSetsByLevel.get(level);
	}

	public boolean is(BlockState state) {
		return this.jobSite.isPresent() && state.typeHolder().is(this.jobSite.get());
	}

	public boolean isValidTarget(BlockState blockstate) {
		return jobSite().isPresent() && blockstate.typeHolder().is(jobSite.get());
	}
}
