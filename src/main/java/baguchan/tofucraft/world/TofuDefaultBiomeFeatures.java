package baguchan.tofucraft.world;

import baguchan.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuFluids;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class TofuDefaultBiomeFeatures {
	public static BlockClusterFeatureConfig NETHER_SOYBEAN_CLUSTER = (new BlockClusterFeatureConfig.Builder((BlockStateProvider) new SimpleBlockStateProvider((BlockState) TofuBlocks.SOYBEAN_SOUL.func_176223_P().func_206870_a((Property) SoybeanSoulCropsBlock.field_176488_a, Integer.valueOf(7))), (BlockPlacer) SimpleBlockPlacer.field_236447_c_)).func_227315_a_(64).func_227317_b_().func_227322_d_();

	public static BlockClusterFeatureConfig SOUL_SOYBEAN_CLUSTER = (new BlockClusterFeatureConfig.Builder((BlockStateProvider) new SimpleBlockStateProvider((BlockState) TofuBlocks.SOYBEAN_SOUL.func_176223_P().func_206870_a((Property) SoybeanSoulCropsBlock.field_176488_a, Integer.valueOf(7))), (BlockPlacer) SimpleBlockPlacer.field_236447_c_)).func_227315_a_(64).func_227317_b_().func_227322_d_();

	public static BlockClusterFeatureConfig TOFU_FLOWER_CLUSTER = (new BlockClusterFeatureConfig.Builder((BlockStateProvider) new SimpleBlockStateProvider(TofuBlocks.TOFU_FLOWER.func_176223_P()), (BlockPlacer) SimpleBlockPlacer.field_236447_c_)).func_227315_a_(64).func_227322_d_();

	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN = register("tofucraft:nether_soybean", (ConfiguredFeature<?, ?>) ((ConfiguredFeature) Feature.field_227248_z_.func_225566_b_((IFeatureConfig) NETHER_SOYBEAN_CLUSTER).func_227228_a_(Features.Placements.field_243996_g).func_242733_d(128)).func_242729_a(1));

	public static final ConfiguredFeature<?, ?> NETHER_SOYBEAN_PATCH = register("tofucraft:nether_soybean_patch", (ConfiguredFeature<?, ?>) ((ConfiguredFeature) Feature.field_227248_z_.func_225566_b_((IFeatureConfig) NETHER_SOYBEAN_CLUSTER).func_227228_a_(Features.Placements.field_243996_g).func_242733_d(86)).func_242729_a(3));

	public static final ConfiguredFeature<?, ?> SOUL_SOYBEAN = register("tofucraft:soul_soybean", (ConfiguredFeature<?, ?>) ((ConfiguredFeature) Feature.field_227248_z_.func_225566_b_((IFeatureConfig) SOUL_SOYBEAN_CLUSTER).func_227228_a_(Features.Placements.field_243996_g).func_242733_d(128)).func_242729_a(2));

	public static final ConfiguredFeature<?, ?> TOFU_FLOWER = register("tofucraft:tofu_flower", (ConfiguredFeature<?, ?>) Feature.field_227247_y_.func_225566_b_((IFeatureConfig) TOFU_FLOWER_CLUSTER).func_227228_a_(Features.Placements.field_243996_g).func_227228_a_(Features.Placements.field_243994_e).func_242732_c(2));

	public static final ConfiguredFeature<?, ?> SOYMILK_LAKE = register("tofucraft:soymilk_lake", Feature.field_202289_ai.func_225566_b_((IFeatureConfig) new BlockStateFeatureConfig(TofuBlocks.SOYMILK.func_176223_P())).func_227228_a_(Placement.field_215006_E.func_227446_a_((IPlacementConfig) new ChanceConfig(4))));

	public static final ConfiguredFeature<?, ?> SPRING_SOYMILK = register("tofucraft:spring_soymilk", (ConfiguredFeature<?, ?>) ((ConfiguredFeature) Feature.field_202295_ao.func_225566_b_((IFeatureConfig) new LiquidsConfig(TofuFluids.SOYMILK.func_207188_f(), true, 4, 1, (Set) ImmutableSet.of(TofuBlocks.TOFU_TERRAIN))).func_227228_a_(Placement.field_242908_m.func_227446_a_((IPlacementConfig) new TopSolidRangeConfig(8, 8, 256))).func_242728_a()).func_242732_c(50));

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> p_243968_1_) {
		return (ConfiguredFeature<FC, ?>) Registry.func_218325_a(WorldGenRegistries.field_243653_e, name, p_243968_1_);
	}

	public static void init() {
	}
}
