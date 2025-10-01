package baguchi.tofucraft.client.render.special;

import baguchi.tofucraft.client.render.blockentity.FoodPlateRender;
import baguchi.tofucraft.client.render.blockentity.state.FoodPlateRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Set;


public class FoodPlateSpecialRenderer implements SpecialModelRenderer<ItemContainerContents> {
	private final FoodPlateRender foodPlateRender;
	private final FoodPlateRenderState renderState;

	public FoodPlateSpecialRenderer(FoodPlateRender p_386864_, FoodPlateRenderState renderState) {
		this.foodPlateRender = p_386864_;
		this.renderState = renderState;
	}

	@Override
	public void submit(@org.jetbrains.annotations.Nullable ItemContainerContents itemContainerContents, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int i1, boolean b, int i2) {

		if (itemContainerContents != null && itemContainerContents.getSlots() > 0) {
			this.renderState.plateState = Block.byItem(itemContainerContents.getStackInSlot(0).getItem()).defaultBlockState();

			//Minecraft.getInstance().getItemModelResolver().updateForTopItem(this.renderState.plateItem, itemContainerContents.getStackInSlot(0), ItemDisplayContext.GROUND, null, null, 0);

			this.renderState.candle = itemContainerContents.getStackInSlot(0).is(ItemTags.CANDLES);
			this.renderState.cake = Block.byItem(itemContainerContents.getStackInSlot(0).getItem()) instanceof CakeBlock;
			this.renderState.fire = false;
			this.renderState.renderAmount = foodPlateRender.getRenderAmount(itemContainerContents.getStackInSlot(0));
		}
		this.renderState.lightCoords = i;
		this.renderState.direction = Direction.NORTH;

		this.foodPlateRender.renderInHand(renderState, poseStack, submitNodeCollector);

	}

	@Override
	public void getExtents(Set<Vector3f> p_428562_) {
		PoseStack posestack = new PoseStack();
		posestack.translate(0F, 1F, 0F);

		float minModel = 3 / 16F;
		float maxY = 1 / 16F;
		float maxModel = 13 / 16F;
		Vector3f vector3f = new Vector3f();
		vector3f.add(minModel, 0, minModel);
		vector3f.add(maxModel, 0, maxModel);
		vector3f.add(maxModel, 1 / 16.0F, maxModel);
		vector3f.add(minModel, 1 / 16.0F, minModel);
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
		public @org.jetbrains.annotations.Nullable SpecialModelRenderer<?> bake(BakingContext bakingContext) {


			FoodPlateRender foodPlateRender1 = new FoodPlateRender();

			FoodPlateRenderState foodPlateRenderState = foodPlateRender1.createRenderState();

			return new FoodPlateSpecialRenderer(foodPlateRender1, foodPlateRenderState);
		}

		@Override
		public MapCodec<FoodPlateSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

	}
}