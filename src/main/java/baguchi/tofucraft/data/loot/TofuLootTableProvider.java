package baguchi.tofucraft.data.loot;

import net.minecraft.core.registries.SingleRegistryBootstrap;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TofuLootTableProvider {
	private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();
	private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);

	public static SingleRegistryBootstrap<LootTable> create() {
		return new LootTableProvider(LOCATIONS, List.of(new LootTableProvider.SubProviderEntry(TofuBlockLootTables::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(TofuEntityLootTables::new, LootContextParamSets.ENTITY),
				new LootTableProvider.SubProviderEntry(TofuChestLootTables::new, LootContextParamSets.CHEST)));
	}

	public static Set<ResourceKey<LootTable>> all() {
		return IMMUTABLE_LOCATIONS;
	}
}
