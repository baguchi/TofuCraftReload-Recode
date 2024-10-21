package baguchan.tofucraft.client.render.entity.effect;

import baguchan.tofucraft.client.render.state.NattoCobWebRenderState;
import baguchan.tofucraft.entity.effect.NattoCobWebEntity;
import baguchan.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class NattoCobWebRender extends EntityRenderer<NattoCobWebEntity, NattoCobWebRenderState> {
	private final ItemRenderer itemRenderer;

	public NattoCobWebRender(EntityRendererProvider.Context context) {
		super(context);

		this.itemRenderer = context.getItemRenderer();
	}

	@Override
	public void render(NattoCobWebRenderState entity, PoseStack stack, MultiBufferSource buffer, int packedLightIn) {
		boolean isSpawing = entity.isSpawning;
		if (isSpawing) {
			stack.pushPose();
			stack.scale(5.5F, 6.0F, 5.5F);
			stack.mulPose(entity.direction.getOpposite().getRotation());
			stack.translate(0.0F, 0.0F, -0.125F);
			stack.mulPose(Axis.XP.rotationDegrees(90.0F));

			ItemStack itemStack = new ItemStack(TofuItems.NATTO_COBWEB.get());
			BakedModel bakedmodel = this.itemRenderer.getModel(itemStack, null, (LivingEntity) null, 0);

			this.itemRenderer.render(itemStack, ItemDisplayContext.GROUND, false, stack, buffer, packedLightIn, OverlayTexture.NO_OVERLAY, bakedmodel);
			stack.popPose();
		} else {
			stack.pushPose();
			stack.scale(6.0F, 6.0F, 6.0F);
			stack.mulPose(entity.direction.getRotation());
			stack.translate(0.0F, 0.0F, -0.125F);
			stack.mulPose(Axis.XP.rotationDegrees(90.0F));


			ItemStack itemStack = new ItemStack(TofuItems.NATTO_COBWEB.get());
			BakedModel bakedmodel = this.itemRenderer.getModel(itemStack, null, (LivingEntity) null, 0);

			this.itemRenderer.render(itemStack, ItemDisplayContext.GROUND, false, stack, buffer, packedLightIn, OverlayTexture.NO_OVERLAY, bakedmodel);
			stack.popPose();
		}
		super.render(entity, stack, buffer, packedLightIn);
	}

	@Override
	public NattoCobWebRenderState createRenderState() {
		return new NattoCobWebRenderState();
	}

	@Override
	public void extractRenderState(NattoCobWebEntity p_362104_, NattoCobWebRenderState p_361028_, float p_362204_) {
		super.extractRenderState(p_362104_, p_361028_, p_362204_);
		p_361028_.direction = p_362104_.getAttachFace();
		p_361028_.isSpawning = p_362104_.isSpawing();
	}
}