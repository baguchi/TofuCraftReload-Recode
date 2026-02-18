package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.TofunianStatueBlock;
import baguchi.tofucraft.blockentity.TofunianStatueBlockEntity;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TofunianStatueModel;
import baguchi.tofucraft.client.render.blockentity.state.TofunianStateRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TofunianStatueRender implements BlockEntityRenderer<TofunianStatueBlockEntity, TofunianStateRenderState> {
	public static final Identifier TEXTURES = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian_statue.png");
	private final TofunianStatueModel tofunianModel;

	public TofunianStatueRender(BlockEntityRendererProvider.Context context) {
		this.tofunianModel = new TofunianStatueModel(context.bakeLayer(TofuModelLayers.TOFUNIAN));
	}

	public TofunianStatueRender(EntityModelSet context) {
		this.tofunianModel = new TofunianStatueModel(context.bakeLayer(TofuModelLayers.TOFUNIAN));
	}
	@Override
	public void extractRenderState(TofunianStatueBlockEntity p_445916_, TofunianStateRenderState p_447093_, float p_446851_, Vec3 p_445788_, @Nullable ModelFeatureRenderer.CrumblingOverlay p_446944_) {
		BlockEntityRenderer.super.extractRenderState(p_445916_, p_447093_, p_446851_, p_445788_, p_446944_);
		p_447093_.direction = p_445916_.getBlockState().getValue(TofunianStatueBlock.FACING);
	}

	@Override
	public TofunianStateRenderState createRenderState() {
		return new TofunianStateRenderState();
	}

	@Override
	public void submit(TofunianStateRenderState tofunianStateRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		float f = tofunianStateRenderState.direction.getOpposite().toYRot();
		poseStack.pushPose();
		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.translate(0.0F, -1.501F, 0.0F);
		poseStack.translate(-0.5F, 0.0F, 0.5F);
		poseStack.mulPose(Axis.YP.rotationDegrees(f));
		submitNodeCollector.submitModel(this.tofunianModel, tofunianStateRenderState.direction, poseStack, RenderTypes.entityCutout(TEXTURES), tofunianStateRenderState.lightCoords, OverlayTexture.NO_OVERLAY, 0, null);
		poseStack.popPose();
	}
}
