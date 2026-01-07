package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.state.TofuCowRenderState;
import baguchi.tofucraft.entity.TofuCow;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.animal.cow.BabyCowModel;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;


public class TofuCowRender extends MobRenderer<TofuCow, TofuCowRenderState, CowModel> {
	private static final Identifier TOFU_COW_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_cow/tofu_cow.png");
	private static final Identifier TOFU_COW_BABY_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_cow/tofu_cow_baby.png");
	private static final Identifier TOFU_COW_ZUNDA_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_cow/tofu_cow_zunda.png");
	private static final Identifier TOFU_COW_ZUNDA_BABY_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_cow/tofu_cow_zunda_baby.png");

	private final AdultAndBabyModelPair<CowModel> models;

	public TofuCowRender(EntityRendererProvider.Context context) {
		super(context, new CowModel(context.bakeLayer(ModelLayers.MOOSHROOM)), 0.7F);
		this.models = bakeModels(context);
	}

	private static AdultAndBabyModelPair<CowModel> bakeModels(EntityRendererProvider.Context context) {
		return new AdultAndBabyModelPair<>(
				new CowModel(context.bakeLayer(ModelLayers.MOOSHROOM)), new BabyCowModel(context.bakeLayer(ModelLayers.MOOSHROOM_BABY))
		);
	}


	@Override
	public void submit(TofuCowRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		this.model = this.models.getModel(state.isBaby);
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	public TofuCowRenderState createRenderState() {
		return new TofuCowRenderState();
	}

	@Override
	public Identifier getTextureLocation(TofuCowRenderState entity) {
		if (entity.isBaby) {
			if (entity.type == TofuCow.TofuCowType.ZUNDA) {
				return TOFU_COW_ZUNDA_BABY_LOCATION;
			}
			return TOFU_COW_BABY_LOCATION;
		}
		if (entity.type == TofuCow.TofuCowType.ZUNDA) {
			return TOFU_COW_ZUNDA_LOCATION;
		}
		return TOFU_COW_LOCATION;
	}

	@Override
	public void extractRenderState(TofuCow p_362733_, TofuCowRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.type = p_362733_.getTofuCowType();
	}
}