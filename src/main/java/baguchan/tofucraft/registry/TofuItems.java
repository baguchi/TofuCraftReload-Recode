package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.item.BitternItem;
import baguchan.tofucraft.item.BugleItem;
import baguchan.tofucraft.item.SoymilkBottleItem;
import baguchan.tofucraft.item.TofuScoopItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuItems {
	public static final Item TOFUKINU = new Item((new Item.Properties()).food(TofuFoods.TOFU).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUMOMEN = new Item((new Item.Properties()).food(TofuFoods.TOFU).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUISHI = new Item((new Item.Properties()).food(TofuFoods.ISHITOFU).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUMETAL = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUDIAMOND = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUDIAMOND_NUGGET = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUHELL = new Item((new Item.Properties()).food(TofuFoods.TOFUHELL).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUSOUL = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUGRILLED = new Item((new Item.Properties()).food(TofuFoods.TOFUGRILLED).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUZUNDA = new Item((new Item.Properties()).food(TofuFoods.TOFUZUNDA).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BITTERN = new BitternItem((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SALT = new BitternItem((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SEEDS_SOYBEANS = new ItemNameBlockItem(TofuBlocks.SOYBEAN, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SEEDS_SOYBEANS_NETHER = new ItemNameBlockItem(TofuBlocks.SOYBEAN_NETHER, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SEEDS_SOYBEANS_SOUL = new ItemNameBlockItem(TofuBlocks.SOYBEAN_SOUL, (new Item.Properties()).rarity(Rarity.UNCOMMON).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOYBEAN_PARCHED = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item KINAKO = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item EDAMAME = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BOILED_EDAMAME = new Item((new Item.Properties()).food(TofuFoods.BOILED_EDAMAME).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item LEEK = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ZUNDA = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ZUNDAMA = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ZUNDARUBY = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_HAMBURG_RAW = new Item((new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_HAMBURG = new Item((new Item.Properties()).food(TofuFoods.TOFU_HAMBURG).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item RAW_TOFU_FISH = new Item((new Item.Properties()).food(TofuFoods.RAW_TOFUFISH).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item COOKED_TOFU_FISH = new Item((new Item.Properties()).food(TofuFoods.COOKED_TOFUFISH).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUCOOKIE = new Item((new Item.Properties()).food(TofuFoods.TOFUCOOKIE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOYSTICK = new Item((new Item.Properties()).food(TofuFoods.SOYSTICK).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SALTYMELON = new Item((new Item.Properties()).food(TofuFoods.SALTYMELON).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYMILK = new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOYMILK_APPLE = new SoymilkBottleItem(MobEffects.DAMAGE_RESISTANCE, MobEffects.ABSORPTION, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOYMILK_COCOA = new SoymilkBottleItem(MobEffects.JUMP, MobEffects.MOVEMENT_SPEED, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOYMILK_KINAKO = new SoymilkBottleItem(MobEffects.MOVEMENT_SPEED, MobEffects.DIG_SPEED, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOYMILK_PUDDING = new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOYMILK_PUMPKIN = new SoymilkBottleItem(MobEffects.DAMAGE_BOOST, MobEffects.DIG_SPEED, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item KINAKO_MANJU = new Item((new Item.Properties()).food(TofuFoods.KINAKO_MANJU).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ZUNDA_MANJU = new Item((new Item.Properties()).food(TofuFoods.ZUNDA_MANJU).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item NETHER_MANJU = new Item((new Item.Properties()).food(TofuFoods.NETHER_MANJU).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOUL_MANJU = new Item((new Item.Properties()).food(TofuFoods.SOUL_MANJU).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item SOY_CHOCOLATE = new Item((new Item.Properties()).food(TofuFoods.SOY_CHOCOLATE).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUNIAN_SOY_CHOCOLATE = new Item((new Item.Properties()).food(TofuFoods.SOY_CHOCOLATE).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item BUCKET_SOYMILK = new BucketItem(() -> TofuFluids.SOYMILK, (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BUCKET_SOYMILK_NETHER = new BucketItem(() -> TofuFluids.SOYMILK_HELL, (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BUCKET_SOYMILK_SOUL = new BucketItem(() -> TofuFluids.SOYMILK_SOUL, (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	//public static final Item TOFUFISH_BUCKET = new FishBucketItem(() -> TofuEntityTypes.TOFUFISH, () -> Fluids.WATER, (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	//public static final Item TOFUFISH_SOYMILK_BUCKET = new FishBucketItem(() -> TofuEntityTypes.TOFUFISH, () -> TofuFluids.SOYMILK, (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BUCKET_BITTERN = new BucketItem(() -> TofuFluids.BITTERN, (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item KINUSWORD = new SwordItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item KINUAXE = new AxeItem(TofuItemTier.KINU, 0.0F, -2.25F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item KINUPICKAXE = new PickaxeItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item KINUSHOVEL = new ShovelItem(TofuItemTier.KINU, 0.0F, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item MOMENSWORD = new SwordItem(TofuItemTier.MOMEN, 0, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item MOMENAXE = new AxeItem(TofuItemTier.MOMEN, 1.0F, -2.5F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item MOMENPICKAXE = new PickaxeItem(TofuItemTier.MOMEN, 0, -2.25F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item MOMENSHOVEL = new ShovelItem(TofuItemTier.MOMEN, 0.0F, -2.25F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item SOLIDSWORD = new SwordItem(TofuItemTier.SOLID, 3, -2.3F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOLIDAXE = new AxeItem(TofuItemTier.SOLID, 7.0F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOLIDPICKAXE = new PickaxeItem(TofuItemTier.SOLID, 1, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOLIDSHOVEL = new ShovelItem(TofuItemTier.SOLID, 1.5F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item METALSWORD = new SwordItem(TofuItemTier.METAL, 3, -2.3F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item METALAXE = new AxeItem(TofuItemTier.METAL, 6.0F, -3.0F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item METALPICKAXE = new PickaxeItem(TofuItemTier.METAL, 1, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item METALSHOVEL = new ShovelItem(TofuItemTier.METAL, 1.5F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUDIAMONDSWORD = new SwordItem(TofuItemTier.TOFUDIAMOND, 3, -2.3F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUDIAMONDAXE = new AxeItem(TofuItemTier.TOFUDIAMOND, 6.0F, -3.0F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUDIAMONDPICKAXE = new PickaxeItem(TofuItemTier.TOFUDIAMOND, 1, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUDIAMONDSHOVEL = new ShovelItem(TofuItemTier.TOFUDIAMOND, 1.5F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_KINUHELMET = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_KINUCHESTPLATE = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_KINULEGGINS = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_KINUBOOTS = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_MOMENHELMET = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_MOMENCHESTPLATE = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_MOMENLEGGINS = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_MOMENBOOTS = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_SOLIDHELMET = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_SOLIDCHESTPLATE = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_SOLIDLEGGINS = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_SOLIDBOOTS = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_METALHELMET = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_METALCHESTPLATE = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_METALLEGGINS = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_METALBOOTS = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_DIAMONDHELMET = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_DIAMONDCHESTPLATE = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_DIAMONDLEGGINS = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_DIAMONDBOOTS = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item BUGLE = new BugleItem((new Item.Properties()).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUSCOOP = new TofuScoopItem((new Item.Properties()).stacksTo(1).durability(264).tab(TofuItemGroup.TOFUCRAFT));


	public static void register(RegistryEvent.Register<Item> registry, Item item, String id) {
		if (item instanceof BlockItem)
			Item.BY_BLOCK.put(((BlockItem) item).getBlock(), item);
		item.setRegistryName(new ResourceLocation("tofucraft", id));
		registry.getRegistry().register(item);
	}

	public static void register(RegistryEvent.Register<Item> registry, Item item) {
		if (item instanceof BlockItem && item.getRegistryName() == null) {
			item.setRegistryName(((BlockItem) item).getBlock().getRegistryName());
			Item.BY_BLOCK.put(((BlockItem) item).getBlock(), item);
		}
		registry.getRegistry().register(item);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> registry) {
		register(registry, TOFUKINU, "tofukinu");
		register(registry, TOFUMOMEN, "tofumomen");
		register(registry, TOFUISHI, "tofuishi");
		register(registry, TOFUMETAL, "tofumetal");
		register(registry, TOFUDIAMOND, "tofudiamond");
		register(registry, TOFUDIAMOND_NUGGET, "tofudiamondnugget");
		register(registry, TOFUHELL, "tofuhell");
		register(registry, TOFUSOUL, "tofusoul");
		register(registry, TOFUGRILLED, "tofugrilled");
		register(registry, TOFUZUNDA, "tofuzunda");
		register(registry, BITTERN, "bittern_bottle");
		register(registry, SALT, "salt");
		register(registry, TOFUSCOOP, "tofuscoop");
		register(registry, SEEDS_SOYBEANS, "seeds_soybeans");
		register(registry, SEEDS_SOYBEANS_NETHER, "seeds_soybeans_nether");
		register(registry, SEEDS_SOYBEANS_SOUL, "seeds_soybeans_soul");
		register(registry, SOYBEAN_PARCHED, "soybeans_parched");
		register(registry, KINAKO, "kinako");
		register(registry, EDAMAME, "edamame");
		register(registry, BOILED_EDAMAME, "edamame_boild");
		register(registry, LEEK, "leek");
		register(registry, ZUNDA, "zunda");
		register(registry, ZUNDAMA, "zundama");
		register(registry, ZUNDARUBY, "zundaruby");
		register(registry, TOFU_HAMBURG_RAW, "tofuhamburg_raw");
		register(registry, TOFU_HAMBURG, "tofuhamburg");
		register(registry, RAW_TOFU_FISH, "raw_tofufish");
		register(registry, COOKED_TOFU_FISH, "cooked_tofufish");
		register(registry, TOFUCOOKIE, "tofucookie");
		register(registry, SOYSTICK, "soystick");
		register(registry, SALTYMELON, "saltymelon");
		register(registry, SOYMILK, "soymilk");
		register(registry, SOYMILK_APPLE, "soymilk_apple");
		register(registry, SOYMILK_COCOA, "soymilk_cocoa");
		register(registry, SOYMILK_KINAKO, "soymilk_kinako");
		register(registry, SOYMILK_PUDDING, "soymilk_pudding");
		register(registry, SOYMILK_PUMPKIN, "soymilk_pumpkin");
		register(registry, KINAKO_MANJU, "kinakomanju");
		register(registry, ZUNDA_MANJU, "zundamanju");
		register(registry, NETHER_MANJU, "nethermanju");
		register(registry, SOUL_MANJU, "soulmanju");
		register(registry, SOY_CHOCOLATE, "soy_chocolate");
		register(registry, TOFUNIAN_SOY_CHOCOLATE, "tofunian_soy_chocolate");
		register(registry, BUCKET_SOYMILK, "bucket_soymilk");
		register(registry, BUCKET_SOYMILK_NETHER, "bucket_soymilk_nether");
		register(registry, BUCKET_SOYMILK_SOUL, "bucket_soymilk_soul");
		register(registry, BUCKET_BITTERN, "bucket_bittern");
		register(registry, KINUSWORD, "swordkinu");
		register(registry, KINUAXE, "toolkinuaxe");
		register(registry, KINUPICKAXE, "toolkinupickaxe");
		register(registry, KINUSHOVEL, "toolkinushovel");
		register(registry, MOMENSWORD, "swordmomen");
		register(registry, MOMENAXE, "toolmomenaxe");
		register(registry, MOMENPICKAXE, "toolmomenpickaxe");
		register(registry, MOMENSHOVEL, "toolmomenshovel");
		register(registry, SOLIDSWORD, "swordsolid");
		register(registry, SOLIDAXE, "toolsolidaxe");
		register(registry, SOLIDPICKAXE, "toolsolidpickaxe");
		register(registry, SOLIDSHOVEL, "toolsolidshovel");
		register(registry, METALSWORD, "swordmetal");
		register(registry, METALAXE, "toolmetalaxe");
		register(registry, METALPICKAXE, "toolmetalpickaxe");
		register(registry, METALSHOVEL, "toolmetalshovel");
		register(registry, TOFUDIAMONDSWORD, "sworddiamond");
		register(registry, TOFUDIAMONDAXE, "tooldiamondaxe");
		register(registry, TOFUDIAMONDPICKAXE, "tooldiamondpickaxe");
		register(registry, TOFUDIAMONDSHOVEL, "tooldiamondshovel");
		register(registry, ARMOR_KINUHELMET, "armorkinuhelmet");
		register(registry, ARMOR_KINUCHESTPLATE, "armorkinuchestplate");
		register(registry, ARMOR_KINULEGGINS, "armorkinuleggins");
		register(registry, ARMOR_KINUBOOTS, "armorkinuboots");
		register(registry, ARMOR_MOMENHELMET, "armormomenhelmet");
		register(registry, ARMOR_MOMENCHESTPLATE, "armormomenchestplate");
		register(registry, ARMOR_MOMENLEGGINS, "armormomenleggins");
		register(registry, ARMOR_MOMENBOOTS, "armormomenboots");
		register(registry, ARMOR_SOLIDHELMET, "armorsolidhelmet");
		register(registry, ARMOR_SOLIDCHESTPLATE, "armorsolidchestplate");
		register(registry, ARMOR_SOLIDLEGGINS, "armorsolidleggins");
		register(registry, ARMOR_SOLIDBOOTS, "armorsolidboots");
		register(registry, ARMOR_METALHELMET, "armormetalhelmet");
		register(registry, ARMOR_METALCHESTPLATE, "armormetalchestplate");
		register(registry, ARMOR_METALLEGGINS, "armormetalleggins");
		register(registry, ARMOR_METALBOOTS, "armormetalboots");
		register(registry, ARMOR_DIAMONDHELMET, "armordiamondhelmet");
		register(registry, ARMOR_DIAMONDCHESTPLATE, "armordiamondchestplate");
		register(registry, ARMOR_DIAMONDLEGGINS, "armordiamondleggins");
		register(registry, ARMOR_DIAMONDBOOTS, "armordiamondboots");
		register(registry, BUGLE, "bugle");
	}
}
