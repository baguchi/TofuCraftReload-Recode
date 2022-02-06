package baguchan.tofucraft.world.gen.grower;

import baguchan.tofucraft.world.gen.feature.ModTreeFeature;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class TofuTreeGrower extends AbstractTreeGrower {
	@Nullable
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random p_60038_, boolean p_60039_) {
		return p_60038_.nextInt(8) == 0 ? ModTreeFeature.TOFU_TREE_BIG : ModTreeFeature.TOFU_TREE;
	}
}