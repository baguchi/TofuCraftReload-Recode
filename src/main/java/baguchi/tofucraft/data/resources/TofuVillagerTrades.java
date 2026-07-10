package baguchi.tofucraft.data.resources;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.Optional;

import static net.minecraft.world.item.trading.VillagerTrades.enchantedItem;

public class TofuVillagerTrades {
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_1_SOYBEAN_ZUNDA_RUBY = resourceKey("tofunian_farmer/1/soybean_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_1_LEEK_ZUNDA_RUBY = resourceKey("tofunian_farmer/1/leek_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_1_ZUNDA_RUBY_GRILLED_TOFU = resourceKey("tofunian_farmer/1/zunda_ruby_grilled_tofu");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_2_KINAKO_ZUNDA_RUBY = resourceKey("tofunian_farmer/2/kinako_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_2_SALT_ZUNDA_RUBY = resourceKey("tofunian_farmer/2/salt_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_2_ZUNDA_RUBY_TOFU_COOKIE = resourceKey("tofunian_farmer/2/zunda_ruby_tofu_cookie");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_3_NATTO_ZUNDA_RUBY = resourceKey("tofunian_farmer/3/natto_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_3_MISO_ZUNDA_RUBY = resourceKey("tofunian_farmer/3/miso_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_3_ZUNDA_RUBY_MISO_SOUP = resourceKey("tofunian_farmer/3/zunda_ruby_miso_soup");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_4_ZUNDA_RUBY_STRAWBERRY_TOFU = resourceKey("tofunian_farmer/4/zunda_ruby_strawberry_tofu");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_4_ZUNDA_RUBY_SESAME_TOFU = resourceKey("tofunian_farmer/4/zunda_ruby_sesame_tofu");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_FARMER_5_ZUNDA_RUBY_TOFU_CAKE = resourceKey("tofunian_farmer/5/zunda_ruby_tofu_cake");


	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_1_TOFU_ISHI_ZUNDA_RUBY = resourceKey("tofunian_smith/1/tofu_ishi_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_SWORD = resourceKey("tofunian_smith/1/zunda_ruby_tofu_metal_sword");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_AXE = resourceKey("tofunian_smith/1/zunda_ruby_tofu_metal_axe");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_2_TOFU_METAL_ZUNDA_RUBY = resourceKey("tofunian_smith/2/tofu_metal_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_PICKAXE = resourceKey("tofunian_smith/2/zunda_ruby_tofu_metal_pickaxe");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_SHOVEL = resourceKey("tofunian_smith/2/zunda_ruby_tofu_metal_shovel");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_SPEAR = resourceKey("tofunian_smith/3/zunda_ruby_tofu_metal_spear");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_HOE = resourceKey("tofunian_smith/3/zunda_ruby_tofu_metal_hoe");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_HELMET = resourceKey("tofunian_smith/4/zunda_ruby_tofu_metal_helmet");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_CHESTPLATE = resourceKey("tofunian_smith/4/zunda_ruby_tofu_metal_chestplate");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_LEGGINGS = resourceKey("tofunian_smith/4/zunda_ruby_tofu_metal_leggings");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_BOOTS = resourceKey("tofunian_smith/4/zunda_ruby_tofu_metal_boots");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_5_ZUNDA_RUBY_TEMPLATE = resourceKey("tofunian_smith/5/zunda_ruby_tofu_metal_leggings");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SMITH_5_ZUNDA_RUBY_ZUNDA_ARROW = resourceKey("tofunian_smith/5/zunda_ruby_zunda_arrow");


	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_1_SOYBEAN_ZUNDA_RUBY = resourceKey("tofunian_soy_worker/1/soybean_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_1_ZUNDA_RUBY_SOYMILK = resourceKey("tofunian_soy_worker/1/zunda_ruby_tofu_soymilk");
	;
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_2_BOTTLE_ZUNDA_RUBY = resourceKey("tofunian_soy_worker/2/bottle_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_SOY_SAUCE = resourceKey("tofunian_soy_worker/2/zunda_ruby_soysauce");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_YUDOFU = resourceKey("tofunian_soy_worker/2/zunda_ruby_yudofu");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_PUMPKIN = resourceKey("tofunian_soy_worker/3/zunda_ruby_soymilk_pumpkin");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_COCOA = resourceKey("tofunian_soy_worker/3/zunda_ruby_soymilk_cocoa");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_HONEY = resourceKey("tofunian_soy_worker/3/zunda_ruby_soymilk_honey");

	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_PUDDING = resourceKey("tofunian_soy_worker/4/zunda_ruby_soymilk_pudding");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_RAMUNE = resourceKey("tofunian_soy_worker/4/zunda_ruby_soymilk_ramune");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_TEA = resourceKey("tofunian_soy_worker/5/zunda_ruby_soymilk_tea");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_STRAWBERRY = resourceKey("tofunian_soy_worker/5/zunda_ruby_soymilk_strayberry");

	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_1_TOFU_GEM_ZUNDA_RUBY = resourceKey("tofunian_tofu_engineer/1/tofu_gem_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_1_ZUNDA_RUBY_CIRCUIT = resourceKey("tofunian_tofu_engineer/1/zunda_ruby_circuit");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_1_ZUNDA_RUBY_COIL = resourceKey("tofunian_tofu_engineer/1/zunda_ruby_coil");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_2_TOFU_METAL_ZUNDA_RUBY = resourceKey("tofunian_tofu_engineer/2/tofu_metal");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_2_ZUNDA_RUBY_CAPACITOR = resourceKey("tofunian_tofu_engineer/2/zunda_ruby_capacitor");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_2_ZUNDA_RUBY_OSCILLATOR = resourceKey("tofunian_tofu_engineer/2/zunda_ruby_oscillator");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_3_ADVANCE_TOFU_GEM_ZUNDA_RUBY = resourceKey("tofunian_tofu_engineer/3/advance_tofu_gem");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_3_ZUNDA_RUBY_BATTERY = resourceKey("tofunian_tofu_engineer/3/zunda_ruby_battery");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_4_ZUNDA_RUBY_TOFU_CORE = resourceKey("tofunian_tofu_engineer/4/zunda_ruby_tofu_core");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_4_ZUNDA_RUBY_TOFU_DEVICE = resourceKey("tofunian_tofu_engineer/4/zunda_ruby_tofu_device");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_ENGINEER_5_ZUNDA_RUBY_ANTENNA = resourceKey("tofunian_tofu_engineer/5/zunda_ruby_antenna");




	public static final ResourceKey<VillagerTrade> TOFUNIAN_TREAVELER_SOYBEAN_ZUNDA_RUBY = resourceKey("tofunian_traveler/buying/soybean_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_TREAVELER_SOYMILK_ZUNDA_RUBY = resourceKey("tofunian_traveler/buying/soymilk_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_TREAVELER_EDAMAME_ZUNDA_RUBY = resourceKey("tofunian_traveler/buying/edamame_zunda_ruby");


	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_1_SOYBEAN_EMERALD = resourceKey("tofunian_soy_worker/1/soybean_emerald");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_1_EMERALD_SOYMILK = resourceKey("tofunian_soy_worker/1/emerald_soymilk");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_1_EMERALD_GRILLED = resourceKey("tofunian_soy_worker/1/emerald_tofu_grilled");

	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_2_BOTTLE_EMERALD = resourceKey("tofu_craftsman/2/bottle_emerald");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_2_EMERALD_SOY_SAUCE = resourceKey("tofu_craftsman/2/emerald_soysauce");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_2_EMERALD_SOY_OIL = resourceKey("tofu_craftsman/2/emerald_soyoile");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_3_SALT_EMERALD = resourceKey("tofu_craftsman/3/salt_emerald");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_3_EMERALD_OAGE = resourceKey("tofu_craftsman/3/emerald_oage");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_3_EMERALD_FRIED_POUCH = resourceKey("tofu_craftsman/3/emerald_fried_pouch");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_3_EMERALD_FRIED_TOFU = resourceKey("tofu_craftsman/3/emerald_fried");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_3_EMERALD_AGEDASHI_TOFU = resourceKey("tofu_craftsman/3/emerald_agedashi_tofu");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_4_EMERALD_MORIJIO = resourceKey("tofu_craftsman/4/emerald_mirijio");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_4_EMERALD_OKARA_DONUT = resourceKey("tofu_craftsman/4/emerald_okara_donut");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_5_EMERALD_MISO_SOUP = resourceKey("tofu_craftsman/5/emerald_miso_soup");
	public static final ResourceKey<VillagerTrade> TOFU_CRAFTSMAN_5_EMERALD_MOYASHI_ITAME = resourceKey("tofu_craftsman/5/emerald_moyashgi_itame");


	public static ResourceKey<VillagerTrade> resourceKey(String path) {
		return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, path));
	}

	public static void bootstrap(BootstrapContext<VillagerTrade> context) {
		HolderGetter<Item> items = context.lookup(Registries.ITEM);
		HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
		HolderSet<Enchantment> enchantmentsForTradedEquipment = enchantments.getOrThrow(EnchantmentTags.ON_TRADED_EQUIPMENT);
		register(
				context,
				TOFUNIAN_FARMER_1_SOYBEAN_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SEEDS_SOYBEANS.get(), 24), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_1_LEEK_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.LEEK.get(), 32), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_1_ZUNDA_RUBY_GRILLED_TOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TOFU_GRILLED, 9), 16, 2, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_FARMER_2_KINAKO_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.KINAKO.get(), 16), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_2_SALT_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SALT.get(), 24), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_2_ZUNDA_RUBY_TOFU_COOKIE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.TOFUCOOKIE, 14), 16, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_3_NATTO_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.NATTO.get(), 12), new ItemStackTemplate(TofuItems.ZUNDARUBY), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_3_MISO_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.MISO.get(), 12), new ItemStackTemplate(TofuItems.ZUNDARUBY), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_3_ZUNDA_RUBY_MISO_SOUP,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.MISOSOUP, 2), 8, 12, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_4_ZUNDA_RUBY_STRAWBERRY_TOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.TOFU_STRAWBERRY, 12), 8, 15, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_4_ZUNDA_RUBY_SESAME_TOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.TOFU_SESAME, 12), 8, 15, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_5_ZUNDA_RUBY_TOFU_CAKE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuBlocks.TOFU_CAKE.asItem()), 3, 20, 0.1F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SMITH_1_TOFU_ISHI_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.TOFU_ISHI.get(), 32), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_SWORD,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TOFU_METAL_SWORD), 10, 3
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_SWORD.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_AXE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.TOFU_METAL_AXE), 10, 3
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_AXE.get()))
		);

		register(
				context,
				TOFUNIAN_SMITH_2_TOFU_METAL_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.TOFU_METAL.get(), 18), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_PICKAXE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.TOFU_METAL_PICKAXE), 10, 6
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_PICKAXE.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_SHOVEL,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TOFU_METAL_SHOVEL), 10, 6
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_SHOVEL.get()))
		);


		register(
				context,
				TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_SPEAR,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TOFU_METAL_SPEAR), 10, 12
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_SPEAR.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_HOE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TOFU_METAL_HOE), 10, 12
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_HOE.get()))
		);

		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_HELMET,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.TOFU_METAL_HELMET), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_HELMET.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_CHESTPLATE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 5), new ItemStackTemplate(TofuItems.TOFU_METAL_CHESTPLATE), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_CHESTPLATE.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_LEGGINGS,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 4), new ItemStackTemplate(TofuItems.TOFU_METAL_LEGGINGS), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_LEGGINGS.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_BOOTS,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.TOFU_METAL_BOOTS), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_BOOTS.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_5_ZUNDA_RUBY_TEMPLATE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 14), new ItemStackTemplate(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE), 4, 22
						, 0.1F,
						Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SMITH_5_ZUNDA_RUBY_ZUNDA_ARROW,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.ZUNDA_ARROW, 16), 12, 22
						, 0.1F,
						Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SOY_WORKER_1_SOYBEAN_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SEEDS_SOYBEANS.get(), 24), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_1_ZUNDA_RUBY_SOYMILK,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.SOYMILK_BOTTLE, 3), 12, 2, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SOY_WORKER_2_BOTTLE_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(Items.GLASS_BOTTLE, 8), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_SOY_SAUCE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.BOTTLE_SOYSAUSE, 5), 12, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_YUDOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.YUDOFU, 3), 12, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_COCOA,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.SOYMILK_COCOA_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_PUDDING,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.SOYMILK_PUDDING_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_PUMPKIN,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.SOYMILK_PUMPKIN_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_HONEY,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.SOYMILK_HONEY_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_RAMUNE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.SOYMILK_RAMUNE_BOTTLE, 3), 8, 15, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_STRAWBERRY,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.SOYMILK_STRAWBERRY_BOTTLE, 5), 6, 20, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_TEA,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.SOYMILK_TEA_BOTTLE, 5), 6, 20, 0.1F, Optional.empty(), List.of())
		);


		register(
				context,
				TOFUNIAN_ENGINEER_1_TOFU_GEM_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.TOFUGEM.get(), 10), new ItemStackTemplate(TofuItems.ZUNDARUBY), 16, 3, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_1_ZUNDA_RUBY_CIRCUIT,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TF_CIRCUIT, 6), 12, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_1_ZUNDA_RUBY_COIL,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TF_COIL, 6), 12, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_2_TOFU_METAL_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.TOFU_METAL.get(), 12), new ItemStackTemplate(TofuItems.ZUNDARUBY), 12, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_2_ZUNDA_RUBY_CAPACITOR,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStackTemplate(TofuItems.TF_CAPACITOR, 4), 12, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_2_ZUNDA_RUBY_OSCILLATOR,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.TF_OSCILLATOR, 7), 12, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_3_ADVANCE_TOFU_GEM_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.ADVANCE_TOFUGEM.get(), 8), new ItemStackTemplate(TofuItems.ZUNDARUBY, 2), 12, 12, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_3_ZUNDA_RUBY_BATTERY,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStackTemplate(TofuItems.TF_BATTERY, 1), 6, 11, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_4_ZUNDA_RUBY_TOFU_CORE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStackTemplate(TofuItems.TOFU_CORE, 1), 6, 16, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_4_ZUNDA_RUBY_TOFU_DEVICE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 5), new ItemStackTemplate(TofuItems.TF_DEVICE, 1), 6, 17, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_ENGINEER_5_ZUNDA_RUBY_ANTENNA,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 6), new ItemStackTemplate(TofuBlocks.ANTENNA_BASIC.asItem(), 1), 6, 16, 0.1F, Optional.empty(), List.of())
		);


		register(
				context,
				TOFUNIAN_TREAVELER_SOYBEAN_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SEEDS_SOYBEANS.get(), 24), new ItemStackTemplate(TofuItems.ZUNDARUBY), 6, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_TREAVELER_SOYMILK_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SOYMILK_BOTTLE.get(), 3), new ItemStackTemplate(TofuItems.ZUNDARUBY), 4, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_TREAVELER_EDAMAME_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.EDAMAME.get(), 24), new ItemStackTemplate(TofuItems.ZUNDARUBY), 4, 2, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFU_CRAFTSMAN_1_SOYBEAN_EMERALD,
				new VillagerTrade(new TradeCost(TofuItems.SEEDS_SOYBEANS.get(), 16), new ItemStackTemplate(Items.EMERALD, 1), 12, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_1_EMERALD_SOYMILK,
				new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(TofuItems.SOYMILK_BOTTLE, 3), 12, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_1_EMERALD_GRILLED,
				new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(TofuItems.TOFU_GRILLED, 9), 12, 2, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFU_CRAFTSMAN_2_BOTTLE_EMERALD,
				new VillagerTrade(new TradeCost(Items.GLASS_BOTTLE, 8), new ItemStackTemplate(Items.EMERALD, 1), 12, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_2_EMERALD_SOY_OIL,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.BOTTLE_SOYOIL, 7), 12, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_2_EMERALD_SOY_SAUCE,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.BOTTLE_SOYSAUSE, 6), 8, 7, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFU_CRAFTSMAN_3_SALT_EMERALD,
				new VillagerTrade(new TradeCost(TofuItems.SALT.get(), 14), new ItemStackTemplate(Items.EMERALD, 1), 12, 11, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_3_EMERALD_AGEDASHI_TOFU,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.AGEDASHI_TOFU, 6), 8, 12, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_3_EMERALD_OAGE,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.OAGE, 6), 8, 12, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_3_EMERALD_FRIED_TOFU,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.TOFU_FRIED, 8), 8, 12, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_3_EMERALD_FRIED_POUCH,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.TOFU_FRIED_POUCH, 8), 8, 12, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFU_CRAFTSMAN_4_EMERALD_MORIJIO,
				new VillagerTrade(new TradeCost(Items.EMERALD, 15), new ItemStackTemplate(TofuBlocks.MORIJIO.asItem(), 2), 8, 17, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_4_EMERALD_OKARA_DONUT,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.OKARA_DONUT, 6), 8, 16, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_5_EMERALD_MISO_SOUP,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.MISOSOUP, 3), 6, 20, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFU_CRAFTSMAN_5_EMERALD_MOYASHI_ITAME,
				new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(TofuItems.MOYASHIITAME, 3), 6, 20, 0.1F, Optional.empty(), List.of())
		);
	}

	public static Holder.Reference<VillagerTrade> register(
			BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> resourceKey, VillagerTrade villagerTrade
	) {
		return context.register(resourceKey, villagerTrade);
	}
}
