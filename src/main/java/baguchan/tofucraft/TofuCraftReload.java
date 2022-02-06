package baguchan.tofucraft;

import baguchan.tofucraft.api.tfenergy.TofuEnergyMap;
import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.client.ClientProxy;
import baguchan.tofucraft.client.ClientRegistrar;
import baguchan.tofucraft.message.SaltFurnaceBitternMessage;
import baguchan.tofucraft.message.SaltFurnaceWaterMessage;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import baguchan.tofucraft.message.TFStorageSoymilkMessage;
import baguchan.tofucraft.registry.TofuFoliagePlacerType;
import baguchan.tofucraft.registry.TofuNoiseGeneratorSettings;
import baguchan.tofucraft.utils.JigsawHelper;
import baguchan.tofucraft.world.gen.feature.ModNetherFeature;
import baguchan.tofucraft.world.gen.feature.ModTreeFeature;
import baguchan.tofucraft.world.gen.feature.TofuWorldFeatures;
import baguchan.tofucraft.world.placement.ModNetherPlacements;
import baguchan.tofucraft.world.placement.TofuWorldPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

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


	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("tofucraft", "net"))
			.networkProtocolVersion(() -> NETWORK_PROTOCOL)
			.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
			.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
			.simpleChannel();

	public TofuCraftReload() {
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		setupMessages();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(FMLCommonSetupEvent event) {
		ModNetherFeature.init();
		ModNetherPlacements.init();
		TofuFoliagePlacerType.init();
		ModTreeFeature.init();
		TofuWorldFeatures.init();
		TofuWorldPlacements.init();
		TofuNoiseGeneratorSettings.init();
		TofuEnergyMap.init();
	}


	private void setupMessages() {
		CHANNEL.messageBuilder(SoyMilkDrinkedMessage.class, 0)
				.encoder(SoyMilkDrinkedMessage::serialize).decoder(SoyMilkDrinkedMessage::deserialize)
				.consumer(SoyMilkDrinkedMessage::handle)
				.add();
		CHANNEL.messageBuilder(SaltFurnaceBitternMessage.class, 1)
				.encoder(SaltFurnaceBitternMessage::writePacketData).decoder(SaltFurnaceBitternMessage::readPacketData)
				.consumer(SaltFurnaceBitternMessage::handle)
				.add();
		CHANNEL.messageBuilder(SaltFurnaceWaterMessage.class, 2)
				.encoder(SaltFurnaceWaterMessage::writePacketData).decoder(SaltFurnaceWaterMessage::readPacketData)
				.consumer(SaltFurnaceWaterMessage::handle)
				.add();
		CHANNEL.messageBuilder(TFStorageSoymilkMessage.class, 3)
				.encoder(TFStorageSoymilkMessage::writePacketData).decoder(TFStorageSoymilkMessage::readPacketData)
				.consumer(TFStorageSoymilkMessage::handle)
				.add();
	}

	private void doClientStuff(FMLClientSetupEvent event) {
	}

	private void enqueueIMC(InterModEnqueueEvent event) {
	}

	private void processIMC(InterModProcessEvent event) {
	}

	@SubscribeEvent
	public void onServerAboutToStartEvent(ServerStartedEvent event) {
		// SETUP Tofu Worker House
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_plains_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_taiga_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_savanna_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_snowy_1"), 8);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_desert_1"), 8);
	}


	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(TofuCraftReload.MODID, name.toLowerCase(Locale.ROOT));
	}
}
