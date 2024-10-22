package baguchi.tofucraft.world.gen.grower;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.features.ModTreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class TofuTreeGrowers {
	public static final TreeGrower TOFU_TREE = new TreeGrower(
			TofuCraftReload.prefix("tofu_tree").toString(),
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(ModTreeFeatures.TOFU_TREE),
			Optional.of(ModTreeFeatures.TOFU_TREE_BIG),
			Optional.of(ModTreeFeatures.TOFU_TREE),
			Optional.of(ModTreeFeatures.TOFU_TREE_BIG)
	);

	public static final TreeGrower ZUNDA_MUSHROOM = new TreeGrower(
			TofuCraftReload.prefix("zunda_mushroom").toString(),
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(ModTreeFeatures.ZUNDA_MUSHROOM),
			Optional.of(ModTreeFeatures.ZUNDA_MUSHROOM_BIG),
			Optional.of(ModTreeFeatures.ZUNDA_MUSHROOM),
			Optional.of(ModTreeFeatures.ZUNDA_MUSHROOM_BIG)
	);

	public static final TreeGrower APRICOT_TREE = new TreeGrower(
			TofuCraftReload.prefix("apricot_tree").toString(),
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(ModTreeFeatures.APRICOT_TREE),
			Optional.of(ModTreeFeatures.APRICOT_TREE),
			Optional.of(ModTreeFeatures.APRICOT_TREE),
			Optional.of(ModTreeFeatures.APRICOT_TREE)
	);
}