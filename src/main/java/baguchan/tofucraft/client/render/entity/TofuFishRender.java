package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.TofuFishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuFishRender extends MobRenderer<TofuFishEntity, CodModel<TofuFishEntity>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofufish.png");

	public TofuFishRender(EntityRendererProvider.Context p_173954_) {
		super(p_173954_, new CodModel<>(p_173954_.bakeLayer(ModelLayers.COD)), 0.3F);
	}

	public ResourceLocation getTextureLocation(TofuFishEntity p_114015_) {
		return LOCATION;
	}

	protected void setupRotations(TofuFishEntity p_114017_, PoseStack p_114018_, float p_114019_, float p_114020_, float p_114021_) {
		super.setupRotations(p_114017_, p_114018_, p_114019_, p_114020_, p_114021_);
		float f = 4.3F * Mth.sin(0.6F * p_114019_);
		p_114018_.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!p_114017_.isInWater()) {
			p_114018_.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
			p_114018_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}

	}
}