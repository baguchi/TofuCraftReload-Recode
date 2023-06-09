package baguchan.tofucraft.client.render;

import baguchan.tofucraft.entity.projectile.NattoStringEntity;
import baguchan.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NattoStringRender<T extends NattoStringEntity> extends EntityRenderer<T> {
	private final float scale;
	private final boolean fullBright;
	private final ItemRenderer itemRenderer;

	public NattoStringRender(EntityRendererProvider.Context context, float scale, boolean bright) {
		super(context);
		this.scale = scale;
		this.fullBright = bright;
		this.itemRenderer = context.getItemRenderer();
	}

	protected int getBlockLightLevel(T p_116092_, BlockPos p_116093_) {
		return this.fullBright ? 15 : super.getBlockLightLevel(p_116092_, p_116093_);
	}

	public void render(T p_116085_, float p_116086_, float p_116087_, PoseStack p_116088_, MultiBufferSource p_116089_, int p_116090_) {
		if (p_116085_.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(p_116085_) < 12.25D)) {
			p_116088_.pushPose();
			p_116088_.scale(this.scale, this.scale, this.scale);
			p_116088_.mulPose(this.entityRenderDispatcher.cameraOrientation());
			p_116088_.mulPose(Axis.YP.rotationDegrees(180.0F));
			this.itemRenderer.renderStatic(new ItemStack(TofuItems.NATTO_COBWEB.get()), ItemDisplayContext.GROUND, p_116090_, OverlayTexture.NO_OVERLAY, p_116088_, p_116089_, p_116085_.level(), p_116085_.getId());
			p_116088_.popPose();
			super.render(p_116085_, p_116086_, p_116087_, p_116088_, p_116089_, p_116090_);
		}
	}

	public ResourceLocation getTextureLocation(T p_116083_) {
		return TextureAtlas.LOCATION_BLOCKS;
	}
}
