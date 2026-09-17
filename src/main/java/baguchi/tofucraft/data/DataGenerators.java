package baguchi.tofucraft.data;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.generator.FluidTagGenerator;
import baguchi.tofucraft.data.generator.RegistryDataGenerator;
import baguchi.tofucraft.data.generator.TofuDamageTypeTags;
import baguchi.tofucraft.data.generator.TofuDataMapsProvider;
import baguchi.tofucraft.data.generator.TofuEquipmentModelProvider;
import baguchi.tofucraft.data.generator.TofuModelData;
import baguchi.tofucraft.data.generator.tags.TofuBiomeTags;
import baguchi.tofucraft.data.generator.tags.TofuBlockTags;
import baguchi.tofucraft.data.generator.tags.TofuCustomTags;
import baguchi.tofucraft.data.generator.tags.TofuEnchantTags;
import baguchi.tofucraft.data.generator.tags.TofuEntityTags;
import baguchi.tofucraft.data.generator.tags.TofuItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		event.createWorldRegistryObjects(RegistryDataGenerator.BUILDER);
		event.createReloadableRegistryObjects(RegistryDataGenerator.RELOADABLE_BUILDER);

		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getWorldLookupProvider();
		generator.addProvider(true, new LootModifierProviderFactory().create(event, lookupProvider));
		generator.addProvider(true, new TofuModelData(packOutput));
		generator.addProvider(true, new TofuEquipmentModelProvider(packOutput));

		BlockTagsProvider blocktags = new TofuBlockTags(packOutput, lookupProvider);
		generator.addProvider(true, blocktags);
		generator.addProvider(true, new TofuItemTags(packOutput, lookupProvider, blocktags.contentsGetter()));
		generator.addProvider(true, new TofuEntityTags(packOutput, lookupProvider));
		generator.addProvider(true, new TofuDamageTypeTags(packOutput, lookupProvider));
		generator.addProvider(true, new TofuEnchantTags(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.BannerPatternTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.PoiTypeTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.ConfiguredFeatureTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.VillagerTradeTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.SoundEventTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuBiomeTags(packOutput, lookupProvider));
		generator.addProvider(true, new FluidTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuDataMapsProvider(packOutput, lookupProvider));
	}
}