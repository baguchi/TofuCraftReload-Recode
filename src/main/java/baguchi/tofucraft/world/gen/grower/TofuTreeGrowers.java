package baguchi.tofucraft.world.gen.grower;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;

public class TofuTreeGrowers {
	public static final TreeGrower TOFU_TREE = new TreeGrower(
			TofuCraftReload.prefix("tofu_tree").toString(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.TOFU_TREE).build(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.TOFU_TREE_BIG).build(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.TOFU_TREE).build(),
			null
	);

	public static final TreeGrower ZUNDA_MUSHROOM = new TreeGrower(
			TofuCraftReload.prefix("zunda_mushroom").toString(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.ZUNDA_MUSHROOM).build(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.ZUNDA_MUSHROOM_BIG).build(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.ZUNDA_MUSHROOM).build(),
			null
	);

	public static final TreeGrower APRICOT_TREE = new TreeGrower(
			TofuCraftReload.prefix("apricot_tree").toString(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.APRICOT_TREE).build(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.APRICOT_TREE).build(),
			WeightedList.<ResourceKey<Feature>>builder().add(ModTreeFeatures.APRICOT_TREE).build(),
			null
	);
}