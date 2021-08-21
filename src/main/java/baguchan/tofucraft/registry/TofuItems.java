package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.item.*;
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

	public static final Item BUCKET_SOYMILK = new BucketItem(() -> TofuFluids.SOYMILK, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BUCKET_SOYMILK_NETHER = new BucketItem(() -> TofuFluids.SOYMILK_HELL, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BUCKET_SOYMILK_SOUL = new BucketItem(() -> TofuFluids.SOYMILK_SOUL, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	//public static final Item TOFUFISH_BUCKET = new FishBucketItem(() -> TofuEntityTypes.TOFUFISH, () -> Fluids.WATER, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	//public static final Item TOFUFISH_SOYMILK_BUCKET = new FishBucketItem(() -> TofuEntityTypes.TOFUFISH, () -> TofuFluids.SOYMILK, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item BUCKET_BITTERN = new BucketItem(() -> TofuFluids.BITTERN, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFU_KINU_SWORD = new SwordItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_KINU_AXE = new AxeItem(TofuItemTier.KINU, 0.0F, -2.25F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_KINU_PICKAXE = new PickaxeItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_KINU_SHOVEL = new ShovelItem(TofuItemTier.KINU, 0.0F, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFU_MOMEN_SWORD = new SwordItem(TofuItemTier.MOMEN, 0, -2.2F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_MOMEN_AXE = new AxeItem(TofuItemTier.MOMEN, 1.0F, -2.5F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_MOMEN_PICKAXE = new PickaxeItem(TofuItemTier.MOMEN, 0, -2.25F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_MOMEN_SHOVEL = new ShovelItem(TofuItemTier.MOMEN, 0.0F, -2.25F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFU_SOLID_SWORD = new SwordItem(TofuItemTier.SOLID, 3, -2.3F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_SOLID_AXE = new AxeItem(TofuItemTier.SOLID, 7.0F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_SOLID_PICKAXE = new PickaxeItem(TofuItemTier.SOLID, 1, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_SOLID_SHOVEL = new ShovelItem(TofuItemTier.SOLID, 1.5F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFU_METAL_SWORD = new SwordItem(TofuItemTier.METAL, 3, -2.3F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_METAL_AXE = new AxeItem(TofuItemTier.METAL, 6.0F, -3.0F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_METAL_PICKAXE = new PickaxeItem(TofuItemTier.METAL, 1, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_METAL_SHOVEL = new ShovelItem(TofuItemTier.METAL, 1.5F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFU_DIAMOND_SWORD = new SwordItem(TofuItemTier.TOFUDIAMOND, 3, -2.3F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_DIAMOND_AXE = new AxeItem(TofuItemTier.TOFUDIAMOND, 6.0F, -3.0F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_DIAMOND_PICKAXE = new PickaxeItem(TofuItemTier.TOFUDIAMOND, 1, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFU_DIAMOND_SHOVEL = new ShovelItem(TofuItemTier.TOFUDIAMOND, 1.5F, -2.9F, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_TOFU_KINUHELMET = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_KINUCHESTPLATE = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_KINULEGGINGS = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_KINUBOOTS = new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_TOFU_MOMENHELMET = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_MOMENCHESTPLATE = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_MOMENLEGGINGS = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_MOMENBOOTS = new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_TOFU_SOLIDHELMET = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_SOLIDCHESTPLATE = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_SOLIDLEGGINGS = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_SOLIDBOOTS = new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_TOFU_METALHELMET = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_METALCHESTPLATE = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_METALLEGGINGS = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_METALBOOTS = new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_TOFU_DIAMONDHELMET = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.HEAD, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_DIAMONDCHESTPLATE = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.CHEST, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_DIAMONDLEGGINGS = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.LEGS, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item ARMOR_TOFU_DIAMONDBOOTS = new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlot.FEET, (new Item.Properties()).tab(TofuItemGroup.TOFUCRAFT));

	public static final Item BUGLE = new BugleItem((new Item.Properties()).stacksTo(1).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUSCOOP = new TofuScoopItem((new Item.Properties()).stacksTo(1).durability(264).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item TOFUSTICK = new TofuStickItem((new Item.Properties()).stacksTo(1).durability(264).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item FUKUMAME = new FukumameItem((new Item.Properties()).stacksTo(1).durability(64).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item NETHER_FUKUMAME = new NetherFukumameItem((new Item.Properties()).stacksTo(1).durability(64).tab(TofuItemGroup.TOFUCRAFT));
	public static final Item SOUL_FUKUMAME = new SoulFukumameItem((new Item.Properties()).stacksTo(1).durability(64).rarity(Rarity.UNCOMMON).tab(TofuItemGroup.TOFUCRAFT));


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

		register(registry, BUGLE, "bugle");
		register(registry, TOFUSCOOP, "tofuscoop");
		register(registry, TOFUSTICK, "tofustick");
		register(registry, FUKUMAME, "fukumame");
		register(registry, NETHER_FUKUMAME, "nether_fukumame");
		register(registry, SOUL_FUKUMAME, "soul_fukumame");

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
		register(registry, TOFU_KINU_SWORD, "tofu_kinu_sword");
		register(registry, TOFU_KINU_AXE, "tofu_kinu_axe");
		register(registry, TOFU_KINU_PICKAXE, "tofu_kinu_pickaxe");
		register(registry, TOFU_KINU_SHOVEL, "tofu_kinu_shovel");
		register(registry, TOFU_MOMEN_SWORD, "tofu_momen_sword");
		register(registry, TOFU_MOMEN_AXE, "tofu_momen_axe");
		register(registry, TOFU_MOMEN_PICKAXE, "tofu_momen_pickaxe");
		register(registry, TOFU_MOMEN_SHOVEL, "tofu_momen_shovel");
		register(registry, TOFU_SOLID_SWORD, "tofu_solid_sword");
		register(registry, TOFU_SOLID_AXE, "tofu_solid_axe");
		register(registry, TOFU_SOLID_PICKAXE, "tofu_solid_pickaxe");
		register(registry, TOFU_SOLID_SHOVEL, "tofu_solid_shovel");
		register(registry, TOFU_METAL_SWORD, "tofu_metal_sword");
		register(registry, TOFU_METAL_PICKAXE, "tofu_metal_axe");
		register(registry, TOFU_METAL_AXE, "tofu_metal_pickaxe");
		register(registry, TOFU_METAL_SHOVEL, "tofu_metal_shovel");
		register(registry, TOFU_DIAMOND_SWORD, "tofu_diamond_sword");
		register(registry, TOFU_DIAMOND_AXE, "tofu_diamond_axe");
		register(registry, TOFU_DIAMOND_PICKAXE, "tofu_diamond_pickaxe");
		register(registry, TOFU_DIAMOND_SHOVEL, "tofu_diamond_shovel");
		register(registry, ARMOR_TOFU_KINUHELMET, "tofu_kinu_helmet");
		register(registry, ARMOR_TOFU_KINUCHESTPLATE, "tofu_kinu_chestplate");
		register(registry, ARMOR_TOFU_KINULEGGINGS, "tofu_kinu_leggings");
		register(registry, ARMOR_TOFU_KINUBOOTS, "tofu_kinu_boots");
		register(registry, ARMOR_TOFU_MOMENHELMET, "tofu_momen_helmet");
		register(registry, ARMOR_TOFU_MOMENCHESTPLATE, "tofu_momen_chestplate");
		register(registry, ARMOR_TOFU_MOMENLEGGINGS, "tofu_momen_leggings");
		register(registry, ARMOR_TOFU_MOMENBOOTS, "tofu_momen_boots");
		register(registry, ARMOR_TOFU_SOLIDHELMET, "tofu_solid_helmet");
		register(registry, ARMOR_TOFU_SOLIDCHESTPLATE, "tofu_solid_chestplate");
		register(registry, ARMOR_TOFU_SOLIDLEGGINGS, "tofu_solid_leggings");
		register(registry, ARMOR_TOFU_SOLIDBOOTS, "tofu_solid_boots");
		register(registry, ARMOR_TOFU_METALHELMET, "tofu_metal_helmet");
		register(registry, ARMOR_TOFU_METALCHESTPLATE, "tofu_metal_chestplate");
		register(registry, ARMOR_TOFU_METALLEGGINGS, "tofu_metal_leggings");
		register(registry, ARMOR_TOFU_METALBOOTS, "tofu_metal_boots");
		register(registry, ARMOR_TOFU_DIAMONDHELMET, "tofu_diamond_helmet");
		register(registry, ARMOR_TOFU_DIAMONDCHESTPLATE, "tofu_diamond_chestplate");
		register(registry, ARMOR_TOFU_DIAMONDLEGGINGS, "tofu_diamond_leggings");
		register(registry, ARMOR_TOFU_DIAMONDBOOTS, "tofu_diamond_boots");
	}
}
