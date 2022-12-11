package baguchan.tofucraft;

import baguchan.tofucraft.api.tfenergy.TofuEnergyMap;
import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.client.ClientProxy;
import baguchan.tofucraft.client.ClientRegistrar;
import baguchan.tofucraft.event.CraftingEvents;
import baguchan.tofucraft.message.SaltFurnaceBitternMessage;
import baguchan.tofucraft.message.SaltFurnaceWaterMessage;
import baguchan.tofucraft.message.SoyHealthMessage;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import baguchan.tofucraft.message.TFStorageSoymilkMessage;
import baguchan.tofucraft.registry.TofuAdvancements;
import baguchan.tofucraft.registry.TofuBiomeModifiers;
import baguchan.tofucraft.registry.TofuBiomes;
import baguchan.tofucraft.registry.TofuBlockEntitys;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuCarvers;
import baguchan.tofucraft.registry.TofuContainers;
import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuFeatures;
import baguchan.tofucraft.registry.TofuFluidTypes;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuFoliagePlacerType;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuLootModifiers;
import baguchan.tofucraft.registry.TofuParticleTypes;
import baguchan.tofucraft.registry.TofuPoiTypes;
import baguchan.tofucraft.registry.TofuProfessions;
import baguchan.tofucraft.registry.TofuRecipes;
import baguchan.tofucraft.registry.TofuSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@Mod(TofuCraftReload.MODID)
public class TofuCraftReload {
	public static final String MODID = "tofucraft";

	public static final String NETWORK_PROTOCOL = "2";

	public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

	public static final Capability<SoyHealthCapability> SOY_HEALTH_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	public static final Capability<TofuLivingCapability> TOFU_LIVING_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	public static final Logger LOGGER = LogManager.getLogger(TofuCraftReload.MODID);


	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("tofucraft", "net"))
			.networkProtocolVersion(() -> NETWORK_PROTOCOL)
			.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
			.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
			.simpleChannel();

	public TofuCraftReload() {
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		setupMessages();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		TofuCarvers.WORLD_CARVER.register(modBus);
		TofuBlocks.BLOCKS.register(modBus);
		TofuItems.ITEMS.register(modBus);
		TofuEntityTypes.ENTITIES.register(modBus);
		TofuFluidTypes.FLUID_TYPES.register(modBus);
		TofuFluids.FLUIDS.register(modBus);

		TofuPoiTypes.POI_TYPES.register(modBus);
		TofuProfessions.PROFESSIONS.register(modBus);
		TofuLootModifiers.LOOT_MODIFIERS.register(modBus);
		TofuRecipes.RECIPE_SERIALIZERS.register(modBus);
		TofuContainers.MENU_TYPES.register(modBus);
		TofuBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modBus);
		TofuFoliagePlacerType.FOLIAGE_PLACER_TYPE.register(modBus);
		TofuFeatures.FEATURES.register(modBus);
		TofuSounds.SOUND_EVENTS.register(modBus);
		TofuParticleTypes.PARTICLE_TYPES.register(modBus);

		TofuBlockEntitys.BLOCK_ENTITIES.register(modBus);


		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new CraftingEvents());
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TofuConfig.COMMON_SPEC);
	}

	private void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			TofuAdvancements.init();
			TofuItems.registerDispenserItem();
			TofuItems.registerCompostableItem();
			TofuItems.registerAnimalFeed();
			GiveGiftToHero.GIFTS.put(TofuProfessions.TOFU_CRAFTSMAN.get(), new ResourceLocation(TofuCraftReload.MODID, "gameplay/hero_of_the_village/tofu_craftsman_gift"));
			TofuEnergyMap.init();
			TofuBiomes.init();
		});
	}


	private void setupMessages() {
		CHANNEL.messageBuilder(SoyMilkDrinkedMessage.class, 0)
				.encoder(SoyMilkDrinkedMessage::serialize).decoder(SoyMilkDrinkedMessage::deserialize)
				.consumerMainThread(SoyMilkDrinkedMessage::handle)
				.add();
		CHANNEL.messageBuilder(SoyHealthMessage.class, 1)
				.encoder(SoyHealthMessage::serialize).decoder(SoyHealthMessage::deserialize)
				.consumerMainThread(SoyHealthMessage::handle)
				.add();
		CHANNEL.messageBuilder(SaltFurnaceBitternMessage.class, 2)
				.encoder(SaltFurnaceBitternMessage::writePacketData).decoder(SaltFurnaceBitternMessage::readPacketData)
				.consumerMainThread(SaltFurnaceBitternMessage::handle)
				.add();
		CHANNEL.messageBuilder(SaltFurnaceWaterMessage.class, 3)
				.encoder(SaltFurnaceWaterMessage::writePacketData).decoder(SaltFurnaceWaterMessage::readPacketData)
				.consumerMainThread(SaltFurnaceWaterMessage::handle)
				.add();
		CHANNEL.messageBuilder(TFStorageSoymilkMessage.class, 4)
				.encoder(TFStorageSoymilkMessage::writePacketData).decoder(TFStorageSoymilkMessage::readPacketData)
				.consumerMainThread(TFStorageSoymilkMessage::handle)
				.add();
	}

	private void doClientStuff(FMLClientSetupEvent event) {
	}

	private void enqueueIMC(InterModEnqueueEvent event) {
	}

	private void processIMC(InterModProcessEvent event) {
	}


	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(TofuCraftReload.MODID, name.toLowerCase(Locale.ROOT));
	}
}
