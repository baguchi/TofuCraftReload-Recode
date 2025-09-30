package baguchi.tofucraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class ZundaExplosionParticle extends SimpleAnimatedParticle {
	private final SpriteSet sprites;

	public ZundaExplosionParticle(ClientLevel p_106905_, double p_106906_, double p_106907_, double p_106908_, double p_106909_, SpriteSet p_106910_) {
		super(p_106905_, p_106906_, p_106907_, p_106908_, p_106910_, 0.0125F);
		this.lifetime = 6 + this.random.nextInt(4);
		this.rCol = 103F / 255F;
		this.gCol = 185F / 255F;
		this.bCol = 0;
		this.quadSize = 2.0F * (1.0F - (float) p_106909_ * 0.5F);
		this.sprites = p_106910_;
		this.setSpriteFromAge(p_106910_);
	}

	public int getLightColor(float p_106921_) {
		return 15728880;
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			this.setSpriteFromAge(this.sprites);
		}

	}

	@Override
	public SingleQuadParticle.Layer getLayer() {
		return Layer.OPAQUE;
	}


	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet p_106925_) {
			this.sprites = p_106925_;
		}

		public Particle createParticle(SimpleParticleType p_106936_, ClientLevel p_106937_, double p_106938_, double p_106939_, double p_106940_, double p_106941_, double p_106942_, double p_106943_, RandomSource randomSource) {
			return new ZundaExplosionParticle(p_106937_, p_106938_, p_106939_, p_106940_, p_106941_, this.sprites);
		}
	}
}
