package baguchan.tofucraft;

import baguchan.tofucraft.api.tfenergy.TofuEnergyMap;
import baguchan.tofucraft.client.ClientRegistrar;
import baguchan.tofucraft.event.CraftingEvents;
import baguchan.tofucraft.network.SaltFurnaceBitternPacket;
import baguchan.tofucraft.network.SaltFurnaceWaterPacket;
import baguchan.tofucraft.network.SetTFMinerBlockPacket;
import baguchan.tofucraft.network.SoyMilkDrinkedPacket;
import baguchan.tofucraft.network.TFStorageSoymilkPacket;
import baguchan.tofucraft.registry.ModInteractionInformations;
import baguchan.tofucraft.registry.TofuAdvancements;
import baguchan.tofucraft.registry.TofuArmorMaterial;
import baguchan.tofucraft.registry.TofuAttachments;
import baguchan.tofucraft.registry.TofuBiomeSources;
import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlockSetTypes;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuCarvers;
import baguchan.tofucraft.registry.TofuCreativeModeTabs;
import baguchan.tofucraft.registry.TofuDataComponents;
import baguchan.tofucraft.registry.TofuEffects;
import baguchan.tofucraft.registry.TofuEnchantments;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuFeatures;
import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuFoliagePlacerType;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuLootModifiers;
import baguchan.tofucraft.registry.TofuMenus;
import baguchan.tofucraft.registry.TofuParticleTypes;
import baguchan.tofucraft.registry.TofuPoiTypes;
import baguchan.tofucraft.registry.TofuProfessions;
import baguchan.tofucraft.registry.TofuRecipeBookTypes;
import baguchan.tofucraft.registry.TofuRecipes;
import baguchan.tofucraft.registry.TofuSounds;
import com.google.common.collect.Maps;
import com.google.common.reflect.Reflection;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Map;

@Mod(TofuCraftReload.MODID)
public class TofuCraftReload {
	public static final String MODID = "tofucraft";

	public static final String NETWORK_PROTOCOL = "2";

	public static final Logger LOGGER = LogManager.getLogger(TofuCraftReload.MODID);

	public TofuCraftReload(ModContainer modContainer, IEventBus modBus) {
		IEventBus forgeBus = NeoForge.EVENT_BUS;

		modBus.addListener(this::setup);
		modBus.addListener(this::setupPackets);

		TofuBlocks.BLOCKS.register(modBus);
		TofuItems.ITEMS.register(modBus);

		TofuEntityTypes.ENTITIES.register(modBus);

		TofuBlockEntitys.BLOCK_ENTITIES.register(modBus);
		TofuMenus.MENU_TYPES.register(modBus);
		TofuEffects.MOB_EFFECTS.register(modBus);
		TofuFluidTypes.FLUID_TYPES.register(modBus);
		TofuFluids.FLUIDS.register(modBus);
		modBus.addListener(TofuFluids::registerFluids);
		TofuEnchantments.ENCHANTMENT.register(modBus);
		TofuCreativeModeTabs.CREATIVE_MODE_TABS.register(modBus);
		TofuRecipes.RECIPE_TYPES.register(modBus);
		TofuDataComponents.DATA_COMPONENT_TYPES.register(modBus);
		TofuRecipes.RECIPE_SERIALIZERS.register(modBus);
		TofuPoiTypes.POI_TYPES.register(modBus);
		TofuProfessions.PROFESSIONS.register(modBus);
		TofuLootModifiers.LOOT_MODIFIERS.register(modBus);

		TofuArmorMaterial.ARMOR_MATERIALS.register(modBus);

		TofuFoliagePlacerType.FOLIAGE_PLACER_TYPE.register(modBus);
		TofuFeatures.FEATURES.register(modBus);
		TofuSounds.SOUND_EVENTS.register(modBus);
		TofuAttachments.ATTACHMENT_TYPES.register(modBus);
		TofuParticleTypes.PARTICLE_TYPES.register(modBus);
		TofuAdvancements.CRITERIONS_REGISTER.register(modBus);
		TofuCarvers.WORLD_CARVER.register(modBus);


		if (FMLEnvironment.dist == Dist.CLIENT) {
			modBus.addListener(ClientRegistrar::setup);
		}
		NeoForge.EVENT_BUS.register(new CraftingEvents());
		modContainer.registerConfig(ModConfig.Type.COMMON, TofuConfig.COMMON_SPEC);
	}

	private void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {

			Reflection.initialize(TofuRecipeBookTypes.class);

			Map<ResourceLocation, MultiNoiseBiomeSourceParameterList.Preset> map = Maps.newHashMap();
			map.putAll(Map.copyOf(MultiNoiseBiomeSourceParameterList.Preset.BY_NAME));
			map.put(new ResourceLocation(TofuCraftReload.MODID, "tofu_world"), TofuBiomeSources.TOFU_WORLD_PRESET);
			MultiNoiseBiomeSourceParameterList.Preset.BY_NAME = map;

			TofuBlockSetTypes.init();
			TofuAdvancements.init();
			TofuItems.registerDispenserItem();
			TofuItems.registerCompostableItem();
			TofuBlocks.flamableInit();
			GiveGiftToHero.GIFTS.put(TofuProfessions.TOFU_CRAFTSMAN.get(), ResourceKey.create(Registries.LOOT_TABLE, new ResourceLocation(TofuCraftReload.MODID, "gameplay/hero_of_the_village/tofu_craftsman_gift")));
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
		registrar.playBidirectional(SoyMilkDrinkedPacket.TYPE, SoyMilkDrinkedPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playBidirectional(SetTFMinerBlockPacket.TYPE, SetTFMinerBlockPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playBidirectional(SaltFurnaceWaterPacket.TYPE, SaltFurnaceWaterPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playBidirectional(SaltFurnaceBitternPacket.TYPE, SaltFurnaceBitternPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playBidirectional(TFStorageSoymilkPacket.TYPE, TFStorageSoymilkPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
	}


	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(TofuCraftReload.MODID, name.toLowerCase(Locale.ROOT));
	}
}
