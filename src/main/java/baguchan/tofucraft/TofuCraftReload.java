package baguchan.tofucraft;

import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.SoyHealthStorage;
import baguchan.tofucraft.client.ClientProxy;
import baguchan.tofucraft.client.ClientRegistrar;
import baguchan.tofucraft.message.SaltFurnaceBitternMessage;
import baguchan.tofucraft.message.SaltFurnaceWaterMessage;
import baguchan.tofucraft.message.SoyMilkDrinkedMessage;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuRecipes;
import baguchan.tofucraft.world.TofuDefaultBiomeFeatures;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Locale;

@Mod(TofuCraftReload.MODID)
public class TofuCraftReload {
	public static final String MODID = "tofucraft";

	public static final String NETWORK_PROTOCOL = "2";

	public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

	@CapabilityInject(SoyHealthCapability.class)
	public static final Capability<SoyHealthCapability> SOY_HEALTH_CAPABILITY = null;

	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("tofucraft", "net"))
			.networkProtocolVersion(() -> NETWORK_PROTOCOL)
			.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
			.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
			.simpleChannel();

	public TofuCraftReload() {
		setupMessages();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(FMLCommonSetupEvent event) {
		TofuDefaultBiomeFeatures.init();
		TofuRecipes.register();
		TofuDimensions.registerDimensionStuff();
		CapabilityManager.INSTANCE.register(SoyHealthCapability.class, new SoyHealthStorage<>(), SoyHealthCapability::new);
	}

	private void setupMessages() {
		CHANNEL.messageBuilder(SoyMilkDrinkedMessage.class, 0)
				.encoder(SoyMilkDrinkedMessage::serialize).decoder(SoyMilkDrinkedMessage::deserialize)
				.consumer(SoyMilkDrinkedMessage::handle)
				.add();
		CHANNEL.messageBuilder(SaltFurnaceWaterMessage.class, 1)
				.encoder(SaltFurnaceWaterMessage::writePacketData).decoder(SaltFurnaceWaterMessage::readPacketData)
				.consumer(SaltFurnaceWaterMessage::handle)
				.add();
		CHANNEL.messageBuilder(SaltFurnaceBitternMessage.class, 2)
				.encoder(SaltFurnaceBitternMessage::writePacketData).decoder(SaltFurnaceBitternMessage::readPacketData)
				.consumer(SaltFurnaceBitternMessage::handle)
				.add();
	}

	private void doClientStuff(FMLClientSetupEvent event) {
	}

	private void enqueueIMC(InterModEnqueueEvent event) {
	}

	private void processIMC(InterModProcessEvent event) {
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}

	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(TofuCraftReload.MODID, name.toLowerCase(Locale.ROOT));
	}
}
