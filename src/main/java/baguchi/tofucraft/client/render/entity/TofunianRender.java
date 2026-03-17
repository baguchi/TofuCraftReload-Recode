package baguchi.tofucraft.client.render.entity;

import baguchi.bagus_lib.client.layer.CustomArmorLayer;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TofunianBabyModel;
import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.layer.TofunianClothLayer;
import baguchi.tofucraft.client.render.layer.TofunianEyeLayer;
import baguchi.tofucraft.client.render.layer.TofunianRoleLayer;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import baguchi.tofucraft.entity.tofunian.Tofunian;
import baguchi.tofucraft.registry.TofunianProfessions;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

import java.time.LocalDate;
import java.time.temporal.ChronoField;


public class TofunianRender extends MobRenderer<Tofunian, TofunianRenderState, TofunianModel<TofunianRenderState>> {
	private static final Identifier LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian.png");
	private static final Identifier LOCATION_BABY = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/variant/tofunian_plain_baby.png");
	public static final Identifier BAGU_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/secret/bagunian.png");

	private final AdultAndBabyModelPair<TofunianModel<TofunianRenderState>> models;


	public TofunianRender(EntityRendererProvider.Context context) {
		super(context, new TofunianModel<>(context.bakeLayer(TofuModelLayers.TOFUNIAN)), 0.5F);
		this.addLayer(new TofunianEyeLayer<>(this));
		this.addLayer(new TofunianClothLayer<>(this));
		this.addLayer(new TofunianRoleLayer(this));
		this.addLayer(new CustomArmorLayer<>(this, context));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
		this.addLayer(new ItemInHandLayer<>(this));
		this.models = bakeModels(context);
	}

	private static AdultAndBabyModelPair<TofunianModel<TofunianRenderState>> bakeModels(EntityRendererProvider.Context context) {
		return new AdultAndBabyModelPair<>(
				new TofunianModel<>(context.bakeLayer(TofuModelLayers.TOFUNIAN)), new TofunianBabyModel<>(context.bakeLayer(TofuModelLayers.TOFUNIAN_BABY))
		);
	}


	@Override
	public void submit(TofunianRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		this.model = this.models.getModel(state.isBaby);
		super.submit(state, poseStack, submitNodeCollector, camera);
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

	public Identifier getTextureLocation(TofunianRenderState entity) {
		if (!entity.isBaby && entity.nameTag != null) {
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

		if (entity.isBaby) {
			return LOCATION_BABY;
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
		renderState.craftingAnimationState.copyFrom(tofunian.craftingAnimationState);
		renderState.craftOnceAnimationState.copyFrom(tofunian.craftOnceAnimationState);
		renderState.actions = tofunian.getAction();
		if (tofunian.getRole().is(TofunianProfessions.NONE)) {
			renderState.rolesTexture = null;
		} else {
			renderState.rolesTexture = tofunian.getRole().getKey().identifier();
		}

		renderState.clothTexture = tofunian.getClothTexture();
		renderState.texture = tofunian.getTexture();
	}
}
