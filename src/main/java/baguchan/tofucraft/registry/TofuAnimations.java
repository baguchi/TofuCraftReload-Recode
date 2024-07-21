package baguchan.tofucraft.registry;

import bagu_chan.bagus_lib.event.RegisterBagusAnimationEvents;
import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuAnimations {
	public static final ResourceLocation COUGH = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "cough");

	@SubscribeEvent
	public static void registerAnimation(RegisterBagusAnimationEvents events) {
		events.addAnimationState(COUGH);
	}
}
