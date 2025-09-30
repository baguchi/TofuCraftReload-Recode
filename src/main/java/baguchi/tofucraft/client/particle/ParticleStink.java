package baguchi.tofucraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;


public class ParticleStink extends SingleQuadParticle {

	private final float scale;
	private final float shake;

	public ParticleStink(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, double scale, int duration, double shake, TextureAtlasSprite sprite) {
		super(level, x, y, z, sprite);
		this.scale = (float) scale * 0.8f * 0.1f;
		lifetime = duration;
		xd = vx * 0.8;
		yd = vy * 0.8;
		zd = vz * 0.8;
		roll = oRoll = (float) (random.nextInt(4) * Math.PI / 2);
		this.shake = (float) shake;
	}
	@Override
	public SingleQuadParticle.Layer getLayer() {
		return Layer.OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		xd *= shake;
		yd *= shake;
		zd *= shake;
		var time = (age) / (float) lifetime;
		alpha = Mth.clamp(1.0F - time, 0.1F, 1F);
	}

	@Override
	public float getQuadSize(float tick) {
		var time = (age + tick) / (float) lifetime;

		return scale * ((1.25f * time) + 0.3f);
	}

	public static final class StinkFactory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public StinkFactory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType p_446632_, ClientLevel p_107095_, double x, double y, double z, double motionX, double motionY, double motionZ, RandomSource random) {
			return new ParticleStink(p_107095_, x, y, z, motionX, motionY, motionZ, 10.0F, 80, 1.2F, this.sprite.get(random));
		}
	}

}
