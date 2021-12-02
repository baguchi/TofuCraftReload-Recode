package baguchan.tofucraft.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class TofuVillageStructure extends JigsawFeature {
	public TofuVillageStructure(Codec<JigsawConfiguration> p_66150_) {
		super(p_66150_, 0, true, true, (p_197185_) -> {
			return true;
		});
	}

	/*@Override
	public String getFeatureName() {
		return "tofucraft:tofu_village";
	}*/

	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}
}
