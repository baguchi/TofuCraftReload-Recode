package baguchi.tofucraft;

import baguchi.tofucraft.api.entity.TofunianClothVariant;
import baguchi.tofucraft.api.entity.TofunianVariant;
import baguchi.tofucraft.api.tfenergy.TofuEnergyMap;
import baguchi.tofucraft.client.ClientRegistrar;
import baguchi.tofucraft.data.resources.registries.TofunianClothVariants;
import baguchi.tofucraft.data.resources.registries.TofunianVariants;
import baguchi.tofucraft.event.CraftingEvents;
import baguchi.tofucraft.network.BossInfoPacket;
import baguchi.tofucraft.network.OpenTofuBookPacket;
import baguchi.tofucraft.network.SaltFurnaceBitternPacket;
import baguchi.tofucraft.network.SaltFurnaceWaterPacket;
import baguchi.tofucraft.network.SoyMilkDrinkedPacket;
import baguchi.tofucraft.network.TFCraftingTableSavedRecipePacket;
import baguchi.tofucraft.network.TFStorageSoymilkPacket;
import baguchi.tofucraft.registry.ModInteractionInformations;
import baguchi.tofucraft.registry.TofuAdvancements;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuBiomeSources;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuBlockSetTypes;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuCarvers;
import baguchi.tofucraft.registry.TofuCreativeModeTabs;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuEffects;
import baguchi.tofucraft.registry.TofuEnchantmentEffects;
import baguchi.tofucraft.registry.TofuEntityDatas;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuFeatures;
import baguchi.tofucraft.registry.TofuFluidTypes;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuFoliagePlacerType;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuLootModifiers;
import baguchi.tofucraft.registry.TofuMenus;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuPoiTypes;
import baguchi.tofucraft.registry.TofuProfessions;
import baguchi.tofucraft.registry.TofuRecipeBookCategory;
import baguchi.tofucraft.registry.TofuRecipePropertySets;
import baguchi.tofucraft.registry.TofuRecipes;
import baguchi.tofucraft.registry.TofuSensorTypes;
import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.registry.TofuTags;
import baguchi.tofucraft.registry.TofuTreeDecoratorType;
import baguchi.tofucraft.registry.TofuTrunkPlacerType;
import baguchi.tofucraft.registry.TofunianProfessions;
import com.google.common.collect.Maps;
import com.google.common.reflect.Reflection;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.util.thread.EffectiveSide;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Map;

@Mod(TofuCraftReload.MODID)
public class TofuCraftReload {
	public static final String MODID = "tofucraft";

	public static final String NETWORK_PROTOCOL = "2";

	public static final Logger LOGGER = LogManager.getLogger(TofuCraftReload.MODID);

	public static final FeatureFlag EXPERIMENTAL = FeatureFlags.REGISTRY.getFlag(Identifier.fromNamespaceAndPath(MODID, "experimental_extra"));

	public TofuCraftReload(ModContainer modContainer, Dist dist, IEventBus modBus) {
		IEventBus forgeBus = NeoForge.EVENT_BUS;
		if (dist.isClient()) {
			modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}

		modBus.addListener(this::setup);
		modBus.addListener(this::setupPackets);
		modBus.addListener(TofuCreativeModeTabs::registerBuckets);

		Reflection.initialize(TofuTags.Items.class);


		modBus.addListener(DataPackRegistryEvent.NewRegistry.class, event -> event.dataPackRegistry(TofunianClothVariants.TOFUNIAN_CLOTH_VARIANT_REGISTRY_KEY, TofunianClothVariant.DIRECT_CODEC, TofunianClothVariant.DIRECT_CODEC));
		modBus.addListener(DataPackRegistryEvent.NewRegistry.class, event -> event.dataPackRegistry(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY, TofunianVariant.DIRECT_CODEC, TofunianVariant.DIRECT_CODEC));

		TofuBlocks.BLOCKS.register(modBus);
		TofuItems.ITEMS.register(modBus);
		TofuItems.ITEMS.addAlias(prefix("glassbowl"), prefix("glass_bowl"));
		TofuItems.ITEMS.addAlias(prefix("tofudiamondnugget"), prefix("tofu_diamond_nugget"));

		TofuItems.ITEMS.addAlias(prefix("bucket_soymilk"), prefix("soymilk_bucket"));
		TofuItems.ITEMS.addAlias(prefix("bucket_soymilk_nether"), prefix("soymilk_nether_bucket"));
		TofuItems.ITEMS.addAlias(prefix("bucket_soymilk_soul"), prefix("soymilk_bucket_soul"));
		TofuItems.ITEMS.addAlias(prefix("bucket_bittern"), prefix("bittern_bucket"));
		TofuItems.ITEMS.addAlias(prefix("bucket_doubanjiang"), prefix("doubanjiang_bucket"));


		TofuItems.ITEMS.addAlias(prefix("soymilk"), prefix("soymilk_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_pudding"), prefix("soymilk_pudding_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_annin"), prefix("soymilk_annin_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_apple"), prefix("soymilk_apple_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_cocoa"), prefix("soymilk_cocoa_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_fruits"), prefix("soymilk_fruits_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_honey"), prefix("soymilk_honey_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_kinako"), prefix("soymilk_kinako_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_pumpkin"), prefix("soymilk_pumpkin_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_ramune"), prefix("soymilk_ramune_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_sakura"), prefix("soymilk_sakura_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_strawberry"), prefix("soymilk_strawberry_bottle"));
		TofuItems.ITEMS.addAlias(prefix("soymilk_tea"), prefix("soymilk_tea_bottle"));
		TofuEntityTypes.ENTITIES.register(modBus);
		TofuEntityTypes.ENTITIES.addAlias(prefix("fukumame_thower"), prefix("fukumame_thrower"));
		TofuEntityTypes.ENTITIES.addAlias(prefix("tofucow"), prefix("tofu_cow"));
		TofuEntityTypes.ENTITIES.addAlias(prefix("tofupig"), prefix("tofu_pig"));
		TofuEntityTypes.ENTITIES.addAlias(prefix("tofufish"), prefix("tofu_fish"));
		TofuEntityTypes.ENTITIES.addAlias(prefix("tofuslime"), prefix("tofu_slime"));
		TofuEntityTypes.ENTITIES.addAlias(prefix("tofucreeper"), prefix("tofu_creeper"));
		TofuEntityTypes.ENTITIES.addAlias(prefix("tofuspider"), prefix("tofu_spider"));
		TofuSensorTypes.SENSOR_TYPE.register(modBus);
		TofuEnchantmentEffects.ENTITY_EFFECTS.register(modBus);
		TofuBlockEntitys.BLOCK_ENTITIES.register(modBus);
		TofuMenus.MENU_TYPES.register(modBus);
		TofuEffects.MOB_EFFECTS.register(modBus);
		TofuFluidTypes.FLUID_TYPES.register(modBus);
		TofuFluids.FLUIDS.register(modBus);
		modBus.addListener(TofuFluids::registerFluids);
		TofuCreativeModeTabs.CREATIVE_MODE_TABS.register(modBus);
		TofuRecipeBookCategory.RECIPE_CATEGORY_REGISTER.register(modBus);
		TofuRecipes.RECIPE_TYPES.register(modBus);
		TofuEntityDatas.ENTITY_DATAS.register(modBus);
		TofuDataComponents.DATA_COMPONENT_TYPES.register(modBus);
		TofuRecipes.RECIPE_SERIALIZERS.register(modBus);
		TofuPoiTypes.POI_TYPES.register(modBus);
		TofuLootModifiers.LOOT_MODIFIERS.register(modBus);
		TofuTrunkPlacerType.TRUNK_PLACER_TYPE.register(modBus);

		TofuFoliagePlacerType.FOLIAGE_PLACER_TYPE.register(modBus);
		TofuTreeDecoratorType.TREE_DECORATOR_TYPE.register(modBus);

		TofuFeatures.FEATURES.register(modBus);
		TofuSounds.SOUND_EVENTS.register(modBus);
		TofuAttachments.ATTACHMENT_TYPES.register(modBus);
		TofuParticleTypes.PARTICLE_TYPES.register(modBus);
		TofuAdvancements.CRITERIONS_REGISTER.register(modBus);
		TofuCarvers.WORLD_CARVER.register(modBus);
		TofuProfessions.PROFESSIONS.register(modBus);
		TofunianProfessions.TOFUNIAN_PROFESSION.register(modBus);
		if (FMLEnvironment.getDist() == Dist.CLIENT) {
			modBus.addListener(ClientRegistrar::setup);
		}
		NeoForge.EVENT_BUS.register(new CraftingEvents());
		modContainer.registerConfig(ModConfig.Type.COMMON, TofuConfig.COMMON_SPEC);
		modBus.addListener(TofuCraftReload::registerBETypes);
	}

	private static void registerBETypes(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.SIGN,
				TofuBlocks.TOFU_STEM_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_SIGN.get(),
				TofuBlocks.LEEK_SIGN.get(), TofuBlocks.LEEK_WALL_SIGN.get(),
				TofuBlocks.LEEK_GREEN_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_SIGN.get(),
				TofuBlocks.SPROUT_SIGN.get(), TofuBlocks.SPROUT_WALL_SIGN.get());

		event.modify(BlockEntityType.HANGING_SIGN,
				TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get(),
				TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get(),
				TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get(),
				TofuBlocks.SPROUT_HANGING_SIGN.get(), TofuBlocks.SPROUT_WALL_HANGING_SIGN.get());
		event.modify(BlockEntityType.SHELF,
				TofuBlocks.TOFU_STEM_SHELF.get(),
				TofuBlocks.LEEK_SHELF.get(),
				TofuBlocks.LEEK_GREEN_SHELF.get(),
				TofuBlocks.SPROUT_SHELF.get());

		event.modify(BlockEntityType.BRUSHABLE_BLOCK, TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());
	}

	private void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			Map<Identifier, MultiNoiseBiomeSourceParameterList.Preset> map = Maps.newHashMap();
			map.putAll(Map.copyOf(MultiNoiseBiomeSourceParameterList.Preset.BY_NAME));
			map.put(Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_world"), TofuBiomeSources.TOFU_WORLD_PRESET);
			MultiNoiseBiomeSourceParameterList.Preset.BY_NAME = map;

			TofuRecipePropertySets.addToMap();


			TofuBlockSetTypes.init();
			TofuAdvancements.init();
			TofuItems.registerDispenserItem();
			TofuBlocks.flamableInit();
			TofuBiomes.init();
			TofuEnergyMap.init();
			ModInteractionInformations.init();

			FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;


			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.SAPLING_TOFU.get()), TofuBlocks.POTTED_TOFU_SAPLING);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.SAPLING_APRICOT.get()), TofuBlocks.POTTED_APRICOT_SAPLING);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.ZUNDATOFU_MUSHROOM.get()), TofuBlocks.POTTED_ZUNDA_TOFU_MUSHROOM);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.LEEK.get()), TofuBlocks.POTTED_LEEK);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.TOFU_FLOWER.get()), TofuBlocks.POTTED_TOFU_FLOWER);
		});
	}


	public void setupPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();
		registrar.playToClient(SoyMilkDrinkedPacket.TYPE, SoyMilkDrinkedPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playToClient(SaltFurnaceWaterPacket.TYPE, SaltFurnaceWaterPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playToClient(SaltFurnaceBitternPacket.TYPE, SaltFurnaceBitternPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playToClient(TFStorageSoymilkPacket.TYPE, TFStorageSoymilkPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playToClient(BossInfoPacket.Display.TYPE, BossInfoPacket.Display.STREAM_CODEC, BossInfoPacket.Display::execute);
		registrar.playToClient(BossInfoPacket.Remove.TYPE, BossInfoPacket.Remove.STREAM_CODEC, BossInfoPacket.Remove::execute);
		registrar.playToClient(OpenTofuBookPacket.TYPE, OpenTofuBookPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playToServer(TFCraftingTableSavedRecipePacket.TYPE, TFCraftingTableSavedRecipePacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
	}

	public static Identifier prefix(String name) {
		return Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, name.toLowerCase(Locale.ROOT));
	}

	public static RegistryAccess registryAccess() {
		if (EffectiveSide.get().isServer()) {
			return ServerLifecycleHooks.getCurrentServer().registryAccess();
		}
		return Minecraft.getInstance().getConnection().registryAccess();
	}
}
