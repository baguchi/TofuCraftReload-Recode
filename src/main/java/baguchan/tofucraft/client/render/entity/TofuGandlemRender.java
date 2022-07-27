package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuGandlemModel;
import baguchan.tofucraft.client.render.layer.TofuGandlemEmissiveLayer;
import baguchan.tofucraft.entity.TofuGandlem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuGandlemRender<T extends TofuGandlem> extends MobRenderer<T, TofuGandlemModel<T>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem.png");

	public TofuGandlemRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofuGandlemModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFU_GANDLEM)), 0.5F);
		this.addLayer(new TofuGandlemEmissiveLayer<>(this, LOCATION, (p_234809_, p_234810_, p_234811_) -> {
			return p_234809_.isAlive() ? Mth.clamp(Mth.cos(p_234809_.getHealth() / p_234809_.getMaxHealth()) * 0.2F, 0.1F, 1.0F) : 0.0F;
		}, TofuGandlemModel::getCoreModelParts));
	}

	protected float getFlipDegrees(T p_115337_) {
		return 0.0F;
	}


	public ResourceLocation getTextureLocation(T p_114029_) {
		return LOCATION;
	}
}
