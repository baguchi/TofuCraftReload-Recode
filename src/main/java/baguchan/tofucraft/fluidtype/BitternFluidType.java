package baguchan.tofucraft.fluidtype;

import baguchan.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.function.Consumer;

public class BitternFluidType extends FluidType {
	public BitternFluidType(Properties properties) {
		super(properties);
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = new ResourceLocation(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_FLOW = new ResourceLocation(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation(TofuCraftReload.MODID, "textures/block/bittern_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack) {
				ResourceLocation texture = this.getRenderOverlayTexture(mc);
				if (texture == null) return;
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderTexture(0, texture);
				BufferBuilder buffer = Tesselator.getInstance().getBuilder();
				BlockPos playerEyePos = BlockPos.containing(mc.player.getX(), mc.player.getEyeY(), mc.player.getZ());
				float brightness = LightTexture.getBrightness(mc.player.level.dimensionType(), mc.player.level.getMaxLocalRawBrightness(playerEyePos));
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();
				RenderSystem.setShaderColor(brightness, brightness, brightness, 0.8F);
				float uOffset = -mc.player.getYRot() / 64.0F;
				float vOffset = mc.player.getXRot() / 64.0F;
				Matrix4f pose = stack.last().pose();
				buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				buffer.vertex(pose, -1.0F, -1.0F, -0.5F).uv(4.0F + uOffset, 4.0F + vOffset).endVertex();
				buffer.vertex(pose, 1.0F, -1.0F, -0.5F).uv(uOffset, 4.0F + vOffset).endVertex();
				buffer.vertex(pose, 1.0F, 1.0F, -0.5F).uv(uOffset, vOffset).endVertex();
				buffer.vertex(pose, -1.0F, 1.0F, -0.5F).uv(4.0F + uOffset, vOffset).endVertex();
				BufferUploader.drawWithShader(buffer.end());
				RenderSystem.disableBlend();
			}
		});
	}
}
