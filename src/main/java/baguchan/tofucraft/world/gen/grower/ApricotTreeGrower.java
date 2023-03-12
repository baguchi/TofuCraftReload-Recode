package baguchan.tofucraft.world.gen.grower;

import baguchan.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;

public class ApricotTreeGrower extends AbstractTreeGrower {
    @Override
    protected Holder<ConfiguredFeature<TreeConfiguration, ?>> getConfiguredFeature(Random p_222910_, boolean p_222911_) {
        return ModTreeFeatures.APRICOT_TREE;
    }
}
