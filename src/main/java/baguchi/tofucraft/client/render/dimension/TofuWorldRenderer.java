package baguchi.tofucraft.client.render.dimension;

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
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.AtlasManager;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.MoonPhase;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class TofuWorldRenderer {
	private static final Identifier SUN_SPRITE = TofuCraftReload.prefix("mabou_sun");

	private final GpuBuffer sunBuffer;
	private final GpuBuffer moonBuffer;
	private final RenderSystem.AutoStorageIndexBuffer quadIndices;
	private final TextureAtlas celestialsAtlas;

	public TofuWorldRenderer(AtlasManager p_455011_) {
		this.quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
		this.celestialsAtlas = p_455011_.getAtlasOrThrow(AtlasIds.CELESTIALS);
		this.sunBuffer = buildSunQuad(this.celestialsAtlas);
		this.moonBuffer = buildMoonPhases(this.celestialsAtlas);
	}

	private static GpuBuffer buildSunQuad(TextureAtlas p_455519_) {
		return buildCelestialQuad("Sun quad", p_455519_.getSprite(SUN_SPRITE));
	}

	private static GpuBuffer buildMoonPhases(TextureAtlas p_455196_) {
		MoonPhase[] amoonphase = MoonPhase.values();
		VertexFormat vertexformat = DefaultVertexFormat.POSITION_TEX;

		GpuBuffer gpubuffer;
		try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(amoonphase.length * 4 * vertexformat.getVertexSize())) {
			BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, vertexformat);

			for (MoonPhase moonphase : amoonphase) {
				TextureAtlasSprite textureatlassprite = p_455196_.getSprite(TofuCraftReload.prefix("moon/" + moonphase.getSerializedName()));
				bufferbuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(textureatlassprite.getU1(), textureatlassprite.getV1());
				bufferbuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(textureatlassprite.getU0(), textureatlassprite.getV1());
				bufferbuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(textureatlassprite.getU0(), textureatlassprite.getV0());
				bufferbuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(textureatlassprite.getU1(), textureatlassprite.getV0());
			}

			try (MeshData meshdata = bufferbuilder.buildOrThrow()) {
				gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, meshdata.vertexBuffer());
			}
		}

		return gpubuffer;
	}

	private static GpuBuffer buildCelestialQuad(String p_454894_, TextureAtlasSprite p_456200_) {
		VertexFormat vertexformat = DefaultVertexFormat.POSITION_TEX;

		GpuBuffer gpubuffer;
		try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(4 * vertexformat.getVertexSize())) {
			BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, vertexformat);
			bufferbuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(p_456200_.getU0(), p_456200_.getV0());
			bufferbuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(p_456200_.getU1(), p_456200_.getV0());
			bufferbuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(p_456200_.getU1(), p_456200_.getV1());
			bufferbuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(p_456200_.getU0(), p_456200_.getV1());

			try (MeshData meshdata = bufferbuilder.buildOrThrow()) {
				gpubuffer = RenderSystem.getDevice().createBuffer(() -> p_454894_, 32, meshdata.vertexBuffer());
			}
		}

		return gpubuffer;
	}



	public void renderTofuSunMoonAndStars(PoseStack p_363513_, float sunAngle, MoonPhase moonPhase, float p_362569_, float p_363542_) {
		p_363513_.pushPose();
		p_363513_.mulPose(Axis.YP.rotationDegrees(-90.0F));
		p_363513_.mulPose(Axis.XP.rotationDegrees(sunAngle));
		this.renderSun(p_362569_, p_363513_);
		this.renderMoon(moonPhase, p_362569_, p_363513_);

		p_363513_.popPose();
	}

	private void renderSun(float p_362331_, PoseStack p_361665_) {
			Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
			matrix4fstack.pushMatrix();
			matrix4fstack.mul(p_361665_.last().pose());
			matrix4fstack.translate(0.0F, 100.0F, 0.0F);
			matrix4fstack.scale(30.0F, 1.0F, 30.0F);
			GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, p_362331_), new Vector3f(), new Matrix4f());
			GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
			GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
			GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

			try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky sun", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
				renderpass.setPipeline(RenderPipelines.CELESTIAL);
				RenderSystem.bindDefaultUniforms(renderpass);
				renderpass.setUniform("DynamicTransforms", gpubufferslice);
				renderpass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
				renderpass.setVertexBuffer(0, this.sunBuffer);
				renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
				renderpass.drawIndexed(0, 0, 6, 1);
			}

			matrix4fstack.popMatrix();

	}

	private void renderMoon(MoonPhase moonPhase, float p_362497_, PoseStack p_362676_) {
			int i = moonPhase.index() * 4;
			Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
			matrix4fstack.pushMatrix();
			matrix4fstack.mul(p_362676_.last().pose());
			matrix4fstack.translate(0.0F, -100.0F, 0.0F);
			matrix4fstack.scale(20.0F, 1.0F, 20.0F);
			GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, p_362497_), new Vector3f(), new Matrix4f());
			GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
			GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
			GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

			try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky moon", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty())) {
				renderpass.setPipeline(RenderPipelines.CELESTIAL);
				RenderSystem.bindDefaultUniforms(renderpass);
				renderpass.setUniform("DynamicTransforms", gpubufferslice);
				renderpass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
				renderpass.setVertexBuffer(0, this.moonBuffer);
				renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
				renderpass.drawIndexed(i, 0, 6, 1);
			}

			matrix4fstack.popMatrix();
	}
}
