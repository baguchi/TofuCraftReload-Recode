package baguchi.tofucraft.client;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.renderer.state.SkyRenderState;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import javax.annotation.Nullable;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class TofuDimensionEffects extends DimensionSpecialEffects {
	private static final ResourceLocation SUN_LOCATION = TofuCraftReload.prefix("textures/environment/mabou_sun.png");
	private static final ResourceLocation MOON_LOCATION = TofuCraftReload.prefix("textures/environment/moon_phases.png");


	private final SkyRenderer skyRenderer = new SkyRenderer();
	@Nullable
	private AbstractTexture sunTexture;
	@Nullable
	private AbstractTexture moonTexture;
	private final GpuBuffer sunBuffer;
	private final GpuBuffer moonBuffer;
	private final RenderSystem.AutoStorageIndexBuffer quadIndices;
	public TofuDimensionEffects() {
		super(SkyType.OVERWORLD, false, false);
		this.quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
		this.sunBuffer = this.buildSunQuad();
		this.moonBuffer = this.buildMoonPhases();
		this.initTextures();
	}

	protected void initTextures() {
		this.sunTexture = this.getTexture(SUN_LOCATION);
		this.moonTexture = this.getTexture(MOON_LOCATION);
	}

	private AbstractTexture getTexture(ResourceLocation p_449048_) {
		TextureManager texturemanager = Minecraft.getInstance().getTextureManager();
		AbstractTexture abstracttexture = texturemanager.getTexture(p_449048_);
		abstracttexture.setUseMipmaps(false);
		return abstracttexture;
	}

	private GpuBuffer buildSunQuad() {
		GpuBuffer gpubuffer;
		try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(4 * DefaultVertexFormat.POSITION_TEX.getVertexSize())) {
			BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			Matrix4f matrix4f = new Matrix4f();
			bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, -1.0F).setUv(0.0F, 0.0F);
			bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, -1.0F).setUv(1.0F, 0.0F);
			bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, 1.0F).setUv(1.0F, 1.0F);
			bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, 1.0F).setUv(0.0F, 1.0F);

			try (MeshData meshdata = bufferbuilder.buildOrThrow()) {
				gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Sun quad", 40, meshdata.vertexBuffer());
			}
		}

		return gpubuffer;
	}

	private GpuBuffer buildMoonPhases() {
		int i = 8;
		int j = DefaultVertexFormat.POSITION_TEX.getVertexSize();

		GpuBuffer gpubuffer;
		try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(32 * j)) {
			BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			Matrix4f matrix4f = new Matrix4f();

			for (int k = 0; k < 8; ++k) {
				int l = k % 4;
				int i1 = k / 4 % 2;
				float f = (float) l / 4.0F;
				float f1 = (float) i1 / 2.0F;
				float f2 = (float) (l + 1) / 4.0F;
				float f3 = (float) (i1 + 1) / 2.0F;
				bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, 1.0F).setUv(f2, f3);
				bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, 1.0F).setUv(f, f3);
				bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, -1.0F).setUv(f, f1);
				bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, -1.0F).setUv(f2, f1);
			}

			try (MeshData meshdata = bufferbuilder.buildOrThrow()) {
				gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, meshdata.vertexBuffer());
			}
		}

		return gpubuffer;
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 p_108908_, float p_108909_) {
		return p_108908_.multiply((double) (p_108909_ * 0.94F + 0.06F), (double) (p_108909_ * 0.94F + 0.06F), (double) (p_108909_ * 0.91F + 0.09F));
	}

	@Override
	public boolean isFoggyAt(int p_108905_, int p_108906_) {
		return false;
	}


	@Override
	public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog) {
		PoseStack poseStack = new PoseStack();
		setupFog.run();

		float f = ARGB.redFloat(skyRenderState.skyColor);
		float f1 = ARGB.greenFloat(skyRenderState.skyColor);
		float f2 = ARGB.blueFloat(skyRenderState.skyColor);
		this.skyRenderer.renderSkyDisc(f, f1, f2);
		if (skyRenderState.isSunriseOrSunset) {
			this.skyRenderer.renderSunriseAndSunset(poseStack, skyRenderState.sunAngle, skyRenderState.sunriseAndSunsetColor);
		}

		renderTofuSunMoonAndStars(
				poseStack, skyRenderState.timeOfDay, skyRenderState.moonPhase, skyRenderState.rainBrightness, skyRenderState.starBrightness
		);

		if (skyRenderState.shouldRenderDarkDisc) {
			this.skyRenderer.renderDarkDisc();
		}
		return true;
	}

	public void renderTofuSunMoonAndStars(PoseStack p_363513_, float p_362201_, int p_362572_, float p_362569_, float p_363542_) {
		p_363513_.pushPose();
		p_363513_.mulPose(Axis.YP.rotationDegrees(-90.0F));
		p_363513_.mulPose(Axis.XP.rotationDegrees(p_362201_ * 360.0F));
		this.renderSun(p_362569_, p_363513_);
		this.renderMoon(p_362572_, p_362569_, p_363513_);
		if (p_363542_ > 0.0F) {
			this.skyRenderer.renderStars(p_363542_, p_363513_);
		}

		p_363513_.popPose();
	}

	private void renderSun(float p_362331_, PoseStack p_361665_) {
		if (this.sunTexture != null) {
			Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
			matrix4fstack.pushMatrix();
			matrix4fstack.mul(p_361665_.last().pose());
			matrix4fstack.translate(0.0F, 100.0F, 0.0F);
			matrix4fstack.scale(30.0F, 1.0F, 30.0F);
			GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, p_362331_), new Vector3f(), new Matrix4f(), 0.0F);
			GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
			GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
			GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

			try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky sun", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
				renderpass.setPipeline(RenderPipelines.CELESTIAL);
				RenderSystem.bindDefaultUniforms(renderpass);
				renderpass.setUniform("DynamicTransforms", gpubufferslice);
				renderpass.bindSampler("Sampler0", this.sunTexture.getTextureView());
				renderpass.setVertexBuffer(0, this.sunBuffer);
				renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
				renderpass.drawIndexed(0, 0, 6, 1);
			}

			matrix4fstack.popMatrix();
		}

	}

	private void renderMoon(int p_364754_, float p_362497_, PoseStack p_362676_) {
		if (this.moonTexture != null) {
			int i = p_364754_ & 7;
			int j = i * 4;
			Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
			matrix4fstack.pushMatrix();
			matrix4fstack.mul(p_362676_.last().pose());
			matrix4fstack.translate(0.0F, -100.0F, 0.0F);
			matrix4fstack.scale(20.0F, 1.0F, 20.0F);
			GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, p_362497_), new Vector3f(), new Matrix4f(), 0.0F);
			GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
			GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
			GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

			try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky moon", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
				renderpass.setPipeline(RenderPipelines.CELESTIAL);
				RenderSystem.bindDefaultUniforms(renderpass);
				renderpass.setUniform("DynamicTransforms", gpubufferslice);
				renderpass.bindSampler("Sampler0", this.moonTexture.getTextureView());
				renderpass.setVertexBuffer(0, this.moonBuffer);
				renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
				renderpass.drawIndexed(j, 0, 6, 1);
			}

			matrix4fstack.popMatrix();
		}

	}
}
