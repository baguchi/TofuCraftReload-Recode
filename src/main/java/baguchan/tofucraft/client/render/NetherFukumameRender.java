package baguchan.tofucraft.client.render;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherFukumameRender extends AbstractSoybeanRender<NetherFukumameEntity> {
	public static final ResourceLocation FUKUMAME_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/projectiles/nether_fukumame.png");

	public NetherFukumameRender(EntityRendererProvider.Context p_174399_) {
		super(p_174399_);
	}

	public ResourceLocation getTextureLocation(NetherFukumameEntity p_116001_) {
		return FUKUMAME_LOCATION;
	}
}