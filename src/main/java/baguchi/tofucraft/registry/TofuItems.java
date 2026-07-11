package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.tfenergy.TFEnergyData;
import baguchi.tofucraft.data.generator.tags.TofuCustomTags;
import baguchi.tofucraft.datamap.TofuHarden;
import baguchi.tofucraft.dispenser.DamageableProjectileDispenseBehavior;
import baguchi.tofucraft.item.ApricotItem;
import baguchi.tofucraft.item.BugleItem;
import baguchi.tofucraft.item.ChiliItem;
import baguchi.tofucraft.item.DescSoymilkBottleItem;
import baguchi.tofucraft.item.DishItem;
import baguchi.tofucraft.item.DoubleUsageSeedItem;
import baguchi.tofucraft.item.FukumameItem;
import baguchi.tofucraft.item.GhastFoodItem;
import baguchi.tofucraft.item.InfernoNetherFukumameItem;
import baguchi.tofucraft.item.KoujiBaseItem;
import baguchi.tofucraft.item.NattoCobWebItem;
import baguchi.tofucraft.item.NetherFukumameItem;
import baguchi.tofucraft.item.NourishmentItem;
import baguchi.tofucraft.item.RamuneSoymilkBottleItem;
import baguchi.tofucraft.item.RollingPinItem;
import baguchi.tofucraft.item.SaltFoodItem;
import baguchi.tofucraft.item.SeedAndRootItem;
import baguchi.tofucraft.item.SoulFukumameItem;
import baguchi.tofucraft.item.SoymilkBottleItem;
import baguchi.tofucraft.item.TFBatteryItem;
import baguchi.tofucraft.item.TofuBookItem;
import baguchi.tofucraft.item.UnstableZundamaItem;
import baguchi.tofucraft.item.ZundaIngotItem;
import baguchi.tofucraft.item.armor.BreakableTofuArmorItem;
import baguchi.tofucraft.item.armor.BreakableTofuBootsItem;
import baguchi.tofucraft.item.armor.TofuArmorItem;
import baguchi.tofucraft.item.block.DeferredBlockItem;
import baguchi.tofucraft.item.tool.TofuAxeItem;
import baguchi.tofucraft.item.tool.TofuHoeItem;
import baguchi.tofucraft.item.tool.TofuPickaxeItem;
import baguchi.tofucraft.item.tool.TofuScoopItem;
import baguchi.tofucraft.item.tool.TofuShearsItem;
import baguchi.tofucraft.item.tool.TofuShieldItem;
import baguchi.tofucraft.item.tool.TofuShovelItem;
import baguchi.tofucraft.item.tool.TofuStickItem;
import baguchi.tofucraft.item.tool.TofuSwordItem;
import baguchi.tofucraft.item.tool.ZundaAlloySwordItem;
import baguchi.tofucraft.item.tool.ZundaArrowItem;
import baguchi.tofucraft.item.tool.ZundaBowItem;
import baguchi.tofucraft.item.tool.ZundaMushroomOnAStickItem;
import baguchi.tofucraft.item.tool.bucket.BucketDispenseBehavior;
import baguchi.tofucraft.item.tool.bucket.TofuMetalBucketItem;
import baguchi.tofucraft.utils.RecipeHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class TofuItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TofuCraftReload.MODID);

	public static final DeferredHolder<Item, Item> TOFU_KINU = ITEMS.registerItem("tofu_kinu", (properties) -> new Item((properties).food(TofuFoods.TOFU, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN = ITEMS.registerItem("tofu_momen", (properties) -> new Item((properties).food(TofuFoods.TOFU, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_ISHI = ITEMS.registerItem("tofu_ishi", (properties) -> new Item((properties).food(TofuFoods.ISHITOFU)));
	public static final DeferredHolder<Item, Item> TOFU_METAL = ITEMS.registerItem("tofu_metal", (properties) -> new Item(properties.trimMaterial(TofuTrimMaterials.TOFU_METAL)));
	public static final DeferredHolder<Item, Item> TOFU_METAL_NUGGET = ITEMS.registerItem("tofu_metal_nugget", Item::new);
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND = ITEMS.registerItem("tofu_diamond", (properties) -> new Item(properties.trimMaterial(TofuTrimMaterials.TOFU_DIAMOND)));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_NUGGET = ITEMS.registerItem("tofu_diamond_nugget", Item::new);
	public static final DeferredHolder<Item, Item> TOFU_HELL = ITEMS.registerItem("tofu_hell", (properties) -> new Item((properties).food(TofuFoods.TOFUHELL, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_SOUL = ITEMS.registerItem("tofu_soul", (properties) -> new Item((properties).food(TofuFoods.TOFUSOUL, TofuConsumables.SOUL_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_GRILLED = ITEMS.registerItem("tofu_grilled", (properties) -> new Item((properties).food(TofuFoods.TOFUGRILLED, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_ZUNDA = ITEMS.registerItem("tofu_zunda", (properties) -> new Item((properties).food(TofuFoods.TOFUZUNDA, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_MISO = ITEMS.registerItem("tofu_miso", (properties) -> new Item((properties).food(TofuFoods.TOFUMISO, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_DRIED = ITEMS.registerItem("tofu_dried", (properties) -> new Item((properties).food(TofuFoods.TOFUDRIED, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_SMOKE = ITEMS.registerItem("smoketofu", (properties) -> new Item((properties).food(TofuFoods.TOFUSMOKE, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> SHUDOFU = ITEMS.registerItem("shudofu", (properties) -> new Item((properties).food(TofuFoods.SHUDOFU, TofuConsumables.COUGH)));

	public static final DeferredHolder<Item, Item> TOFU_SESAME = ITEMS.registerItem("tofu_sesame", (properties) -> new Item((properties).food(TofuFoods.TOFUSESAME, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_FRIED = ITEMS.registerItem("tofu_fried", (properties) -> new Item((properties).food(TofuFoods.TOFUFRIED, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_FRIED_POUCH = ITEMS.registerItem("tofu_fried_pouch", (properties) -> new Item((properties).food(TofuFoods.TOFUFRIED_POUCH, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_EGG = ITEMS.registerItem("tofu_egg", (properties) -> new Item((properties).food(TofuFoods.TOFU_EGG, TofuConsumables.FAST_FOOD)));


	public static final DeferredHolder<Item, Item> TOFU_ANNIN = ITEMS.registerItem("tofu_annin", (properties) -> new Item((properties).food(TofuFoods.TOFUANNIN, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> TOFU_STRAWBERRY = ITEMS.registerItem("tofu_strawberry", (properties) -> new Item((properties).food(TofuFoods.TOFUSTRAWBERRY, TofuConsumables.FAST_FOOD)));


	public static final DeferredHolder<Item, Item> TOFU_MINCED = ITEMS.registerItem("tofu_minced", (properties) -> new Item((properties).food(TofuFoods.TOFU)));


	public static final DeferredHolder<Item, Item> BITTERN_BOTTLE = ITEMS.registerItem("bittern_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> CRIMSON_BOTTLE = ITEMS.registerItem("crimson_fluid_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> WARPED_BOTTLE = ITEMS.registerItem("warped_fluid_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SHROOM_BOTTLE = ITEMS.registerItem("shroom_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SALT = ITEMS.registerItem("salt", Item::new);
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS = ITEMS.registerItem("seeds_soybeans", (properties) -> new DoubleUsageSeedItem(TofuBlocks.SOYBEAN, TofuBlocks.SPROUTS, (properties)));
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS_NETHER = ITEMS.registerItem("seeds_soybeans_nether", (properties) -> new DeferredBlockItem(TofuBlocks.SOYBEAN_NETHER, (properties)));
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS_SOUL = ITEMS.registerItem("seeds_soybeans_soul", (properties) -> new DeferredBlockItem(TofuBlocks.SOYBEAN_SOUL, (properties).rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS_PALE = ITEMS.registerItem("seeds_soybeans_pale", (properties) -> new DeferredBlockItem(TofuBlocks.SOYBEAN_PALE, (properties)));
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS_PALE_GLOW = ITEMS.registerItem("seeds_soybeans_pale_glow", (properties) -> new DeferredBlockItem(TofuBlocks.SOYBEAN_PALE, (properties)));

	public static final DeferredHolder<Item, Item> SOYBEAN_PARCHED = ITEMS.registerItem("soybeans_parched", Item::new);
	public static final DeferredHolder<Item, Item> KINAKO = ITEMS.registerItem("kinako", Item::new);
	public static final DeferredHolder<Item, Item> EDAMAME = ITEMS.registerItem("edamame", Item::new);
	public static final DeferredHolder<Item, Item> BOILED_EDAMAME = ITEMS.registerItem("edamame_boild", (properties) -> new Item((properties).food(TofuFoods.BOILED_EDAMAME)));
	public static final DeferredHolder<Item, Item> MINCEDPOTATO = ITEMS.registerItem("mincedpotato", Item::new);

	public static final DeferredHolder<Item, Item> SEEDS_CHILI = ITEMS.registerItem("seeds_chili", (properties) -> new DeferredBlockItem(TofuBlocks.CHILI_CROP, (properties)));
	public static final DeferredHolder<Item, Item> CHILI = ITEMS.registerItem("chili", (properties) -> new ChiliItem((properties).food(TofuFoods.CHILI, TofuConsumables.COUGH)));
	public static final DeferredHolder<Item, Item> DOUBANJIANG = ITEMS.registerItem("doubanjiang", Item::new);
	public static final DeferredHolder<Item, Item> MABODOFU = ITEMS.registerItem("mabodofu", (properties) -> new DishItem((properties).food(TofuFoods.MABODOFU, TofuConsumables.CHILI_FOOD).stacksTo(16).usingConvertsTo(Items.BOWL)));

	public static final DeferredHolder<Item, Item> FUKUMENI = ITEMS.registerItem("fukumeni", (properties) -> new Item((properties).food(TofuFoods.FUKUMENI)));
	public static final DeferredHolder<Item, Item> KOYADOFUSTEW = ITEMS.registerItem("koyadofustew", (properties) -> new DishItem((properties).food(TofuFoods.KOYADOFUSTEW).usingConvertsTo(Items.BOWL).stacksTo(16)));

	public static final DeferredHolder<Item, Item> KOUJI_BASE = ITEMS.registerItem("koujibase", KoujiBaseItem::new);
	public static final DeferredHolder<Item, Item> KOUJI = ITEMS.registerItem("kouji", Item::new);

	public static final DeferredHolder<Item, Item> MISO = ITEMS.registerItem("miso", Item::new);
	public static final DeferredHolder<Item, Item> BOTTLE_SOYSAUSE = ITEMS.registerItem("bottle_soysause", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
	public static final DeferredHolder<Item, Item> NANBAN = ITEMS.registerItem("nanban", (properties) -> new DishItem((properties).food(TofuFoods.NANBAN, TofuConsumables.CHILI_FOOD).usingConvertsTo(Items.BOWL).stacksTo(16)));
	public static final DeferredHolder<Item, Item> NANBANTOFU = ITEMS.registerItem("nanbantofu", (properties) -> new DishItem((properties).food(TofuFoods.NANBANTOFU, TofuConsumables.CHILI_FOOD).usingConvertsTo(Items.BOWL).stacksTo(16)));

	public static final DeferredHolder<Item, Item> SOY_PIZZA = ITEMS.registerItem("soy_pizza", (properties) -> new Item((properties).food(TofuFoods.SOY_CHEESE)));
	public static final DeferredHolder<Item, Item> SOY_CHEESE = ITEMS.registerItem("soy_cheese", (properties) -> new Item((properties).food(TofuFoods.SOY_CHEESE)));
	public static final DeferredHolder<Item, Item> SOY_NETHER_CHEESE = ITEMS.registerItem("soy_nether_cheese", (properties) -> new Item((properties).food(TofuFoods.SOY_NETHER_CHEESE, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> SOY_SOUL_CHEESE = ITEMS.registerItem("soy_soul_cheese", (properties) -> new Item((properties).food(TofuFoods.SOY_SOUL_CHEESE, TofuConsumables.SOUL_FOOD)));
	public static final DeferredHolder<Item, Item> MONSTER_JERKY = ITEMS.registerItem("monster_jerky", (properties) -> new Item((properties).food(TofuFoods.MONSTER_JERKY)));
	public static final DeferredHolder<Item, Item> MONSTER_PORK_JERKY = ITEMS.registerItem("monster_pork_jerky", (properties) -> new Item((properties).food(TofuFoods.MONSTER_PORK_JERKY)));
	public static final DeferredHolder<Item, Item> ROTTEN_PORK = ITEMS.registerItem("rotten_pork", (properties) -> new Item((properties).food(TofuFoods.ROTTEN_PORK, Consumables.ROTTEN_FLESH)));


	public static final DeferredHolder<Item, Item> YUDOFU = ITEMS.registerItem("yudofu", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.YUDOFU)));
	public static final DeferredHolder<Item, Item> EDAMAME_RICE = ITEMS.registerItem("edamame_rice", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.EDAMAME_RICE)));
	public static final DeferredHolder<Item, Item> AGEDASHI_TOFU = ITEMS.registerItem("agedashi_tofu", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.AGEDASHI_TOFU)));
	public static final DeferredHolder<Item, Item> TOFU_STEAK = ITEMS.registerItem("tofusteak", (properties) -> new Item((properties).food(TofuFoods.TOFUSTEAK)));
	public static final DeferredHolder<Item, Item> OAGE = ITEMS.registerItem("oage", (properties) -> new Item((properties).food(TofuFoods.OAGE, TofuConsumables.FAST_FOOD)));


	public static final DeferredHolder<Item, Item> NATTO = ITEMS.registerItem("natto", (properties) -> new Item((properties).food(TofuFoods.NATTO)));
	public static final DeferredHolder<Item, Item> NETHER_NATTO = ITEMS.registerItem("nether_natto", (properties) -> new Item((properties).food(TofuFoods.NETHER_NATTO)));

	public static final DeferredHolder<Item, Item> STARCH = ITEMS.registerItem("starch", Item::new);
	public static final DeferredHolder<Item, Item> STARCH_RAW = ITEMS.registerItem("starch_raw", Item::new);
	public static final DeferredHolder<Item, Item> FILTERCLOTH = ITEMS.registerItem("filtercloth", Item::new);

	public static final DeferredHolder<Item, Item> GELATIN = ITEMS.registerItem("gelatin", Item::new);
	public static final DeferredHolder<Item, Item> GELATINRAW = ITEMS.registerItem("gelatinraw", Item::new);

	public static final DeferredHolder<Item, Item> LEEK = ITEMS.registerItem("leek", (properties) -> new DeferredBlockItem(TofuBlocks.LEEK_CROP, (properties)));
	public static final DeferredHolder<Item, Item> RICE = ITEMS.registerItem("rice", Item::new);
	public static final DeferredHolder<Item, Item> SEEDS_RICE = ITEMS.registerItem("seeds_rice", (properties) -> new SeedAndRootItem(TofuBlocks.RICE_CROP.get(), TofuBlocks.RICE_ROOT.get(), (properties)));
	public static final DeferredHolder<Item, Item> SPROUTS = ITEMS.registerItem("sprouts", (properties) -> new Item(properties.food(TofuFoods.SPROUTS)));

	public static final DeferredHolder<Item, Item> CHIKUWA = ITEMS.registerItem("chikuwa", (properties) -> new Item((properties).food(TofuFoods.CHIKUWA)));
	public static final DeferredHolder<Item, Item> TOFU_CHIKUWA = ITEMS.registerItem("tofu_chikuwa", (properties) -> new Item((properties).food(TofuFoods.TOFU_CHIKUWA)));

	public static final DeferredHolder<Item, Item> APRICOT = ITEMS.registerItem("apricot", (properties) -> new ApricotItem((properties).food(TofuFoods.APRICOT)));
	public static final DeferredHolder<Item, Item> APRICOTJERRY_BOTTLE = ITEMS.registerItem("apricotjerry_bottle", (properties) -> new Item((properties.stacksTo(16))));
	public static final DeferredHolder<Item, Item> APRICOTJERRY_BREAD = ITEMS.registerItem("apricotjerry_bread", (properties) -> new Item((properties).food(TofuFoods.APRICOT_BREAD)));
	public static final DeferredHolder<Item, Item> APRICOTSEED = ITEMS.registerItem("apricotseed", Item::new);
	public static final DeferredHolder<Item, Item> KYONINSO = ITEMS.registerItem("kyoninso", Item::new);

	public static final DeferredHolder<Item, Item> YUBA = ITEMS.registerItem("yuba", (properties) -> new Item((properties).food(TofuFoods.YUBA)));

	public static final DeferredHolder<Item, Item> ZUNDA = ITEMS.registerItem("zunda", Item::new);
	public static final DeferredHolder<Item, Item> ZUNDAMA = ITEMS.registerItem("zundama", Item::new);
	public static final DeferredHolder<Item, Item> UNSTABLE_ZUNDAMA = ITEMS.registerItem("unstable_zundama", (properties) -> new UnstableZundamaItem((properties)));
	public static final DeferredHolder<Item, Item> ZUNDA_INGOT = ITEMS.registerItem("zunda_ingot", (properties) -> new ZundaIngotItem((properties.food(TofuFoods.ZUNDA_INGOT))));
	public static final DeferredHolder<Item, Item> ZUNDA_ALLOY_TOFU = ITEMS.registerItem("zunda_alloy_tofu", Item::new);
	public static final DeferredHolder<Item, Item> ZUNDA_ALLOY_TOFU_SWORD = ITEMS.registerItem("zunda_alloy_tofu_sword", (properties) -> new ZundaAlloySwordItem((properties.component(TofuDataComponents.TF_ENERGY_DATA, new TFEnergyData(8000, 8000)))));
	public static final DeferredHolder<Item, Item> ZUNDA_TOTEM = ITEMS.registerItem("zunda_totem", (properties) -> new Item((properties.stacksTo(1).component(DataComponents.DEATH_PROTECTION, new DeathProtection(List.of(new ClearAllStatusEffectsConsumeEffect(),
			new ApplyStatusEffectsConsumeEffect(
					List.of(
							new MobEffectInstance(TofuEffects.HEART_RECOVER, 60, 2),
							new MobEffectInstance(MobEffects.ABSORPTION, 100, 0)
					)
			)))))));
	public static final DeferredHolder<Item, Item> ZUNDARUBY = ITEMS.registerItem("zundaruby", (properties) -> new Item(properties.trimMaterial(TofuTrimMaterials.ZUNDA_RUBY)));

	public static final DeferredHolder<Item, Item> GLASS_BOWL = ITEMS.registerItem("glass_bowl", Item::new);
	public static final DeferredHolder<Item, Item> TOFU_METAL_BOWL = ITEMS.registerItem("tofu_metal_bowl", Item::new);
	public static final DeferredHolder<Item, Item> CERAMIC_BOWL = ITEMS.registerItem("ceramic_bowl", Item::new);


	public static final DeferredHolder<Item, Item> TOFU_HAMBURG_RAW = ITEMS.registerItem("tofuhamburg_raw", Item::new);
	public static final DeferredHolder<Item, Item> TOFU_HAMBURG = ITEMS.registerItem("tofuhamburg", (properties) -> new Item((properties).food(TofuFoods.TOFU_HAMBURG)));
	public static final DeferredHolder<Item, Item> RAW_TOFU_FISH = ITEMS.registerItem("raw_tofufish", (properties) -> new Item((properties).food(TofuFoods.RAW_TOFUFISH)));
	public static final DeferredHolder<Item, Item> COOKED_TOFU_FISH = ITEMS.registerItem("cooked_tofufish", (properties) -> new Item((properties).food(TofuFoods.COOKED_TOFUFISH)));
	public static final DeferredHolder<Item, Item> MISODENGAKU = ITEMS.registerItem("misodengaku", (properties) -> new DishItem((properties).food(TofuFoods.MISODENGAKU, TofuConsumables.MISO_FOOD).usingConvertsTo(Items.STICK)));
	public static final DeferredHolder<Item, Item> MISO_CHEESE_DENGAKU = ITEMS.registerItem("miso_cheese_dengaku", (properties) -> new DishItem((properties).food(TofuFoods.MISO_CHEESE_DENGAKU, TofuConsumables.MISO_FOOD).usingConvertsTo(Items.STICK)));
	public static final DeferredHolder<Item, Item> TOFUCOOKIE = ITEMS.registerItem("tofucookie", (properties) -> new Item((properties).food(TofuFoods.TOFUCOOKIE)));
	public static final DeferredHolder<Item, Item> TTTBURGER = ITEMS.registerItem("tttburger", (properties) -> new Item((properties).food(TofuFoods.TTTBURGER)));
	public static final DeferredHolder<Item, Item> MEAT_WRAPPED_YUBA = ITEMS.registerItem("meatwrapped_yuba", (properties) -> new Item((properties).food(TofuFoods.MEAT_WRAPPED_YUBA)));
	public static final DeferredHolder<Item, Item> SOYMEAT = ITEMS.registerItem("soymeat", (properties) -> new Item((properties).food(TofuFoods.SOYMEAT)));

	public static final DeferredHolder<Item, Item> SOYSTICK = ITEMS.registerItem("soystick", (properties) -> new Item((properties).food(TofuFoods.SOYSTICK, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> LONGER_SOYSTICK = ITEMS.registerItem("longer_soystick", (properties) -> new Item((properties).rarity(Rarity.UNCOMMON).food(TofuFoods.LONGER_SOYSTICK, TofuConsumables.LONGER_FOOD)));
	public static final DeferredHolder<Item, Item> MISOSOUP = ITEMS.registerItem("misosoup", (properties) -> new DishItem((properties).food(TofuFoods.MISOSOUP, TofuConsumables.MISO_FOOD).usingConvertsTo(Items.BOWL).stacksTo(16)));
	public static final DeferredHolder<Item, Item> MOYASHIITAME = ITEMS.registerItem("moyashiitame", (properties) -> new DishItem((properties).food(TofuFoods.MOYASHIITAME).usingConvertsTo(Items.BOWL).stacksTo(16)));
	public static final DeferredHolder<Item, Item> MOYASHIOHITASHI = ITEMS.registerItem("moyashiohitashi", (properties) -> new DishItem((properties).food(TofuFoods.MOYASHIOHITASHI).usingConvertsTo(Items.BOWL).stacksTo(16)));
	public static final DeferredHolder<Item, Item> SALTYMELON = ITEMS.registerItem("saltymelon", (properties) -> new SaltFoodItem((properties).food(TofuFoods.SALTYMELON, TofuConsumables.SMALL_SALT_FOOD)));

	public static final DeferredHolder<Item, Item> SOYMILK_BOTTLE = ITEMS.registerItem("soymilk_bottle", (properties) -> new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_ANNIN_BOTTLE = ITEMS.registerItem("soymilk_annin_bottle", (properties) -> new SoymilkBottleItem(MobEffects.HEALTH_BOOST, MobEffects.ABSORPTION, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_APPLE_BOTTLE = ITEMS.registerItem("soymilk_apple_bottle", (properties) -> new SoymilkBottleItem(MobEffects.RESISTANCE, MobEffects.ABSORPTION, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_COCOA_BOTTLE = ITEMS.registerItem("soymilk_cocoa_bottle", (properties) -> new SoymilkBottleItem(MobEffects.JUMP_BOOST, MobEffects.SPEED, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_FRUITS_BOTTLE = ITEMS.registerItem("soymilk_fruits_bottle", (properties) -> new SoymilkBottleItem(MobEffects.SLOW_FALLING, MobEffects.JUMP_BOOST, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_HONEY_BOTTLE = ITEMS.registerItem("soymilk_honey_bottle", (properties) -> new DescSoymilkBottleItem(MobEffects.REGENERATION, MobEffects.ABSORPTION, (properties).stacksTo(16).food(TofuFoods.DRINK, TofuConsumables.SOYMILK_HONEY).usingConvertsTo(Items.GLASS_BOTTLE).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_KINAKO_BOTTLE = ITEMS.registerItem("soymilk_kinako_bottle", (properties) -> new SoymilkBottleItem(MobEffects.SPEED, MobEffects.HASTE, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_PUDDING_BOTTLE = ITEMS.registerItem("soymilk_pudding_bottle", (properties) -> new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_PUMPKIN_BOTTLE = ITEMS.registerItem("soymilk_pumpkin_bottle", (properties) -> new SoymilkBottleItem(MobEffects.STRENGTH, MobEffects.HASTE, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_RAMUNE_BOTTLE = ITEMS.registerItem("soymilk_ramune_bottle", (properties) -> new RamuneSoymilkBottleItem(drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_SAKURA_BOTTLE = ITEMS.registerItem("soymilk_sakura_bottle", (properties) -> new SoymilkBottleItem(MobEffects.RESISTANCE, MobEffects.FIRE_RESISTANCE, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_STRAWBERRY_BOTTLE = ITEMS.registerItem("soymilk_strawberry_bottle", (properties) -> new SoymilkBottleItem(MobEffects.HASTE, MobEffects.SPEED, drinkItemProperties(properties).rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, Item> SOYMILK_TEA_BOTTLE = ITEMS.registerItem("soymilk_tea_bottle", (properties) -> new SoymilkBottleItem(MobEffects.LUCK, MobEffects.WATER_BREATHING, drinkItemProperties(properties).rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, Item> SOYMILK_HELL_BOTTLE = ITEMS.registerItem("soymilk_hell_bottle", (properties) -> new SoymilkBottleItem(MobEffects.FIRE_RESISTANCE, MobEffects.RESISTANCE, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_SOUL_BOTTLE = ITEMS.registerItem("soymilk_soul_bottle", (properties) -> new SoymilkBottleItem(MobEffects.ABSORPTION, MobEffects.HEALTH_BOOST, drinkItemProperties(properties)));
	public static final DeferredHolder<Item, Item> SOYMILK_OMINOUS_BOTTLE = ITEMS.registerItem("soymilk_ominous_bottle", (properties) -> new Item(properties.stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));

	public static final DeferredHolder<Item, Item> SOYMILK_PALE_BOTTLE = ITEMS.registerItem("soymilk_pale_bottle", (properties) -> new DescSoymilkBottleItem(MobEffects.RESISTANCE, MobEffects.REGENERATION, properties.stacksTo(16).food(TofuFoods.DRINK, TofuConsumables.SOYMILK_PALE).craftRemainder(Items.GLASS_BOTTLE).usingConvertsTo(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_PALE_GLOW_BOTTLE = ITEMS.registerItem("soymilk_pale_glow_bottle", (properties) -> new DescSoymilkBottleItem(MobEffects.REGENERATION, MobEffects.RESISTANCE, properties.stacksTo(16).food(TofuFoods.DRINK, TofuConsumables.SOYMILK_PALE_GLOW).craftRemainder(Items.GLASS_BOTTLE).usingConvertsTo(Items.GLASS_BOTTLE)));



	public static final DeferredHolder<Item, Item> KINAKO_MANJU = ITEMS.registerItem("kinakomanju", (properties) -> new Item((properties).food(TofuFoods.KINAKO_MANJU)));
	public static final DeferredHolder<Item, Item> ZUNDA_MANJU = ITEMS.registerItem("zundamanju", (properties) -> new Item((properties).food(TofuFoods.ZUNDA_MANJU, TofuConsumables.SOY_FOOD)));
	public static final DeferredHolder<Item, Item> NETHER_MANJU = ITEMS.registerItem("nethermanju", (properties) -> new Item((properties).food(TofuFoods.NETHER_MANJU, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> SOUL_MANJU = ITEMS.registerItem("soulmanju", (properties) -> new Item((properties).food(TofuFoods.SOUL_MANJU, TofuConsumables.SOUL_FOOD)));
	public static final DeferredHolder<Item, Item> ZUNDA_MOCHI = ITEMS.registerItem("zunda_mochi", (properties) -> new Item((properties).food(TofuFoods.ZUNDA_MOCHI, TofuConsumables.SOY_FOOD)));
	public static final DeferredHolder<Item, Item> KINAKO_MOCHI = ITEMS.registerItem("kinako_mochi", (properties) -> new Item((properties).food(TofuFoods.KINAKO_MOCHI)));
	public static final DeferredHolder<Item, Item> CRIMSON_SOUP = ITEMS.registerItem("crimson_soup", (properties) -> new DishItem((properties).food(TofuFoods.CRIMSON_SOUP, TofuConsumables.CRIMSON_SOUP).usingConvertsTo(Items.BOWL).stacksTo(16)));


	public static final DeferredHolder<Item, Item> ONIGIRI = ITEMS.registerItem("onigiri", (properties) -> new Item((properties).food(TofuFoods.ONIGIRI)));
	public static final DeferredHolder<Item, Item> ONIGIRI_SALT = ITEMS.registerItem("onigiri_salt", (properties) -> new SaltFoodItem((properties).food(TofuFoods.ONIGIRI_SALT)));

	public static final DeferredHolder<Item, Item> INARI = ITEMS.registerItem("inari", (properties) -> new Item((properties).food(TofuFoods.INARI)));

	public static final DeferredHolder<Item, Item> OKARA = ITEMS.registerItem("okara", Item::new);
	public static final DeferredHolder<Item, Item> OKARASTICK = ITEMS.registerItem("okarastick", (properties) -> new Item((properties).food(TofuFoods.OKARASTICK, TofuConsumables.FAST_FOOD)));
	public static final DeferredHolder<Item, Item> OKARA_DONUT = ITEMS.registerItem("okara_donut", (properties) -> new Item((properties).food(TofuFoods.OKARA_DONUT)));

	public static final DeferredHolder<Item, Item> SOBOROTOFUSAUTE = ITEMS.registerItem("soborotofusaute", (properties) -> new DishItem((properties).stacksTo(16).usingConvertsTo(Items.BOWL).food(TofuFoods.SOBOROTOFUSAUTE)));


	public static final DeferredHolder<Item, Item> YAKIONIGIRI_MISO = ITEMS.registerItem("yakionigiri_miso", (properties) -> new Item((properties).food(TofuFoods.YAKIONIGIRI_MISO, TofuConsumables.MISO_FOOD)));
	public static final DeferredHolder<Item, Item> YAKIONIGIRI_SHOYU = ITEMS.registerItem("yakionigiri_shoyu", (properties) -> new Item((properties).food(TofuFoods.YAKIONIGIRI_SHOYU)));
	public static final DeferredHolder<Item, Item> RICE_BURGER = ITEMS.registerItem("riceburger", (properties) -> new Item((properties).food(TofuFoods.RICE_BURGER)));
	public static final DeferredHolder<Item, Item> RICE_NATTO = ITEMS.registerItem("ricenatto", (properties) -> new NourishmentItem((properties).craftRemainder(TofuItems.CERAMIC_BOWL.get()).usingConvertsTo(TofuItems.CERAMIC_BOWL.get()).food(TofuFoods.RICE_NATTO)));
	public static final DeferredHolder<Item, Item> RICE_NATTO_LEEK = ITEMS.registerItem("ricenattoleek", (properties) -> new NourishmentItem((properties).craftRemainder(TofuItems.CERAMIC_BOWL.get()).usingConvertsTo(TofuItems.CERAMIC_BOWL.get()).food(TofuFoods.RICE_NATTOLEEK)));
	public static final DeferredHolder<Item, Item> RICE_NETHER_NATTO = ITEMS.registerItem("rice_nether_natto", (properties) -> new NourishmentItem((properties).craftRemainder(TofuItems.CERAMIC_BOWL.get()).usingConvertsTo(TofuItems.CERAMIC_BOWL.get()).food(TofuFoods.RICE_NETHER_NATTO, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> RICE_NETHER_NATTO_LEEK = ITEMS.registerItem("rice_nether_natto_leek", (properties) -> new NourishmentItem((properties).craftRemainder(TofuItems.CERAMIC_BOWL.get()).usingConvertsTo(TofuItems.CERAMIC_BOWL.get()).food(TofuFoods.RICE_NETHER_NATTO_LEEK, TofuConsumables.HELL_FOOD)));

	public static final DeferredHolder<Item, Item> RICE_TOFU = ITEMS.registerItem("ricetofu", (properties) -> new Item((properties).craftRemainder(TofuItems.CERAMIC_BOWL.get()).usingConvertsTo(TofuItems.CERAMIC_BOWL.get()).food(TofuFoods.RICE_TOFU)));
	public static final DeferredHolder<Item, Item> RICE_SOBORO_TOFU = ITEMS.registerItem("ricesoborotofu", (properties) -> new Item((properties).craftRemainder(TofuItems.CERAMIC_BOWL.get()).usingConvertsTo(TofuItems.CERAMIC_BOWL.get()).food(TofuFoods.RICE_SOBORO_TOFU)));
	public static final DeferredHolder<Item, Item> GOHEIMOCHI = ITEMS.registerItem("goheimochi", (properties) -> new DishItem((properties).food(TofuFoods.GOHEIMOCHI, TofuConsumables.MISO_FOOD).usingConvertsTo(Items.STICK)));


	public static final DeferredHolder<Item, Item> SOY_CHOCOLATE = ITEMS.registerItem("soy_chocolate", (properties) -> new Item((properties).food(TofuFoods.SOY_CHOCOLATE)));
	public static final DeferredHolder<Item, Item> TOFUNIAN_SOY_CHOCOLATE = ITEMS.registerItem("tofunian_soy_chocolate", (properties) -> new Item((properties).food(TofuFoods.SOY_CHOCOLATE)));

	public static final DeferredHolder<Item, Item> SOYMILK_BUCKET = ITEMS.registerItem("soymilk_bucket", (properties) -> new BucketItem(TofuFluids.SOYMILK.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> SOYMILK_NETHER_BUCKET = ITEMS.registerItem("soymilk_nether_bucket", (properties) -> new BucketItem(TofuFluids.SOYMILK_HELL.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> SOYMILK_SOUL_BUCKET = ITEMS.registerItem("soymilk_soul_bucket", (properties) -> new BucketItem(TofuFluids.SOYMILK_SOUL.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));

	public static final DeferredHolder<Item, Item> TOFUFISH_BUCKET = ITEMS.registerItem("tofufish_bucket", (properties) -> new MobBucketItem(TofuEntityTypes.TOFUFISH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFUFISH_SOYMILK_BUCKET = ITEMS.registerItem("tofufish_soymilk_bucket", (properties) -> new MobBucketItem(TofuEntityTypes.TOFUFISH.get(), TofuFluids.SOYMILK.get(), SoundEvents.BUCKET_EMPTY_FISH, (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> BITTERN_BUCKET = ITEMS.registerItem("bittern_bucket", (properties) -> new BucketItem(TofuFluids.BITTERN.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> DOUBANJIANG_BUCKET = ITEMS.registerItem("doubanjiang_bucket", (properties) -> new BucketItem(TofuFluids.DOUBANJIANG.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFU_METAL_BUCKET = ITEMS.registerItem("tofu_metal_bucket", (properties) -> new TofuMetalBucketItem((properties.component(TofuDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY))));

	public static final DeferredHolder<Item, Item> PUDDING = ITEMS.registerItem("pudding", (properties) -> new Item((properties).stacksTo(16).food(TofuFoods.PUDDING).usingConvertsTo(TofuItems.GLASS_BOWL.get())));
	public static final DeferredHolder<Item, Item> PUDDING_SOYMILK = ITEMS.registerItem("pudding_soymilk", (properties) -> new Item((properties).stacksTo(16).food(TofuFoods.PUDDING_SOYMILK).usingConvertsTo(TofuItems.GLASS_BOWL.get())));
	public static final DeferredHolder<Item, Item> NIKUJAGA = ITEMS.registerItem("nikujaga", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.NIKUJAGA).usingConvertsTo(Items.BOWL)));
	public static final DeferredHolder<Item, Item> TOFUSOMEN = ITEMS.registerItem("tofusomen", Item::new);
	public static final DeferredHolder<Item, Item> TOFUSOMENBOWL_GLASS = ITEMS.registerItem("tofusomenbowl_glass", (properties) -> new Item((properties).stacksTo(16).food(TofuFoods.TOFUSOMEN).usingConvertsTo(TofuItems.GLASS_BOWL.get())));
	public static final DeferredHolder<Item, Item> TASTYBEEFSTEW = ITEMS.registerItem("tastybeefstew", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.TASTYSTEW).usingConvertsTo(Items.BOWL), true, true));
	public static final DeferredHolder<Item, Item> TASTYSTEW = ITEMS.registerItem("tastystew", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.TASTYSTEW).usingConvertsTo(Items.BOWL), true, true));

	public static final DeferredHolder<Item, Item> SOUL_HIYAYAKKO_GLASS = ITEMS.registerItem("soul_hiyayakko", (properties) -> new GhastFoodItem((properties).stacksTo(16).usingConvertsTo(TofuItems.GLASS_BOWL.get())));
	public static final DeferredHolder<Item, Item> HIYAYAKKO_GLASS = ITEMS.registerItem("hiyayakko", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.HIYAYAKKO).usingConvertsTo(TofuItems.GLASS_BOWL.get()), false));
	public static final DeferredHolder<Item, Item> NATTOHIYAYAKKO_GLASS = ITEMS.registerItem("nattohiyayakko", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.NATTOHIYAYAKKO).usingConvertsTo(TofuItems.GLASS_BOWL.get())));
	public static final DeferredHolder<Item, Item> WARABI_MOCHI = ITEMS.registerItem("warabi_mochi", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.WARABI_MOCHI).usingConvertsTo(TofuItems.GLASS_BOWL.get())));

	public static final DeferredHolder<Item, Item> GRATIN = ITEMS.registerItem("gratin", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.GRATIN, TofuConsumables.SOY_FOOD).usingConvertsTo(TofuItems.TOFU_METAL_BOWL.get())));
	public static final DeferredHolder<Item, Item> CAPRESE = ITEMS.registerItem("caprese", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.CAPRESE, TofuConsumables.SOY_FOOD).usingConvertsTo(Items.BOWL)));
	public static final DeferredHolder<Item, Item> MUSHROOM_ANKAKE = ITEMS.registerItem("mushroom_ankake", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.MUSHROOM_ANKAKE).usingConvertsTo(Items.BOWL)));
	public static final DeferredHolder<Item, Item> TOFU_ANKAKE = ITEMS.registerItem("tofu_ankake", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.TOFU_ANKAKE).usingConvertsTo(Items.BOWL)));


	public static final DeferredHolder<Item, Item> TOFU_KINU_SWORD = ITEMS.registerItem("tofu_kinu_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.KINU, 0F, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_SPEAR = ITEMS.registerItem(
			"tofu_kinu_spear", (properties) -> new Item(properties.spear(TofuToolMaterials.KINU, 0.3F, 0.1F, 0.75F, 20.0F, 14.0F, 10.0F, 5.1F, 15.0F, 4.6F)
			));
	public static final DeferredHolder<Item, Item> TOFU_KINU_AXE = ITEMS.registerItem("tofu_kinu_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.KINU, 0F, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_PICKAXE = ITEMS.registerItem("tofu_kinu_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.KINU, 0F, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_SHOVEL = ITEMS.registerItem("tofu_kinu_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.KINU, 0F, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_HOE = ITEMS.registerItem("tofu_kinu_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.KINU, 0F, -0.5F, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_MOMEN_SWORD = ITEMS.registerItem("tofu_momen_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.MOMEN, 0F, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_SPEAR = ITEMS.registerItem(
			"tofu_momen_spear", (properties) -> new Item(properties.spear(TofuToolMaterials.MOMEN, 0.3F, 0.1F, 0.75F, 20.0F, 14.0F, 10.0F, 5.1F, 15.0F, 4.6F)
			));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_AXE = ITEMS.registerItem("tofu_momen_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.MOMEN, 0F, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_PICKAXE = ITEMS.registerItem("tofu_momen_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.MOMEN, 0F, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_SHOVEL = ITEMS.registerItem("tofu_momen_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.MOMEN, 0F, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_HOE = ITEMS.registerItem("tofu_momen_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.MOMEN, 0F, -1.4F, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_SOLID_SWORD = ITEMS.registerItem("tofu_solid_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.SOLID, 3, -2.3F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_SPEAR = ITEMS.registerItem(
			"tofu_solid_spear", (properties) -> new Item(properties.spear(TofuToolMaterials.SOLID, 0.65F, 0.82F, 0.85F, 6.5F, 10.0F, 6F, 5.1F, 15F, 4.6F)
			));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_AXE = ITEMS.registerItem("tofu_solid_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.SOLID, 6.0F, -2.9F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_PICKAXE = ITEMS.registerItem("tofu_solid_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.SOLID, 1.0F, -2.8F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_SHOVEL = ITEMS.registerItem("tofu_solid_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.SOLID, 1.5F, -2.7F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_HOE = ITEMS.registerItem("tofu_solid_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.SOLID, -1.0F, 0.0F, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_METAL_SWORD = ITEMS.registerItem("tofu_metal_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.METAL, 3, -2.3F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_SPEAR = ITEMS.registerItem(
			"tofu_metal_spear", (properties) -> new Item(properties.spear(TofuToolMaterials.METAL, 0.85F, 0.82F, 0.65F, 4.0F, 9.0F, 8.25F, 5.1F, 12.5F, 4.6F)
			));
	public static final DeferredHolder<Item, Item> TOFU_METAL_AXE = ITEMS.registerItem("tofu_metal_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.METAL, 6.0F, -2.9F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_PICKAXE = ITEMS.registerItem("tofu_metal_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.METAL, 1.0F, -2.8F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_SHOVEL = ITEMS.registerItem("tofu_metal_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.METAL, 1.5F, -2.7F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_SHEARS = ITEMS.registerItem("tofu_metal_shears", (properties) -> new TofuShearsItem((properties).stacksTo(1).durability(224)));
	public static final DeferredHolder<Item, Item> TOFU_METAL_HOE = ITEMS.registerItem("tofu_metal_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.METAL, -2.0F, -1.0F, (properties.stacksTo(1))));


	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_SWORD = ITEMS.registerItem("tofu_diamond_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.TOFUDIAMOND, 3, -2.3F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_SPEAR = ITEMS.registerItem(
			"tofu_diamond_spear", (properties) -> new Item(properties.rarity(Rarity.UNCOMMON).spear(TofuToolMaterials.TOFUDIAMOND, 1.05F, 1.075F, 0.5F, 3.0F, 7.5F, 6.5F, 5.1F, 10.0F, 4.6F)
			));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_AXE = ITEMS.registerItem("tofu_diamond_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.TOFUDIAMOND, 6.0F, -2.9F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_PICKAXE = ITEMS.registerItem("tofu_diamond_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.TOFUDIAMOND, 1.0F, -2.7F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_SHOVEL = ITEMS.registerItem("tofu_diamond_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.TOFUDIAMOND, 1.5F, -2.9F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_HOE = ITEMS.registerItem("tofu_diamond_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.TOFUDIAMOND, -3.0F, 0.0F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("tofu_upgrade_smithing_template", TofuItems::createTofuUpgradeTemplate);
	public static final DeferredHolder<Item, Item> ZUNDA_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("zunda_upgrade_smithing_template", TofuItems::createZundaBowUpgradeTemplate);


	public static final DeferredHolder<Item, Item> TOFU_KINU_HELMET = ITEMS.registerItem("tofu_kinu_helmet", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.KINU, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_CHESTPLATE = ITEMS.registerItem("tofu_kinu_chestplate", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.KINU, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_LEGGINGS = ITEMS.registerItem("tofu_kinu_leggings", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.KINU, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_BOOTS = ITEMS.registerItem("tofu_kinu_boots", (properties) -> new BreakableTofuBootsItem(TofuArmorMaterials.KINU, ArmorType.BOOTS, -0.5F, (properties.stacksTo(1).component(TofuDataComponents.MAX_FALL_DURABILITY, 1))));

	public static final DeferredHolder<Item, Item> TOFU_MOMEN_HELMET = ITEMS.registerItem("tofu_momen_helmet", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.MOMEN, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_CHESTPLATE = ITEMS.registerItem("tofu_momen_chestplate", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.MOMEN, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_LEGGINGS = ITEMS.registerItem("tofu_momen_leggings", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.MOMEN, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_BOOTS = ITEMS.registerItem("tofu_momen_boots", (properties) -> new BreakableTofuBootsItem(TofuArmorMaterials.MOMEN, ArmorType.BOOTS, -0.25F, (properties.stacksTo(1).component(TofuDataComponents.MAX_FALL_DURABILITY, 16))));

	public static final DeferredHolder<Item, Item> TOFU_SOLID_HELMET = ITEMS.registerItem("tofu_solid_helmet", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_CHESTPLATE = ITEMS.registerItem("tofu_solid_chestplate", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_LEGGINGS = ITEMS.registerItem("tofu_solid_leggings", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_BOOTS = ITEMS.registerItem("tofu_solid_boots", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.BOOTS, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_METAL_HELMET = ITEMS.registerItem("tofu_metal_helmet", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_CHESTPLATE = ITEMS.registerItem("tofu_metal_chestplate", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_LEGGINGS = ITEMS.registerItem("tofu_metal_leggings", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_BOOTS = ITEMS.registerItem("tofu_metal_boots", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.BOOTS, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_HELMET = ITEMS.registerItem("tofu_diamond_helmet", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.HELMET, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_CHESTPLATE = ITEMS.registerItem("tofu_diamond_chestplate", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.CHESTPLATE, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_LEGGINGS = ITEMS.registerItem("tofu_diamond_leggings", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.LEGGINGS, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_BOOTS = ITEMS.registerItem("tofu_diamond_boots", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.BOOTS, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));

	public static final DeferredHolder<Item, Item> TOFU_SHIELD = ITEMS.registerItem("tofu_shield", (properties) -> new TofuShieldItem((properties).stacksTo(1).durability(450).repairable(TofuTags.Items.TOFU_METAL_TOOL_MATERIAL)
			.equippableUnswappable(EquipmentSlot.OFFHAND)
			.delayedComponent(
					DataComponents.BLOCKS_ATTACKS,
					context -> new BlocksAttacks(
							0.25F,
							0.75F,
							List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
							new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
							Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
							Optional.of(SoundEvents.SHIELD_BLOCK),
							Optional.of(SoundEvents.SHIELD_BREAK)
					)
			)
			.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
			.equippableUnswappable(EquipmentSlot.OFFHAND)));

	/*public static final DeferredHolder<Item, Item> TOFU_DIAMOND_SHIELD = ITEMS.registerItem("tofu_diamond_shield", (properties) -> new TofuShieldItem((properties).stacksTo(1).durability(1061)
			.rarity(Rarity.UNCOMMON)
			.equippableUnswappable(EquipmentSlot.OFFHAND)
			.repairable(TofuTags.Items.TOFU_DIAMOND_TOOL_MATERIAL)
			.delayedComponent(
					DataComponents.BLOCKS_ATTACKS,
					context -> new BlocksAttacks(
							0.25F,
							0.65F,
							List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
							new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 0.75F),
							Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
							Optional.of(SoundEvents.SHIELD_BLOCK),
							Optional.of(SoundEvents.SHIELD_BREAK)
					)
			)
			.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
			.equippableUnswappable(EquipmentSlot.OFFHAND)));
*/

	public static final DeferredHolder<Item, Item> ROLLINGPIN = ITEMS.registerItem("rollingpin", (properties) -> new RollingPinItem((properties)));

	public static final DeferredHolder<Item, Item> BUGLE = ITEMS.registerItem("bugle", (properties) -> new BugleItem((properties).stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFUSCOOP = ITEMS.registerItem("tofuscoop", (properties) -> new TofuScoopItem((properties).stacksTo(1).durability(364)));
	public static final DeferredHolder<Item, Item> TOFUSTICK = ITEMS.registerItem("tofustick", (properties) -> new TofuStickItem((properties).stacksTo(1).rarity(Rarity.UNCOMMON).durability(264).requiredFeatures(TofuCraftReload.EXPERIMENTAL)));
	public static final DeferredHolder<Item, Item> FUKUMAME = ITEMS.registerItem("fukumame", (properties) -> new FukumameItem((properties).stacksTo(1).enchantable(3).durability(64)));
	public static final DeferredHolder<Item, Item> NETHER_FUKUMAME = ITEMS.registerItem("nether_fukumame", (properties) -> new NetherFukumameItem((properties).stacksTo(1).enchantable(3).durability(64)));
	public static final DeferredHolder<Item, Item> INFERNO_NETHER_FUKUMAME = ITEMS.registerItem("inferno_nether_fukumame", (properties) -> new InfernoNetherFukumameItem((properties).stacksTo(1).enchantable(3).durability(64)));

	public static final DeferredHolder<Item, Item> SOUL_FUKUMAME = ITEMS.registerItem("soul_fukumame", (properties) -> new SoulFukumameItem((properties).stacksTo(1).enchantable(3).durability(64).rarity(Rarity.UNCOMMON)));

	public static final DeferredHolder<Item, Item> ZUNDA_BOW = ITEMS.registerItem("zunda_bow", (properties) -> new ZundaBowItem((properties).rarity(Rarity.UNCOMMON).enchantable(3).durability(686)));

	public static final DeferredHolder<Item, Item> ZUNDA_ARROW = ITEMS.registerItem("zunda_arrow", (properties) -> new ZundaArrowItem((properties)));

	public static final DeferredHolder<Item, Item> ZUNDAMUSHROOM_ON_A_STICK = ITEMS.registerItem("zundamushroom_on_a_stick", (properties) -> new ZundaMushroomOnAStickItem<>((properties).durability(25), TofuEntityTypes.TOFUPIG, 7));

	public static final DeferredHolder<Item, Item> TOFUGEM = ITEMS.registerItem("tofugem", Item::new);
	public static final DeferredHolder<Item, Item> ADVANCE_TOFUGEM = ITEMS.registerItem("adv_tofugem", Item::new);
	public static final DeferredHolder<Item, Item> TOFU_GEM_DUST = ITEMS.registerItem("tofu_gem_dust", Item::new);
	public static final DeferredHolder<Item, Item> TF_CAPACITOR = ITEMS.registerItem("tf_capacitor", Item::new);
	public static final DeferredHolder<Item, Item> TF_COIL = ITEMS.registerItem("tf_coil", Item::new);
	public static final DeferredHolder<Item, Item> TF_CIRCUIT = ITEMS.registerItem("tf_circuit", Item::new);
	public static final DeferredHolder<Item, Item> TF_OSCILLATOR = ITEMS.registerItem("tf_oscillator", Item::new);
	public static final DeferredHolder<Item, Item> TOFU_CORE = ITEMS.registerItem("tofu_core", Item::new);
	public static final DeferredHolder<Item, Item> TF_DEVICE = ITEMS.registerItem("tf_device", Item::new);
	public static final DeferredHolder<Item, Item> TF_BATTERY = ITEMS.registerItem("tf_battery", (properties) -> new TFBatteryItem((properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFUNIAN_SPAWN_EGG = ITEMS.registerItem("tofunian_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFUNIAN.get()))));
	public static final DeferredHolder<Item, Item> TOFU_COW_SPAWN_EGG = ITEMS.registerItem("tofu_cow_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFUCOW.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> TOFU_PIG_SPAWN_EGG = ITEMS.registerItem("tofu_pig_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFUPIG.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> TOFU_SLIME_SPAWN_EGG = ITEMS.registerItem("tofu_slime_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFUSLIME.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> OAGE_CUBE_SPAWN_EGG = ITEMS.registerItem("oage_cube_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.OAGE_CUBE.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> TOFU_CREEPER_SPAWN_EGG = ITEMS.registerItem("tofu_creeper_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFUCREEPER.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));

	public static final DeferredHolder<Item, Item> TOFUSPIDER_SPAWN_EGG = ITEMS.registerItem("tofu_spider_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFUSPIDER.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> TOFUFISH_SPAWN_EGG = ITEMS.registerItem("tofu_fish_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFUFISH.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> TRAVELER_TOFUNIAN_SPAWN_EGG = ITEMS.registerItem("traveler_tofunian_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TRAVELER_TOFUNIAN.get()))));
	public static final DeferredHolder<Item, Item> TOFU_GANDLEM_SPAWN_EGG = ITEMS.registerItem("tofu_gandlem_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFU_GANDLEM.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> TOFU_GOLEM_SPAWN_EGG = ITEMS.registerItem("tofu_golem_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.TOFU_GOLEM.get()))));
	public static final DeferredHolder<Item, Item> SHUDOFUSPIDER_SPAWN_EGG = ITEMS.registerItem("shudofuspider_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.SHUDOFUSPIDER.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));
	public static final DeferredHolder<Item, Item> FUKUMAME_THROWER_SPAWN_EGG = ITEMS.registerItem("fukumame_thrower_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.FUKUMAME_THROWER.get()))));
	public static final DeferredHolder<Item, Item> ZUNDAMITE_SPAWN_EGG = ITEMS.registerItem("zundamite_spawn_egg", (properties) -> new SpawnEggItem((properties.spawnEgg(TofuEntityTypes.ZUNDAMITE.get()).requiredFeatures(TofuCraftReload.EXPERIMENTAL))));

	public static final DeferredHolder<Item, Item> NATTO_COBWEB = ITEMS.registerItem("natto_cobweb", (properties) -> new NattoCobWebItem((properties.requiredFeatures(TofuCraftReload.EXPERIMENTAL))));

	//Tofu delight item
	public static final DeferredHolder<Item, Item> TOMATO_SOYBEAN_STEW = ITEMS.registerItem("tomato_soybean_stew", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.TOMATO_SOYBEAN_STEW)));

	public static final DeferredHolder<Item, Item> BOTTLE_DASHI = ITEMS.registerItem("bottle_dashi", (properties) -> new Item((properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> BOTTLE_MIRIN = ITEMS.registerItem("bottle_mirin", (properties) -> new Item((properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, Item> BOTTLE_SOYOIL = ITEMS.registerItem("bottle_soyoil", (properties) -> new Item((properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYSAUSE_RAMEN = ITEMS.registerItem("soysause_ramen", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.SOYSAUSE_RAMEN)));

	public static final DeferredHolder<Item, Item> HELL_MABOU = ITEMS.registerItem("hell_mabou", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.HELL_MABOU, TofuConsumables.HELL_CHILI_FOOD)));
	public static final DeferredHolder<Item, Item> RED_SOUP = ITEMS.registerItem("red_soup", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.RED_SOUP, TofuConsumables.HELL_CHILI_FOOD)));
	public static final DeferredHolder<Item, Item> HELL_RED_SOUP = ITEMS.registerItem("hell_red_soup", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.HELL_RED_SOUP, TofuConsumables.HELL_CHILI_FOOD)));

	public static final DeferredHolder<Item, Item> SUKIYAKI = ITEMS.registerItem("sukiyaki", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.SUKIYAKI)));
	public static final DeferredHolder<Item, Item> TOFU_BUNS_BURGER = ITEMS.registerItem("tofu_buns_burger", (properties) -> new Item((properties).food(TofuFoods.TOFU_BUNS_BURGER)));

	public static final DeferredHolder<Item, Item> STEAMED_BREAD = ITEMS.registerItem("steamed_bread", (properties) -> new Item((properties).food(TofuFoods.STEAMED_BREAD)));
	public static final DeferredHolder<Item, Item> STEAMED_BREAD_COCOA = ITEMS.registerItem("steamed_bread_cocoa", (properties) -> new Item((properties).food(TofuFoods.STEAMED_BREAD)));
	public static final DeferredHolder<Item, Item> SANBUZHAN = ITEMS.registerItem("sanbuzhan", (properties) -> new Item((properties).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL).food(TofuFoods.SANBUZHAN)));

	public static final DeferredHolder<Item, Item> KINAKO_BREAD = ITEMS.registerItem("kinako_bread", (properties) -> new Item((properties).food(TofuFoods.KINAKO_BREAD)));

	public static final DeferredHolder<Item, Item> EDAMAME_TEMPLA = ITEMS.registerItem("edamame_templa", (properties) -> new SaltFoodItem((properties).food(TofuFoods.EDAMAME_TEMPLA)));
	public static final DeferredHolder<Item, Item> NEGIMA = ITEMS.registerItem("negima", (properties) -> new DishItem((properties).food(TofuFoods.NEGIMA).usingConvertsTo(Items.STICK)));
	public static final DeferredHolder<Item, Item> SOY_KARAAGE = ITEMS.registerItem("soy_karaage", (properties) -> new Item((properties).food(TofuFoods.SOY_KARAAGE)));
	public static final DeferredHolder<Item, Item> SOYMEATDON = ITEMS.registerItem("soymeatdon", (properties) -> new Item((properties).craftRemainder(TofuItems.CERAMIC_BOWL.get()).usingConvertsTo(TofuItems.CERAMIC_BOWL.get()).food(TofuFoods.SOYMEATDON)));

	public static final DeferredHolder<Item, Item> TOFUNIAN_BANNER_PATTERN = ITEMS.registerItem("tofunian_banner_pattern", (properties) -> new Item(properties.stacksTo(1).delayedComponent(DataComponents.PROVIDES_BANNER_PATTERNS, (context) -> context.getOrThrow(TofuCustomTags.BannerPatternTagGenerator.TOFUNIAN_BANNER_PATTERN)).rarity(Rarity.RARE).requiredFeatures(TofuCraftReload.EXPERIMENTAL)));

	public static final DeferredHolder<Item, Item> LEEK_BOAT = ITEMS.registerItem("leek_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> LEEK_GREEN_BOAT = ITEMS.registerItem("leek_green_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_GREEN_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFU_STEM_BOAT = ITEMS.registerItem("tofu_stem_boat", (properties) -> new BoatItem(TofuEntityTypes.TOFU_STEM_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> SPROUT_BOAT = ITEMS.registerItem("sprout_boat", (properties) -> new BoatItem(TofuEntityTypes.SPROUT_BOAT.get(), properties.stacksTo(1)));

	public static final DeferredHolder<Item, Item> LEEK_CHEST_BOAT = ITEMS.registerItem("leek_chest_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_CHEST_BOAT.get(), properties.stacksTo(1).requiredFeatures(TofuCraftReload.EXPERIMENTAL)));
	public static final DeferredHolder<Item, Item> LEEK_GREEN_CHEST_BOAT = ITEMS.registerItem("leek_green_chest_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_GREEN_CHEST_BOAT.get(), properties.stacksTo(1).requiredFeatures(TofuCraftReload.EXPERIMENTAL)));
	public static final DeferredHolder<Item, Item> TOFU_STEM_CHEST_BOAT = ITEMS.registerItem("tofu_stem_chest_boat", (properties) -> new BoatItem(TofuEntityTypes.TOFU_STEM_CHEST_BOAT.get(), properties.stacksTo(1).requiredFeatures(TofuCraftReload.EXPERIMENTAL)));
	public static final DeferredHolder<Item, Item> SPROUT_CHEST_BOAT = ITEMS.registerItem("sprout_chest_boat", (properties) -> new BoatItem(TofuEntityTypes.SPROUT_CHEST_BOAT.get(), properties.stacksTo(1)));


	public static final DeferredHolder<Item, Item> MUSIC_DISC_GREEN_BRANCH = ITEMS.registerItem("music_disc_green_branch", (properties) -> new Item(properties.jukeboxPlayable(TofuJukeboxSongs.GREEN_BRANCH).requiredFeatures(TofuCraftReload.EXPERIMENTAL)));
	public static final DeferredHolder<Item, Item> TOFU_CRAFTERS_BOOK = ITEMS.registerItem("tofu_crafters_book", TofuBookItem::new);

	public static final DeferredHolder<Item, Item> SOY_FORCE_SHARD = ITEMS.registerItem("soy_force_shard", Item::new);

	public static final DeferredHolder<Item, Item> SOY_ORB = ITEMS.registerItem("soy_orb", Item::new);

	private static Supplier<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}


	private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

	private static final Component TOFU_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_upgrade"))).withStyle(TITLE_FORMAT);
	private static final Component TOFU_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component TOFU_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component TOFU_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.base_slot_description")));

	private static final Component ZUNDA_BOW_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "zunda_upgrade"))).withStyle(TITLE_FORMAT);

	private static final Component ZUNDA_BOW_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component ZUNDA_BOW_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component ZUNDA_BOW_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.base_slot_description")));


	private static final Identifier EMPTY_SLOT_HELMET = Identifier.withDefaultNamespace("container/slot/helmet");
	private static final Identifier EMPTY_SLOT_CHESTPLATE = Identifier.withDefaultNamespace("container/slot/chestplate");
	private static final Identifier EMPTY_SLOT_LEGGINGS = Identifier.withDefaultNamespace("container/slot/leggings");
	private static final Identifier EMPTY_SLOT_BOOTS = Identifier.withDefaultNamespace("container/slot/boots");
	private static final Identifier EMPTY_SLOT_HOE = Identifier.withDefaultNamespace("container/slot/hoe");
	private static final Identifier EMPTY_SLOT_AXE = Identifier.withDefaultNamespace("container/slot/axe");
	private static final Identifier EMPTY_SLOT_SWORD = Identifier.withDefaultNamespace("container/slot/sword");
	private static final Identifier EMPTY_SLOT_SHOVEL = Identifier.withDefaultNamespace("container/slot/shovel");
	private static final Identifier EMPTY_SLOT_PICKAXE = Identifier.withDefaultNamespace("container/slot/pickaxe");
	private static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");
	private static final Identifier EMPTY_SLOT_BOW = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "container/slot/empty_slot_bow");
	private static final Identifier EMPTY_SLOT_ZUNDAMA = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "container/slot/empty_slot_zundama");


	private static List<Identifier> createTofuUpgradeIconList() {
		return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
	}

	private static List<Identifier> createTofuUpgradeMaterialList() {
		return List.of(EMPTY_SLOT_INGOT);
	}


	public static SmithingTemplateItem createTofuUpgradeTemplate(Item.Properties p_363106_) {
		return new SmithingTemplateItem(TOFU_UPGRADE_APPLIES_TO, TOFU_UPGRADE_INGREDIENTS, TOFU_UPGRADE_BASE_SLOT_DESCRIPTION, TOFU_UPGRADE, createTofuUpgradeIconList(), createTofuUpgradeMaterialList(), p_363106_.requiredFeatures(TofuCraftReload.EXPERIMENTAL));
	}

	private static List<Identifier> createZundaBowUpgradeIconList() {
		return List.of(EMPTY_SLOT_BOW);
	}

	private static List<Identifier> createZundaBowUpgradeMaterialList() {
		return List.of(EMPTY_SLOT_ZUNDAMA);
	}


	public static SmithingTemplateItem createZundaBowUpgradeTemplate(Item.Properties p_363106_) {
		return new SmithingTemplateItem(ZUNDA_BOW_UPGRADE_APPLIES_TO, ZUNDA_BOW_UPGRADE_INGREDIENTS, ZUNDA_BOW_UPGRADE_BASE_SLOT_DESCRIPTION, ZUNDA_BOW_UPGRADE, createZundaBowUpgradeIconList(), createZundaBowUpgradeMaterialList(), p_363106_);
	}


	private static Item.Properties drinkItemProperties(Item.Properties properties) {
		return properties.stacksTo(16).food(TofuFoods.DRINK, TofuConsumables.SOYMILK).usingConvertsTo(Items.GLASS_BOTTLE).craftRemainder(Items.GLASS_BOTTLE);
	}

	public static void registerDispenserItem() {

		DispenseItemBehavior dispenseitembehavior1 = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem) p_123562_.getItem();
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				Level level = p_123561_.level();
				if (dispensiblecontaineritem.emptyContents((Player) null, level, blockpos, (BlockHitResult) null)) {
					dispensiblecontaineritem.checkExtraContent((Player) null, level, p_123562_, blockpos);
					return new ItemStack(Items.BUCKET);
				} else {
					return this.defaultDispenseItemBehavior.dispense(p_123561_, p_123562_);
				}
			}
		};
		DispenserBlock.registerBehavior(SOYMILK_BUCKET.get(), dispenseitembehavior1);
		DispenserBlock.registerBehavior(SOYMILK_NETHER_BUCKET.get(), dispenseitembehavior1);
		DispenserBlock.registerBehavior(SOYMILK_SOUL_BUCKET.get(), dispenseitembehavior1);
		DispenserBlock.registerBehavior(BITTERN_BUCKET.get(), dispenseitembehavior1);
		DispenseItemBehavior dispenseitembehavior2 = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				FluidState fluidState = p_123561_.level().getFluidState(blockpos);

				ItemStack result = RecipeHelper.getBitternResult(p_123561_.level(), fluidState.getType(), p_123562_.copyWithCount(1));
				if (result != null) {
					p_123561_.level().setBlock(blockpos, Block.byItem(result.getItem()).defaultBlockState(), 11);
					p_123561_.level().levelEvent(2001, blockpos, Block.getId(p_123561_.level().getBlockState(blockpos)));
					p_123562_.shrink(1);
					this.defaultDispenseItemBehavior.dispense(p_123561_, new ItemStack(Items.GLASS_BOTTLE));
				}
				return p_123562_;
			}
		};
		DispenserBlock.registerBehavior(BITTERN_BOTTLE.get(), dispenseitembehavior2);
		DispenseItemBehavior dispenseitembehavior3 = new DefaultDispenseItemBehavior() {
			private boolean success = false;
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				BlockState state = p_123561_.level().getBlockState(blockpos);
				if (state.is(TofuTags.Blocks.PICKABLE_TOFU)) {
					ItemStack stack = new ItemStack(Item.BY_BLOCK.get(state.getBlock()));
					p_123561_.level().levelEvent(2001, blockpos, Block.getId(state));
					p_123561_.level().removeBlock(blockpos, false);
					this.defaultDispenseItemBehavior.dispense(p_123561_, stack);
					TofuHarden harden = TofuDataMaps.HARDEN_DATA.get(state.getBlock());
					int i = harden != null ? harden.level() : 0;
					p_123562_.hurtAndBreak(1 + i, p_123561_.level(), null, p_348118_ -> {
					});

					setSuccess(true);
				}
				return p_123562_;
			}

			public boolean isSuccess() {
				return this.success;
			}

			public void setSuccess(boolean p_123574_) {
				this.success = p_123574_;
			}

			protected void playSound(BlockSource p_123572_) {
				p_123572_.level().levelEvent(this.isSuccess() ? 1000 : 1001, p_123572_.pos(), 0);
			}
		};
		DispenserBlock.registerBehavior(TOFUSCOOP.get(), dispenseitembehavior3);

		DispenserBlock.registerBehavior(FUKUMAME.get(), new DamageableProjectileDispenseBehavior(FUKUMAME.get()) {

			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(NETHER_FUKUMAME.get(), new DamageableProjectileDispenseBehavior(NETHER_FUKUMAME.get()) {

			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(INFERNO_NETHER_FUKUMAME.get(), new DamageableProjectileDispenseBehavior(INFERNO_NETHER_FUKUMAME.get()) {
			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(SOUL_FUKUMAME.get(), new DamageableProjectileDispenseBehavior(SOUL_FUKUMAME.get()) {

			protected int shootCount() {
				return 6;
			}
		});

		DispenserBlock.registerBehavior(ZUNDA_ARROW.get(), new ProjectileDispenseBehavior(ZUNDA_ARROW.get()) {
		});

		DispenseItemBehavior dispenseitembehavior4 = new DefaultDispenseItemBehavior() {
			private boolean success = false;

			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				FluidState fluidState = p_123561_.level().getFluidState(blockpos);
				ItemStack result = RecipeHelper.getBitternResult(p_123561_.level(), fluidState.getType(), p_123562_.copyWithCount(1));
				if (result != null) {

					p_123561_.level().setBlock(blockpos, Block.byItem(result.getItem()).defaultBlockState(), 11);
					p_123561_.level().levelEvent(2001, blockpos, Block.getId(p_123561_.level().getBlockState(blockpos)));
					p_123562_.shrink(1);
					this.defaultDispenseItemBehavior.dispense(p_123561_, new ItemStack(Items.GLASS_BOTTLE));
					setSuccess(true);
				}

				return p_123562_;
			}

			public boolean isSuccess() {
				return this.success;
			}

			public void setSuccess(boolean p_123574_) {
				this.success = p_123574_;
			}

			protected void playSound(BlockSource p_123572_) {
				p_123572_.level().levelEvent(this.isSuccess() ? 1000 : 1001, p_123572_.pos(), 0);
			}
		};
		DispenserBlock.registerBehavior(CRIMSON_BOTTLE.get(), dispenseitembehavior4);
		DispenserBlock.registerBehavior(WARPED_BOTTLE.get(), dispenseitembehavior4);
		DispenserBlock.registerBehavior(TofuItems.TOFU_METAL_BUCKET.get(), new BucketDispenseBehavior());
	}
}
