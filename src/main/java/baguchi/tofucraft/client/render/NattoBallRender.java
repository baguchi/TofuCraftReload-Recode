package baguchi.tofucraft.client.render;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.state.NattoBallRenderState;
import baguchi.tofucraft.entity.projectile.NattoBallEntity;
import baguchi.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;


public class NattoBallRender extends EntityRenderer<NattoBallEntity, NattoBallRenderState> {
	private static final Identifier TEXTURE_LOCATION = TofuCraftReload.prefix("textures/entity/projectile/natto_projectile.png");
	private static final RenderType RENDER_TYPE = RenderTypes.entityCutout(TEXTURE_LOCATION);
	private final ItemModelResolver itemModelResolver;

	public NattoBallRender(EntityRendererProvider.Context context) {
		super(context);
		this.itemModelResolver = context.getItemModelResolver();
	}


	@Override
	protected int getBlockLightLevel(NattoBallEntity p_116092_, BlockPos p_116093_) {
		return 15;
	}

	@Override
	public NattoBallRenderState createRenderState() {
		return new NattoBallRenderState();
	}

	@Override
	public void extractRenderState(NattoBallEntity p_362104_, NattoBallRenderState p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		this.itemModelResolver.updateForNonLiving(p_361028_.itemStackRenderState, TofuBlocks.NATTO_COBWEB.get().asItem().getDefaultInstance(), ItemDisplayContext.GROUND, p_362104_);

	}

	@Override
	public void submit(NattoBallRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		poseStack.pushPose();
		poseStack.scale(1, 1, 1);
		poseStack.rotate(cameraRenderState.orientation);
		poseStack.rotateDegrees(Axis.YP, 180.0F);
		renderState.itemStackRenderState.submit(poseStack, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor);
		poseStack.popPose();

		super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
	}
}
