package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TofuParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, TofuCraftReload.MODID);

	public static final Supplier<SimpleParticleType> TOFU_PORTAL = PARTICLE_TYPES.register("tofu_portal", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_SOYMILK_HANG = PARTICLE_TYPES.register("drip_soymilk_hang", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_SOYMILK_FALL = PARTICLE_TYPES.register("drip_soymilk_fall", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> SOYMILK_SPLASH = PARTICLE_TYPES.register("soymilk_splash", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_SOYSAUCE_HANG = PARTICLE_TYPES.register("drip_soysauce_hang", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> DRIP_SOYSAUCE_FALL = PARTICLE_TYPES.register("drip_soysauce_fall", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> SOYSAUCE_SPLASH = PARTICLE_TYPES.register("soysauce_splash", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> ZUNDA_CLOUD = PARTICLE_TYPES.register("zunda_cloud", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> SIMPLE_STINKE = PARTICLE_TYPES.register("simple_stink", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> STINK = PARTICLE_TYPES.register("stink", () -> new SimpleParticleType(false));
	public static final Supplier<SimpleParticleType> ZUNDA_EXPLOSION = PARTICLE_TYPES.register("zunda_explosion", () -> new SimpleParticleType(true));
	public static final Supplier<SimpleParticleType> ZUNDA_EMIT = PARTICLE_TYPES.register("zunda_emit", () -> new SimpleParticleType(true));
}