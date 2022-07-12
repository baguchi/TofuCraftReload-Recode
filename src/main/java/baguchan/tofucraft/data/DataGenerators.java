package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		event.getGenerator().addProvider(event.includeServer(), new BlockstateGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new ItemModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator(), event.getExistingFileHelper());
		event.getGenerator().addProvider(event.includeServer(), blocktags);
		event.getGenerator().addProvider(event.includeServer(), new ItemTagGenerator(event.getGenerator(), blocktags, event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new FluidTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new LootGenerator(event.getGenerator()));
		event.getGenerator().addProvider(event.includeServer(), new CraftingGenerator(event.getGenerator()));
	}
}