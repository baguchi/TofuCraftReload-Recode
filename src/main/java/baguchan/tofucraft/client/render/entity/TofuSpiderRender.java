package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.client.model.TofuSpiderModel;
import baguchan.tofucraft.entity.TofuSpiderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuSpiderRender extends MobRenderer<TofuSpiderEntity, TofuSpiderModel<TofuSpiderEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("tofucraft:textures/entity/tofuspider/tofuspider.png");

	public TofuSpiderRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new TofuSpiderModel(), 0.8F);
	}

	protected void scale(TofuSpiderEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
		p_225620_2_.func_227862_a_(1.3F, 1.3F, 1.3F);
		super.func_225620_a_((LivingEntity) p_225620_1_, p_225620_2_, p_225620_3_);
	}

	protected float getFlipDegrees(TofuSpiderEntity p_77037_1_) {
		return 180.0F;
	}

	public ResourceLocation getTextureLocation(TofuSpiderEntity entity) {
		return TEXTURE;
	}
}
