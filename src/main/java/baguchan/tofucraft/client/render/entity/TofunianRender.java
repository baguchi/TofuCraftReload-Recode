package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.entity.TofunianEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianRender extends MobRenderer<TofunianEntity, TofunianModel<TofunianEntity>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian.png");

	public TofunianRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofunianModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFUNIAN)), 0.5F);
		this.addLayer(new ItemInHandLayer<>(this));
	}

	public ResourceLocation getTextureLocation(TofunianEntity p_114029_) {
		return LOCATION;
	}
}
