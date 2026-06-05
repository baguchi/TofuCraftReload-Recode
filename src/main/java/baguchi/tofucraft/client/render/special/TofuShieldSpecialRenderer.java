package baguchi.tofucraft.client.render.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class TofuShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {
	private final Identifier identifier;
	private final ShieldModel model;

	public TofuShieldSpecialRenderer(Identifier identifier, ShieldModel p_386724_) {
		this.identifier = identifier;
		this.model = p_386724_;
	}

	@Nullable
	public DataComponentMap extractArgument(ItemStack p_387204_) {
		return p_387204_.immutableComponents();
	}


	@Override
	public void submit(@org.jspecify.annotations.Nullable DataComponentMap typedDataComponents, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int i1, boolean b, int i2) {
		poseStack.pushPose();
		poseStack.scale(1.0F, -1.0F, -1.0F);

		submitNodeCollector.submitModelPart(this.model.handle(), poseStack, this.model.renderType().apply(identifier), i, i1, null);
		submitNodeCollector.submitModelPart(this.model.plate(), poseStack, this.model.renderType().apply(identifier), i, i1, null);

		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> p_470829_) {

	}

	public static record Unbaked(Identifier texture) implements SpecialModelRenderer.Unbaked<DataComponentMap> {
		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(Identifier.CODEC.fieldOf("texture").forGetter(Unbaked::texture)).apply(i, Unbaked::new));

		@Override
		public @org.jspecify.annotations.Nullable SpecialModelRenderer<DataComponentMap> bake(BakingContext bakingContext) {
			return new TofuShieldSpecialRenderer(this.texture, new ShieldModel(bakingContext.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
		}

		@Override
		public MapCodec<TofuShieldSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
