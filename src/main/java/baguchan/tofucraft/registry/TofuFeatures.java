package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.feature.BigLeekFeature;
import baguchan.tofucraft.world.gen.feature.TemplateFeature;
import baguchan.tofucraft.world.gen.feature.TofuBlobFeature;
import baguchan.tofucraft.world.gen.feature.TofuBuildingFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuFeatures {

	public static final Feature<BlockStateConfiguration> TOFU_ROCK = new TofuBlobFeature(BlockStateConfiguration.CODEC);
	public static final Feature<BlockStateConfiguration> TOFU_BUILDING = new TofuBuildingFeature(BlockStateConfiguration.CODEC);

	public static final Feature<NoneFeatureConfiguration> BIG_LEEK = new BigLeekFeature(NoneFeatureConfiguration.CODEC);

	public static final Feature<NoneFeatureConfiguration> BIG_ZUNDA_TOFU_MUSHROOM = new TemplateFeature(NoneFeatureConfiguration.CODEC, 7, 7, new ResourceLocation[]{new ResourceLocation(TofuCraftReload.MODID, "zunda_tofu_mushroom/small_1"), new ResourceLocation(TofuCraftReload.MODID, "zunda_tofu_mushroom/small_2")});


	@SubscribeEvent
	public static void registerFeature(RegistryEvent.Register<Feature<?>> registry) {
		registry.getRegistry().register(TOFU_ROCK.setRegistryName("tofu_rock"));
		registry.getRegistry().register(TOFU_BUILDING.setRegistryName("tofu_building"));
		registry.getRegistry().register(BIG_LEEK.setRegistryName("big_leek"));
		registry.getRegistry().register(BIG_ZUNDA_TOFU_MUSHROOM.setRegistryName("big_zunda_tofu_mushroom"));
	}

}
