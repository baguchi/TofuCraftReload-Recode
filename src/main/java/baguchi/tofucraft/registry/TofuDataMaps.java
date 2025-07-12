package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.datamap.TofuHarden;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.DataMapsUpdatedEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuDataMaps {
	public static final DataMapType<Block, TofuHarden> TOFU_HARDEN = DataMapType.builder(
			TofuCraftReload.prefix("tofu_harden"), Registries.BLOCK, TofuHarden.CODEC).synced(TofuHarden.LEVEL_CODEC, false).build();

	public static final Map<Block, TofuHarden> HARDEN_DATA = new HashMap<>();

	@SubscribeEvent
	public static void onDataMapsUpdated(DataMapsUpdatedEvent event) {
		event.ifRegistry(Registries.BLOCK, registry -> registry.getDataMap(TofuDataMaps.TOFU_HARDEN).forEach((blockResourceKey, tofuHarden) -> {
			HARDEN_DATA.put(registry.getValue(blockResourceKey), tofuHarden);
		}));
	}

	@SubscribeEvent
	private static void register(final RegisterDataMapTypesEvent event) {
		event.register(TOFU_HARDEN);
	}
}
