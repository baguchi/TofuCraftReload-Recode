package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TofuStructures {
	public static final ResourceKey<Structure> TOFU_CASTLE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle"));
	public static final ResourceKey<Structure> TOFU_VILLAGE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village"));
	public static final ResourceKey<Structure> ZUNDA_TOFU_VILLAGE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "zunda_tofu_village"));
	public static final ResourceKey<Structure> TOFU_RUINS = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_ruins"));

	public static final ResourceKey<StructureSet> TOFU_CASTLE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle"));
	public static final ResourceKey<StructureSet> TOFU_VILLAGE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village"));
	public static final ResourceKey<StructureSet> TOFU_RUINS_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_ruins"));

	public static final ResourceKey<StructureTemplatePool> TOFU_CASTLE_MAIN = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle/main"));
	public static final ResourceKey<StructureTemplatePool> TOFU_CASTLE_MAIN_2 = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle/main2"));
	public static final ResourceKey<StructureTemplatePool> TOFU_CASTLE_BRIDGE = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle/bridge"));
	public static final ResourceKey<StructureTemplatePool> TOFU_CASTLE_REWARD_BRIDGE = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle/reward_bridge"));
	public static final ResourceKey<StructureTemplatePool> TOFU_CASTLE_TOFU_GANDLEM = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_castle/mob/tofu_gandlem"));

	public static final ResourceKey<StructureTemplatePool> TOFU_VILLAGE_STREETS = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village/plains/streets"));
	public static final ResourceKey<StructureTemplatePool> TOFU_VILLAGE_HOUSE = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village/plains/house"));
	public static final ResourceKey<StructureTemplatePool> TOFU_VILLAGE_TOWN_CENTERS = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village/plains/town_centers"));


	public static final ResourceKey<StructureTemplatePool> ZUNDA_TOFU_VILLAGE_STREETS = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village/zunda/streets"));
	public static final ResourceKey<StructureTemplatePool> ZUNDA_TOFU_VILLAGE_HOUSE = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village/zunda/house"));
	public static final ResourceKey<StructureTemplatePool> ZUNDA_TOFU_VILLAGE_TOWN_CENTERS = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village/zunda/town_centers"));

	public static final ResourceKey<StructureTemplatePool> TOFUNIAN = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_village/tofunian"));


	public static final ResourceKey<StructureTemplatePool> TOFU_RUINS_START = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_ruins"));


	public static final ResourceKey<StructureProcessorList> TOFU_PLAIN_VILLAGE_ROAD = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_plain_village_road"));
	public static final ResourceKey<StructureProcessorList> ZUNDA_FOREST_VILLAGE_ROAD = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "zunda_forest_village_road"));


	public static void bootstrapStructures(BootstrapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);

		context.register(
				TOFU_CASTLE,
				new JigsawStructure(
						new Structure.StructureSettings.Builder(biomes.getOrThrow(TofuTags.Biomes.TOFU_CASTLE))
								.spawnOverrides(
										Arrays.stream(MobCategory.values())
												.collect(
														Collectors.toMap(
																p_236555_ -> (MobCategory) p_236555_,
																p_236551_ -> new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create())
														)
												)
								)
								.generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
								.terrainAdapation(TerrainAdjustment.BEARD_THIN)
								.build(),
						pools.getOrThrow(TOFU_CASTLE_MAIN),
						Optional.empty(),
						6,
						ConstantHeight.of(VerticalAnchor.absolute(0)),
						false,
						Optional.empty(),
						80,
						List.of(),
						JigsawStructure.DEFAULT_DIMENSION_PADDING,
						JigsawStructure.DEFAULT_LIQUID_SETTINGS
				)
		);
		context.register(
				TOFU_VILLAGE,
				new JigsawStructure(
						new Structure.StructureSettings.Builder(biomes.getOrThrow(TofuTags.Biomes.TOFU_VILLAGE))
								.terrainAdapation(TerrainAdjustment.BEARD_THIN)
								.build(),
						pools.getOrThrow(TofuStructures.TOFU_VILLAGE_TOWN_CENTERS),
						6,
						ConstantHeight.of(VerticalAnchor.absolute(0)),
						true,
						Heightmap.Types.WORLD_SURFACE_WG
				)
		);
		context.register(
				ZUNDA_TOFU_VILLAGE,
				new JigsawStructure(
						new Structure.StructureSettings.Builder(biomes.getOrThrow(TofuTags.Biomes.ZUNDA_TOFU_VILLAGE))
								.terrainAdapation(TerrainAdjustment.BEARD_THIN)
								.build(),
						pools.getOrThrow(TofuStructures.ZUNDA_TOFU_VILLAGE_TOWN_CENTERS),
						6,
						ConstantHeight.of(VerticalAnchor.absolute(0)),
						true,
						Heightmap.Types.WORLD_SURFACE_WG
				)
		);
		context.register(
				TOFU_RUINS,
				new JigsawStructure(
						new Structure.StructureSettings.Builder(biomes.getOrThrow(TofuTags.Biomes.TOFU_RUINS))
								.terrainAdapation(TerrainAdjustment.BEARD_THIN)
								.build(),
						pools.getOrThrow(TofuStructures.TOFU_RUINS_START),
						6,
						ConstantHeight.of(VerticalAnchor.absolute(0)),
						false,
						Heightmap.Types.WORLD_SURFACE_WG
				)
		);
	}

	public static void bootstrapSets(BootstrapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
		context.register(TOFU_CASTLE_SET, new StructureSet(structures.getOrThrow(TOFU_CASTLE), new RandomSpreadStructurePlacement(34, 6, RandomSpreadType.LINEAR, 16324620)));
		context.register(TOFU_VILLAGE_SET, new StructureSet(List.of(StructureSet.entry(structures.getOrThrow(TOFU_VILLAGE), 1), StructureSet.entry(structures.getOrThrow(ZUNDA_TOFU_VILLAGE), 1))
				, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 16324620)));
		context.register(TOFU_RUINS_SET, new StructureSet(structures.getOrThrow(TOFU_RUINS), new RandomSpreadStructurePlacement(28, 8, RandomSpreadType.LINEAR, 83469867)));
	}

	public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {
		Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
		HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

		context.register(TOFU_VILLAGE_TOWN_CENTERS, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/town_centers/rest_place")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(TOFU_VILLAGE_HOUSE, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/house/hut_1")), 4),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/house/farmlands")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/house/smithing_hut")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/house/water_space")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/house/smithing_hut_2")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/house/water_space_2")), 1)
		), StructureTemplatePool.Projection.RIGID));


		context.register(TOFU_VILLAGE_STREETS, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/streets/road_1"), processors.getOrThrow(TOFU_PLAIN_VILLAGE_ROAD)), 2),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/streets/road_2"), processors.getOrThrow(TOFU_PLAIN_VILLAGE_ROAD)), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/streets/road_3"), processors.getOrThrow(TOFU_PLAIN_VILLAGE_ROAD)), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/streets/road_4"), processors.getOrThrow(TOFU_PLAIN_VILLAGE_ROAD)), 2),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/streets/road_5"), processors.getOrThrow(TOFU_PLAIN_VILLAGE_ROAD)), 3),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/plains/streets/road_6"), processors.getOrThrow(TOFU_PLAIN_VILLAGE_ROAD)), 3)
		), StructureTemplatePool.Projection.TERRAIN_MATCHING));


		context.register(ZUNDA_TOFU_VILLAGE_TOWN_CENTERS, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/town_centers/rest_place")), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(ZUNDA_TOFU_VILLAGE_STREETS, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/streets/road_1"), processors.getOrThrow(ZUNDA_FOREST_VILLAGE_ROAD)), 3),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/streets/road_2"), processors.getOrThrow(ZUNDA_FOREST_VILLAGE_ROAD)), 2),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/streets/road_3"), processors.getOrThrow(ZUNDA_FOREST_VILLAGE_ROAD)), 2),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/streets/road_4"), processors.getOrThrow(ZUNDA_FOREST_VILLAGE_ROAD)), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/streets/road_5"), processors.getOrThrow(ZUNDA_FOREST_VILLAGE_ROAD)), 3),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/streets/road_6"), processors.getOrThrow(ZUNDA_FOREST_VILLAGE_ROAD)), 3)
		), StructureTemplatePool.Projection.TERRAIN_MATCHING));
		context.register(ZUNDA_TOFU_VILLAGE_HOUSE, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/house/hut_1")), 4),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/house/hut_2")), 2),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/house/farmlands")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/house/smithing_hut")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/house/water_space")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/house/smithing_hut_2")), 1),
				Pair.of(StructurePoolElement.legacy(name("tofu_village/zunda/house/water_space_2")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(TOFUNIAN, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(name("tofu_village/tofunian")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(TOFU_RUINS_START, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(name("tofu_ruins/tofu_world/portal_01")), 3),
				Pair.of(StructurePoolElement.single(name("tofu_ruins/tofu_world/portal_02")), 1)
		), StructureTemplatePool.Projection.RIGID));

		context.register(TOFU_CASTLE_MAIN, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(name("tofu_castle/tofucastle_main")), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(TOFU_CASTLE_MAIN_2, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(name("tofu_castle/tofucastle_main_2")), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(TOFU_CASTLE_BRIDGE, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(name("tofu_castle/bridge/tofucastle_brokenbridge")), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(TOFU_CASTLE_REWARD_BRIDGE, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(name("tofu_castle/bridge/tofucastle_reward_bridge")), 1)
		), StructureTemplatePool.Projection.RIGID));
		context.register(TOFU_CASTLE_TOFU_GANDLEM, new StructureTemplatePool(emptyPool, ImmutableList.of(
				Pair.of(StructurePoolElement.single(name("tofu_castle/mob/tofu_gandlem")), 1)
		), StructureTemplatePool.Projection.RIGID));
	}

	public static void bootstrapProcessors(BootstrapContext<StructureProcessorList> context) {
		context.register(TOFU_PLAIN_VILLAGE_ROAD, new StructureProcessorList(ImmutableList.of(
				new RuleProcessor(
						ImmutableList.of(
								new ProcessorRule(new BlockMatchTest(TofuBlocks.ISHITOFU_BRICK.get()), new BlockMatchTest(TofuBlocks.SOYMILK.get()), TofuBlocks.LEEK_PLANKS.get().defaultBlockState()),
								new ProcessorRule(new RandomBlockMatchTest(TofuBlocks.ISHITOFU_BRICK.get(), 0.2F), AlwaysTrueTest.INSTANCE, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()),
								new ProcessorRule(new BlockMatchTest(TofuBlocks.TOFU_TERRAIN.get()), new BlockMatchTest(TofuBlocks.SOYMILK.get()), TofuBlocks.SOYMILK.get().defaultBlockState()))))));
		context.register(ZUNDA_FOREST_VILLAGE_ROAD, new StructureProcessorList(ImmutableList.of(
				new RuleProcessor(
						ImmutableList.of(
								new ProcessorRule(new BlockMatchTest(TofuBlocks.ZUNDATOFU_BRICK.get()), new BlockMatchTest(TofuBlocks.SOYMILK.get()), TofuBlocks.TOFU_STEM_PLANKS.get().defaultBlockState()),
								new ProcessorRule(new RandomBlockMatchTest(TofuBlocks.ZUNDATOFU_BRICK.get(), 0.2F), AlwaysTrueTest.INSTANCE, TofuBlocks.TOFU_TERRAIN.get().defaultBlockState()),
								new ProcessorRule(new BlockMatchTest(TofuBlocks.TOFU_TERRAIN.get()), new BlockMatchTest(TofuBlocks.SOYMILK.get()), TofuBlocks.SOYMILK.get().defaultBlockState()))))));
	}

	private static String name(String name) {
		return ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, name).toString();
	}
}
