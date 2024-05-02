package baguchan.tofucraft.client.particle;

import baguchan.tofucraft.client.render.TofuCraftRenderType;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ParticleStink extends TextureSheetParticle {

	private final float scale;
	private final float shake;

	public ParticleStink(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, double scale, int duration, double shake) {
		super(level, x, y, z);
		this.scale = (float) scale * 0.8f * 0.1f;
		lifetime = duration;
		xd = vx * 0.8;
		yd = vy * 0.8;
		zd = vz * 0.8;
		roll = oRoll = (float) (random.nextInt(4) * Math.PI / 2);
		this.shake = (float) shake;
	}

	@Override
	public void render(VertexConsumer consumer, Camera camera, float tick) {
		var time = (age + tick) / (float) lifetime;
		alpha = time;
		this.quadSize = scale * ((0.7f * time) + 0.3f);
		super.render(consumer, camera, tick);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return TofuCraftRenderType.NO_TRANSLUCENT_DEPTH;
	}

	@Override
	public void tick() {
		super.tick();
		xd *= shake;
		yd *= shake;
		zd *= shake;
	}

	@OnlyIn(Dist.CLIENT)
	public static final class StinkFactory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public StinkFactory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ParticleStink particleCloud = new ParticleStink(level, x, y, z, xSpeed, ySpeed, zSpeed, 5.0F, 60, 1.01F);
			particleCloud.setSpriteFromAge(sprite);
			return particleCloud;
		}
	}

}
