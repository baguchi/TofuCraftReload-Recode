package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.foliage.MushroomFoliagePlacer;
import baguchan.tofucraft.world.gen.foliage.TofuFoliagePlacer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuFoliagePlacerType {
	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPE = DeferredRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE, TofuCraftReload.MODID);


	public static final Supplier<FoliagePlacerType<TofuFoliagePlacer>> TOFU_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPE.register("tofu_foliage_placer", () -> new FoliagePlacerType<>(TofuFoliagePlacer.CODEC));
	public static final Supplier<FoliagePlacerType<MushroomFoliagePlacer>> MUSHROOM_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPE.register("mushroom_foliage_placer", () -> new FoliagePlacerType<>(MushroomFoliagePlacer.CODEC));
}
