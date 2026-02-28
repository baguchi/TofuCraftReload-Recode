package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.trunk.SproutTrunkPlacer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuTrunkPlacerType {
	public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPE = DeferredRegister.create(BuiltInRegistries.TRUNK_PLACER_TYPE, TofuCraftReload.MODID);


	public static final Supplier<TrunkPlacerType<SproutTrunkPlacer>> SPROUT_TRUNK_PLACER = TRUNK_PLACER_TYPE.register("sprout_trunk_placer", () -> new TrunkPlacerType<>(SproutTrunkPlacer.CODEC));

}
