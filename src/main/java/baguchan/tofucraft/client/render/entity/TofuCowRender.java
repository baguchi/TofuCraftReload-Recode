package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.entity.TofuCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuCowRender extends MobRenderer<TofuCowEntity, CowModel<TofuCowEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("tofucraft:textures/entity/tofucow/tofucow.png");

	public TofuCowRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, (EntityModel) new CowModel(), 0.7F);
	}

	public ResourceLocation getTextureLocation(TofuCowEntity entity) {
		return TEXTURE;
	}
}
