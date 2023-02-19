package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.TofuCreeper;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuCreeperRender extends CreeperRenderer {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofucreeper.png");

	public TofuCreeperRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_);
	}

	public ResourceLocation getTextureLocation(TofuCreeper p_114029_) {
		return LOCATION;
	}
}
