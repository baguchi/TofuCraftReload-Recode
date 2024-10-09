package baguchan.tofucraft.client;

import bagu_chan.bagus_lib.animation.BaguAnimationController;
import bagu_chan.bagus_lib.api.client.IRootModel;
import bagu_chan.bagus_lib.client.event.BagusModelEvent;
import bagu_chan.bagus_lib.util.client.AnimationUtil;
import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.animation.definitions.CoughAnimation;
import baguchan.tofucraft.client.sound.TofuMusicManager;
import baguchan.tofucraft.registry.TofuAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT)
public class TofuClientEvents {

	@SubscribeEvent
	public static void onClientTickEvent(ClientTickEvent.Post event) {
		Player player = Minecraft.getInstance().player;
		if (player != null && !Minecraft.getInstance().isPaused()) {
			TofuMusicManager.tick();
		}
	}

	@SubscribeEvent
	public static void onInitModelEvent(BagusModelEvent.Init event) {
		IRootModel rootModel = event.getRootModel();
		if (event.isSupportedAnimateModel()) {
			rootModel.getBagusRoot().getAllParts().forEach(ModelPart::resetPose);
		}
	}

	@SubscribeEvent
	public static void onAnimateModelEvent(BagusModelEvent.PostAnimate event) {
		IRootModel rootModel = event.getRootModel();
		BaguAnimationController controller = AnimationUtil.getAnimationController(event.getEntity());
		if (event.isSupportedAnimateModel() && controller != null) {
			rootModel.animateBagu(controller.getAnimationState(TofuAnimations.COUGH), CoughAnimation.COUGH, event.getAgeInTick());
			if (event.getModel() instanceof HumanoidModel<?> humanoidModel) {
				humanoidModel.hat.copyFrom(humanoidModel.head);
			}
		}
	}
}
