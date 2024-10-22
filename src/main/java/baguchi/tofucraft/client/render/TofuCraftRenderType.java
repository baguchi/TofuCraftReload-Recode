package baguchi.tofucraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import org.jetbrains.annotations.Nullable;

public class TofuCraftRenderType extends RenderType {
	public TofuCraftRenderType(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
		super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
	}

	public static ParticleRenderType NO_TRANSLUCENT_DEPTH = new ParticleRenderType() {

		@Nullable
		@Override
		public BufferBuilder begin(Tesselator p_350949_, TextureManager p_107437_) {
			RenderSystem.depthMask(false);
			RenderSystem.disableCull();
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			return p_350949_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
		}
		public String toString() {
			return "NO_TRANSLUCENT_DEPTH";
		}
	};
}
