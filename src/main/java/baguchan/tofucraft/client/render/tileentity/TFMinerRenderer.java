package baguchan.tofucraft.client.render.tileentity;

import baguchan.tofucraft.blockentity.tfenergy.TFMinerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;

public class TFMinerRenderer implements BlockEntityRenderer<TFMinerBlockEntity> {
	public TFMinerRenderer(BlockEntityRendererProvider.Context p_173675_) {
	}

	public void render(TFMinerBlockEntity p_112583_, float p_112584_, PoseStack p_112585_, MultiBufferSource p_112586_, int p_112587_, int p_112588_) {
		if (Minecraft.getInstance().player.canUseGameMasterBlocks() || Minecraft.getInstance().player.isSpectator()) {
			BlockPos blockpos = p_112583_.getStructurePos();
			Vec3i vec3i = p_112583_.getStructureSize();
			if (vec3i.getX() >= 1 && vec3i.getY() >= 1 && vec3i.getZ() >= 1) {
				double d0 = (double) blockpos.getX();
				double d1 = (double) blockpos.getZ();
				double d5 = (double) blockpos.getY();
				double d8 = d5 + (double) vec3i.getY();
				double d2;
				double d3;
				d2 = (double) vec3i.getX();
				d3 = (double) vec3i.getZ();

				double d4;
				double d6;
				double d7;
				double d9;
				d4 = d2 < 0.0 ? d0 + 1.0 : d0;
				d6 = d3 < 0.0 ? d1 + 1.0 : d1;
				d7 = d4 + d2;
				d9 = d6 + d3;


				float f = 1.0F;
				float f1 = 0.9F;
				float f2 = 0.5F;
				VertexConsumer vertexconsumer = p_112586_.getBuffer(RenderType.lines());
				LevelRenderer.renderLineBox(p_112585_, vertexconsumer, d4, d5, d6, d7, d8, d9, 0.9F, 0.9F, 0.9F, 1.0F, 0.5F, 0.5F, 0.5F);

			}
		}
	}

	public boolean shouldRenderOffScreen(TFMinerBlockEntity p_112581_) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 96;
	}

	@Override
	public net.minecraft.world.phys.AABB getRenderBoundingBox(TFMinerBlockEntity blockEntity) {
		return INFINITE_EXTENT_AABB;
	}
}
