package baguchi.tofucraft.data;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.generator.FluidTagGenerator;
import baguchi.tofucraft.data.generator.TofuAdvancementGenerator;
import baguchi.tofucraft.data.generator.TofuDamageTypeTags;
import baguchi.tofucraft.data.generator.TofuDataMapsProvider;
import baguchi.tofucraft.data.generator.TofuEquipmentModelProvider;
import baguchi.tofucraft.data.generator.TofuModelData;
import baguchi.tofucraft.data.generator.recipe.CraftingGenerator;
import baguchi.tofucraft.data.generator.tags.TofuBiomeTags;
import baguchi.tofucraft.data.generator.tags.TofuBlockTags;
import baguchi.tofucraft.data.generator.tags.TofuCustomTags;
import baguchi.tofucraft.data.generator.tags.TofuEnchantTags;
import baguchi.tofucraft.data.generator.tags.TofuEntityTags;
import baguchi.tofucraft.data.generator.tags.TofuItemTags;
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
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();

		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getWorldLookupProvider();
		generator.addProvider(true, new LootModifierProviderFactory().create(event, lookupProvider));
		generator.addProvider(true, new TofuModelData(packOutput));
		generator.addProvider(true, new TofuEquipmentModelProvider(packOutput));

		BlockTagsProvider blocktags = new TofuBlockTags(packOutput, lookupProvider);
		generator.addProvider(true, blocktags);
		generator.addProvider(true, new TofuItemTags(packOutput, lookupProvider, blocktags.contentsGetter()));
		generator.addProvider(true, new TofuEntityTags(packOutput, lookupProvider));
		generator.addProvider(true, new TofuDamageTypeTags(packOutput, lookupProvider));
		generator.addProvider(true, new TofuEnchantTags(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.BannerPatternTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.PoiTypeTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.ConfiguredFeatureTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.VillagerTradeTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuCustomTags.SoundEventTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuBiomeTags(packOutput, lookupProvider));
		generator.addProvider(true, new FluidTagGenerator(packOutput, lookupProvider));
		generator.addProvider(true, new TofuDataMapsProvider(packOutput, lookupProvider));
	}

	@SubscribeEvent
	public static void gatherRegistry(GatherDataRegistryEntriesEvent event) {
		event.add(Registries.NOISE, (context) -> {
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
				.add(Registries.BLOCK_STATE_PROVIDER, TofuBlockStateProviders::bootstrap)
				.add(Registries.LOOT_TABLE, TofuLootTableProvider.create())
				.add(Registries.ADVANCEMENT, TofuAdvancementGenerator.create())
				.add(RecipeProvider.asBootstrap(CraftingGenerator::new));
	}
}