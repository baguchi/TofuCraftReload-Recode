package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.feature.DefaultTofuFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuBiomes {

	private static ResourceLocation name(String name) {
		return new ResourceLocation(TofuCraftReload.MODID, name);
	}


	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void loadingBiome(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		if (event.getName().toString().contains("minecraft:crimson_forest"))
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, DefaultTofuFeatures.NETHER_SOYBEAN);
		if (event.getCategory() == Biome.BiomeCategory.NETHER)
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, DefaultTofuFeatures.NETHER_SOYBEAN_PATCH);
		if (event.getName().toString().contains("minecraft:warped_forest") || event.getName().toString().contains("minecraft:soul_sand_valley"))
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, DefaultTofuFeatures.SOUL_SOYBEAN);
	}
}