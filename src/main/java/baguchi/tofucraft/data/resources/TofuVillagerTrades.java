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
import net.minecraft.tags.PotionTags;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
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


	public static final ResourceKey<VillagerTrade> TOFUNIAN_TREAVELER_SOYBEAN_ZUNDA_RUBY = resourceKey("tofunian_traveler/buying/soybean_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_TREAVELER_SOYMILK_ZUNDA_RUBY = resourceKey("tofunian_traveler/buying/soymilk_zunda_ruby");
	public static final ResourceKey<VillagerTrade> TOFUNIAN_TREAVELER_EDAMAME_ZUNDA_RUBY = resourceKey("tofunian_traveler/buying/edamame_zunda_ruby");


	public static ResourceKey<VillagerTrade> resourceKey(String path) {
		return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, path));
	}

	public static void bootstrap(BootstrapContext<VillagerTrade> context) {
		HolderGetter<Item> items = context.lookup(Registries.ITEM);
		Optional<HolderSet<Enchantment>> enchantmentsForTradedEquipment = context.lookup(Registries.ENCHANTMENT)
				.get(EnchantmentTags.ON_TRADED_EQUIPMENT)
				.map(named -> (HolderSet<Enchantment>) named);
		Optional<HolderSet<Enchantment>> enchantmentsForBooks = context.lookup(Registries.ENCHANTMENT)
				.get(EnchantmentTags.TRADEABLE)
				.map(named -> (HolderSet<Enchantment>) named);
		Optional<HolderSet<Enchantment>> doubleTradePrice = context.lookup(Registries.ENCHANTMENT)
				.get(EnchantmentTags.DOUBLE_TRADE_PRICE)
				.map(named -> (HolderSet<Enchantment>) named);
		Optional<HolderSet<Potion>> potionsForTippedArrows = context.lookup(Registries.POTION).get(PotionTags.TRADEABLE).map(named -> (HolderSet<Potion>) named);
		HolderGetter<VillagerType> villagerVariants = context.lookup(Registries.VILLAGER_TYPE);
		register(
				context,
				TOFUNIAN_FARMER_1_SOYBEAN_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SEEDS_SOYBEANS.get(), 24), new ItemStack(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_1_LEEK_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.LEEK.get(), 32), new ItemStack(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_1_ZUNDA_RUBY_GRILLED_TOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStack(TofuItems.TOFUGRILLED, 9), 16, 2, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_FARMER_2_KINAKO_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.KINAKO.get(), 16), new ItemStack(TofuItems.ZUNDARUBY), 16, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_2_SALT_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SALT.get(), 24), new ItemStack(TofuItems.ZUNDARUBY), 16, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_2_ZUNDA_RUBY_TOFU_COOKIE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.TOFUCOOKIE, 14), 16, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_3_NATTO_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.NATTO.get(), 12), new ItemStack(TofuItems.ZUNDARUBY), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_3_MISO_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.MISO.get(), 12), new ItemStack(TofuItems.ZUNDARUBY), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_3_ZUNDA_RUBY_MISO_SOUP,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuItems.MISOSOUP, 2), 8, 12, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_4_ZUNDA_RUBY_STRAWBERRY_TOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuItems.TOFUSTRAWBERRY, 12), 8, 15, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_4_ZUNDA_RUBY_SESAME_TOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuItems.TOFUSESAME, 12), 8, 15, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_FARMER_5_ZUNDA_RUBY_TOFU_CAKE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuBlocks.TOFUCAKE), 3, 20, 0.1F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SMITH_1_TOFU_ISHI_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.TOFUISHI.get(), 32), new ItemStack(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_SWORD,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStack(TofuItems.TOFU_METAL_SWORD), 10, 3
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_SWORD.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_1_ZUNDA_RUBY_TOFU_METAL_AXE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.TOFU_METAL_AXE), 10, 3
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_AXE.get()))
		);

		register(
				context,
				TOFUNIAN_SMITH_2_TOFU_METAL_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.TOFUMETAL.get(), 18), new ItemStack(TofuItems.ZUNDARUBY), 16, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_PICKAXE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.TOFU_METAL_PICKAXE), 10, 6
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_PICKAXE.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_2_ZUNDA_RUBY_TOFU_METAL_SHOVEL,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStack(TofuItems.TOFU_METAL_SHOVEL), 10, 6
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_SHOVEL.get()))
		);


		register(
				context,
				TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_SPEAR,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStack(TofuItems.TOFU_METAL_SPEAR), 10, 12
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_SPEAR.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_3_ZUNDA_RUBY_TOFU_METAL_HOE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStack(TofuItems.TOFU_METAL_HOE), 10, 12
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_HOE.get()))
		);

		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_HELMET,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuItems.TOFU_METAL_HELMET), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_HELMET.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_CHESTPLATE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 5), new ItemStack(TofuItems.TOFU_METAL_CHESTPLATE), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_CHESTPLATE.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_LEGGINGS,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 4), new ItemStack(TofuItems.TOFU_METAL_LEGGINGS), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_LEGGINGS.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_4_ZUNDA_RUBY_TOFU_METAL_BOOTS,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.TOFU_METAL_BOOTS), 10, 16
						, 0.1F,
						Optional.empty(),
						enchantedItem(items, enchantmentsForTradedEquipment, TofuItems.TOFU_METAL_BOOTS.get()))
		);
		register(
				context,
				TOFUNIAN_SMITH_5_ZUNDA_RUBY_TEMPLATE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 14), new ItemStack(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE), 4, 22
						, 0.1F,
						Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SMITH_5_ZUNDA_RUBY_ZUNDA_ARROW,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuItems.ZUNDA_ARROW, 16), 12, 22
						, 0.1F,
						Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SOY_WORKER_1_SOYBEAN_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SEEDS_SOYBEANS.get(), 24), new ItemStack(TofuItems.ZUNDARUBY), 16, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_1_ZUNDA_RUBY_SOYMILK,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStack(TofuItems.SOYMILK_BOTTLE, 3), 2, 2, 0.05F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SOY_WORKER_2_BOTTLE_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(Items.GLASS_BOTTLE, 8), new ItemStack(TofuItems.ZUNDARUBY), 16, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_SOY_SAUCE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.BOTTLE_SOYSAUSE, 5), 12, 5, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_2_ZUNDA_RUBY_YUDOFU,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 1), new ItemStack(TofuItems.YUDOFU, 3), 12, 6, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_COCOA,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.SOYMILK_COCOA_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_PUDDING,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.SOYMILK_PUDDING_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_PUMPKIN,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.SOYMILK_PUMPKIN_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_3_ZUNDA_RUBY_SOYMILK_HONEY,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.SOYMILK_HONEY_BOTTLE, 3), 8, 10, 0.1F, Optional.empty(), List.of())
		);

		register(
				context,
				TOFUNIAN_SOY_WORKER_4_ZUNDA_RUBY_SOYMILK_RAMUNE,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 2), new ItemStack(TofuItems.SOYMILK_RAMUNE_BOTTLE, 3), 8, 15, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_STRAWBERRY,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuItems.SOYMILK_STRAWBERRY_BOTTLE, 5), 6, 20, 0.1F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_SOY_WORKER_5_ZUNDA_RUBY_SOYMILK_TEA,
				new VillagerTrade(new TradeCost(TofuItems.ZUNDARUBY.get(), 3), new ItemStack(TofuItems.SOYMILK_TEA_BOTTLE, 5), 6, 20, 0.1F, Optional.empty(), List.of())
		);


		register(
				context,
				TOFUNIAN_TREAVELER_SOYBEAN_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SEEDS_SOYBEANS.get(), 24), new ItemStack(TofuItems.ZUNDARUBY), 6, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_TREAVELER_SOYMILK_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.SOYMILK_BOTTLE.get(), 3), new ItemStack(TofuItems.ZUNDARUBY), 4, 2, 0.05F, Optional.empty(), List.of())
		);
		register(
				context,
				TOFUNIAN_TREAVELER_EDAMAME_ZUNDA_RUBY,
				new VillagerTrade(new TradeCost(TofuItems.EDAMAME.get(), 24), new ItemStack(TofuItems.ZUNDARUBY), 4, 2, 0.05F, Optional.empty(), List.of())
		);
	}

	public static Holder.Reference<VillagerTrade> register(
			BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> resourceKey, VillagerTrade villagerTrade
	) {
		return context.register(resourceKey, villagerTrade);
	}
}
