package baguchi.tofucraft.client.render.special;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Set;

public class TofuShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {
	private final ShieldModel model;

	public TofuShieldSpecialRenderer(ShieldModel p_386724_) {
		this.model = p_386724_;
	}

	@Nullable
	public DataComponentMap extractArgument(ItemStack p_387204_) {
		return p_387204_.immutableComponents();
	}

	@Override
	public void submit(@org.jetbrains.annotations.Nullable DataComponentMap typedDataComponents, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int i1, boolean b) {
		poseStack.pushPose();
		poseStack.scale(1.0F, -1.0F, -1.0F);

		submitNodeCollector.submitModelPart(this.model.handle(), poseStack, this.model.renderType(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofumetal_shield.png")), i, i1, null);
		submitNodeCollector.submitModelPart(this.model.plate(), poseStack, this.model.renderType(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofumetal_shield.png")), i, i1, null);

		poseStack.popPose();
	}

	@Override
	public void getExtents(Set<Vector3f> set) {

	}


	public static record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final TofuShieldSpecialRenderer.Unbaked INSTANCE = new TofuShieldSpecialRenderer.Unbaked();
		public static final MapCodec<TofuShieldSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

		@Override
		public @org.jetbrains.annotations.Nullable SpecialModelRenderer<?> bake(BakingContext bakingContext) {
			return new TofuShieldSpecialRenderer(new ShieldModel(bakingContext.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
		}

		@Override
		public MapCodec<TofuShieldSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
