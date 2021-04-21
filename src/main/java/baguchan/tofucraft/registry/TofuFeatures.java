package baguchan.tofucraft.registry;

import baguchan.tofucraft.world.gen.feature.BigLeekFeature;
import baguchan.tofucraft.world.gen.feature.SurfaceTofuTemplateFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
public class TofuFeatures {
	public static final Feature<NoFeatureConfig> TOFU_BUILDING = (Feature<NoFeatureConfig>) new SurfaceTofuTemplateFeature(NoFeatureConfig.field_236558_a_, 4, 4, new ResourceLocation[]{new ResourceLocation("tofucraft", "tofu/tofu_1"), new ResourceLocation("tofucraft", "tofu/tofu_2"), new ResourceLocation("tofucraft", "tofu/tofu_3"), new ResourceLocation("tofucraft", "tofu/tofu_4"), new ResourceLocation("tofucraft", "tofu/tofu_5")});

	public static final Feature<NoFeatureConfig> ZUNDA_MUSHROOM_SMALL = (Feature<NoFeatureConfig>) new SurfaceTofuTemplateFeature(NoFeatureConfig.field_236558_a_, 7, 7, new ResourceLocation[]{new ResourceLocation("tofucraft", "mushroom/mushroom_zunda_small"), new ResourceLocation("tofucraft", "mushroom/mushroom_zunda_cache_small")});

	public static final Feature<NoFeatureConfig> ZUNDA_MUSHROOM_BIG = (Feature<NoFeatureConfig>) new SurfaceTofuTemplateFeature(NoFeatureConfig.field_236558_a_, 7, 7, new ResourceLocation[]{new ResourceLocation("tofucraft", "mushroom/mushroom_zunda_big"), new ResourceLocation("tofucraft", "mushroom/mushroom_zunda_big2")});

	public static final Feature<NoFeatureConfig> BIG_LEEK = (Feature<NoFeatureConfig>) new BigLeekFeature(NoFeatureConfig.field_236558_a_);

	@SubscribeEvent
	public static void registerFeature(RegistryEvent.Register<Feature<?>> registry) {
		registry.getRegistry().register(TOFU_BUILDING.setRegistryName("tofu_building"));
		registry.getRegistry().register(ZUNDA_MUSHROOM_SMALL.setRegistryName("zunda_mushroom_small"));
		registry.getRegistry().register(ZUNDA_MUSHROOM_BIG.setRegistryName("zunda_mushroom_big"));
		registry.getRegistry().register(BIG_LEEK.setRegistryName("big_leek"));
	}
}
