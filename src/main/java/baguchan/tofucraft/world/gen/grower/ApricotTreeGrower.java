package baguchan.tofucraft.world.gen.grower;

import baguchan.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class ApricotTreeGrower extends AbstractTreeGrower {
	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
		return ModTreeFeatures.APRICOT_TREE;
	}
}
