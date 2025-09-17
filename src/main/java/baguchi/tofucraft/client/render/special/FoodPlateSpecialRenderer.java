package baguchi.tofucraft.client.render.special;

import baguchi.tofucraft.client.render.blockentity.FoodPlateRender;
import baguchi.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Set;


public class FoodPlateSpecialRenderer implements SpecialModelRenderer<ItemContainerContents> {
	private final FoodPlateRender foodPlateRender;

	public FoodPlateSpecialRenderer(FoodPlateRender p_386864_) {
		this.foodPlateRender = p_386864_;
	}

	@Override
	public void render(@org.jetbrains.annotations.Nullable ItemContainerContents itemContainerContents, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1, boolean b) {
		this.foodPlateRender.renderInHand(TofuBlocks.FOODPLATE.asOptional(), itemContainerContents, itemDisplayContext, poseStack, multiBufferSource, i, i1);

	}

	@Override
	public void getExtents(Set<Vector3f> p_428562_) {
		PoseStack posestack = new PoseStack();
		posestack.translate(0F, 1F, 0F);

		float minModel = 3 / 16F;
		float maxY = 1 / 16F;
		float maxModel = 13 / 16F;

		float f = minModel / 16.0F;
		float f1 = 0 / 16.0F;
		float f2 = minModel / 16.0F;
		Vector3f vector3f = new Vector3f();
		vector3f.add(-1F, 0, -1);
		vector3f.add(1F, 0, 1);
		vector3f.add(1F, 1 / 16.0F, 1);
		vector3f.add(-1F, 1 / 16.0F, -1);
		p_428562_.add(vector3f);

		//this.modelBase.root().getExtentsForGui(posestack, p_428562_);
	}

	@Nullable
	public ItemContainerContents extractArgument(ItemStack p_386678_) {
		return p_386678_.get(DataComponents.CONTAINER);
	}


	public record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<FoodPlateSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(FoodPlateSpecialRenderer.Unbaked::new);

		@Override
		public MapCodec<FoodPlateSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(EntityModelSet p_386741_) {
			return new FoodPlateSpecialRenderer(new FoodPlateRender(p_386741_));
		}
	}
}