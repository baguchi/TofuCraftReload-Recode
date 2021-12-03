package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuFoliagePlacerType {

	public static final FoliagePlacerType<TofuFoliagePlacer> TOFU_FOLIAGE_PLACER = new FoliagePlacerType<>(TofuFoliagePlacer.CODEC);
	@SubscribeEvent
	public static void registerFoliagePlacer(RegistryEvent.Register<FoliagePlacerType<?>> registry) {
		registry.getRegistry().register(TOFU_FOLIAGE_PLACER.setRegistryName("tofu_foliage_placer"));
	}
}
