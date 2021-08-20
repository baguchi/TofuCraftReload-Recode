package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.carver.TofuCanyonCarver;
import baguchan.tofucraft.world.carver.TofuCaveCarver;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuCarvers {
	public static final WorldCarver<CaveCarverConfiguration> TOFU_CAVE = new TofuCaveCarver(CaveCarverConfiguration.CODEC);
	public static final WorldCarver<CanyonCarverConfiguration> TOFU_CANYON = new TofuCanyonCarver(CanyonCarverConfiguration.CODEC);

	@SubscribeEvent
	public static void registerCarvers(RegistryEvent.Register<WorldCarver<?>> registry) {
		registry.getRegistry().register(TOFU_CAVE.setRegistryName("tofu_cave"));
		registry.getRegistry().register(TOFU_CANYON.setRegistryName("tofu_canyon"));
	}
}
