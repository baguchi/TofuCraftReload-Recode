package baguchi.tofucraft.client.particle;

import baguchi.tofucraft.registry.TofuParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;

public class ZundaExplosionSeedParticle extends NoRenderParticle {
	public ZundaExplosionSeedParticle(ClientLevel p_106947_, double p_106948_, double p_106949_, double p_106950_) {
		super(p_106947_, p_106948_, p_106949_, p_106950_, (double) 0.0F, (double) 0.0F, (double) 0.0F);
		this.lifetime = 8;
	}

	public void tick() {
		for (int i = 0; i < 6; ++i) {
			double d0 = this.x + (this.random.nextDouble() - this.random.nextDouble()) * (double) 4.0F;
			double d1 = this.y + (this.random.nextDouble() - this.random.nextDouble()) * (double) 4.0F;
			double d2 = this.z + (this.random.nextDouble() - this.random.nextDouble()) * (double) 4.0F;
			this.level.addParticle(TofuParticleTypes.ZUNDA_EXPLOSION.get(), d0, d1, d2, (double) ((float) this.age / (float) this.lifetime), (double) 0.0F, (double) 0.0F);
		}

		++this.age;
		if (this.age == this.lifetime) {
			this.remove();
		}

	}


	public static class Provider<T extends SimpleParticleType> implements ParticleProvider<T> {
		public Particle createParticle(T p_106969_, ClientLevel p_106970_, double p_106971_, double p_106972_, double p_106973_, double p_106974_, double p_106975_, double p_106976_) {
			return new ZundaExplosionSeedParticle(p_106970_, p_106971_, p_106972_, p_106973_);
		}
	}
}
