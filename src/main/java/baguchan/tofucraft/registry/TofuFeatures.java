package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.feature.TofuBlobFeature;
import baguchan.tofucraft.world.gen.feature.TofuBuildingFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuFeatures {

	public static final Feature<BlockStateConfiguration> TOFU_ROCK = new TofuBlobFeature(BlockStateConfiguration.CODEC);
	public static final Feature<BlockStateConfiguration> TOFU_BUILDING = new TofuBuildingFeature(BlockStateConfiguration.CODEC);

	@SubscribeEvent
	public static void registerFeature(RegistryEvent.Register<Feature<?>> registry) {
		registry.getRegistry().register(TOFU_ROCK.setRegistryName("tofu_rock"));
		registry.getRegistry().register(TOFU_BUILDING.setRegistryName("tofu_building"));
	}

}
