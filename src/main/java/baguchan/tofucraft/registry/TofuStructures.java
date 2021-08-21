package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.structure.TofuVillageStructure;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuStructures {
	public static final StructureFeature<JigsawConfiguration> TOFU_VILLAGE = new TofuVillageStructure(JigsawConfiguration.CODEC);

	static StructurePieceType setPieceId(StructurePieceType p_67164_, String p_67165_) {
		return Registry.register(Registry.STRUCTURE_PIECE, p_67165_.toLowerCase(Locale.ROOT), p_67164_);
	}

	@SubscribeEvent
	public static void registerStructure(RegistryEvent.Register<StructureFeature<?>> registry) {
		registry.getRegistry().register(TOFU_VILLAGE.setRegistryName("tofu_village"));
		StructureFeature.STRUCTURES_REGISTRY.put(prefix("tofu_village"), TOFU_VILLAGE);
	}

	private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> configFeatureRegister(String p_127268_, ConfiguredStructureFeature<FC, F> p_127269_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_127268_, p_127269_);
	}

	private static String prefix(String path) {
		return "tofucraft:" + path;
	}
}