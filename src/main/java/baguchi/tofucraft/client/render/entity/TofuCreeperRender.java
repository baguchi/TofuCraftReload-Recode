package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;


public class TofuCreeperRender extends CreeperRenderer {
	private static final Identifier LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_creeper/tofu_creeper.png");
	private static final Identifier POWER_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_creeper/tofu_creeper_zunda.png");

	public TofuCreeperRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_);
	}

	@Override
	public Identifier getTextureLocation(CreeperRenderState p_114029_) {
		if (p_114029_.isPowered) {
			return POWER_LOCATION;
		}
		return LOCATION;
	}
}
