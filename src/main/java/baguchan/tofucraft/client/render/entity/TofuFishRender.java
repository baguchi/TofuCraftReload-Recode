package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.entity.TofuFishEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CodModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuFishRender extends MobRenderer<TofuFishEntity, CodModel<TofuFishEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("tofucraft:textures/entity/tofufish.png");

	public TofuFishRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CodModel(), 0.3F);
	}

	protected void setupRotations(TofuFishEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
		super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
		float f = 4.3F * MathHelper.sin(0.6F * p_225621_3_);
		p_225621_2_.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!p_225621_1_.isInWater()) {
			p_225621_2_.translate(0.1F, 0.1F, -0.1F);
			p_225621_2_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}

	}

	public ResourceLocation getTextureLocation(TofuFishEntity entity) {
		return TEXTURE;
	}
}
