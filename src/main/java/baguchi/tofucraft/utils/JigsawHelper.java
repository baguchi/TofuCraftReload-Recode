package baguchi.tofucraft.utils;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JigsawHelper {
	private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(
			Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath("minecraft", "empty"));

	public static void registerJigsaw(MinecraftServer server, ResourceLocation poolLocation, ResourceLocation nbtLocation, int weight) {
		RegistryAccess manager = server.registryAccess();
		Registry<StructureTemplatePool> templatePoolRegistry = manager.lookupOrThrow(Registries.TEMPLATE_POOL);
		Registry<StructureProcessorList> processorListRegistry = manager.lookupOrThrow(Registries.PROCESSOR_LIST);
		Optional<Holder.Reference<StructureTemplatePool>> pool = templatePoolRegistry.get(poolLocation);

		if (pool.isEmpty()) return;

		ObjectArrayList<StructurePoolElement> elements = pool.get().value().templates;

		Holder<StructureProcessorList> processorListHolder = processorListRegistry.getOrThrow(EMPTY_PROCESSOR_LIST_KEY);

		StructurePoolElement element = SinglePoolElement.legacy(nbtLocation.toString(), processorListHolder).apply(StructureTemplatePool.Projection.RIGID);
		for (int i = 0; i < weight; i++) {
			elements.add(element);
		}

		List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList<>(pool.get().value().rawTemplates);

		elements.addAll(pool.get().value().templates);
		elementCounts.addAll(pool.get().value().rawTemplates);

		pool.get().value().templates = elements;
		pool.get().value().rawTemplates = elementCounts;
	}
}
