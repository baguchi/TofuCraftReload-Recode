package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class TofuShieldBWLR extends BlockEntityWithoutLevelRenderer {
	private ShieldModel shieldModel;

	public TofuShieldBWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
		this.shieldModel = new ShieldModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SHIELD));
	}

	@Override
	public void renderByItem(ItemStack pStack, ItemDisplayContext pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pOverlay) {
		if (pStack.is(TofuItems.TOFU_SHIELD.get())) {
			pPoseStack.pushPose();
			pPoseStack.scale(1.0F, -1.0F, -1.0F);
			VertexConsumer vertexconsumer = ItemRenderer.getFoilBuffer(pBuffer, this.shieldModel.renderType(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofumetal_shield.png")), true, pStack.hasFoil());
			this.shieldModel.handle().render(pPoseStack, vertexconsumer, pPackedLight, pOverlay);
			this.shieldModel.plate().render(pPoseStack, vertexconsumer, pPackedLight, pOverlay);

			pPoseStack.popPose();
		}
		if (pStack.is(TofuItems.REFLECT_TOFU_SHIELD.get())) {
			pPoseStack.pushPose();
			pPoseStack.scale(1.0F, -1.0F, -1.0F);
			VertexConsumer vertexconsumer = ItemRenderer.getFoilBuffer(pBuffer, this.shieldModel.renderType(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/reflect_tofu_shield.png")), true, pStack.hasFoil());
			this.shieldModel.handle().render(pPoseStack, vertexconsumer, pPackedLight, pOverlay);
			this.shieldModel.plate().render(pPoseStack, vertexconsumer, pPackedLight, pOverlay);

			pPoseStack.popPose();

			pPoseStack.pushPose();
			pPoseStack.scale(1.0F, -1.0F, -1.0F);
			VertexConsumer vertexconsumer2 = ItemRenderer.getFoilBuffer(pBuffer, RenderType.eyes(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/reflect_tofu_shield_overlay.png")), true, pStack.hasFoil());
			this.shieldModel.plate().render(pPoseStack, vertexconsumer2, pPackedLight, pOverlay);

			pPoseStack.popPose();
		}
	}
}