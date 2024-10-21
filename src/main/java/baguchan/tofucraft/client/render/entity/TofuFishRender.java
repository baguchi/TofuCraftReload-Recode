package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofuFishModel;
import baguchan.tofucraft.entity.TofuFish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuFishRender extends MobRenderer<TofuFish, LivingEntityRenderState, TofuFishModel> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofufish.png");

	public TofuFishRender(EntityRendererProvider.Context p_173954_) {
		super(p_173954_, new TofuFishModel(p_173954_.bakeLayer(TofuModelLayers.TOFUFISH)), 0.3F);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	public ResourceLocation getTextureLocation(LivingEntityRenderState p_114015_) {
		return LOCATION;
	}


	@Override
	protected void setupRotations(LivingEntityRenderState p_360516_, PoseStack p_114010_, float p_114011_, float p_114012_) {
		super.setupRotations(p_360516_, p_114010_, p_114011_, p_114012_);
		float f = 4.3F * Mth.sin(0.6F * p_360516_.ageInTicks);
		p_114010_.mulPose(Axis.YP.rotationDegrees(f));
		if (!p_360516_.isInWater) {
			p_114010_.translate(0.1F, 0.1F, -0.1F);
			p_114010_.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}