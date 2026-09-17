package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;

import java.util.List;

public class ModVegetationFeatures {
	public static final ResourceKey<Feature> TOFU_TREES = registerKey("tofu_trees");
	public static final ResourceKey<Feature> BIG_ZUNDA_TOFU_MUSHROOM = registerKey("big_zunda_tofu_mushroom");

	public static ResourceKey<Feature> registerKey(String name) {
		return ResourceKey.create(Registries.FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Feature> context) {
		HolderGetter<Feature> holdergetter = context.lookup(Registries.FEATURE);

		Holder<Feature> holder1 = holdergetter.getOrThrow(ModTreeFeatures.TOFU_TREE);
		Holder<Feature> holder2 = holdergetter.getOrThrow(ModTreeFeatures.TOFU_TREE_BIG);
		context.register(TOFU_TREES, new RandomSelectorFeature(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(holder2), 0.1F)), PlacementUtils.inlinePlaced(holder1)));
		Holder<Feature> holder3 = holdergetter.getOrThrow(ModTreeFeatures.ZUNDA_MUSHROOM);
		Holder<Feature> holder4 = holdergetter.getOrThrow(ModTreeFeatures.ZUNDA_MUSHROOM_BIG);
		context.register(BIG_ZUNDA_TOFU_MUSHROOM, new RandomSelectorFeature(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(holder3), 0.1F)), PlacementUtils.inlinePlaced(holder4)));
	}
}
