package baguchan.tofucraft.client.render.entity;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.entity.TofuSlime;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuSlimeRender extends SlimeRenderer {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofuslime.png");


	public TofuSlimeRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_);
	}

	@Override
	public ResourceLocation getTextureLocation(SlimeRenderState p_114029_) {
		return LOCATION;
	}

	@Override
	protected boolean isShaking(SlimeRenderState p_115304_) {
		return super.isShaking(p_115304_);
	}
}
