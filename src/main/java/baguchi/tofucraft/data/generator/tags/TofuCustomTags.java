package baguchi.tofucraft.data.generator.tags;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.resources.TofuVillagerTrades;
import baguchi.tofucraft.registry.TofuBannerPatterns;
import baguchi.tofucraft.registry.TofuPoiTypes;
import baguchi.tofucraft.registry.TofuTags;
import baguchi.tofucraft.world.gen.features.TofuWorldFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FeatureTags;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

public class TofuCustomTags {

	public static class BannerPatternTagGenerator extends KeyTagProvider<BannerPattern> {

		public static final TagKey<BannerPattern> TOFUNIAN_BANNER_PATTERN = create("pattern_item/tofunian");

		public BannerPatternTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.BANNER_PATTERN, provider, TofuCraftReload.MODID);
		}

		private static TagKey<BannerPattern> create(String name) {
			return TagKey.create(Registries.BANNER_PATTERN, TofuCraftReload.prefix(name));
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			tag(TOFUNIAN_BANNER_PATTERN).add(TofuBannerPatterns.TOFUNIAN);
		}

		@Override
		public String getName() {
			return "Tofucraft Banner Pattern Tags";
		}
	}

	public static class PoiTypeTagGenerator extends KeyTagProvider<PoiType> {

		public PoiTypeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.POINT_OF_INTEREST_TYPE, provider, TofuCraftReload.MODID);
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			tag(TofuTags.PoiTypes.TOFU_VILLAGE).add(TofuPoiTypes.TOFUNIAN_STATUE);
			tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(TofuPoiTypes.TOFU_CRAFTSMAN);
		}
	}

	public static class ConfiguredFeatureTagGenerator extends KeyTagProvider<ConfiguredFeature<?, ?>> {

		public ConfiguredFeatureTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.CONFIGURED_FEATURE, provider, TofuCraftReload.MODID);
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			tag(FeatureTags.CAN_SPAWN_FROM_BONE_MEAL).add(TofuWorldFeatures.WILD_SPROUTS).add(TofuWorldFeatures.TOFU_FLOWER).add(TofuWorldFeatures.ZUNDA_TOFU_MUSHROOM);
		}
	}

	public static class VillagerTradeTagGenerator extends KeyTagProvider<VillagerTrade> {

		public VillagerTradeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.VILLAGER_TRADE, provider, TofuCraftReload.MODID);
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			tag(TofuTags.TofunianTrade.FARMER_LEVEL_1)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_1_LEEK_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_1_SOYBEAN_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_1_ZUNDA_RUBY_GRILLED_TOFU);
			tag(TofuTags.TofunianTrade.FARMER_LEVEL_2)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_2_KINAKO_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_2_SALT_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_2_ZUNDA_RUBY_TOFU_COOKIE);
			tag(TofuTags.TofunianTrade.FARMER_LEVEL_3)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_3_MISO_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_3_NATTO_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_3_ZUNDA_RUBY_MISO_SOUP);
			tag(TofuTags.TofunianTrade.FARMER_LEVEL_4)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_4_ZUNDA_RUBY_SESAME_TOFU)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_4_ZUNDA_RUBY_STRAWBERRY_TOFU);
			tag(TofuTags.TofunianTrade.FARMER_LEVEL_5)
					.add(TofuVillagerTrades.TOFUNIAN_FARMER_5_ZUNDA_RUBY_TOFU_CAKE);

			tag(TofuTags.TofunianTrade.SMITH_LEVEL_1)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_1_TOFU_ISHI_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_AXE)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_SWORD);
			tag(TofuTags.TofunianTrade.SMITH_LEVEL_2)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_2_TOFU_METAL_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_PICKAXE)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_SHOVEL);
			tag(TofuTags.TofunianTrade.SMITH_LEVEL_3)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_HOE)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_SPEAR);
			tag(TofuTags.TofunianTrade.SMITH_LEVEL_4)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_HELMET)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_CHESTPLATE)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_LEGGINGS)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_BOOTS);
			tag(TofuTags.TofunianTrade.SMITH_LEVEL_5)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_5_ZUNDA_RUBY_TEMPLATE)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_5_ZUNDA_RUBY_ZUNDA_ARROW);

			tag(TofuTags.TofunianTrade.SOY_WORKER_LEVEL_1)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_1_ZUNDA_RUBY_SOYMILK)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_1_SOYBEAN_ZUNDA_RUBY);
			tag(TofuTags.TofunianTrade.SOY_WORKER_LEVEL_2)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_2_BOTTLE_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_SOY_SAUCE)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_YUDOFU);
			tag(TofuTags.TofunianTrade.SOY_WORKER_LEVEL_3)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_COCOA)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_HONEY)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_PUMPKIN);
			tag(TofuTags.TofunianTrade.SOY_WORKER_LEVEL_4)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_PUDDING)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_RAMUNE);
			tag(TofuTags.TofunianTrade.SOY_WORKER_LEVEL_5)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_STRAWBERRY)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_TEA);
			tag(TofuTags.TofunianTrade.ENGINEER_LEVEL_1)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_1_TOFU_GEM_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_1_ZUNDA_RUBY_CIRCUIT)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_1_ZUNDA_RUBY_COIL);
			tag(TofuTags.TofunianTrade.ENGINEER_LEVEL_2)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_2_TOFU_METAL_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_2_ZUNDA_RUBY_CAPACITOR)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_2_ZUNDA_RUBY_OSCILLATOR);
			tag(TofuTags.TofunianTrade.ENGINEER_LEVEL_3)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_3_ADVANCE_TOFU_GEM_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_3_ZUNDA_RUBY_BATTERY);
			tag(TofuTags.TofunianTrade.ENGINEER_LEVEL_4)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_4_ZUNDA_RUBY_TOFU_CORE)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_4_ZUNDA_RUBY_TOFU_DEVICE);
			tag(TofuTags.TofunianTrade.ENGINEER_LEVEL_5)
					.add(TofuVillagerTrades.TOFUNIAN_ENGINEER_5_ZUNDA_RUBY_ANTENNA);



			tag(TofuTags.TofunianTrade.TOFUNIAN_TRAVELER_COMMON)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_COCOA)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_HONEY)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_PUMPKIN)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_PUDDING)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_RAMUNE);

			tag(TofuTags.TofunianTrade.TOFUNIAN_TRAVELER_BUYING)
					.add(TofuVillagerTrades.TOFUNIAN_TREAVELER_SOYMILK_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_TREAVELER_SOYBEAN_ZUNDA_RUBY)
					.add(TofuVillagerTrades.TOFUNIAN_TREAVELER_EDAMAME_ZUNDA_RUBY);


			tag(TofuTags.TofunianTrade.TOFUNIAN_TRAVELER_UNCOMMON)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_STRAWBERRY)
					.add(TofuVillagerTrades.TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_TEA)
					.add(TofuVillagerTrades.TOFUNIAN_SMITH_5_ZUNDA_RUBY_ZUNDA_ARROW);


			tag(TofuTags.TofunianTrade.TOFU_CRAFTSMAN_LEVEL_1)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_1_SOYBEAN_EMERALD)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_1_EMERALD_GRILLED)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_1_EMERALD_SOYMILK);
			tag(TofuTags.TofunianTrade.TOFU_CRAFTSMAN_LEVEL_2)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_2_BOTTLE_EMERALD)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_2_EMERALD_SOY_OIL)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_2_EMERALD_SOY_SAUCE);
			tag(TofuTags.TofunianTrade.TOFU_CRAFTSMAN_LEVEL_3)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_3_SALT_EMERALD)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_3_EMERALD_OAGE)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_3_EMERALD_FRIED_POUCH)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_3_EMERALD_FRIED_TOFU)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_3_EMERALD_AGEDASHI_TOFU);
			tag(TofuTags.TofunianTrade.TOFU_CRAFTSMAN_LEVEL_4)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_4_EMERALD_OKARA_DONUT)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_4_EMERALD_MORIJIO);
			tag(TofuTags.TofunianTrade.TOFU_CRAFTSMAN_LEVEL_5)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_5_EMERALD_MISO_SOUP)
					.add(TofuVillagerTrades.TOFU_CRAFTSMAN_5_EMERALD_MOYASHI_ITAME);

		}
	}

	public static class SoundEventTagGenerator extends KeyTagProvider<SoundEvent> {

		public SoundEventTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.SOUND_EVENT, provider, TofuCraftReload.MODID);
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			//tag(TofuTags.SoundEvents.BOSS_MUSIC);
		}
	}
}
