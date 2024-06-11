package baguchan.tofucraft.fluidtype;

import baguchan.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.function.Consumer;

public class SoymilkHellFluidType extends FluidType {
	public SoymilkHellFluidType(FluidType.Properties properties) {
		super(properties);
	}

	@Override
	public boolean isVaporizedOnPlacement(Level level, BlockPos pos, FluidStack stack) {
		return false;
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/soymilk_hell_overlay.png");

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
				BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				BlockPos playerEyePos = BlockPos.containing(mc.player.getX(), mc.player.getEyeY(), mc.player.getZ());
				float brightness = LightTexture.getBrightness(mc.player.level().dimensionType(), mc.player.level().getMaxLocalRawBrightness(playerEyePos));
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();
				RenderSystem.setShaderColor(brightness, brightness, brightness, 0.8F);
				float uOffset = -mc.player.getYRot() / 64.0F;
				float vOffset = mc.player.getXRot() / 64.0F;
				Matrix4f pose = stack.last().pose();
				buffer.addVertex(pose, -1.0F, -1.0F, -0.5F).setUv(4.0F + uOffset, 4.0F + vOffset);
				buffer.addVertex(pose, 1.0F, -1.0F, -0.5F).setUv(uOffset, 4.0F + vOffset);
				buffer.addVertex(pose, 1.0F, 1.0F, -0.5F).setUv(uOffset, vOffset);
				buffer.addVertex(pose, -1.0F, 1.0F, -0.5F).setUv(4.0F + uOffset, vOffset);
				BufferUploader.drawWithShader(buffer.buildOrThrow());
				RenderSystem.disableBlend();
			}
		});
	}
}
