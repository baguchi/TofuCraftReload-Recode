package baguchan.tofucraft.client.particle;

import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoymilkDripParticle extends TextureSheetParticle {
	private final Fluid type;
	protected boolean isGlowing;

	public SoymilkDripParticle(ClientLevel p_106051_, double p_106052_, double p_106053_, double p_106054_, Fluid p_106055_) {
		super(p_106051_, p_106052_, p_106053_, p_106054_);
		this.setSize(0.01F, 0.01F);
		this.gravity = 0.06F;
		this.type = p_106055_;
	}

	protected Fluid getType() {
		return this.type;
	}

	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public int getLightColor(float p_106065_) {
		return this.isGlowing ? 240 : super.getLightColor(p_106065_);
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		this.preMoveUpdate();
		if (!this.removed) {
			this.yd -= (double) this.gravity;
			this.move(this.xd, this.yd, this.zd);
			this.postMoveUpdate();
			if (!this.removed) {
				this.xd *= (double) 0.98F;
				this.yd *= (double) 0.98F;
				this.zd *= (double) 0.98F;
				BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
				FluidState fluidstate = this.level.getFluidState(blockpos);
				if (fluidstate.getType() == this.type && this.y < (double) ((float) blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
					this.remove();
				}

			}
		}
	}

	protected void preMoveUpdate() {
		if (this.lifetime-- <= 0) {
			this.remove();
		}

	}

	protected void postMoveUpdate() {
	}

	@OnlyIn(Dist.CLIENT)
	static class CoolingDripHangParticle extends SoymilkDripParticle.DripHangParticle {
		CoolingDripHangParticle(ClientLevel p_106068_, double p_106069_, double p_106070_, double p_106071_, Fluid p_106072_, ParticleOptions p_106073_) {
			super(p_106068_, p_106069_, p_106070_, p_106071_, p_106072_, p_106073_);
		}

		protected void preMoveUpdate() {
			this.rCol = 1.0F;
			this.gCol = 16.0F / (float) (40 - this.lifetime + 16);
			this.bCol = 4.0F / (float) (40 - this.lifetime + 8);
			super.preMoveUpdate();
		}
	}

	@OnlyIn(Dist.CLIENT)
	static class DripHangParticle extends SoymilkDripParticle {
		private final ParticleOptions fallingParticle;

		DripHangParticle(ClientLevel p_106085_, double p_106086_, double p_106087_, double p_106088_, Fluid p_106089_, ParticleOptions p_106090_) {
			super(p_106085_, p_106086_, p_106087_, p_106088_, p_106089_);
			this.fallingParticle = p_106090_;
			this.gravity *= 0.02F;
			this.lifetime = 40;
		}

		protected void preMoveUpdate() {
			if (this.lifetime-- <= 0) {
				this.remove();
				this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
			}

		}

		protected void postMoveUpdate() {
			this.xd *= 0.02D;
			this.yd *= 0.02D;
			this.zd *= 0.02D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	static class DripLandParticle extends SoymilkDripParticle {
		DripLandParticle(ClientLevel p_106102_, double p_106103_, double p_106104_, double p_106105_, Fluid p_106106_) {
			super(p_106102_, p_106103_, p_106104_, p_106105_, p_106106_);
			this.lifetime = (int) (16.0D / (Math.random() * 0.8D + 0.2D));
		}
	}


	@OnlyIn(Dist.CLIENT)
	static class FallAndLandParticle extends SoymilkDripParticle.FallingParticle {
		protected final ParticleOptions landParticle;

		FallAndLandParticle(ClientLevel p_106116_, double p_106117_, double p_106118_, double p_106119_, Fluid p_106120_, ParticleOptions p_106121_) {
			super(p_106116_, p_106117_, p_106118_, p_106119_, p_106120_);
			this.landParticle = p_106121_;
		}

		protected void postMoveUpdate() {
			if (this.onGround) {
				this.remove();
				this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
			}

		}
	}


	@OnlyIn(Dist.CLIENT)
	static class FallingParticle extends SoymilkDripParticle {
		FallingParticle(ClientLevel p_106132_, double p_106133_, double p_106134_, double p_106135_, Fluid p_106136_) {
			this(p_106132_, p_106133_, p_106134_, p_106135_, p_106136_, (int) (64.0D / (Math.random() * 0.8D + 0.2D)));
		}

		FallingParticle(ClientLevel p_172022_, double p_172023_, double p_172024_, double p_172025_, Fluid p_172026_, int p_172027_) {
			super(p_172022_, p_172023_, p_172024_, p_172025_, p_172026_);
			this.lifetime = p_172027_;
		}

		protected void postMoveUpdate() {
			if (this.onGround) {
				this.remove();
			}

		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class SoymilkFallProvider implements ParticleProvider<SimpleParticleType> {
		protected final SpriteSet sprite;

		public SoymilkFallProvider(SpriteSet p_106373_) {
			this.sprite = p_106373_;
		}

		public Particle createParticle(SimpleParticleType p_106384_, ClientLevel p_106385_, double p_106386_, double p_106387_, double p_106388_, double p_106389_, double p_106390_, double p_106391_) {
			SoymilkDripParticle dripparticle = new SoymilkDripParticle.FallAndLandParticle(p_106385_, p_106386_, p_106387_, p_106388_, TofuFluids.SOYMILK.get(), TofuParticleTypes.SOYMILK_SPLASH.get());
			dripparticle.setColor(1.0F, 1.0F, 1.0F);
			dripparticle.pickSprite(this.sprite);
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class SoymilkHangProvider implements ParticleProvider<SimpleParticleType> {
		protected final SpriteSet sprite;

		public SoymilkHangProvider(SpriteSet p_106394_) {
			this.sprite = p_106394_;
		}

		public Particle createParticle(SimpleParticleType p_106405_, ClientLevel p_106406_, double p_106407_, double p_106408_, double p_106409_, double p_106410_, double p_106411_, double p_106412_) {
			SoymilkDripParticle dripparticle = new SoymilkDripParticle.DripHangParticle(p_106406_, p_106407_, p_106408_, p_106409_, TofuFluids.SOYMILK.get(), TofuParticleTypes.DRIP_SOYMILK_FALL.get());
			dripparticle.setColor(1.0F, 1.0F, 1.0F);
			dripparticle.pickSprite(this.sprite);
			return dripparticle;
		}
	}
}