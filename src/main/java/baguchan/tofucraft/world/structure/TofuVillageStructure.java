package baguchan.tofucraft.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;

public class TofuVillageStructure extends JigsawFeature {
	public static final StructureTemplatePool START = Pools.register(new StructureTemplatePool(new ResourceLocation("village/plains/town_centers"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy("village/plains/town_centers/plains_fountain_01", ProcessorLists.MOSSIFY_20_PERCENT), 50), Pair.of(StructurePoolElement.legacy("village/plains/town_centers/plains_meeting_point_1", ProcessorLists.MOSSIFY_20_PERCENT), 50), Pair.of(StructurePoolElement.legacy("village/plains/town_centers/plains_meeting_point_2"), 50), Pair.of(StructurePoolElement.legacy("village/plains/town_centers/plains_meeting_point_3", ProcessorLists.MOSSIFY_70_PERCENT), 50), Pair.of(StructurePoolElement.legacy("village/plains/zombie/town_centers/plains_fountain_01", ProcessorLists.ZOMBIE_PLAINS), 1), Pair.of(StructurePoolElement.legacy("village/plains/zombie/town_centers/plains_meeting_point_1", ProcessorLists.ZOMBIE_PLAINS), 1), Pair.of(StructurePoolElement.legacy("village/plains/zombie/town_centers/plains_meeting_point_2", ProcessorLists.ZOMBIE_PLAINS), 1), Pair.of(StructurePoolElement.legacy("village/plains/zombie/town_centers/plains_meeting_point_3", ProcessorLists.ZOMBIE_PLAINS), 1)), StructureTemplatePool.Projection.RIGID));

	public TofuVillageStructure(Codec<JigsawConfiguration> p_66150_) {
		super(p_66150_, 0, true, true);
	}

	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}
}
