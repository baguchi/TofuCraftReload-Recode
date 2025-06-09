package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuAnimations {
	public static final ResourceLocation COUGH = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "cough");
	public static final ResourceLocation THROWN_RIGHT = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "thrown_right");
	public static final ResourceLocation THROWN_LEFT = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "thrown_left");
	public static final ResourceLocation BUSTER_RIGHT = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "buster_right");
	public static final ResourceLocation BUSTER_LEFT = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "buster_left");

	@SubscribeEvent
	public static void registerAnimation(baguchi.bagus_lib.event.RegisterBagusAnimationEvents events) {
		if (events.getEntity() instanceof LivingEntity) {
			events.addAnimationState(COUGH);
			events.addAnimationState(THROWN_RIGHT);
			events.addAnimationState(THROWN_LEFT);
			events.addFirstPersonPlayableAnimationState(THROWN_RIGHT);
			events.addFirstPersonPlayableAnimationState(THROWN_LEFT);
			events.addAnimationState(BUSTER_RIGHT);
			events.addAnimationState(BUSTER_LEFT);
			events.addFirstPersonPlayableAnimationState(BUSTER_RIGHT);
			events.addFirstPersonPlayableAnimationState(BUSTER_LEFT);
		}
	}
}
