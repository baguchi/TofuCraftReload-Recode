package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.foliage.MushroomFoliagePlacer;
import baguchan.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TofuFoliagePlacerType {
	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPE = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, TofuCraftReload.MODID);


	public static final RegistryObject<FoliagePlacerType<TofuFoliagePlacer>> TOFU_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPE.register("tofu_foliage_placer", () -> new FoliagePlacerType<>(TofuFoliagePlacer.CODEC));
	public static final RegistryObject<FoliagePlacerType<MushroomFoliagePlacer>> MUSHROOM_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPE.register("mushroom_foliage_placer", () -> new FoliagePlacerType<>(MushroomFoliagePlacer.CODEC));
}
