package baguchan.tofucraft.client;

import bagu_chan.bagus_lib.animation.BaguAnimationController;
import bagu_chan.bagus_lib.api.client.IRootModel;
import bagu_chan.bagus_lib.client.event.BagusModelEvent;
import bagu_chan.bagus_lib.util.client.AnimationUtil;
import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.animation.definitions.CoughAnimation;
import baguchan.tofucraft.client.screen.ReceivingTofuLevelScreen;
import baguchan.tofucraft.registry.TofuAnimations;
import baguchan.tofucraft.registry.TofuDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT)
public class TofuClientEvents {
	@SubscribeEvent
	public static void onScreenOpeningEvent(ScreenEvent.Opening event) {
		if (event.getNewScreen() instanceof ReceivingLevelScreen receivingLevelScreen && ((Minecraft.getInstance().cameraEntity != null && Minecraft.getInstance().cameraEntity.level().dimension() == TofuDimensions.tofu_world))) {
			event.setNewScreen(new ReceivingTofuLevelScreen(receivingLevelScreen.levelReceived));
		}
	}

	@SubscribeEvent
	public static void onInitModelEvent(BagusModelEvent.Init event) {
		IRootModel rootModel = event.getRootModel();
		if (rootModel != null) {
			rootModel.getBagusRoot().getAllParts().forEach(ModelPart::resetPose);
		}
	}

	@SubscribeEvent
	public static void onAnimateModelEvent(BagusModelEvent.PostAnimate event) {
		IRootModel rootModel = event.getRootModel();
		BaguAnimationController controller = AnimationUtil.getAnimationController(event.getEntity());
		if (rootModel != null && controller != null) {
			rootModel.animateBagu(controller.getAnimationState(TofuAnimations.COUGH), CoughAnimation.COUGH, event.getAgeInTick());
			if (event.getModel() instanceof HumanoidModel<?> humanoidModel) {
				humanoidModel.hat.copyFrom(humanoidModel.head);
			}
		}
	}
}
