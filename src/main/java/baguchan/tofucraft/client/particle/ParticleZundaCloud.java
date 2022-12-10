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
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class ParticleZundaCloud extends TextureSheetParticle {

	private final float scale;
	private final EnumCloudBehavior behavior;
	private final float shake;

	public enum EnumCloudBehavior {
		GROW
	}

	public ParticleZundaCloud(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, double scale, int duration, EnumCloudBehavior behavior, double shake) {
		super(level, x, y, z);
		this.scale = (float) scale * 0.4f * 0.1f;
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
		if (alpha <= 1.1) alpha = 1f;
		if (behavior == EnumCloudBehavior.GROW) this.quadSize = scale * ((0.7f * time) + 0.3f);
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
	public static final class CloudFactory implements ParticleProvider<CloudData> {
		private final SpriteSet sprite;

		public CloudFactory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(CloudData type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ParticleZundaCloud particleCloud = new ParticleZundaCloud(level, x, y, z, xSpeed, ySpeed, zSpeed, type.getScale(), type.getDuration(), type.getBehavior(), type.getShake());
			particleCloud.setSpriteFromAge(sprite);
			return particleCloud;
		}
	}

	public static class CloudData implements ParticleOptions {
		public static final ParticleOptions.Deserializer<ParticleZundaCloud.CloudData> DESERIALIZER = new ParticleOptions.Deserializer<ParticleZundaCloud.CloudData>() {
			public ParticleZundaCloud.CloudData fromCommand(ParticleType<CloudData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
				reader.expect(' ');
				float scale = (float) reader.readDouble();
				reader.expect(' ');
				int duration = reader.readInt();
				reader.expect(' ');
				float shake = (float) reader.readDouble();
				return new ParticleZundaCloud.CloudData(particleTypeIn, scale, duration, EnumCloudBehavior.GROW, shake);
			}

			public ParticleZundaCloud.CloudData fromNetwork(ParticleType<ParticleZundaCloud.CloudData> type, FriendlyByteBuf buff) {
				return new ParticleZundaCloud.CloudData(type, buff.readFloat(), buff.readInt(), EnumCloudBehavior.GROW, buff.readFloat());
			}
		};

		private final ParticleType<ParticleZundaCloud.CloudData> type;

		private final float scale;
		private final int duration;
		private final EnumCloudBehavior behavior;
		private final float shake;

		public CloudData(ParticleType<ParticleZundaCloud.CloudData> type, float scale, int duration, EnumCloudBehavior behavior, float shake) {
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
			return String.format(Locale.ROOT, "zundacloud", BuiltInRegistries.PARTICLE_TYPE.getKey(this.getType()), this.scale, this.duration, this.shake);
		}

		@Override
		public ParticleType<ParticleZundaCloud.CloudData> getType() {
			return type;
		}

		@OnlyIn(Dist.CLIENT)
		public float getScale() {
			return this.scale;
		}

		@OnlyIn(Dist.CLIENT)
		public EnumCloudBehavior getBehavior() {
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

		public static Codec<CloudData> CODEC(ParticleType<CloudData> particleType) {
			return RecordCodecBuilder.create((codecBuilder) -> codecBuilder.group(
							Codec.FLOAT.fieldOf("scale").forGetter(CloudData::getScale),
							Codec.STRING.fieldOf("behavior").forGetter((CloudData) -> CloudData.getBehavior().toString()),
							Codec.INT.fieldOf("duration").forGetter(CloudData::getDuration),
							Codec.FLOAT.fieldOf("airdrag").forGetter(CloudData::getShake)
					).apply(codecBuilder, (scale, behavior, duration, shake) ->
							new CloudData(particleType, scale, duration, EnumCloudBehavior.GROW.valueOf(behavior), shake))
			);
		}
	}

}
