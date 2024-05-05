package baguchan.tofucraft.client;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.client.screen.ReceivingTofuLevelScreen;
import baguchan.tofucraft.registry.TofuDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT)
public class TofuCommonEvents {
	@SubscribeEvent
	public static void onScreenOpeningEvent(ScreenEvent.Opening event) {
		if (event.getNewScreen() instanceof ReceivingLevelScreen receivingLevelScreen && ((Minecraft.getInstance().cameraEntity != null && Minecraft.getInstance().cameraEntity.level().dimension() == TofuDimensions.tofu_world))) {
			event.setNewScreen(new ReceivingTofuLevelScreen(receivingLevelScreen.levelReceived));
		}
	}
}
