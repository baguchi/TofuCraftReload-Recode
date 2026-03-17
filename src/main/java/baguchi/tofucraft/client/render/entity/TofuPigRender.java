package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.state.TofuPigRenderState;
import baguchi.tofucraft.entity.TofuPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.animal.pig.BabyPigModel;
import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SimpleEquipmentLayer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;


public class TofuPigRender extends MobRenderer<TofuPig, TofuPigRenderState, PigModel> {
	private static final Identifier PIG_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_pig/tofu_pig.png");
	private static final Identifier PIG_BABY_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_pig/tofu_pig_baby.png");
	private static final Identifier PIG_ZUNDA_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_pig/tofu_pig_zunda.png");
	private static final Identifier PIG_ZUNDA_BABY_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_pig/tofu_pig_zunda_baby.png");
	private static final Identifier PIG_GRILLED_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_pig/tofu_pig_grilled.png");
	private static final Identifier PIG_METAL_LOCATION = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_pig/tofu_pig_metal.png");
	private final AdultAndBabyModelPair<PigModel> models;

	public TofuPigRender(EntityRendererProvider.Context context) {
		super(context, new PigModel(context.bakeLayer(ModelLayers.PIG)), 0.7F);
		this.models = bakeModels(context);
		this.addLayer(
				new SimpleEquipmentLayer<>(
						this,
						context.getEquipmentRenderer(),
						EquipmentClientInfo.LayerType.PIG_SADDLE,
						state -> state.saddle,
						new PigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)),
						null
				)
		);
	}

	private static AdultAndBabyModelPair<PigModel> bakeModels(EntityRendererProvider.Context context) {
		return new AdultAndBabyModelPair<>(
				new PigModel(context.bakeLayer(ModelLayers.PIG)), new BabyPigModel(context.bakeLayer(ModelLayers.PIG_BABY))
		);
	}

	@Override
	public void submit(TofuPigRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		this.model = this.models.getModel(state.isBaby);
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	public TofuPigRenderState createRenderState() {
		return new TofuPigRenderState();
	}

	@Override
	public void extractRenderState(TofuPig p_362733_, TofuPigRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.type = p_362733_.getTofuPigType();
		p_360515_.saddle = p_362733_.getItemBySlot(EquipmentSlot.SADDLE).copy();
	}

	@Override
	public Identifier getTextureLocation(TofuPigRenderState renderState) {
		if (renderState.isBaby) {
			if (renderState.type == TofuPig.TofuPigType.ZUNDA) {
				return PIG_ZUNDA_BABY_LOCATION;
			}
			return PIG_BABY_LOCATION;
		}

		if (renderState.type == TofuPig.TofuPigType.ZUNDA) {
			return PIG_ZUNDA_LOCATION;
		}

		if (renderState.type == TofuPig.TofuPigType.GRILLED) {
			return PIG_GRILLED_LOCATION;
		}

		if (renderState.type == TofuPig.TofuPigType.METAL) {
			return PIG_METAL_LOCATION;
		}

		return PIG_LOCATION;
	}

}