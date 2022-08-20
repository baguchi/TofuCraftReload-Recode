package baguchan.tofucraft.client.particle;

import baguchan.tofucraft.client.render.TofuCraftRenderType;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class ParticleStink extends TextureSheetParticle {

	private final float scale;
	private final EnumStinkBehavior behavior;
	private final float shake;

	public enum EnumStinkBehavior {
		GROW
	}

	public ParticleStink(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, double scale, int duration, EnumStinkBehavior behavior, double shake) {
		super(level, x, y, z);
		this.scale = (float) scale * 0.8f * 0.1f;
		lifetime = duration;
		xd = vx * 0.8;
		yd = vy * 0.8;
		zd = vz * 0.8;
		this.behavior = behavior;
		roll = oRoll = (float) (random.nextInt(4) * Math.PI / 2);
		this.shake = (float) shake;
	}

	@Override
	public void render(VertexConsumer consumer, Camera camera, float tick) {
		var time = (age + tick) / (float) lifetime;
		alpha = 0.5f * ((float) (1 - Math.exp(4 * (time - 1)) - Math.pow(1500, -time)));
		if (alpha <= 0.1) alpha = 0.1f;
		if (behavior == EnumStinkBehavior.GROW) this.quadSize = scale * ((0.7f * time) + 0.3f);
		else this.quadSize = scale;
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
	public static final class StinkFactory implements ParticleProvider<StinkData> {
		private final SpriteSet sprite;

		public StinkFactory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(StinkData type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ParticleStink particleCloud = new ParticleStink(level, x, y, z, xSpeed, ySpeed, zSpeed, type.getScale(), type.getDuration(), type.getBehavior(), type.getShake());
			particleCloud.setSpriteFromAge(sprite);
			return particleCloud;
		}
	}

	public static class StinkData implements ParticleOptions {
		public static final ParticleOptions.Deserializer<ParticleStink.StinkData> DESERIALIZER = new ParticleOptions.Deserializer<ParticleStink.StinkData>() {
			public ParticleStink.StinkData fromCommand(ParticleType<StinkData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
				reader.expect(' ');
				float scale = (float) reader.readDouble();
				reader.expect(' ');
				int duration = reader.readInt();
				reader.expect(' ');
				float shake = (float) reader.readDouble();
				return new ParticleStink.StinkData(particleTypeIn, scale, duration, EnumStinkBehavior.GROW, shake);
			}

			public ParticleStink.StinkData fromNetwork(ParticleType<ParticleStink.StinkData> type, FriendlyByteBuf buff) {
				return new ParticleStink.StinkData(type, buff.readFloat(), buff.readInt(), EnumStinkBehavior.GROW, buff.readFloat());
			}
		};

		private final ParticleType<ParticleStink.StinkData> type;

		private final float scale;
		private final int duration;
		private final EnumStinkBehavior behavior;
		private final float shake;

		public StinkData(ParticleType<ParticleStink.StinkData> type, float scale, int duration, EnumStinkBehavior behavior, float shake) {
			this.type = type;
			this.scale = scale;
			this.behavior = behavior;
			this.shake = shake;
			this.duration = duration;
		}

		@Override
		public void writeToNetwork(FriendlyByteBuf buffer) {
			buffer.writeFloat(this.scale);
			buffer.writeInt(this.duration);
			buffer.writeFloat(this.shake);
		}

		@SuppressWarnings("deprecation")
		@Override
		public String writeToString() {
			return String.format(Locale.ROOT, "Stink", Registry.PARTICLE_TYPE.getKey(this.getType()), this.scale, this.duration, this.shake);
		}

		@Override
		public ParticleType<ParticleStink.StinkData> getType() {
			return type;
		}

		@OnlyIn(Dist.CLIENT)
		public float getScale() {
			return this.scale;
		}

		@OnlyIn(Dist.CLIENT)
		public EnumStinkBehavior getBehavior() {
			return this.behavior;
		}

		@OnlyIn(Dist.CLIENT)
		public int getDuration() {
			return this.duration;
		}

		@OnlyIn(Dist.CLIENT)
		public float getShake() {
			return this.shake;
		}

		public static Codec<StinkData> CODEC(ParticleType<StinkData> particleType) {
			return RecordCodecBuilder.create((codecBuilder) -> codecBuilder.group(
							Codec.FLOAT.fieldOf("scale").forGetter(StinkData::getScale),
							Codec.STRING.fieldOf("behavior").forGetter((StinkData) -> StinkData.getBehavior().toString()),
							Codec.INT.fieldOf("duration").forGetter(StinkData::getDuration),
							Codec.FLOAT.fieldOf("airdrag").forGetter(StinkData::getShake)
					).apply(codecBuilder, (scale, behavior, duration, shake) ->
							new StinkData(particleType, scale, duration, EnumStinkBehavior.GROW.valueOf(behavior), shake))
			);
		}
	}

}
