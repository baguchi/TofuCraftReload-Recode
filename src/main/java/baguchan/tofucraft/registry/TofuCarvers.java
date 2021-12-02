package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.carver.TofuCanyonCarver;
import baguchan.tofucraft.world.carver.TofuCaveCarver;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuCarvers {
	public static final WorldCarver<CaveCarverConfiguration> TOFU_CAVE_CARVER = new TofuCaveCarver(CaveCarverConfiguration.CODEC);
	public static final WorldCarver<CanyonCarverConfiguration> TOFU_CANYON_CARVER = new TofuCanyonCarver(CanyonCarverConfiguration.CODEC);

	private static <WC extends CarverConfiguration> ConfiguredWorldCarver<WC> register(String p_126856_, ConfiguredWorldCarver<WC> p_126857_) {
		return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_CARVER, p_126856_, p_126857_);
	}

	@SubscribeEvent
	public static void registerCarvers(RegistryEvent.Register<WorldCarver<?>> registry) {
		registry.getRegistry().register(TOFU_CAVE_CARVER.setRegistryName("tofu_cave"));
		registry.getRegistry().register(TOFU_CANYON_CARVER.setRegistryName("tofu_canyon"));
	}
}
