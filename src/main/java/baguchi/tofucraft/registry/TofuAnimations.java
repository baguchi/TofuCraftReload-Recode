package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuAnimations {
	public static final ResourceLocation COUGH = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "cough");

	@SubscribeEvent
	public static void registerAnimation(baguchi.bagus_lib.event.RegisterBagusAnimationEvents events) {
		events.addAnimationState(COUGH);
	}
}
