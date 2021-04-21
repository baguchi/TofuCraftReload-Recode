package baguchan.tofucraft.client.render;

import com.mojang.blaze3d.systems.RenderSystem;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IWeatherRenderHandler;

@OnlyIn(Dist.CLIENT)
public class TofuWeatherRenderer implements IWeatherRenderHandler {
	private static final ResourceLocation RAIN_TEXTURES = new ResourceLocation("tofucraft", "textures/environment/tofurain.png");

	private static final ResourceLocation SNOW_TEXTURES = new ResourceLocation("textures/environment/snow.png");

	private final float[] rainSizeX = new float[1024];

	private final float[] rainSizeZ = new float[1024];

	public TofuWeatherRenderer() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				float f = (j - 16);
				float f1 = (i - 16);
				float f2 = MathHelper.func_76129_c(f * f + f1 * f1);
				this.rainSizeX[i << 5 | j] = -f1 / f2;
				this.rainSizeZ[i << 5 | j] = f / f2;
			}
		}
	}

	public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc, LightTexture lightmap, double xIn, double yIn, double zIn) {
		renderNormalWeather(ticks, partialTicks, mc, lightmap, xIn, yIn, zIn);
	}

	private void renderNormalWeather(int ticks, float partialTicks, Minecraft mc, LightTexture lightmap, double xIn, double yIn, double zIn) {
		float f = mc.field_71441_e.func_72867_j(partialTicks);
		if (f > 0.0F) {
			lightmap.func_205109_c();
			ClientWorld clientWorld = mc.field_71441_e;
			int i = MathHelper.func_76128_c(xIn);
			int j = MathHelper.func_76128_c(yIn);
			int k = MathHelper.func_76128_c(zIn);
			Tessellator tessellator = Tessellator.func_178181_a();
			BufferBuilder bufferbuilder = tessellator.func_178180_c();
			RenderSystem.enableAlphaTest();
			RenderSystem.disableCull();
			RenderSystem.normal3f(0.0F, 1.0F, 0.0F);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.defaultAlphaFunc();
			RenderSystem.enableDepthTest();
			int l = 5;
			if (Minecraft.func_71375_t())
				l = 10;
			RenderSystem.depthMask(Minecraft.func_238218_y_());
			int i1 = -1;
			float f1 = ticks + partialTicks;
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
			for (int j1 = k - l; j1 <= k + l; j1++) {
				for (int k1 = i - l; k1 <= i + l; k1++) {
					int l1 = (j1 - k + 16) * 32 + k1 - i + 16;
					double d0 = this.rainSizeX[l1] * 0.5D;
					double d1 = this.rainSizeZ[l1] * 0.5D;
					blockpos$mutable.func_181079_c(k1, 0, j1);
					Biome biome = clientWorld.func_226691_t_((BlockPos) blockpos$mutable);
					if (biome.func_201851_b() != Biome.RainType.NONE) {
						int i2 = clientWorld.func_205770_a(Heightmap.Type.MOTION_BLOCKING, (BlockPos) blockpos$mutable).getY();
						int j2 = j - l;
						int k2 = j + l;
						if (j2 < i2)
							j2 = i2;
						if (k2 < i2)
							k2 = i2;
						int l2 = i2;
						if (i2 < j)
							l2 = j;
						if (j2 != k2) {
							Random random = new Random((k1 * k1 * 3121 + k1 * 45238971 ^ j1 * j1 * 418711 + j1 * 13761));
							blockpos$mutable.func_181079_c(k1, j2, j1);
							float f2 = biome.func_225486_c((BlockPos) blockpos$mutable);
							if (f2 >= 0.15F) {
								if (i1 != 0) {
									if (i1 >= 0)
										tessellator.func_78381_a();
									i1 = 0;
									mc.func_110434_K().func_110577_a(RAIN_TEXTURES);
									bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181704_d);
								}
								int i3 = ticks + k1 * k1 * 3121 + k1 * 45238971 + j1 * j1 * 418711 + j1 * 13761 & 0x1F;
								float f3 = -(i3 + partialTicks) / 32.0F * (3.0F + random.nextFloat());
								double d2 = (k1 + 0.5F) - xIn;
								double d4 = (j1 + 0.5F) - zIn;
								float f4 = MathHelper.func_76133_a(d2 * d2 + d4 * d4) / l;
								float f5 = ((1.0F - f4 * f4) * 0.5F + 0.5F) * f;
								blockpos$mutable.func_181079_c(k1, l2, j1);
								int j3 = WorldRenderer.func_228421_a_((IBlockDisplayReader) clientWorld, (BlockPos) blockpos$mutable);
								bufferbuilder.func_225582_a_(k1 - xIn - d0 + 0.5D, k2 - yIn, j1 - zIn - d1 + 0.5D).func_225583_a_(0.0F, j2 * 0.25F + f3).func_227885_a_(1.0F, 1.0F, 1.0F, f5).func_227886_a_(j3).func_181675_d();
								bufferbuilder.func_225582_a_(k1 - xIn + d0 + 0.5D, k2 - yIn, j1 - zIn + d1 + 0.5D).func_225583_a_(1.0F, j2 * 0.25F + f3).func_227885_a_(1.0F, 1.0F, 1.0F, f5).func_227886_a_(j3).func_181675_d();
								bufferbuilder.func_225582_a_(k1 - xIn + d0 + 0.5D, j2 - yIn, j1 - zIn + d1 + 0.5D).func_225583_a_(1.0F, k2 * 0.25F + f3).func_227885_a_(1.0F, 1.0F, 1.0F, f5).func_227886_a_(j3).func_181675_d();
								bufferbuilder.func_225582_a_(k1 - xIn - d0 + 0.5D, j2 - yIn, j1 - zIn - d1 + 0.5D).func_225583_a_(0.0F, k2 * 0.25F + f3).func_227885_a_(1.0F, 1.0F, 1.0F, f5).func_227886_a_(j3).func_181675_d();
							} else {
								if (i1 != 1) {
									if (i1 >= 0)
										tessellator.func_78381_a();
									i1 = 1;
									mc.func_110434_K().func_110577_a(SNOW_TEXTURES);
									bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181704_d);
								}
								float f6 = -((ticks & 0x1FF) + partialTicks) / 512.0F;
								float f7 = (float) (random.nextDouble() + f1 * 0.01D * (float) random.nextGaussian());
								float f8 = (float) (random.nextDouble() + (f1 * (float) random.nextGaussian()) * 0.001D);
								double d3 = (k1 + 0.5F) - xIn;
								double d5 = (j1 + 0.5F) - zIn;
								float f9 = MathHelper.func_76133_a(d3 * d3 + d5 * d5) / l;
								float f10 = ((1.0F - f9 * f9) * 0.3F + 0.5F) * f;
								blockpos$mutable.func_181079_c(k1, l2, j1);
								int k3 = WorldRenderer.func_228421_a_((IBlockDisplayReader) clientWorld, (BlockPos) blockpos$mutable);
								int l3 = k3 >> 16 & 0xFFFF;
								int i4 = (k3 & 0xFFFF) * 3;
								int j4 = (l3 * 3 + 240) / 4;
								int k4 = (i4 * 3 + 240) / 4;
								bufferbuilder.func_225582_a_(k1 - xIn - d0 + 0.5D, k2 - yIn, j1 - zIn - d1 + 0.5D).func_225583_a_(0.0F + f7, j2 * 0.25F + f6 + f8).func_227885_a_(1.0F, 1.0F, 1.0F, f10).func_225587_b_(k4, j4).func_181675_d();
								bufferbuilder.func_225582_a_(k1 - xIn + d0 + 0.5D, k2 - yIn, j1 - zIn + d1 + 0.5D).func_225583_a_(1.0F + f7, j2 * 0.25F + f6 + f8).func_227885_a_(1.0F, 1.0F, 1.0F, f10).func_225587_b_(k4, j4).func_181675_d();
								bufferbuilder.func_225582_a_(k1 - xIn + d0 + 0.5D, j2 - yIn, j1 - zIn + d1 + 0.5D).func_225583_a_(1.0F + f7, k2 * 0.25F + f6 + f8).func_227885_a_(1.0F, 1.0F, 1.0F, f10).func_225587_b_(k4, j4).func_181675_d();
								bufferbuilder.func_225582_a_(k1 - xIn - d0 + 0.5D, j2 - yIn, j1 - zIn - d1 + 0.5D).func_225583_a_(0.0F + f7, k2 * 0.25F + f6 + f8).func_227885_a_(1.0F, 1.0F, 1.0F, f10).func_225587_b_(k4, j4).func_181675_d();
							}
						}
					}
				}
			}
			if (i1 >= 0)
				tessellator.func_78381_a();
			RenderSystem.enableCull();
			RenderSystem.disableBlend();
			RenderSystem.defaultAlphaFunc();
			RenderSystem.disableAlphaTest();
			lightmap.func_205108_b();
		}
	}
}
