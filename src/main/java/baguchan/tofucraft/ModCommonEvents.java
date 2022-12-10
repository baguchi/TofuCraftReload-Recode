package baguchan.tofucraft;

import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {
	@SubscribeEvent
	public static void addPackFinders(AddPackFindersEvent event) {
		/*try {
			if (event.getPackType() == PackType.CLIENT_RESOURCES) {
				var resourcePath = ModList.get().getModFileById(TofuCraftReload.MODID).getFile().findResource("tofucraft_legacy");
				var pack = new PathPackResources(ModList.get().getModFileById(TofuCraftReload.MODID).getFile().getFileName() + ":" + resourcePath, true, resourcePath);
				var metadataSection = pack.getMetadataSection(PackMetadataSection.TYPE);
				if (metadataSection != null) {
					event.addRepositorySource((packConsumer, packConstructor) ->
							packConsumer.accept(packConstructor.create(
									"builtin/tofucraft_legacy", Component.literal("TofuCraft Classic"), false,
									() -> pack, metadataSection, Pack.Position.TOP, PackSource.BUILT_IN, false)));
				}
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}*/
	}
}
