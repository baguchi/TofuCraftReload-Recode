package baguchan.tofucraft.registry;

import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import baguchan.tofucraft.entity.projectile.ZundaArrowEntity;
import baguchan.tofucraft.item.BitternItem;
import baguchan.tofucraft.item.BugleItem;
import baguchan.tofucraft.item.FukumameItem;
import baguchan.tofucraft.item.NetherFukumameItem;
import baguchan.tofucraft.item.SoulFukumameItem;
import baguchan.tofucraft.item.SoymilkBottleItem;
import baguchan.tofucraft.item.TofuHoeItem;
import baguchan.tofucraft.item.TofuScoopItem;
import baguchan.tofucraft.item.TofuShieldItem;
import baguchan.tofucraft.item.TofuStickItem;
import baguchan.tofucraft.item.ZundaArrowItem;

import java.util.concurrent.Callable;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.Effects;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = "tofucraft", bus = EventBusSubscriber.Bus.MOD)
public class TofuItems {
	public static final Item TOFUKINU = new Item((new Item.Properties()).func_221540_a(TofuFoods.TOFU).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUMOMEN = new Item((new Item.Properties()).func_221540_a(TofuFoods.TOFU).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUISHI = new Item((new Item.Properties()).func_221540_a(TofuFoods.ISHITOFU).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUMETAL = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUDIAMOND = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUDIAMOND_NUGGET = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUHELL = new Item((new Item.Properties()).func_221540_a(TofuFoods.TOFUHELL).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUSOUL = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUGRILLED = new Item((new Item.Properties()).func_221540_a(TofuFoods.TOFUGRILLED).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUZUNDA = new Item((new Item.Properties()).func_221540_a(TofuFoods.TOFUZUNDA).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item BITTERN = (Item) new BitternItem((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SALT = (Item) new BitternItem((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUSCOOP = (Item) new TofuScoopItem((new Item.Properties()).func_200917_a(1).func_200918_c(264).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUSTICK = (Item) new TofuStickItem((new Item.Properties()).func_200917_a(1).func_200918_c(64).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item FUKUMAME = (Item) new FukumameItem((new Item.Properties()).func_200917_a(1).func_200918_c(64).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item NETHER_FUKUMAME = (Item) new NetherFukumameItem((new Item.Properties()).func_200917_a(1).func_200918_c(64).func_200916_a(TofuItemGroup.TOFUCRAFT), false);

	public static final Item INFERNO_NETHER_FUKUMAME = (Item) new NetherFukumameItem((new Item.Properties()).func_200917_a(1).func_200918_c(64).func_200916_a(TofuItemGroup.TOFUCRAFT), true);

	public static final Item SOUL_FUKUMAME = (Item) new SoulFukumameItem((new Item.Properties()).func_200917_a(1).func_200918_c(64).func_208103_a(Rarity.UNCOMMON).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ZUNDA_ARROW = (Item) new ZundaArrowItem((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SEEDS_SOYBEANS = (Item) new BlockNamedItem(TofuBlocks.SOYBEAN, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SEEDS_SOYBEANS_NETHER = (Item) new BlockNamedItem(TofuBlocks.SOYBEAN_NETHER, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SEEDS_SOYBEANS_SOUL = (Item) new BlockNamedItem(TofuBlocks.SOYBEAN_SOUL, (new Item.Properties()).func_208103_a(Rarity.UNCOMMON).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYBEAN_PARCHED = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item KINAKO = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item EDAMAME = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item BOILED_EDAMAME = new Item((new Item.Properties()).func_221540_a(TofuFoods.BOILED_EDAMAME).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item LEEK = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ZUNDA = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ZUNDAMA = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ZUNDARUBY = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFU_HAMBURG_RAW = new Item((new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFU_HAMBURG = new Item((new Item.Properties()).func_221540_a(TofuFoods.TOFU_HAMBURG).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item RAW_TOFU_FISH = new Item((new Item.Properties()).func_221540_a(TofuFoods.RAW_TOFUFISH).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item COOKED_TOFU_FISH = new Item((new Item.Properties()).func_221540_a(TofuFoods.COOKED_TOFUFISH).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUCOOKIE = new Item((new Item.Properties()).func_221540_a(TofuFoods.TOFUCOOKIE).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYSTICK = new Item((new Item.Properties()).func_221540_a(TofuFoods.SOYSTICK).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SALTYMELON = new Item((new Item.Properties()).func_221540_a(TofuFoods.SALTYMELON).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYMILK = (Item) new SoymilkBottleItem(Effects.field_76428_l, Effects.field_76444_x, (new Item.Properties()).func_200917_a(16).func_200919_a(Items.field_151069_bo).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYMILK_APPLE = (Item) new SoymilkBottleItem(Effects.field_76429_m, Effects.field_180152_w, (new Item.Properties()).func_200917_a(16).func_200919_a(Items.field_151069_bo).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYMILK_COCOA = (Item) new SoymilkBottleItem(Effects.field_76430_j, Effects.field_76429_m, (new Item.Properties()).func_200917_a(16).func_200919_a(Items.field_151069_bo).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYMILK_KINAKO = (Item) new SoymilkBottleItem(Effects.field_76424_c, Effects.field_76422_e, (new Item.Properties()).func_200917_a(16).func_200919_a(Items.field_151069_bo).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYMILK_PUDDING = (Item) new SoymilkBottleItem(Effects.field_76428_l, Effects.field_180152_w, (new Item.Properties()).func_200917_a(16).func_200919_a(Items.field_151069_bo).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOYMILK_PUMPKIN = (Item) new SoymilkBottleItem(Effects.field_76420_g, Effects.field_76422_e, (new Item.Properties()).func_200917_a(16).func_200919_a(Items.field_151069_bo).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item KINAKO_MANJU = new Item((new Item.Properties()).func_221540_a(TofuFoods.KINAKO_MANJU).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ZUNDA_MANJU = new Item((new Item.Properties()).func_221540_a(TofuFoods.ZUNDA_MANJU).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item NETHER_MANJU = new Item((new Item.Properties()).func_221540_a(TofuFoods.NETHER_MANJU).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOUL_MANJU = new Item((new Item.Properties()).func_221540_a(TofuFoods.SOUL_MANJU).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOY_CHOCOLATE = new Item((new Item.Properties()).func_221540_a(TofuFoods.SOY_CHOCOLATE).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUNIAN_SOY_CHOCOLATE = new Item((new Item.Properties()).func_221540_a(TofuFoods.SOY_CHOCOLATE).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item BUCKET_SOYMILK = (Item) new BucketItem(() -> TofuFluids.SOYMILK, (new Item.Properties()).func_200919_a(Items.field_151133_ar).func_200917_a(1).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item BUCKET_SOYMILK_NETHER = (Item) new BucketItem(() -> TofuFluids.SOYMILK_HELL, (new Item.Properties()).func_200919_a(Items.field_151133_ar).func_200917_a(1).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item BUCKET_SOYMILK_SOUL = (Item) new BucketItem(() -> TofuFluids.SOYMILK_SOUL, (new Item.Properties()).func_200919_a(Items.field_151133_ar).func_200917_a(1).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUFISH_BUCKET = (Item) new FishBucketItem(() -> TofuEntityTypes.TOFUFISH, () -> Fluids.field_204546_a, (new Item.Properties()).func_200919_a(Items.field_151133_ar).func_200917_a(1).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUFISH_SOYMILK_BUCKET = (Item) new FishBucketItem(() -> TofuEntityTypes.TOFUFISH, () -> TofuFluids.SOYMILK, (new Item.Properties()).func_200919_a(Items.field_151133_ar).func_200917_a(1).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item BUCKET_BITTERN = (Item) new BucketItem(() -> TofuFluids.BITTERN, (new Item.Properties()).func_200919_a(Items.field_151133_ar).func_200917_a(1).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item KINUSWORD = (Item) new SwordItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item KINUAXE = (Item) new AxeItem(TofuItemTier.KINU, 0.0F, -2.25F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item KINUPICKAXE = (Item) new PickaxeItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item KINUSHOVEL = (Item) new ShovelItem(TofuItemTier.KINU, 0.0F, -2.2F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item MOMENSWORD = (Item) new SwordItem(TofuItemTier.MOMEN, 0, -2.2F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item MOMENAXE = (Item) new AxeItem(TofuItemTier.MOMEN, 1.0F, -2.5F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item MOMENPICKAXE = (Item) new PickaxeItem(TofuItemTier.MOMEN, 0, -2.25F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item MOMENSHOVEL = (Item) new ShovelItem(TofuItemTier.MOMEN, 0.0F, -2.25F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOLIDSWORD = (Item) new SwordItem(TofuItemTier.SOLID, 3, -2.3F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOLIDAXE = (Item) new AxeItem(TofuItemTier.SOLID, 7.0F, -2.9F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOLIDPICKAXE = (Item) new PickaxeItem(TofuItemTier.SOLID, 1, -2.9F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item SOLIDSHOVEL = (Item) new ShovelItem(TofuItemTier.SOLID, 1.5F, -2.9F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item METALSWORD = (Item) new SwordItem(TofuItemTier.METAL, 3, -2.3F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item METALAXE = (Item) new AxeItem(TofuItemTier.METAL, 6.0F, -3.0F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item METALPICKAXE = (Item) new PickaxeItem(TofuItemTier.METAL, 1, -2.9F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item METALSHOVEL = (Item) new ShovelItem(TofuItemTier.METAL, 1.5F, -2.9F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUDIAMONDSWORD = (Item) new SwordItem(TofuItemTier.TOFUDIAMOND, 3, -2.3F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUDIAMONDAXE = (Item) new AxeItem(TofuItemTier.TOFUDIAMOND, 6.0F, -3.0F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUDIAMONDPICKAXE = (Item) new PickaxeItem(TofuItemTier.TOFUDIAMOND, 1, -2.9F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUDIAMONDSHOVEL = (Item) new ShovelItem(TofuItemTier.TOFUDIAMOND, 1.5F, -2.9F, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_KINUHELMET = (Item) new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_KINUCHESTPLATE = (Item) new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_KINULEGGINS = (Item) new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlotType.LEGS, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_KINUBOOTS = (Item) new ArmorItem(TofuArmorMaterial.KINU, EquipmentSlotType.FEET, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_MOMENHELMET = (Item) new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_MOMENCHESTPLATE = (Item) new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_MOMENLEGGINS = (Item) new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlotType.LEGS, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_MOMENBOOTS = (Item) new ArmorItem(TofuArmorMaterial.MOMEN, EquipmentSlotType.FEET, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_SOLIDHELMET = (Item) new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_SOLIDCHESTPLATE = (Item) new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_SOLIDLEGGINS = (Item) new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlotType.LEGS, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_SOLIDBOOTS = (Item) new ArmorItem(TofuArmorMaterial.SOLID, EquipmentSlotType.FEET, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_METALHELMET = (Item) new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_METALCHESTPLATE = (Item) new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_METALLEGGINS = (Item) new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlotType.LEGS, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_METALBOOTS = (Item) new ArmorItem(TofuArmorMaterial.METAL, EquipmentSlotType.FEET, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_DIAMONDHELMET = (Item) new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_DIAMONDCHESTPLATE = (Item) new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_DIAMONDLEGGINS = (Item) new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item ARMOR_DIAMONDBOOTS = (Item) new ArmorItem(TofuArmorMaterial.DIAMOND, EquipmentSlotType.FEET, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUISHI_SHIELD = (Item) new TofuShieldItem((new Item.Properties()).func_200917_a(1).func_200918_c(324).func_200916_a(TofuItemGroup.TOFUCRAFT).setISTER(() -> baguchan.tofucraft.client.render.item.TofuShieldItemRender::new));

	public static final Item TOFUMETAL_SHIELD = (Item) new TofuShieldItem((new Item.Properties()).func_200917_a(1).func_200918_c(436).func_200916_a(TofuItemGroup.TOFUCRAFT).setISTER(() -> baguchan.tofucraft.client.render.item.TofuShieldItemRender::new));

	public static final Item TOFUHOE = (Item) new TofuHoeItem((new Item.Properties()).func_200917_a(1).func_200918_c(324).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item BUGLE = (Item) new BugleItem((new Item.Properties()).func_200917_a(1).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUNIAN_SPAWNEGG = (Item) new SpawnEggItem(TofuEntityTypes.TOFUNIAN, 15460584, 13291425, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TRAVELER_TOFUNIAN_SPAWNEGG = (Item) new SpawnEggItem(TofuEntityTypes.TRAVELER_TOFUNIAN, 15460584, 8763986, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUCOW_SPAWNEGG = (Item) new SpawnEggItem(TofuEntityTypes.TOFUCOW, 15460584, 10724259, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUFISH_SPAWNEGG = (Item) new SpawnEggItem(TofuEntityTypes.TOFUFISH, 15460584, 3817023, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUSLIME_SPAWNEGG = (Item) new SpawnEggItem(TofuEntityTypes.TOFUSLIME, 15460584, 3026478, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static final Item TOFUSPIDER_SPAWNEGG = (Item) new SpawnEggItem(TofuEntityTypes.TOFUSPIDER, 15460584, 3026478, (new Item.Properties()).func_200916_a(TofuItemGroup.TOFUCRAFT));

	public static void register(RegistryEvent.Register<Item> registry, Item item, String id) {
		if (item instanceof BlockItem)
			Item.field_179220_a.put(((BlockItem) item).func_179223_d(), item);
		item.setRegistryName(new ResourceLocation("tofucraft", id));
		registry.getRegistry().register((IForgeRegistryEntry) item);
	}

	public static void register(RegistryEvent.Register<Item> registry, Item item) {
		if (item instanceof BlockItem && item.getRegistryName() == null) {
			item.setRegistryName(((BlockItem) item).func_179223_d().getRegistryName());
			Item.field_179220_a.put(((BlockItem) item).func_179223_d(), item);
		}
		registry.getRegistry().register((IForgeRegistryEntry) item);
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
		register(registry, TOFUSTICK, "tofustick");
		register(registry, FUKUMAME, "fukumame");
		register(registry, NETHER_FUKUMAME, "nether_fukumame");
		register(registry, INFERNO_NETHER_FUKUMAME, "inferno_nether_fukumame");
		register(registry, SOUL_FUKUMAME, "soul_fukumame");
		register(registry, ZUNDA_ARROW, "zunda_arrow");
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
		register(registry, TOFUFISH_BUCKET, "tofufish_bucket");
		register(registry, TOFUFISH_SOYMILK_BUCKET, "tofufish_soymilk_bucket");
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
		register(registry, TOFUISHI_SHIELD, "tofuishi_shield");
		register(registry, TOFUMETAL_SHIELD, "tofumetal_shield");
		register(registry, TOFUHOE, "tofuhoe");
		register(registry, BUGLE, "bugle");
		register(registry, TOFUNIAN_SPAWNEGG, "tofunian_spawnegg");
		register(registry, TRAVELER_TOFUNIAN_SPAWNEGG, "traveler_tofunian_spawnegg");
		register(registry, TOFUCOW_SPAWNEGG, "tofucow_spawnegg");
		register(registry, TOFUFISH_SPAWNEGG, "tofufish_spawnegg");
		register(registry, TOFUSLIME_SPAWNEGG, "tofuslime_spawnegg");
		register(registry, TOFUSPIDER_SPAWNEGG, "tofuspider_spawnegg");
		DispenserBlock.func_199774_a((IItemProvider) ZUNDA_ARROW, (IDispenseItemBehavior) new ProjectileDispenseBehavior() {
			protected ProjectileEntity func_82499_a(World p_82499_1_, IPosition p_82499_2_, ItemStack p_82499_3_) {
				ZundaArrowEntity arrowentity = new ZundaArrowEntity(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
				arrowentity.field_70251_a = AbstractArrowEntity.PickupStatus.ALLOWED;
				return (ProjectileEntity) arrowentity;
			}
		});
		DispenserBlock.func_199774_a((IItemProvider) FUKUMAME, (IDispenseItemBehavior) new ProjectileDispenseBehavior() {
			protected ProjectileEntity func_82499_a(World p_82499_1_, IPosition p_82499_2_, ItemStack p_82499_3_) {
				FukumameEntity fukumameEntity = new FukumameEntity(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
				return (ProjectileEntity) fukumameEntity;
			}

			public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
				ServerWorld serverWorld = p_82487_1_.func_197524_h();
				IPosition iposition = DispenserBlock.func_149939_a(p_82487_1_);
				Direction direction = (Direction) p_82487_1_.func_189992_e().func_177229_b((Property) DispenserBlock.field_176441_a);
				for (int i = 0; i < 5; i++) {
					ProjectileEntity projectileentity = func_82499_a((World) serverWorld, iposition, p_82487_2_);
					projectileentity.func_70186_c(direction.func_82601_c(), (direction.func_96559_d() + 0.1F), direction.func_82599_e(), func_82500_b(), func_82498_a());
					serverWorld.func_217376_c((Entity) projectileentity);
				}
				p_82487_2_.func_96631_a(1, serverWorld.func_201674_k(), (ServerPlayerEntity) null);
				return p_82487_2_;
			}
		});
		DispenserBlock.func_199774_a((IItemProvider) NETHER_FUKUMAME, (IDispenseItemBehavior) new ProjectileDispenseBehavior() {
			protected ProjectileEntity func_82499_a(World p_82499_1_, IPosition p_82499_2_, ItemStack p_82499_3_) {
				NetherFukumameEntity fukumameEntity = new NetherFukumameEntity(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
				return (ProjectileEntity) fukumameEntity;
			}

			public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
				ServerWorld serverWorld = p_82487_1_.func_197524_h();
				IPosition iposition = DispenserBlock.func_149939_a(p_82487_1_);
				Direction direction = (Direction) p_82487_1_.func_189992_e().func_177229_b((Property) DispenserBlock.field_176441_a);
				for (int i = 0; i < 5; i++) {
					ProjectileEntity projectileentity = func_82499_a((World) serverWorld, iposition, p_82487_2_);
					projectileentity.func_70186_c(direction.func_82601_c(), (direction.func_96559_d() + 0.1F), direction.func_82599_e(), func_82500_b(), func_82498_a());
					serverWorld.func_217376_c((Entity) projectileentity);
				}
				p_82487_2_.func_96631_a(1, serverWorld.func_201674_k(), (ServerPlayerEntity) null);
				return p_82487_2_;
			}
		});
		DispenserBlock.func_199774_a((IItemProvider) INFERNO_NETHER_FUKUMAME, (IDispenseItemBehavior) new ProjectileDispenseBehavior() {
			protected ProjectileEntity func_82499_a(World p_82499_1_, IPosition p_82499_2_, ItemStack p_82499_3_) {
				NetherFukumameEntity fukumameEntity = new NetherFukumameEntity(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
				fukumameEntity.func_70015_d(10);
				return (ProjectileEntity) fukumameEntity;
			}

			public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
				ServerWorld serverWorld = p_82487_1_.func_197524_h();
				IPosition iposition = DispenserBlock.func_149939_a(p_82487_1_);
				Direction direction = (Direction) p_82487_1_.func_189992_e().func_177229_b((Property) DispenserBlock.field_176441_a);
				for (int i = 0; i < 5; i++) {
					ProjectileEntity projectileentity = func_82499_a((World) serverWorld, iposition, p_82487_2_);
					projectileentity.func_70186_c(direction.func_82601_c(), (direction.func_96559_d() + 0.1F), direction.func_82599_e(), func_82500_b(), func_82498_a());
					serverWorld.func_217376_c((Entity) projectileentity);
				}
				p_82487_2_.func_96631_a(1, serverWorld.func_201674_k(), (ServerPlayerEntity) null);
				return p_82487_2_;
			}
		});
		DispenserBlock.func_199774_a((IItemProvider) SOUL_FUKUMAME, (IDispenseItemBehavior) new ProjectileDispenseBehavior() {
			protected ProjectileEntity func_82499_a(World p_82499_1_, IPosition p_82499_2_, ItemStack p_82499_3_) {
				SoulFukumameEntity fukumameEntity = new SoulFukumameEntity(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
				return (ProjectileEntity) fukumameEntity;
			}

			public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
				ServerWorld serverWorld = p_82487_1_.func_197524_h();
				IPosition iposition = DispenserBlock.func_149939_a(p_82487_1_);
				Direction direction = (Direction) p_82487_1_.func_189992_e().func_177229_b((Property) DispenserBlock.field_176441_a);
				for (int i = 0; i < 5; i++) {
					ProjectileEntity projectileentity = func_82499_a((World) serverWorld, iposition, p_82487_2_);
					projectileentity.func_70186_c(direction.func_82601_c(), (direction.func_96559_d() + 0.1F), direction.func_82599_e(), func_82500_b(), func_82498_a());
					serverWorld.func_217376_c((Entity) projectileentity);
				}
				p_82487_2_.func_96631_a(1, serverWorld.func_201674_k(), (ServerPlayerEntity) null);
				return p_82487_2_;
			}
		});
	}
}
