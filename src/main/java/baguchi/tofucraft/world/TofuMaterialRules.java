package baguchi.tofucraft.world;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.TofuWorldMaterialRules;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;

public class TofuMaterialRules {
	public static final ResourceKey<MaterialRule> TOFU_WORLD = createKey("tofu_world");

	private static ResourceKey<MaterialRule> createKey(String name) {
		return ResourceKey.create(Registries.MATERIAL_RULE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name));
	}

	private static MaterialRule makeStateRule(Block block) {
		return MaterialRules.state(block.defaultBlockState());
	}

	public static void bootstrap(BootstrapContext<MaterialRule> context) {
		HolderGetter<MaterialRule> rules = context.lookup(Registries.MATERIAL_RULE);
		HolderGetter<MaterialCondition> conditions = context.lookup(Registries.MATERIAL_CONDITION);
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

		context.register(
				TOFU_WORLD,
				TofuWorldMaterialRules.tofuWorld(context, biomes));
	}

	public static MaterialRule air() {
		return MaterialRules.state(Blocks.AIR.defaultBlockState());
	}
}
