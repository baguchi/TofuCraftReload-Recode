package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.client.render.layer.TofunianItemInHandLayer;
import baguchan.tofucraft.client.render.layer.TofunianRoleLayer;
import baguchan.tofucraft.entity.TofunianEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianRender extends MobRenderer<TofunianEntity, TofunianModel<TofunianEntity>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian.png");

	public TofunianRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofunianModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFUNIAN)), 0.5F);
		this.addLayer(new TofunianRoleLayer(this));
		this.addLayer(new TofunianItemInHandLayer<>(this));
	}

	protected void scale(TofunianEntity p_116314_, PoseStack p_116315_, float p_116316_) {
		float var4 = p_116314_.getScale();
		if (p_116314_.isBaby()) {
			this.shadowRadius = 0.25F;
		} else {
			this.shadowRadius = 0.5F;
		}

		p_116315_.scale(var4, var4, var4);
	}

	public ResourceLocation getTextureLocation(TofunianEntity p_114029_) {
		return LOCATION;
	}
}
