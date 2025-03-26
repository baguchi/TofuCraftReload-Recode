package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.render.layer.TofuPigTypeLayer;
import baguchi.tofucraft.client.render.state.TofuPigRenderState;
import baguchi.tofucraft.entity.TofuPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SimpleEquipmentLayer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuPigRender extends AgeableMobRenderer<TofuPig, TofuPigRenderState, PigModel> {
	private static final ResourceLocation PIG_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofu_pig/tofu_pig.png");

	public TofuPigRender(EntityRendererProvider.Context p_174304_) {
		super(p_174304_, new PigModel(p_174304_.bakeLayer(ModelLayers.PIG)), new PigModel(p_174304_.bakeLayer(ModelLayers.PIG_BABY)), 0.5F);
		this.addLayer(new TofuPigTypeLayer(this));
		this.addLayer(
				new SimpleEquipmentLayer<>(
						this,
						p_174304_.getEquipmentRenderer(),
						EquipmentClientInfo.LayerType.PIG_SADDLE,
						p_397421_ -> p_397421_.saddle,
						new PigModel(p_174304_.bakeLayer(ModelLayers.PIG_SADDLE)),
						new PigModel(p_174304_.bakeLayer(ModelLayers.PIG_BABY_SADDLE))
				)
		);
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
	public ResourceLocation getTextureLocation(TofuPigRenderState p_114482_) {
		return PIG_LOCATION;
	}

}