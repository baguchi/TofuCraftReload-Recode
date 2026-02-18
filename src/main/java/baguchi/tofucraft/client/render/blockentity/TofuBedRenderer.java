package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.utils.TofuBedBlock;
import baguchi.tofucraft.blockentity.TofuBedBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.state.BedRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;


public class TofuBedRenderer implements BlockEntityRenderer<TofuBedBlockEntity, BedRenderState> {
	public static final Identifier BED_TEXTURES = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/bed/tofubed.png");
	private final Model.Simple headModel;
	private final Model.Simple footModel;

	public TofuBedRenderer(BlockEntityRendererProvider.Context context) {
		this.headModel = new Model.Simple(context.bakeLayer(ModelLayers.BED_HEAD), RenderTypes::entitySolid);
		this.footModel = new Model.Simple(context.bakeLayer(ModelLayers.BED_FOOT), RenderTypes::entitySolid);

	}

	private void renderPiece(PoseStack p_173542_, MultiBufferSource p_173543_, ModelPart p_173544_, Direction p_173545_, int p_173547_, int p_173548_, boolean p_173549_) {
		p_173542_.pushPose();
		p_173542_.translate(0.0D, 0.5625D, p_173549_ ? -1.0D : 0.0D);
		p_173542_.mulPose(Axis.XP.rotationDegrees(90.0F));
		p_173542_.translate(0.5D, 0.5D, 0.5D);
		p_173542_.mulPose(Axis.ZP.rotationDegrees(180.0F + p_173545_.toYRot()));
		p_173542_.translate(-0.5D, -0.5D, -0.5D);
		VertexConsumer var9 = p_173543_.getBuffer(RenderTypes.entitySolid(BED_TEXTURES));
		p_173544_.render(p_173542_, var9, p_173547_, p_173548_);
		p_173542_.popPose();
	}

	@Override
	public BedRenderState createRenderState() {
		return new BedRenderState();
	}

	@Override
	public void extractRenderState(TofuBedBlockEntity p_445886_, BedRenderState p_447090_, float p_446336_, Vec3 p_445891_, @Nullable ModelFeatureRenderer.CrumblingOverlay p_446152_) {
		BlockEntityRenderState.extractBase(p_445886_, p_447090_, p_446152_);
		//p_447090_.color = p_445886_.getColor();
		p_447090_.facing = p_445886_.getBlockState().getValue(BedBlock.FACING);
		p_447090_.isHead = p_445886_.getBlockState().getValue(BedBlock.PART) == BedPart.HEAD;
		if (p_445886_.getLevel() != null) {
			DoubleBlockCombiner.NeighborCombineResult<? extends TofuBedBlockEntity> neighborcombineresult = DoubleBlockCombiner.combineWithNeigbour(TofuBlockEntitys.TOFUBED.get(), TofuBedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, p_445886_.getBlockState(), p_445886_.getLevel(), p_445886_.getBlockPos(), (p_112202_, p_112203_) -> false);
			p_447090_.lightCoords = ((Int2IntFunction) neighborcombineresult.apply(new BrightnessCombiner())).get(p_447090_.lightCoords);
		}
	}

	@Override
	public void submit(BedRenderState p_445609_, PoseStack p_439782_, SubmitNodeCollector p_439369_, CameraRenderState p_451216_) {
		SpriteId bedSprite = Sheets.getBedSprite(p_445609_.color);
		this.submitPiece(p_439782_, p_439369_, p_445609_.isHead ? this.headModel : this.footModel, p_445609_.facing, bedSprite, p_445609_.lightCoords, OverlayTexture.NO_OVERLAY, false, p_445609_.breakProgress);
	}

	public void submitSpecial(PoseStack p_439640_, SubmitNodeCollector p_439587_, int p_439564_, int p_440267_, SpriteId p_440126_) {
		this.submitPiece(p_439640_, p_439587_, this.headModel, Direction.SOUTH, p_440126_, p_439564_, p_440267_, false, (ModelFeatureRenderer.CrumblingOverlay) null);
		this.submitPiece(p_439640_, p_439587_, this.footModel, Direction.SOUTH, p_440126_, p_439564_, p_440267_, true, (ModelFeatureRenderer.CrumblingOverlay) null);
	}

	private void submitPiece(PoseStack p_440523_, SubmitNodeCollector p_440584_, Model.Simple p_440701_, Direction p_439931_, SpriteId p_439303_, int p_438920_, int p_439582_, boolean p_440123_, @Nullable ModelFeatureRenderer.CrumblingOverlay p_439398_) {
		p_440523_.pushPose();
		preparePose(p_440523_, p_440123_, p_439931_);
		p_440584_.submitModel(p_440701_, Unit.INSTANCE, p_440523_, RenderTypes.entityCutout(BED_TEXTURES), p_438920_, p_439582_, -1, null, 0, p_439398_);
		p_440523_.popPose();
	}

	private static void preparePose(PoseStack p_428361_, boolean p_428252_, Direction p_428231_) {
		p_428361_.translate(0.0F, 0.5625F, p_428252_ ? -1.0F : 0.0F);
		p_428361_.mulPose(Axis.XP.rotationDegrees(90.0F));
		p_428361_.translate(0.5F, 0.5F, 0.5F);
		p_428361_.mulPose(Axis.ZP.rotationDegrees(180.0F + p_428231_.toYRot()));
		p_428361_.translate(-0.5F, -0.5F, -0.5F);
	}

	/*public void getExtents(Set<Vector3f> p_428359_) {
		PoseStack posestack = new PoseStack();
		preparePose(posestack, false, Direction.SOUTH);
		this.headModel.root().getExtentsForGui(posestack, p_428359_);
		posestack.setIdentity();
		preparePose(posestack, true, Direction.SOUTH);
		this.footModel.root().getExtentsForGui(posestack, p_428359_);
	}*/
}
