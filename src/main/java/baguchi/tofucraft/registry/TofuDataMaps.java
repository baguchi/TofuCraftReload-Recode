package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.datamap.TofuSignal;
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
	public static final DataMapType<Block, TofuSignal> TOFU_SIGNAL = DataMapType.builder(
			TofuCraftReload.prefix("tofu_signal"), Registries.BLOCK, TofuSignal.CODEC).synced(TofuSignal.LEVEL_CODEC, false).build();

	public static final Map<Block, TofuSignal> TOFU_SIGNAL_DATA = new HashMap<>();

	@SubscribeEvent
	public static void onDataMapsUpdated(DataMapsUpdatedEvent event) {
		event.ifRegistry(Registries.BLOCK, registry -> registry.getDataMap(TofuDataMaps.TOFU_SIGNAL).forEach((blockResourceKey, tofuHarden) -> {
			TOFU_SIGNAL_DATA.put(registry.getValue(blockResourceKey), tofuHarden);
		}));
	}

	@SubscribeEvent
	private static void register(final RegisterDataMapTypesEvent event) {
		event.register(TOFU_SIGNAL);
	}
}
