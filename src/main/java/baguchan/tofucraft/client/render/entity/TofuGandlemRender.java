package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuGandlemModel;
import baguchan.tofucraft.client.render.layer.TofuGandlemChargedLayer;
import baguchan.tofucraft.client.render.layer.TofuGandlemEmissiveLayer;
import baguchan.tofucraft.entity.TofuGandlem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuGandlemRender<T extends TofuGandlem> extends MobRenderer<T, TofuGandlemModel<T>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem.png");
	private static final ResourceLocation SHOOTING_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem_shooting.png");

	private static final ResourceLocation CHARGE_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofu_gandlem/tofu_gandlem_charge.png");

	public TofuGandlemRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofuGandlemModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFU_GANDLEM)), 0.5F);
		this.addLayer(new TofuGandlemEmissiveLayer<>(this, LOCATION, (p_234809_, p_234810_, p_234811_) -> {
			return p_234809_.isAlive() && !p_234809_.isSleep() ? Mth.clamp(p_234809_.getHealth() / p_234809_.getMaxHealth() + 0.4F, 0.4F, 1.0F) : 0.0F;
		}, TofuGandlemModel::getCoreModelParts));
		this.addLayer(new TofuGandlemChargedLayer<>(this, CHARGE_LOCATION, (p_234809_, p_234810_, p_234811_) -> {
			return p_234809_.isAlive() ? 1.0F : 0.0F;
		}, TofuGandlemModel::getCoreModelParts));
	}

	protected float getFlipDegrees(T p_115337_) {
		return 0.0F;
	}


	public ResourceLocation getTextureLocation(T p_114029_) {
		return p_114029_.isCharging() || p_114029_.isShoot() ? SHOOTING_LOCATION : LOCATION;
	}
}
