package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.block.TofunianStatueBlock;
import baguchan.tofucraft.blockentity.TofunianStatueBlockEntity;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofunianModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TofunianStatueRender implements BlockEntityRenderer<TofunianStatueBlockEntity> {
	public static final ResourceLocation TEXTURES = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofunian_statue.png");
	private final TofunianModel<?> tofunianModel;

	public TofunianStatueRender(BlockEntityRendererProvider.Context context) {
		this.tofunianModel = new TofunianModel<>(context.bakeLayer(TofuModelLayers.TOFUNIAN));
	}

	@Override
	public void render(TofunianStatueBlockEntity plateBlockEntity, float p_112308_, PoseStack poseStack, MultiBufferSource p_112310_, int p_112311_, int p_112312_) {
		float f = plateBlockEntity.getBlockState().getValue(TofunianStatueBlock.FACING).getOpposite().toYRot();
		poseStack.pushPose();
		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.translate(0.0F, -1.501F, 0.0F);
		poseStack.translate(-0.5F, 0.0F, 0.5F);
		poseStack.mulPose(Axis.YP.rotationDegrees(f));
		this.tofunianModel.renderToBuffer(poseStack, p_112310_.getBuffer(RenderType.entityCutoutNoCull(TEXTURES)), p_112311_, p_112312_, 1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
	}
}
