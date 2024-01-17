package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.feature.BigLeekFeature;
import baguchan.tofucraft.world.gen.feature.TofuBlobFeature;
import baguchan.tofucraft.world.gen.feature.TofuBuildingFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, TofuCraftReload.MODID);

	public static final Supplier<Feature<BlockStateConfiguration>> TOFU_ROCK = FEATURES.register("tofu_rock", () -> new TofuBlobFeature(BlockStateConfiguration.CODEC));
	public static final Supplier<Feature<BlockStateConfiguration>> TOFU_BUILDING = FEATURES.register("tofu_building", () -> new TofuBuildingFeature(BlockStateConfiguration.CODEC));

	public static final Supplier<Feature<NoneFeatureConfiguration>> BIG_LEEK = FEATURES.register("big_leek", () -> new BigLeekFeature(NoneFeatureConfiguration.CODEC));

}
