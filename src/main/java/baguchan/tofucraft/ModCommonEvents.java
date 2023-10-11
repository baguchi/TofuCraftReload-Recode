package baguchan.tofucraft;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {

	@SubscribeEvent
	public static void addPackFinders(AddPackFindersEvent event) {
		if (event.getPackType() == PackType.CLIENT_RESOURCES) {
			var resourcePath = ModList.get().getModFileById(TofuCraftReload.MODID).getFile().findResource("tofucraft_legacy");
			var supplier = new PathPackResources.PathResourcesSupplier(resourcePath, true);

			var pack = createBuiltinPack("builtin/tofucraft_legacy", supplier, Component.literal("TofuCraft Classic"));

			event.addRepositorySource(packConsumer -> packConsumer.accept(pack));
		}
	}


	protected static Pack createBuiltinPack(String p_250596_, Pack.ResourcesSupplier p_249625_, Component p_249043_) {
		return Pack.readMetaAndCreate(p_250596_, p_249043_, false, p_249625_, PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
	}
}
