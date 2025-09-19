package baguchi.tofucraft.client.render.special;

import baguchi.tofucraft.client.render.blockentity.TofunianStatueRender;
import baguchi.tofucraft.client.render.blockentity.state.TofunianStateRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Set;


public class TofunianStatueSpecialRenderer implements NoDataSpecialModelRenderer {
	private final TofunianStatueRender tofunianStatueRender;

	public TofunianStatueSpecialRenderer(TofunianStatueRender p_386864_) {
		this.tofunianStatueRender = p_386864_;
	}

	@Override
	public void getExtents(Set<Vector3f> set) {

	}

	@Override
	public void submit(ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int i1, boolean b) {
		this.tofunianStatueRender.renderInHand(new TofunianStateRenderState(), poseStack, submitNodeCollector);
	}


	public static record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<TofunianStatueSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(TofunianStatueSpecialRenderer.Unbaked::new);

		@Override
		public @Nullable SpecialModelRenderer<?> bake(BakingContext bakingContext) {
			return new TofunianStatueSpecialRenderer(new TofunianStatueRender(bakingContext.entityModelSet()));
		}

		@Override
		public MapCodec<TofunianStatueSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}