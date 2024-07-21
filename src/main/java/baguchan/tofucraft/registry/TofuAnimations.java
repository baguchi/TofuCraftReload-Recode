package baguchan.tofucraft.registry;

import bagu_chan.bagus_lib.event.RegisterBagusAnimationEvents;
import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuAnimations {
	public static final ResourceLocation COUGH = new ResourceLocation(TofuCraftReload.MODID, "cough");

	@SubscribeEvent
	public static void registerAnimation(RegisterBagusAnimationEvents events) {
		events.addAnimationState(COUGH);
	}
}
