package baguchan.tofucraft.client.render.entity;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.TofuModelLayers;
import baguchan.tofucraft.client.model.TofunianModel;
import baguchan.tofucraft.client.render.layer.TofunianClothLayer;
import baguchan.tofucraft.client.render.layer.TofunianEyeLayer;
import baguchan.tofucraft.client.render.layer.TofunianRoleLayer;
import baguchan.tofucraft.entity.Tofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@OnlyIn(Dist.CLIENT)
public class TofunianRender extends MobRenderer<Tofunian, TofunianModel<Tofunian>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian.png");
	public static final ResourceLocation BAGU_LOCATION = new ResourceLocation(TofuCraftReload.MODID, "textures/entity/tofunian/secret/bagunian.png");

	public TofunianRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofunianModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFUNIAN)), 0.5F);
		this.addLayer(new TofunianEyeLayer<>(this));
		this.addLayer(new TofunianClothLayer(this));
		this.addLayer(new TofunianRoleLayer(this));
		this.addLayer(new CustomArmorLayer<>(this, p_173956_));
		this.addLayer(new ItemInHandLayer<>(this, p_173956_.getItemInHandRenderer()));
	}

	protected void scale(Tofunian p_116314_, PoseStack p_116315_, float p_116316_) {
		super.scale(p_116314_, p_116315_, p_116316_);
		float var4 = p_116314_.getScale();
		if (p_116314_.isBaby()) {
			this.shadowRadius = 0.25F;
		} else {
			this.shadowRadius = 0.5F;
		}

		p_116315_.scale(var4, var4, var4);
	}

	public ResourceLocation getTextureLocation(Tofunian entity) {
		String s = ChatFormatting.stripFormatting(entity.getName().getString());
		if (s != null && "bagu_chan".equals(s)) {
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);
			if ((j == 10 && i == 31) || (j == 12 && i == 15)) {
				return BAGU_LOCATION;
			}
		}
		return LOCATION;
	}
}
