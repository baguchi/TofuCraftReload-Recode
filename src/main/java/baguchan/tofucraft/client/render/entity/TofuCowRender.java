package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuCowRender extends CowRenderer {
	private static final ResourceLocation COW_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofucow/tofucow.png");


	public TofuCowRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_);
	}

	@Override
	public ResourceLocation getTextureLocation(Cow p_114029_) {
		return COW_LOCATION;
	}
}
