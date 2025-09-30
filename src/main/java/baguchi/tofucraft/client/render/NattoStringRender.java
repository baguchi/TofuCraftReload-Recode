package baguchi.tofucraft.client.render;

import baguchi.tofucraft.client.render.state.NattoStringRenderState;
import baguchi.tofucraft.entity.projectile.NattoStringEntity;
import baguchi.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;


public class NattoStringRender<E extends NattoStringEntity, T extends NattoStringRenderState> extends EntityRenderer<E, T> {
	private final float scale;
	private final boolean fullBright;
	private final ItemModelResolver itemModelResolver;

	public NattoStringRender(EntityRendererProvider.Context context, float scale, boolean bright) {
		super(context);
		this.scale = scale;
		this.fullBright = bright;
		this.itemModelResolver = context.getItemModelResolver();
	}

	protected int getBlockLightLevel(E p_116092_, BlockPos p_116093_) {
		return this.fullBright ? 15 : super.getBlockLightLevel(p_116092_, p_116093_);
	}

	@Override
	public T createRenderState() {
		return (T) new NattoStringRenderState();
	}

	@Override
	public void extractRenderState(E p_362104_, T p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		this.itemModelResolver.updateForNonLiving(p_361028_.itemStackRenderState, TofuItems.NATTO_COBWEB.get().getDefaultInstance(), ItemDisplayContext.GROUND, p_362104_);

	}

	@Override
	public void submit(T renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		poseStack.pushPose();
		poseStack.scale(this.scale, this.scale, this.scale);
		poseStack.mulPose(cameraRenderState.orientation);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
		renderState.itemStackRenderState.submit(poseStack, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor);
		poseStack.popPose();

		super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
	}
}
