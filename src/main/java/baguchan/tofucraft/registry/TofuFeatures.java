package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.feature.BigLeekFeature;
import baguchan.tofucraft.world.gen.feature.TofuBlobFeature;
import baguchan.tofucraft.world.gen.feature.TofuBuildingFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TofuFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TofuCraftReload.MODID);

	public static final RegistryObject<Feature<BlockStateConfiguration>> TOFU_ROCK = FEATURES.register("tofu_rock", () -> new TofuBlobFeature(BlockStateConfiguration.CODEC));
	public static final RegistryObject<Feature<BlockStateConfiguration>> TOFU_BUILDING = FEATURES.register("tofu_building", () -> new TofuBuildingFeature(BlockStateConfiguration.CODEC));

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> BIG_LEEK = FEATURES.register("big_leek", () -> new BigLeekFeature(NoneFeatureConfiguration.CODEC));
}
