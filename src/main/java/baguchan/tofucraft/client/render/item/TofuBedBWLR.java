package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.blockentity.TofuBedBlockEntity;
import baguchan.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public class TofuBedBWLR extends BlockEntityWithoutLevelRenderer {

	public TofuBedBWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pOverlay) {
		Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new TofuBedBlockEntity(BlockPos.ZERO, TofuBlocks.TOFUBED.get().defaultBlockState()), pPoseStack, pBuffer, pPackedLight, pOverlay);
	}
}