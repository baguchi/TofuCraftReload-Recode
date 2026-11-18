package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuTags;
import baguchi.tofucraft.world.gen.feature.BigLeekFeature;
import baguchi.tofucraft.world.gen.feature.TofuBuildingFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.DeltaFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.WaterloggedVegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class TofuWorldFeatures {
	public static final RuleTest TOFU_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFU_TERRAIN.get());
	public static final RuleTest TOFUSLATE_ORE_REPLACEABLES = new BlockMatchTest(TofuBlocks.TOFUSLATE.get());

	public static final ImmutableList<BlockReplacement> ORE_DIAMOND_TARGET_LIST = ImmutableList.of(BlockReplacement.replace(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFU_DIAMOND.get().defaultBlockState()), BlockReplacement.replace(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get().defaultBlockState()));
	public static final ImmutableList<BlockReplacement> ORE_TOFUGEM_TARGET_LIST = ImmutableList.of(BlockReplacement.replace(TOFU_ORE_REPLACEABLES, TofuBlocks.ORE_TOFUGEM.get().defaultBlockState()));
	public static final ImmutableList<BlockReplacement> ORE_SOY_FORCE_TARGET_LIST = ImmutableList.of(BlockReplacement.replace(TOFUSLATE_ORE_REPLACEABLES, TofuBlocks.TOFUSLATE_SOY_FORCE_ORE.get().defaultBlockState()));

	public static final ResourceKey<Feature> ORE_KINU_TOFU = registerKey("ore_kinu_tofu");
	public static final ResourceKey<Feature> ORE_MINCED_TOFU = registerKey("ore_minced_tofu");

	public static final ResourceKey<Feature> ORE_DIAMOND_SMALL = registerKey("ore_tofu_diamond_small");
	public static final ResourceKey<Feature> ORE_DIAMOND_MEDIUM = registerKey("ore_tofu_diamond_medium");
	public static final ResourceKey<Feature> ORE_DIAMOND_LARGE = registerKey("ore_tofu_diamond_large");
	public static final ResourceKey<Feature> ORE_DIAMOND_BURIED = registerKey("ore_tofu_diamond_buried");

	public static final ResourceKey<Feature> ORE_SOY_FORCE = registerKey("ore_soy_force");

	public static final ResourceKey<Feature> ORE_TOFUGEM_SMALL = registerKey("ore_tofugem_small");
	public static final ResourceKey<Feature> ORE_TOFUGEM_LARGE = registerKey("ore_tofugem_large");

	public static final ResourceKey<Feature> TOFU_DELTA = registerKey("tofu_delta");

	public static final ResourceKey<Feature> SPROUT_WATER_POOL = registerKey("sprout_water_pool");
	public static final ResourceKey<Feature> SPRING_DOUBANJIANG = registerKey("spring_doubanjiang");
	public static final ResourceKey<Feature> SPRING_SOYSAUCE = registerKey("spring_soysauce");
	public static final ResourceKey<Feature> WILD_SPROUTS = registerKey("wild_sprouts");

	public static final ResourceKey<Feature> TOFU_FLOWER = registerKey("tofu_flower");
	public static final ResourceKey<Feature> LEEK = registerKey("leek");
	public static final ResourceKey<Feature> BIG_LEEK = registerKey("big_leek");

	public static final ResourceKey<Feature> TOFU_DRY_BUILDING = registerKey("tofu_dry_building");

	public static final ResourceKey<Feature> ZUNDA_TOFU_MUSHROOM = registerKey("zunda_tofu_mushroom");


	public static ResourceKey<Feature> registerKey(String name) {
		return ResourceKey.create(Registries.FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Feature> context) {
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

		RuleTest ruletest = new TagMatchTest(TofuTags.Blocks.TOFU_TERRAIN);
		HolderGetter<Feature> lookup = context.lookup(Registries.FEATURE);
		context.register(ORE_KINU_TOFU, new OreFeature(ruletest, TofuBlocks.KINUTOFU.get().defaultBlockState(), 20));
		context.register(ORE_MINCED_TOFU, new OreFeature(ruletest, TofuBlocks.MINCEDTOFU.get().defaultBlockState(), 28));

		context.register(ORE_DIAMOND_SMALL, new OreFeature(ORE_DIAMOND_TARGET_LIST, 4, 0.0F));
		context.register(ORE_DIAMOND_MEDIUM, new OreFeature(ORE_DIAMOND_TARGET_LIST, 8, 0.25F));
		context.register(ORE_DIAMOND_LARGE, new OreFeature(ORE_DIAMOND_TARGET_LIST, 12, 0.5F));
		context.register(ORE_DIAMOND_BURIED, new OreFeature(ORE_DIAMOND_TARGET_LIST, 8, 1.0F));

		context.register(ORE_SOY_FORCE, new OreFeature(ORE_SOY_FORCE_TARGET_LIST, 8, 0.0F));


		context.register(ORE_TOFUGEM_SMALL, new OreFeature(ORE_TOFUGEM_TARGET_LIST, 6));
		context.register(ORE_TOFUGEM_LARGE, new OreFeature(ORE_TOFUGEM_TARGET_LIST, 10));

		context.register(TOFU_DELTA,
				new DeltaFeature(TofuBlocks.DOUBANJIANG.get().defaultBlockState(), TofuBlocks.MABOU_TERRAIN.get().defaultBlockState(), UniformInt.of(3, 7), UniformInt.of(0, 2))
		);
		context.register(WILD_SPROUTS, new SimpleBlockFeature(BlockStateProvider.of(TofuBlocks.WILD_SPROUTS.get())));

		context.register(SPROUT_WATER_POOL, new WaterloggedVegetationPatchFeature(blocks.getOrThrow(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE), BlockStateProvider.holderOf(TofuBlocks.MINCEDTOFU.get()), PlacementUtils.inlinePlaced(lookup.getOrThrow(ModTreeFeatures.SPROUT), new PlacementModifier[0]), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.055F, UniformInt.of(4, 7), 0.7F));
		context.register(
				SPRING_DOUBANJIANG,
				new SpringFeature(
						TofuFluids.DOUBANJIANG.get().defaultFluidState(),
						true,
						4,
						1,
						HolderSet.direct(
								Block::builtInRegistryHolder,
								TofuBlocks.TOFU_TERRAIN.get(),
								TofuBlocks.TOFUSLATE.get()
						)
				)
		);
		context.register(
				SPRING_SOYSAUCE,
				new SpringFeature(
						TofuFluids.SOYSAUCE.get().defaultFluidState(),
						true,
						4,
						1,
						HolderSet.direct(
								Block::builtInRegistryHolder,
								TofuBlocks.TOFU_TERRAIN.get(),
								TofuBlocks.TOFUSLATE.get()
						)
				)
		);
		context.register(TOFU_FLOWER, new SimpleBlockFeature(BlockStateProvider.of(TofuBlocks.TOFU_FLOWER.get())));
		context.register(LEEK, new SimpleBlockFeature(Holder.direct(new WeightedStateProvider(
				WeightedList.<BlockState>builder().add(TofuBlocks.LEEK.get().defaultBlockState(), 10).add(TofuBlocks.TALL_LEEK.get().defaultBlockState(), 1)
		)), false));
		context.register(BIG_LEEK, new BigLeekFeature());

		context.register(TOFU_DRY_BUILDING, new TofuBuildingFeature(Holder.direct(new NoiseThresholdProvider(
				2345L,
				NormalNoise.createParity(0, 1.0),
				8.0F,
				-0.5F,
				0.65F,
				TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(),
				List.of(
						TofuBlocks.FRIED_TOFU.get().defaultBlockState(),
						TofuBlocks.FRIED_POUCH_TOFU.get().defaultBlockState()
				),
				List.of(
						TofuBlocks.DRIED_TOFU.get().defaultBlockState()
				)
		))));

		context.register(ZUNDA_TOFU_MUSHROOM, new SimpleBlockFeature(BlockStateProvider.of(TofuBlocks.ZUNDA_TOFU_MUSHROOM.get())));

	}
}