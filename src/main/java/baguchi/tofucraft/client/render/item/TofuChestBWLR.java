package baguchi.tofucraft.client.render.item;

import baguchi.tofucraft.blockentity.TofuChestBlockEntity;
import baguchi.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class TofuChestBWLR extends BlockEntityWithoutLevelRenderer {

	public TofuChestBWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack pStack, ItemDisplayContext pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pOverlay) {
		Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new TofuChestBlockEntity(BlockPos.ZERO, TofuBlocks.TOFUCHEST.get().defaultBlockState()), pPoseStack, pBuffer, pPackedLight, pOverlay);
	}
}