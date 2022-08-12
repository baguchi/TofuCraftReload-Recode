package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.render.layer.TofuPigTypeLayer;
import baguchan.tofucraft.entity.TofuPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuPigRender extends MobRenderer<TofuPig, PigModel<TofuPig>> {
	private static final ResourceLocation PIG_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofupig/tofupig.png");

	public TofuPigRender(EntityRendererProvider.Context p_174304_) {
		super(p_174304_, new PigModel<>(p_174304_.bakeLayer(ModelLayers.PIG)), 0.5F);
		this.addLayer(new TofuPigTypeLayer(this));
		this.addLayer(new SaddleLayer<>(this, new PigModel<>(p_174304_.bakeLayer(ModelLayers.PIG_SADDLE)), new ResourceLocation("textures/entity/pig/pig_saddle.png")));
	}

	@Override
	public ResourceLocation getTextureLocation(TofuPig p_114482_) {
		return PIG_LOCATION;
	}

}