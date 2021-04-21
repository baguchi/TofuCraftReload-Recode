package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.client.render.layer.AdvancedHeldItemLayer;
import baguchan.tofucraft.client.render.layer.TofunianRoleLayer;
import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianRender<T extends TofunianEntity> extends MobRenderer<T, TofunianModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("tofucraft:textures/entity/tofunian/tofunian.png");

	public TofunianRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, (EntityModel) new TofunianModel(), 0.5F);
		func_177094_a((LayerRenderer) new BipedArmorLayer((IEntityRenderer) this, new BipedModel(0.25F), new BipedModel(0.5F)));
		func_177094_a((LayerRenderer) new HeadLayer((IEntityRenderer) this));
		func_177094_a((LayerRenderer) new ElytraLayer((IEntityRenderer) this));
		func_177094_a((LayerRenderer) new AdvancedHeldItemLayer((IEntityRenderer) this));
		func_177094_a((LayerRenderer) new TofunianRoleLayer((IEntityRenderer) this));
	}

	public ResourceLocation getTextureLocation(T entity) {
		return TEXTURE;
	}
}
