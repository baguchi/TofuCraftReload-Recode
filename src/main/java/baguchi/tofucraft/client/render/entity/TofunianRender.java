package baguchi.tofucraft.client.render.entity;

import baguchi.bagus_lib.client.layer.CustomArmorLayer;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.layer.TofunianClothLayer;
import baguchi.tofucraft.client.render.layer.TofunianEyeLayer;
import baguchi.tofucraft.client.render.layer.TofunianRoleLayer;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import baguchi.tofucraft.entity.Tofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

import java.time.LocalDate;
import java.time.temporal.ChronoField;


public class TofunianRender extends MobRenderer<Tofunian, TofunianRenderState, TofunianModel<TofunianRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian.png");
	public static final ResourceLocation BAGU_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/secret/bagunian.png");

	public TofunianRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofunianModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFUNIAN)), 0.5F);
		this.addLayer(new TofunianEyeLayer<>(this));
		this.addLayer(new TofunianClothLayer(this));
		this.addLayer(new TofunianRoleLayer(this));
		this.addLayer(new CustomArmorLayer<>(this, p_173956_));
		this.addLayer(new ItemInHandLayer<>(this) {

			@Override
			public void submit(PoseStack p_433803_, SubmitNodeCollector p_434482_, int p_433450_, TofunianRenderState p_434546_, float p_433047_, float p_433527_) {
				if (!p_434546_.isBaby) {
					super.submit(p_433803_, p_434482_, p_433450_, p_434546_, p_433047_, p_433527_);
				}
			}
		});
	}


	@Override
	protected void scale(TofunianRenderState p_362272_, PoseStack p_115315_) {
		super.scale(p_362272_, p_115315_);
		float var4 = p_362272_.scale;
		if (p_362272_.isBaby) {
			this.shadowRadius = 0.25F;
		} else {
			this.shadowRadius = 0.5F;
		}

		p_115315_.scale(var4, var4, var4);
	}

	public ResourceLocation getTextureLocation(TofunianRenderState entity) {
		if (entity.nameTag != null) {
			String s = ChatFormatting.stripFormatting(entity.nameTag.getString());
			if (s != null && "bagu_chan".equals(s)) {
				LocalDate localdate = LocalDate.now();
				int i = localdate.get(ChronoField.DAY_OF_MONTH);
				int j = localdate.get(ChronoField.MONTH_OF_YEAR);
				if ((j == 10 && i == 31) || (j == 12 && i == 15)) {
					return BAGU_LOCATION;
				}
			}
		}
		if (entity.texture != null) {
			return entity.texture;
		}
		return LOCATION;
	}

	@Override
	public TofunianRenderState createRenderState() {
		return new TofunianRenderState();
	}

	@Override
	public void extractRenderState(Tofunian tofunian, TofunianRenderState renderState, float partialTick) {
		super.extractRenderState(tofunian, renderState, partialTick);
		HumanoidMobRenderer.extractHumanoidRenderState(tofunian, renderState, partialTick, this.itemModelResolver);

		renderState.id = tofunian.getId();
		renderState.isPassenger = tofunian.isPassenger() && (tofunian.getVehicle() != null && tofunian.getVehicle().shouldRiderSit());

		renderState.unhappyCounter = tofunian.getUnhappyCounter();
		renderState.attackTime = tofunian.attackAnim;
		renderState.eatFoodAnimationState.copyFrom(tofunian.eatFoodAnimationState);
		renderState.happyAnimationState.copyFrom(tofunian.happyAnimationState);
		renderState.waveAnimationState.copyFrom(tofunian.waveAnimationState);
		renderState.actions = tofunian.getAction();
		renderState.roles = tofunian.getRole();
		renderState.clothTexture = tofunian.getClothTexture();
		renderState.texture = tofunian.getTexture();
	}
}
