package baguchi.tofucraft.client.render.dimension;

import baguchi.tofucraft.client.TofuWorldTextureManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.renderpearl.api.buffers.GpuBufferSlice;
import com.mojang.renderpearl.api.commands.RenderPass;
import com.mojang.renderpearl.api.textures.GpuTextureView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.neoforged.neoforge.client.CustomSkyboxRenderer;
import org.joml.Matrix4fc;

import java.util.Optional;
import java.util.OptionalDouble;

public class TofuWorldSpecialEffect implements CustomSkyboxRenderer {

	@Override
	public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4fc modelViewMatrix, GpuBufferSlice skyFog) {
		TofuWorldRenderer tofuWorldRenderer = TofuWorldTextureManager.INSTANCE.getTofuWorldRenderer();
		RenderSystem.setShaderFog(skyFog);
		GpuTextureView colorTexture = Minecraft.getInstance().gameRenderer.mainRenderTarget().getColorTextureView();
		GpuTextureView depthTexture = Minecraft.getInstance().gameRenderer.mainRenderTarget().getDepthTextureView();

		if (tofuWorldRenderer != null) {
			try (RenderPass renderPass = RenderSystem.getDevice()
					.createCommandEncoder()
					.createRenderPass(() -> "Sky", colorTexture, Optional.empty(), depthTexture, OptionalDouble.empty())) {
				PoseStack poseStack = new PoseStack();
				RenderSystem.bindDefaultUniforms(renderPass);

				tofuWorldRenderer.renderTofuSunMoonAndStars(
						renderPass,
						poseStack, skyRenderState.sunAngle,
						skyRenderState.moonAngle,
						skyRenderState.starAngle,
						skyRenderState.moonPhase,
						skyRenderState.rainBrightness,
						skyRenderState.starBrightness
				);
			}
		}
		return true;
	}
}
