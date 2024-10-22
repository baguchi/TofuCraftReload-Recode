package baguchi.tofucraft.client;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.sound.TofuMusicManager;
import net.minecraft.client.Minecraft;
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


	/*@SubscribeEvent
	public static void onAnimateModelEvent(BagusModelEvent.PostAnimate event) {
		BaguAnimationController controller = AnimationUtil.getAnimationController(event.getEntity());
		if (event.isSupportedAnimateModel() && controller != null) {
			rootModel.animateBagu(controller.getAnimationState(TofuAnimations.COUGH), CoughAnimation.COUGH, event.getAgeInTick());
			if (event.getModel() instanceof HumanoidModel<?> humanoidModel) {
				humanoidModel.hat.copyFrom(humanoidModel.head);
			}
		}
	}*/
}
