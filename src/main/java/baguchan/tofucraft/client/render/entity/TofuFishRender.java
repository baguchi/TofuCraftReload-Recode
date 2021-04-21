package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.entity.TofuFishEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CodModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuFishRender extends MobRenderer<TofuFishEntity, CodModel<TofuFishEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("tofucraft:textures/entity/tofufish.png");

	public TofuFishRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, (EntityModel) new CodModel(), 0.3F);
	}

	protected void setupRotations(TofuFishEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.func_225621_a_((LivingEntity) entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.func_76126_a(0.6F * ageInTicks);
		matrixStackIn.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(f));
		if (!entityLiving.func_70090_H()) {
			matrixStackIn.func_227861_a_(0.10000000149011612D, 0.10000000149011612D, -0.10000000149011612D);
			matrixStackIn.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(90.0F));
		}
	}

	public ResourceLocation getTextureLocation(TofuFishEntity entity) {
		return TEXTURE;
	}
}
