package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.particle.ParticleSimpleStink;
import baguchan.tofucraft.client.particle.ParticleStink;
import baguchan.tofucraft.client.particle.ParticleZundaCloud;
import baguchan.tofucraft.client.particle.SoymilkDripParticle;
import baguchan.tofucraft.client.particle.SoymilkSplashParticle;
import baguchan.tofucraft.client.particle.TofuPortalParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TofuParticleTypes {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, TofuCraftReload.MODID);

	public static final Supplier<SimpleParticleType> TOFU_PORTAL = PARTICLE_TYPES.register("tofu_portal", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_SOYMILK_HANG = PARTICLE_TYPES.register("drip_soymilk_hang", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_SOYMILK_FALL = PARTICLE_TYPES.register("drip_soymilk_fall", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> SOYMILK_SPLASH = PARTICLE_TYPES.register("soymilk_splash", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_MISO_HANG = PARTICLE_TYPES.register("drip_miso_hang", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_MISO_FALL = PARTICLE_TYPES.register("drip_miso_fall", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> MISO_SPLASH = PARTICLE_TYPES.register("miso_splash", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> ZUNDA_CLOUD = PARTICLE_TYPES.register("zunda_cloud", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> SIMPLE_STINKE = PARTICLE_TYPES.register("simple_stink", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> STINK = PARTICLE_TYPES.register("stink", () -> new SimpleParticleType(false));
	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(TofuParticleTypes.TOFU_PORTAL.get(), TofuPortalParticle.Provider::new);
		event.registerSpriteSet(TofuParticleTypes.TOFU_PORTAL.get(), TofuPortalParticle.Provider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_SOYMILK_HANG.get(), SoymilkDripParticle.SoymilkHangProvider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_SOYMILK_FALL.get(), SoymilkDripParticle.SoymilkFallProvider::new);
		event.registerSpriteSet(TofuParticleTypes.SOYMILK_SPLASH.get(), SoymilkSplashParticle.Provider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_MISO_HANG.get(), SoymilkDripParticle.MisoHangProvider::new);
		event.registerSpriteSet(TofuParticleTypes.DRIP_MISO_FALL.get(), SoymilkDripParticle.MisoFallProvider::new);
		event.registerSpriteSet(TofuParticleTypes.MISO_SPLASH.get(), SoymilkSplashParticle.MisoProvider::new);
		event.registerSpriteSet(TofuParticleTypes.ZUNDA_CLOUD.get(), ParticleZundaCloud.CloudFactory::new);
		event.registerSpriteSet(TofuParticleTypes.STINK.get(), ParticleStink.StinkFactory::new);
		event.registerSpriteSet(TofuParticleTypes.SIMPLE_STINKE.get(), ParticleSimpleStink.Provider::new);
	}
}