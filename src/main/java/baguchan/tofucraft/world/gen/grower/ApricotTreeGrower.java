package baguchan.tofucraft.world.gen.grower;

import baguchan.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ApricotTreeGrower extends AbstractTreeGrower {
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
		return ModTreeFeatures.APRICOT_TREE;
	}
}
