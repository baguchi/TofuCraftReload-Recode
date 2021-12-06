package baguchan.tofucraft.event;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.placement.ModNetherPlacements;
import baguchan.tofucraft.world.placement.TofuWorldPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class BiomeLoadEvents {

	private static ResourceLocation name(String name) {
		return new ResourceLocation(TofuCraftReload.MODID, name);
	}


	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void loadingBiome(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		if (event.getName().toString().contains("minecraft:crimson_forest"))
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModNetherPlacements.PATCH_NETHER_SOYBEAN_CRIMSON);
		if (event.getCategory() == Biome.BiomeCategory.NETHER)
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModNetherPlacements.PATCH_NETHER_SOYBEAN_NORMAL);
		if (event.getName().toString().contains("minecraft:warped_forest") || event.getName().toString().contains("minecraft:soul_sand_valley"))
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModNetherPlacements.PATCH_SOUL_SOYBEAN);

		if (event.getName().toString().contains("tofucraft:tofu_forest")) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_FOREST);
		}

		if (event.getName().toString().contains("tofucraft:tofu_plains")) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.TOFU_TREES_PLAINS);
		}

		if (event.getName().toString().contains("tofucraft")) {
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND);
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_LARGE);
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TofuWorldPlacements.ORE_TOFU_DIAMOND_BURIED);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TofuWorldPlacements.PATCH_LEEK);
		}
	}
}