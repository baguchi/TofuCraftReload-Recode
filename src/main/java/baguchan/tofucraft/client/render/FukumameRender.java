package baguchan.tofucraft.client.render;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FukumameRender extends AbstractSoybeanRender<FukumameEntity> {
	public static final ResourceLocation FUKUMAME_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/projectiles/fukumame.png");

	public FukumameRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	public ResourceLocation getTextureLocation(FukumameEntity p_116001_) {
		return FUKUMAME_LOCATION;
	}
}