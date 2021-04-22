package baguchan.tofucraft.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ZundaSporeParticle extends SpriteTexturedParticle {
	private ZundaSporeParticle(ClientWorld p_i232437_1_, double p_i232437_2_, double p_i232437_4_, double p_i232437_6_) {
		super(p_i232437_1_, p_i232437_2_, p_i232437_4_ - 0.125D, p_i232437_6_);
		this.field_70552_h = 0.4F;
		this.field_70553_i = 0.4F;
		this.field_70551_j = 0.7F;
		func_187115_a(0.01F, 0.01F);
		this.field_70544_f *= this.field_187136_p.nextFloat() * 0.6F + 0.2F;
		this.field_70547_e = (int) (16.0D / (Math.random() * 0.8D + 0.2D));
		this.field_190017_n = false;
	}

	private ZundaSporeParticle(ClientWorld p_i232438_1_, double p_i232438_2_, double p_i232438_4_, double p_i232438_6_, double p_i232438_8_, double p_i232438_10_, double p_i232438_12_) {
		super(p_i232438_1_, p_i232438_2_, p_i232438_4_ - 0.125D, p_i232438_6_, p_i232438_8_, p_i232438_10_, p_i232438_12_);
		func_187115_a(0.01F, 0.01F);
		this.field_70544_f *= this.field_187136_p.nextFloat() * 0.6F + 0.6F;
		this.field_70547_e = (int) (16.0D / (Math.random() * 0.8D + 0.2D));
		this.field_190017_n = false;
	}

	public IParticleRenderType func_217558_b() {
		return IParticleRenderType.field_217602_b;
	}

	public void func_189213_a() {
		this.field_187123_c = this.field_187126_f;
		this.field_187124_d = this.field_187127_g;
		this.field_187125_e = this.field_187128_h;
		if (this.field_70547_e-- <= 0) {
			func_187112_i();
		} else {
			func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class ZundaSporeFactory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprite;

		public ZundaSporeFactory(IAnimatedSprite p_i232441_1_) {
			this.sprite = p_i232441_1_;
		}

		public Particle createParticle(BasicParticleType p_199234_1_, ClientWorld p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
			Random random = p_199234_2_.random;
			double d0 = random.nextGaussian() * 9.999999974752427E-7D;
			double d1 = random.nextGaussian() * 9.999999747378752E-5D;
			double d2 = random.nextGaussian() * 9.999999974752427E-7D;
			ZundaSporeParticle underwaterparticle = new ZundaSporeParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, d0, d1, d2);
			underwaterparticle.func_217568_a(this.sprite);
			underwaterparticle.func_70538_b(0.5F, 0.9F, 0.5F);
			return underwaterparticle;
		}
	}
}
