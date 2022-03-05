package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.structure.TofuVillageStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuStructures {
	public static final StructureFeature<JigsawConfiguration> TOFU_VILLAGE = new TofuVillageStructure(JigsawConfiguration.CODEC);

	@SubscribeEvent
	public static void registerStructure(RegistryEvent.Register<StructureFeature<?>> registry) {
		registry.getRegistry().register(TOFU_VILLAGE.setRegistryName("tofu_village"));
	}

	private static String prefix(String path) {
		return "tofucraft:" + path;
	}
}