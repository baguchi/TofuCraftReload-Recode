package baguchan.tofucraft.world.gen.grower;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.world.gen.features.ModTreeFeatures;
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