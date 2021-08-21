package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuSlimeRender extends SlimeRenderer {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofuslime.png");


	public TofuSlimeRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_);
	}

	@Override
	public ResourceLocation getTextureLocation(Slime p_114029_) {
		return LOCATION;
	}
}
