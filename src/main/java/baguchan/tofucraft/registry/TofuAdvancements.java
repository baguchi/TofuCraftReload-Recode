package baguchan.tofucraft.registry;

import baguchan.tofucraft.advancements.ChiliDistractionTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class TofuAdvancements {
	public static final ChiliDistractionTrigger CHILI_DISTRACTION = CriteriaTriggers.register(new ChiliDistractionTrigger());

	public static void init() {
	}
}
