package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.carver.TofuCanyonCarver;
import baguchan.tofucraft.world.carver.TofuCaveCarver;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuCarvers {
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVER = DeferredRegister.create(BuiltInRegistries.CARVER, TofuCraftReload.MODID);

	public static final Supplier<WorldCarver<CaveCarverConfiguration>> TOFU_CAVE_CARVER = WORLD_CARVER.register("tofu_cave", () -> new TofuCaveCarver(CaveCarverConfiguration.CODEC));
	public static final Supplier<WorldCarver<CanyonCarverConfiguration>> TOFU_CANYON_CARVER = WORLD_CARVER.register("tofu_canyon", () -> new TofuCanyonCarver(CanyonCarverConfiguration.CODEC));
}
