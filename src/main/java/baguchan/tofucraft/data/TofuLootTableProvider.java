package baguchan.tofucraft.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class TofuLootTableProvider {
	public static LootTableProvider create(PackOutput p_250807_) {
		return new LootTableProvider(p_250807_, Set.of(), List.of(new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)));
	}
}
