package baguchan.tofucraft.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.ArrayList;
import java.util.List;

public class JigsawHelper {
	public static void registerJigsaw(MinecraftServer server, ResourceLocation poolLocation, ResourceLocation nbtLocation, int weight) {
		RegistryAccess manager = server.registryAccess();
		Registry<StructureTemplatePool> pools = manager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
		StructureTemplatePool pool = pools.get(poolLocation);

		StructureProcessorList processorList = manager.registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).get(poolLocation);
		List<StructurePoolElement> elements = pool.templates;

		if (processorList != null && manager.registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getResourceKey(processorList).isPresent()) {
			ResourceKey<StructureProcessorList> processorListKey = manager.registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getResourceKey(processorList).get();

			StructurePoolElement element = StructurePoolElement.legacy(nbtLocation.toString(), manager.registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getHolderOrThrow(processorListKey)).apply(StructureTemplatePool.Projection.RIGID);
			for (int i = 0; i < weight; i++) {
				elements.add(element);
			}

			List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList(pool.rawTemplates);

			elements.addAll(pool.templates);
			elementCounts.addAll(pool.rawTemplates);

			pool.templates = elements;
			pool.rawTemplates = elementCounts;
		}
	}
}
