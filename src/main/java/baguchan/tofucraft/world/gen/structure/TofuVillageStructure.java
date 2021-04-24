package baguchan.tofucraft.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.JigsawStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

public class TofuVillageStructure extends JigsawStructure {
	public TofuVillageStructure(Codec<VillageConfig> p_i232001_1_) {
		super(p_i232001_1_, 0, true, true);
	}

	@Override
	public GenerationStage.Decoration step() {
		return GenerationStage.Decoration.SURFACE_STRUCTURES;
	}
}
