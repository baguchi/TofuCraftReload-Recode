package baguchi.tofucraft.client.render.special;

import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TofunianStatueModel;
import baguchi.tofucraft.client.render.blockentity.TofunianStatueRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Set;


public class TofunianStatueSpecialRenderer implements NoDataSpecialModelRenderer {
	private final TofunianStatueModel model;

	public TofunianStatueSpecialRenderer(TofunianStatueModel p_386864_) {
		this.model = p_386864_;
	}

	@Override
	public void getExtents(Set<Vector3f> set) {
		PoseStack posestack = new PoseStack();
		this.model.root().getExtentsForGui(posestack, set);
	}

	@Override
	public void submit(ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int i1, boolean b) {
		poseStack.pushPose();
		poseStack.scale(-1.5F, -1.5F, 1.5F);
		poseStack.translate(-0.4, -1F, 0);
		submitNodeCollector.submitModel(this.model, Direction.SOUTH, poseStack, RenderType.entityCutoutNoCull(TofunianStatueRender.TEXTURES), i, i1, -1, null, 0, null);
		poseStack.popPose();
	}


	public record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<TofunianStatueSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(TofunianStatueSpecialRenderer.Unbaked::new);

		@Override
		public @Nullable SpecialModelRenderer<?> bake(BakingContext bakingContext) {

			TofunianStatueModel tofunianStatue = new TofunianStatueModel(bakingContext.entityModelSet().bakeLayer(TofuModelLayers.TOFUNIAN));
			return new TofunianStatueSpecialRenderer(tofunianStatue);
		}

		@Override
		public MapCodec<TofunianStatueSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}