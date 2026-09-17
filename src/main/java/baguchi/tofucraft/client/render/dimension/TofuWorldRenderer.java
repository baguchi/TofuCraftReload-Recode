package baguchi.tofucraft.client.render.dimension;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.renderpearl.api.buffers.GpuBuffer;
import com.mojang.renderpearl.api.buffers.GpuBufferSlice;
import com.mojang.renderpearl.api.commands.RenderPass;
import com.mojang.renderpearl.api.pipeline.PrimitiveTopology;
import com.mojang.renderpearl.api.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.MoonPhase;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector4f;

import java.util.function.Supplier;

public class TofuWorldRenderer {
	private static final Identifier SUN_SPRITE = TofuCraftReload.prefix("mabou_sun");

	private final GpuBuffer sunBuffer;
	private final GpuBuffer moonBuffer;
	private final RenderSystem.AutoStorageIndexBuffer quadIndices;
	private final TextureAtlas celestialsAtlas;
	private final RenderTarget renderTarget;

	public TofuWorldRenderer(AtlasManager atlasManager, RenderTarget renderTarget) {
		this.renderTarget = renderTarget;
		this.quadIndices = RenderSystem.getSequentialBuffer(PrimitiveTopology.QUADS);
		this.celestialsAtlas = atlasManager.getAtlasOrThrow(AtlasIds.CELESTIALS);
		this.sunBuffer = buildSunQuad(this.celestialsAtlas);
		this.moonBuffer = buildMoonPhases(this.celestialsAtlas);
	}

	private static GpuBuffer buildSunQuad(TextureAtlas p_455519_) {
		return buildCelestialQuad("Sun quad", p_455519_.getSprite(SUN_SPRITE));
	}

	private static GpuBuffer buildMoonPhases(TextureAtlas atlas) {
		MoonPhase[] phases = MoonPhase.values();
		VertexFormat format = DefaultVertexFormat.POSITION_TEX;

		try (ByteBufferBuilder byteBufferBuilder = ByteBufferBuilder.exactlySized(phases.length * 4 * format.getVertexSize())) {
			BufferBuilder bufferBuilder = new BufferBuilder(byteBufferBuilder, PrimitiveTopology.QUADS, format);

			for (MoonPhase phase : phases) {
				TextureAtlasSprite sprite = atlas.getSprite(TofuCraftReload.prefix("moon/" + phase.getSerializedName()));
				bufferBuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(sprite.getU1(), sprite.getV1());
				bufferBuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(sprite.getU0(), sprite.getV1());
				bufferBuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(sprite.getU0(), sprite.getV0());
				bufferBuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(sprite.getU1(), sprite.getV0());
			}

			try (MeshData mesh = bufferBuilder.buildOrThrow()) {
				return RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, mesh.vertexBuffer());
			}
		}
	}

	private static GpuBuffer buildCelestialQuad(String name, TextureAtlasSprite sprite) {
		VertexFormat format = DefaultVertexFormat.POSITION_TEX;

		try (ByteBufferBuilder byteBufferBuilder = ByteBufferBuilder.exactlySized(4 * format.getVertexSize())) {
			BufferBuilder bufferBuilder = new BufferBuilder(byteBufferBuilder, PrimitiveTopology.QUADS, format);
			bufferBuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(sprite.getU0(), sprite.getV0());
			bufferBuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(sprite.getU1(), sprite.getV0());
			bufferBuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(sprite.getU1(), sprite.getV1());
			bufferBuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(sprite.getU0(), sprite.getV1());

			try (MeshData mesh = bufferBuilder.buildOrThrow()) {
				return RenderSystem.getDevice().createBuffer(() -> name, 32, mesh.vertexBuffer());
			}
		}
	}


	public void renderTofuSunMoonAndStars(
			RenderPass renderPass, PoseStack p_363513_, float p_362201_, float p_362569_, float p_363542_, MoonPhase p_455415_, float p_468909_, float p_467714_
	) {
		p_363513_.pushPose();
		p_363513_.rotateDegrees(Axis.YP, -90.0F);
		p_363513_.pushPose();
		p_363513_.rotate(Axis.XP, p_362201_);
		this.renderSun(renderPass, p_468909_, p_363513_);
		p_363513_.popPose();
		p_363513_.pushPose();
		p_363513_.rotate(Axis.XP, p_362569_);
		this.renderMoon(renderPass, p_455415_, p_468909_, p_363513_);
		p_363513_.popPose();
		p_363513_.popPose();
	}

	private void renderSun(RenderPass renderPass, float rainBrightness, PoseStack poseStack) {
		Matrix4f modelViewMatrix = this.applyCelestialBodyTransform(poseStack, 100.0F, 30.0F);
		GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(modelViewMatrix, new Vector4f(1.0F, 1.0F, 1.0F, rainBrightness));
		this.drawCelestialBody(() -> "Sun", renderPass, dynamicTransforms, this.quadIndices.getBuffer(6), this.sunBuffer, 0);
	}

	private Matrix4f applyCelestialBodyTransform(PoseStack poseStack, float height, float scale) {
		Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
		modelViewStack.pushMatrix();
		modelViewStack.mul(poseStack.last().pose());
		modelViewStack.translate(0.0F, height, 0.0F);
		modelViewStack.scale(scale, 1.0F, scale);
		Matrix4f modelViewMatrix = new Matrix4f(modelViewStack);
		modelViewStack.popMatrix();
		return modelViewMatrix;
	}

	private void drawCelestialBody(
			Supplier<String> label, RenderPass renderPass, GpuBufferSlice dynamicTransforms, GpuBuffer indexBuffer, GpuBuffer vertexBuffer, int baseVertex
	) {
		renderPass.pushDebugGroup(label);
		renderPass.setPipeline(RenderSystem.getCompiledPipeline(RenderPipelines.CELESTIAL));
		RenderSystem.bindDefaultUniforms(renderPass);
		renderPass.setUniform("DynamicTransforms", dynamicTransforms);
		renderPass.setUniform("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
		renderPass.setVertexBuffer(0, vertexBuffer.slice());
		renderPass.setIndexBuffer(indexBuffer, this.quadIndices.type());
		renderPass.drawIndexed(6, 1, 0, baseVertex, 0);
		renderPass.popDebugGroup();
	}


	private void renderMoon(RenderPass renderPass, MoonPhase moonPhase, float rainBrightness, PoseStack poseStack) {
		int baseVertex = moonPhase.index() * 4;
		Matrix4f modelViewMatrix = this.applyCelestialBodyTransform(poseStack, 100.0F, 20.0F);
		GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(modelViewMatrix, new Vector4f(1.0F, 1.0F, 1.0F, rainBrightness));
		this.drawCelestialBody(() -> "Moon", renderPass, dynamicTransforms, this.quadIndices.getBuffer(6), this.moonBuffer, baseVertex);
	}
}
