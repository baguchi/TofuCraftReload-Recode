package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;

import java.util.ArrayList;
import java.util.List;

public class TofuStructures {
	public static final ResourceKey<Structure> TOFU_CASTLE = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(TofuCraftReload.MODID, "tofu_castle"));

	public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {

		Registry<StructureTemplatePool> templatePools = event.getServer().registryAccess().registry(Registry.TEMPLATE_POOL_REGISTRY).get();
		Registry<StructureProcessorList> processorLists = event.getServer().registryAccess().registry(Registry.PROCESSOR_LIST_REGISTRY).get();

		TofuStructures.addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/plains/houses"), TofuCraftReload.MODID + ":village/houses/tofu_craftsman_house_plains_1e", 5);
		TofuStructures.addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/snowy/houses"), TofuCraftReload.MODID + ":village/houses/tofu_craftsman_house_snowy_1", 3);
		TofuStructures.addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/savanna/houses"), TofuCraftReload.MODID + ":village/houses/tofu_craftsman_house_savanna_1", 4);
		TofuStructures.addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/desert/houses"), TofuCraftReload.MODID + ":village/houses/tofu_craftsman_house_desert_1", 3);
		TofuStructures.addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/taiga/houses"), TofuCraftReload.MODID + ":village/houses/tofu_craftsman_house_taiga_1", 4);
	}

	public static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry, Registry<StructureProcessorList> processorListRegistry, ResourceLocation poolRL, String nbtPieceRL, int weight) {
		StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
		if (pool == null) return;

		ResourceLocation emptyProcessor = new ResourceLocation("minecraft", "empty");
		Holder<StructureProcessorList> processorHolder = processorListRegistry.getHolderOrThrow(ResourceKey.create(Registry.PROCESSOR_LIST_REGISTRY, emptyProcessor));

		SinglePoolElement piece = SinglePoolElement.single(nbtPieceRL, processorHolder).apply(StructureTemplatePool.Projection.RIGID);

		for (int i = 0; i < weight; i++) {
			pool.templates.add(piece);
		}

		List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
		listOfPieceEntries.add(new Pair<>(piece, weight));
		pool.rawTemplates = listOfPieceEntries;
	}
}
