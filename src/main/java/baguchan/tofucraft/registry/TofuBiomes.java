package baguchan.tofucraft.registry;

import baguchan.tofucraft.world.TofuDefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "tofucraft")
public class TofuBiomes {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void loadingBiome(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		if (event.getName().toString().contains("minecraft:crimson_forest"))
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, TofuDefaultBiomeFeatures.NETHER_SOYBEAN);
		if (event.getCategory() == Biome.Category.NETHER)
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, TofuDefaultBiomeFeatures.NETHER_SOYBEAN_PATCH);
		if (event.getName().toString().contains("minecraft:warped_forest") || event.getName().toString().contains("minecraft:soul_sand_valley"))
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, TofuDefaultBiomeFeatures.SOUL_SOYBEAN);
	}
}
