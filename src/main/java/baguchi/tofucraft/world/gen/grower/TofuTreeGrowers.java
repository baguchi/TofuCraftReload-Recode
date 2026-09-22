package baguchi.tofucraft.world.gen.grower;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;

public class TofuTreeGrowers {
	public static final TreeGrower TOFU_TREE = new TreeGrower(
			TofuCraftReload.prefix("tofu_tree").toString(),
			WeightedList.of(new Weighted<>(ModTreeFeatures.TOFU_TREE, 9), new Weighted<>(ModTreeFeatures.TOFU_TREE_BIG, 1)),
			WeightedList.of(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.TOFU_TREE).build(),
			ModTreeFeatures.TOFU_TREE
	);

	public static final TreeGrower ZUNDA_MUSHROOM = new TreeGrower(
			TofuCraftReload.prefix("zunda_mushroom").toString(),
			WeightedList.of(new Weighted<>(ModTreeFeatures.ZUNDA_MUSHROOM, 9), new Weighted<>(ModTreeFeatures.ZUNDA_MUSHROOM_BIG, 1)),
			WeightedList.of(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.ZUNDA_MUSHROOM).build(),
			ModTreeFeatures.ZUNDA_MUSHROOM
	);

	public static final TreeGrower APRICOT_TREE = new TreeGrower(
			TofuCraftReload.prefix("apricot_tree").toString(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.APRICOT_TREE).build(),
			WeightedList.of(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.APRICOT_TREE).build(),
			ModTreeFeatures.APRICOT_TREE
	);
}