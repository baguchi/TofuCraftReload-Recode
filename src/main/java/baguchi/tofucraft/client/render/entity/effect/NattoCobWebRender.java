package baguchi.tofucraft.client.render.entity.effect;

import baguchi.tofucraft.client.render.state.NattoCobWebRenderState;
import baguchi.tofucraft.entity.effect.NattoCobWebEntity;
import baguchi.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;


public class NattoCobWebRender extends EntityRenderer<NattoCobWebEntity, NattoCobWebRenderState> {
	private final ItemModelResolver itemModelResolver;

	public NattoCobWebRender(EntityRendererProvider.Context context) {
		super(context);

		this.itemModelResolver = context.getItemModelResolver();
	}

	@Override
	public void submit(NattoCobWebRenderState entity, PoseStack stack, SubmitNodeCollector buffer, CameraRenderState cameraRenderState) {
		boolean isSpawing = entity.isSpawning;
		if (isSpawing) {
			stack.pushPose();
			stack.scale(5.5F, 6.0F, 5.5F);
			stack.mulPose(entity.direction.getOpposite().getRotation());
			stack.translate(0.0F, 0.0F, -0.125F);
			stack.mulPose(Axis.XP.rotationDegrees(90.0F));

			entity.item.submit(stack, buffer, entity.lightCoords, OverlayTexture.NO_OVERLAY, entity.outlineColor);
			stack.popPose();
		} else {
			stack.pushPose();
			stack.scale(6.0F, 6.0F, 6.0F);
			stack.mulPose(entity.direction.getRotation());
			stack.translate(0.0F, 0.0F, -0.125F);
			stack.mulPose(Axis.XP.rotationDegrees(90.0F));

			entity.item.submit(stack, buffer, entity.lightCoords, OverlayTexture.NO_OVERLAY, entity.outlineColor);
			stack.popPose();
		}
		super.submit(entity, stack, buffer, cameraRenderState);
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
		this.itemModelResolver.updateForNonLiving(p_361028_.item, TofuItems.NATTO_COBWEB.get().getDefaultInstance(), ItemDisplayContext.GROUND, p_362104_);

	}
}