package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.data.TofuLootTableProvider;
import baguchi.tofucraft.data.generator.recipe.CraftingGenerator;
import baguchi.tofucraft.data.provider.TofuBlockStateProviders;
import baguchi.tofucraft.data.resources.ModConfiguredFeatures;
import baguchi.tofucraft.data.resources.TofuVillagerTrades;
import baguchi.tofucraft.data.resources.TofunianTradeSets;
import baguchi.tofucraft.data.resources.builder.TofuNoiseBuilder;
import baguchi.tofucraft.data.resources.registries.TofunianClothVariants;
import baguchi.tofucraft.data.resources.registries.TofunianVariants;
import baguchi.tofucraft.registry.TofuBannerPatterns;
import baguchi.tofucraft.registry.TofuBiomeSources;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuDamageTypes;
import baguchi.tofucraft.registry.TofuDimensionTypes;
import baguchi.tofucraft.registry.TofuEnchantments;
import baguchi.tofucraft.registry.TofuLevelStems;
import baguchi.tofucraft.registry.TofuStructures;
import baguchi.tofucraft.registry.TofuTrimMaterials;
import baguchi.tofucraft.world.TofuMaterialRules;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;

public class RegistryDataGenerator {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.NOISE, (context) -> {
			})
			.add(Registries.FEATURE, ModConfiguredFeatures::bootstrapConfiguredFeature)
			.add(Registries.PLACED_FEATURE, ModConfiguredFeatures::bootstrapPlacedFeature)
			.add(Registries.PROCESSOR_LIST, TofuStructures::bootstrapProcessors)
			.add(Registries.STRUCTURE, TofuStructures::bootstrapStructures)
			.add(Registries.STRUCTURE_SET, TofuStructures::bootstrapSets)
			.add(Registries.TEMPLATE_POOL, TofuStructures::bootstrapPools)
			.add(Registries.MATERIAL_RULE, TofuMaterialRules::bootstrap)
			.add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, TofuBiomeSources::bootstrapPreset)
			.add(Registries.NOISE_SETTINGS, TofuNoiseBuilder::bootstrap)
			.add(Registries.DIMENSION_TYPE, TofuDimensionTypes::bootstrap)
			.add(Registries.BIOME, TofuBiomes::bootstrap)
			.add(Registries.LEVEL_STEM, TofuLevelStems::bootstrapLevelStem)
			.add(Registries.TRIM_MATERIAL, TofuTrimMaterials::bootstrap)
			.add(Registries.DAMAGE_TYPE, TofuDamageTypes::bootstrap)
			.add(Registries.ENCHANTMENT, TofuEnchantments::bootstrap)
			.add(Registries.BANNER_PATTERN, TofuBannerPatterns::bootstrap)
			.add(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY, TofunianVariants::bootstrap)
			.add(TofunianClothVariants.TOFUNIAN_CLOTH_VARIANT_REGISTRY_KEY, TofunianClothVariants::bootstrap)
			.add(Registries.VILLAGER_TRADE, TofuVillagerTrades::bootstrap)
			.add(Registries.TRADE_SET, TofunianTradeSets::bootstrap)
			.add(Registries.BLOCK_STATE_PROVIDER, TofuBlockStateProviders::bootstrap);

	public static final RegistrySetBuilder RELOADABLE_BUILDER = new RegistrySetBuilder()
			.add(Registries.LOOT_TABLE, TofuLootTableProvider.create())
			.add(Registries.ADVANCEMENT, TofuAdvancementGenerator.create())
			.add(VanillaRecipeProvider.create())
			.add(RecipeProvider.asBootstrap(CraftingGenerator::new));

}