package baguchi.tofucraft.world.gen;

import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.material.VanillaMaterialConditions;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;

public class TofuWorldMaterialRules {
	private static final MaterialRule AIR = makeStateRule(Blocks.AIR);
	private static final MaterialRule BEDROCK = makeStateRule(TofuBlocks.TOFU_BEDROCK.get());
	private static final MaterialRule TOFUSLATE = makeStateRule(TofuBlocks.TOFUSLATE.get());
	private static final MaterialRule TOFU_TERRAIN = makeStateRule(TofuBlocks.TOFU_TERRAIN.get());
	private static final MaterialRule TOFU_TERRAIN_ZUNDA = makeStateRule(TofuBlocks.TOFU_TERRAIN_ZUNDA.get());
	private static final MaterialRule MINCED_TOFU = makeStateRule(TofuBlocks.MINCEDTOFU.get());
	private static final MaterialRule OKARA_BLOCK = makeStateRule(TofuBlocks.OKARA_BLOCK.get());

	private static MaterialRule makeStateRule(Block p_194811_) {
		return MaterialRules.state(p_194811_.defaultBlockState());
	}

	public static MaterialRule tofuWorld(BootstrapContext<MaterialRule> context, HolderGetter<Biome> biomes) {
		return tofuWorldLike(context, biomes, false, true);
	}

	public static MaterialRule tofuWorldLike(BootstrapContext<MaterialRule> context, HolderGetter<Biome> biomes, boolean p_198382_, boolean p_198383_) {
		HolderGetter<MaterialRule> rules = context.lookup(Registries.MATERIAL_RULE);
		HolderGetter<MaterialCondition> conditions = context.lookup(Registries.MATERIAL_CONDITION);
		ImmutableList.Builder<MaterialRule> builder = ImmutableList.builder();
		if (p_198382_) {
			builder.add(MaterialRules.ifTrue(MaterialRules.not(MaterialRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
		}

		if (p_198383_) {
			builder.add(MaterialRules.ifTrue(MaterialRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
		}

		builder.add(MaterialRules.ifTrue(MaterialRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), TOFUSLATE));

		MaterialRule zundaSurface = MaterialRules.sequence(MaterialRules.ifTrue(MaterialRules.waterBlockCheck(-1, 0), TOFU_TERRAIN_ZUNDA), TOFU_TERRAIN);

		MaterialRule zunda = MaterialRules.ifTrue(MaterialRules.isBiome(biomes, TofuBiomes.ZUNDA_FOREST), MaterialRules.sequence(
				MaterialRules.ifTrue(MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR), zundaSurface),
				MaterialRules.ifTrue(MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_FLOOR), TOFU_TERRAIN)
		));
		MaterialRule ishi = MaterialRules.ifTrue(MaterialRules.isBiome(biomes, TofuBiomes.TOFU_BEACH), MaterialRules.sequence(
				MaterialRules.ifTrue(MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR), MINCED_TOFU),
				MaterialRules.ifTrue(MaterialRules.getCondition(conditions, VanillaMaterialConditions.UNDER_FLOOR), MINCED_TOFU)
		));
		MaterialRule overworldLike = MaterialRules.sequence(zunda, ishi);


		MaterialRule surfacerules$rulesource9 = MaterialRules.ifTrue(MaterialRules.abovePreliminarySurface(), overworldLike);

		builder.add(surfacerules$rulesource9);

		return MaterialRules.sequence(builder.build().toArray((p_198379_) -> {
			return new MaterialRule[p_198379_];
		}));
	}
}
