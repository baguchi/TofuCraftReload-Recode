package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.TofuCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuCowRender extends MobRenderer<TofuCow, CowModel<TofuCow>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofucow/tofucow.png");
	private static final ResourceLocation TOFU_COW_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofucow/tofuzundacow.png");

	public TofuCowRender(EntityRendererProvider.Context p_174304_) {
		super(p_174304_, new CowModel<>(p_174304_.bakeLayer(ModelLayers.COW)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(TofuCow entity) {
		String type = "";

		if (entity.getTofuCowType() != TofuCow.TofuCowType.NORMAL)
			type = "_" + entity.getTofuCowType().name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofucow/tofucow" + type + ".png");
	}
}