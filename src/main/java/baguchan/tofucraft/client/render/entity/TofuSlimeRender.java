package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.entity.TofuSlimeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuSlimeRender extends MobRenderer<TofuSlimeEntity, SlimeModel<TofuSlimeEntity>> {
	private static final ResourceLocation SLIME_LOCATION = new ResourceLocation("tofucraft", "textures/entity/tofuslime.png");

	public TofuSlimeRender(EntityRendererManager p_i47193_1_) {
		super(p_i47193_1_, new SlimeModel(16), 0.25F);
		func_177094_a((LayerRenderer) new SlimeGelLayer(this));
	}

	public void render(TofuSlimeEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
		this.field_76989_e = 0.25F * p_225623_1_.func_70809_q();
		super.func_225623_a_((MobEntity) p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
	}

	protected void scale(TofuSlimeEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
		float f = 0.999F;
		p_225620_2_.func_227862_a_(0.999F, 0.999F, 0.999F);
		p_225620_2_.func_227861_a_(0.0D, 0.0010000000474974513D, 0.0D);
		float f1 = p_225620_1_.func_70809_q();
		float f2 = MathHelper.func_219799_g(p_225620_3_, p_225620_1_.field_70812_c, p_225620_1_.field_70811_b) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		p_225620_2_.func_227862_a_(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	public ResourceLocation getTextureLocation(TofuSlimeEntity p_110775_1_) {
		return SLIME_LOCATION;
	}
}
