package baguchi.tofucraft.registry;

import baguchi.bagus_lib.event.RegisterBagusAnimationStateEvents;
import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuAnimations {
	public static final Identifier COUGH = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "cough");
	public static final Identifier THROWN_RIGHT = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "thrown_right");
	public static final Identifier THROWN_LEFT = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "thrown_left");
	public static final Identifier BUSTER_RIGHT = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "buster_right");
	public static final Identifier BUSTER_LEFT = Identifier.fromNamespaceAndPath(TofuCraftReload.MODID, "buster_left");

	@SubscribeEvent
	public static void registerAnimation(RegisterBagusAnimationStateEvents events) {
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
