package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.TofunianStatueBlock;
import baguchi.tofucraft.blockentity.TofunianStatueBlockEntity;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TofunianStatueModel;
import baguchi.tofucraft.client.render.blockentity.state.TofunianStateRenderState;
import baguchi.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.Map;

public class TofunianStatueRender implements BlockEntityRenderer<TofunianStatueBlockEntity, TofunianStateRenderState> {
	public static final Identifier TEXTURES = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian_statue.png");
	private final TofunianStatueModel tofunianModel;
	private static final Map<Direction, Transformation> TRANSFORMATIONS = Util.makeEnumMap(Direction.class, TofunianStatueRender::createModelTransformation);

	public TofunianStatueRender(BlockEntityRendererProvider.Context context) {
		this.tofunianModel = new TofunianStatueModel(context.bakeLayer(TofuModelLayers.TOFUNIAN));
	}

	public TofunianStatueRender(EntityModelSet context) {
		this.tofunianModel = new TofunianStatueModel(context.bakeLayer(TofuModelLayers.TOFUNIAN));
	}

	public static Transformation modelTransformation(Direction facing) {
		return TRANSFORMATIONS.get(facing);
	}

	private static Transformation createModelTransformation(Direction facing) {
		return new Transformation(new Matrix4f().rotationAround(Axis.YP.rotationDegrees(-facing.toYRot()), 0.5F, 1F, 0.3F));
	}

	@Override
	public void extractRenderState(TofunianStatueBlockEntity blockEntity, TofunianStateRenderState p_447093_, float p_446851_, Vec3 p_445788_, @Nullable ModelFeatureRenderer.CrumblingOverlay p_446944_) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, p_447093_, p_446851_, p_445788_, p_446944_);
		boolean hasLevel = blockEntity.getLevel() != null;
		BlockState blockState = hasLevel ? blockEntity.getBlockState() : TofuBlocks.TOFUNIAN_STATUE.get().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		p_447093_.direction = blockState.getValue(TofunianStatueBlock.FACING);
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
