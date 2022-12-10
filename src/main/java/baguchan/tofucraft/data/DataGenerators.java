package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput packOutput = gen.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		event.getGenerator().addProvider(event.includeClient(), new BlockstateGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeClient(), new ItemModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		BlockTagsProvider blocktags = new BlockTagGenerator(packOutput, lookupProvider, event.getExistingFileHelper());
		event.getGenerator().addProvider(event.includeServer(), blocktags);
		event.getGenerator().addProvider(event.includeServer(), new ItemTagGenerator(packOutput, lookupProvider, blocktags, event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new EntityTagGenerator(packOutput, lookupProvider, event.getExistingFileHelper()));

		event.getGenerator().addProvider(event.includeServer(), new FluidTagGenerator(packOutput, lookupProvider, event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), TofuLootTableProvider.create(packOutput));
		event.getGenerator().addProvider(event.includeServer(), new CraftingGenerator(packOutput));
		event.getGenerator().addProvider(event.includeServer(), new WorldGenerator(packOutput));
	}
}