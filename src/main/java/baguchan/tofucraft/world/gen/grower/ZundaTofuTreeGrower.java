package baguchan.tofucraft.world.gen.grower;

import baguchan.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class ZundaTofuTreeGrower extends AbstractTreeGrower {
	@Nullable
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_60038_, boolean p_60039_) {
		return p_60038_.nextInt(8) == 0 ? ModTreeFeatures.ZUNDA_MUSHROOM_BIG : ModTreeFeatures.ZUNDA_MUSHROOM;
	}
}