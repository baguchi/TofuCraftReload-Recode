package baguchan.tofucraft.client.model;

import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class TofunianModel<T extends TofunianEntity> extends BipedModel<T> {
	public TofunianModel() {
		this(0.0F, false);
	}

	protected TofunianModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
	}

	public TofunianModel(float modelSize, boolean par2) {
		super(modelSize, 0.0F, 64, par2 ? 32 : 64);
		this.field_78090_t = 64;
		this.field_78089_u = 64;
		this.field_178720_f = new ModelRenderer((Model) this, 32, 0);
		this.field_178720_f.func_78793_a(0.0F, 13.0F, -0.0F);
		this.field_178720_f.func_228301_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSize + 0.5F);
		this.field_78116_c = new ModelRenderer((Model) this, 0, 0);
		this.field_78116_c.func_78793_a(0.0F, 13.0F, -0.0F);
		this.field_78116_c.func_228301_a_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSize);
		this.field_78116_c.func_228301_a_(-1.5F, -11.0F, -0.0F, 3.0F, 3.0F, 0.0F, modelSize);
		this.field_178723_h = new ModelRenderer((Model) this, 28, 16);
		this.field_178723_h.func_78793_a(-4.0F, 15.0F, 0.0F);
		this.field_178723_h.func_228301_a_(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		setRotateAngle(this.field_178723_h, 0.0F, 0.0F, 0.10000737F);
		this.field_78115_e = new ModelRenderer((Model) this, 8, 16);
		this.field_78115_e.func_78793_a(0.0F, 14.0F, 0.0F);
		this.field_78115_e.func_228301_a_(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, modelSize);
		this.field_178724_i = new ModelRenderer((Model) this, 28, 16);
		this.field_178724_i.field_78809_i = true;
		this.field_178724_i.func_78793_a(4.0F, 15.0F, 0.0F);
		this.field_178724_i.func_228301_a_(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		setRotateAngle(this.field_178724_i, 0.0F, 0.0F, -0.10000737F);
		this.field_178721_j = new ModelRenderer((Model) this, 0, 16);
		this.field_178721_j.func_78793_a(-1.4F, 18.0F, 0.0F);
		this.field_178721_j.func_228301_a_(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize);
		this.field_178722_k = new ModelRenderer((Model) this, 0, 16);
		this.field_178722_k.field_78809_i = true;
		this.field_178722_k.func_78793_a(1.4F, 18.0F, 0.0F);
		this.field_178722_k.func_228301_a_(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.func_225597_a_((LivingEntity) entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float f6 = 12.0F;
		this.field_178723_h.field_78800_c = -4.0F;
		this.field_178724_i.field_78800_c = 4.0F;
		this.field_178723_h.field_78798_e = 0.0F;
		this.field_178724_i.field_78798_e = 0.0F;
		this.field_178721_j.field_78798_e = 0.0F;
		this.field_178722_k.field_78798_e = 0.0F;
		this.field_178721_j.field_78797_d = 6.0F + f6;
		this.field_178722_k.field_78797_d = 6.0F + f6;
		this.field_78116_c.field_78798_e = -0.0F;
		this.field_78116_c.field_78797_d = f6 + 2.0F;
		if (this.field_217113_d) {
			this.field_78115_e.field_78795_f = 0.5F;
			this.field_178723_h.field_78795_f += 0.4F;
			this.field_178724_i.field_78795_f += 0.4F;
			this.field_178721_j.field_78798_e = 3.9F;
			this.field_178722_k.field_78798_e = 3.9F;
			this.field_178721_j.field_78797_d = 6.0F + f6 + 0.2F;
			this.field_178722_k.field_78797_d = 6.0F + f6 + 0.2F;
			this.field_78116_c.field_78797_d = f6 + 2.0F + 4.2F;
			this.field_78115_e.field_78797_d = 17.2F;
			this.field_178724_i.field_78797_d = 18.2F;
			this.field_178723_h.field_78797_d = 18.2F;
		} else {
			this.field_78115_e.field_78795_f = 0.0F;
			this.field_178721_j.field_78798_e = 0.0F;
			this.field_178722_k.field_78798_e = 0.0F;
			this.field_178721_j.field_78797_d = 6.0F + f6;
			this.field_178722_k.field_78797_d = 6.0F + f6;
			this.field_78116_c.field_78797_d = f6 + 2.0F;
			this.field_78115_e.field_78797_d = 14.0F;
			this.field_178724_i.field_78797_d = 15.0F;
			this.field_178723_h.field_78797_d = 15.0F;
		}
		boolean flag = (entityIn.func_213719_ec() > 0);
		if (flag) {
			this.field_78116_c.field_78808_h = 0.3F * MathHelper.func_76126_a(0.45F * ageInTicks);
			this.field_78116_c.field_78795_f = 0.4F;
		} else {
			this.field_78116_c.field_78808_h = 0.0F;
		}
		if (this.field_217114_e) {
			this.field_78116_c.field_78797_d = f6 + 1.0F;
		} else {
			this.field_78116_c.field_78797_d = f6 + 2.0F;
		}
		this.field_178720_f.field_78800_c = this.field_78116_c.field_78800_c;
		this.field_178720_f.field_78797_d = this.field_78116_c.field_78797_d;
		this.field_178720_f.field_78798_e = this.field_78116_c.field_78798_e;
		this.field_178720_f.field_78795_f = this.field_78116_c.field_78795_f;
		this.field_178720_f.field_78796_g = this.field_78116_c.field_78796_g;
		this.field_178720_f.field_78808_h = this.field_78116_c.field_78808_h;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.field_78795_f = x;
		modelRenderer.field_78796_g = y;
		modelRenderer.field_78808_h = z;
	}
}
