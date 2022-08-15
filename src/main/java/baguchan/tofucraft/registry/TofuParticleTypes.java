package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.particle.ParticleStink;
import baguchan.tofucraft.client.particle.ParticleZundaCloud;
import baguchan.tofucraft.client.particle.SoymilkDripParticle;
import baguchan.tofucraft.client.particle.SoymilkSplashParticle;
import baguchan.tofucraft.client.particle.TofuPortalParticle;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TofuParticleTypes {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TofuCraftReload.MODID);

	public static final RegistryObject<SimpleParticleType> TOFU_PORTAL = PARTICLE_TYPES.register("tofu_portal", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> DRIP_SOYMILK_HANG = PARTICLE_TYPES.register("drip_soymilk_hang", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> DRIP_SOYMILK_FALL = PARTICLE_TYPES.register("drip_soymilk_fall", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SOYMILK_SPLASH = PARTICLE_TYPES.register("soymilk_splash", () -> new SimpleParticleType(false));
	public static final RegistryObject<ParticleType<ParticleZundaCloud.CloudData>> ZUNDA_CLOUD = PARTICLE_TYPES.register("zunda_cloud", () -> new ParticleType<ParticleZundaCloud.CloudData>(false, ParticleZundaCloud.CloudData.DESERIALIZER) {
		@Override
		public Codec<ParticleZundaCloud.CloudData> codec() {
			return ParticleZundaCloud.CloudData.CODEC(ZUNDA_CLOUD.get());
		}
	});
	public static final RegistryObject<ParticleType<ParticleStink.StinkData>> STINK = PARTICLE_TYPES.register("stink", () -> new ParticleType<ParticleStink.StinkData>(false, ParticleStink.StinkData.DESERIALIZER) {
		@Override
		public Codec<ParticleStink.StinkData> codec() {
			return ParticleStink.StinkData.CODEC(STINK.get());
		}
	});

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		ParticleEngine particles = Minecraft.getInstance().particleEngine;

		event.register(TofuParticleTypes.TOFU_PORTAL.get(), TofuPortalParticle.Provider::new);
		event.register(TofuParticleTypes.DRIP_SOYMILK_HANG.get(), SoymilkDripParticle.SoymilkHangProvider::new);
		event.register(TofuParticleTypes.DRIP_SOYMILK_FALL.get(), SoymilkDripParticle.SoymilkFallProvider::new);
		event.register(TofuParticleTypes.SOYMILK_SPLASH.get(), SoymilkSplashParticle.Provider::new);
		event.register(TofuParticleTypes.ZUNDA_CLOUD.get(), ParticleZundaCloud.CloudFactory::new);
		event.register(TofuParticleTypes.STINK.get(), ParticleStink.StinkFactory::new);

	}
}