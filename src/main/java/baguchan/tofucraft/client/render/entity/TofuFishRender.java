package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuFishModel;
import baguchan.tofucraft.entity.TofuFish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuFishRender extends MobRenderer<TofuFish, TofuFishModel<TofuFish>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofufish.png");

	public TofuFishRender(EntityRendererProvider.Context p_173954_) {
		super(p_173954_, new TofuFishModel<>(p_173954_.bakeLayer(TofuModelLayers.TOFUFISH)), 0.3F);
	}

	public ResourceLocation getTextureLocation(TofuFish p_114015_) {
		return LOCATION;
	}

	protected void setupRotations(TofuFish p_114017_, PoseStack p_114018_, float p_114019_, float p_114020_, float p_114021_) {
		super.setupRotations(p_114017_, p_114018_, p_114019_, p_114020_, p_114021_);
		float f = 4.3F * Mth.sin(0.6F * p_114019_);
		p_114018_.mulPose(Axis.YP.rotationDegrees(f));
		if (!p_114017_.isInWater()) {
			p_114018_.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
			p_114018_.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}

	}
}