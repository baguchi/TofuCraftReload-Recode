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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@OnlyIn(Dist.CLIENT)
public class TofunianClothLayer extends RenderLayer<Tofunian, TofunianModel<Tofunian>> {
	public static final ResourceLocation BAGU_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/secret/bagu_chan.png");
	public static final ResourceLocation ZUNDAMON_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/secret/zundamon.png");


	public TofunianClothLayer(RenderLayerParent<Tofunian, TofunianModel<Tofunian>> tofunianRender) {
		super(tofunianRender);
	}

	public void render(
			PoseStack p_117148_,
			MultiBufferSource p_117149_,
			int p_117150_,
			Tofunian p_117151_,
			float p_117152_,
			float p_117153_,
			float p_117154_,
			float p_117155_,
			float p_117156_,
			float p_117157_
	) {
		if (!p_117151_.isInvisible() && p_117151_.getTofunianType() != Tofunian.TofunianType.NORMAL) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(p_117151_), p_117148_, p_117149_, p_117150_, p_117151_, -1);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(Tofunian entity) {
		String s = ChatFormatting.stripFormatting(entity.getName().getString());
		if (s != null && "bagu_chan".equals(s)) {
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);
			if (!(j == 10 && i == 31 || (j == 12 && i == 15))) {
				return BAGU_LOCATION;
			}
		}

		if (s != null && ("zundamon".equals(s))) {
			return ZUNDAMON_LOCATION;
		}

		String cloth = "";
		if (entity.getTofunianType() != Tofunian.TofunianType.NORMAL)
			cloth = entity.getTofunianType().name().toLowerCase();
		return ResourceLocation.parse("tofucraft:textures/entity/tofunian/" + cloth + "_cloth.png");
	}
}
