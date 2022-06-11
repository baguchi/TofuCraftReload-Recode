package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.feature.BigLeekFeature;
import baguchan.tofucraft.world.gen.feature.TemplateFeature;
import baguchan.tofucraft.world.gen.feature.TofuBlobFeature;
import baguchan.tofucraft.world.gen.feature.TofuBuildingFeature;
import net.minecraft.resources.ResourceLocation;
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

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> BIG_ZUNDA_TOFU_MUSHROOM = FEATURES.register("big_zunda_tofu_mushroom", () -> new TemplateFeature(NoneFeatureConfiguration.CODEC, 7, 7, new ResourceLocation[]{new ResourceLocation(TofuCraftReload.MODID, "zunda_tofu_mushroom/small_1"), new ResourceLocation(TofuCraftReload.MODID, "zunda_tofu_mushroom/small_2")}));


}
