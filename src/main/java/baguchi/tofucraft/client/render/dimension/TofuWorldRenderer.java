package baguchi.tofucraft.client.render.dimension;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderTarget;
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

import java.util.Optional;
import java.util.OptionalDouble;

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
			PoseStack p_363513_, float p_362201_, float p_362569_, float p_363542_, MoonPhase p_455415_, float p_468909_, float p_467714_
	) {
		p_363513_.pushPose();
		p_363513_.mulPose(Axis.YP.rotationDegrees(-90.0F));
		p_363513_.pushPose();
		p_363513_.mulPose(Axis.XP.rotation(p_362201_));
		this.renderSun(p_468909_, p_363513_);
		p_363513_.popPose();
		p_363513_.pushPose();
		p_363513_.mulPose(Axis.XP.rotation(p_362569_));
		this.renderMoon(p_455415_, p_468909_, p_363513_);
		p_363513_.popPose();
		p_363513_.popPose();
	}

	private void renderSun(float rainBrightness, PoseStack poseStack) {
		Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
		modelViewStack.pushMatrix();
		modelViewStack.mul(poseStack.last().pose());
		modelViewStack.translate(0.0F, 100.0F, 0.0F);
		modelViewStack.scale(30.0F, 1.0F, 30.0F);
		GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms()
				.writeTransform(new Matrix4f(modelViewStack), new Vector4f(1.0F, 1.0F, 1.0F, rainBrightness));
		GpuTextureView color = this.renderTarget.getColorTextureView();
		GpuTextureView depth = this.renderTarget.getDepthTextureView();
		GpuBuffer indexBuffer = this.quadIndices.getBuffer(6);

		try (RenderPass renderPass = RenderSystem.getDevice()
				.createCommandEncoder()
				.createRenderPass(() -> "Sky sun", color, Optional.empty(), depth, OptionalDouble.empty())) {
			renderPass.setPipeline(RenderPipelines.CELESTIAL);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", dynamicTransforms);
			renderPass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
			renderPass.setVertexBuffer(0, this.sunBuffer.slice());
			renderPass.setIndexBuffer(indexBuffer, this.quadIndices.type());
			renderPass.drawIndexed(6, 1, 0, 0, 0);
		}

		modelViewStack.popMatrix();
	}

	private void renderMoon(MoonPhase moonPhase, float rainBrightness, PoseStack poseStack) {
		int baseVertex = moonPhase.index() * 4;
		Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
		modelViewStack.pushMatrix();
		modelViewStack.mul(poseStack.last().pose());
		modelViewStack.translate(0.0F, 100.0F, 0.0F);
		modelViewStack.scale(20.0F, 1.0F, 20.0F);
		GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms()
				.writeTransform(new Matrix4f(modelViewStack), new Vector4f(1.0F, 1.0F, 1.0F, rainBrightness));
		GpuTextureView color = this.renderTarget.getColorTextureView();
		GpuTextureView depth = this.renderTarget.getDepthTextureView();
		GpuBuffer indexBuffer = this.quadIndices.getBuffer(6);

		try (RenderPass renderPass = RenderSystem.getDevice()
				.createCommandEncoder()
				.createRenderPass(() -> "Sky moon", color, Optional.empty(), depth, OptionalDouble.empty())) {
			renderPass.setPipeline(RenderPipelines.CELESTIAL);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", dynamicTransforms);
			renderPass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
			renderPass.setVertexBuffer(0, this.moonBuffer.slice());
			renderPass.setIndexBuffer(indexBuffer, this.quadIndices.type());
			renderPass.drawIndexed(6, 1, 0, baseVertex, 0);
		}

		modelViewStack.popMatrix();
	}
}
