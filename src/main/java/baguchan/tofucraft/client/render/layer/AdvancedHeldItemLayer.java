package baguchan.tofucraft.client.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AdvancedHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {
	public AdvancedHeldItemLayer(IEntityRenderer<T, M> p_i50934_1_) {
		super(p_i50934_1_);
	}

	public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, T p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
		boolean flag = (p_225628_4_.func_184591_cq() == HandSide.RIGHT);
		ItemStack itemstack = flag ? p_225628_4_.func_184592_cb() : p_225628_4_.func_184614_ca();
		ItemStack itemstack1 = flag ? p_225628_4_.func_184614_ca() : p_225628_4_.func_184592_cb();
		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			p_225628_1_.func_227860_a_();
			if ((func_215332_c()).field_217114_e) {
				float f = 0.5F;
				p_225628_1_.func_227861_a_(0.0D, 0.75D, 0.0D);
				p_225628_1_.func_227862_a_(0.5F, 0.5F, 0.5F);
			}
			renderArmWithItem(p_225628_4_, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, p_225628_1_, p_225628_2_, p_225628_3_);
			renderArmWithItem(p_225628_4_, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, p_225628_1_, p_225628_2_, p_225628_3_);
			p_225628_1_.func_227865_b_();
		}
	}

	private void renderArmWithItem(LivingEntity p_229135_1_, ItemStack p_229135_2_, ItemCameraTransforms.TransformType p_229135_3_, HandSide p_229135_4_, MatrixStack p_229135_5_, IRenderTypeBuffer p_229135_6_, int p_229135_7_) {
		if (!p_229135_2_.isEmpty()) {
			p_229135_5_.func_227860_a_();
			((IHasArm) func_215332_c()).func_225599_a_(p_229135_4_, p_229135_5_);
			p_229135_5_.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-90.0F));
			p_229135_5_.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
			boolean flag = (p_229135_4_ == HandSide.LEFT);
			p_229135_5_.func_227861_a_(((flag ? -1 : true) / 16.0F), 0.125D, -0.25D);
			Minecraft.func_71410_x().func_175597_ag().func_228397_a_(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
			p_229135_5_.func_227865_b_();
		}
	}
}
