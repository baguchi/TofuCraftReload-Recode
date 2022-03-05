package baguchan.tofucraft.world.gen.grower;

import baguchan.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class TofuTreeGrower extends AbstractTreeGrower {
	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_60038_, boolean p_60039_) {
		return p_60038_.nextInt(8) == 0 ? ModTreeFeatures.TOFU_TREE_BIG : ModTreeFeatures.TOFU_TREE;
	}
}