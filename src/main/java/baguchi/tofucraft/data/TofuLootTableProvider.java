package baguchi.tofucraft.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TofuLootTableProvider {
	public static LootTableProvider create(PackOutput p_250807_, CompletableFuture<HolderLookup.Provider> p_323798_) {
		return new LootTableProvider(p_250807_, Set.of(), List.of(new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)), p_323798_);
	}
}
