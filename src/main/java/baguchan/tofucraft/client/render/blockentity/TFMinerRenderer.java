package baguchan.tofucraft.client.render.blockentity;

import baguchan.tofucraft.blockentity.tfenergy.TFMinerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class TFMinerRenderer implements BlockEntityRenderer<TFMinerBlockEntity> {
	public TFMinerRenderer(BlockEntityRendererProvider.Context p_173675_) {
	}

	public void render(TFMinerBlockEntity p_112583_, float p_112584_, PoseStack p_112585_, MultiBufferSource p_112586_, int p_112587_, int p_112588_) {
		BlockPos blockpos = p_112583_.getStructurePos();
		BlockPos blockpos2 = p_112583_.getWorkingBlockPos();
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
		//target beam rendering
		if (blockpos2 != null) {

			VertexConsumer vertexconsumer = p_112586_.getBuffer(RenderType.lines());
			Matrix4f matrix4f = p_112585_.last().pose();
			Matrix3f matrix3f = p_112585_.last().normal();
			BlockPos workPos = blockpos2;
			BlockPos originalPos = p_112583_.getBlockPos();
			float x = (float) (workPos.getX() - originalPos.getX()) + 0.5F;
			float y = (float) (workPos.getY() - originalPos.getY()) + 0.5F;
			float z = (float) (workPos.getZ() - originalPos.getZ()) + 0.5F;
			vertexconsumer.vertex(matrix4f, (float) 0.5F, (float) 1.5F, (float) 0.5F).color(0.9F, 0.9F, 0.9F, 1.0F).normal(p_112585_.last(), 0, 0, 0).endVertex();
			vertexconsumer.vertex(matrix4f, x, y, (float) z).color(0.9F, 0.9F, 0.9F, 1.0F).normal(p_112585_.last(), 0, 0, 0).endVertex();

			//box
			LevelRenderer.renderLineBox(p_112585_, vertexconsumer, -0.3F + 0.5F, 1.5F - 0.3F, -0.3F + 0.5F, 0.3F + 0.5F, 1.5F + 0.3F, 0.3F + 0.5F, 0.9F, 0.9F, 0.9F, 1.0F, 0.5F, 0.5F, 0.5F);


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
