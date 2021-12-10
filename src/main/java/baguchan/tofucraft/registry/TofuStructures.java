package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.structure.TofuVillageStructure;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuStructures {
	public static final StructureTemplatePool TOFU_VILLAGE_START = Pools.register(new StructureTemplatePool(new ResourceLocation("tofucraft:tofu_village/town_centers"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.single("tofucraft:tofu_village/town_centers/fountain", ProcessorLists.EMPTY), 1)), StructureTemplatePool.Projection.RIGID));

	public static final StructureFeature<JigsawConfiguration> TOFU_VILLAGE = new TofuVillageStructure(JigsawConfiguration.CODEC);

	public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> TOFU_VILLAGE_FEATURES = register("tofucraft:tofu_village", TOFU_VILLAGE.configured(new JigsawConfiguration(() -> {
		return TOFU_VILLAGE_START;
	}, 6)));

	static StructurePieceType setPieceId(StructurePieceType p_67164_, String p_67165_) {
		return Registry.register(Registry.STRUCTURE_PIECE, p_67165_.toLowerCase(Locale.ROOT), p_67164_);
	}

	@SubscribeEvent
	public static void registerStructure(RegistryEvent.Register<StructureFeature<?>> registry) {
		registry.getRegistry().register(TOFU_VILLAGE.setRegistryName("tofu_village"));
		StructureFeature.STRUCTURES_REGISTRY.put(prefix("tofu_village"), TOFU_VILLAGE);
	}


	private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> register(String p_127268_, ConfiguredStructureFeature<FC, F> p_127269_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_127268_, p_127269_);
	}

	private static String prefix(String path) {
		return "tofucraft:" + path;
	}
}