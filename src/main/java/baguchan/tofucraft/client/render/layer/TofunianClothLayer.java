package baguchan.tofucraft.client.render.layer;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.entity.Tofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianClothLayer extends RenderLayer<Tofunian, TofunianModel<Tofunian>> {
	public static final ResourceLocation BAGU_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofunian/bagu_chan.png");

	public TofunianClothLayer(RenderLayerParent<Tofunian, TofunianModel<Tofunian>> tofunianRender) {
		super(tofunianRender);
	}

	public void render(PoseStack p_117720_, MultiBufferSource p_117721_, int p_117722_, Tofunian p_117723_, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
		if (!p_117723_.isInvisible() && p_117723_.getTofunianType() != Tofunian.TofunianType.NORMAL) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(p_117723_), p_117720_, p_117721_, p_117722_, p_117723_, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(Tofunian entity) {
		String s = ChatFormatting.stripFormatting(entity.getName().getString());
		if (s != null && "bagu_chan".equals(s)) {
			return BAGU_LOCATION;
		}

		String cloth = "";
		if (entity.getTofunianType() != Tofunian.TofunianType.NORMAL)
			cloth = entity.getTofunianType().name().toLowerCase();
		return new ResourceLocation("tofucraft:textures/entity/tofunian/" + cloth + "_cloth.png");
	}
}
